package ggboy.study.java.serial;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ggboy.java.common.utils.bean.BeanUtil;
import ggboy.java.common.utils.date.DateUtil;
import ggboy.java.common.utils.lock.StringLock;
import ggboy.java.common.utils.sql.mysql.Select;
import ggboy.study.java.mybatis.BaseDao;

public class SerialService {

	private BaseDao dao;

	public static long haoshi = 0L;
	public static long bianli = 0L;

	public SerialService() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:mustry/study/mybatis/zxc.xml");
		dao = (BaseDao) context.getBean("baseDao");
	}
	
	public String getSerial(String mark) {
		return getSerial(mark,1).get(0);
	}

	public List<String> getSerial(String mark, int size) {
		StringLock lock = StringLock.writeLock(mark);
		try {
			int count = 0;
			List<Map<String, Object>> serial = dao.select(new Select("select * from serial where mark = '" + mark + "'", true));
			if (BeanUtil.isEmpty(serial)) {
				createMark(mark);
			} else {
				count = Integer.valueOf((String) serial.get(0).get("count"));
			}
			List<String> serialNumList = new ArrayList<String>(size);
			for (int i = 1; i <= size; i++) {
				serialNumList.add(mark + (count + i));
			}
			dao.update(new Select("update serial set count = '" + (count + size) + "' where mark = '" + mark + "'", true));
			return serialNumList;
		} finally {
			lock.unlock();
		}
	}

	private void createMark(String mark) {
		Date date = new Date();
		Date invalidDate = new Date(date.getTime() + 1000L);
		Select insert = new Select("insert into serial (mark,count,gmt_create,gmt_invalid) values ('" + mark + "','0',"
				+ DateUtil.toMysqlString(date) + "," + DateUtil.toMysqlString(invalidDate) + ")", true);
		int i = dao.insert(insert);
		System.out.println("insert result : " + i);
	}
}
