package Study;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
 * 호텔운영을 관리하는 프로그램 작성(Map 사용)
 * - Key값은 방번호
 */
public class Homework3 {
	private Scanner sc;
	private Map<String, Hotel> hotelMap;
	
	Homework3() {
		sc = new Scanner(System.in);
		hotelMap = new HashMap<>();
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		new Homework3().hotelStart();
	}
	
	private void workMenu() {
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("========================================================");
		System.out.println("1.체크인\t\t2.체크아웃\t3.객실상태\t4.업무종료");
		System.out.println("========================================================");
		System.out.print("메뉴선택>");
	} 

	private void hotelStart() throws ClassNotFoundException {
		System.out.println("========================================================");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("========================================================");
		
		hotelOpen();	//호텔오픈
		
		while(true) {
			workMenu();
			int input = Integer.parseInt(sc.nextLine());
			switch (input) {
			case 1:		//체크인
				checkIn();
				break;
			case 2:		//체크아웃
				checkOut();
				break;
			case 3:		//객실상태
				roomInfo();
				break;
			case 4:		//업무종료
				hotelClose();
				System.out.println("호텔 문을 닫았습니다.");
				System.exit(4);
			default:
				System.out.println("잘못 입력했습니다. 다시 입력하세요.");
			}//switch
		}//while
	}//hotelStart

	private void hotelOpen() throws ClassNotFoundException {	//객실상태 정보를 파일에서 불러오는 메서드
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream("d:/EE/hotel.bin"));
			
			Object obj = null;
			
			while((obj = ois.readObject()) != null) {
				Hotel h = (Hotel)obj;
				
				hotelMap.put(h.getRoomNo(), h);
			}
			ois.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
//			e.printStackTrace();
		}
	}

	private void hotelClose() {	//객실상태 정보를 파일로 저장하는 메서드
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("d:/EE/hotel.bin"));
			
			Set<String> keySet = hotelMap.keySet();
			
			Iterator<String> it = keySet.iterator();
			
			while(it.hasNext()) {
				String roomNo = it.next();
				
				Hotel h = hotelMap.get(roomNo);
				
				oos.writeObject(h);
			}
			oos.close();
		} catch (Exception e) {
//			e.printStackTrace();
		}	
	}

	private void roomInfo() {	//객실상태
		Set<String> keySet = hotelMap.keySet();
		if(keySet.size() == 0) {
			System.out.println("체크인된 룸이 없습니다.");
		}else {
			Iterator<String> it = keySet.iterator();
			while(it.hasNext()) {
				String no = it.next();
				Hotel h = hotelMap.get(no);
				System.out.print("방번호 : " + h.getRoomNo() + " ");
				System.out.print("이름 : " + h.getName());
				System.out.println();
			}
		}
		System.out.println("객실상태를 확인했습니다.");
		System.out.println("========================================================");
	}

	private void checkOut() {	//체크아웃
		System.out.println("어느 방을 체크아웃 하시겠습니까?");
		System.out.print("방 번호 입력>");
		String no = sc.nextLine();
		if(hotelMap.get(no) == null) {
			System.out.println(no + "호실은 빈방입니다.");
		}else {
			hotelMap.remove(no);
			System.out.println("체크아웃 되었습니다.");
		}
		System.out.println("========================================================");
	}

	private void checkIn() {	//체크인
		System.out.println("어느 방에 체크인 하시겠습니까?");
		System.out.print("방 번호 입력>");
		String no = sc.nextLine();
		if(hotelMap.get(no) != null) {
			System.out.println(no + "호실은 이미 체크인 상태입니다.");
			return;
		}
		
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력>");
		String nm = sc.nextLine();
		
		hotelMap.put(no, new Hotel(no, nm));
		System.out.println("체크인 되었습니다.");
		System.out.println("========================================================");
	}
}//Homework3

class Hotel implements Serializable {
	private String roomNo;	//방번호
	private String name;	//이름
	
	public Hotel(String roomNo, String name) {
		super();
		this.roomNo = roomNo;
		this.name = name;
	}
	
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
