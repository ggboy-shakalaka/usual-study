package ggboy.study.java.spring_mybatis;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component(value="sqlSessionFactory")
public class SqlSessionFactory extends SqlSessionFactoryBean {
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://local:3306/study";
	private String username = "root";
	private String password = "root";
	private int initialSize = 0;
	private int maxActive = 20;
	private int maxIdle = 20;
	private int minIdle = 1;
	private int maxWait = 60000;
	
	public SqlSessionFactory () {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setInitialSize(initialSize);
		dataSource.setMaxActive(maxActive);
		dataSource.setMaxIdle(maxIdle);
		dataSource.setMinIdle(minIdle);
		dataSource.setMaxWait(maxWait);
		
		Resource[] resource = {new ClassPathResource("ggboy/study/java/spring_mybatis/baseMapper.xml")};
		
		this.setDataSource(dataSource);
		this.setMapperLocations(resource);
	}
}
