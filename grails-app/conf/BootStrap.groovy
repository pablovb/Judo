import es.liceo.judo.security.Role
import es.liceo.judo.security.User
import es.liceo.judo.security.UserRole

class BootStrap {

    def springSecurityService
    
    def init = { servletContext ->
    
        // roles
        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: 
            new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
        def staffRole = Role.findByAuthority('ROLE_STAFF') ?: 
            new Role(authority: 'ROLE_STAFF').save(failOnError: true)
        def liceoRole = Role.findByAuthority('ROLE_CLUB') ?: 
            new Role(authority: 'ROLE_CLUB').save(failOnError: true)
        
        // users
        def adminUser = User.findByUsername('admin') ?: new User(
            username: 'admin',
            password: springSecurityService.encodePassword('admin'),
            enabled: true).save(failOnError: true)
            
        if (!adminUser.authorities.contains(adminRole)) {
            UserRole.create adminUser, adminRole
        }
    }
    
    def destroy = {
    
    }
    
}
