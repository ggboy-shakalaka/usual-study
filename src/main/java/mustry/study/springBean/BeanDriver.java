package mustry.study.springBean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanDriver implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory arg0) throws BeansException {
	}

	/*
	 *  spring在初始化,并且实例小部分bean后,会调用此接口
	 *  此时将bean定义放入注册表中,托管到spring进行实例化并注入到bean容器中
	 */
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		String[] packages = this.getClass().getPackage().getName().split("\\.");
		String pkg = packages[0] + "." + packages[1] + "." + packages[2];
		new Scanner(registry).scan(pkg);
	}

}
