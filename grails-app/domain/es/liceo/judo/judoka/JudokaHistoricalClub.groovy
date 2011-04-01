package es.liceo.judo.judoka

import java.io.Serializable;
import java.util.Date;

import es.liceo.judo.club.Club;

/**
* Clase con la información sobre un judoka que 
* varía con el tiempo (el club).
*
* @author Pablo
*
*/
class JudokaHistoricalClub implements Serializable {
	
	Long histVersion
	Date dateCreated
	Date lastUpdated
	static belongsTo = [judoka:Judoka]
	static hasOne = [club:Club]

    static constraints = {
		judoka(blank:false, nullable:false)
		club(blank:true, nullable:true)
		histVersion(blank:false, nullable:false)
		dateCreated()
		lastUpdated()
    }
	
	static mapping = {
		id composite: ['judoka', 'histVersion']
	}
	
	String toString() {
		judoka.toString() + " - " + club.toString()
	}
	
	Long getActualHistVersion(Judoka judoka) {
		executeQuery("SELECT MAX(histVersion) from JudokaHistoricalClub h " +
			"where h.judoka.dni=?", [judoka.dni]).get(0)
	}
}
