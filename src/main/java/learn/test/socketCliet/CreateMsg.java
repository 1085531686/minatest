package learn.test.socketCliet;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import learn.test.util.TypeTrans;
import learn.test.util.Util;

public class CreateMsg {

	public static byte[] creatMessage(Map<String, String> msgHead,byte[] body) throws Exception {
		// ѹ������
		String zipType = Util.appendBlankRight(msgHead.get("zipType"), 1);
		// ���ͻ�������
		String senderOrgCode =  Util.appendBlankRight(msgHead.get("senderOrgCode"), 3);
		// ���ͻ�������
		String senderOrgType =  Util.appendZeroLeft(msgHead.get("senderOrgType"), 2);
		//���շ��͵Ļ�����Ϣ��3λ������+2λ��������+3λ�ո�
		String senderOrg = Util.appendBlankRight((senderOrgCode+senderOrgType),8);
		
		// ���ջ�������
		String recvOrgCode = Util.appendBlankRight(msgHead.get("recvOrgCode"), 3);
		//���ջ�������
		String recvOrgType = Util.appendZeroLeft(msgHead.get("recvOrgType"), 2);
		//���շ��͵Ļ�����Ϣ��3λ������+2λ��������+3λ�ո�
		String recvOrg = Util.appendBlankRight((recvOrgCode+recvOrgType),8);
		//����ʱ��
		Date traddate = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMddHHmmss");
		String sendTime =  formatDate.format(traddate);

		// md5
		MessageDigest alga = MessageDigest.getInstance("MD5");
		String verifyCode = TypeTrans.bytes2HexString(alga.digest(body));
		//ƴ�ӱ���ͷ�ַ���
		String mes = msgHead.get("msgID") + msgHead.get("msgType") + zipType + senderOrg
				+ recvOrg + sendTime + verifyCode;
		System.out.println("�ӱ��ı��뿪ʼ����ͷ���ݣ�"+mes);
		byte mesByte[] = mes.getBytes();
		//ƴ�ӱ���ͷ�ͱ�����
		byte bytes[] = Util.concat(mesByte,body);
		
		int length=bytes.length+4;
		byte [] lengthbyte=TypeTrans.int2ByteBigEndian(length);

        byte[] sum=Util.concat(lengthbyte, bytes);
        return sum;
	}
}
