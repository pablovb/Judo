import grails.plugins.springsecurity.SecurityConfigType

// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// The default codec used to encode data with ${}
grails.views.default.codec = "html" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable for AppEngine!
grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// set per-environment serverURL stem for creating absolute links
environments {
    production {
        grails.serverURL = "http://www.changeme.com"
    }
    development {
        grails.serverURL = "http://localhost:8080/${appName}"
    }
    test {
        grails.serverURL = "http://localhost:8080/${appName}"
    }

}

// TODO log4j configuration
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    appenders {
		rollingFile name: "fileout", maxFileSize: "10MB", maxBackupIndex: 10, file: "C:\\grails-1.3.7\\workspace\\judo\\logs\\judo.log"
		console name:"stdout"
    }

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'
    
    debug  'grails.app'
    
    root = {
		error 'stdout', 'fileout'
    }
}

// **********************************************************************************************

// SECURITY-PLUGIN
// Modified default properties from C:\Users\Pablo\.grails\1.3.7\projects\judo\plugins\spring-security-core-1.1.2\grails-app\conf

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'es.liceo.judo.security.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'es.liceo.judo.security.UserRole'
grails.plugins.springsecurity.authority.className = 'es.liceo.judo.security.Role'

grails.plugins.springsecurity.registerLoggerListener=true

// password algorithm
grails.plugins.springsecurity.password.algorithm='SHA-512'

// salt in passwords
// grails.plugins.springsecurity.dao.reflectionSaltSourceProperty = 'username'

grails.plugins.springsecurity.rememberMe.cookieName="judo_remember_me_cookie"
grails.plugins.springsecurity.rememberMe.tokenValiditySeconds=1209600

// ip restrictions
// grails.plugins.springsecurity.ipRestrictions = [:]

// roles hierarchies
grails.plugins.springsecurity.roleHierarchy = '''
   ROLE_ADMIN > ROLE_STAFF
   ROLE_STAFF > ROLE_CLUB
'''

// TODO Security rules
grails.plugins.springsecurity.securityConfigType = SecurityConfigType.InterceptUrlMap
grails.plugins.springsecurity.interceptUrlMap = [
   '/security/**':  ['ROLE_ADMIN'],
   '/user/**':      ['ROLE_ADMIN'],
   '/role/**':      ['ROLE_ADMIN'],
   '/js/**':        ['IS_AUTHENTICATED_ANONYMOUSLY'],
   '/css/**':       ['IS_AUTHENTICATED_ANONYMOUSLY'],
   '/images/**':    ['IS_AUTHENTICATED_ANONYMOUSLY'],
   '/login/**':     ['IS_AUTHENTICATED_ANONYMOUSLY'],
   '/logout/**':    ['IS_AUTHENTICATED_ANONYMOUSLY'],
   '/*':            ['ROLE_CLUB']
]

grails.plugins.springsecurity.successHandler.useReferer=true

// Messages
grails.plugins.springsecurity.errors.login.disabled="Sorry, your account is disabled."
grails.plugins.springsecurity.errors.login.expired="Sorry, your account has expired."
grails.plugins.springsecurity.errors.login.passwordExpired="Sorry, your password has expired."
grails.plugins.springsecurity.errors.login.locked="Sorry, your account is locked."
grails.plugins.springsecurity.errors.login.fail="Sorry, we were not able to find a user with that username and password."