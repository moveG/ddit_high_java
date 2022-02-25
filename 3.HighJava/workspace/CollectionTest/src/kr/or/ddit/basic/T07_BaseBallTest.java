package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * 문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
 * - 컴퓨터의 숫자는 난수를 이용하여 구한다.(스트라이크는 'S', 볼은 'B'로 출력한다.)
 * 
 * 예시) 컴퓨터의 난수가 9 5 7 일 때 실행
 * - 숫자입력 => 3 5 6
 * - 3 5 6 => 1S 0B
 * - 숫자입력 => 7 8 9
 * - 7 8 9 => 0S 2B
 *     :
 * - 숫자입력 => 9 5 7
 * - 9 5 7 => 3S 0B    
 * - 5번째 만에 맞췄군요.
 */
public class T07_BaseBallTest {
	Scanner sc = new Scanner(System.in);
	List<Integer> comList;
	List<Integer> userList;
	int strike;
	int ball;
	
	public static void main(String[] args) {
		new T07_BaseBallTest().start();	
	}
	
	public void comNum() {
		Set<Integer> com = new HashSet<>();
		
		while(com.size() < 3) {	//중복되지 않은 데이터가 3개가 될 때까지 반복실행됨
			int num = (int)(Math.random() * 9 + 1);
			com.add(num);
		}
		comList = new ArrayList<Integer>(com);
		
		Collections.shuffle(comList);
	}
	
	public void userNum() {
		int user1;
		int user2;
		int user3;
		System.out.println("1~9까지의 숫자를 입력하세요.");
		do {
			System.out.print("숫자입력>");
			user1 = Integer.parseInt(sc.nextLine());
			System.out.print("숫자입력>");
			user2 = Integer.parseInt(sc.nextLine());
			System.out.print("숫자입력>");
			user3 = Integer.parseInt(sc.nextLine());
			
			if(user1 == user2 || user2 == user3 || user3 == user1) {
				System.out.println("중복되는 숫자는 입력할 수 없습니다. 다시 입력하세요.");
			}
		}while(user1 == user2 || user2 == user3 || user3 == user1);
		
		userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
	}
	
	public void ballCount() {
		strike = 0;
		ball = 0;
		
		for(int i = 0; i < userList.size(); i++) {
			for(int j = 0; j < comList.size(); j++) {
				if(userList.get(i) == comList.get(j)) {
					if(i == j) {
						strike++;
					}else {
						ball++;
					}
				}
			}
		}
		System.out.println(userList.get(0) + ", " + userList.get(1) + ", " + userList.get(2)
					+ " => strike : " + strike + "S " + ball + "B");
	}
	
	public void start() {
		comNum();
		System.out.println(comList);
		int count = 0;
		do {
			count++;
			userNum();
			ballCount();
		}while(strike != 3);
		
		System.out.println(count + "번째 만에 맞췄군요.");
	}
}

