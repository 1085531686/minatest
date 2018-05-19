package learn.test.socketCliet;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import learn.test.util.TypeTrans;
import learn.test.util.Util;

public class CreateMsg {

	public static byte[] creatMessage(Map<String, String> msgHead,byte[] body) throws Exception {
		// 压缩类型
		String zipType = Util.appendBlankRight(msgHead.get("zipType"), 1);
		// 发送机构代码
		String senderOrgCode =  Util.appendBlankRight(msgHead.get("senderOrgCode"), 3);
		// 发送机构类型
		String senderOrgType =  Util.appendZeroLeft(msgHead.get("senderOrgType"), 2);
		//最终发送的机构信息（3位机构号+2位机构类型+3位空格）
		String senderOrg = Util.appendBlankRight((senderOrgCode+senderOrgType),8);
		
		// 接收机构代码
		String recvOrgCode = Util.appendBlankRight(msgHead.get("recvOrgCode"), 3);
		//接收机构类型
		String recvOrgType = Util.appendZeroLeft(msgHead.get("recvOrgType"), 2);
		//最终发送的机构信息（3位机构号+2位机构类型+3位空格）
		String recvOrg = Util.appendBlankRight((recvOrgCode+recvOrgType),8);
		//发送时间
		Date traddate = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMddHHmmss");
		String sendTime =  formatDate.format(traddate);

		// md5
		MessageDigest alga = MessageDigest.getInstance("MD5");
		String verifyCode = TypeTrans.bytes2HexString(alga.digest(body));
		//拼接报文头字符串
		String mes = msgHead.get("msgID") + msgHead.get("msgType") + zipType + senderOrg
				+ recvOrg + sendTime + verifyCode;
		System.out.println("从报文编码开始报文头内容："+mes);
		byte mesByte[] = mes.getBytes();
		//拼接报文头和报文体
		byte bytes[] = Util.concat(mesByte,body);
		
		int length=bytes.length+4;
		byte [] lengthbyte=TypeTrans.int2ByteBigEndian(length);

        byte[] sum=Util.concat(lengthbyte, bytes);
        return sum;
	}
}
