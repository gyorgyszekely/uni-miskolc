package hu.miskolc.uni.iit.hydrominder.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerAuthenticationCredentials;

@Repository
public class UserManagementDaoImpl implements UserManagementDao{
	
	@Autowired
	private HydrominderMapper persistence;
	

	@Override
	public CustomerAuthenticationCredentials getAllRegisteredUsers() {
		return persistence.getAllUsers();
	}

}
