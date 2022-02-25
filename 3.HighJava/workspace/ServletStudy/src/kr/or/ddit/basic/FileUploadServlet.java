package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet {
	private static final String UPLOAD_DIR = "upload_files";
	//메모리 임계크기(이 크기를 넘어가면 첨부한 파일정보를 레파지토리 위치에 임시파일로 저장함)
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;
	//파일 1개당 최대크기
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 40;
	//요청파일 최대크기
	private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 50;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//인코딩 타입이 multipart/form-data인 경우...
		//multipart : 여러개로 쪼갬
		if(ServletFileUpload.isMultipartContent(req)) {
			//isMultipartContent() : 멀티파츠인지 확인해서 boolean값을 반환함
			//폼필드 값 저장용 Map 선언
			Map<String, String> formMap = new HashMap<String, String>();
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			factory.setRepository(new File("d:/D_Other/temp"));
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			//파일 1개의 사이즈가 이 설정값을 벗어나면 업로드가 안됨
			upload.setSizeMax(MAX_REQUEST_SIZE);
			//전체 파일 업로드 요청의 사이즈가 이 설정값을 벗어나면 업로드가 안됨
			
			//웹 애플리케이션 루트 디렉토리 기준 업로드 설정하기
			String uploadPath = getServletContext().getRealPath("") 
					+ UPLOAD_DIR;
			//파일 IO작업을 하려면 실제 경로를 알아야 한다. 그래서 getRealPath()를 통해서 실제경로를 찾아냄
			//separator : 구분자
			//UPLOAD_DIR : 폴더를 만들겠다.
			System.out.println("uploadPath => " + uploadPath);
			
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			try {
				List<FileItem> formItems = upload.parseRequest(req);
				
				if(formItems != null && formItems.size() > 0) {
					for(FileItem item : formItems) {	//파일인 경우...
						if(!item.isFormField()) {
							String fileName =
									new File(item.getName()).getName();
							String filePath = uploadPath + File.separator + fileName;
							File storeFile = new File(filePath);
							item.write(storeFile);	//업로드 파일 저장
						}else {	//폼데이터인 경우...
							//폼필드의 값이 한글인 경우에는 해당 문자열을 적절히 변환해줘야한다.
							formMap.put(item.getFieldName(), item.getString("UTF-8"));
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			for(Entry<String, String> entry : formMap.entrySet()) {
				System.out.println("파라미터 명 : " + entry.getKey());
				System.out.println("파리미터 값 : " + entry.getValue());
			}
			
			resp.setContentType("text/html");
			resp.getWriter().print("업로드 완료됨!!!");
		}
	}
}
