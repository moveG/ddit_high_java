package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TCPClient {
	
	public static void main(String[] args) throws IOException {
		String serverIp = "192.168.43.144";
		//자기 자신의 호스트 정보를 나타내는 방법
		//IP : 127.0.0.1
		//컴퓨터 이름 : localhost
		
		System.out.println(serverIp + "서버에 접속 중입니다...");
		
		//소켓을 생성하여 서버에 연결을 요청한다.
		Socket socket = new Socket(serverIp, 8888);
		
		//연결되면 이후의 명령이 실행된다.
		System.out.println("연결되었습니다.");
		
		//서버에서 전송된 메시지 받기
		//메시지를 받기위해 InputStream객체를 생성
		//Socket의 getInputStream()메서드 이용
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);

		//서버로부터 받은 메시지 출력
		System.out.println("받은 메시지 : " + dis.readUTF());
		System.out.println("연결 종료...");
		
		dis.close();
		socket.close();
	}
}
