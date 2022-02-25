package kr.or.ddit.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.StringTokenizer;

/**
 * 간단한 웹서버 예제
 */
public class MyHttpServer {
	private final int port = 80;
	private final String encoding = "UTF-8";
	
	public static void main(String[] args) {
		new MyHttpServer().start();
	}
	
	/*
	 * 서버시작
	 */
	private void start() {
		System.out.println("HTTP서버가 시작되었습니다.");
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(port);
			
			while(true) {
				try {
					Socket socket = server.accept();
					
					HttpHandler handler = new HttpHandler(socket);
					
					handler.start();	//요청 처리 시작...
				} catch (IOException e) {
					System.err.println("커넥션 오류!!");
					e.printStackTrace();
				} catch (RuntimeException e) {
					System.err.println("알 수 없는 오류!!");
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.err.println("서버 시작 오류!!");
			e.printStackTrace();
		} finally {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/*
	 * HTTP 요청 처리를 위한 Thread클래스
	 */
	private class HttpHandler extends Thread {
		private final Socket socket;
		
		public HttpHandler(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			OutputStream os = null;
			BufferedReader br = null;
			
			try {
				os = new BufferedOutputStream(
						socket.getOutputStream());
				br = new BufferedReader(
						new InputStreamReader(
							socket.getInputStream()));
				
				//요청헤더 정보 파싱하기
				StringBuilder request = new StringBuilder();
				//++가 아니라 StringBuilder를 사용하면 속도가 빨라짐
				
				while(true) {
					String str = br.readLine();
					
					if(str.equals("")) break;	//emptyLine 체크
					
					request.append(str + "/n");
					//한줄씩 읽어서 누적시켜 저장함
					//String에 시키는 것보다는 StringBuilder의 append를 사용하는 것이 빠름
				}
				System.out.println("요청헤더 : \n" + request.toString());
				System.out.println("================================");
				
				String reqPath = "";
				
				//요청페이지 정보 가져오기
				StringTokenizer st =
						new StringTokenizer(request.toString());
				//특정기준(공백, 콤마(,) 등)을 바탕으로 문자열을 쪼갬(토큰화)
				//여기서는 기준을 정하지 않았으므로 디폴트값인 공백을 기준으로 잘라 저장함
				
				while(st.hasMoreTokens()) {	//쪼개져 저장된 토큰을 하나씩 부름
					String token = st.nextToken();
					if(token.startsWith("/")) {	//각 토큰이 슬래시('/')로 시작하는지 체크함
						reqPath = token;
						//공백을 기준으로 자른 토큰들 중에서 슬래시('/')로 시작하는 것은 딱 하나만 존재함
						//슬래시('/')로 시작하는 토큰을 문자열로 저장함
					}
				}
				
				//경로 디코딩
				reqPath = URLDecoder.decode(reqPath, "UTF-8");
				
				//상대경로(프로젝트 폴더 기준) 설정
				String filePath = "./WebContent" + reqPath;
				
				//해당 파일이름을 이용하여 Content-type정보 추출
				String contentType = URLConnection
						.getFileNameMap().getContentTypeFor(filePath);
				
				//CSS파일은 인식이 안되므로 추가함...
				if(contentType == null && filePath.endsWith(".css")) {
					contentType = "text/css";
				}
				System.out.println("ContentType : " + contentType);
				
				File file = new File(filePath);
				if(!file.exists()) {
					makeErrorPage(os, 404, "Not Found");
					return;
				}
				byte[] body = makeResponseBody(filePath);
				byte[] header = makeResponseHeader(body.length, contentType);
				
				//요청헤더가 HTTP/1.0 또는 그 이후의 버전을 지원할 경우 MIME헤더를 전송한다.
				if(request.toString().indexOf("HTTP/") != -1) {
					os.write(header);	//응답내용 보내기
				}
				System.out.println("응답헤더 : \n" + new String(header));
				System.out.println("================================");
				
				os.write(body);	//응답내용 보내기
				os.flush();
			} catch (IOException e) {
				System.err.println("입출력 오류!!");
				e.printStackTrace();
			} finally {
				try {
					socket.close();	//소켓 닫기(연결 끊기)
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * 응답바디 생성하기
	 * @param filePath
	 * @return data(파일내용을 배열로 리턴)
	 */
	private byte[] makeResponseBody(String filePath) {
		FileInputStream fis = null;
		byte[] data = null;
		
		try {
			File file = new File(filePath);
			data = new byte[(int) file.length()];
			
			fis = new FileInputStream(file);
			fis.read(data);
		} catch (IOException e) {
			System.err.println("입출력 오류!!");
			e.printStackTrace();
		} finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}
	
	/*
	 * 응답헤더 생성하기
	 * @param length 응답내용 크기
	 * @param contentType MIME타입
	 * @return 바이트배열
	 */
	private byte[] makeResponseHeader(int length, String contentType) {
		String header = "HTTP/1.1 200 OK\r\n"
				+ "Server: MyHTTPServer 1.0\r\n"
				+ "Content-length: " + length + "\r\n"
				+ "Content-Type: " + contentType
				+ "; charset=" + this.encoding + "\r\n\r\n";
		//200 OK : 요청이 성공했다는 의미의 응답상태 코드
		//charset : 캐릭터셋이 UTF-8이라는 것을 알려주기 위해 작성
		//"\r\n\r\n" : emptyLine
		return header.getBytes();
	}
	
	/*
	 * 에러페이지 생성
	 * @param out
	 * @param statusCode
	 * @param errMsg
	 */
	private void makeErrorPage(OutputStream os, int statusCode, String errMsg) {
		String statusLine = "HTTP/1.1" + " " + statusCode + " " + errMsg;
		
		try {
			os.write(statusLine.getBytes());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
