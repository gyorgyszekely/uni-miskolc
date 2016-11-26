package hu.miskolc.uni.iit.hydrominder.services;

import org.springframework.stereotype.Service;

import hu.miskolc.uni.iit.hydrominder.persistence.UserManagementDao;
import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerDefinedProfile;
import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerProfileRequest;
import hu.miskolc.uni.iit.hydrominder.types.bean.Profile;

/**
 * For API doc please see {@link CustomerDrinkProfileManagement}
 * 
 * @author gszekely
 *
 */
@Service
public class CustomerDrinkProfileManagementImpl implements CustomerDrinkProfileManagement{
	
	protected UserManagementDao userManagementDao;

	public CustomerDrinkProfileManagementImpl(UserManagementDao userManagementDao) {
		this.userManagementDao = userManagementDao;
	}
	
	/* (non-Javadoc)
	 * @see hu.miskolc.uni.iit.hydrominder.services.CustomerDrinkProfileManagement#preloadProfiles(hu.miskolc.uni.iit.hydrominder.types.bean.CustomerProfileRequest)
	 */
	@Override
	public CustomerDefinedProfile preloadProfiles(CustomerProfileRequest customerProfileRequest) {
		final String customerId = customerProfileRequest.getCustomerId();
		if(customerId == null || "".equals(customerId.trim()))
		{
			throw new IllegalArgumentException("No valid customerid given.");
		}
		return userManagementDao.getCustomerProfile(customerId);
	}

	/* (non-Javadoc)
	 * @see hu.miskolc.uni.iit.hydrominder.services.CustomerDrinkProfileManagement#createProfile(hu.miskolc.uni.iit.hydrominder.types.bean.CustomerProfileRequest)
	 */
	@Override
	public void createProfile(CustomerProfileRequest customerProfileRequest) {
		final Profile profile = customerProfileRequest.getCustomerProfiles().get(0);
		if(customerProfileRequest.getCustomerId() == null || "".equals(customerProfileRequest.getCustomerId().trim()))
		{
			throw new IllegalArgumentException("No valid customerid given.");
		}
		if(profile == null || profile.getReminders().isEmpty())
		{
			throw new IllegalArgumentException("No reminder given.");
		}
		if(profile.getDrinkFrequency() < 1.0 || (profile.getFriendlyName() != null && "".equals(profile.getFriendlyName().trim())) || (profile.getName() != null &&  "".equals(profile.getName().trim())))
		{
			throw new IllegalArgumentException("No reminder given.");
		}
		userManagementDao.createProfileWithReminders(profile);
	}

}
