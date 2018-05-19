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
	
	// socket��ȡ��
	private BufferedInputStream cin ; // socket��ȡ��
	// socket������
	private BufferedOutputStream cout;
	
	public SocketClient(String ip, int port) {
		this.host = ip;
		this.port = port;
	}
	private void startConnect()  {
		try {

			InetAddress addr = InetAddress.getByName(host); // server��ip��ַ
			// ���ӷ�������
			client = new Socket();

			client.connect(new InetSocketAddress(addr, port), 10000);

			client.setSoTimeout(10000); // ��ʱʱ��
			client.setTcpNoDelay(true); // ��������
			client.setSoLinger(false, 0); // �ر�������������

			if (client.isClosed() == false
					&& client.isConnected() == true){
				// �ѽ�������
			}else{
				
			}
			// ��ȡ������
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
		// ��������
		try {
			this.startConnect();
			// ������Ϣ
			this.sendBuffer(sendMsg);
			// ������Ϣ
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
				cout.close(); // �رշ�����
		} catch (IOException e) {
		}
		try {
			if (cin != null)
				cin.close(); // �رս�����
		} catch (IOException e) {
		}
		try {
			if (client != null)
				client.close(); // �ر�Socket����
		} catch (IOException e) {
		}
	}
	
	public byte[] recieveBuffer() throws Exception {
		// ���ĳ���4���ֽ�
		byte[] lengthBytes = new byte[4];
		byte[] messageBytes;
		if (cin.read(lengthBytes, 0, 4) == 4) {
			// ���ĳ���
			int length = TypeTrans.byte2Int(lengthBytes);
			// ��ȥ���ĳ���֮���buff
			byte[] buffer = new byte[length - 4];

			// ==============add==��=bis.read�����շ�ȫ��=====================//
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
			// �����ĳ���4���ֽ�+���ĳ���֮�����ɱ��ν��յ�bytes
			messageBytes = bytesBuilder.toBytes();
			// �����ĳ���4���ֽ�+���ĳ���֮�����ɱ��ν��յ�bytes
			//messageBytes = Utility.concat(lengthBytes, buffer);
			return messageBytes;
		} else {
			throw new Exception("��readXmlBytesFromSocket.calss��" + "����ȡ���ݳ���ʧ�ܡ�");
		}
	}

}
