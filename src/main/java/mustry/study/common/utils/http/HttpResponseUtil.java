package mustry.study.common.utils.http;

import javax.servlet.http.HttpServletResponse;

import mustry.study.common.exception.DemoException;

public class HttpResponseUtil {
	public static void write(HttpServletResponse resp,String str) {
		try {
			resp.setContentType("text/plain; charset=utf-8");
			resp.setCharacterEncoding("utf8");
			resp.getWriter().write(str);
		} catch (Exception e) {
			throw new DemoException("resp写入异常",e);
		}
	}
	
	public static void write(HttpServletResponse resp,byte[] data) {
		try {
			resp.getOutputStream().write(data);
		} catch (Exception e) {
			throw new DemoException("resp写入异常",e);
		}
	}
}