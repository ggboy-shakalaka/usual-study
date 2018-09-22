package ggboy.study.java.spring_mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

import ggboy.java.common.exception.CommonUtilException;
import ggboy.java.common.utils.sql.mysql.Select;

public class Main {
	public static void main(String[] args) throws CommonUtilException {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ggboy/study/java/spring_mybatis/spring.xml");
		BaseDao dao = (BaseDao) context.getBean("baseDao");
		Select sql = new Select("select * from invite limit 0,1", true);
		List<Map<String,Object>> users = dao.select(sql);
		System.out.println(JSON.toJSONString(users.get(0)));
	}
}
