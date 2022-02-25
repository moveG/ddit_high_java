package Study;

public class S0830_01 {
	
	public static void main(String[] args) {
		/*
		 * 싱글 스레드 프로그램
		 */
		for(int i = 0; i <= 200; i++) {
			System.out.print("*");
		}
		System.out.println();
		for(int i = 0; i <= 200; i++) {
			System.out.print("@");
		}
		//위에서부터 차례대로 코드를 실행함
	}
}
