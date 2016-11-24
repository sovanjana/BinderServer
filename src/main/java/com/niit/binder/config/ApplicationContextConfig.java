package com.niit.binder.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.binder.dao.UserDAO;
import com.niit.binder.dao.impl.UserDAOImpl;
import com.niit.binder.model.Blog;
import com.niit.binder.model.Event;
import com.niit.binder.model.Forum;
import com.niit.binder.model.ForumComment;
import com.niit.binder.model.Friend;
import com.niit.binder.model.Job;
import com.niit.binder.model.JobApplication;
import com.niit.binder.model.Users;

@Configuration						//@Configuration indicates that the class can be used by the Spring IoC container as a source of bean definitions.
@ComponentScan("com.niit.binder")		//@ComponentScan annotation is used to specify the base packages to scan.
//@ComponentScan(basePackages = "com.niit.binder", excludeFilters = @Filter(type = FilterType.ANNOTATION, value = AppConfig.class))
@EnableTransactionManagement		/**when we are using @Configuration i.e XML free configuration and need to 
									 * connect to database with hibernate. We need to use @EnableTransactionManagement.
									 */
public class ApplicationContextConfig {
	@Bean(name="dataSource")			//setup dataSource to desired database
	public DataSource getDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		/**
		 * Simple implementation of the standard JDBC DataSource interface, configuring the plain old JDBC DriverManager via 
		 * bean properties, and returning a new Connection from every getConnection call.
		 */
				/*--- Database connection settings ---*/
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");		//specify the driver...
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");		//specify the db_url...
		dataSource.setUsername("COLL");		//specify the db_username...
		dataSource.setPassword("COLL");		//specify the db_password...
		

		Properties connectionProperties = new Properties();
		connectionProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		connectionProperties.setProperty("hibernate.show_sql", "true");
		connectionProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		connectionProperties.setProperty("hibernate.format_sql", "true");
		connectionProperties.setProperty("hibernate.jdbc.use_get_generated_keys", "true");
		dataSource.setConnectionProperties(connectionProperties);		
		return dataSource;                                    // we are using oracle db for our project...
	}
	
	@Autowired		//@Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished..
	@Bean(name = "sessionFactory")			//sessionfactory creates the session for the application...
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		
		//specify all the model classes... 
		sessionBuilder.addAnnotatedClass(Users.class);			
		sessionBuilder.addAnnotatedClass(Blog.class);	
		sessionBuilder.addAnnotatedClass(Event.class);	
		sessionBuilder.addAnnotatedClass(Friend.class);	
		sessionBuilder.addAnnotatedClass(Job.class);		
		sessionBuilder.addAnnotatedClass(Forum.class);	
		sessionBuilder.addAnnotatedClass(ForumComment.class);
		sessionBuilder.addAnnotatedClass(JobApplication.class);
		
		return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired		//@Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished..
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
	
	return transactionManager;
	}
	
	@Autowired
	@Bean(name = "userDAO")
	public UserDAO getUserDetailsDAO(SessionFactory sessionFactory) {
		return new UserDAOImpl(sessionFactory);
	}
}
