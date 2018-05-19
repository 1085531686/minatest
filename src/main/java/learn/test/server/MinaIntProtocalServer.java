package learn.test.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import learn.test.dao.DaoContext;
import learn.test.serverTest.TimeServerHandler;
import learn.test.util.DaemonThreadFactory;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaIntProtocalServer {

	 public static void main( String[] args ) throws IOException
	    {
		   //初始化数据库连接
		    DaoContext.init();
		    
	        IoAcceptor acceptor = new NioSocketAcceptor();
	        acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );
	        acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter(new IntMsgLenghtProtocalFactory()));
	        //创建handler 工作线程
	        ThreadPoolExecutor theadpool = new ThreadPoolExecutor(15,20,30,TimeUnit.MINUTES,new SynchronousQueue<Runnable>(),new DaemonThreadFactory(),new ThreadPoolExecutor.DiscardOldestPolicy());
	        acceptor.getFilterChain().addLast("workThread", new ExecutorFilter(theadpool));
	        acceptor.setHandler(  new IntMsgHandler() );
	        
	        acceptor.getSessionConfig().setReadBufferSize( 2048 );
	        //检查空闲时间 秒为单位
	        acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 30 );
	        
	        acceptor.bind( new InetSocketAddress(60000) );
	    }
}
