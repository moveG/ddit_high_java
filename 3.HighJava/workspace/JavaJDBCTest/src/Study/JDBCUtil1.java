package Study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil1 {
	/*
	 * static블럭은 JDBCUtil1클래스가 로딩될 때 한번만 실행됨
	 */
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공!");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!");
			e.printStackTrace();
		}
	}
	
	/*
	 * 커넥션(Connection) 객체 생성
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "LDG89", "java");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패!");
			e.printStackTrace();
			return null;
		}	
	}
	
	/*
	 * 자원 반납
	 */
	public static void disConnect(
			Connection conn,
			Statement stmt,
			PreparedStatement pstmt,
			ResultSet rs) {
		if(conn != null) try {conn.close();} catch (SQLException e) {}
		if(stmt != null) try {stmt.close();} catch (SQLException e) {}
		if(pstmt != null) try {pstmt.close();} catch (SQLException e) {}
		if(rs != null) try {rs.close();} catch (SQLException e) {}
	}
}
