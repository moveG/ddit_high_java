package Study;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문자 변환 보조스트림 예제
 */
public class S0904_09 {
	/*
	 * InputStreamReader는 파일의 인코딩 방식을 지정할 수 있다.
	 * - ex) new InputStreamReader(바이트기반스트림객체, 인코딩방식)
	 * 
	 * 인코딩 방식
	 * - 한글 인코딩 방식은 크게 UTF-8과 EUC-KR 방식으로 나뉜다.
	 * 
	 * - 원래 한글윈도우는 CP949 방식을 사용했는데, 윈도우를 개발한 MS에서 
	 *   EUC-KR 방식에서 확장하였기 때문에 MS949라고도 부른다.
	 * 
	 * - 한글 윈도우의 메모장에서 말하는 ANSI인코딩이란, CP949(Code Page 949)를 말한다.
	 *   CP949는 EUC-KR의 확장이며, 하위호환성이 있다.
	 * 
	 * - MS949 : 윈도우의 기본 한글 인코딩 방식(ANSI계열)
	 * - UTF-8 : 유니코드 UTF-8 인코딩 방식(영문자 및 숫자 : 1byte, 한글 : 3byte)
	 * - US-ASCII : 영문 전용 인코딩 방식
	 * 
	 * - ANSI는 영어를 표기하기 위해 만든 코드로 규격 자체에 한글이 없었다가
	 *   나중에 EUC-KR, CP949 같은 방식으로 한글이 포함되었다.
	 * 
	 * - 참고)
	 *   ASCII => extended ASCII(ISO 8859-1) => 조합형(초성, 중성, 종성), 완성형(KSC 5601)
	 *  ----------------------------------------------------------------------------
	 *         => 유닉스 계열 : EUC-KR(확장 유닉스 코드)
	 *         => 윈도우 계열 : CP949(확장 완성형) - 일부문자(8822자)를 추가함
	 *  ----------------------------------------------------------------------------
	 *         => ANSI 계열 => ECU-KR
	 *         => 유니코드(UTF-8)
	 */
	public static void main(String[] args) {
		//파일 인코딩을 이용하여 읽어오기
		FileInputStream fis = null;
		InputStreamReader isr = null;
		
		try {
			//fis = new FileInputStream("d:/EE/test_ansi.txt");
			fis = new FileInputStream("d:/EE/test_utf8.txt");
			
			//isr = new InputStreamReader(fis, "cp949");
			//isr = new InputStreamReader(fis, "ms949");
			//isr = new InputStreamReader(fis, "euc-kr");
			isr = new InputStreamReader(fis, "utf-8");
			//InputStreamReader : 바이트기반 스트림을 문자기반 스트림으로 변환해주는 보조 스트림
			//바이트기반 스트림은 한글이 깨지므로 문자기반 스트림이 필요함
			//인코딩 방식을 두번째 파라미터로 넣어 명시해 줄 수 있다.
			int c;
			
			while((c = isr.read()) != -1) {
				System.out.print((char)c);
			}
			System.out.println();
			System.out.println("출력 끝...");
			
			isr.close();
			//보조 스트림만 닫아도 된다.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
