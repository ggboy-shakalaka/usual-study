package ggboy.study.java.springTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ggboy.study.java.common.exception.DemoException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/test.xml" })
public class SpringTest {

	@Test
	public void test() throws DemoException {
	}
}