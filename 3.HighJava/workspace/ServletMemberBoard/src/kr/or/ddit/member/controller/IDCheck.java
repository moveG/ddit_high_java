package kr.or.ddit.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

public class IDCheck extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("id");
		
		IMemberService service = MemberServiceImpl.getInstance();
		
		int cnt = service.idCheck(id);
		
		request.setAttribute("cnt", cnt);
		request.getRequestDispatcher("member/checkId.jsp").forward(request, response);
	}
}
