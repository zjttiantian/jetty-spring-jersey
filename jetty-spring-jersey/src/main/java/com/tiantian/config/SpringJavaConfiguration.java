package com.tiantian.config;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

//@Component
@Configuration
@ComponentScan(basePackages = {"com.tiantian"})
@EnableTransactionManagement
public class SpringJavaConfiguration {

	private DataSource dataSource;

	@Bean(autowire = Autowire.BY_TYPE)
	public DataSource dataSource() {
		PoolProperties poolProperties = new PoolProperties();
		poolProperties.setDriverClassName("com.mysql.jdbc.Driver");
		poolProperties.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=utf-8&amp;autoReconnect=true&amp;failOverReadOnly=false&amp;maxReconnects=10");
		poolProperties.setUsername("root");
		poolProperties.setPassword("");
		poolProperties.setJmxEnabled(true);
		poolProperties.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
		poolProperties.setRemoveAbandonedTimeout(60);
		poolProperties.setRemoveAbandoned(true);
		poolProperties.setLogAbandoned(false);
		poolProperties.setMinIdle(10);
		poolProperties.setMinEvictableIdleTimeMillis(30000);
		poolProperties.setMaxWait(10);
		poolProperties.setInitialSize(2);
		poolProperties.setMaxActive(10);
		poolProperties.setTimeBetweenEvictionRunsMillis(30000);
		poolProperties.setValidationQuery("SELECT 1");
		poolProperties.setValidationInterval(30000);
		poolProperties.setTestOnReturn(false);
		poolProperties.setTestOnBorrow(true);
		poolProperties.setTestWhileIdle(true);
		poolProperties.setJmxEnabled(true);
		dataSource = new DataSource();
		dataSource.setPoolProperties(poolProperties);
		return dataSource;
	}


	@Bean(name="sqlSessionFactory" )
	public SqlSessionFactoryBean getSqlSessionFactory() throws IOException{
		SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
		bean.setDataSource(dataSource());

		//方法一、使用mybatis-config.xml这种方式需要在配置文件中一个一个添加mapper文件路径，不方便
		/*org.springframework.core.io.Resource re =new ClassPathResource("mybatis-config.xml");
		bean.setConfigLocation(re);*/

		//方法二、使用通配符，方便简单
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		bean.setMapperLocations(resolver.getResources("classpath*:/mybatis/*/*Mapper.xml"));

		return bean;
	}

	@Bean(name="mapper",autowire=Autowire.BY_NAME)
	public MapperScannerConfigurer getMapperScannerConfigurer(){
		MapperScannerConfigurer conf=new MapperScannerConfigurer();
		conf.setBasePackage("com.tiantian.mapper");
		conf.setSqlSessionFactoryBeanName("sqlSessionFactory");
		return conf;
	}

	@Bean(name = "transactionManager")
	public DataSourceTransactionManager dataSourceTransactionManager(){
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource);
		return dataSourceTransactionManager;
	}

}
