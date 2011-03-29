package es.liceo.judo.judoka

/**
* Clase con la información sobre un judoka que 
* varía con el tiempo.
*
* @author Pablo
*
*/
class JudokaHistorical {
	
	Date startDate
	Date endDate
	//TODO club (hasOne?)
	static belongsTo = [judoka:Judoka]

    static constraints = {
    }
}
