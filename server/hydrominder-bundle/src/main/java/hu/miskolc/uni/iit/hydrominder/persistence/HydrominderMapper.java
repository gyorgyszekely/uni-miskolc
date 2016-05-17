package hu.miskolc.uni.iit.hydrominder.persistence;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerAuthenticationCredentials;
import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerDefinedProfile;
import hu.miskolc.uni.iit.hydrominder.types.bean.Reminder;

/**
 *  For details please see {@link UserManagementDao}
 * 
 * @author gszekely
 *
 */
public interface HydrominderMapper {
	
	List<CustomerAuthenticationCredentials> getAllUsers();
	
	CustomerAuthenticationCredentials getCustomerByName(@Param("userName")String userName, @Param("authByEmail")boolean authByEmail);
	
	CustomerDefinedProfile getCustomerProfile(@Param("customerId")String customerId);
	
	int insertDrinkProfile(@Param("name")String name, @Param("description")String description,@Param("friendlyName")String friendlyName,@Param("drinkFrequency")Float drinkFrequency);

	int insertReminder(@Param("profileId")int profileId,@Param("title")String title,@Param("timets")Timestamp timets);

	void batchInsertReminder(@Param("profileId")int profileId, @Param("reminders")List<Reminder> reminders);
}
