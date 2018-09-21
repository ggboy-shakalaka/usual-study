package mustry.study.springBeanReq;

import java.lang.reflect.Method;

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

import mustry.study.common.utils.http.HttpRequestUtil;
import mustry.study.common.utils.http.HttpResponseUtil;
import mustry.study.common.utils.serialize.SerializableUtil;
import mustry.study.common.utils.string.StringUtil;
import mustry.study.springBeanReq.info.DemoReq;
import mustry.study.springBeanReq.info.ReqInfo;
import mustry.study.springBeanReq.info.RespInfo;

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
			byte[]  reqData = HttpRequestUtil.req2Byte(request);
			DemoReq demoReq = SerializableUtil.reverseSerialize(reqData, DemoReq.class);
			Object obj = context.getBean(StringUtil.toLowerCase(demoReq.getClazz().getSimpleName()));
			Method method = obj.getClass().getMethod(demoReq.getMethod(), demoReq.getParameterTypes());
			Object respObj = method.invoke(obj, demoReq.getParameters());
			HttpResponseUtil.write(response, SerializableUtil.serialize(respObj));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}