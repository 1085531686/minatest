package learn.test.message;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

public class MassageParser {

	MessageHead messageHead ;

	public MessageHead getMessageHead() {
		return messageHead;
	}

	public Message parseMessage(byte[] bytes, Message message) {
		messageHead = message.getmHead();
		MessageHead messageHead = this.parseHead(bytes);
		message.setmHead(messageHead);
		bytes = ArrayUtils.subarray(bytes, messageHead.getHeadLength(), bytes.length);
		List<String> props = message.getBody().getMessagebody();
		String keyValue = "";
		for (String prop : props) {
			int itemLen = 0;
			itemLen = MessagePropAnalysis.PropertySplit(prop);
			keyValue = MessagePropAnalysis.PropertyAnalyzer(prop, bytes);
			bytes = ArrayUtils.subarray(bytes, itemLen, bytes.length);
			message.getBody().getValues().add(keyValue);
		}
		return message;
	}

	MessageHead parseHead(byte[] bytes) {

		MessageHead messageHeader = new MessageHead();
		String propertyValue = "";
		int bwtcd = 0;
		int subLength = 0;
		try {
			// ���ĳ��Ƚ���
			propertyValue = this.getMessageHead().getMsgLen();
			subLength = MessagePropAnalysis.PropertySplit(propertyValue);
			bwtcd = bwtcd + subLength;
			messageHeader.setMsgLen(MessagePropAnalysis.PropertyAnalyzer(
					propertyValue, bytes));
			bytes = ArrayUtils.subarray(bytes, subLength, bytes.length);

			// ���ı������
			propertyValue = this.getMessageHead().getMsgID();
			subLength = MessagePropAnalysis.PropertySplit(propertyValue);
			bwtcd = bwtcd + subLength;
			messageHeader.setMsgID(MessagePropAnalysis.PropertyAnalyzer(
					propertyValue, bytes));
			bytes = ArrayUtils.subarray(bytes, subLength, bytes.length);

			// �������ͽ���
			propertyValue = this.getMessageHead().getMsgType();
			subLength = MessagePropAnalysis.PropertySplit(propertyValue);
			bwtcd = bwtcd + subLength;
			messageHeader.setMsgType(MessagePropAnalysis.PropertyAnalyzer(
					propertyValue, bytes));
			bytes = ArrayUtils.subarray(bytes, subLength, bytes.length);

			// ѹ����־
			propertyValue = this.getMessageHead().getZipType();
			subLength = MessagePropAnalysis.PropertySplit(propertyValue);
			bwtcd = bwtcd + subLength;
			messageHeader.setZipType(MessagePropAnalysis.PropertyAnalyzer(
					propertyValue, bytes));
			bytes = ArrayUtils.subarray(bytes, subLength, bytes.length);

			// ���ͻ�����+���ͻ�������
			propertyValue = this.getMessageHead().getSenderOrgCode();
			subLength = MessagePropAnalysis.PropertySplit(propertyValue);
			bwtcd = bwtcd + subLength;
			messageHeader.setSenderOrgCode(MessagePropAnalysis
					.PropertyAnalyzer(propertyValue, bytes));
			bytes = ArrayUtils.subarray(bytes, subLength, bytes.length);

			// ���ջ�����+���ջ�������
			propertyValue = this.getMessageHead().getRecvOrgCode();
			subLength = MessagePropAnalysis.PropertySplit(propertyValue);
			bwtcd = bwtcd + subLength;
			messageHeader.setRecvOrgCode(MessagePropAnalysis.PropertyAnalyzer(
					propertyValue, bytes));
			bytes = ArrayUtils.subarray(bytes, subLength, bytes.length);
			// ����ʱ��
			propertyValue = this.getMessageHead().getSendTime();
			subLength = MessagePropAnalysis.PropertySplit(propertyValue);
			bwtcd = bwtcd + subLength;
			messageHeader.setSendTime(MessagePropAnalysis.PropertyAnalyzer(
					propertyValue, bytes));
			bytes = ArrayUtils.subarray(bytes, subLength, bytes.length);

			// MD5Ч��
			propertyValue = this.getMessageHead().getVerifyCode();
			subLength = MessagePropAnalysis.PropertySplit(propertyValue);
			bwtcd = bwtcd + subLength;
			messageHeader.setVerifyCode(MessagePropAnalysis.PropertyAnalyzer(
					propertyValue, bytes));
			bytes = ArrayUtils.subarray(bytes, subLength, bytes.length);

			messageHeader.setHeadLength(bwtcd);
		} catch (Exception e) {

		}
		return messageHeader;
	}
}
