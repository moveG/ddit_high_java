package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 에러(예외)처리를 위한 서블릿 예제
 */
public class T04_ErrorHandlerTest extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Throwable throwable = (Throwable) 
				req.getAttribute("javax.servlet.error.exception");		//예외객체
		//던질 수 있는 것은 모두 Throwable객체에 속함(Error, Exception)
		//Error : 우리가 처리할 수 없음
		//Exception : 처리 가능
		Integer statusCode = (Integer) 
				req.getAttribute("javax.servlet.error.status_code");	//에러상태코드
		String message = (String)
				req.getAttribute("javax.servlet.error.message");		//에러메시지
		
		//에러가 발생한 서블릿 이름
		String servletName = (String)
				req.getAttribute("javax.servlet.error.servlet_name");
		if(servletName == null) {
			servletName = "알 수 없는 서블릿 이름";
			//이름이 없을 경우 "알 수 없는 서블릿 이름"으로 초기화됨
		}
		
		//에러가 발생한 URL 정보
		String reqURI = (String)
				req.getAttribute("javax.servlet.error.request_uri");
		if(reqURI == null) {
			reqURI = "알 수 없는 URI";
			//URI가 없을 경우 "알 수 없는 URI"로 초기화됨
		}
		
		//응답메시지 설정
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String title = "Error/Exception 정보";
		
		out.println("<!DOCTYPE>"
				+ "<html>"
				+ "<head><title>" + title + "</title></head>"
				+ "<body>");
		
		if(throwable == null && statusCode == null) {
			out.println("<h2>에러정보 없음</h2>");
		}else {
			out.println("<h2>예외/에러 정보</h2>");			
			out.println("상태코드 : " + statusCode + "<br><br>");			
			out.println("에러메시지 : " + message + "<br><br>");			
			out.println("서블릿이름 : " + servletName + "<br><br>");			
			out.println("요청URI : " + reqURI + "<br><br>");
			
			if(statusCode != null) {
				out.println("예외타입 : " + throwable.getClass().getName() + "<br><br>");
				out.println("예외메시지 : " + throwable.getMessage());
			}
		}
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
