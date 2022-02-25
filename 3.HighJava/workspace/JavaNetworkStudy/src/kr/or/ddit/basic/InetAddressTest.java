package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
	/*
	 * - InetAddress클래스 : IP주소를 다루기 위한 클래스
	 * 
	 * - getByName()메서드는 www.naver.com 또는 SEM-PC 등과 같은 머신이름이나 IP주소를
	 *   파라미터 값을 이용하여 유효한 InetAddress객체로 제공한다.
	 */
	public static void main(String[] args) throws UnknownHostException {
		//다음의 IP정보 가져오기
		InetAddress daumIp = InetAddress.getByName("www.daum.net");
		System.out.println("Host Name : " + daumIp.getHostName());
		System.out.println("Host Address : " + daumIp.getHostAddress());
		System.out.println();
		
		//자기 컴퓨터의 IP정보 가져오기
//		InetAddress localIp = InetAddress.getByName("192.168.43.144");
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("내 컴퓨터의 Host Name : " + localIp.getHostName());
		System.out.println("내 컴퓨터의 Host Address : " + localIp.getHostAddress());
		System.out.println();
		
		//IP주소가 여러개인 호스트의 정보 가져오기
		InetAddress[] daumIps = InetAddress.getAllByName("www.daum.net");
		for(InetAddress dIp : daumIps) {
			System.out.println(dIp.toString());
		}
	}
}
