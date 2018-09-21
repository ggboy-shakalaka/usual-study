package mustry.study.springBeanReq;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.ConstructorArgumentValues.ValueHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;

public class Scanner extends ClassPathBeanDefinitionScanner {

	private BeanDefinitionRegistry registry;

	public Scanner(BeanDefinitionRegistry registry) {
		super(registry, false);
		this.registry = registry;
	}

	@Override
	public Set<BeanDefinitionHolder> doScan(String... basePackages) {
		Set<BeanDefinition> candidates = new LinkedHashSet<BeanDefinition>();

		for (String pkg : basePackages) {
			candidates.addAll(findCandidateComponents(pkg));
		}

		for (BeanDefinition candidate : candidates) {
			Class<?> clazz = null;
			try {
				clazz = this.getClass().getClassLoader().loadClass(candidate.getBeanClassName());
			} catch (ClassNotFoundException e) {
				continue;
			}

			BeanDefinitionHolder definitionHolder = null;
			// 判断此class是否含有remote注解
			if (isRemoteInterface(clazz)) {
				// 创建bean定义持有器,其中candidate为bean定义,beanName为Id
				definitionHolder = new BeanDefinitionHolder(candidate, clazz.getName());
				// 定义beanClassName,beanClassName为实例bean的name
				definitionHolder.getBeanDefinition().setBeanClassName(BeanProxyFactory.class.getName());
				// 定义bean构造器传入参数
				ConstructorArgumentValues argumentValues = new ConstructorArgumentValues();
				argumentValues.addGenericArgumentValue(new ValueHolder(clazz));
				argumentValues.addGenericArgumentValue(new ValueHolder(new DemoReqInvocationHandler(clazz)));
				// 将构造器参数传入bean定义持有器
				definitionHolder.getBeanDefinition().getConstructorArgumentValues().addArgumentValues(argumentValues);
			}
			if (definitionHolder != null) {
				// 将bean定义持有器注册进springIoc容器中
				this.registerBeanDefinition(definitionHolder, this.registry);
			}
			// 此时除了关键的springBean其他bean并没有实例化,所以将bean定义放入注册表中,而不是bean实例
		}
		return null;
	}

	private static boolean isRemoteInterface(Class<?> clazz) {
		if (clazz == null) {
			return false;
		}
		if (clazz.isAnnotationPresent(Remote.class)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isCandidateComponent(MetadataReader metadataReader) {
		return true;
	}

	@Override
	public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return true;
	}

}