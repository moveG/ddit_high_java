package kr.or.ddit.basic;

/**
 * 은행의 입출금을 스레드로 처리하는 예제
 * (Synchronized를 이용한 동기화 처리)
 */
public class T16_SyncAccountTest {
	
	public static void main(String[] args) {
		SyncAccount sAcc = new SyncAccount();
		sAcc.setBalance(20000);
		
		BankThread bth1 = new BankThread(sAcc);
		BankThread bth2 = new BankThread(sAcc);
		
		bth1.start();
		bth2.start();
	}
}

/*
 * 은행의 입출금을 관리하는 클래스 정의
 */
class SyncAccount {
	//잔액이 저장될 변수
	private int balance;

	public synchronized int getBalance() {
		return balance;
	}
	public synchronized void setBalance(int balance) {
		this.balance = balance;
	}
	
	//입금처리를 수행하는 메서드
	public void deposit(int money) {
		balance += money;
	}
	
	//출금처리를 수행하는 메서드(출금성공 : true 반환, 출금실패 : false 반환)
	public synchronized boolean withdraw(int money) {
		if(balance >= money) {	//출금금액보다 잔액이 많을 경우
			for(int i = 1; i <= 1000000000; i++) {}	//시간벌기 용도
			
			balance -= money;	//출금하기
			
			System.out.println("메서드 안에서 balance(잔액) = " + getBalance());
			
			return true;	//출금성공
		}else {
			return false;	//출금실패
		}
	}
}

/*
 * 은행업무를 처리하는 스레드
 */
class BankThread extends Thread {
	private SyncAccount sAcc;
	
	public BankThread(SyncAccount sAcc) {
		this.sAcc = sAcc;
	}
	
	@Override
	public void run() {
		boolean result = sAcc.withdraw(6000);	//6000원 인출
		System.out.println("스레드 안에서 result(출금결과) = " + result
								+ ", balance(잔액) = " + sAcc.getBalance());
	}
	
}
