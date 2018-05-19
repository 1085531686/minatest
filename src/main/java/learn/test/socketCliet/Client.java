package learn.test.socketCliet;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Client {

	public static void main(String[] args) throws Exception {
		Map<String, String> msgHead = new HashMap<String, String>();
		msgHead.put("msgID", "81");
		msgHead.put("msgType", "01");
		msgHead.put("zipType", "0");
		msgHead.put("senderOrgCode", "001");
		msgHead.put("senderOrgType", "" + 23);
		msgHead.put("recvOrgCode", "");
		msgHead.put("recvOrgType", "");
		StringBuffer message = new StringBuffer();
		message.append("2018-01-01 23:23:23").append("001").append("00").append("³µA12345");
		SocketClient sclient = new SocketClient("127.0.0.1", 1010);
		byte[] msg =CreateMsg.creatMessage(msgHead, message.toString().getBytes(Charset.forName("GBK")));
		sclient.sendMsgQuery(msg);
		
		
	}
}
