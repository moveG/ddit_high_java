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
	//메모리 임계크기(이 크기를 넘어가면 첨부한 파일정보가 레파지토리 위치에 임시파일로 저장됨)
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;
	//파일 1개당 최대크기
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 40; 
	//요청파일 최대크기
	private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 50;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//파일 전송은 get방식으로는 불가능하므로 항상 post방식으로 전송된다고 생각하면 됨
		//인코딩 타입이 multipart/form-data 인 경우...
		//multipart : 여러개로 쪼개서 날리겠다.
		if(ServletFileUpload.isMultipartContent(req)) {
			//isMultipartContent() : 멀티파츠인지 확인해서 boolean값을 반환함
			//폼필드 값 저장용 Map 선언
			Map<String, String> formMap = new HashMap<String, String>();
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			factory.setRepository(new File("d:/D_Other/temp"));
			//메모리 크기를 넘는 데이터를 임시로 temp폴더에 저장하겠다.
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			//파싱작업 용도
			upload.setFileSizeMax(MAX_FILE_SIZE);
			//파일 1개의 사이즈가 이 설정값을 벗어나면 업로드가 안됨
			upload.setSizeMax(MAX_REQUEST_SIZE);
			//전체 파일 업로드 요청의 사이즈가 이 설정값을 벗어나면 업로드가 안됨
			
			//웹 애플리케이션 루트 디렉토리 기준 업로드 설정하기
			String uploadPath = getServletContext().getRealPath("") 
					+ UPLOAD_DIR;
			//파일 IO작업을 하려면 실제 경로를 알아야 한다. 그래서 getRealPath()를 통해서 실제경로를 찾아냄
			//separator : 구분자 - OS의 기본 구분자를 넣어준다.
			//UPLOAD_DIR : 폴더를 만들겠다.
			//getRealPath("") : 괄호 안에는 경로의 구분자를 넣어준다. 넣지 않으면 디폴트값인 '/'가 설정된다.
			System.out.println("uploadPath => " + uploadPath);
			
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			try {
				List<FileItem> formItems = upload.parseRequest(req);
				
				if(formItems != null && formItems.size() > 0) {
					for(FileItem item : formItems) {
						if(!item.isFormField()) {	//파일인 경우...
							//전체 경로를 제외한 파일명만 추출하기
							//폼필드 : 폼데이터를 가지고 있는 영역
							//폼필드가 아니라면 파일임
							String fileName = 
									new File(item.getName()).getName();
							//파일이름을 알기 위해 사용함
							String filePath = uploadPath + File.separator + fileName;
							
							File storeFile = new File(filePath);
							item.write(storeFile);	//업로드 파일 저장, 파라미터로 파일 객체를 요구함
						}else {	//폼데이터인 경우...
							//폼필드의 값이 한글인 경우에는 해당 문자열을 적절히 변환해줘야한다.
							formMap.put(item.getFieldName(), item.getString("UTF-8"));
							//getFieldName() : 폼필드의 이름을 가져옮
							//getString() : 파라미터값을 가져옮
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
