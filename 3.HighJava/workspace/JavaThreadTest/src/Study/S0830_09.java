package Study;

public class S0830_09 {
	
	public static void main(String[] args) {
		AST as = new AST();
		
		//데몬스레드로 설정하기(start()메서드를 호출하기 전에 설정해야 한다.)
		as.setDaemon(true);
		as.start();
		
		try {
			for(int i = 1; i <= 20; i++) {
				System.out.println("작업 " + i);
				
				Thread.sleep(1000);
			}
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		
		System.out.println("메인 스레드 종료.");
	}
}

/*
 * 데이터 저장 기능
 * 
 * - 무한반복 되어야할 스레드를 데몬스레드로 설정
 * - 일반스레드가 종료되면 데몬스레드는 자동으로 종료됨
 */
class AST extends Thread {
	public void save() {
		System.out.println("작업 내용을 저장합니다.");
	}
	
	@Override
	public void run() {
		while(true) {
			//계속 저장하고 3초 쉬고를 반복함
			try {
				Thread.sleep(3000);
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			
			//저장기능 호출
			save();
		}
	}
}
