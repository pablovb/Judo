package es.liceo.judo.championship

import es.liceo.judo.category.Category;

/**
 * Clase con informacion sobre un campeonato de judo.
 *
 * No se puede crear con fecha anterior a la actual, pero al modificarlo
 * si deberia poder hacerse en una fecha anterior a la actual (para cerrar
 * la inscripcion, por ejemplo).
 * 
 * @author Pablo
 *
 */
class Championship {
	
	String name
	Date date
	Boolean open
	static hasMany = [categories:Category]

    static constraints = {
		name(blank:false, nullable:false)
		date(blank:false, nullable:false, validator:{ val, obj ->
				(obj.id != null) || (new Date()?.before(obj.date))
			})
		open(blank:false, nullable:false, default:false, validator:{ val, obj ->
				(!val) || (new Date()?.before(obj.date))
			})
    }
	
	static mapping = {
		// parece que no se puede por varios campos
		sort date:"desc"
	}
	
	String toString() {
		return name + " - " + date.format("dd/MM/yyyy")
	}
	
}
