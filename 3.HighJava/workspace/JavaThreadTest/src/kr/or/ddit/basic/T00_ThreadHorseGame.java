package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 10마리의 말들이 경주하는 경마 프로그램 작성하기
 * 
 * - 말은 Horse라는 이름의 클래스로 구성
 * - 이 클래스에는 말이름(String), 등수(int)가 멤버변수
 * - 이 클래스에는 오름차순 등수 정렬 기능이 존재함(Comparable 인터페이스 구현)
 * 
 * - 경기 구간은 1~50 구간으로 되어있다.
 * 
 * - 경기 중 각 말들의 위치를 >로 나타내시오. 
 *   ex)
 *   1번말 --->------------------------------------
 *   2번말 ----->----------------------------------
 * 	  :
 * - 경기가 끝나면 등수를 기준으로 정렬하여 출력한다.
 */
public class T00_ThreadHorseGame {
	public static int horseRank = 0;
	public static List<Horse> horseList = new ArrayList<Horse>();
	
	public static void main(String[] args) {
		horseList.add(new Horse("1번마"));
		horseList.add(new Horse("2번마"));
		horseList.add(new Horse("3번마"));
		horseList.add(new Horse("4번마"));
		horseList.add(new Horse("5번마"));
		horseList.add(new Horse("6번마"));
		horseList.add(new Horse("7번마"));
		horseList.add(new Horse("8번마"));
		horseList.add(new Horse("9번마"));
		horseList.add(new Horse("0번마"));
		
		for(Horse h1 : horseList) {
			h1.start();
		}
		for(Horse h1 : horseList) {
			try {
				h1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Collections.sort(horseList);
		System.out.println("===============");
		System.out.println("경기종료");
		System.out.println("===============");
		System.out.println("경기결과");
		for(Horse h : horseList) {
			if(h.getRank() < 10) {
				System.out.println(h.getRank() + "등" + "   : " + h.getHorseName());
			}else {
				System.out.println(h.getRank() + "등" + " : " + h.getHorseName());
			}
		}
		System.out.println("===============");
	}
}

/*
 * 말
 */
class Horse extends Thread implements Comparable<Horse> {
	private String horseName;
	private int rank;
	
	public Horse(String horseName) {
		this.horseName = horseName;
	}
	public String getHorseName() {
		return horseName;
	}
	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 50; i++) {
			String location = "";
			System.out.println();
			System.out.println();
			for(int j = 0; j < i; j++) {
				location += "-";
			}
			location += ">";
			for(int j = 49; j > i; j--) {
				location += "-";
			}
			System.out.print(horseName + " : " + location);
			
			System.out.println();
			
			
			try {
				Thread.sleep((int)(Math.random() * 300));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("=============");
		System.out.println(horseName + " 통과");
		System.out.println("=============");
		
		rank = ++T00_ThreadHorseGame.horseRank;
	}
	
	@Override
	public int compareTo(Horse hs) {
		if(rank > hs.getRank()) {
			return 1;
		}else if(rank == hs.getRank()){
			return 0;
		}else {
			return -1;
		}
	}
}