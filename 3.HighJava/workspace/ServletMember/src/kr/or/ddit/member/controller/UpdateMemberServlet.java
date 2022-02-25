package kr.or.ddit.member.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;

import kr.or.ddit.cmm.service.AtchFileServiceImpl;
import kr.or.ddit.cmm.service.IAtchFileService;
import kr.or.ddit.cmm.vo.AtchFileVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.FileUploadRequestWrapper;

@WebServlet("/member/memUpdate.do")
public class UpdateMemberServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String memId = req.getParameter("memId");
		
		//1. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();
		
		//2. 회원정보 조회
		MemberVO mv = memService.getMember(memId);
		
		//3. 첨부파일 조회
		if(mv.getAtchFileId() > 0) {	//첨부파일 존재 확인
			//첨부파일 정보 조회
			AtchFileVO fileVO = new AtchFileVO();
			fileVO.setAtchFileId(mv.getAtchFileId());
			
			IAtchFileService fileService = AtchFileServiceImpl.getInstance();
			List<AtchFileVO> atchFileList = fileService.getAtchList(fileVO);
			
			req.setAttribute("atchFileList", atchFileList);
		}
		
		//4. request 객체에 회원정보 저장
		req.setAttribute("mv", mv);
		
		//5. View 화면으로 이동
		RequestDispatcher dispatcher 
			= req.getRequestDispatcher("/WEB-INF/views/member/memUpdate.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		AtchFileVO atchFileVO = new AtchFileVO();
		
		//1. 기존 첨부파일 아이디 정보 가져와서 설정
		long atchFileId = req.getParameter("atchFileId") == null
				? -1
				: Long.parseLong(req.getParameter("atchFileId"));
		atchFileVO.setAtchFileId(atchFileId);
		
		//2. Multipart가 맞는지 확인
		if(((FileUploadRequestWrapper)req).isMultipartContent()) {
			IAtchFileService fileService = AtchFileServiceImpl.getInstance();
			
			Map<String, FileItem[]> fileItemMap =
					((FileUploadRequestWrapper)req).getFileItemMap();
			System.out.println("파일 아이템 크기 => " + fileItemMap.size());
			
			if(fileItemMap.size() > 0) {	//0보다 크면 파일이 존재함
				try {
					atchFileVO = fileService.saveAtchList(fileItemMap);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		//3. 요청 파라미터 정보 가져오기
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		
		//4. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();
		
		//5. 회원정보 수정하기
		MemberVO mv = new MemberVO();
		
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		mv.setAtchFileId(atchFileVO.getAtchFileId());
		
		int cnt = memService.updateMember(mv);
		
		String msg = "";
		if(cnt > 0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		req.setAttribute("msg", msg);
		
		//6. 목록조회 화면으로 이동
		String redirectUrl = req.getContextPath() + "/member/memList.do?msg="
				+ URLEncoder.encode(msg, "UTF-8");
		resp.sendRedirect(redirectUrl);
	}
}
