package learn.test.message;

import java.nio.charset.Charset;

import learn.test.util.TypeTrans;

import org.apache.commons.lang3.ArrayUtils;

public class MessagePropAnalysis {

	public static int PropertySplit(String propertyValue) {

		String[] propertyValueSplitList = propertyValue.split(",");

		return Integer.parseInt(propertyValueSplitList[0]);
	}
	
	public static String PropertyAnalyzer(String propertyValue, byte[] bytes) {
		// 返回值
		String propertyReturnValue = "";

		String[] propertyValueSplitList = propertyValue.split(",");

		// 得到属性长度
		int propertyLength = Integer.parseInt(propertyValueSplitList[0]);
		// 取出本次转换的字节
		byte[] bytesTemp = ArrayUtils.subarray(bytes, 0,
				propertyLength);
		// 得到属性类型
		String propertyType = propertyValueSplitList[1];

		// ----------------根据不同的属性类型进行计算----------------------------------
		// byte类型---（对应byte类型）
		if (MessagePropType.TYPE_BYTE.equals(propertyType)) {
			propertyReturnValue = TypeTrans.bytes2String(bytesTemp);
		}
		// String类型---（对应char类型）
		else if (MessagePropType.TYPE_STRING.equals(propertyType)) {
			
			propertyReturnValue = new String(bytesTemp,Charset.forName("GBK"));
		}
		// int类型---(对应Integer类型)
		else if (MessagePropType.TYPE_INTEGER.equals(propertyType)) {
			propertyReturnValue = String.valueOf(TypeTrans
					.byte2Int(bytesTemp));
		}
		// long类型--（对应Dword类型）
		else if (MessagePropType.TYPE_LONG.equals(propertyType)) {
			propertyReturnValue = String.valueOf(TypeTrans
					.bytes2Long(bytesTemp));
		}
		// short类型--（对应word类型）
		else if (MessagePropType.TYPE_SHORT.equals(propertyType)) {
			propertyReturnValue = String.valueOf(TypeTrans
					.byte2Short(bytesTemp));
		}
		// BIT类型--（对应位元计算）
		else if (MessagePropType.TYPE_BIT.equals(propertyType)) {
			propertyReturnValue = TypeTrans.bytesToBit(bytesTemp);
		}
		// BIT类型--（int64）
		else if (MessagePropType.TYPE_INT64.equals(propertyType)) {
//			Long t = TypeTrans.bytes2Llong(bytesTemp);
			propertyReturnValue = String
					.valueOf(TypeTrans.bytes2Llong(bytesTemp));
		}
		return propertyReturnValue;

	}
}
