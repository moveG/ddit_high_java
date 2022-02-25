package Study;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class S0902_03 {
	
	public static void main(String[] args) throws IOException {
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		
		//arraycopy를 이용한 배열 복사
		outSrc = new byte[inSrc.length];	//배열은 생성순간 크기가 정해지기 때문에 공간을 미리 확보해야함
		System.arraycopy(inSrc, 0, outSrc, 0, inSrc.length);
		//inSrc 배열의 첫번째(0)부터 복사해서 outSrc 배열의 첫번째부터 inSrc.length 길이만큼 넣어줌
		
		System.out.println("arraycopy를 이용한  outSrc : " + Arrays.toString(outSrc));
		
		//스트림 선언 및 객체 생성
		ByteArrayInputStream bais = null;	//스트림 객체 선언
		bais = new ByteArrayInputStream(inSrc);	//객체 생성
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int data;	//읽어온 자료를 저장할 변수
		
		//read()메서드 : byte단위로 자료를 읽어와 int타입으로 반환한다.
//      더이상 읽어올 자료가 없으면 -1을 반환한다.
		while((data = bais.read()) != -1) {
			baos.write(data);
		}
		
		//출력된 스트림값들을 배열로 변환해서 반환하는 메서드
		outSrc = baos.toByteArray();
		
		System.out.println("inSrc : " + Arrays.toString(inSrc));
		System.out.println("outSrc : " + Arrays.toString(outSrc));
		
		//스트림 객체 닫기
		bais.close();
		baos.close();
	}
}
