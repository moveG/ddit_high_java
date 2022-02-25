package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 나에게 패킷을 날린 클라이언트의 IP주소와 Port를 얻어서
 * 서버의 현재시간을 클라이언트에게 전송해주는 예제
 */
public class UDPServer {
	private DatagramSocket socket;
	
	public static void main(String[] args) throws IOException {
		new UDPServer().start();
	}

	private void start() throws IOException {
		//포트 7777번을 사용하여 수신하는 소캣 생성
		socket = new DatagramSocket(7777);
		
		//패킷 송수신을 위한 객체변수 선언
		DatagramPacket inPacket, outPacket;
		
		byte[] inMsg = new byte[1];	//패킷수신을 위한 바이트 배열
		byte[] outMsg;				//패킷송신을 위한 바이트 배열
		
		while(true) {
			//데이터를 수신하기 위한 패킷을 생성한다.
			inPacket = new DatagramPacket(inMsg, inMsg.length);
			System.out.println("패킷 수신 대기중...");
			
			//패킷을 통해 데이터를 수신(receive)한다.
			socket.receive(inPacket);
			System.out.println("패킷 수신 완료...");
			
			//수신한 패킷으로부터 client의 IP주소와 Port를 얻는다.
			InetAddress address = inPacket.getAddress();
			int port = inPacket.getPort();
			
			//서버의 현재시간을 시분초 형태([hh:mm:ss])로 변환한다.
			SimpleDateFormat sdf = 
					new SimpleDateFormat("[hh:mm:ss]");
			String time = sdf.format(new Date());
			outMsg = time.getBytes();	//시간문자열을 바이트배열로 변환
			
			//패킷을 생성하여 client에게 전송(send)한다.
			outPacket = 
				new DatagramPacket(outMsg, outMsg.length, address, port);
			socket.send(outPacket);	//전송시작
			System.out.println("전송완료...");
		}
	}
}
