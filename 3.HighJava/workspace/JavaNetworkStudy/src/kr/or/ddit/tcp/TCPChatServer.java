package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPChatServer {
	/*
	 * 채팅 프로그램 예제
	 * 
	 * - 서버 소켓을 만들고 클라이언트가 접속하면 소켓을 만들어
	 *   데이터를 받는 클래스와 데이터를 보내는 스레드 클래스에 이 소켓을 넘겨준다.
	 */
	public static void main(String[] args) {
		new TCPChatServer().serverStart();
	}
	
	public void serverStart() {
		//TCP소켓 통신을 위해 ServerSocket객체 생성
		ServerSocket server = null;
		Socket socket = null;
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버 준비 완료...");
			socket = server.accept();
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket); 
			
			sender.start();
			receiver.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class Receiver extends Thread {
		private DataInputStream dis;
		
		public Receiver(Socket socket) {
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(dis != null) {
				try {
					System.out.println(dis.readUTF());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class Sender extends Thread {
		private Scanner sc;
		private String name;
		private DataOutputStream dos;
		
		public Sender(Socket socket) {
			name = "[" + socket.getInetAddress() + ":"
					   + socket.getPort() + "]";
			sc = new Scanner(System.in);
			
			try {
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(dos != null) {
				try {
					dos.writeUTF(name + " >>> " + sc.nextLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}	
