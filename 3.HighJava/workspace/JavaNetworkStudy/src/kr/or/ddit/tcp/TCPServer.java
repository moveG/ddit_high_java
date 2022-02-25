package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	
	public static void main(String[] args) throws IOException {
		//TCP소켓 통신을 하기위해 ServerSocket객체를 생성
		ServerSocket server = new ServerSocket(8888);
		System.out.println("서버가 접속을 기다립니다...");
		
		//accept()메서드는 Client에서 연결요청이 들어올 때까지 계속 기다린다.
		//연결요청이 들어오면 Socket객체를 생성해서 Client의 Socket과 연결한다.
		Socket socket = server.accept();
		
		//=======================================================
		//이 다음은 클라이언트와 연결된 후의 작업을 수행한다.
		System.out.println("접속한 클라이언트 정보");
		System.out.println("주소 : " + socket.getInetAddress());
		
		//Client에게 메시지 전송
		//OutputStream객체를 구성하여 전송
		//접속한 Socket의 getOutputStream()메서드를 이용하여 구함
		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		dos.writeUTF("어서오세요...");
		
		System.out.println("메시지를 보냈습니다.");
		
		dos.close();
		server.close();
		socket.close();
	}
}
