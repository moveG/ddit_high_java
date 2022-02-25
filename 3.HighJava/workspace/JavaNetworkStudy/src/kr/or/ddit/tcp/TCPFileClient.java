package kr.or.ddit.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 파일전송 클라이언트
 * 
 * - 클라이언트는 서버에 접속하여 서버가 보내주는 파일을 d:/AA폴더에 저장한다.
 */
public class TCPFileClient {
	private Socket socket;
	private InputStream is;
	private FileOutputStream fos;
	
	public static void main(String[] args) {
		new TCPFileClient().clientStart();
	}

	private void clientStart() {
		String path = "d:/AA/";
		String fileName = "네모바지.png";
		File file = new File(path);
		
		if(!file.exists()) {
			file.mkdirs();
			System.out.println("폴더 생성 성공!");
		}
		
		file = new File(path + fileName);
		
		try {
			socket = new Socket("192.168.43.144", 7777);
			System.out.println("파일 다운로드 시작...");
			
			fos = new FileOutputStream(file);
			
			BufferedInputStream bis = 
					new BufferedInputStream(socket.getInputStream());
			BufferedOutputStream bos = 
					new BufferedOutputStream(fos);
			
			int c = 0;
			while((c = bis.read()) != -1) {
				bos.write(c);
			}
			
			bis.close();
			bos.close();
			
			System.out.println("파일 다운로드 완료...");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(is != null) {
				try {is.close();} catch (IOException e) {}
			}
			if(fos != null) {
				try {fos.close();} catch (IOException e) {}
			}
			if(socket != null) {
				try {socket.close();} catch (IOException e) {}
			}
		}
	}
}
