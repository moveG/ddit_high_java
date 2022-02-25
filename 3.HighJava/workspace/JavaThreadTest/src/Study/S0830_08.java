package Study;

public class S0830_08 {
	
	public static void main(String[] args) {
		System.out.println("최대 우선순위 : " + Thread.MAX_PRIORITY);
		System.out.println("보통 우선순위 : " + Thread.NORM_PRIORITY);
		System.out.println("최저 우선순위 : " + Thread.MIN_PRIORITY);
		//최대(MAX, 우선순위 높음)  : 10
		//중간(NORM, 우선순위 보통) : 5(default값)
		//최소(MIN, 우선순위 낮음)  : 1
		//진짜 우선순위 대로 나올지는 알 수 없다.
		
		//우선순위 입력 : setPriority()
		//우선순위 출력 : getPriority()
		
		Thread t1 = new Th1();
		Thread t2 = new Th1();
		Thread t3 = new Th1();
		Thread t4 = new Th1();
		Thread t5 = new Th1();
		
		Thread t6 = new Th2();

		//우선순위는 start()메서드를 호출하기 전에 설정해야 한다.
		t1.setPriority(1);
		t2.setPriority(1);
		t3.setPriority(1);
		t4.setPriority(1);
		t5.setPriority(1);
		t6.setPriority(10);
		
		System.out.println("t1의 우선순위 : " + t1.getPriority());
		System.out.println("t6의 우선순위 : " + t6.getPriority());
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
	}
}

/*
 * 대문자를 출력하는 메서드
 */
class Th1 extends Thread {
	@Override
	public void run() {
		for(char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.println(ch);
			
			//아무것도 하지않는 반복문(시간때우기용)
			for(long i = 1; i <= 1000000000L; i++) {}
		}
	}
}

/*
 * 소문자를 출력하는 메서드
 */
class Th2 extends Thread {
	@Override
	public void run() {
		for(char ch = 'a'; ch <= 'z'; ch++) {
			System.out.println(ch);
			
			//아무것도 하지않는 반복문(시간때우기용)
			for(long i = 1; i <= 1000000000L; i++) {}
		}
	}
}