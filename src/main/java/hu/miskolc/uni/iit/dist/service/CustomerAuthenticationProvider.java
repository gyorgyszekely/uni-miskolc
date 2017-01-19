package hu.miskolc.uni.iit.dist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CustomerAuthenticationProvider implements AuthenticationProvider
{
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication)
	{
		if (!supports(authentication.getClass()))
		{
			return null;
		}
		final String principal = (String) authentication.getPrincipal();
		Assert.notNull(principal, "Principal cannot be null.");
		UserDetails currentUser = userDetailsService.loadUserByUsername(principal);
		if (authentication.getCredentials().equals(currentUser.getPassword()))
		{
			return new UsernamePasswordAuthenticationToken(principal, currentUser.getPassword(), currentUser.getAuthorities());
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication)
	{
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
