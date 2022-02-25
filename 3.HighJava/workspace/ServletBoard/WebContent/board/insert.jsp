<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert</title>
	</head>
	<body>
		<form action="BoardInsert.do" method="post">
			<label>제목</label>
			<input type="text" name="board_title">
			<br>
			<label>내용</label>
			<textarea rows="10" cols="30" name="board_content"></textarea>
			<br>
			<label>작성자</label>
			<input type="text" name="board_writer">
			<br>
			<label>비밀번호</label>
			<input type="text" name="board_password">
			<br><br>
			<input type="submit" value="등록">
			<input type="button" value="취소" onclick="location.href='BoardList.do'">
		</form>
	</body>
</html>