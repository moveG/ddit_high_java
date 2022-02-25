package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class URLConnectionTest {
	/*
	 * URLConnection : 어플리케이션과 URL간의 통신 연결을 위한 추상클래스
	 */
	public static void main(String[] args) throws IOException {
		//특정 서버(ex: danawa)의 정보와 파일 내용을 출력하는 예제
		URL url = new URL("http://www.danawa.com/");
		
		//Header정보 가져오기
		//URLConnection객체 구하기
		URLConnection uc = url.openConnection();
		
		System.out.println("Connection-Type : " + uc.getContentType());
		System.out.println("Encoding : " + uc.getContentEncoding());
		System.out.println("Content : " + uc.getContent());
		System.out.println();
		
		//전체 Header정보 출력하기
		Map<String, List<String>> hm = uc.getHeaderFields();
		Iterator<String> it = hm.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			System.out.println(key + " : " + hm.get(key));
		}
		System.out.println("===========================================");
		
		//해당 Host의 페이지내용 읽어오기
		//파일을 읽어오기 위한 스트림 객체 생성하기
		
		//방법1 : URLConnection의 getInputStream() 이용
		InputStream is1 = url.openConnection().getInputStream(); 
		
		//방법2 : URL객체의 openStream() 이용
		InputStream is2 = url.openStream();
		
		
		InputStreamReader isr = new InputStreamReader(is2, "utf-8");
		BufferedReader br = new BufferedReader(isr);
		
		//내용 출력하기
		String str = "";
		
		while((str = br.readLine()) != null) {
			System.out.println(str);
		}
		br.close();
	}
}
