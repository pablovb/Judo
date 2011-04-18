import es.liceo.judo.category.Category
import es.liceo.judo.championship.Championship
import es.liceo.judo.club.Club
import es.liceo.judo.judoka.Judoka
import es.liceo.judo.judoka.JudokaHistoricalClub
import es.liceo.judo.security.Role
import es.liceo.judo.security.User
import es.liceo.judo.security.UserRole

class BootStrap {

	def springSecurityService

	def init = { servletContext ->

		// create roles and users
		createUsersAndRoles()

		// create championships and categories
		createChampionships()

		// create judokas and registrations for championships
		createRegistrations()
	}

	def destroy = {

	}

	def createUsersAndRoles = {

		if (log.isDebugEnabled()) {
			log.debug("Creando usuarios y roles...")
		}

		// roles
		def adminRole = Role.findByAuthority('ROLE_ADMIN') ?:
				new Role(authority: 'ROLE_ADMIN').save(failOnError: true, flush: true)
		def staffRole = Role.findByAuthority('ROLE_STAFF') ?:
				new Role(authority: 'ROLE_STAFF').save(failOnError: true, flush: true)
		def clubRole = Role.findByAuthority('ROLE_CLUB') ?:
				new Role(authority: 'ROLE_CLUB').save(failOnError: true, flush: true)

		// admin user
		def adminUser = User.findByUsername('admin') ?: new User(
				username: 'admin',
				password: springSecurityService.encodePassword('admin'),
				enabled: true).save(failOnError: true, flush: true)

		if (!adminUser.authorities.contains(adminRole)) {
			UserRole.create(adminUser, adminRole)
		}

		// staff user
		def staffUser = User.findByUsername('staff') ?: new User(
				username: 'staff',
				password: springSecurityService.encodePassword('staff'),
				enabled: true).save(failOnError: true, flush: true)

		if (!staffUser.authorities.contains(staffRole)) {
			UserRole.create(staffUser, staffRole)
		}

		// club user
		def clubUser = User.findByUsername('liceo') ?: new Club(
				username: 'liceo',
				password: springSecurityService.encodePassword('liceo'),
				enabled: true,
				name: 'Colegio Liceo',
				location: 'A Coruña').save(failOnError: true, flush: true)

		if (!clubUser.authorities.contains(clubRole)) {
			UserRole.create(clubUser, clubRole)
		}

	}

	def createChampionships = {

		if (log.isDebugEnabled()) {
			log.debug("Creando campeonatos y categorías...")
		}

		def trofeoLiceo = Championship.findByNameAndDate('Trofeo Liceo', new Date('2011/05/21')) ?:
				new Championship(
				name: 'Trofeo Liceo',
				date: new Date('2011/05/21'),
				open: true)

		def cadeteMasc = new Category(
				name: 'Cadete Sub-17',
				sex: 'Masculino',
				bornAgo: 10,
				minWeight: 78,
				maxWeight: 80.5)

		if (!(trofeoLiceo.category?.contains(cadeteMasc))) {
			trofeoLiceo.addToCategory(cadeteMasc)
		}
		println "Categorías de " + trofeoLiceo.name + ":"
		trofeoLiceo.category?.each {
			println "\t" + it.toString()
		}

		trofeoLiceo.save(failOnError: true, flush: true)

	}

	def createRegistrations = {

		if (log.isDebugEnabled()) {
			log.debug("Creando judokas e inscripciones...")
		}
		
		def judoka = Judoka.findByDni("32839629E") ?: new Judoka(
			dni: '32839629E',
			name: 'Pablo',
			surname: 'Vázquez Blázquez',
			sex: 'Hombre',
			bornDate: new Date('1983/09/01'))
		
		def judokaHist = new JudokaHistoricalClub(
			judoka: judoka,
			club: Club.findByUsername('liceo'),
			histVersion: 1)
		
		judoka.addToClub(judokaHist)
		
		judoka.save(failOnError: true, flush: true)
		
		Club actualClub = judoka.retrieveActualClub()
		
	}

}
