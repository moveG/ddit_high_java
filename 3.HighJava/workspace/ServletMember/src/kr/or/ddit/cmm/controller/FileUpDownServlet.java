package kr.or.ddit.cmm.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.cmm.service.AtchFileServiceImpl;
import kr.or.ddit.cmm.service.IAtchFileService;
import kr.or.ddit.cmm.vo.AtchFileVO;

@WebServlet("/fileupdown.do")
public class FileUpDownServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		long fileId = req.getParameter("fileId") != null
				? Long.parseLong(req.getParameter("fileId"))
				: -1;
		int fileSn = req.getParameter("fileSn") != null
				? Integer.parseInt(req.getParameter("fileSn"))
				: -1;
		
		//파일정보 조회
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		AtchFileVO fileVO = new AtchFileVO();
		fileVO.setAtchFileId(fileId);
		fileVO.setFileSn(fileSn);
		
		fileVO = fileService.getAtchDetail(fileVO);
		
		//파일 다운로드 처리를 위한 응답헤더 정보 설정
		resp.setContentType("application/octet-stream");	//바이너리 데이터라는 의미
		
		//'+'문자 공백처리
		//Content-Disposition : Wrapper할 때 사용
		//attachment를 사용하지 않으면 default값은 inline으로 설정되어 화면에 출력됨
		resp.setHeader("Content-Disposition", "attachment; filename=\"" 
				+ URLEncoder.encode(fileVO.getOrignlFileNm(), "UTF-8")
				.replaceAll("\\+", "%20") + "\"");
		//원래 파일명(임시이름이 아닌...)으로 다운로드 받을 수 있도록 설정
		//파일명 사이의 플러스(+)를 스페이스(%20)로 변환
		
		BufferedInputStream bis = 
				new BufferedInputStream(
						new FileInputStream(fileVO.getFileStreCours()));		
		BufferedOutputStream bos = 
				new BufferedOutputStream(resp.getOutputStream());
			
		int c = -1;
		while((c = bis.read()) != -1) {
			bos.write(c);
		}
		bis.close();
		bos.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
