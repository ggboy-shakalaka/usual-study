package ggboy.study.java.classFile;

import java.io.File;
import java.util.Arrays;

import mustry.common.exception.CommonUtilException;
import mustry.common.utils.bytee.ByteUtil;
import mustry.common.utils.io.IoUtil;

public class ClassReader {

	private final byte[] classBytes;
	private int headLength;
	private int[] items;
	private final int CLASS_BEGIN = 0xCAFEBABE;

	public ClassReader(File file) {
		this.classBytes = getFileBytes(file);
		if (readUnsignedInt(0) != CLASS_BEGIN)
			throw new IllegalArgumentException("File : \"" + file.getAbsolutePath() + "\" is not a java class.");
		resolveClass();
	}

	void resolveClass() {
		int count = getLength(8);
		items = new int[count];
		int pointer = 10;
		for (int i = 1; i < count; i++) {
			items[i] = pointer + 1;
			int skipSize;
			switch (classBytes[pointer]) {
			case 9:
			case 10:
			case 11:
			case 3:
			case 4:
			case 12:
			case 18:
				skipSize = 5;
				break;
			case 5:
			case 6:
				skipSize = 9;
				i++;
				break;
			case 1:
				skipSize = 3 + getLength(pointer + 1);
				break;
			case 15:
				skipSize = 4;
				break;
			default:
				skipSize = 3;
				break;
			}
			pointer += skipSize;
		}
		headLength = pointer;
	}

	public String getClassName() {
		return readUTF8(items[getIndex(headLength + 2)]);
	}

	public void getRuntimeVisibleAnnotations() {
		int pointer = getAttributesPointer() + 2;
		int annoPointer = 0;
		for (int i = getLength(pointer - 2); i > 0; --i) {
			String attrName = readUTF8(pointer);
			if ("RuntimeVisibleAnnotations".equals(attrName)) {
				annoPointer = pointer + 6;
				break;
			}
			pointer += 6 + readUnsignedInt(pointer + 2);
		}

		if (annoPointer == 0)
			return;

		annoPointer += 2;
		for (int i = getLength(annoPointer - 2); i > 0; --i) {
			String attrName = readUTF8(annoPointer);
			System.out.println(attrName);
			for (int j = getLength(annoPointer + 2); j > 0; --j) {
				String key = readUTF8(annoPointer + 4);
				String value = readUTF8(annoPointer + 7);
				System.out.println(key + " : " + value);
				annoPointer += 5;
			}
			annoPointer += 4;
		}
	}

	int getAttributesPointer() {
		// skip head & interfaces
		int pointer = headLength + 8 + getLength(headLength + 6) * 2;
		// skip fields
		pointer += 2;
		for (int i = getLength(pointer - 2); i > 0; --i) {
			for (int j = getLength(pointer + 6); j > 0; --j) {
				pointer += 6 + readUnsignedInt(pointer + 10);
			}
			pointer += 8;
		}
		// skip methods
		pointer += 2;
		for (int i = getLength(pointer - 2); i > 0; --i) {
			for (int j = getLength(pointer + 6); j > 0; --j) {
				pointer += 6 + readUnsignedInt(pointer + 10);
			}
			pointer += 8;
		}
		return pointer;
	}

	String toString(int start, int length) {
		byte[] data = Arrays.copyOfRange(classBytes, start, start + length);
		return ByteUtil.toString(data);
	}

	public String readUTF8(int index) {
		int item = readUnsignedShort(index);
		if (index == 0 || item == 0) {
			return null;
		}
		index = items[item];
		return toString(index + 2, readUnsignedShort(index));
	}

	byte[] getFileBytes(File file) {
		try {
			return IoUtil.file2bytes(file);
		} catch (CommonUtilException e) {
			e.printStackTrace();
			return null;
		}
	}

	int getLength(int pointer) {
		return readUnsignedShort(pointer);
	}

	int getIndex(int pointer) {
		return readUnsignedShort(pointer);
	}

	int getPointer(int index) {
		return items[index];
	}

	int readUnsignedShort(int index) {
		return ((classBytes[index] & 0xff) << 8) | (classBytes[index + 1] & 0xff);
	}

	int readUnsignedInt(int index) {
		return ((classBytes[index] & 0xff) << 24) | ((classBytes[index + 1] & 0xff) << 16)
				| ((classBytes[index + 2] & 0xff) << 8) | (classBytes[index + 3] & 0xff);
	}

	public void demo() {
		org.springframework.asm.ClassReader cr = new org.springframework.asm.ClassReader(classBytes);
		cr.accept(null, 0);
	}

	public static void main(String[] args) {
		ClassReader classReader = new ClassReader(
				new File("C:\\Users\\A\\Desktop\\Validator.class"));
		System.out.println(classReader.getClassName());
		System.out.println(classReader.classBytes[7]);
		// classReader.getRuntimeVisibleAnnotations();
		// classReader.demo();
	}

}
