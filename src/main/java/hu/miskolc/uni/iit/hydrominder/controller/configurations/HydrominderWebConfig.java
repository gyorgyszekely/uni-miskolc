package hu.miskolc.uni.iit.hydrominder.controller.configurations;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import hu.miskolc.uni.iit.hydrominder.controller.HydrominderWebController;
import hu.miskolc.uni.iit.hydrominder.services.CustomerAuthenticationService;
import hu.miskolc.uni.iit.hydrominder.services.CustomerDrinkProfileManagement;

/**
 * Configuration class for mobil endpoint. This class also define an HTTP message converter for JSON request unmarshaling.
 * 
 * @author gszekely
 *
 */
@EnableWebMvc
@Configuration
public class HydrominderWebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new GsonHttpMessageConverter());
	}
	
	@Bean
	public HydrominderWebController getHydrominderWebController(CustomerAuthenticationService customerAuthenticationProvider, CustomerDrinkProfileManagement customerDrinkProfileManagement) {
		return new HydrominderWebController(customerAuthenticationProvider, customerDrinkProfileManagement);
	}

	
}
