package learn.test.message;

public class Message {
    MessageHead mHead;
	
	byte [] message;

	MessageBodyConfig body;
	
	public MessageHead getmHead() {
		return mHead;
	}
	
	  

	public void setmHead(MessageHead mHead) {
		this.mHead = mHead;
	}

	public byte[] getMessage() {
		return message;
	}

	public void setMessage(byte[] message) {
		this.message = message;
	}



	public MessageBodyConfig getBody() {
		return body;
	}



	public void setBody(MessageBodyConfig body) {
		this.body = body;
	}
	
	
}
