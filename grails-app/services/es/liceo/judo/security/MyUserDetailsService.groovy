package es.liceo.judo.security

import java.util.Collection;

import es.liceo.judo.club.Club
import org.codehaus.groovy.grails.plugins.springsecurity.GormUserDetailsService
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

class MyUserDetailsService extends GormUserDetailsService {

	protected UserDetails createUserDetails(user, Collection<GrantedAuthority> authorities) {

		def conf = SpringSecurityUtils.securityConfig

		String usernamePropertyName = conf.userLookup.usernamePropertyName
		String passwordPropertyName = conf.userLookup.passwordPropertyName
		String enabledPropertyName = conf.userLookup.enabledPropertyName
		String accountExpiredPropertyName = conf.userLookup.accountExpiredPropertyName
		String accountLockedPropertyName = conf.userLookup.accountLockedPropertyName
		String passwordExpiredPropertyName = conf.userLookup.passwordExpiredPropertyName

		String username = user."$usernamePropertyName"
		String password = user."$passwordPropertyName"
		boolean enabled = enabledPropertyName ? user."$enabledPropertyName" : true
		boolean accountExpired = accountExpiredPropertyName ? user."$accountExpiredPropertyName" : false
		boolean accountLocked = accountLockedPropertyName ? user."$accountLockedPropertyName" : false
		boolean passwordExpired = passwordExpiredPropertyName ? user."$passwordExpiredPropertyName" : false

		UserDetails userDetails = null
		if (Role.findByAuthority('ROLE_CLUB') in authorities) {
			userDetails = new Club(username, password, enabled, !accountExpired, !passwordExpired,
				!accountLocked, authorities, user.id, user.name, user.location)
		} else {
		 	userDetails = new GrailsUser(username, password, enabled, !accountExpired, !passwordExpired,
				!accountLocked, authorities, user.id)
		}
		
		return userDetails
	}
}
