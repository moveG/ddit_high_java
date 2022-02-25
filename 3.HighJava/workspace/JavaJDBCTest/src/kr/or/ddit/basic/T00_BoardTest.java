package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import kr.or.ddit.util.JDBCUtil;

/**
 * 게시판을 관리하는 다음 기능들을 구현하시오.
 * 
 * 기능 : 전체목록 출력, 새글작성, 수정, 삭제, 검색 
 * 
 * 시퀀스의 다음값 구하기
 * 시퀀스이름.nextVal
 */
public class T00_BoardTest {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
	private int no;
	
	private Scanner sc = new Scanner(System.in);
	
	/*
	 * 시작
	 */
	public static void main(String[] args) {
		T00_BoardTest board = new T00_BoardTest();
		board.start();
	}

	/*
	 * 메뉴 출력
	 */
	private void displayMenu() {
		System.out.println("====================================================");
		System.out.println("1.게시글 작성\t2.게시글 읽기\t3.게시글 검색\t4.작업 종료");
		System.out.println("====================================================");
		System.out.print("원하는 작업 선택 >");
	}
	
	/*
	 * 메인
	 */
	private void start() {
		while(true) {
			displayList();	//목록 출력
			displayMenu();	//메뉴 출력
			
			int choice = Integer.parseInt(sc.nextLine());	//작업 선택
			switch(choice) {
			case 1:	//게시글 작성
				write();
				break;
			case 2:	//게시글 읽기
				read();
				break;
			case 3:	//게시글 검색
				search();
				break;
			case 4:	//작업 종료
				System.out.println("작업을 종료합니다.");
				System.exit(3);
			default :
				System.out.println("번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요.");
			}
		}
	}
	
	/*
	 * 게시글 검색
	 */
	private void search() {
		/*
		 * 검색할 회원ID, 회원이름, 전화번호, 주소 등을 입력하면
		 * 입력한 정보만 사용하여 검색하는 기능을 구현하시오.
		 * 
		 * - 주소는 입력한 값이 포함만 되어도 검색되도록 한다.
		 * - 입력을 하지 않을 자료는 엔터키로 다음 입력으로 넘긴다.
		 */
		System.out.println();
		System.out.println("검색할 정보를 입력하세요.");
		System.out.print("게시글 번호 >");
		String no = sc.nextLine();
		System.out.print("게시글 제목 >");
		String title = sc.nextLine();
		System.out.print("게시글 내용 >");
		String content = sc.nextLine();
		System.out.print("게시글 작성자 >");
		String writer = sc.nextLine();
		
		while(true) {
			try {
				conn = JDBCUtil.getConnection();
				String sql = "SELECT * "
						+ "     FROM JDBC_BOARD "
						+ "    WHERE 1 = 1 ";
				if(no != null && !no.equals("")) {
					sql += " AND BOARD_NO = ? ";
				}
				if(title != null && !title.equals("")) {
					sql += " AND BOARD_TITLE LIKE '%' || ? || '%' ";
				}
				if(content != null && !content.equals("")) {
					sql += " AND BOARD_CONTENT LIKE '%' || ? || '%' ";
				}
				if(writer != null && !writer.equals("")) {
					sql += " AND BOARD_WRITER = ? ";
				}
				pstmt = conn.prepareStatement(sql);
				
				int index = 1;
				
				if(no != null && !no.equals("")) {
					pstmt.setString(index++, no);
				}
				if(title != null && !title.equals("")) {
					pstmt.setString(index++, title);
				}
				if(content != null && !content.equals("")) {
					pstmt.setString(index++, content);
				}
				if(writer != null && !writer.equals("")) {
					pstmt.setString(index++, writer);
				}
				
				rs = pstmt.executeQuery();
				
				System.out.println();
				System.out.println("====================================================");
				System.out.println("=========== 대          덕          게          시          판 ============");
				System.out.println("====================================================");
				System.out.println(" 번호\t제목\t\t작성자\t작성일");
				System.out.println("====================================================");
				while(rs.next()) {
					String no2 = rs.getString("board_no");
					String title2 = rs.getString("board_title");
					String writer2 = rs.getString("board_writer");
					Object date2 = rs.getObject("board_date");
					
					if(Integer.parseInt(no2) > 9) {
						System.out.println(" " + no2 + "\t" + title2 + "\t\t"
								+ writer2 + "\t" + sdf.format(date2));
					}else {
						System.out.println("  " + no2 + "\t" + title2 + "\t\t"
								+ writer2 + "\t" + sdf.format(date2));
					}
					System.out.println("----------------------------------------------------");
				}
				System.out.println("====================================================");
				System.out.println("검색작업 완료...");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disConnect(conn, stmt, pstmt, rs);
			}
			System.out.println("====================================================");
			System.out.println("1.게시글 작성\t2.게시글 읽기\t3.뒤로 가기\t4.작업 종료");
			System.out.println("====================================================");
			System.out.print("원하는 작업 선택 >");
			int choice = Integer.parseInt(sc.nextLine());	//작업 선택
			switch(choice) {
			case 1:	//게시글 작성
				write();
				break;
			case 2:	//게시글 읽기
				read();
				break;
			case 3:	//목록으로
				start();
				break;
			case 4:	//작업 종료
				System.out.println("작업을 종료합니다.");
				System.exit(3);
			default :
				System.out.println("번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요.");
			}
		}
	}

