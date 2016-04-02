package hu.miskolc.uni.iit.hydrominder.persistence;

import java.util.List;

import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerAuthenticationCredentials;

public interface UserManagementDao {

	List<CustomerAuthenticationCredentials> getAllRegisteredUsers();
	
	CustomerAuthenticationCredentials getRegisteredUserByName(String userName);
}
