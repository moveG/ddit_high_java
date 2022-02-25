package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;

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
	 * - getParameter() : 파라미터 값이 하나인 경우 사용됨
	 * 
	 * - getParameterValues() : 파라미터 값이 여럿인 경우 사용됨(ex) checkbox)
	 * 
	 * - getParameterNames() : request에 존재하는 모든 파라미터 정보를 가져올 때 사용됨
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//req.setCharacterEncoding("UTF-8");
		
		//getParameter로 value를 뽑아 String에 저장함
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String rlgn = req.getParameter("rlgn");
		String[] hobbys = req.getParameterValues("hobby");
		String[] foods = req.getParameterValues("food");
		
		//resp.setContentType("text/html; charset=UTF-8");
		resp.setContentType("text/html;");
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<p>이름 : " + username + "</p>");
		out.println("<p>패스워드 : " + password + "</p>");
		out.println("<p>성별 : " + gender + "</p>");
		if(hobbys != null) {
			for(String hobby : hobbys) {
				out.println("<p>취미 : " + hobby + "</p>");
			}
		}
		out.println("<p>종교 : " + rlgn + "</p>");
		if(foods != null) {
			for(int i = 0; i < foods.length; i++) {
				out.println("<p>음식 : " + foods[i] + "</p>");
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
