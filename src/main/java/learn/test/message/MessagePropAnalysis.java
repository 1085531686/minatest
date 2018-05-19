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
		// ����ֵ
		String propertyReturnValue = "";

		String[] propertyValueSplitList = propertyValue.split(",");

		// �õ����Գ���
		int propertyLength = Integer.parseInt(propertyValueSplitList[0]);
		// ȡ������ת�����ֽ�
		byte[] bytesTemp = ArrayUtils.subarray(bytes, 0,
				propertyLength);
		// �õ���������
		String propertyType = propertyValueSplitList[1];

		// ----------------���ݲ�ͬ���������ͽ��м���----------------------------------
		// byte����---����Ӧbyte���ͣ�
		if (MessagePropType.TYPE_BYTE.equals(propertyType)) {
			propertyReturnValue = TypeTrans.bytes2String(bytesTemp);
		}
		// String����---����Ӧchar���ͣ�
		else if (MessagePropType.TYPE_STRING.equals(propertyType)) {
			
			propertyReturnValue = new String(bytesTemp,Charset.forName("GBK"));
		}
		// int����---(��ӦInteger����)
		else if (MessagePropType.TYPE_INTEGER.equals(propertyType)) {
			propertyReturnValue = String.valueOf(TypeTrans
					.byte2Int(bytesTemp));
		}
		// long����--����ӦDword���ͣ�
		else if (MessagePropType.TYPE_LONG.equals(propertyType)) {
			propertyReturnValue = String.valueOf(TypeTrans
					.bytes2Long(bytesTemp));
		}
		// short����--����Ӧword���ͣ�
		else if (MessagePropType.TYPE_SHORT.equals(propertyType)) {
			propertyReturnValue = String.valueOf(TypeTrans
					.byte2Short(bytesTemp));
		}
		// BIT����--����ӦλԪ���㣩
		else if (MessagePropType.TYPE_BIT.equals(propertyType)) {
			propertyReturnValue = TypeTrans.bytesToBit(bytesTemp);
		}
		// BIT����--��int64��
		else if (MessagePropType.TYPE_INT64.equals(propertyType)) {
//			Long t = TypeTrans.bytes2Llong(bytesTemp);
			propertyReturnValue = String
					.valueOf(TypeTrans.bytes2Llong(bytesTemp));
		}
		return propertyReturnValue;

	}
}
