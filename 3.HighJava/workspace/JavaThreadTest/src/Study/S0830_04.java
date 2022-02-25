package Study;

import java.util.ArrayList;
import java.util.List;

public class S0830_04 {

	public static void main(String[] args) {
		/*
		 * 1~20억까지의 합계를 구하는데 걸린 시간 체크하기
		 * 전체 합계를 구하는 작업을 단독(1개의 스레드를 사용)으로 했을 떄와
		 * 여러 스레드로 분할해서 작업할 때의 시간을 비교해보기 
		 */
		
		//1. 싱글스레드
		Sum s = new Sum(1L, 2000000000L);
		
		long sTime = System.currentTimeMillis();
		
		s.start();
		try {
			s.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long eTime = System.currentTimeMillis();
		
		System.out.println("싱글스레드 : " + (eTime - sTime));
		System.out.println("=================================================");
		
		//2. 멀티스레드
		List<Sum> sumList = new ArrayList<Sum>();
		
		sumList.add(new Sum(	     1L,  500000000L));
		sumList.add(new Sum( 500000001L, 1000000000L));
		sumList.add(new Sum(1000000001L, 1500000000L));
		sumList.add(new Sum(1500000001L, 2000000000L));
		
		sTime = System.currentTimeMillis();
		
		for(Thread t : sumList) {
			t.start();
		}
		for(Thread t : sumList) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		eTime = System.currentTimeMillis();
		
		System.out.println("멀티스레드 : " + (eTime-sTime));
		System.out.println("=================================================");	
	}
}

class Sum extends Thread {
	private long min, max;

	public Sum(long min, long max) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public void run() {
		long sum = 0L;
		for(long i = min; i <= max; i++) {
			sum += i;
		}
		System.out.println(min + " - " + max + "합계 : " + sum);
	}
}
