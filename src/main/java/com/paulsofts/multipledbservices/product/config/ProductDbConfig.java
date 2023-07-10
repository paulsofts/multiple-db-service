package com.paulsofts.multipledbservices.product.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		
		entityManagerFactoryRef = "secondDbEntityManagerFactoryBean",
		basePackages = {"com.paulsofts.multipledbservices.product.repository"},
		transactionManagerRef = "secondDbPlatformTransactionManager"
		
		)
public class ProductDbConfig {
	
	//this is used for reading configuration from properties file
		@Autowired
		private Environment environment;
		
		//DataSource bean
		@Bean(name = "secondDataSource")
		@Primary
		public DataSource dataSource() {
			//we will fetch and set database configurations from application.properties file
			DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
			driverManagerDataSource.setUrl(environment.getProperty("second.datasource.url"));
			driverManagerDataSource.setUsername(environment.getProperty("second.datasource.username"));
			driverManagerDataSource.setPassword(environment.getProperty("second.datasource.password"));
			driverManagerDataSource.setDriverClassName(environment.getProperty("second.datasource.driver-class-name"));
			
			return driverManagerDataSource;
		}
		
		//EntityManager bean
		@Bean(name = "secondDbEntityManagerFactoryBean")
		public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
			LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
			
			//setting data source
			bean.setDataSource(dataSource());
			
			//setting JPA adapter
			JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
			bean.setJpaVendorAdapter(jpaVendorAdapter);
			
			//setting hibernate properties
			Map<String, String> hibernate_properties = new HashMap<String, String>();
			hibernate_properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			hibernate_properties.put("hibernate.show_sql", "true");
			hibernate_properties.put("hibernate.hbm2ddl.auto", "create");
			
			bean.setJpaPropertyMap(hibernate_properties);
			
			//setting entities(model class) package path
			bean.setPackagesToScan("com.paulsofts.multipledbservices.product.data");
			return bean;
		}
		
		//TransactionManager bean
		@Bean(name = "secondDbPlatformTransactionManager")
		@Primary
		public PlatformTransactionManager platformTransactionManagerBean() {
			JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
			jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
			return jpaTransactionManager;
		}

}
