package kr.or.ddit.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload2")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
					   maxFileSize = 1024 * 1024 * 5,
				    maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadServlet2 extends HttpServlet {
	private static final String UPLOAD_DIR = "upload_files";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIR;
		
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		try {
			String fileName = "";
			
			for(Part part : req.getParts()) {
				//part객체에서 FileName을 가져옮
				fileName = getFileName(part);
				
				//폼필드가 아니거나(파일이라는 의미) 파일이 비어있지 않은 경우(파일이 존재한다는 의미)...
				if(fileName != null && !fileName.equals("")) {
					//파일이름이 존재하고, 파일이름이 공백도 아님(아무 파일도 넣지 않고 업로드를 한 경우를 걸러내기 위해)
					part.write(uploadPath + File.separator + fileName);	//파일 저장
					System.out.println(uploadPath + File.separator + fileName + "업로드 완료!");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("파라미터 값 : " + req.getParameter("sender"));
		
		resp.setContentType("text/html");
		resp.getWriter().println("업로드 완료됨!!!");
	}
	
	/**
	 * Part 객체를 파싱하여 파일이름 추출
	 * @param part : Part 타입의 객체
	 * @return 파일명 : 파일명이 존재하지 않으면 null을 리턴함(폼필드)
	 */
	private String getFileName(Part part) {
		/*
		 * Content-Disposition헤더에 대해서...
		 * 
		 * 1. 의미 : Http Response Body에 오는 컨텐츠의 기질/성향을 알려주는 속성
		 * 
		 * 2. response header에 사용되는 경우... ex)파일 다운로드
		 * - Content-Disposition : inline(default) => ex) 그림파일을 화면에 출력
		 * - Content-Disposition : attatchment	   => 파일 다운로드
		 * - Content-Disposition : attatchment; filename = "filename.jpg"
		 * 										   => filename.jpg 이름으로 다운로드
		 * 
		 * 3. multipart body를 위한 헤더정보로 사용되는 경우... ex)파일 업로드
		 * - Content-Disposition : form-data
		 * - Content-Disposition : form-data; name = "fieldName" => 필드인 경우
		 * - Content-Disposition : form-data; name = "fieldName"; filename = "filename.jpg"
		 * 														 => 파일인 경우
		 */
		for(String content : part.getHeader("Content-Disposition").split(";")) {
			//multipart의 헤더부분만 추출
			if(content.trim().startsWith("filename")) {
				//filename으로 시작하는 부분
				return content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
				//substring() : filename.jpg부분을 추출하기 위해 사용
				//indexOf() : 괄호안에 작성한 부분이 어디있는지 찾아서 인덱스값을 반환함 
				//replace() : 쌍따옴표를 없애기 위해 사용
			}
		}
		return null;
	}
}
