package hu.miskolc.uni.iit.hydrominder.persistence;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import oracle.jdbc.driver.OracleDriver;

/**
 * Configuration class for persistence layer.
 * 
 * @author gszekely
 *
 */
@Configuration
@MapperScan("hu.miskolc.uni.iit.hydrominder.persistence")
public class HydrominderPersistenceConfig implements ApplicationContextAware {
	
	private final static String DB_URI = "jdbc:oracle:thin:@localhost:1521/xe";
	private final static String DB_USER = "hydrouser";
	private final static String DB_PWD = "password";

	protected ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		
	}
	
	@Bean
	public DataSource getDataSource() {
		SimpleDriverDataSource oradata = null;
		try {
			oradata = new SimpleDriverDataSource();
			oradata.setDriverClass(OracleDriver.class);
			oradata.setUrl(HydrominderPersistenceConfig.DB_URI);
			oradata.setUsername(HydrominderPersistenceConfig.DB_USER);
			oradata.setPassword(HydrominderPersistenceConfig.DB_PWD);
		} catch (Exception ex) {
			throw new RuntimeException("Fatal error, unable to create datasource.", ex);
		}
		return oradata;
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setTypeAliasesPackage("hu.miskolc.uni.iit.hydrominder.types.bean");
		return sessionFactory;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(getDataSource());
	}

}
