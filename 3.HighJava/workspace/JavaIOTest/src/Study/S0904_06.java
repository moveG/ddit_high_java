package Study;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 파일 출력 예제
 */
public class S0904_06 {
	/*
	 * 파일에 출력하기
	 */
	public static void main(String[] args) {
		FileOutputStream fos = null;
		
		try {
			//출력용 OutputStream객체 생성
//			fos = new FileOutputStream("d:/D_Other/out.txt",);
			fos = new FileOutputStream("d:/D_Other/out.txt", true);
			//boolean값 true를 붙이면 파일을 덮어쓰는게 아니라, 기존파일에 새로운 내용을 추가한다.
			
			for(char ch = 'a'; ch <= 'z'; ch++) {
				fos.write(ch);
			}
			System.out.println("파일에 쓰기 작업 완료...");
			
			//쓰기작업 완료 후 스트림 닫기
			fos.close();
			System.out.println("=================================");
			
			//저장된 파일의 내용을 읽어와 화면에 출력하기
			FileInputStream fis = new FileInputStream("d:/D_Other/out.txt");
			
			int c;
			
			while((c = fis.read()) != -1) {
				System.out.print((char)c);
			}
			System.out.println();
			System.out.println("출력 끝...");
			
			//읽기작업 완료 후 스트림 닫기
			fis.close();
			System.out.println("=================================");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
