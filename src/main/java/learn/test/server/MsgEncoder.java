package learn.test.server;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class MsgEncoder extends ProtocolEncoderAdapter {

	@Override
	public void encode(IoSession arg0, Object message, ProtocolEncoderOutput out)
			throws Exception {
		// TODO Auto-generated method stub
		
		out.write(message);
	}

	
}
