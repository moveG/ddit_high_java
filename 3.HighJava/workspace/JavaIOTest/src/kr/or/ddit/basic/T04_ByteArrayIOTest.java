package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04_ByteArrayIOTest {
	
	public static void main(String[] args) {
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4];	//자료를 읽을 때 사용할 배열
		
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			//available()메서드 : 읽어올 수 있는 byte의 숫자를 반환
			while(bais.available() > 0) {
				/*
				bais.read(temp);
				//temp배열의 크기만큼 자료를 읽어와 temp배열에 저장함
				//temp라는 byte배열을 파라미터로 넣어줌, temp배열의 사이즈만큼 자료를 읽어와서 temp배열에 저장함
				
				baos.write(temp);
				//temp배열의 내용을 출력한다.
				*/
				
				int len = bais.read(temp);
				//버퍼로 읽어들인 byte의 숫자를 반환한다.
				System.out.println("버퍼로 읽어들인 byte의 숫자 : " + len);
				
				baos.write(temp, 0, len);
				//temp배열의 내용 중에서 0번째부터 len의 개수만큼 출력한다.
				
				System.out.println("temp : " + Arrays.toString(temp));
			}
			System.out.println();
			
			outSrc = baos.toByteArray();
			
			System.out.println("inSrc : " + Arrays.toString(inSrc));
			System.out.println("outSrc : " + Arrays.toString(outSrc));
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bais.close();
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
