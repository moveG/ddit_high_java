package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.JDBCUtil;

/*
 * 회원정보를 관리하는 프로그램을 작성하는데 
 * 아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
 * (DB의 MYMEMBER테이블을 이용하여 작업한다.)
 * 
 * - 자료 삭제는 회원ID를 입력 받아서 삭제한다.
 * 
 * - 예시메뉴)
 *	----------------------
 *		== 작업 선택 ==
 *		1. 자료 입력			---> insert
 *		2. 자료 삭제			---> delete
 *		3. 자료 수정			---> update
 *		4. 전체 자료 출력	    ---> select
 *		5. 작업 끝.
 *	----------------------
 * 
 * - 회원관리 프로그램 테이블 생성 스크립트 
 * create table mymember(
 * mem_id varchar2(8) not null,  -- 회원ID
 * mem_name varchar2(100) not null, -- 이름
 * mem_tel varchar2(50) not null, -- 전화번호
 * mem_addr varchar2(128),    -- 주소
 * CONSTRAINT MYMEMBER_PK PRIMARY KEY (mem_id)
 * );
 */
public class T00_MemberTest {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner sc = new Scanner(System.in);
	
	/*
	 * 시작
	 */
	public static void main(String[] args) throws InterruptedException {
		T00_MemberTest mem = new T00_MemberTest();
		mem.start();
	}
	
	/*
	 * 메뉴 출력
	 */
	private void displayMenu() {
		System.out.println("==================");
		System.out.println("===== 작업 선택 =====");
		System.out.println("  1. 회원정보 입력");
		System.out.println("  2. 회원정보 삭제");
		System.out.println("  3. 회원정보 수정");
		System.out.println("  4. 전체자료 출력");
		System.out.println("  5. 작업 종료");
		System.out.println("==================");
		System.out.print("원하는 작업 선택 >");
	}
	
	/*
	 * 메인
	 */
	private void start() throws InterruptedException {
		int choice;
		while(true) {
			displayMenu(); //메뉴 출력
			
			choice = Integer.parseInt(sc.nextLine());	//작업 선택
			switch(choice) {
			case 1:	//회원정보 입력
				insertMember();
				break;
			case 2:	//회원정보 삭제
				deleteMember();
				break;
			case 3:	//회원정보 수정
				updateMember();
				break;
			case 4:	//전체자료 출력
				displayMemberAll();
				break;
			case 5:	//작업 종료
				System.out.println("작업을 종료합니다.");
				System.exit(5);
			default :
				System.out.println("번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요.");
			}
		}		
	}
	
	/*
	 * 전체자료 출력
	 */
	private void displayMemberAll() throws InterruptedException {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM MYMEMBER ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			System.out.println();
			System.out.println("===============================================");
			System.out.println(" ID\t이름\t전화번호\t\t주소");
			System.out.println("===============================================");
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				System.out.println(memId + "\t" + memName + "\t"
								 + memTel + "\t" + memAddr);
			}
			System.out.println("===============================================");
			System.out.println("출력작업 완료...");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}

	/*
	 * 회원정보 수정
	 */
	private void updateMember() {
		boolean chk = false;
		String memId = "";
		
		do {
			System.out.println();
			System.out.println("수정할 회원정보를 입력하세요.");
			System.out.print("회원 ID >");
			memId = sc.nextLine();
			
			chk = checkMember(memId);
			
			if(chk == false) {
				System.out.println("회원 ID가 " + memId + "인 회원은 존재하지 않습니다.");
				System.out.println("다른 ID를 입력해주세요.");
			}
		}while(chk == false);
		
		System.out.print("회원 이름 >");
		String memName = sc.nextLine();
		System.out.print("회원 전화번호 >");
		String memTel = sc.nextLine();
		System.out.print("회원 주소 >");
		String memAddr = sc.nextLine();
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "UPDATE MYMEMBER "
					+ " 	 SET MEM_NAME = ? "
					+ "		   , MEM_TEL = ? "
					+ " 	   , MEM_ADDR = ? "
					+ "    WHERE MEM_ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + " 정보수정 완료!");
			}else {
				System.out.println(memId + " 정보수정 실패!");
			}
		} catch (SQLException e) {
			System.out.println(memId + " 정보수정 실패!");
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}

	/*
	 * 회원정보 삭제
	 */
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원정보를 입력하세요.");
		System.out.print("회원 ID >");
		String memId = sc.nextLine();
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + " 회원탈퇴 완료!");
			}else {
				System.out.println(memId + " 회원탈퇴 실패!");
			}
		} catch (SQLException e) {
			System.out.println(memId + " 회원탈퇴 실패!");
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}

	/*
	 * 회원정보 입력
	 */
	private void insertMember() {
		boolean chk = false;	//중복여부(회원ID 존재여부) 확인
		String memId = "";
		
		do {
			System.out.println();
			System.out.println("추가할 회원정보를 입력하세요.");
			System.out.print("회원 ID >");
			memId = sc.nextLine();
			
			chk = checkMember(memId);	//중복여부(회원ID 존재여부) 확인
			
			if(chk == true) {
				System.out.println("회원 ID가 " + memId + "인 회원이 이미 존재합니다.");
				System.out.println("다른 ID를 입력해주세요.");
			}
		}while(chk == true);	//chk == false라면 중복X => 회원등록 가능O
		
		System.out.print("회원 이름 >");
		String memName = sc.nextLine();
		System.out.print("회원 전화번호 >");
		String memTel = sc.nextLine();
		System.out.print("회원 주소 >");
		String memAddr = sc.nextLine();
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "INSERT INTO MYMEMBER "
				+ " (MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR) "
				+ " VALUES (?, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + " 회원가입 완료!");
			}else {
				System.out.println(memId + " 회원가입 실패!");
			}
		} catch (SQLException e) {
			System.out.println(memId + " 회원가입 실패!");
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}
	
	/*
	 * 중복여부(회원ID 존재여부) 확인
	 * @param memId 회원ID
	 * @return 회원이 존재하면 true, 존재하지 않으면 false
	 */
	private boolean checkMember(String memId) {
		boolean chk = false;
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "SELECT COUNT(*) AS CNT "
					    + " FROM MYMEMBER "
					   + " WHERE MEM_ID = ? ";	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
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
