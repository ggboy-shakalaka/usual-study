package mustry.study.common.utils.http;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StreamUtils;

import mustry.study.common.exception.DemoException;

public class HttpRequestUtil {
	public static byte[] req2Byte(HttpServletRequest req) {
		try {
			return StreamUtils.copyToByteArray(req.getInputStream());
		} catch (IOException e) {
			throw new DemoException("请求流转换异常", e);
		}
	}
}