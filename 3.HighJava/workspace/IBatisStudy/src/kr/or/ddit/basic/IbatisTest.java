package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class IbatisTest {
	/*
	 * iBatis를 이용하여 DB자료를 처리하는 작업 순서
	 * 
	 * 1. iBatis를 이용하여 DB자료를 처리하는 작업 순서
	 *    1-1. xml설정문서 읽어오기
	 *    1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체를 생성하기
	 * 
	 * 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
	 *    2-1. insert작업 연습
	 *       1) 저장할 데이터를 VO에 담는다.
	 *       2) SqlMapClient객체 변수를 이용하여 해당 쿼리문을 실행한다.
	 *          형식 : smc.insert("namespace값.id값", 파라미터클래스);
	 *          반환값 : 성공하면 null이 반환된다.
	 *    2-2. update작업 연습
	 *       1) update()메서드의 반환값은 성공한 레코드 숫자이다.
	 *       
	 *    2-3. delete작업 연습
	 *       1) delete()메서드의 반환값은 성공한 레코드 숫자이다.
	 *    
	 *    2-4. select작업 연습
	 *       1) 응답의 결과가 여러개인 경우
	 *          - 응답결과가 여러개일 경우에는 queryForList()메서드를 사용한다.
	 *          - 이 메서드는 여러개의 레코드를 VO에 담은 후 이 VO데이터를 List에 추가해주는 작업을 자동으로 수행한다.
	 *       2) 응답의 결과가 하나인 경우   
	 */
	public static void main(String[] args) {
		//iBatis를 이용하여 DB자료를 처리하는 작업 순서
		//1. iBatis의 환경설정 파일을 읽어와 실행시킨다.
		try {
			//1-1. xml설정문서 읽어오기
			//설정파일의 인코딩 설정
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");

			//1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체를 생성하기
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			//smc를 만들기 위해서 위의 작업을 수행함
			
			//Reader객체 닫기
			rd.close();
			
			//2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
			//2-1. insert작업 연습
			System.out.println("insert작업 시작...");
			
			//1) 저장할 데이터를 VO에 담는다.
			MemberVO mv = new MemberVO();
			mv.setMemId("a006");
			mv.setMemName("최순신");
			mv.setMemTel("010-0000-0000");
			mv.setMemAddr("대전시 중구 대흥동");
			
			//2) SqlMapClient객체 변수를 이용하여 해당 쿼리문을 실행한다.
			//형식 : smc.insert("namespace값.id값", 파라미터클래스);
			//반환값 : 성공하면 null이 반환된다.
//			Object obj = smc.insert("memberTest.insertMember", mv);
//			//insert 파라미터 1개 : 쿼리만 실행하면 될 때 사용
//			//insert 파라미터 2개 : 쿼리에 내용을 넣을 때 사용
//			
//			if(obj == null) {
//				System.out.println("insert작업 성공!");
//			}else {
//				System.out.println("insert작업 실패!");
//			}
//			System.out.println("================================");
//			
//			//update로 insert를 실행할 수 있음
//			//성공한 레코드 숫자를 반환함
//			int cnt = smc.update("memberTest.insertMember", mv);
//			
//			if(cnt > 0) {
//				System.out.println("insert작업 성공!");
//			}else {
//				System.out.println("insert작업 실패!");
//			}
//			System.out.println("================================");
			
			//2-2. update작업 연습
			System.out.println("update작업 시작...");
			MemberVO mv2 = new MemberVO();
			mv2.setMemId("a006");
			mv2.setMemName("조명석");
			mv2.setMemTel("010-1111-1111");
			mv2.setMemAddr("대전시 중구 대흥동");
			
			//1) update()메서드의 반환값은 성공한 레코드 숫자이다.
			int cnt = smc.delete("memberTest.updateMember", mv2);
			
			if(cnt > 0) {
				System.out.println("update작업 성공!");
			}else {
				System.out.println("update작업 실패!");
			}
			System.out.println("================================");
			
			//2-3. delete작업 연습
			System.out.println("delete작업 시작...");
			
			//1) delete()메서드의 반환값은 성공한 레코드 숫자이다.
			cnt = smc.delete("memberTest.deleteMember", "a006");
			
			if(cnt > 0) {
				System.out.println("delete작업 성공!");
			}else {
				System.out.println("delete작업 실패!");
			}
			System.out.println("================================");
			
			//2-4. select작업 연습
			//1) 응답의 결과가 여러개인 경우
			System.out.println("select작업 시작(응답의 결과가 여러개인 경우)...");
			
			//응답결과가 여러개일 경우에는 queryForList()메서드를 사용한다.
			//이 메서드는 여러개의 레코드를 VO에 담은 후 이 VO데이터를 List에 추가해주는 작업을 자동으로 수행한다.
			List<MemberVO> memList = 
					smc.queryForList("memberTest.selectAllMember");
			
			for(MemberVO memVO : memList) {
				System.out.println("ID : " + memVO.getMemId());
				System.out.println("이름 : " + memVO.getMemName());
				System.out.println("전화 : " + memVO.getMemTel());
				System.out.println("주소 : " + memVO.getMemAddr());
				System.out.println("-------------------------------");
			}
			System.out.println("출력 완료...");
			System.out.println("================================");
			
			//2) 응답의 결과가 하나인 경우   
			System.out.println("select작업 시작(응답의 결과가 하나인 경우)...");
			System.out.println("================================");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
