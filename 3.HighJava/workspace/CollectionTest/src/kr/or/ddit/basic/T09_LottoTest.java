package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * 로또 구매 프로그램
 * - 사용자는 로또를 구매할 때 구매할 금액을 입력하고 입력한 금액에 맞게 로또를 출력한다.
 *   (단, 로또 한장의 금액은 1000원이고 거스름돈도 계산하여 출력한다.
 */
public class T09_LottoTest {
	private Scanner sc;
	
	public T09_LottoTest(){
		sc = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		new T09_LottoTest().lottoStart();
	}

	public void lottoStart() {
		while(true) {
			System.out.println("==========================");
			System.out.println("Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("==========================");
			System.out.print("메뉴선택 : ");
			int input = Integer.parseInt(sc.nextLine());
			
			switch (input) {
			case 1:
				lotto();
				break;
			case 2:
				System.out.println("감사합니다.");
				System.exit(2);
			}
		}
	}

	public void lotto() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("로또 구입 시작");
		System.out.println("(1000원에 로또 번호 하나입니다.)");
		System.out.print("금액입력 : ");
		int input = Integer.parseInt(sc.nextLine());
		if(input < 1000) {
			System.out.println("금액이 부족합니다.");
		}else {
			int count = input / 1000;
			int change = input % 1000;
			
			System.out.println("행운의 로또번호는 아래와 같습니다.");
			for(int i = 0; i < count; i++) {
				Set<Integer> lottoNo = new TreeSet<>();
				while(lottoNo.size() < 6) {
					int lottoNumber = (int)(Math.random() * 45 + 1);
					lottoNo.add(lottoNumber);
				}
				Iterator it = lottoNo.iterator();
				System.out.print("로또번호 " + (i + 1) + " : ");
				while(it.hasNext()) {
					System.out.print(it.next());
					if(it.hasNext()) {
						System.out.print(", ");
					}else {
						System.out.print(" ");
					}
				}
				System.out.println();
			}
			System.out.println("받은 금액은 " + input + "원이고, 거스름돈은 " + change + "원입니다.");
		}
	}
}
