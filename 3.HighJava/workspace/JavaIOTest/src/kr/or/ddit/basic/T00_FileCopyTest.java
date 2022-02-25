package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 'd:/D_Other/'에 있는 'Tulips.jpg'파일을
 * '복사본_Tulips.jpg'로 복사하는 프로그램을 작성하시오.
 */
public class T00_FileCopyTest {
	
	public static void main(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream("d:/D_Other/Tulips.jpg");
			fos = new FileOutputStream("d:/D_Other/복사본_Tulips.jpg");
			
			int c;
			
			while((c = fis.read()) != -1) {
				fos.write(c);
			}
			
			System.out.println("파일 복사 완료...");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
