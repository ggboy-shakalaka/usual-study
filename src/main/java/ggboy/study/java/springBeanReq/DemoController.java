package ggboy.study.java.springBeanReq;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import ggboy.study.java.springBeanReq.info.ReqInfo;
import ggboy.study.java.springBeanReq.info.RespInfo;

@Controller
public class DemoController {

	@Resource(name = "mustry.study.springBeanReq.Demo")
	private Demo demo;

	@Autowired
	private ApplicationContext context;

	@ResponseBody
	@RequestMapping(value = "dothing.html", method = { RequestMethod.GET })
	public String dothing() {
		ReqInfo req = new ReqInfo();
		req.setMessage("be a test...");
		System.out.println("dothing -> getthing , reqData : " + JSON.toJSONString(req));
		long start = System.currentTimeMillis();
		RespInfo resp = demo.action(req);
		long end = System.currentTimeMillis() - start;
		System.out.println("dothing <- getthing (耗时:" + end + " ms), respData : " + JSON.toJSONString(resp));
		return "success";
	}

	@RequestMapping(value = "getthing.html", method = { RequestMethod.POST })
	public void getthing(HttpServletRequest request, HttpServletResponse response) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}