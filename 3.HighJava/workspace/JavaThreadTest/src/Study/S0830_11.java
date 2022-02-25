package Study;

public class S0830_11 {
	/*
	 * 3개(명)의 스레드가 각각 알파벳 대문자를 출력하는데
	 * 출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성하기
	 */
	static String strRank = "";
	
	public static void main(String[] args) {
		DisplayCharacter[] disChars = new DisplayCharacter[] {
				new DisplayCharacter("홍길동"),
				new DisplayCharacter("일지매"), 
				new DisplayCharacter("변학도") 
		};
		for(Thread t : disChars) {
			t.start();
		}
		for(Thread t : disChars) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("=================");
		System.out.println("경기 끝...");
		System.out.println("=================");
		System.out.println("경기 결과");
		System.out.println("순위 : " + strRank);
		System.out.println("=================");
	}
}

/*
 * 영어 대문자를 출력하는 스레드 클래스
 */
class DisplayCharacter extends Thread {
	private String name;
	
	public DisplayCharacter(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for(char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.println(name + "의 출력문자 : " + ch);
			try {
				Thread.sleep((int)(Math.random() * 301 + 200));
				//sleep()메서드의 값을 200~500사이의 난수로 한다.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name + " 출력 끝...");
		S0830_11.strRank += name + " ";
	}
}
