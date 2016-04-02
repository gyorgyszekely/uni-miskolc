package hu.miskolc.uni.iit.hydrominder.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerAuthenticationCredentials;

public interface CustomerAuthenticationService extends UserDetailsService {

	List<CustomerAuthenticationCredentials> showUsers();
	
	boolean credentialMatcher(String credential, String hashedCredential);
}
