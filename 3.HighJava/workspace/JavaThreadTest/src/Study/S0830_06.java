package Study;

import javax.swing.JOptionPane;

/**
 * 멀티 스레드에서 카운트다운 처리
 */
public class S0830_06 {
	public static boolean check = false;
	
	public static void main(String[] args) {
		Input in = new Input();
		in.start();
		
		Count ct = new Count();
		ct.start();
	}
}

/**
 * 데이터 입력 스레드
 */
class Input extends Thread {
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("입력하세요.");
		S0830_06.check = true;
		System.out.println("입력한 값은 " + str + "입니다.");
	}
}

/**
 * 카운트다운 처리 스레드
 */
class Count extends Thread {
	@Override
	public void run() {
		for(int i = 10; i >= 1; i--) {
			if(S0830_06.check == true) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0);
	}
}
