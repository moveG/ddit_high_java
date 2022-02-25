package Study;

public class S0830_13 {
	/*
	 * Thread의 stop()메서드를 호출하면 스레드가 바로 멈춘다.
	 * 이때 사용하던 자원을 정리하지 못하고 프로그램이 종료되기 때문에
	 * 나중에 실행되는 프로그램에 영향을 줄 수 있다.
	 * 그래서 현재 stop()메서드는 비추천으로 되어있다.
	 */
	public static void main(String[] args) {
//		//방법1 : setStop()메서드를 이용한 스레드 정지
//		ThreadStopEx1 t1 = new ThreadStopEx1();
//		t1.start();
//		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		t.setStop(true);
//		//th.stop();	//스레드를 정리없이 강제로 죽임, 비추천
		
		//방법2 : interrupt()메서드를 이용한 스레드 정지
		ThreadStopEx2 t2 = new ThreadStopEx2();
		t2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t2.interrupt();	//인터럽트 걸기
	}
}

class ThreadStopEx1 extends Thread {
	private boolean stop;
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	@Override
	public void run() {
		while(!stop) {
			System.out.println("스레드 처리 중...");
		}
		System.out.println("자원 정리 중...");
		System.out.println("실행종료");
	}
}

/*
 * interrupt()를 이용하려 스레드를 정지시키는 방법
 */
class ThreadStopEx2 extends Thread {
	@Override
	public void run() {
//		//방법1 : sleep()메서드 또는 join()메서드 등을 사용할 때 interrupt()메서드를
//		//		  호출하면 interruptedException이 발생한다.
//		try {
//			while(true) {
//				System.out.println("스레드 처리 중...");
//				Thread.sleep(1);
//			}
//		} catch (InterruptedException e) {}
//		//Thread.sleep()작업 동안 외부에서 interruptedException이 발생할 수 있으니 try-catch로 감싸줘야함
//		//while무한루프 중 외부에서 interrupt가 발생하면 무한루프에서 빠져나와 catch문으로 이동함
//		
//		System.out.println("자원 정리 중...");
//		System.out.println("실행 종료");
		
		//방법2 : interrupt()메서드가 호출되었나 검사하기
		while(true) {
			System.out.println("스레드 처리 중...");
//			//검사방법1 : 스레드의 인스턴스 객체용 메서드를 이용하는 방법
//			if(isInterrupted()) {
//				System.out.println("인스턴스용 isInterrupted() : " + isInterrupted());
//				//인스턴스가 걸려있으면 true를, 아니면 false를 반환함
//				break;
//			}
			
			//검사방법2 : 스레드의 정적 메서드를 이용하는 방법
			if(Thread.interrupted()) {
				System.out.println("정적 메서드 Thread.interrupted() : " + Thread.interrupted());
				//인터럽트가 걸리는 시점에만 true였다가 자신을 초기화시켜 false가 됨
				break;
			}
		}
	}
}
