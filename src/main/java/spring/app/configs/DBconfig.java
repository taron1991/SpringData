package spring.app.configs;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "spring.app.repositories",entityManagerFactoryRef = "session")
public class DBConfig {
    /**
     * Bean for connection
     * @param
     * @return
     */ @Bean
    public BasicDataSource getData(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.postgresql.Driver");
        basicDataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        basicDataSource.setPassword("westside");
        basicDataSource.setUsername("postgres");
        return basicDataSource;
    }
    /**
     * Bean for SPRING ORM
     * @param basicDataSource
     * @return
     */
    @Bean("session")
    public LocalSessionFactoryBean sessionFactory(BasicDataSource basicDataSource){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(basicDataSource);
        localSessionFactoryBean.setPackagesToScan("spring.app.models");
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        localSessionFactoryBean.setHibernateProperties(properties);
        return localSessionFactoryBean;
    }

    /**
     * Bean for SPRING DATA
     * @param basicDataSource
     * @return
     */
    @Bean("hiberJpa")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(BasicDataSource basicDataSource){
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);

        LocalContainerEntityManagerFactoryBean local = new LocalContainerEntityManagerFactoryBean();
        local.setDataSource(basicDataSource);
        local.setPackagesToScan("spring.app.models");
        local.setJpaVendorAdapter(vendorAdapter);
        return local;
    }

    /**
     * Bean for SPRING DATA
     * @param
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager( EntityManagerFactory entityManager){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManager);
        return jpaTransactionManager;
    }

    /**
     * Bean for SPRING JDBC
     * @param basicDataSource
     * @return
     */
    @Bean
    public JdbcTemplate jdbcTemplate(BasicDataSource basicDataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(basicDataSource);
        return jdbcTemplate;
    }
}
