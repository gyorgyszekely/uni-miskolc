package hu.miskolc.uni.iit.hydrominder.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Authentication engine, user can authenticate his nick or email and password complex principal triad.
 * 
 * @author gszekely
 *
 */
@Service
public class CustomerAuthenticationProvider implements AuthenticationProvider{
	
	private static final Logger logger =  LoggerFactory.getLogger(CustomerAuthenticationProvider.class);
	
	private CustomerAuthenticationService customerAuthenticationService;
	
	public CustomerAuthenticationProvider(CustomerAuthenticationService customerAuthenticationService) {
		this.customerAuthenticationService = customerAuthenticationService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if(!supports(authentication.getClass()))
		{
			return null;
		}
		final String principal = (String)authentication.getPrincipal();
		Assert.notNull(principal, "Principal cannot be null.");
		UserDetails currentUser =  customerAuthenticationService.loadUserByUsername(principal);
		if(customerAuthenticationService.credentialMatcher(authentication.getCredentials().toString(), currentUser.getPassword()))
		{
			logger.debug("Sucessfully authenticated {}." , principal);
			return new UsernamePasswordAuthenticationToken(principal, currentUser.getPassword(), currentUser.getAuthorities());
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
