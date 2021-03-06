package es.liceo.judo.registration

import java.io.Serializable;
import java.util.Date;

import es.liceo.judo.championship.Championship;
import es.liceo.judo.judoka.JudokaHistoricalClub;

/**
* Clase con información sobre la inscripción de un judoka
* en un campeonato.
*
* Para inscribirse, es necesario que tenga la licencia actualizada.
*
* @author Pablo
*
*/
class Registration implements Serializable {
	
	BigDecimal weight
	Boolean licenseUpdated
	Date dateCreated
	Date lastUpdated
	static belongsTo = [judoka:JudokaHistoricalClub, championship:Championship]

    static constraints = {
		championship(blank:false, nullable: false)
		judoka(blank:false, nullable: false, unique:'championship')
		weight(blank:false, nullable: false, range:20..100)
		licenseUpdated(nullable: false, default:false, 
			validator:{ val, obj ->
				val
			})
		dateCreated()
		lastUpdated()
    }
	
	String toString() {
		return championship.toString() + 
			" | " + judoka.judoka.toString() + 
			" | " + weight + " Kg"
	}
}