	/*
	 * 게시글 읽기
	 */
	private void read() {
		System.out.println();
		System.out.println("게시글 번호를 입력하세요.");
		System.out.print("번호 >");
		no = Integer.parseInt(sc.nextLine());
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM JDBC_BOARD WHERE BOARD_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bNo = rs.getInt("board_no");
				String title = rs.getString("board_title");
				String content = rs.getString("board_content");
				String writer = rs.getString("board_writer");
				String date = rs.getString("board_date");
			
				System.out.println();
				System.out.println("====================================================");
				System.out.println(bNo + "번 게시물");
				System.out.println("제목 : " + title);
				System.out.println("====================================================");
				System.out.println("내용 : ");
				System.out.println(content);
				System.out.println();
				System.out.println("====================================================");
				System.out.println("작성자 : " + writer);
				System.out.println("작성일 : " + date);
				System.out.println("====================================================");
				System.out.println("1.게시글 수정\t2.게시글 삭제\t3.뒤로 가기");
				System.out.println("====================================================");
				System.out.print("원하는 작업 선택 >");
			}
			int choice = Integer.parseInt(sc.nextLine());	//작업 선택
			switch(choice) {
			case 1:	//게시글 수정
				update();
				break;
			case 2:	//게시글 삭제
				delete();
				break;
			case 3:	//뒤로 가기
				break;
			default :
				System.out.println("번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요.");	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}

	/*
	 * 게시글 삭제
	 */
	private void delete() {
		System.out.println();
		System.out.println("정말로 삭제하시겠습니까?");
		System.out.println("1.예\t2.아니오");
		System.out.print("원하는 작업 선택 >");
		int no2 = Integer.parseInt(sc.nextLine());
		switch(no2) {
		case 1:	//게시물 삭제
			try {
				conn = JDBCUtil.getConnection();
				String sql = "DELETE FROM JDBC_BOARD WHERE BOARD_NO = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				int cnt = pstmt.executeUpdate();
				
				if(cnt > 0) {
					System.out.println("게시글 삭제 완료");
				}else {
					System.out.println("게시글 삭제 실패");
				}
			} catch (SQLException e) {
				System.out.println("게시글 삭제 실패");
				e.printStackTrace();
			} finally {
				JDBCUtil.disConnect(conn, stmt, pstmt, rs);
			}
			break;
		case 2:	//뒤로 가기
			break;
		default :
			System.out.println("번호를 잘못 입력했습니다.");
			System.out.println("다시 입력하세요.");	
		}
	}

	/*
	 * 게시글 수정
	 */
	private void update() {
		System.out.println();
		System.out.print("제목 >");
		String title = sc.nextLine();
		System.out.print("내용 >");
		String content = sc.nextLine();
	
		try {
			conn = JDBCUtil.getConnection();
			String sql = "UPDATE JDBC_BOARD "
					+ " 	 SET BOARD_TITLE = ? "
					+ "		   , BOARD_CONTENT = ? "
					+ " 	   , BOARD_DATE = SYSDATE "
					+ "    WHERE BOARD_NO = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, no);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("게시글 수정 완료");
			}else {
				System.out.println("게시글 수정 실패");
			}
		} catch (SQLException e) {
			System.out.println("게시글 수정 실패");
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}

	/*
	 * 게시글 작성
	 */
	private void write() {
		System.out.println();
		System.out.print("제목>");
		String title = sc.nextLine();
		System.out.print("내용>");
		String content = sc.nextLine();
		System.out.print("작성자>");
		String writer = sc.nextLine();
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "INSERT INTO JDBC_BOARD "
					+ " (BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT) "
					+ " VALUES (BOARD_SEQ.nextVal, ?, ?, SYSDATE, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("게시글 작성 성공");
			}else {
				System.out.println("게시글 작성 실패");
			}
		} catch (SQLException e) {
			System.out.println("게시글 작성 실패");
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}
	
	/*
	 * 목록 
	 */
	private void displayList() {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM JDBC_BOARD ORDER BY BOARD_NO DESC ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			System.out.println();
			System.out.println("====================================================");
			System.out.println("=========== 대          덕          게          시          판 ============");
			System.out.println("====================================================");
			System.out.println(" 번호\t제목\t\t작성자\t작성일");
			System.out.println("====================================================");
			while(rs.next()) {
				int no = rs.getInt("board_no");
				String title = rs.getString("board_title");
				String writer = rs.getString("board_writer");
				Object date = rs.getObject("board_date");
				
				if(no > 9) {
					System.out.println(" " + no + "\t" + title + "\t\t"
							+ writer + "\t" + sdf.format(date));
				}else {
					System.out.println("  " + no + "\t" + title + "\t\t"
							+ writer + "\t" + sdf.format(date));
				}
				System.out.println("----------------------------------------------------");
			}
			System.out.println("====================================================");
			System.out.println("출력작업 완료...");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}
}
