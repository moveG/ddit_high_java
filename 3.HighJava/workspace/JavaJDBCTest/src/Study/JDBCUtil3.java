package Study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * db.properties파일의 내용으로 DB정보를 설정하는 방법
 * 
 * 1. ResourceBundle객체 이용하기
 */
public class JDBCUtil3 {
	static ResourceBundle bundle;	//ResourceBundle객체 변수 선언
	
	static {
		bundle = ResourceBundle.getBundle("db");
		
		try {
			Class.forName(bundle.getString("driver"));
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
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass"));					
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
