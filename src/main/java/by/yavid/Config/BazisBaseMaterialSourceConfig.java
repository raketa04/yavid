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
        basePackages = "by.yavid.Repository.BazisBaseMaterial",
        entityManagerFactoryRef = "BazisBaseMaterialEntityManagerFactory",
        transactionManagerRef = "BazisBaseMaterialTransactionManager"
)
public class BazisBaseMaterialSourceConfig {

    private Environment env;

    public BazisBaseMaterialSourceConfig(Environment env) {
        this.env = env;
    }

    @Bean
    @ConfigurationProperties(prefix="datasource.basematerial")
    public DataSourceProperties BazisBaseMaterialDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource BazisBaseMaterialDataSource() {
        DataSourceProperties bazisBaseMaterialDataSourceProperties = BazisBaseMaterialDataSourceProperties();
        return DataSourceBuilder.create()
                .driverClassName(bazisBaseMaterialDataSourceProperties.getDriverClassName())
                .url(bazisBaseMaterialDataSourceProperties.getUrl())
                .username(bazisBaseMaterialDataSourceProperties.getUsername())
                .password(bazisBaseMaterialDataSourceProperties.getPassword())
                .build();
    }

    @Bean
    public PlatformTransactionManager BazisBaseMaterialTransactionManager()
    {
        EntityManagerFactory factory = BazisBaseMaterialEntityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean BazisBaseMaterialEntityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(BazisBaseMaterialDataSource());
        factory.setPackagesToScan(new String[]{"by.yavid.Entity.BazisBaseMaterial"});
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
        factory.setJpaProperties(jpaProperties);

        return factory;
    }


}
