package kr.or.ddit.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 파일전송 서버
 * 
 * - 서버는 클라이언트가 접속하면 서버 컴퓨터의 d:/D_Other폴더에 있는
 *   Tulips.jpg파일을 클라이언트로 전송한다.
 */
public class TCPFileServer {
	private ServerSocket server;
	private Socket socket;
	private OutputStream os;
	private FileInputStream fis;
	private File file = new File("d:/D_Other/spongebob.png");
	
	public void serverStart() {
		while(true) {
			try {
				server = new ServerSocket(7777);
				System.out.println("서버준비 완료...");
				
				socket = server.accept();
				System.out.println("파일전송 시작...");
				fis = new FileInputStream(file);
				os = socket.getOutputStream();
				
				BufferedInputStream bis = new BufferedInputStream(fis);
				BufferedOutputStream bos = new BufferedOutputStream(os);
				
				int c = 0;
				while((c = bis.read()) != -1) {
					bos.write(c);
				}
				
				bis.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(fis != null) {
					try {fis.close();} catch (IOException e) {}
				}
				if(os != null) {
					try {os.close();} catch (IOException e) {}
				}
				if(socket != null) {
					try {socket.close();} catch (IOException e) {}
				}
				if(server != null) {
					try {server.close();} catch (IOException e) {}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new TCPFileServer().serverStart();
	}
}
