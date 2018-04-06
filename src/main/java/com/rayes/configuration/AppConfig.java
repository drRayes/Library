package com.rayes.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rayes.model.entity.Book;
import com.rayes.model.entity.Person;
import com.rayes.model.entity.Role;
import com.rayes.model.entity.Subscription;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * Class of spring and spring mvc configuration.
 *
 * @author rayes
 */

@EnableWebMvc
@Configuration
@ComponentScan({ "com.rayes.*" })
@EnableTransactionManagement
@Import({ SecurityConfig.class })
public class AppConfig implements WebMvcConfigurer {



	private Properties getHibernateProperties(){
		Properties p = new Properties();
		p.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		p.put("hibernate.show_sql","true");
		p.put("hibernate.hbm2ddl.auto", "update");
		return p;
	}

	@Autowired
	@Bean(name="dataSource")
	public DataSource getH2() {
		System.out.println("Hibernate initiated");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:~/test");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		System.out.println("connection es");
		return dataSource;
	}

	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSession(DataSource dataSource) {
		LocalSessionFactoryBuilder factoryBuilder = new LocalSessionFactoryBuilder(dataSource);
		factoryBuilder.addProperties(getHibernateProperties());
		factoryBuilder.addAnnotatedClass(Book.class);
		factoryBuilder.addAnnotatedClass(Person.class);
		factoryBuilder.addAnnotatedClass(Subscription.class);
		factoryBuilder.addAnnotatedClasses(Role.class);
		return factoryBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransaction(SessionFactory sessionFactory) {

		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
		
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		converter.setObjectMapper(objectMapper);
		List<MediaType> list = new ArrayList<>();
		list.add(MediaType.APPLICATION_JSON);
		converter.setSupportedMediaTypes(list);
		converters.add(converter);
	}

	
}