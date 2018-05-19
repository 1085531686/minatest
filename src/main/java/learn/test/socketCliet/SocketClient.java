package learn.test.socketCliet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import learn.test.util.BytesBuilder;
import learn.test.util.TypeTrans;

public class SocketClient {

	private Socket client;
	
	private int port;
	
	private String host;
	
	// socket读取流
	private BufferedInputStream cin ; // socket读取流
	// socket发送流
	private BufferedOutputStream cout;
	
	public SocketClient(String ip, int port) {
		this.host = ip;
		this.port = port;
	}
	private void startConnect()  {
		try {

			InetAddress addr = InetAddress.getByName(host); // server端ip地址
			// 连接服务器端
			client = new Socket();

			client.connect(new InetSocketAddress(addr, port), 10000);

			client.setSoTimeout(10000); // 超时时间
			client.setTcpNoDelay(true); // 立即发送
			client.setSoLinger(false, 0); // 关闭连接立即返回

			if (client.isClosed() == false
					&& client.isConnected() == true){
				// 已建立连接
			}else{
				
			}
			// 获取流对象
			cin = new BufferedInputStream(client.getInputStream());
			cout = new BufferedOutputStream(client.getOutputStream());
		} catch (IOException e) {
			
		}
	}
	
	public void sendBuffer(byte[] bufferString) throws IOException {
		cout.write(bufferString);
		cout.flush();
	}
	
	public byte[] sendMsgQuery(byte[] sendMsg) throws Exception {	
		byte[] responseMsg = null;
		// 建立连接
		try {
			this.startConnect();
			// 发送消息
			this.sendBuffer(sendMsg);
			// 接收消息
			byte[] btys= recieveBuffer();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			this.shutdownConnection();
		}
		return responseMsg;
	}
	
	private void shutdownConnection() {
		try {
			if (cout != null)
				cout.close(); // 关闭发送流
		} catch (IOException e) {
		}
		try {
			if (cin != null)
				cin.close(); // 关闭接收流
		} catch (IOException e) {
		}
		try {
			if (client != null)
				client.close(); // 关闭Socket连接
		} catch (IOException e) {
		}
	}
	
	public byte[] recieveBuffer() throws Exception {
		// 报文长度4个字节
		byte[] lengthBytes = new byte[4];
		byte[] messageBytes;
		if (cin.read(lengthBytes, 0, 4) == 4) {
			// 报文长度
			int length = TypeTrans.byte2Int(lengthBytes);
			// 除去报文长度之外的buff
			byte[] buffer = new byte[length - 4];

			// ==============add==用=bis.read不能收发全部=====================//
			int nIdx = 0;
			int nTotalLen = buffer.length;
			int nReadLen = 0;
			while (nIdx < nTotalLen) {
				nReadLen = cin.read(buffer, nIdx, nTotalLen - nIdx);
				if (nReadLen > 0) {
					nIdx = nIdx + nReadLen;
				} else {
					break;
				}
			}
			BytesBuilder bytesBuilder = new BytesBuilder();
			bytesBuilder.append(lengthBytes);
			bytesBuilder.append(buffer);
			// 将报文长度4个字节+报文长度之外的组成本次接收的bytes
			messageBytes = bytesBuilder.toBytes();
			// 将报文长度4个字节+报文长度之外的组成本次接收的bytes
			//messageBytes = Utility.concat(lengthBytes, buffer);
			return messageBytes;
		} else {
			throw new Exception("【readXmlBytesFromSocket.calss】" + "【获取数据长度失败】");
		}
	}

}
