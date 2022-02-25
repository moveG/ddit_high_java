package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 기본 타입 입출력 보조 스트림 예제
 * @author PC-15
 *
 */
public class T13_DataIOStreamTest {
	
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("d:/D_Other/test.dat");		
		DataOutputStream dos = new DataOutputStream(fos);
		//DataOutputStream은 출력용 데이터를 자료형에 맞게 출력해준다.
		
		dos.writeUTF("홍길동");	//문자열(UTF-8) 데이터 출력
		dos.writeInt(17);		//정수형(Int)으로 데이터 출력
		dos.writeFloat(3.14f);	//실수형(Float)으로 데이터 출력
		dos.writeDouble(3.14);	//실수형(Double)으로 데이터 출력
		dos.writeBoolean(true);	//논리형(Boolean)으로 데이터 출력
		
		System.out.println("출력완료");		
		dos.close();
		
		System.out.println("=============================================");
		
		//출력한 자료 읽어오기
		FileInputStream fis = new FileInputStream("d:/D_Other/test.dat");
		DataInputStream dis = new DataInputStream(fis);
		
		System.out.println("문자열 자료 : " + dis.readUTF());
		System.out.println("정수형(Int) 자료 : " + dis.readInt());
		System.out.println("실수형(Float) 자료 : " + dis.readFloat());
		System.out.println("실수형(Double) 자료 : " + dis.readDouble());
		System.out.println("논리형(Boolean) 자료 : " + dis.readBoolean());
		//연속적인 byte배열이므로 순서가 중요하다.
		dis.close();
	}
}
