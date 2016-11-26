package hu.miskolc.uni.iit.hydrominder.types.bean;

import java.util.List;

/**
 * Customer defined profiles holder class. 
 * @author gszekely
 *
 */
public class CustomerDefinedProfile {
	
	protected String customerId;
	protected List<Profile> customerProfiles;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public List<Profile> getCustomerProfiles() {
		return customerProfiles;
	}
	public void setCustomerProfiles(List<Profile> customerProfiles) {
		this.customerProfiles = customerProfiles;
	}
	

}
