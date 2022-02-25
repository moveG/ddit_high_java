package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class T00_LoginStudy extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		
		String title = "로그인 성공!!!";
		if(session.isNew()) {
			session.setAttribute("userId", req.getParameter("userId"));
			session.setAttribute("password", req.getParameter("password"));
		}
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<!DOCTYPE html>"
				+ "<html>"
				+ "<head><title>" + title + "</title></head>"
				+ "<body>"
				+ "<h1 align=\"center\">" + title + "</h1>"
				+ "유저 아이디 : " + session.getAttribute("userId") + "<br>" 
				+ "패스워드 : " + session.getAttribute("password") + "<br>"
				+ "</body></html>");
	}
}
