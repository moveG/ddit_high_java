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
public class HorseRacing {
	public static int rank = 0;
	
	public static void main(String[] args) throws InterruptedException {
		List<Horses> horseList = new ArrayList<Horses>();
		for(int i = 0; i < 10; i++) {
			horseList.add(new Horses(i + "번마"));
		}
		
		Start start = new Start(horseList);
		
		for(Horses h : horseList) {
			h.start();
		}
		start.start();
		
		for(Horses h : horseList) {
			try {
				h.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			start.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("==========================================================");
		System.out.println("경기종료");
		System.out.println("===============");
		Thread.sleep(2000);
		
		Collections.sort(horseList);
		
		for(Horses h : horseList) {
			if(h.getRank() == 9) {
				System.out.println((h.getRank() + 1) + "등 : " + h.getHorseName());
			}else {
				System.out.println(" " + (h.getRank() + 1) + "등 : " + h.getHorseName());
			}
		}
		System.out.println("===============");
	}
}

class Start extends Thread {
	List<Horses> horseList;
	
	public Start(List<Horses> horseList) {
		this.horseList = horseList;
	}
	
	@Override
	public void run() {
		while(true) {
			if(HorseRacing.rank == horseList.size()) {
				break;
			}
			System.out.println();
			System.out.println();
			
			for(int i = 0; i < horseList.size(); i++) {
				System.out.print(horseList.get(i).getHorseName() + " : ");
				
				for(int j = 1; j <= 50; j++) {
					if(horseList.get(i).getLocation() == j) {
						System.out.print("🐎");
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

class Horses extends Thread implements Comparable<Horses> {
	private String horseName;
	private int rank;
	private int location;
	
	public Horses(String horseName) {
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
				Thread.sleep((int)(Math.random() * 200 + 100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		rank = HorseRacing.rank++;
	}

	@Override
	public int compareTo(Horses h) {
		if(rank > h.getRank()) {
			return 1;
		}else if(rank == h.getRank()) {
			return 0;
		}else {
			return -1;
		}
	}
}
