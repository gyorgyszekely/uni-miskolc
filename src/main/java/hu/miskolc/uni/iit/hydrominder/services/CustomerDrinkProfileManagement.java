package hu.miskolc.uni.iit.hydrominder.services;

import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerDefinedProfile;
import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerProfileRequest;

/**
 * API for drink time management.
 * 
 * @author gszekely
 *
 */
public interface CustomerDrinkProfileManagement {

	/**
	 * Preload proper profile list.
	 * 
	 * @param customerProfileRequest
	 * @return
	 */
	CustomerDefinedProfile preloadProfiles(CustomerProfileRequest customerProfileRequest);
	
	/**
	 * Create new profile with custom reminders.
	 * 
	 * @param customerProfileRequest
	 */
	void createProfile(CustomerProfileRequest customerProfileRequest);
}
