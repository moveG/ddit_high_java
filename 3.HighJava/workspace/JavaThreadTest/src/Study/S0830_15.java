package Study;

public class S0830_15 {
	/*
	 * 동기화 처리방법
	 * - 메서드 자체에 동기화 설정하기
	 * - 동기화 블럭으로 설정하기
	 * 
	 * - 메서드 자체에 동기화를 설정하는 것은 메서드 전체에 동기화가 설정됨
	 * - 원하는 위치에 블럭을 설정할 수 있는 동기화블럭보다 융통성이 떨어짐
	 * - 동기화가 과도하면 성능이 저하됨
	 */
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		
		WorkThread wth1 = new WorkThread(sObj);
		WorkThread wth2 = new WorkThread(sObj);
		
		wth1.start();
		wth2.start();
		//합계가 10, 20, 30, ...으로 가야하지만
		//동시에 add()메서드가 실행되어 뒤죽박죽 합계가 실행됨(20, 20, 30, ...)
		//임계영역의 문제 때문에 순차적으로 합계가 되지 않음
		//동기화 처리를 통해 보정이 가능함
	}
}

/*
 * 공통으로 사용할 객체
 */
class ShareObject {
	private int sum = 0;
	
	//동기화 처리방법 1 : 메서드 자체에 동기화 설정하기
//	public synchronized void add() {
	public void add() {
		//동기화 처리하는 방법2 : 동기화 블럭으로 설정하기
//		synchronized (this) {	//this : 현재 객체(자기 자신)를 동기화 시키겠다.
			for(int i = 0; i < 1000000000; i++) {}	//동기화 전까지 시간벌기 용도
			
			int n = sum;
			n += 10;	//10 증가
			sum = n;
			
			System.out.println(Thread.currentThread().getName() + " 합계 : " + sum);
//		}
	}
	
	public int getSum() {
		return sum;
	}
}

/*
 * 작업을 수행하는 스레드
 */
class WorkThread extends Thread {
	ShareObject sObj;
	
	public WorkThread(ShareObject sObj) {
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			synchronized (sObj) {	//여기에서도 동기화 설정이 가능함
				sObj.add();	//ShareObject스레드의 add메서드를 10번 호출	
			}
		}
	}
}
