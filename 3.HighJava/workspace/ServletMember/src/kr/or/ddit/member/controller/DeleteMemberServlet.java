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

@WebServlet("/member/memDelete.do")
public class DeleteMemberServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String memId = req.getParameter("memId");
		
		//1. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();
		
		//2. 회원정보 삭제
		int cnt = memService.deleteMember(memId);
		
		String msg = "";
		if(cnt > 0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		//3. 목록조회 화면으로 이동
		String redirectUrl = req.getContextPath() + "/member/memList.do?msg=" 
				+ URLEncoder.encode(msg, "UTF-8");
		resp.sendRedirect(redirectUrl);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
