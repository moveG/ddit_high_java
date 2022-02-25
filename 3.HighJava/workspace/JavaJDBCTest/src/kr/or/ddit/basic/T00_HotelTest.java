package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.JDBCUtil;

/*
 * 호텔운영을 관리하는 프로그램 작성(JDBC 사용)
 * - Key값은 방번호
 */
public class T00_HotelTest {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner sc;
	
	public T00_HotelTest() {
		sc = new Scanner(System.in);
		
	}

	//프로그램 시작 메인
	public static void main(String[] args) {
		new T00_HotelTest().hotelStart();
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
			case 2: checkOut();		//체크아웃
				break;
			case 3: roomInfo();		//객실상태
				break;
			case 4: 				//호텔종료
				System.out.println("호텔 문을 닫았습니다.");
				System.exit(4);
			default:
				System.out.println("잘못 입력했습니다. 다시 입력하세요.");
			}//switch
		}//while
	}//hotelStart
	
	//객실상태
	private void roomInfo() {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM HOTEL_MNG ORDER BY ROOM_NUM ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			System.out.println("========================================================");
			while(rs.next()) {
				int roomNo = rs.getInt("room_num");
				String name = rs.getString("guest_name");
				
				System.out.println("방 번호 : " + roomNo + ", 투숙객 : " + name);
			}
			System.out.println("========================================================");
			System.out.println("객실상태를 확인했습니다.");
			System.out.println("========================================================");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}
	
	//체크아웃
	private void checkOut() {
		boolean chk = false;
		int roomNo = 0;
		
		do {
			System.out.println("어느 방을 체크아웃 하시겠습니까?");
			System.out.print("방 번호 입력>");
			roomNo = Integer.parseInt(sc.nextLine());
			
			chk = checkRoom(roomNo);	//중복확인
			
			if(chk == false) {
				System.out.println("방 번호 " + roomNo + "는 이미 체크아웃된 상태입니다.");
				System.out.println("다른 방을 선택해주세요.");
			}
		}while(chk == false);
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "DELETE FROM HOTEL_MNG WHERE ROOM_NUM = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNo);
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("체크아웃 성공!");
			}else {
				System.out.println("체크아웃 실패!");
			}
		} catch (SQLException e) {
			System.out.println("체크아웃 실패!");
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		System.out.println("========================================================");
	}
	
	//체크인
	private void checkIn() {
		boolean chk = false;
		int roomNo = 0;
		
		do {
			System.out.println("어느 방에 체크인 하시겠습니까?");
			System.out.print("방 번호 입력>");
			roomNo = Integer.parseInt(sc.nextLine());
			
			chk = checkRoom(roomNo);	//중복확인
			
			if(chk == true) {
				System.out.println("방 번호 " + roomNo + "는 이미 체크인된 상태입니다.");
				System.out.println("다른 방을 선택해주세요.");
			}
		}while(chk == true);
		
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력>");
		String name = sc.nextLine();
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "INSERT INTO HOTEL_MNG "
					+ " (ROOM_NUM, GUEST_NAME) "
					+ " VALUES (?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNo);
			pstmt.setString(2, name);
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("체크인 성공!");
			}else {
				System.out.println("체크인 실패!");
			}
		} catch (SQLException e) {
			System.out.println("체크인 실패!");
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		System.out.println("========================================================");
	}
	
	//중복확인
	private boolean checkRoom(int roomNo) {
		boolean chk = false;
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "SELECT COUNT(*) AS CNT "
					+ "     FROM HOTEL_MNG "
					+ "    WHERE ROOM_NUM = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNo);
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			while(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if(cnt > 0) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		return chk;
	}
}
