package kr.or.ddit.basic;

public class T12_ThreadYieldTest {
	/*
	 * yield()메서드
	 * 
	 * 1. 현재 실행대기 중인  동등한 우선순위 이상의 다른 스레드에게 실행기회를 제공한다.
	 * 2. 현재 실행 중인 스레드의 상태를 Runnable상태로 바꾼다.
	 * 3. yield()메서드를 실행한다고해서 현재 실행 중인 스레드가
	 *    곧바로 runnable 상태로 전이된다고 확실할 수는 없다.
	 *    
	 * YieldThreadEx1은 양보를 하기 때문에 대부분 YieldThreadEx2가 먼저 종료됨(항상은 아님)   
	 */
	public static void main(String[] args) {
		Thread th1 = new YieldThreadEx1();
		Thread th2 = new YieldThreadEx2();
		
		th1.start();
		th2.start();
	}
}

class YieldThreadEx1 extends Thread {
	public YieldThreadEx1() {
		super("YieldThreadEx1");	//부모클래스(여기서는 Thread클래스의 생성자)의 생상자를 호출
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " : " + i);
			Thread.yield();		//양보하기
		}
	}
}

class YieldThreadEx2 extends Thread {
	public YieldThreadEx2() {
		super("YieldThreadEx2");
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " : " + i);
		}
	}
}
