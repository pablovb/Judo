package es.liceo.judo.category

import es.liceo.judo.championship.Championship;

/**
 * 
 * @author Pablo
 *
 */
class Category {

	String name
	String sex
	Integer bornAgo
	BigDecimal minWeight
	BigDecimal maxWeight
	static belongsTo = [championship:Championship]
	
    static constraints = {
		name(blank:false, nullable:false, 
			inList:["Cadete Sub-17", "Infantil Sub-15", "Alevin Sub-13", "Benjamín", "Benjamín B"])
		sex(blank:false, nullable:false, inList:["Masculino", "Femenino"])
		bornAgo(blank:false, nullable:false)
		minWeight(validator:{ val, obj ->
				(((val == null) && obj.maxWeight != null) || (obj.minWeight <= obj.maxWeight))
		})
		maxWeight(validator:{ val, obj ->
				(((val == null && obj.minWeight != null)) || (obj.maxWeight > obj.minWeight))
		})
    }
	
	static mapping = {
		sort "bornAgo"
	}
	
	String toString() {
		return name + " " + sex + 
			" (" + minWeight + ", " + maxWeight + ")"
	}
	
}
