package Study;

/**
 * 은행의 입출금을 스레드로 처리하는 예제
 * (Synchronized를 이용한 동기화 처리)
 */
public class S0830_16 {
	
	public static void main(String[] args) {
		Account acc = new Account();
		acc.setChange(20000);
		
		Bank b1 = new Bank(acc);
		Bank b2 = new Bank(acc);
		
		b1.start();
		b2.start();
	}
}

/*
 * 은행의 입출금을 관리하는 클래스 정의
 */
class Account {
	//잔액이 저장될 변수
	private int change;

	public synchronized int getChange() {
		return change;
	}

	public synchronized void setChange(int change) {
		this.change = change;
	}
	
	//입금처리를 수행하는 메서드
	public void depo(int money) {
		change += money;
	}
	
	//출금처리를 수행하는 메서드(출금성공 : true, 출금실패 : false)
	public synchronized boolean withdraw(int money) {
		if(change > money) {	//출금금액보다 잔액이 많을 경우
			for(int i = 1; i <= 1000000000; i++) {}	//시간벌기 용도
			change -= money;
			System.out.println("메서드 안에서 잔액 : " + getChange());
			return true;
		}else {
			return false;
		}
	}
}

/*
 * 은행업무를 처리하는 스레드
 */
class Bank extends Thread {
	private Account sAcc;
	
	public Bank(Account sAcc) {
		this.sAcc = sAcc;
	}
	
	@Override
	public void run() {
		boolean result = sAcc.withdraw(6000);	//6000원 인출
		System.out.println("스레드 안에서 출금결과 : " + result + ", 잔액 : " + sAcc.getChange());
	}
}
