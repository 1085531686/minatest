package learn.test.serverTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;


import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaTimeServer {

	 public static void main( String[] args ) throws IOException
	    {
	        IoAcceptor acceptor = new NioSocketAcceptor();
	        acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );
	        acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory( Charset.forName( "UTF-8" ))));
	        
	        acceptor.getFilterChain().addLast("workThread", new ExecutorFilter(Executors.newFixedThreadPool(20)));
	        acceptor.setHandler(  new TimeServerHandler() );

	        acceptor.getSessionConfig().setReadBufferSize( 2048 );
	        acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 10 );
	        
	        acceptor.bind( new InetSocketAddress(1010) );
	    }
}
