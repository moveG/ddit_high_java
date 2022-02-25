package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 동작방식
 * 
 * 1. 사용자(클라이언트)가 URL을 클릭하면 HTTP Request메시지를 서블릿 컨테이너로 전송(요청)함
 * 
 * 2. 컨테이너는 web.xml에 정의된 URL패턴을 확인하여 어느 서블릿을 통해 처리해야 할지를 검색함
 *    (로딩이 안된 경우에는 로딩부터 시작, 로딩시 init()메서드가 호출됨)
 * 
 * 3. 서블릿 컨테이너는 요청을 처리할 개별 스레드 객체를 생성하여 서블릿 객체의 service()메서드를 호출함
 *    (HttpServletRequest, HttpServletResponse객체를 생성하여 파라미터로 넘겨줌)
 * 
 * 4. service()메서드는 메서드 타입을 체크하여 적절한 메서드를 호출함
 *    (doGet, doPost, doPut, doDelete 등 - 웹브라우저는 doGet과 doPost만 사용가능)
 * 
 * 5. 요청처리가 완료되면 HttpServletRequest, HttpServletResponse객체는 소멸함
 * 
 * 6. 컨테이너로부터 서블릿이 제거되면 destroy()메서드가 호출됨
 */
public class T02_ServletTest extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//Post방식으로 넘어오는 Body데이터를 인코딩 처리함
		//Get방식은 톰캣의 URIEncoding설정을 이용함
		//반드시 request에서 값을 가져오기 전에 먼저 설정해야 적용됨
		req.setCharacterEncoding("UTF-8");
		
		//request로부터 name값을 가져옴
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		System.out.println("name값 : " + name + ", age값 : " + age + "살");
		
		//응답메시지 인고딩 설정(Context-Type의 charset=UTF-8과 동일함)
		resp.setCharacterEncoding("UTF-8");
		//헤더정보
		resp.setContentType("text/plain");
		//resp.setContentType("text/plain; charset=UTF-8");
		
		//실제 수행할 로직(기능)이 시작되는 부분
		PrintWriter out = resp.getWriter();
		
		out.println("이름 : " + name);
		out.println("나이 : " + age + "살");
		out.println("서블릿 경로 : " + req.getServletPath());
		out.println("컨텍스트 경로 : " + req.getContextPath());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//Post방식으로 요청된 리퀘스트를 Get방식으로 처리하게 설정함
		doGet(req, resp);
	}
}
