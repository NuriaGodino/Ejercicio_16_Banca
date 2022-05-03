package config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement //habilita la transacccionalidad mediante anotaciones
@PropertySource(value = "classpath:config/application.properties")
@Configuration
@ComponentScan(basePackages = {"service", "converters"})
@EnableJpaRepositories(basePackages = {"dao"}, entityManagerFactoryRef = "factory", transactionManagerRef = "txManager") //le especificamos el nombre dle obj encargado de crear los em y el nombre del obj encargado de la tans
public class ServiceConfig {
	@Value("${driver}")
	String driver;
	@Value("${url}")
	String url;
	@Value("${usuario}")
	String user;
	@Value("${pwd}")
	String pwd;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource data = new DriverManagerDataSource();
		data.setDriverClassName(driver);
		data.setUrl(url);
		data.setUsername(user);
		data.setPassword(pwd);
		return data;
	}

	// adaptador de Hibernate
	@Bean
	public HibernateJpaVendorAdapter adapter() { //obj de spring, realiza la integracion de jpa hibernate dentro de spring
		HibernateJpaVendorAdapter adp = new HibernateJpaVendorAdapter(); //se le da una propiedad para indicar que trabajamos por sql
		adp.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return adp;
	}

	// factoria EntityManager: Objeto para acceder a capa de persistencia con JPA
	@Bean
	public LocalContainerEntityManagerFactoryBean factory(DataSource dataSource, HibernateJpaVendorAdapter adapter) { //configuracion de jpa 
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setPersistenceUnitName("tiendaPU"); //nombre de unidad de persistencia, tinedaPU es un nombre a la unidad de persistencia que puede ser cualquier cosa
		factory.setDataSource(dataSource); //llamada al datasource(datos dde coexion a la bbdd)
		factory.setPackagesToScan("model"); //donde esta el paquete de entidaddes
		Properties props = new Properties();
		props.put("hibernate.enable_lazy_load_no_trans", true);
		factory.setJpaProperties(props);
		factory.setJpaVendorAdapter(adapter); //adaptador de hibernate
		return factory;
	}

	// gestor de transacción
	@Bean
	public JpaTransactionManager txManager(LocalContainerEntityManagerFactoryBean factory) { //todas las operaciones en la persistencia tiene que estar englobadas en una transaccion, asi que este obj lo que hace es gestionar por nostors las transacciones
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(factory.getObject());
		return manager;
	}

}
