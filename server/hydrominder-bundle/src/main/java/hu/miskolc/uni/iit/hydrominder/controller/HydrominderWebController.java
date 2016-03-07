package hu.miskolc.uni.iit.hydrominder.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hu.miskolc.uni.iit.hydrominder.services.CustomerAuthenticationProvider;
import hu.miskolc.uni.iit.hydrominder.types.bean.CustomerAuthenticationCredentials;

@Controller
public class HydrominderWebController {
	
	private CustomerAuthenticationProvider customerAuthenticationProvider;
	
	public HydrominderWebController(CustomerAuthenticationProvider customerAuthenticationProvider) {
		this.customerAuthenticationProvider = customerAuthenticationProvider;
	}

	@RequestMapping(value = "/preload", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomerAuthenticationCredentials preload() {
		return customerAuthenticationProvider.showUsers();

	}
	
	
}
