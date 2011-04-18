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
	static belongsTo = [judoka:Judoka, club:Club]

    static constraints = {
		judoka(blank:false, nullable:false)
		club(blank:false, nullable:false)
		histVersion(blank:false, nullable:false, unique:'judoka')
		dateCreated()
		lastUpdated()
    }
	
	String toString() {
		judoka.toString() + " - " + club.toString()
	}
	
	static Long retrieveJudokaActualVersion(Judoka judoka) {
		executeQuery("SELECT MAX(histVersion) from JudokaHistoricalClub h " +
			"where h.judoka.dni=:dni", [dni: judoka.dni]).get(0)
	}
}
