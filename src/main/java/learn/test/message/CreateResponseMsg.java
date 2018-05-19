package learn.test.message;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;


import learn.test.util.BytesBuilder;
import learn.test.util.TypeTrans;
import learn.test.util.Util;

public class CreateResponseMsg {

	public static byte[] creatResponseMsg(MessageHead reqMsgHeader,
			byte[] responseByte) throws NoSuchAlgorithmException {

		BytesBuilder bytesBuilder = new BytesBuilder();

		BytesBuilder bytesBuilder1 = new BytesBuilder();
		// ���ı���
		String msgID = reqMsgHeader.getMsgID();
		// ��������
		String msgType = reqMsgHeader.getMsgType();
		// ���İ汾��
		String msgVer = "1";
		// ѹ����־
		String zipType = reqMsgHeader.getZipType();
		System.out.println("===================================="+zipType);
		// ���ͷ��������뼰�������ͣ�������+��������+�ո�
		String senderOrgCode = Util.appendBlankRight(reqMsgHeader.getRecvOrgCode(),8);
		// ���շ��������뼰�������ͣ�������+��������+�ո�
		String recvOrgCode = Util.appendBlankRight(reqMsgHeader.getSenderOrgCode(), 8);
		// �������ڽ���ʱ��
		Date traddate = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMddHHmmss");
		String mesdate = formatDate.format(traddate);
		
		
		// md5
		String verifyCode = null;
		MessageDigest alga = MessageDigest.getInstance("MD5");
		if("FF".equals(responseByte)){
			verifyCode = TypeTrans.bytes2HexString(alga.digest("java����socket��̨�������з����쳣".getBytes()));
		}else{
			
		    verifyCode = TypeTrans.bytes2HexString(alga.digest(responseByte));
		}
		//��װ���ر���
		if("FF".equals(responseByte)){
			bytesBuilder.append("FF".getBytes());
		}else{
			bytesBuilder.append(msgID.getBytes());
		}
		bytesBuilder.append(msgType.getBytes());
		bytesBuilder.append(zipType.getBytes());
		bytesBuilder.append(senderOrgCode.getBytes());
		bytesBuilder.append(recvOrgCode.getBytes());
		bytesBuilder.append(mesdate.getBytes());
		bytesBuilder.append(verifyCode.getBytes());
		if("FF".equals(responseByte)){
			bytesBuilder.append("java����socket��̨�������з����쳣".getBytes());
		}else{
			bytesBuilder.append(responseByte);
		}

		int bwcd = 4 + bytesBuilder.length();

		byte[] bwcdByte = TypeTrans.int2ByteBigEndian(bwcd);
		bytesBuilder1.append(bwcdByte);
		bytesBuilder1.append(bytesBuilder.toBytes());
		return bytesBuilder1.toBytes();
	}

}
