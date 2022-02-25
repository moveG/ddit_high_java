package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileWriter(문자기반 스트림) 테스트
 */
public class T07_FileWriterTest {
	/*
	 * 사용자가 입력한 내용을 그대로 파일로 저장하기
	 */
	public static void main(String[] args) {
		//콘솔(표준입력장치)과 연결된 입력용 문자기반 스트림 생성
		//InputStreamReader : 바이트기반 스트림을 문자기반 스트림으로 변환해주는 보조 스트림이다.
		//보조 스트림은 중간다리 역할을 함, 보완 및 보조 역할을 수행함
		//보조 스트림은 일반 스트림 없이는 의미가 없음
		InputStreamReader isr = new InputStreamReader(System.in);
		
		FileWriter fw = null;	//파일 출력용 문자기반 스트림
		
		try {
			//파일 출력용 문자기반 스트림 객체 생성
			fw = new FileWriter("d:/D_Other/testChar.txt");

			System.out.println("아무거나 입력하세요.");
			//콘솔에서 입력할 때 입력의 끝 표시는 Ctrl+Z 키를 누르면 된다.
			//마지막에 enter를 치고 Ctrl+Z를 눌러 종료시키자.
		
			int c;
		
			while((c = isr.read()) != -1) {
				fw.write(c);	//콘솔에서 입력받은 값을 파일에 출력하기
			}
			System.out.println("작업 끝...");
			
			//finally에 넣어주는것이 더 좋은 방법임
			isr.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
