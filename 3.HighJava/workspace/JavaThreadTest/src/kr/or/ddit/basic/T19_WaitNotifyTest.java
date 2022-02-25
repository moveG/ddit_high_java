package kr.or.ddit.basic;

public class T19_WaitNotifyTest {
	/*
	 * wait()메서드
	 * - 동기화 영역에서 락을 풀고 Wait-Set영역(공유객체별 존재)으로 이동시킨다.
	 * 
	 * notify()메서드, notifyAll()메서드
	 * - Wait-Set영역에 있는 모든 스레드(들)를 깨워서 실행될 수 있도록 한다.
	 * - notify()는 하나를, notifyAll()은 Wait-Set에 있는 전부를 깨운다.
	 * 
	 * wait(), notify(), notifyAll()
	 * - 동기화 영역에서만 실행할 수 있다.
	 * - Object클래스에서 제공하는 메서드이다.
	 */
	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		Thread thA = new ThreadA(workObj);
		Thread thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
		
		//ThreadA는 종료
		//ThreadB는 wait상태로 누군가가 깨워줄 때까지 대기함
		//ThreadB를 깨워줄 누군가가 없어서 ThreadB는 계속 wait상태로 대기하고 프로그램은 종료되지 않음
		//wait()에 시간을 설정해주면, 설정한 시간이 경과하면 자동으로 wait상태에서 벗어남
	}
}

/*
 * 공통으로 사용할 객체
 */
class WorkObject {
	public synchronized void methodA() {
		System.out.println("methodA() 메서드 작업 중...");	//메시지를 출력함
		
		notify();	//대기실에 wait하고 있는 것이 있으면 깨움
		
		try {
			wait();	//자신은 대기실로 들어감, 락을 해제함
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void methodB() {
		System.out.println("methodB() 메서드 작업 중...");	//메시지를 출력함
		
		notify();	//대기실에 wait하고 있는 것이 있으면 깨움
		
		try {
			wait(1000);	//자신은 대기실로 들어감, 락을 해제함, 설정한 시간이 경과하면 자동으로 wait상태에서 벗어남
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

/*
 * WorkObject의 methodA()메서드만 호출하는 스레드
 */
class ThreadA extends Thread {
	private WorkObject workObj;
	
	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			workObj.methodA();	//공유객체의 methodA를 10번 호출함, 인스턴스에 진입하면 락을 채움
		}
		System.out.println("ThreadA 종료");
	}
}

/*
 * WorkObject의 methodB()메서드만 호출하는 스레드
 */
class ThreadB extends Thread {
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			workObj.methodB();	//공유객체의 methodB를 10번 호출함, 인스턴스에 진입하면 락을 채움
		}
		System.out.println("ThreadB 종료");
	}
}
