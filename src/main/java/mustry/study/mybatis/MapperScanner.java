package mustry.study.mybatis;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.stereotype.Component;

@Component("mapperScanner")
public class MapperScanner extends MapperScannerConfigurer {
	public MapperScanner() {
		setBasePackage("mustry.study.mybatis");
		setSqlSessionFactoryBeanName("sqlSessionFactory");
	}
}