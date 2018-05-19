package learn.test.message;

public class MessageHead {

	// 报文长度
	private String msgLen = "4,integer";
	// 报文编码
	private String msgID = "2,String";
	// 报文类型
	private String msgType = "2,String";
	// 压缩类型
	private String zipType = "1,String";
	// 发送机构代码及发送机构类型（前3机构号下来两位是机构类型）
	private String senderOrgCode = "8,String";
	// 接收机构代码及接收机构类型（前3机构号下来两位是机构类型）
	private String recvOrgCode = "8,String";
	// 发送时间
	private String sendTime="14,String";
	// MD5效检码
	private String verifyCode = "32,String";
	
	private int headLength = 0;
	
	public String getMsgLen() {
		return msgLen;
	}
	public void setMsgLen(String msgLen) {
		this.msgLen = msgLen;
	}
	public String getMsgID() {
		return msgID;
	}
	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getZipType() {
		return zipType;
	}
	public void setZipType(String zipType) {
		this.zipType = zipType;
	}
	public String getSenderOrgCode() {
		return senderOrgCode;
	}
	public void setSenderOrgCode(String senderOrgCode) {
		this.senderOrgCode = senderOrgCode;
	}
	public String getRecvOrgCode() {
		return recvOrgCode;
	}
	public void setRecvOrgCode(String recvOrgCode) {
		this.recvOrgCode = recvOrgCode;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public int getHeadLength() {
		return headLength;
	}
	public void setHeadLength(int headLength) {
		this.headLength = headLength;
	}
	@Override
	public String toString() {
		return "MessageHead [headLength=" + headLength + ", msgID=" + msgID
				+ ", msgLen=" + msgLen + ", msgType=" + msgType
				+ ", recvOrgCode=" + recvOrgCode + ", sendTime=" + sendTime
				+ ", senderOrgCode=" + senderOrgCode + ", verifyCode="
				+ verifyCode + ", zipType=" + zipType + "]";
	}
	
	
	
}
