package es.liceo.judo.participation

import es.liceo.judo.category.Category;
import es.liceo.judo.registration.Registration;

class Participation {
	
	BigDecimal weight
	static belongsTo = [registration:Registration, category:Category]

    static constraints = {
		weight(blank:false, nullable: false, range:20..100)
		registration(blank:false, nullable: false)
		category(blank:false, nullable: false)
    }
	
	String toString() {
		return registration.championship.toString() +
			" | " + registration.judoka.judoka.toString() +
			" | " + category.toString()
			" | " + weight + " Kg"
	}
	
}
