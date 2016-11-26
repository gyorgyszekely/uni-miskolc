package hu.miskolc.uni.iit.hydrominder.services.configurations;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hu.miskolc.uni.iit.hydrominder.persistence.UserManagementDao;
import hu.miskolc.uni.iit.hydrominder.persistence.UserManagementDaoImpl;
import hu.miskolc.uni.iit.hydrominder.services.CustomerAuthenticationService;
import hu.miskolc.uni.iit.hydrominder.services.CustomerAuthenticationServiceImpl;
import hu.miskolc.uni.iit.hydrominder.services.CustomerDrinkProfileManagement;
import hu.miskolc.uni.iit.hydrominder.services.CustomerDrinkProfileManagementImpl;

/**
 * Configuration class for Service layer.
 * @author gszekely
 *
 */
@Configuration
public class HydrominderServiceConfig implements ApplicationContextAware{

	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		
	}
	
	@Bean
	public CustomerAuthenticationService getCustomerAuthenticationServiceImpl() {
		return new CustomerAuthenticationServiceImpl(getUserManagementDao());
	}
	
	@Bean
	public CustomerDrinkProfileManagement getCustomerDrinkProfileManagementImpl() {
		return new CustomerDrinkProfileManagementImpl(getUserManagementDao());
	}
	
	@Bean
	public UserManagementDao getUserManagementDao() {
		return new UserManagementDaoImpl();
	}
	
}
