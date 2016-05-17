package hu.miskolc.uni.iit.hydrominder.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hu.miskolc.uni.iit.hydrominder.services.CustomerAuthenticationService;
import hu.miskolc.uni.iit.hydrominder.services.CustomerDrinkProfileManagement;
import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerDefinedProfile;
import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerProfileRequest;

/**
 * Mobil client endpoint class.
 * 
 * @author gszekely
 *
 */
@Controller
public class HydrominderWebController {
	
	private CustomerAuthenticationService customerAuthenticationProvider;
	private CustomerDrinkProfileManagement customerDrinkProfileManagement;
	
	public HydrominderWebController(CustomerAuthenticationService customerAuthenticationProvider, CustomerDrinkProfileManagement customerDrinkProfileManagement) {
		this.customerAuthenticationProvider = customerAuthenticationProvider;
		this.customerDrinkProfileManagement = customerDrinkProfileManagement;
	}

	/**
	 * API endpoint, mobile client can call to retrieve each profile by customerid.
	 * 
	 * @param customerProfileRequest
	 * @return
	 */
	@RequestMapping(value = "/mobilegateway/preload", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomerDefinedProfile preload(CustomerProfileRequest customerProfileRequest) {
		return customerDrinkProfileManagement.preloadProfiles(customerProfileRequest);

	}
	
	/**
	 * API endpoint, mobile client can call to persist new recorded reminders per profile.
	 * 
	 * @param customerProfileRequest
	 * @return
	 */
	@RequestMapping(value = "/mobilegateway/create", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String create(CustomerProfileRequest customerProfileCreationRequest) {
		customerDrinkProfileManagement.createProfile(customerProfileCreationRequest);
		return "Success";

	}
	
	
}
