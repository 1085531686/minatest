package learn.test.util;

import java.nio.ByteBuffer;

import org.apache.commons.lang3.ArrayUtils;

public class Util {

	
	//Æ´½ÓbyteÊý×é
	public static byte[] concat(byte[] a, byte[] b) {
		byte[] c = new byte[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;

	}
	
	public static String appendBlankRight(String source, int len) {
		StringBuffer buff = new StringBuffer();
		buff.append(source);
		if (source.getBytes().length < len) {
			buff.append(generateBlankStr(len - source.getBytes().length));
		}
		return buff.toString();
	}
	public static String generateBlankStr(int len) {
		return generateFixupStr(" ", len);
	}
	
	public static String generateFixupStr(String placeholder, int len) {
		String target = "";

		for (int i = 0; i < len; i++) {
			target += placeholder;
		}

		return target;
	}

	public static String appendZeroLeft(String source, int len) {
		String target = source;
		StringBuffer buff = new StringBuffer();
		if (source.getBytes().length < len) {
			buff.append(generateZeroStr(len - source.getBytes().length));
			buff.append(target);
		}
		if (source.getBytes().length >= len) {
			target = source.substring(0, len);
			buff.append(target);
		}

		return buff.toString();
	}
	public static String generateZeroStr(int len) {
		return generateFixupStr("0", len);
	}
	
	

}
