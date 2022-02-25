package Study;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 성능향상을 위한 보조 스트림 예제
 * (바이트 기반의 Buffered스트림 사용 예제)
 */
public class S0905_11 {
	
	public static void main(String[] args) throws IOException {
		//입출력 성능향상을 위해 버퍼를 사용하는 보조스트림
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		
		fos = new FileOutputStream("d:/EE/bufferTest.txt");
		
		//버퍼의 크기를 지정하지 않으면 기본적으로 버퍼크기가 8192byte(8kb)로 설정된다.
		//버퍼의 크기가 5인 스트림 객체 생성
		bos = new BufferedOutputStream(fos, 5);
		
		for(int i = '1'; i <= '9'; i++) {	//숫자 자체를 문자로 지정
			bos.write(i);
		}
		
		bos.flush();
		//작업을 종료하기 전에 버퍼에 남아있는 데이터를 모두 출력시킨다.
		//(close시 자동으로 호출됨)
		//확실하게 하기 위해 직접 기술해주는 것이 좋다.
		
		//버퍼는 기본적으로 지정된 크기가 가득 차야 데이터를 출력시킨다.
		//그러므로 지정된 크기 미만의 자료라면 flush()메서드를 통해 버퍼의 자료를 강제로 출력시킨다.
		
		bos.close();
		//fos.close();
		//보조스트림만 닫아도 기본스트림은 자동으로 닫힌다.
		
		System.out.println("작업 끝...");
	}
}
