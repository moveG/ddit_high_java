package kr.or.ddit.member.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberUpdate extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("id");
		
		IMemberService service = MemberServiceImpl.getInstance();
		
		MemberVO vo = service.detailMember(id);
		
		request.setAttribute("vo", vo);
		
		request.getRequestDispatcher("member/memberUpdate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		/*request.setCharacterEncoding("UTF-8");*/
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		MemberVO vo = new MemberVO();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String picture = request.getParameter("picture") == ""
				? "noImage.jpg"
				: request.getParameter("picture");
		int enabled = Integer.parseInt(request.getParameter("enabled"));
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String strDate = request.getParameter("regdate");
		String authority = request.getParameter("authority");
		String register = request.getParameter("register");
		
		try {
			Date regdate = sdf.parse(strDate);
			vo.setRegdate(regdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		IMemberService service = MemberServiceImpl.getInstance();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setEmail(email);
		vo.setPicture(picture);
		vo.setEnabled(enabled);
		vo.setPhone(phone);
		vo.setAddress(address);
		vo.setAuthority(authority);
		vo.setRegister(register);
		
		int cnt = service.updateMember(vo);
		
		request.setAttribute("cnt", cnt);
		request.setAttribute("id", id);
		request.getRequestDispatcher("member/checkUpdate.jsp").forward(request, response);
	}
}
