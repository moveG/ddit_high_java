package kr.or.ddit.member.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

public class MemberDelete extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("id");
		
		IMemberService service = MemberServiceImpl.getInstance();
		
		int cnt = service.deleteMember(id);
		
		request.setAttribute("cnt", cnt);
		request.setAttribute("id", id);
		request.getRequestDispatcher("member/checkDelete.jsp").forward(request, response);
	}
}
