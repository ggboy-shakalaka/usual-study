package mustry.study.springBeanReq;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import mustry.study.springBeanReq.info.ReqInfo;
import mustry.study.springBeanReq.info.RespInfo;

@Service("demo")
public class DemoImpl implements Demo {

	@Override
	public RespInfo action(ReqInfo req) {
		System.out.println("进入实现类.");
		System.out.println("请求参数:"+JSON.toJSONString(req));
		RespInfo respInfo = new RespInfo();
		respInfo.setCode("success");
		respInfo.setResult("调用成功!");
		return respInfo;
	}

}
