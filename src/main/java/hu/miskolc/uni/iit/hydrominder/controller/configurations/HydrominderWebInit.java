package hu.miskolc.uni.iit.hydrominder.controller.configurations;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import hu.miskolc.uni.iit.hydrominder.persistence.HydrominderPersistenceConfig;
import hu.miskolc.uni.iit.hydrominder.services.configurations.HydrominderServiceConfig;

/**
 * Servelt configuration class, aggregates all defined lowest level layer configs.
 * 
 * @author gszekely
 *
 */
public class HydrominderWebInit extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { HydrominderServiceConfig.class, HydrominderPersistenceConfig.class, HydrominderSecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { HydrominderWebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
