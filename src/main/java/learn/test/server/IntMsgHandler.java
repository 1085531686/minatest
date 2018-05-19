package learn.test.server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import learn.test.dao.DaoContext;
import learn.test.dao.dto.Enwaste;
import learn.test.dao.dto.Exwaste;
import learn.test.dao.mapper.EnwasteMapper;
import learn.test.dao.mapper.ExwasteMapper;
import learn.test.message.CreateResponseMsg;
import learn.test.message.MassageParser;
import learn.test.message.Message;
import learn.test.message.MessageBodyConfig;
import learn.test.message.MessageHead;
import learn.test.util.BytesBuilder;
import learn.test.util.FileUtil;
import learn.test.util.TypeTrans;

import org.apache.ibatis.session.SqlSession;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketSessionConfig;

public class IntMsgHandler extends IoHandlerAdapter{
	/**
	 * 当会话创建是调用该方法
	 */
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("[info]"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"服务端与客户端" + session.getRemoteAddress() + "创建链接...");
		SocketSessionConfig cfg = (SocketSessionConfig) session.getConfig();
		cfg.setKeepAlive(true);
		cfg.setSoLinger(0); // 主动关闭连接后 防止连接进人 TIME_WAIT 状态占用资源 
	}
	@Override
    public void sessionIdle( IoSession session, IdleStatus status ) throws Exception
    {
		int count = session.getIdleCount( status );
        System.out.println( "IDLE " + count);
        //空闲4次关闭连接
        if(count == 3){
        	session.closeNow();
        }
    }
	/**
	 * 当网络连接被打开时此方法被调用，这里可以对session设置一些参数或者添加一些IoFilter的实现，也可以对客户端做一些认证之类的工作
	 */
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("[info]"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"服务端与客户端" + session.getRemoteAddress() + "连接打开...");
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
	}
	
	
    @Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
    	
    	System.out.println("消息发送出去了");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		System.out.println("发生异常了");
		session.closeNow();
    	cause.printStackTrace();
	}

	@Override
    public void messageReceived( IoSession session, Object message ) throws Exception
    {
		IoBuffer messageIo = (IoBuffer) message;

		byte[] bytes = messageIo.array();
		
		Message mess = new Message();
		//放入报文体配置信息
		mess.setBody(new MessageBodyConfig());
		//放入报文头配置信息
		mess.setmHead(new MessageHead());
		//解析器
		MassageParser parse = new MassageParser();
		//解析信息
		parse.parseMessage(bytes, mess);
		//解析后报文头头信息
		System.out.println(mess.getmHead());
		//
		String imageFileName ="";
		//通过消息获取图片路径
		imageFileName =getImageFileName(mess);
	
		//构造返回信息
		BytesBuilder bytesBuilder = new BytesBuilder();
		bytesBuilder.append("AA".getBytes());
		byte[] value= FileUtil.getFile(imageFileName);
		bytesBuilder.append(TypeTrans.int2ByteBigEndian(value.length));
		bytesBuilder.append(value);
		byte[] ret = CreateResponseMsg.creatResponseMsg(mess.getmHead(), bytesBuilder.toBytes());
		IoBuffer buf = IoBuffer.allocate(ret.length);
		buf.put(ret);
		buf.flip();
		session.write(buf);
		
		
    }
	
	private String getImageFileName(Message mess ){
		//解析后报文体信息
		List<Object> values =mess.getBody().getValues();
		String imageFileName ="";
		if("01".equals(mess.getmHead().getMsgType())){
			Enwaste en =new Enwaste();
			en.setEntimeStr((String)values.get(0));
			en.setENSTATION((String)values.get(1));
			en.setENLANE((String)values.get(2));
			en.setVLP((String)values.get(3));
			EnwasteMapper enwasteMapper =DaoContext.getEnwasteMapper();
			en =enwasteMapper.selectByEnInfo(en);
			System.out.println(en);
			if(null !=en){
				if(null != en.getIMAGEFILENAME() && !"".equals(en.getIMAGEFILENAME())){
					imageFileName = en.getIMAGEFILENAME();
					imageFileName =imageFileName.replaceAll("\\\\", "/");
				}
			}
			
		}
      if("02".equals(mess.getmHead().getMsgType())){
			
			Exwaste ex =new Exwaste();
			ex.setExtimeStr((String)values.get(0));
			ex.setEXSTATION((String)values.get(1));
			ex.setEXLANE((String)values.get(2));
			ex.setVLP((String)values.get(3));
			ExwasteMapper exwasteMapper =DaoContext.getExwasteMapper();
			ex =exwasteMapper.selectByExInfo(ex);
			System.out.println(ex);
			if(null !=ex){
				if(null != ex.getIMAGEFILENAME() && !"".equals(ex.getIMAGEFILENAME())){
					imageFileName = ex.getIMAGEFILENAME();
					imageFileName =imageFileName.replaceAll("\\\\", "/");
				}
			}
		}
		return imageFileName;
	}

}
