package kr.or.ddit.basic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

import kr.or.ddit.util.JDBCUtil;
import kr.or.ddit.util.JDBCUtil2;
import kr.or.ddit.util.JDBCUtil3;

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

public class T01_MemberInfoTest {
	//Log4j를 이용하여 로그를 남기기 위해 로그 객체 생성하기
	private static final Logger SQL_LOGGER = Logger.getLogger("log4jexam.sql.Query");
	private static final Logger PARAM_LOGGER = Logger.getLogger("log4jexam.sql.Parameter");
	private static final Logger RESULT_LOGGER = Logger.getLogger(T01_MemberInfoTest.class);
	
	private Connection conn;
	private Statement stmt;
	//쿼리를 DB에 날림
	private PreparedStatement pstmt;
	//Statement의 발전형, 미리 준비된 Statement
	//쿼리의 뼈대를 만들어놓고, '?'자리에 값만 넣는 방식, SQL Injection공격 방어에 용이함
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("-----------------------");
		System.out.println("======= 작 업 선 택 =======");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("-----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
					delectMember();
					break;
				case 3 :  // 자료 수정
					updateMember();
					break;
				case 4 :  // 전체 자료 출력
					displayMemberAll();
					break;
				case 5 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요");
			}
		}while(choice!=5);
	}
	
	/**
	 * 전체 회원정보 출력
	 */
	private void displayMemberAll() {
		System.out.println();
		System.out.println("===============================================");
		System.out.println(" ID\t이름\t전화번호\t\t주소");
		System.out.println("===============================================");
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "select * from mymember ";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				System.out.println(memId + "\t" + memName + "\t" + memTel + "\t" + memAddr);
			}
			System.out.println("===============================================");
			System.out.println("출력작업 완료...");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
	}

	/**
	 * 회원정보 삭제
	 */
	private void delectMember() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("회원 ID> ");
		String memId = scan.next();
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				System.out.println(memId + "회원 삭제 작업 성공!");
			}else {
				System.out.println(memId + "회원 삭제 작업 실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}

	/**
	 * 회원정보 수정
	 */
	private void updateMember() {
		boolean chk = false;	//중복여부(존재여부) 확인
		String memId = "";
		
		do {
			System.out.println();
			System.out.println("수정할 회원 정보를 입력하세요.");
			System.out.print("회원 ID> ");
			memId = scan.next();
			
			chk = checkMember(memId);
			
			if(chk == false) {
				System.out.println("회원 ID가 " + memId + "인 회원은 존재하지 않습니다.");
				System.out.println("다른 ID를 입력해 주세요.");
			}
		}while(chk == false);
		
		System.out.print("회원 이름> ");
		String memName = scan.next();

		System.out.print("회원 전화번호> ");
		String memTel = scan.next();
		
		scan.nextLine();	//입력버퍼 비우기
		System.out.print("회원 주소> ");
		String memAddr = scan.nextLine();
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = " update mymember "
				+ " set mem_name = ? "
				+ "	   ,mem_tel = ? "
				+ "    ,mem_addr = ? "
				+ " where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				System.out.println(memId + "회원 수정 작업 성공!");
			}else {
				System.out.println(memId + "회원 수정 작업 실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}

	/**
	 * 회원정보 추가
	 */
	private void insertMember() {
		boolean chk = false;	//중복여부(존재여부) 확인
		String memId = "";
		
		do {
			System.out.println();
			System.out.println("추가할 회원 정보를 입력하세요.");
			System.out.print("회원 ID> ");
			memId = scan.next();
			
			chk = checkMember(memId);
			
			if(chk == true) {
				System.out.println("회원 ID가 " + memId + "인 회원이 이미 존재합니다.");
				System.out.println("다른 ID를 입력해 주세요.");
			}
		}while(chk == true);	//false면 중복X -> 회원등록O
		
		System.out.print("회원 이름> ");
		String memName = scan.next();

		System.out.print("회원 전화번호> ");
		String memTel = scan.next();
		
		scan.nextLine();	//입력버퍼 비우기
		System.out.print("회원 주소> ");
		String memAddr = scan.nextLine();
		
		try {
			conn = JDBCUtil.getConnection();
			String  sql = "insert into mymember "
				+ " (mem_id, mem_name, mem_tel, mem_addr) "
				+ " values (?, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			//log4j.properties의 설정(rootLogger) 로그레벨 이상의 메시지들은 모두 출력됨
			//설정이 INFO라면 INFO이상의 메시지(INFO, WARN, ERROR, FATAL)만 출력함
			PARAM_LOGGER.debug("파라미터값 : "
					+ memId + ", " + memName + ", " + memTel + ", " + memAddr);
			SQL_LOGGER.info("SQL : " + sql);
			
			int cnt = pstmt.executeUpdate();
			
			RESULT_LOGGER.warn("결과값 : " + cnt);
			
			if(cnt > 0) {
				System.out.println(memId + "회원 추가 작업 성공!");
			}else {
				System.out.println(memId + "회원 추가 작업 실패!");
			}
		} catch (SQLException e) {
			System.out.println(memId + "회원 추가 작업 실패!");
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}
	
	/**
	 * 회원ID를 이용하여 회원이 있는지 알려주는 메서드
	 * @param memId 회원ID
	 * @return 회원이 존재하면 true, 존재하지 않으면 false
	 */
	private boolean checkMember(String memId) {
		boolean chk = false;
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select count(*) as cnt from mymember "
				+ " where mem_id = ? ";
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

	public static void main(String[] args) {
		T01_MemberInfoTest memObj = new T01_MemberInfoTest();
		memObj.start();
	}
}
