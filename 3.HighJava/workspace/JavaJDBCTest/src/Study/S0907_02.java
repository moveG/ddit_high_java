package Study;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 외부의 properties파일을 읽어와 Properties객체로 처리하기
 *
 */
public class S0907_02 {
	/*
	 * 소스폴더 내부의 파일들은 컴파일이 수행된다.
	 * 일반폴더 내부의 파일들은 컴파일이 수행되지 않는다.
	 */
	public static void main(String[] args) {
		//읽어온 정보를 저장할 Properties객체 생성
		Properties prop = new Properties();
		
		//읽어온 파일명을 이용한 File객체 생성
		File file = new File("res/db.properties");
		
		try {
			//파일 읽기를 수행할 FileInputStream객체 생성
			FileInputStream fis = new FileInputStream(file);
			
			//Properties객체로 파일 내용 읽기
			prop.load(fis);
			//파일의 내용을 읽어와서 key와 value값으로 분류해서 Properties객체에 담는다.
			
			//읽어온 자료 출력하기
			//key값만 읽어와서 Enumeration객체로 변환하기
			Enumeration<String> keys = (Enumeration<String>) prop.propertyNames();
			//key의 개수만큼 반복해서 값 출력하기
			while(keys.hasMoreElements()) {
				String k = keys.nextElement();
				String v = prop.getProperty(k);
				System.out.println(k + " : " + v);
			}
			System.out.println("출력작업 완료...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
