package com.nokia.application.config;



import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static java.util.Collections.singletonMap;

@Configuration
@EnableJpaRepositories(

        entityManagerFactoryRef = "hsqlEntityManagerFactory",
        transactionManagerRef = "hsqlTransactionManager",
        basePackages = "com.nokia.application.hsqlrepo"
)
@EnableTransactionManagement
public class HSQLDBConfig {
    
	@Bean
	// @Primary
	@ConfigurationProperties(prefix = "spring.hsql.datasource")
	public DataSource hSQLDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	//@Primary
    @Bean(name = "hsqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory(final EntityManagerFactoryBuilder builder ) {
        return builder.dataSource(hSQLDataSource()).packages("com.nokia.application.model").persistenceUnit("hsqlDb")
                .properties(singletonMap("hibernate.hbm2ddl.auto", "create-drop")).properties(singletonMap("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")).build();
    }
    //@Primary
    @Bean(name = "hsqlTransactionManager")
    public PlatformTransactionManager firstTransactionManager(@Qualifier("hsqlEntityManagerFactory")
                                                              EntityManagerFactory hsqlEntityManagerFactory) {

        return new JpaTransactionManager(hsqlEntityManagerFactory);
    }
}
