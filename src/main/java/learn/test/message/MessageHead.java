package learn.test.message;

public class MessageHead {

	// ���ĳ���
	private String msgLen = "4,integer";
	// ���ı���
	private String msgID = "2,String";
	// ��������
	private String msgType = "2,String";
	// ѹ������
	private String zipType = "1,String";
	// ���ͻ������뼰���ͻ������ͣ�ǰ3������������λ�ǻ������ͣ�
	private String senderOrgCode = "8,String";
	// ���ջ������뼰���ջ������ͣ�ǰ3������������λ�ǻ������ͣ�
	private String recvOrgCode = "8,String";
	// ����ʱ��
	private String sendTime="14,String";
	// MD5Ч����
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
