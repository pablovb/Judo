package es.liceo.judo.category

import java.io.Serializable;
import org.apache.commons.lang.builder.HashCodeBuilder

import es.liceo.judo.championship.Championship;

/**
 * Clase con información sobre una categoría de un campeonato de judo.
 * 
 * Una categoría pertenece en exclusiva a un campeonato (no todos los campeonatos
 * tienen por qué definir las mismas categorías, ni con los mismos criterios).
 * 
 * Una categoría tiene una lista de pesos, que se almacenan aquí (límite inferior, 
 * límite superior).
 * 
 * @author Pablo
 *
 */
class Category implements Serializable {

	String name
	String sex
	Integer bornAgo
	BigDecimal minWeight
	BigDecimal maxWeight
	static belongsTo = [championship:Championship]
	
	// internacionalizar categorias con plugin i18n?
    static constraints = {
		name(blank:false, nullable:false, 
			inList:["Cadete Sub-17", "Infantil Sub-15", "Alevin Sub-13", "Benjamín", "Benjamín B"])
		sex(blank:false, nullable:false, inList:["Masculino", "Femenino"])
		bornAgo(blank:false, nullable:false)
		minWeight(blank:true, nullable:true, validator:{ val, obj ->
				if (val != null) {
					(obj.maxWeight == null) || (val <= obj.maxWeight)
				} else {
					(obj.maxWeight != null)
				}
			})
		maxWeight(blank:true, nullable:true, validator:{ val, obj ->
				if (val != null) {
					(obj.minWeight == null) || (val >= obj.minWeight)
				} else {
					(obj.minWeight != null)
				}
			})
		championship(blank:false, nullable:false, readonly:true, 
			validator:{ val, obj ->
				if (obj.id == null) {
					/*
					 * si se crea, que el campeonato donde se mete
					 * no tenga esa misma categoria
					 */
					!(obj.championship?.categories?.contains(obj))
				} else {
					/*
					 * si se actualiza, que el campeonato no tenga
					 * esa categoría, o si la tiene, que sea esa misma
					 */
					boolean found = false;
					boolean sameId = false;
					obj.championship?.categories?.each {
						if (it.equals(obj)) {
							found = true;
							if (it.id == obj.id) {
								sameId = true;
							}
						}
					}
					return !found || sameId;
				}
			})
    }
	
	static mapping = {
		sort "championship"
	}
	
	String toString() {
		return name + " " + sex + 
			" (" + minWeight + ", " + maxWeight + ")"
	}
	
	boolean equals(other) {
		if (!(other instanceof Category)) {
			return false
		}
		
		other.name == name && other.sex == sex &&
			other.bornAgo == bornAgo && 
			other.minWeight == minWeight &&
			other.maxWeight == maxWeight
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		builder.append(name)
		builder.append(sex)
		builder.append(bornAgo)
		builder.append(minWeight)
		builder.append(maxWeight)
		builder.toHashCode()
	}
	
}
