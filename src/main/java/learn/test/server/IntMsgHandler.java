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
	 * ���Ự�����ǵ��ø÷���
	 */
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("[info]"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"�������ͻ���" + session.getRemoteAddress() + "��������...");
		SocketSessionConfig cfg = (SocketSessionConfig) session.getConfig();
		cfg.setKeepAlive(true);
		cfg.setSoLinger(0); // �����ر����Ӻ� ��ֹ���ӽ��� TIME_WAIT ״̬ռ����Դ 
	}
	@Override
    public void sessionIdle( IoSession session, IdleStatus status ) throws Exception
    {
		int count = session.getIdleCount( status );
        System.out.println( "IDLE " + count);
        //����4�ιر�����
        if(count == 3){
        	session.closeNow();
        }
    }
	/**
	 * ���������ӱ���ʱ�˷��������ã�������Զ�session����һЩ�����������һЩIoFilter��ʵ�֣�Ҳ���ԶԿͻ�����һЩ��֤֮��Ĺ���
	 */
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("[info]"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"�������ͻ���" + session.getRemoteAddress() + "���Ӵ�...");
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
	}
	
	
    @Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
    	
    	System.out.println("��Ϣ���ͳ�ȥ��");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		System.out.println("�����쳣��");
		session.closeNow();
    	cause.printStackTrace();
	}

	@Override
    public void messageReceived( IoSession session, Object message ) throws Exception
    {
		IoBuffer messageIo = (IoBuffer) message;

		byte[] bytes = messageIo.array();
		
		Message mess = new Message();
		//���뱨����������Ϣ
		mess.setBody(new MessageBodyConfig());
		//���뱨��ͷ������Ϣ
		mess.setmHead(new MessageHead());
		//������
		MassageParser parse = new MassageParser();
		//������Ϣ
		parse.parseMessage(bytes, mess);
		//��������ͷͷ��Ϣ
		System.out.println(mess.getmHead());
		//
		String imageFileName ="";
		//ͨ����Ϣ��ȡͼƬ·��
		imageFileName =getImageFileName(mess);
	
		//���췵����Ϣ
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
		//������������Ϣ
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
