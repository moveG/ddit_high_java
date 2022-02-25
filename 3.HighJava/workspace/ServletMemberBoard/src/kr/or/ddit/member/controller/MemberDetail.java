package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberDetail extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("id");
		
		IMemberService service = MemberServiceImpl.getInstance();
		
		MemberVO vo = service.detailMember(id);
		String password = "";
		for(int i = 0; i < vo.getPwd().length(); i++) {
			password += "•";
		}
		vo.setPwd(password);
		
		String authority = "미등록";
		if(vo.getAuthority().equals("ROLE_MANAGER")){
			authority = "매니저";
		}else if(vo.getAuthority().equals("ROLE_USER")){
			authority = "직원";
		}else if(vo.getAuthority().equals("ROLE_ADMIN")){
			authority = "관리자";
		}
		vo.setAuthority(authority);
		
		String enabled = "미등록";
		if(vo.getEnabled() == 0){
			enabled = "퇴사";
		}else if(vo.getEnabled() == 1){
			enabled = "재직";
		}else if(vo.getEnabled() == 2){
			enabled = "휴직";
		}
		
		request.setAttribute("enabled", enabled);
		request.setAttribute("vo", vo);
		request.getRequestDispatcher("member/memberDetail.jsp").forward(request, response);
	}
}
