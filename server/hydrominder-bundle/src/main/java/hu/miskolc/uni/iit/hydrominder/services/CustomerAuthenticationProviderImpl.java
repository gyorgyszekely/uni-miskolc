package hu.miskolc.uni.iit.hydrominder.services;

import org.springframework.stereotype.Service;

import hu.miskolc.uni.iit.hydrominder.persistence.UserManagementDao;
import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerAuthenticationCredentials;

@Service
public class CustomerAuthenticationProviderImpl implements CustomerAuthenticationProvider{

	protected UserManagementDao userManagementDao; 
	
	public CustomerAuthenticationProviderImpl(UserManagementDao userManagementDao) {
		this.userManagementDao = userManagementDao;
	}
	
	@Override
	public CustomerAuthenticationCredentials showUsers() {
		return userManagementDao.getAllRegisteredUsers();
	}
	
	

}
