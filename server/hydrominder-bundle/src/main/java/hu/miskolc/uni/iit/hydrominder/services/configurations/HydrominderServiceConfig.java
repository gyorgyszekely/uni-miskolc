package hu.miskolc.uni.iit.hydrominder.services.configurations;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hu.miskolc.uni.iit.hydrominder.persistence.UserManagementDao;
import hu.miskolc.uni.iit.hydrominder.persistence.UserManagementDaoImpl;
import hu.miskolc.uni.iit.hydrominder.services.CustomerAuthenticationProvider;
import hu.miskolc.uni.iit.hydrominder.services.CustomerAuthenticationProviderImpl;

@Configuration
public class HydrominderServiceConfig implements ApplicationContextAware{

	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		
	}
	
	@Bean
	public CustomerAuthenticationProvider getCustomerAuthenticationProvider() {
		return new CustomerAuthenticationProviderImpl(getUserManagementDao());
	}
	
	@Bean
	public UserManagementDao getUserManagementDao() {
		return new UserManagementDaoImpl();
	}

}
