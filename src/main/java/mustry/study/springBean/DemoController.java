package mustry.study.springBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
	
	@Autowired
	private Demo demo;
	
	@ResponseBody
	@RequestMapping(value="dothing.html",method={RequestMethod.GET})
	public String dothing() {
		demo.action();
		return "success";
	}
}