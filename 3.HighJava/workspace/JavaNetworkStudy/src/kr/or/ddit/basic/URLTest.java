package kr.or.ddit.basic;

import java.net.URL;

public class URLTest {
	/*
	 * - URL(Uniform Resource Locator) 클래스 : 인터넷에 존재하는 서버들의 자원에 접근할 수 있는
	 *   주소를 관리하는 클래스
	 */
	public static void main(String[] args) throws Exception {
		URL url = new URL("http", "ddit.or.kr", 80, 
				"main/index.html?ttt=123&name=홍길동#kkk");
		
		System.out.println("전체 URL주소 : " + url.toString());
		System.out.println("Protocol : " + url.getProtocol());
		System.out.println("Host : " + url.getHost());
		System.out.println("Query : " + url.getQuery());
		System.out.println("File : " + url.getFile());
		System.out.println("Path : " + url.getPath());
		System.out.println("Port : " + url.getPort());
		System.out.println("Ref : " + url.getRef());
		System.out.println();
		
		System.out.println(url.toExternalForm());
		System.out.println(url.toString());
		System.out.println(url.toURI().toString());
	}
}
