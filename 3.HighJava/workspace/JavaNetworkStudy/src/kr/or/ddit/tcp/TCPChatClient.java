package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TCPChatClient {
	
	public static void main(String[] args) {
		new TCPChatClient().clientStart();
	}

	private void clientStart() {
		Socket socket = null;
		
		try {
			socket = new Socket("192.168.43.144", 7777);
			
			System.out.println("서버에 연결되었습니다...");
			System.out.println(socket);
			
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
					   + socket.getLocalPort() + "]";
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
