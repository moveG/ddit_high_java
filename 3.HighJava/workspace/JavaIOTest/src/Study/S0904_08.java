package Study;

import java.io.FileReader;
import java.io.IOException;

/**
 * FileReader(문자기반 스트림) 테스트
 */
public class S0904_08 {
	/*
	 * 문자기반의 스트림을 이용한 파일내용 읽기
	 */
	public static void main(String[] args) throws IOException {
		FileReader fr = null;
		
		//문자단위의 입력을 담당하는 Reader형 객체 생성
		fr = new FileReader("d:/EE/testChar.txt");
		
		int c;
		
		while((c = fr.read()) != -1) {
			System.out.print((char)c);
		}
		fr.close();
	}
}
