package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 쿠키정보를 다루기 위한 예제
 * 
 * 1. 웹서버와 브라우저는 애플리케이션이 사용되는 동안 필요한 값을 쿠키를 통해 공유하여 상태를 유지한다.
 * 
 * 2. 구성요소
 *  - 이름
 *  - 값
 *  - 유효시간(초)
 *  - 도메인 : ex) www.somehost.com 또는 .somehost.com
 *    쿠키의 도메인이 쿠키를 생성한 서버의 도메인을 벗어나면(동일 도메인이 아니면) 브라우저는 쿠키를
 *    저장(생성)하지 않는다.(보안 때문에)
 *  - 경로 : 쿠키를 공유할 기본경로를 지정한다.(도메인 이후 부분으로 디렉토리 수준)
 *    지정하지 않으면 실행한 URL의 경로부분이 사용된다.
 * 
 * 3. 동작방식
 *  - 쿠키 생성단계 : 생성한 쿠키를 응답데이터의 헤더에 저장하여 웹브라우저에 저장한다.
 *  - 쿠키 저장단계 : 웹브라우저는 응답데이터에 포함된 쿠키를 쿠키저장소에 보관한다.
 *  - 쿠키 전송단계 : 웹브라우저는 저장한 쿠키를 요청이 있을 때마다 웹서버에 전송하고,(삭제되기 전까지)
 *    웹서버는 브라우저가 전송한 쿠키를 사용해서 필요한 작업을 수행한다.
 */
public class T05_ServletCookieTest extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//setCookieExam(req, resp);	//쿠키정보 설정 예제
		//readCookieExam(req, resp);	//쿠키정보 읽기 예제
		deleteCookieExam(req, resp);	//쿠키정보 삭제 예제
	}
	
	/*
	 * 쿠키정보 삭제 방법
	 * 
	 * 1. 사용 중인 쿠키 정보를 이용하여 쿠키 객체를 생성한다.
	 * 
	 * 2. 쿠키 객체의 최대 지속시간을 0으로 설정한다.
	 * 
	 * 3. 설정한 쿠키 객체를 응답헤더에 추가하여 전송한다.
	 */
	private void deleteCookieExam(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException {
		Cookie[] cookies = req.getCookies();
		
		//응답헤더에 인코딩 및 Content-type 설정
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		String title = "쿠키정보 삭제 공부";
		
		out.println("<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<title>" + title + "</title>"
				+ "</head>"
				+ "<body>");
		if(cookies != null) {
			out.println("<h2>" + title + "</h2>");
			
//			for(Cookie cookie : cookies) {
//				if((cookie.getName()).equals("name")) {
//					//쿠키 제거
//					cookie.setMaxAge(0);
//					resp.addCookie(cookie);
//					out.println("삭제한 쿠키 : " + cookie.getName() + "<br>");
//				}
//				out.println("쿠키 이름 : " + cookie.getName() + "<br>");
//				out.println("쿠키 값 : " 
//						+ URLDecoder.decode(cookie.getValue(), "UTF-8") + "<br><br>");
//			}
			
			for(Cookie cookie : cookies) {
				cookie.setMaxAge(0);
				resp.addCookie(cookie);
				out.println("삭제한 쿠키 : " + cookie.getName() + "<br>");

				out.println("쿠키 이름 : " + cookie.getName() + "<br>"
						  + "쿠키 값 : " + URLDecoder.decode(cookie.getValue(), "UTF-8") 
						  + "<br><br>");
			}
		}else {
			out.println("<h2>쿠키 정보가 없습니다.</h2>");
		}
		out.println("</body>");
		out.println("</html>");
	}

	/*
	 * 쿠키정보 읽는 방법 
	 */
	private void readCookieExam(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException {
		Cookie[] cookies = req.getCookies();
		
		//응답헤더에 인코딩 및 Content-type 설정
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		String title = "쿠키정보 읽기 공부";
		
		out.println("<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<title>" + title + "</title>"
				+ "</head>"
				+ "<body>");
		if(cookies != null) {
			out.println("<h2>" + title + "</h2>");
			
			for(Cookie cookie : cookies) {
				out.println("name : " + cookie.getName() + "<br>");
				out.println("value : "
						+ URLDecoder.decode(cookie.getValue(), "UTF-8")
						+ "<br>");
				out.println("<hr>");
			}
		}else {
			out.println("<h2>쿠키 정보가 없습니다.</h2>");
		}
		out.println("</body>");
		out.println("</html>");
	}

	/*
	 * 쿠키정보 설정 방법
	 * 
	 * 1. 쿠키 객체를 생성한다.
	 *  - Cookie cookie = new Cookie("key값", "value값");
	 *  - 쿠키값은 사용불가 문자(공백, []()=,"/?@:;)를 제외한 나머지 출력 가능한 아스키 문자를 사용할 수 있다.
	 *  - 영어 이외의 문자(ex)한글) 사용 시에는 URLEncoder.encode()를 사용하여 인코딩 처리를 하면 된다.
	 * 
	 * 2. 쿠키 최대 지속시간(초 단위)을 설정한다.
	 *  - cookie.setMaxAge(60*60*24);	//25시간
	 *  - 지정하지 않으면 웹브라우저 종료와 함께 쿠키도 삭제된다.
	 * 
	 * 3. 응답헤더에 쿠키 객체를 추가한다.
	 *  - response.addCookie(cookie);
	 * 
	 * 4. 출력버퍼가 플러시된 이후에는 쿠키를 추가할 수 없다.
	 *  - 응답헤더를 통해서 브라우저에 전달되기 때문에
	 */
	private void setCookieExam(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException {
		//쿠키 객체 생성
		Cookie userId = new Cookie("userId", req.getParameter("userId"));
		Cookie password = new Cookie("password", req.getParameter("password"));
		//한글 인코딩 처리
		Cookie name = new Cookie("name", 
				URLEncoder.encode(req.getParameter("name"), "UTF-8"));
		
		//쿠키 소멸시간(초 단위) 설정 : 지정하지 않으면 웹브라우저 종료와 함께 쿠키도 삭제된다.
		userId.setMaxAge(60*60*24);		//1일
		//JavaScript를 이용한 직접 접근방지(XSS공격 방지)
		userId.setHttpOnly(true);
		
		password.setMaxAge(60*60*24);	//2일
		name.setMaxAge(60*60*48);		//2일
		
		//응답헤더에 쿠키 추가
		resp.addCookie(userId);
		resp.addCookie(name);
		resp.addCookie(password);
		
		//응답헤더에 인코딩 및 Content-type 설정
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		String title = "쿠키정보 설정 공부";
		
		out.println("<!DOCUMENT html>"
				+ "<html>"
				+ "<head>"
				+ "<title>" + title + "</title>"
				+ "</head>"
				+ "<body>"
				+ "<h1 align=\"center\">" + title + "</h1>"
				+ "<ul>"
				+ "<li><b>아이디</b> : "
				+ req.getParameter("userId") + "</li>"
				+ "<li><b>이름</b> : "
				+ req.getParameter("name") + "</li>"
				+ "<li><b>비밀번호</b> : "
				+ req.getParameter("password") + "</li>"
				+ "</ul>"
				+ "</body>"
				+ "</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
