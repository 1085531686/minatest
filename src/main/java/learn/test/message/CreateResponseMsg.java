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
		// 报文编码
		String msgID = reqMsgHeader.getMsgID();
		// 报文类型
		String msgType = reqMsgHeader.getMsgType();
		// 报文版本号
		String msgVer = "1";
		// 压缩标志
		String zipType = reqMsgHeader.getZipType();
		System.out.println("===================================="+zipType);
		// 发送方机构代码及机构类型（机构号+机构类型+空格）
		String senderOrgCode = Util.appendBlankRight(reqMsgHeader.getRecvOrgCode(),8);
		// 接收方机构代码及机构类型（机构号+机构类型+空格）
		String recvOrgCode = Util.appendBlankRight(reqMsgHeader.getSenderOrgCode(), 8);
		// 交易日期交易时间
		Date traddate = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMddHHmmss");
		String mesdate = formatDate.format(traddate);
		
		
		// md5
		String verifyCode = null;
		MessageDigest alga = MessageDigest.getInstance("MD5");
		if("FF".equals(responseByte)){
			verifyCode = TypeTrans.bytes2HexString(alga.digest("java接收socket后台程序运行发生异常".getBytes()));
		}else{
			
		    verifyCode = TypeTrans.bytes2HexString(alga.digest(responseByte));
		}
		//组装返回报文
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
			bytesBuilder.append("java接收socket后台程序运行发生异常".getBytes());
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
