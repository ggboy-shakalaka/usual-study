package mustry.study.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

import mustry.common.exception.CommonUtilException;
import mustry.common.utils.sql.mysql.Select;

public class Main {
	public static void main(String[] args) throws CommonUtilException {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:mustry/study/mybatis/zxc.xml");
		BaseDao dao = (BaseDao) context.getBean("baseDao");
		Select sql = new Select("user");
		sql.addProperty("USER_NAME");
		sql.or("USER_MAIL","like","123%");
		System.out.println(sql.getSql());
		List<Map<String,Object>> users = dao.select(sql);
		System.out.println(JSON.toJSONString(users));
	}
}
