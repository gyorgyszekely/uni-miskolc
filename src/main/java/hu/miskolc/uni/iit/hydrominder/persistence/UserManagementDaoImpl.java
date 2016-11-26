package hu.miskolc.uni.iit.hydrominder.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerAuthenticationCredentials;
import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerDefinedProfile;
import hu.miskolc.uni.iit.hydrominder.types.bean.Profile;
import hu.miskolc.uni.iit.hydrominder.types.bean.Reminder;

/**
 * For API doc please see {@link UserManagementDao}
 * 
 * @author gszekely
 *
 */
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


	@Override
	public CustomerDefinedProfile getCustomerProfile(String customerId) {
		return persistence.getCustomerProfile(customerId);
	}


	@Override
	public int insertDrinkProfile(Profile profileItem) {
		return persistence.insertDrinkProfile(profileItem.getName(), profileItem.getDescription(), profileItem.getFriendlyName(), (float) profileItem.getDrinkFrequency());
	}


	@Override
	public int insertReminder(int profileId, Reminder reminder) {
		return persistence.insertReminder(profileId, reminder.getTitle(), reminder.getTimets());
	}


	@Override
	public void createProfileWithReminders(Profile profileItem) {
		final Integer profileId = persistence.insertDrinkProfile(profileItem.getName(), profileItem.getDescription(), profileItem.getFriendlyName(), (float) profileItem.getDrinkFrequency());
		persistence.batchInsertReminder(profileId, profileItem.getReminders());
	}

}
