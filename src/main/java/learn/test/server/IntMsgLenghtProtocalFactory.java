package learn.test.server;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class IntMsgLenghtProtocalFactory implements ProtocolCodecFactory {

	private IntMsgLenghtProtocolDeccoder decoder ;
	
	private MsgEncoder encoder ;
	
	
	public IntMsgLenghtProtocalFactory() {
		decoder = new IntMsgLenghtProtocolDeccoder();
		encoder = new MsgEncoder();
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		return decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		return encoder;
	}

	
}
