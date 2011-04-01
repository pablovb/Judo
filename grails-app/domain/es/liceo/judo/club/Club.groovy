package es.liceo.judo.club

import es.liceo.judo.judoka.JudokaHistoricalClub;
import es.liceo.judo.security.User

/**
 * Clase con información sobre un Club/Colegio.
 * 
 * @author Pablo
 *
 */
class Club extends User {
	
	String name
	String location
	static hasMany = [judoka:JudokaHistoricalClub]

    static constraints = {
		name(blank:false, nullable:false, unique:"location")
		location(blank:false, nullable:false)
		judoka()
    }
	
	String toString() {
		name + " (" + location + ")"
	}
	
}
