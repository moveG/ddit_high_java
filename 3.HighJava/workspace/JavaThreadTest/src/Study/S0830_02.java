package Study;

public class S0830_02 {
	
	public static void main(String[] args) {
		/*
		 * 멀티 스레드 프로그램 방식
		 * 
		 * 방법1 : Thread클래스를 상속한 클래스의 인스턴스를 생성한 후,
		 *        이 인스턴스의 start()메서드를 호출한다.
		 * 
		 * 방법2 : Runnable인터페이스를 구현한 class의 인스턴스를 생성한 후
		 *        이 인스턴스를 Thread객체의 인스턴스를 생성할 때 생성자의 매개변수로 넘겨준다.
		 *        이 때 생성된 Thread객체의 인스턴스의 start()메서드를 호출한다.
		 *        다중상속이 불가능하기 때문에 이미 상속을 받은 클래스에는 방법2를 사용하는 것이 적절하다.
		 * 
		 * 방법3 : 익명클래스를 이용하는 방법
		 *        Runnable인터페이스를 구현한 익명클래스를 Thread인터페이스를 생성할때 매개변수로 넘겨준다.
		 */
		
		//방법1
		Mt1 t1 = new Mt1();
		t1.start();
		//Thread클래스에는 start()메서드가 존재함
		//main쓰레드와 Mt1스레드 2개가 실행됨
				
		//방법2-1
		Runnable r1 = new Mt2();
		Thread t2 = new Thread(r1);
		t2.start();
		
		//방법2-2
//		Mt2 t3 = new Mt2();
//		Thread t4 = new Thread(t3);
//		t4.start();
		
		//방법3
		Thread t5 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i <= 200; i++) {
					System.out.print("@");
					try {
						Thread.sleep(100);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		t5.start();
	}
}

class Mt1 extends Thread{
	/*
	 * 방법1 : Thread클래스를 상속한 클래스의 인스턴스를 생성한 후,
	 *        이 인스턴스의 start()메서드를 호출한다.
	 */
	@Override
	public void run() {
		for(int i = 0; i < 200; i++) {
			System.out.print("*");
			try {
				Thread.sleep(100);
				//Thread.sleep(시간) : 주어진 시간동안 작업을 잠시 멈춘다.
				//시간의 단위는 밀리세컨드를 사용한다.
				//1000밀리세컨드는 1초를 의미한다.
			}catch(InterruptedException e) {
				e.printStackTrace();
				
			}
		}
	}
}

class Mt2 implements Runnable {
	/*
	 * 방법2 : Runnable인터페이스를 구현한 class의 인스턴스를 생성한 후
	 *        이 인스턴스를 Thread객체의 인스턴스를 생성할 때 생성자의 매개변수로 넘겨준다.
	 *        이 때 생성된 Thread객체의 인스턴스의 start()메서드를 호출한다.
	 */
	@Override
	public void run() {
		for(int i = 0; i <= 200; i++) {
			System.out.print("$");
			try {
				Thread.sleep(100);
				//Thread.sleep(시간) : 주어진 시간동안 작업을 잠시 멈춘다.
				//시간의 단위는 밀리세컨드를 사용한다.
				//1000밀리세컨드는 1초를 의미한다.
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}
