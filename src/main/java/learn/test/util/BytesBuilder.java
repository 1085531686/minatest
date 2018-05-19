package learn.test.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 
 * 
 * 
 * 
 */
public class BytesBuilder {

	private int count;

	private byte[] value;

	public BytesBuilder() {
		this(16);
	}

	public BytesBuilder(byte[] bytes) {
		this(bytes.length + 16);
		append(bytes);
	}

	public BytesBuilder(int capacity) {
		value = new byte[capacity];
	}

	public BytesBuilder append(byte b) {
		int newCount = count + 1;
		if (newCount > value.length)
			expandCapacity(newCount);
		value[count] = b;
		count = newCount;
		return this;
	}

	public BytesBuilder append(byte[] bytes) {
		int newCount = count + bytes.length;
		if (newCount > value.length)
			expandCapacity(newCount);
		System.arraycopy(bytes, 0, value, count, bytes.length);
		count = newCount;
		return this;
	}

	public BytesBuilder append(byte[] bytes, int offset, int len) {
		int newCount = count + len;
		if (newCount > value.length)
			expandCapacity(newCount);
		System.arraycopy(bytes, offset, value, count, len);
		count = newCount;
		return this;
	}

	/**
	 * 获取缓冲区大小
	 * 
	 * @return 缓冲区大小
	 */
	public int capacity() {
		return value.length;
	}

	private void expandCapacity(int minimumCapacity) {
		int newCapacity = (value.length + 1) * 2;
		if (newCapacity < 0) {
			newCapacity = Integer.MAX_VALUE;
		} else if (minimumCapacity > newCapacity) {
			newCapacity = minimumCapacity;
		}
		byte[] newValue = new byte[newCapacity];
		System.arraycopy(value, 0, newValue, 0, count);
		value = newValue;
	}

	/**
	 * 获取指定位置的数据
	 * 
	 * @param i
	 * @return byte数据
	 */
	public byte getByte(int i) {
		if (i < 0)
			i = 0;
		else if (i > count)
			i = count;
		return value[i];
	}

	/**
	 * 获取数据长度
	 * 
	 * @return 数据长度
	 */
	public int length() {
		return count;
	}

	/**
	 * 获取数据
	 * 
	 * @return byte数组
	 */
	public byte[] toBytes() {
		return ArrayUtils.subarray(value, 0, count);
	}

	/**
	 * 获取数据
	 * 
	 * @param offset
	 *            起始偏移
	 * @param len
	 *            长度
	 * @return byte数组
	 */
	public byte[] toBytes(int offset, int len) {
		len += offset;
		if (len > count)
			len = count;
		return ArrayUtils.subarray(value, offset, len);
	}

	/**
	 * 获取数据的字符串形式
	 * 
	 * @return 字符串
	 */
	public String toString() {
		return new String(toBytes());
	}

	/**
	 * 获取数据的字符串形式
	 * 
	 * @param offset
	 *            起始偏移
	 * @param len
	 *            长度
	 * @return 字符串
	 */
	public String toString(int offset, int len) {
		return new String(toBytes(offset, len));
	}

	/**
	 * 获取数据的字符串形式
	 * 
	 * @param offset
	 *            起始偏移
	 * @param len
	 *            长度
	 * @param charsetName
	 *            字符集名称
	 * @return 字符串
	 * @throws UnsupportedEncodingException
	 */
	public String toString(int offset, int len, String charsetName)
			throws UnsupportedEncodingException {
		return new String(toBytes(offset, len), charsetName);
	}

	/**
	 * 获取数据的字符串形式
	 * 
	 * @param charsetName
	 *            字符集名称
	 * @return 字符串
	 * @throws UnsupportedEncodingException
	 */
	public String toString(String charsetName)
			throws UnsupportedEncodingException {
		return new String(toBytes(), charsetName);
	}

}
