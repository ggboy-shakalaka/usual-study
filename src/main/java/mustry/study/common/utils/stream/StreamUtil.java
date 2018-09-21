package mustry.study.common.utils.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import mustry.study.common.exception.DemoException;

@Deprecated
public class StreamUtil {
	
	/*
	 * 直接用spring的StreamUtils
	 */
	
	public static byte[] input2Byte(InputStream is) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			byte[] data = new byte[100];
			int rc = 0;
			while ((rc = is.read(data, 0, 100)) > 0) {
				os.write(data, 0, rc);
			}
			return os.toByteArray();
		} catch (Exception e) {
			throw new DemoException("流转换出错",e);
		}
	}
	
	public static InputStream byte2Input(byte[] data) {
		return new ByteArrayInputStream(data);  
	}
}