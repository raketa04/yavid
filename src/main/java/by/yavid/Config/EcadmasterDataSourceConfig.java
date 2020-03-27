package by.yavid.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = "by.yavid.Repository.Ecadmaster",
        entityManagerFactoryRef = "ecadmasterEntityManagerFactory",
        transactionManagerRef = "ecadmasterTransactionManager"
)
public class EcadmasterDataSourceConfig {
    @Autowired
    private Environment env;

    @Bean
    @ConfigurationProperties(prefix="datasource.ecadmaster")
    public DataSourceProperties ecadmasterDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource ecadmasterDataSource() {
        DataSourceProperties ecadmasterDataSourceProperties = ecadmasterDataSourceProperties();
        return DataSourceBuilder.create()
                .driverClassName(ecadmasterDataSourceProperties.getDriverClassName())
                .url(ecadmasterDataSourceProperties.getUrl())
                .username(ecadmasterDataSourceProperties.getUsername())
                .password(ecadmasterDataSourceProperties.getPassword())
                .build();
    }

    @Bean
    public PlatformTransactionManager ecadmasterTransactionManager()
    {
        EntityManagerFactory factory = ecadmasterEntityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean ecadmasterEntityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(ecadmasterDataSource());
        factory.setPackagesToScan(new String[]{"by.yavid.Entity.Ecadmaster"});
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
        factory.setJpaProperties(jpaProperties);

        return factory;
    }
}
