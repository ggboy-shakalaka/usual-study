package mustry.study.springBeanReq.info;

import java.io.Serializable;

public class RespInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;
	private String result;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
