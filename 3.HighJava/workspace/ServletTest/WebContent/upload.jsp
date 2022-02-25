<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>File Upload 예제</title>
	</head>
	<body>
		<h3>아파치 자카르타 프로젝트의 fileUpload 모듈을 이용한 파일 업로드</h3>
		<form action="upload" method="post" enctype="multipart/form-data">
		<!-- 파일을 전송할 때는 enctype으로 multipart/form-data를 설정해줘야 한다. -->
		<!-- 파일을 전송할 때는 전송방식으로 post를 설정해줘야 한다enctype으로 multipart/form-data를 설정해줘야 한다. -->
			파일 선택 : <input type="file" name="uploadFile">
			전송자 : <input type="text" name="sender">
			<input type="submit" value="업로드 시작">
		</form>
		
		<br><hr><br>
		
		<h3>서블릿3부터 지원하는 Part, Part인터페이스를 이용한 파일 업로드</h3>
		<form action="upload2" method="post" enctype="multipart/form-data">
		<!-- 파일을 전송할 때는 enctype으로 multipart/form-data를 설정해줘야 한다. -->
		<!-- 파일을 전송할 때는 전송방식으로 post를 설정해줘야 한다enctype으로 multipart/form-data를 설정해줘야 한다. -->
			파일 선택 : <input type="file" name="uploadFile">
			전송자 : <input type="text" name="sender">
			<input type="submit" value="업로드 시작">
		</form>
	</body>
</html>