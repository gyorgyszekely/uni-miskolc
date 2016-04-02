package hu.miskolc.uni.iit.hydrominder.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerAuthenticationCredentials;

@Repository
public class UserManagementDaoImpl implements UserManagementDao{
	
	@Autowired
	private HydrominderMapper persistence;
	

	@Override
	public List<CustomerAuthenticationCredentials> getAllRegisteredUsers() {
		return persistence.getAllUsers();
	}


	@Override
	public CustomerAuthenticationCredentials getRegisteredUserByName(String userName) {
		if(userName.matches("[\\w._%+-]+@[\\w.-]+.[A-Za-z]{2,}")){
			return persistence.getCustomerByName(userName, true);
		}
		return persistence.getCustomerByName(userName, false);
	}

}
