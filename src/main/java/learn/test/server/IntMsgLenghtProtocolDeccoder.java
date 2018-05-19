package learn.test.server;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class IntMsgLenghtProtocolDeccoder extends CumulativeProtocolDecoder {

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		
		 if (in.remaining() >=4 ){
			 
			 System.out.println("���ĳ���"+ in.remaining());
			 int length = in.getInt(in.position());
			 if (length <= 4){
				 System.out.println("���ĳ���"+ length);
			 }
			 if (length >in.remaining()){
				 System.out.println( "������ ��" + length + "���յ���" +in.remaining());
				 return false;
			 }
			 
			 System.out.println( "������ ��" + length + "���յ���" +in.remaining());
		 
			 byte[] bytes = new byte[length];
			 in.get(bytes);
			 IoBuffer buf = IoBuffer.allocate(length);
			 buf.put(bytes);
			 buf.flip();
			 out.write(buf);
			 return true;
		 }
		 
		 return false;
		
	}

}
