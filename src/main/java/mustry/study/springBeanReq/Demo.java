package mustry.study.springBeanReq;

import mustry.study.springBeanReq.info.ReqInfo;
import mustry.study.springBeanReq.info.RespInfo;

@Remote(service = "demo")
public interface Demo {
	public RespInfo action(ReqInfo req);
}