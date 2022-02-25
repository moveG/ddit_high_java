package Study;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 성능향상을 위한 보조 스트림 예제
 * (문자 기반의 Buffered스트림 사용 예제)
 */
public class S0905_12 {

	public static void main(String[] args) throws IOException {
		//이클립스에서 만든 자바 프로그램이 실행되는 기본 위치는 해당 '프로그램 폴더'이다.
//		FileReader fr = new FileReader("src/study/S0905_11.java");
//		
//		int c;
//		
//		while((c = fr.read()) != -1) {
//			System.out.print((char)c);
//		}
//		fr.close();
		
		FileReader fr = new FileReader("src/study/S0905_11.java");
		
		BufferedReader br = new BufferedReader(fr);
		//한줄씩 읽을 수 있도록 해주는 readLine()메서드를 이용하기 위해 BufferedReader 사용
		
		String temp = "";
		
		for(int i = 1; (temp = br.readLine()) != null; i++) {
			System.out.printf("%4d : %s\n", i, temp);
		}
		br.close();
	}
}
