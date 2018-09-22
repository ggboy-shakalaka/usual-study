package ggboy.study.java.common.exception;

public class DemoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String code;
	
	public DemoException(String message) {
		super(message);
	}
	
	public DemoException(String message,Throwable cause) {
		super(message,cause);
	}
	
	public DemoException(String code,String message) {
		super(message);
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
}
