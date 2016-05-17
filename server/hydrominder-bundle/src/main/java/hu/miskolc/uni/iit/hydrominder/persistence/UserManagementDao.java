package hu.miskolc.uni.iit.hydrominder.persistence;

import java.util.List;

import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerAuthenticationCredentials;
import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerDefinedProfile;
import hu.miskolc.uni.iit.hydrominder.types.bean.Profile;
import hu.miskolc.uni.iit.hydrominder.types.bean.Reminder;

/**
 * Persistent layer.
 * 
 * @author gszekely
 *
 */
public interface UserManagementDao {

	/**
	 * Retrieve all registered customer.
	 * @return
	 */
	List<CustomerAuthenticationCredentials> getAllRegisteredUsers();
	
	/**
	 * Retrieve current principal for authentication.
	 * 
	 * @param userName
	 * @param authByEmail
	 * @return
	 */
	CustomerAuthenticationCredentials getRegisteredUserByName(String userName);
	
	/**
	 * Retrieve all defined customer drink profile for specified customerid.
	 * 
	 * @param customerId
	 * @return
	 */
	CustomerDefinedProfile getCustomerProfile(String customerId);
	
	/**
	 *  Persist user defined drink profile.
	 *  
	 * @param profileItem
	 * @return
	 */
	int insertDrinkProfile(Profile profileItem);

	/**
	 * Persist user defined drink time reminders.
	 * 
	 * @param profileId
	 * @param reminder
	 * @return
	 */
	int insertReminder(int profileId, Reminder reminder);
	
	/**
	 * Batch reminder creation operation.
	 * 
	 * @param profileItem
	 */
	void createProfileWithReminders(Profile profileItem);

}
