package hu.miskolc.uni.iit.hydrominder.persistence;

import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerAuthenticationCredentials;

public interface UserManagementDao {

	CustomerAuthenticationCredentials getAllRegisteredUsers();
}
