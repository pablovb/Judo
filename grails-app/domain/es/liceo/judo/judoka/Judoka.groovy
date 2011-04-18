package es.liceo.judo.judoka

import es.liceo.judo.club.Club
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * Clase con información sobre un judoka.
 * 
 * Contiene la información estática (no tiene el club,
 * porque puede cambiar a lo largo de su vida).
 * 
 * @author Pablo
 *
 */
class Judoka implements Serializable {
	
	String dni
	String name
	String surname
	String sex
	Date bornDate
	Date dateCreated
	Date lastUpdated
	static hasMany = [club:JudokaHistoricalClub]

    static constraints = {
		dni(blank:false, nullable:false, unique:true, maxSize:9)
		name(blank:false, nullable:false)
		surname(blank:false, nullable:false)
		bornDate(blank:false, nullable:false)
		sex(blank:false, nullable:false, inList:["Hombre", "Mujer"])
		club(blank:false, nullable:false)
		dateCreated()
		lastUpdated()
    }
	
	static mapping = {
		sort "surname"
	}
	
	String toString() {
		return surname + ", " + name + " (" + dni + ")"
	}
	
	boolean equals(other) {
		if (!(other instanceof Judoka)) {
			return false
		}
		
		other.dni == dni &&
		other.name == name &&
		other.surname == surname &&
		other.sex == sex &&
		other.bornDate == bornDate
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		builder.append(dni)
		builder.append(name)
		builder.append(surname)
		builder.append(sex)
		builder.append(bornDate)
		builder.toHashCode()
	}
	
	Club retrieveActualClub() {
		JudokaHistoricalClub.findByJudokaAndHistVersion(this, 
			JudokaHistoricalClub.retrieveJudokaActualVersion(this)).club
	}
	
}
