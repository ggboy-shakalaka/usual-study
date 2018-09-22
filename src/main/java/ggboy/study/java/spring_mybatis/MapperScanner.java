package ggboy.study.java.spring_mybatis;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.stereotype.Component;

@Component("mapperScanner")
public class MapperScanner extends MapperScannerConfigurer {
	public MapperScanner() {
		setBasePackage("ggboy.study.java.spring_mybatis");
		setSqlSessionFactoryBeanName("sqlSessionFactory");
	}
}