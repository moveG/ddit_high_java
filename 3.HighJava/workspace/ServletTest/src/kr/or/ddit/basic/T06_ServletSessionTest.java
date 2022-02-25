package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 1. 세션(HttpSession) 객체에 대해서...
 *  - 세션을 통해서 사용자(브라우저)별로 구분하여 정보를 관리할 수 있다.(세션ID 이용)
 *  - 쿠키를 사용할 때보다 보안이 향상된다.(정보가 서버에 저장되므로)
 *  - 사용자 별로 1개씩 만들어진다.
 * 
 * 2. 세션 객체를 가져오는 방법
 *  - HttpSession session = request.getSession(boolean값);
 *  - boolean값 : true인 경우 - 세션객체가 존재하지 않으면 새로 생성한다.
 *               false인 경우 - 세션객체가 존재하지 않으면 null을 리턴한다.
 *               
 * 3. 세션 삭제 방법
 *  - invalidate() 메서드 호출
 *  - setMaxInactiveInterval(int interval) 호출
 *    (일정시간(초) 동안 요청이 없으면 세션 객체가 삭제된다.)
 *  - web.xml에 <session-config> 설정하기(분 단위)
 *    (개별 세션이 아닌, 모든 세션에 공통적으로 적용된다.)
 *    <session-config>
 *       <session-timeout>시간</session-timeout>
 *    </session-config>
 *    
 * 4. 브라우저가 종료되면 세션이 종료된다.
 *    (브라우저를 새로 실행하면 새로운 세션이 생성된다.)
 */
public class T06_ServletSessionTest extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//세션을 가져오는데 없으면 새로 생성한다.
		HttpSession session = req.getSession(true);
		
		//생성시간 가져오기
		Date createTime = new Date(session.getCreationTime());
		
		//마지막 접근시간 가져오기
		Date lastAccessTime = new Date(session.getLastAccessedTime());
		
		String title = "재 방문을 환영합니다.";
		int visitCount = 0;		//방문 횟수
		String userId = "lee";	//사용자 ID
		
		if(session.isNew()) {	//세션이 새로 만들어졌는지 확인
			title = "첫 방문을 환영합니다.";
			session.setAttribute("userId", userId);
		}else {
			visitCount = (Integer) session.getAttribute("visitCount");
			visitCount++;
			userId = (String) session.getAttribute("userId");
		}
		System.out.println("visitCount : " + visitCount);
		session.setAttribute("visitCount", visitCount);
		
		//세션 삭제 : 삭제 시 새로고침을 해도 방문횟수가 누적되지 않고, 세션ID가 계속 바뀐다.
		//방법1 : 세션이 삭제된다.
		//session.invalidate();
		//방법2 : 설정한 시간(10초) 동안 세션이 유지된다.
		//session.setMaxInactiveInterval(10);
		//방법3 : web.xml에 <session-config> 설정하기(분 단위)
		
		//응답헤더에 인코딩 및 content-type 설정하기
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<!DOCTYPE html>"
				+ "<html>"
				+ "<head><title>" + title + "</title></head>"
				+ "<body>"
				+ "<h1 align=\"center\">" + title + "</h1>"
				+ "<h2 align=\"center\">세션 정보</h2>"
				+ "<table border=\"1\" align=\"center\">"
				+ "<tr bgcolor=\"orange\"><th>구분</th><th>값</th></tr>"
				+ "<tr><td>세션ID</td><td>" + session.getId() + "</td></tr>"
				+ "<tr><td>생성시간</td><td>" + createTime + "</td></tr>"
				+ "<tr><td>마지막 접근시간</td><td>" + lastAccessTime + "</td></tr>"
				+ "<tr><td>userId</td><td>" + userId + "</td></tr>"
				+ "<tr><td>방문횟수</td><td>" + visitCount + "</td></tr>"
				+ "</table></body></html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
