package learn.test.util;

import java.nio.ByteBuffer;

public class TypeTrans {
    /**
     * 字节数字转10进制数 字符串
     * @param bytes
     * @return
     */
	public static String bytes2String(byte[] bytes) {
		// 将字节数组转换16进制字符串
		String oxString = bytes2HexString(bytes);
		// 将16进制字符串转换为long
		Long longDate = hex2dec(oxString);
		String ret = String.valueOf(longDate);
		return ret;
	}
	/**
	 * 字节数组转 16 进制字符串
	 * @param b
	 * @return
	 */
	public static String bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}
	/**
	 * 16 进制字符串转 10进制数 
	 * @param hexStr
	 * @return
	 */
	public static Long hex2dec(String hexStr) {
		return Long.parseLong(hexStr, 16);
	}
	
	public static int byte2Int(byte[] b) {
		ByteBuffer buffer = ByteBuffer.wrap(b);
		return buffer.getInt();
	}
	public static short byte2Short(byte[] b) {
		ByteBuffer buffer = ByteBuffer.wrap(b);
		return buffer.getShort();
	}
	public static long bytes2Long(byte b[]) {
		int firstByte = (0x000000FF & ((int) b[3]));
		int secondByte = (0x000000FF & ((int) b[2]));
		int thirdByte = (0x000000FF & ((int) b[1]));
		int fourthByte = (0x000000FF & ((int) b[0]));
		long unsignedLong = ((long) (firstByte | secondByte << 8
				| thirdByte << 16 | fourthByte << 24)) & 0xFFFFFFFFL;

		return unsignedLong;
	}
	public static String bytesToBit(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		int a = buffer.getInt();
		String oxString = dec2Hex(a);
		return oxString;
	}
	/**
	 * 10 进制数转16 进制
	 * @param dec
	 * @return
	 */
	public static String dec2Hex(int dec) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			int tmp = (dec >> (7 - i % 8) * 4) & 0x0f;
			if (tmp < 10)
				sb.append(tmp);
			else
				sb.append((char) ('A' + (tmp - 10)));
		}
		return sb.toString();
	}
	
	public static long bytes2Llong(byte b[]) {
		ByteBuffer buffer = ByteBuffer.wrap(b);
		return buffer.getLong();
	}
	public static byte[] int2ByteLittleEndian(int number) {
		int temp = number;
		byte[] b = new byte[4];
		for (int i = 0; i < b.length; i++) {
			b[i] = new Integer(temp & 0xff).byteValue();// 
			temp = temp >> 8; // 向右移8位
		}
		return b;
	}
	
	public static byte[] int2ByteBigEndian(int number) {
		byte[] b = new byte[4];
		b[3] = (byte) (number & 0xff);
		b[2] = (byte) (number >> 8 & 0xff);
		b[1] = (byte) (number >> 16 & 0xff);
		b[0] = (byte) (number >> 24 & 0xff);
		return b;

	}

}
