package ggboy.study.java.common.utils.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ggboy.study.java.common.exception.DemoException;

public class SerializableUtil {
	public static byte[] serialize(Object object) {
		if (null != object) {
			ByteArrayOutputStream outputStream = null;
			ObjectOutputStream objectOutputStream = null;
			try {
				outputStream = new ByteArrayOutputStream();
				objectOutputStream = new ObjectOutputStream(outputStream);
				objectOutputStream.writeObject(object);
				return outputStream.toByteArray();
			} catch (Exception e) {
				throw new DemoException("序列化异常",e);
			} finally {
				if (null != objectOutputStream) {
					try {
						objectOutputStream.close();
					} catch (IOException e) {
						
					}
				}	
				if (null != outputStream) {
					try {
						outputStream.close();
					} catch (IOException e) {
						
					}
				}
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T reverseSerialize(byte[] data,Class<T> clazz) {
		if (null != data) {
			ByteArrayInputStream inputStream = null;
			ObjectInputStream objectInputStream = null;
			try {
				inputStream = new ByteArrayInputStream(data);
				objectInputStream = new ObjectInputStream(inputStream);
				return (T) objectInputStream.readObject();
			} catch (Exception e) {
				throw new DemoException("反序列化异常",e);
			} finally {
				if (null != objectInputStream) {
					try {
						objectInputStream.close();
					} catch (IOException e) {
						
					}
				}
				if (null != inputStream) {
					try {
						inputStream.close();
					} catch (IOException e) {
						
					}
				}
			}
		}
		return null;
	}
}
