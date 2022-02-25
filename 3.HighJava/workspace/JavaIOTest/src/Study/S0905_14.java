package Study;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 프린트 기능 제공 보조 스트림 예제
 */
public class S0905_14 {
	
	public static void main(String[] args) throws IOException {
		FileOutputStream fos1 = new FileOutputStream("d:/EE/print1.txt");
		FileOutputStream fos2 = new FileOutputStream("d:/EE/print2.txt");
		
		//PrintStream은 OutputStream의 서브클래스로, 모든 자료형을 출력할 수 있는 기능을 제공한다.
		PrintStream out = new PrintStream(fos1);
		
		out.print("안녕하세요. PrintStream입니다1.\n");
		out.println("안녕하세요. PrintStream입니다2.");
		out.println("안녕하세요. PrintStream입니다3.");
		out.println(out);	//객체 출력
		out.println(3.14);
		out.println(true);
		
		out.close();
		
		System.out.println("Print 완료...");
		//PrintWriter가 PrintStream보다 다양한 언어의 문자를 처리하는데 적합하다.
		//둘 다 기본적으로 autoflush 기능이 비활성화되어있다.
		//향상된 기능의 PrintWriter가 추가되었지만, PrintStream은 계속 사용되고 있다.
		System.out.println("============================");
		
//		PrintWriter pw = new PrintWriter(fos2);
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos2, "utf-8"));
		
		pw.print("안녕하세요. PrintWriter입니다1.\n");
		pw.println("안녕하세요. PrintWriter입니다2.");
		pw.println("안녕하세요. PrintWriter입니다3.");
		pw.println(out);	//객체 출력
		pw.println(3.14);
		pw.println(true);
		
		pw.close();
		
		System.out.println("Print 완료...");
	}
}
