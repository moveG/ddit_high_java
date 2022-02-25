package Study;

public class S0830_03 {

	public static void main(String[] args) {
		/*
		 * 스레드 수행시간 체크
		 */
		MyRunner r = new MyRunner();
		Thread t = new Thread(r);
		
		//UTC(Universal Time Coordinated)협정 세계 표준시를 이용
				//1970년 1월 1일 0시 0분 0초를 기준으로 경과한 시간을 밀리세컨드 단위로 나타낸다.
				//유닉스 타임스탬프
		long sTime = System.currentTimeMillis();
		t.start();
		try {
			t.join();
			//현재 실행중인 스레드에서 작업중인 스레드
			//(지금은 t스레드)가 종료될 때까지 기다린다.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long eTime = System.currentTimeMillis();
		
		System.out.println("경과시간 : " + (eTime - sTime) + " ms");
	}
}

/**
 * 1~1000000000(일~십억)까지의 합계를 구하는 스레드
 */
class MyRunner implements Runnable {
	@Override
	public void run() {
		long sum = 0;
		for(long i = 1L; i <= 1000000000; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
}
