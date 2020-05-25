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
        basePackages = "by.yavid.Repository.Yavid",
        entityManagerFactoryRef = "yavidEntityManagerFactory",
        transactionManagerRef = "yavidTransactionManager"
)
public class YavidDataSourceConfig {
    @Autowired
    private Environment env;

    @Bean
    @ConfigurationProperties(prefix="datasource.yavid")
    public DataSourceProperties yavidDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource yavidDataSource() {
        DataSourceProperties yavidDataSourceProperties = yavidDataSourceProperties();
        return DataSourceBuilder.create()
                .driverClassName(yavidDataSourceProperties.getDriverClassName())
                .url(yavidDataSourceProperties.getUrl())
                .username(yavidDataSourceProperties.getUsername())
                .password(yavidDataSourceProperties.getPassword())
                .build();
    }

    @Bean
    public PlatformTransactionManager yavidTransactionManager()
    {
        EntityManagerFactory factory = yavidEntityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean yavidEntityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(yavidDataSource());
        factory.setPackagesToScan(new String[]{"by.yavid.Entity.Yavid"});
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
        factory.setJpaProperties(jpaProperties);

        return factory;
    }
}
