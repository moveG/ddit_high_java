package Study;

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
 *   2번말 ------------->--------------------------
 *   3번말 ------->--------------------------------
 * 	  :
 * - 경기가 끝나면 등수를 기준으로 정렬하여 출력한다.
 */
public class HorseGame {
	public static int rank = 0;
	
	public static void main(String[] args) {
		List<Horse> horseList = new ArrayList<Horse>();
		for(int i = 0; i < 10; i++) {
			horseList.add(new Horse(i + "번마"));
		}
		
		Racing racing = new Racing(horseList);
		
		for(Horse h : horseList) {
			h.start();
		}
		racing.start();

		for(Horse h : horseList) {
			try {
				h.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			racing.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		System.out.println("경기종료");
		System.out.println("===============");
		System.out.println("경기결과");

		Collections.sort(horseList);
		
		for(Horse h : horseList) {
			if(h.getRank() == 9) {
				System.out.println((h.getRank() + 1) + "등" + " : " + h.getHorseName());
			}else {
				System.out.println(" " + (h.getRank() + 1) + "등" + " : " + h.getHorseName());
			}
		}
	}
}

class Racing extends Thread {
	List<Horse> horseList;
	
	public Racing(List<Horse> horseList) {
		this.horseList = horseList; 
	}
	
	@Override
	public void run() {
		while(true) {
			if(HorseGame.rank == horseList.size()) {
				break;
			}
			System.out.println();
			System.out.println();
			
			for(int i = 0; i < horseList.size(); i++) {
				System.out.print(horseList.get(i).getHorseName() + " : ");
				
				for(int j = 1; j < 50; j++) {
					if(horseList.get(i).getLocation() == j) {
						System.out.print("말");
					}else {
						System.out.print("=");
					}
				}
				System.out.println();
				
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Horse extends Thread implements Comparable<Horse> {
	private String horseName;
	private int rank;
	private int location;
	
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
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 50; i++) {
			location = i;
			
			try {
				Thread.sleep((int)(Math.random() * 300 + 200));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		rank = HorseGame.rank++;
	}
	
	@Override
	public int compareTo(Horse hs) {
		if(rank > hs.getRank()) {
			return 1;
		}else if(rank == hs.getRank()) {
			return 0;
		}else {
			return -1;
		}
	}	
}
