package ggboy.study.java.springBeanReq;

import ggboy.study.java.springBeanReq.info.ReqInfo;
import ggboy.study.java.springBeanReq.info.RespInfo;

@Remote(service = "demo")
public interface Demo {
	public RespInfo action(ReqInfo req);
}