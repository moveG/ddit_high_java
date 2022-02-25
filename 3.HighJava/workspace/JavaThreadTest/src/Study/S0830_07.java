package Study;

import javax.swing.JOptionPane;

/**
 * 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 * 
 * - 컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
 *   사용자의 가위 바위 보는 showInputDialog() 메서드를 이용하여 입력 받는다.
 * 
 * - 입력시간은 5초로 제한하고 카운트 다운을 진행한다.
 * - 5초 안에 입력이 없으면 게임을 진 것으로 처리한다.
 * - 5초안에 입력이 완료되면 승패를 출력한다.
 * 
 * 결과예시)
 * === 결 과 ===
 * 컴퓨터 : 가위
 * 당 신 : 바위
 * 결 과 : 당신이 이겼습니다.
 */
public class S0830_07 {
	public static boolean check = false;
	
	public static void main(String[] args) {
		Count2 c = new Count2();
		
		String[] data = {"가위", "바위", "보"};
		int index = (int)(Math.random() * 3);
		
		String com = data[index];
		
		User u = new User();
		
		c.start();
		
		do {
			u.run();
		}while(!u.str.equals("가위") && !u.str.equals("바위") && !u.str.equals("보"));
		
		
		check = true;
		
		String result;
		if(u.str.equals(com)) {
			result = "비겼습니다.";
		}else if((u.str.equals("가위") && com.equals("보"))
			   ||(u.str.equals("바위") && com.equals("가위"))
			   ||(u.str.equals("보") && com.equals("바위"))) {
			result = "이겼습니다.";
		}else {
			result = "졌습니다.";
		}
		
		System.out.println("=== 결 과 ===");
		System.out.println("컴퓨터 : " + com);
		System.out.println("당  신 : " + u.str);
		System.out.println("결  과 : " + result);
	}
}

/**
 * 유저 입력 처리 스레드
 */
class User extends Thread {
	String str;
	@Override
	public void run() {
		str = JOptionPane.showInputDialog("가위바위보");
		
		S0830_07.check = true;
	}
}

/**
 * 카운트다운 처리 스레드
 */
class Count2 extends Thread {
	@Override
	public void run() {
		for(int i = 5; i >= 1; i--) {
			if(S0830_07.check == true) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("시간이 초과되었습니다. 당신이 패배하였습니다.");
		System.exit(0);
	}
}
