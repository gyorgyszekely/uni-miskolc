package hu.miskolc.uni.iit.hydrominder.types.bean;

public class CustomerAuthenticationCredentials {
	
	private String customerName;
	private String customerEmailaddress;
	private String password;
	
	public CustomerAuthenticationCredentials() {
	}
	
	public String getCustomerName() {
		return customerName;
	}
	public CustomerAuthenticationCredentials setCustomerName(String customerName) {
		this.customerName = customerName;
		return this;
	}
	public String getCustomerEmailaddress() {
		return customerEmailaddress;
	}
	public CustomerAuthenticationCredentials setCustomerEmailaddress(String customerEmailadress) {
		this.customerEmailaddress = customerEmailadress;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public CustomerAuthenticationCredentials setPassword(String password) {
		this.password = password;
		return this;
	}
	
	

}
