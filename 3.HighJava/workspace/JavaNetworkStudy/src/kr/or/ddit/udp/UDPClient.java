package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 서버에 패킷을 날리고
 * 서버에서 전송한 서버시간을 받는예제
 */
public class UDPClient {

	public static void main(String[] args) throws IOException {
		new UDPClient().start();
	}

	private void start() throws IOException {
		DatagramSocket socket = new DatagramSocket();
		InetAddress serverAddress = 
				InetAddress.getByName("192.168.43.144");
		
		//데이터가 저장될 공간으로 byte배열을 생성한다.(패킷 수신용)
		byte[] msg = new byte[100];
		
		DatagramPacket outPacket = 
				new DatagramPacket(msg, 50, serverAddress, 7777);
		DatagramPacket inPacket =
				new DatagramPacket(msg, msg.length);
		
		socket.send(outPacket);		//전송
		socket.receive(inPacket);	//수신
		
		System.out.println("현재 서버시간 : "
				+ new String(inPacket.getData()));
		
		socket.close();	//소켓 종료
	}
}
