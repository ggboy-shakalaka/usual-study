package ggboy.study.java.serial;

import ggboy.java.common.exception.CommonUtilException;

public class Main {
	public static void main(String[] args) throws CommonUtilException {
		final SerialService service = new SerialService();
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() { @Override public void run() {
				String num = service.getSerial("A");
				System.out.println("serial num is : " + num);
			}}).start();
		}
		
		for(String item : service.getSerial("A",100)) {
			System.out.println(item);
		}
	}
	
	
}
