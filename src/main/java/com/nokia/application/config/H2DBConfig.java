package com.nokia.application.config;
import static java.util.Collections.singletonMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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

@Configuration
@EnableJpaRepositories(

        entityManagerFactoryRef = "h2EntityManagerFactory",
        transactionManagerRef = "h2TransactionManager",
        basePackages = "com.nokia.application.h2repo"
)
@EnableTransactionManagement
public class H2DBConfig {
    
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.h2.datasource")
	public DataSource h2DataSource() {
		return DataSourceBuilder.create().build();
	}
	
	
    @Bean(name = "h2EntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory(@Autowired(required =true) final EntityManagerFactoryBuilder builder ) {
        return builder.dataSource(h2DataSource()).packages("com.nokia.application.model").persistenceUnit("h2Db")
                .properties(singletonMap("hibernate.hbm2ddl.auto", "create-drop")).properties(singletonMap("hibernate.dialect", "org.hibernate.dialect.H2Dialect")).build();
    }
    @Primary
    @Bean(name = "h2TransactionManager")
    public PlatformTransactionManager firstTransactionManager(@Qualifier("h2EntityManagerFactory")
                                                              EntityManagerFactory h2EntityManagerFactory) {

        return new JpaTransactionManager(h2EntityManagerFactory);
    }
}