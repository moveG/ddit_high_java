package kr.or.ddit.udp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPFileReceiver {
	
	public static void main(String[] args) throws IOException {
		int port = 7777;
		
		long fileSize = 0;
		long totalReadBytes = 0;
		
		byte[] buffer = new byte[1000];
		int readBytes = 0;
		
		System.out.println("파일수신 대기중...");
		
		DatagramSocket ds = new DatagramSocket(port);
		FileOutputStream fos = null;
		DatagramPacket dp = 
				new DatagramPacket(buffer, buffer.length);
		ds.receive(dp);	//start받기
		
		String str = new String(dp.getData()).trim();
		
		if(str.equals("start")) {
			//전송파일 이름 받기
			buffer = new byte[1000];	//초기화
			dp = new DatagramPacket(buffer, buffer.length);
			ds.receive(dp);
			
			str = new String(dp.getData()).trim();
			fos = new FileOutputStream("d:/EE/" + str);
			
			//전송파일 크기 받기
			buffer = new byte[1000];	//초기화
			dp = new DatagramPacket(buffer, buffer.length);
			ds.receive(dp);
			
			str = new String(dp.getData()).trim();
			fileSize = Long.parseLong(str);
			
			long startTime = System.currentTimeMillis();
			
			while(true) {
				ds.receive(dp);
				readBytes = dp.getLength();
				fos.write(dp.getData(), 0, readBytes);
				totalReadBytes += readBytes;
				System.out.println();
				
				System.out.println("진행상태 : " + totalReadBytes + "/" + fileSize
						+ " Bytes (" + (totalReadBytes * 100 / fileSize) + "%)");
				
				if(totalReadBytes >= fileSize) {
					break;
				}
			}
			
			long endTime = System.currentTimeMillis();
			long diffTime = endTime - startTime;
			double transferSpeed = fileSize / diffTime;
			
			System.out.println("걸린시간 : " + diffTime + " (ms)");
			System.out.println("평균 수신속도 : " + transferSpeed + " Bytes/ms");
			System.out.println("수신 완료...");
			
			fos.close();
			ds.close();
		}	
	}
}
