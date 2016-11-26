package hu.miskolc.uni.iit.hydrominder.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.miskolc.uni.iit.hydrominder.persistence.UserManagementDao;
import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerAuthenticationCredentials;

/**
 * For details please see {@link CustomerAuthenticationService}
 * 
 * @author gszekely
 *
 */
@Service
public class CustomerAuthenticationServiceImpl implements CustomerAuthenticationService{

	private static final Logger logger =  LoggerFactory.getLogger(CustomerAuthenticationServiceImpl.class);
	protected UserManagementDao userManagementDao;
	
	public CustomerAuthenticationServiceImpl(UserManagementDao userManagementDao) {
		this.userManagementDao = userManagementDao;
	}
	
	/* (non-Javadoc)
	 * @see hu.miskolc.uni.iit.hydrominder.services.CustomerAuthenticationService#showUsers()
	 */
	@Override
	public List<CustomerAuthenticationCredentials> showUsers() {
		return userManagementDao.getAllRegisteredUsers();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{

		CustomerAuthenticationCredentials registeredCustomer = userManagementDao.getRegisteredUserByName(username);
		if(registeredCustomer == null){
			throw new UsernameNotFoundException("Cannot find user: " + username + "in the DB.");
		}
		User hydrominderCustomer = null;
		try {
			final String principal = (registeredCustomer.getCustomerName() != null) ? registeredCustomer.getCustomerName() : registeredCustomer.getCustomerEmailaddress(); 
			hydrominderCustomer = new User(principal, registeredCustomer.getPassword(), processAuthorities(registeredCustomer.getRoles()));
		} catch (Exception ex) {
			throw new IllegalStateException("Could not create secured hydrominder customer.", ex);
		}
		return hydrominderCustomer;
	}
	
	/* (non-Javadoc)
	 * @see hu.miskolc.uni.iit.hydrominder.services.CustomerAuthenticationService#credentialMatcher(java.lang.String, java.lang.String)
	 */
	public boolean credentialMatcher(String credential, String hashedCredential)
	{
		return new BCryptPasswordEncoder().matches(credential, hashedCredential);
	}
	
	/**
	 * Create Authority object list.
	 * @param roles
	 * @return
	 */
	private Collection<SimpleGrantedAuthority> processAuthorities(final Set<String> roles)
	{
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for(String role : roles)
		{
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
	
	

}
