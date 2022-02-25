package Study;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class S0902_00 {
	
	public static void main(String[] args) throws IOException {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream("d:/EE/Tulips.jpg");
			fos = new FileOutputStream("d:/EE/복사본_Tulips.jpg");
			
			int c;
			
			while((c = fis.read()) != -1) {
				fos.write(c);
			}
			System.out.println("파일 복사 완료...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
