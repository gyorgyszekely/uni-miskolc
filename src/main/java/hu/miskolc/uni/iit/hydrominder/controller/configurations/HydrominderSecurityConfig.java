package hu.miskolc.uni.iit.hydrominder.controller.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

import hu.miskolc.uni.iit.hydrominder.filter.HydrominderAuthenticationFilter;
import hu.miskolc.uni.iit.hydrominder.services.CustomerAuthenticationProvider;
import hu.miskolc.uni.iit.hydrominder.services.CustomerAuthenticationService;


/**
 * Configuration class for authorization.
 * 
 * @author gszekely
 *
 */
@EnableWebSecurity
@Configuration
public class HydrominderSecurityConfig extends WebSecurityConfigurerAdapter implements ApplicationContextAware{
	
	private static final Logger logger =  LoggerFactory.getLogger(HydrominderSecurityConfig.class);

	
	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.antMatcher("/mobilegateway/**")
		.csrf().disable()
		.authorizeRequests().anyRequest().authenticated().and()
		.addFilterBefore(getHydrominderAuthenticationFilter(), SecurityContextPersistenceFilter.class)
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint())
		;
		//TODO need PersistentTokenFilter
    }

	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getCustomerAuthenticationProvider(null));

	}

	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#authenticationManagerBean()
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * Customized authentication provider against match registered users.  
	 * 
	 * @param customerAuthenticationService
	 * @return
	 */
	@Bean
	public AuthenticationProvider getCustomerAuthenticationProvider(CustomerAuthenticationService customerAuthenticationService) {
		return new CustomerAuthenticationProvider(customerAuthenticationService);
	}

	/**
	 * Filter bean, catch all incoming request.
	 * @return
	 * @throws Exception
	 */
	@Bean
	public AbstractAuthenticationProcessingFilter getHydrominderAuthenticationFilter() throws Exception
	{ 
		AbstractAuthenticationProcessingFilter hydrominderAuthenticationFilter = new HydrominderAuthenticationFilter();
		hydrominderAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
		return hydrominderAuthenticationFilter;
	}

	
	
	
}
