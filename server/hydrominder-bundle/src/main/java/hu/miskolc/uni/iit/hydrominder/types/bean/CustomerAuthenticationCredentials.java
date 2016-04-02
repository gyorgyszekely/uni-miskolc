package hu.miskolc.uni.iit.hydrominder.types.bean;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomerAuthenticationCredentials {

	private String customerName;
	private String customerEmailaddress;
	private String password;
	private String token;
	private Timestamp expiryts;
	private Set<String> roles = new HashSet<>();
	
	public CustomerAuthenticationCredentials() {
	}
	
	public String getToken() {
		return token;
	}

	public Timestamp getExpiryts() {
		return expiryts;
	}

	public String getCustomerName() {
		return customerName;
	}
	
	public CustomerAuthenticationCredentials setToken(String token) {
		this.token = token;
		return this;
	}
	
	public CustomerAuthenticationCredentials setExpiryts(Timestamp expiryts) {
		this.expiryts = expiryts;
		return this;
	}
	
	public CustomerAuthenticationCredentials setCustomerName(String customerName) {
		this.customerName = customerName;
		return this;
	}
	public String getCustomerEmailaddress() {
		return customerEmailaddress;
	}
	public CustomerAuthenticationCredentials setCustomerEmailaddress(String customerEmailadress) {
		if(customerEmailadress.matches("[\\w._%+-]+@[\\w.-]+.[A-Za-z]{2,}")){
			this.customerEmailaddress = customerEmailadress;
			return this;
		}
		throw new IllegalArgumentException("Has been entered invalid email adress.");
	}
	public String getPassword() {
		return password;
	}
	public CustomerAuthenticationCredentials setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public void setAndEncodePassword(String rawPassword){
		final String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
		setPassword(encodedPassword);
	}

	public Set<String> getRoles() {
		return roles;
	}

	public CustomerAuthenticationCredentials setRoles(String role) {
		this.roles.add(role);
		return this;
	}
	
	

}
