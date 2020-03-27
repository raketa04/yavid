package by.yavid.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.unit.DataSize;

import javax.persistence.EntityManagerFactory;
import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableJpaRepositories(
        basePackages = "by.yavid.Repository.Workbase",
        entityManagerFactoryRef = "workbaseEntityManagerFactory",
        transactionManagerRef = "workbaseTransactionManager"
)
public class WorkbaseDataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(30));
        factory.setMaxRequestSize(DataSize.ofMegabytes(30));
        return factory.createMultipartConfig();
    }

    @Bean
    @ConfigurationProperties(prefix="datasource.workbase")
    public DataSourceProperties workbaseDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource workbaseDataSource() {
        DataSourceProperties workbaseDataSourceProperties = workbaseDataSourceProperties();
        return DataSourceBuilder.create()
                .driverClassName(workbaseDataSourceProperties.getDriverClassName())
                .url(workbaseDataSourceProperties.getUrl())
                .username(workbaseDataSourceProperties.getUsername())
                .password(workbaseDataSourceProperties.getPassword())
                .build();
    }

    @Bean
    public PlatformTransactionManager workbaseTransactionManager()
    {
        EntityManagerFactory factory = workbaseEntityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean workbaseEntityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(workbaseDataSource());
        factory.setPackagesToScan(new String[]{"by.yavid.Entity.Workbase"});
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
        jpaProperties.put("hibernate.format_sql", env.getProperty("spring.jpa.format_sql"));
        factory.setJpaProperties(jpaProperties);

        return factory;
    }

}
