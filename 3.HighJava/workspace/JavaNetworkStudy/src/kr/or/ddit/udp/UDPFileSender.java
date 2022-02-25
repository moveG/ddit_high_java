package kr.or.ddit.udp;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPFileSender {
	
	public static void main(String[] args) {
		File file = new File("d:/D_Other/spongebob.png");
		DatagramSocket ds = null;
		
		if(!file.exists()) {
			System.out.println("파일이 존재하지 않습니다.");
			System.exit(0);	
		}
		
		long fileSize = file.length();
		long totalReadBytes = 0;
		
		long startTime = System.currentTimeMillis();
		int port = 7777;
		
		try {
			ds = new DatagramSocket();
			InetAddress receiveAddr = 
				InetAddress.getByName("192.168.43.144");
			
			String str = "start";	//전송시작을 알려주기 위한 문자열
			DatagramPacket dp = new DatagramPacket(str.getBytes(), 
					str.getBytes().length, receiveAddr, port);
			ds.send(dp);
			
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1000];
			
			str = file.getName();	//파일명
			dp = new DatagramPacket(str.getBytes(), 
					str.getBytes().length, receiveAddr, port);
			ds.send(dp);
			
			str = String.valueOf(fileSize);	//파일크기
			dp = new DatagramPacket(str.getBytes(), 
					str.getBytes().length, receiveAddr, port);
			ds.send(dp);
			
			while(true) {
				Thread.sleep(10);	//패킷전송 간의 간격을 주기 위해서...
				
				int readBytes = fis.read(buffer, 0, buffer.length);
				if(readBytes == -1) {
					break;
				}
				dp = new DatagramPacket(buffer,  readBytes, receiveAddr, port);
				ds.send(dp);
				totalReadBytes += readBytes;
				
				System.out.println();
				System.out.println("진행상태 : " + totalReadBytes + "/" + fileSize
						+ " Bytes (" + (totalReadBytes * 100 / fileSize) + "%)");
			}
			
			long endTime = System.currentTimeMillis();
			long diffTime = endTime - startTime;
			double transferSpeed = fileSize / diffTime;
			
			System.out.println("걸린시간 : " + diffTime + " (ms)");
			System.out.println("평균 전송속도 : " + transferSpeed + " Bytes/ms");
			System.out.println("전송 완료...");
			
			fis.close();
			ds.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
