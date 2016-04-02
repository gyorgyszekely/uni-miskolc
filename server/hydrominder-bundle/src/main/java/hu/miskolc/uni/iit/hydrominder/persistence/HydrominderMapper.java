package hu.miskolc.uni.iit.hydrominder.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerAuthenticationCredentials;

public interface HydrominderMapper {
	
	List<CustomerAuthenticationCredentials> getAllUsers();
	
	CustomerAuthenticationCredentials getCustomerByName(@Param("userName")String userName, @Param("authByEmail")boolean authByEmail);

}
