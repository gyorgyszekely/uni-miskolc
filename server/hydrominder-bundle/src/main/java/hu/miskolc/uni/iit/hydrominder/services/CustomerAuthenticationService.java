package hu.miskolc.uni.iit.hydrominder.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerAuthenticationCredentials;

/**
 * 
 * Authentication service API.
 * @author gszekely
 *
 */
public interface CustomerAuthenticationService extends UserDetailsService {

	/**
	 * List all registered users.
	 * @return
	 */
	List<CustomerAuthenticationCredentials> showUsers();
	
	/**
	 * Check user credential.
	 * 
	 * @param credential
	 * @param hashedCredential
	 * @return
	 */
	boolean credentialMatcher(String credential, String hashedCredential);
}
