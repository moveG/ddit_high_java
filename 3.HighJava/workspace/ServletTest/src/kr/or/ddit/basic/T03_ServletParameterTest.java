package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿을 이용한 파라미터 데이터 처리 예제
 */
public class T03_ServletParameterTest extends HttpServlet {
	/*
	 * 요청 객체로부터 파라미터 데이터를 가져오는 방법
	 * 
	 * - getParameter() : 파라미터 값이 한 개인 경우에 사용된다.
	 * 
	 * - getParameterValues() : 파라미터 값이 여러 개인 경우에 사용된다.
	 *   ex) checkbox
	 * 
	 * - getParameterNames() : request에 존재하는 모든 파라미터 정보를 가져올 때 사용된다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//req.setCharacterEncoding("UTF-8");
		
		//getParameter로 value를 뽑아 String에 저장함
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		//String hobby = req.getParameter("hobby");
		String[] hobbys = req.getParameterValues("hobby");
		String rlgn = req. getParameter("rlgn");
		String[] foods = req.getParameterValues("food");
		//resp.setCharacterEncoding("utf-8");
		//resp.setContentType("text/html; charset=utf-8");
		resp.setContentType("text/html;");
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<p>username : " + username + "</p>");
		out.println("<p>password : " + password + "</p>");
		out.println("<p>gender : " + gender + "</p>");
		//out.println("<p>hobby : " + hobby + "</p>");
		if(hobbys != null) {
			for(String hobby : hobbys) {
				out.println("<p>hobby : " + hobby + "</p>");
			}
		}
		out.println("<p>rlgn : " + rlgn + "</p>");
		if(foods != null) {
			for(String food : foods) {
				out.println("<p>food : " + food + "</p>");
			}
		}
		
		Enumeration<String> params = req.getParameterNames();
		while(params.hasMoreElements()) {
			String param = params.nextElement();
			out.println("<p>파리미터 이름 : " + param + "</p>");
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
