package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 동작방식에 대해서...
 * 
 * 1. 사용자(클라이언트)가 URL을 클릭하면 HTTP Request 메시지를 서블릿 컨테이너로 전송(요청)한다.
 * 
 * 2. 컨테이너는 web.xml에 정의된 url패턴을 확인하여 어느 서블릿을 통해 처리해야 할지를 검색한다.
 *    (로딩이 안 된 경우에는 로딩을 함, 로딩시 init()메서드가 호출된다.)
 * 
 * 3. Servlet Container는 요청을 처리할 개별 스레드 객체를 생성하여 서블릿 객체의 service()메서드를 호출한다.
 *    (HttpServletRequest 및 HttpServletResponse 객체를 생성하여 파라미터로 넘겨준다.) 
 * 
 * 4. service()메서드는 메서드 타입을 체크하여 적절한 메서드를 호출한다.
 *    (doGet, doPost, doPut, doDelete 등) - web브라우저에서는 doGet과 doPost 두 방식만 사용할 수 있음
 * 
 * 5. 요청처리가 완료되면 HttpServletRequest 및 HttpServletResponse 객체는 소멸한다.
 * 
 * 6. 컨테이너로부터 서블릿이 제거되는 경우에 destroy()메서드가 호출된다.
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
		System.out.println("name값 : " + name);
		String age = req.getParameter("age");
		System.out.println("age값 : " + age);
		//응답메시지 인코딩 설정(Context-Type의 charset=UTF-8과 동일함)
		resp.setCharacterEncoding("UTF-8");
		//헤더정보
		//resp.setContentType("text/plain; charset=utf-8");
		resp.setContentType("text/plain");
		
		//실제 수행할 로직(기능)이 시작되는 부분...
		PrintWriter out = resp.getWriter();
		
		out.println("이름 : " + name);
		out.println("나이 : " + age);
		out.println("서블릿 경로 : " + req.getServletPath());
		out.println("컨텍스트 경로 : " + req.getContextPath());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//Post방식으로 들어온 리퀘스트를 Get방식과 같은 방식으로 처리하게 설정함
		doGet(req, resp);
	}
}
