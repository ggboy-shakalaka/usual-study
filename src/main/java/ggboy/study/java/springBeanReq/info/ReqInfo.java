package ggboy.study.java.springBeanReq.info;

import java.io.Serializable;

public class ReqInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
