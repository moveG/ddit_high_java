package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
 * 호텔운영을 관리하는 프로그램 작성(Map 사용)
 * - Key값은 방번호
 */
public class T13_HotelTest {
	private Scanner sc;
	private Map<Integer, Hotel> hotelMap;
	
	public T13_HotelTest() {
		sc = new Scanner(System.in);
		hotelMap = new HashMap<>();
	}

	//프로그램 시작 메인
	public static void main(String[] args) {
		new T13_HotelTest().hotelStart();
	}
	
	//업무출력 메서드
	public void workMenu() {
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("========================================================");
		System.out.println("1.체크인\t\t2.체크아웃\t3.객실상태\t4.업무종료");
		System.out.println("========================================================");
		System.out.print("메뉴선택>");
	}
	//프로그램 시작 메서드
	public void hotelStart() {
		System.out.println("========================================================");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("========================================================");
		
		while(true) {
			workMenu();
			int input = Integer.parseInt(sc.nextLine());
			switch (input) {
			case 1: checkIn();		//체크인
				break;
			case 2: checkout();		//체크아웃
				break;
			case 3: roominfo();		//객실상태
				break;
			case 4:
				System.out.println("호텔 문을 닫았습니다.");
				System.exit(4);
			default:
				System.out.println("잘못 입력했습니다. 다시 입력하세요.");
			}//switch
		}//while
	}//hotelStart
	
	//객실상태
	private void roominfo() {
		Set<Integer> keySet = hotelMap.keySet();
//		List<Integer> list = new ArrayList<>();
//		for(Integer key : keySet)
//		list.add(key);
//		
//		Collections.sort(list);
//		for(int i = list.size() - 1; i >= 0; i--) {
//			System.out.println("방 번호 : " + list.get(i) + ", 투숙객 : " + hotelMap.get(list.get(i)));
//		}
		if(keySet.size() == 0) {
			System.out.println("체크인된 룸이 없습니다.");
		}else {
			Iterator<Integer> it = keySet.iterator();
			while(it.hasNext()) {
				int roomNo = it.next();
				Hotel h = hotelMap.get(roomNo);
				System.out.println("방 번호 : " + h.getRoomNo() + ", 투숙객 : " + h.getName());
			}
		}
		System.out.println("객실상태를 확인했습니다.");
		System.out.println("========================================================");
	}
	
	//체크아웃
	private void checkout() {
		System.out.println("어느 방을 체크아웃 하시겠습니까?");
		System.out.print("방 번호 입력>");
		int roomNo = Integer.parseInt(sc.nextLine());
		
		if(hotelMap.remove(roomNo) == null) {
			System.out.println(roomNo + "호실은 이미 체크아웃된 상태입니다.");
		}else {
			System.out.println("체크아웃 되었습니다.");
		}
		System.out.println("========================================================");
	}
	
	//체크인
	private void checkIn() {
		System.out.println("어느 방에 체크인 하시겠습니까?");
		System.out.print("방 번호 입력>");
		int roomNo = Integer.parseInt(sc.nextLine());
		if(hotelMap.get(roomNo) != null) {
			System.out.println(roomNo + "호실은 이미 체크인 상태입니다.");
			return;
		}
		
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력>");
		String name = sc.nextLine();
		
		hotelMap.put(roomNo, new Hotel(roomNo, name));
		System.out.println("체크인 되었습니다.");
		System.out.println("========================================================");
	}
}

class Hotel {
	private int roomNo;		//룸 번호
	private String name;	//이름
	
	public Hotel(int roomNo, String name) {
		super();
		this.roomNo = roomNo;
		this.name = name;
	}

	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Hotel [roomNo=" + roomNo + ", name=" + name + "]";
	}
}
