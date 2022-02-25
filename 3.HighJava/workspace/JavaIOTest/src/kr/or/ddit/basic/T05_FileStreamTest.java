package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 파일 읽기 예제
 */
public class T05_FileStreamTest {
	/*
	 * FileInputStream객체를 이용한 파일내용 읽기
	 */
	public static void main(String[] args) {
		FileInputStream fis = null;	//변수선언
		
		try {
//			fis = new FileInputStream("d:/D_Other/test2.txt");	//객체생성

			File file = new File("d:/D_Other/test2.txt");		//객체생성
			fis = new FileInputStream(file);	
			//자바 프로그램을 기준으로 읽고 출력하는 것을 나눔
			//test2.txt 파일이 존재하지 않기 때문에 오류가 발생하므로 test2.txt를 만든다음 실햄하면 제대로 출력됨
			//영문자(1byte)는 제대로 출력되지만, 한글(2byte)은 2byte이기 때문에 이상한 문자로 출력됨
			
			int c;	//읽어온 데이터를 저장할 변수
			
			while((c = fis.read()) != -1) {		//읽어온 값이 -1이면 파일의 끝까지 읽었다는 의미(byte기준으로 읽음, 1byte씩 읽음)
				System.out.print((char)c);	//읽어온 자료 출력하기	
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();	//작업 완료 후 스트림 닫기
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
