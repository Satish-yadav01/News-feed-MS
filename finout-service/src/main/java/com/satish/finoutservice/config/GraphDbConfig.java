package com.satish.finoutservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author : Satish Yadav
 * @purpose :
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mysqlEntityManagerFactory",
        transactionManagerRef = "mysqlTransactionManager",
        basePackages = {"com.satish.finoutservice.repo.graph" }
)
@Slf4j
public class GraphDbConfig {

    @Primary
    @Bean("mbproperties")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties mysqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean("mysqlDataSource")
    public DataSource mysqlDataSource(
            @Qualifier("mbproperties") DataSourceProperties dataSourceProperties) {
//				dataSourceProperties.setPassword(AESEncryptionUtility.decrypt(dataSourceProperties.getPassword(),AESEncryptionUtility.SECRET_KEYS));

        System.out.println("------------------creating mysqlDataSource-----");
        DataSource build = dataSourceProperties.initializeDataSourceBuilder().build();
        System.out.println("build : " + build.toString());
        return build;
//		try {
//			// Decrypt the password and initialize the data source
//			dataSourceProperties.setPassword(AESEncryptionUtility.decrypt(dataSourceProperties.getPassword(), AESEncryptionUtility.SECRET_KEYS));
//			return dataSourceProperties.initializeDataSourceBuilder().build();
//		} catch (DataAccessException | IllegalArgumentException ex) {
//			// Log the error and throw a custom exception to be handled globally
//			log.error("Error occurred while creating the MySQL DataSource", ex);
////			throw new handleSQLException("Unable to establish a connection to the MySQL database.", ex);
//		}
    }

    @Primary
    @Bean(name = "mysqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
            @Qualifier("mysqlDataSource") DataSource hubDataSource, EntityManagerFactoryBuilder builder) {
        return builder.dataSource(hubDataSource)
                .packages("com.satish.finoutservice.model.entity.graph")
                .persistenceUnit("mysql")
                .build();
    }

    @Primary
    @Bean(name="mysqlTransactionManager")
    public PlatformTransactionManager mysqlTransactionManager(
            @Qualifier("mysqlEntityManagerFactory") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}
