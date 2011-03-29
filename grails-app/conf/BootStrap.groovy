import es.liceo.judo.security.Role
import es.liceo.judo.security.User
import es.liceo.judo.security.UserRole

class BootStrap {

    def springSecurityService
    
    def init = { servletContext ->
		
		// create roles and users
		createUsersAndRoles()
		
		//TODO
		// create championship
		
		// create categories
    }
    
    def destroy = {
    
    }
	
	def createUsersAndRoles = {
		
		log.debug("Creando usuarios y roles");
		
		// roles
		def adminRole = Role.findByAuthority('ROLE_ADMIN') ?:
			new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
		def staffRole = Role.findByAuthority('ROLE_STAFF') ?:
			new Role(authority: 'ROLE_STAFF').save(failOnError: true)
		def clubRole = Role.findByAuthority('ROLE_CLUB') ?:
			new Role(authority: 'ROLE_CLUB').save(failOnError: true)
		
		// admin user
		def adminUser = User.findByUsername('admin') ?: new User(
			username: 'admin',
			password: springSecurityService.encodePassword('admin'),
			enabled: true).save(failOnError: true)
			
		if (!adminUser.authorities.contains(adminRole)) {
			UserRole.create adminUser, adminRole
		}
		
		// staff user
		def staffUser = User.findByUsername('staff') ?: new User(
			username: 'staff',
			password: springSecurityService.encodePassword('staff'),
			enabled: true).save(failOnError: true)
			
		if (!staffUser.authorities.contains(staffRole)) {
			UserRole.create staffUser, staffRole
		}
		
		// club user
		def clubUser = User.findByUsername('club') ?: new User(
			username: 'club',
			password: springSecurityService.encodePassword('club'),
			enabled: true).save(failOnError: true)
			
		if (!clubUser.authorities.contains(clubRole)) {
			UserRole.create clubUser, clubRole
		}
		
	}
    
}
