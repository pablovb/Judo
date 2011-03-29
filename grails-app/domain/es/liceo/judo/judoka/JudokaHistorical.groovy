package es.liceo.judo.judoka

import java.io.Serializable;

/**
* Clase con la información sobre un judoka que 
* varía con el tiempo.
*
* @author Pablo
*
*/
class JudokaHistorical implements Serializable {
	
	Date startDate
	Date endDate
	//TODO club (hasOne?)
	static belongsTo = [judoka:Judoka]

    static constraints = {
    }
}
