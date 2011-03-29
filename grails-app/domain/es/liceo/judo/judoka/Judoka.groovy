package es.liceo.judo.judoka

import java.util.Date;

/**
 * Clase con información sobre un judoka.
 * 
 * Contiene la información estática (no tiene el club,
 * porque puede cambiar a lo largo de su vida).
 * 
 * @author Pablo
 *
 */
class Judoka {
	
	String dni
	String name
	String surname
	String sex
	Date bornDate
	Date dateCreated
	Date lastUpdated
	static hasOne = [historial:JudokaHistorical]

    static constraints = {
		dni(blank:false, nullable:false, unique:true)
		name(blank:false, nullable:false)
		surname(blank:false, nullable:false)
		bornDate(blank:false, nullable:false)
		sex(inList:["Hombre", "Mujer"])
		dateCreated()
		lastUpdated()
    }
	
	static mapping = {
		sort "surname"
	}
	
	String toString() {
		return surname + ", " + name
	}
	
}
