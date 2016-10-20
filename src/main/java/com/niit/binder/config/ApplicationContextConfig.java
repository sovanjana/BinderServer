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

import com.niit.binder.model.Blog;
import com.niit.binder.model.Chat;
import com.niit.binder.model.Event;
import com.niit.binder.model.Forum;
import com.niit.binder.model.ForumComment;
import com.niit.binder.model.Friend;
import com.niit.binder.model.FriendRequest;
import com.niit.binder.model.Job;
import com.niit.binder.model.Users;

@Configuration
@ComponentScan("com.niit.binder")
@EnableTransactionManagement
public class ApplicationContextConfig {
	@Bean(name="dataSource")
	public DataSource getDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
				/*--- Database connection settings ---*/
		dataSource.setDriverClassName("org.h2.Driver");		//specify the driver...
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/binderdb");		//specify the db_url...
		dataSource.setUsername("sa");		//specify the db_username...
		dataSource.setPassword("sa");		//specify the db_password...
		return dataSource;                                    //we are using h2 db, as it is n memory database...
	}	
	
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");		//echo all excuted SQL to stdout...
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");		//specify which database server you are using...
		properties.put("hibernate.dbm2ddl.auto", "update");		//hbm2ddl.auto property is a property that will define exactly which type of operation you want. It could be create, create-drop, update and validate...
		return properties;
		}
	
	@Autowired		//@Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished..
	@Bean(name = "sessionFactory")			//sessionfactory creates the session for the application...
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		
		//specify all the model classes... 
		sessionBuilder.addAnnotatedClass(Users.class);	
		
		sessionBuilder.addAnnotatedClass(Blog.class);	
		sessionBuilder.addAnnotatedClass(Chat.class);	
		sessionBuilder.addAnnotatedClass(Event.class);	
		sessionBuilder.addAnnotatedClass(Friend.class);	
		sessionBuilder.addAnnotatedClass(FriendRequest.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		
		sessionBuilder.addAnnotatedClass(Forum.class);	
		sessionBuilder.addAnnotatedClass(ForumComment.class);	
		
		return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired		//@Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished..
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
	
	return transactionManager;
	}
	
	/*@Autowired
	@Bean(name = "userDAO")
	public UserDAO getUserDetailsDAO(SessionFactory sessionFactory) {
		return new UserDAOImpl(sessionFactory);
	}*/
}
