<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardVO vo = (BoardVO) request.getAttribute("vo");
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Update</title>
	</head>
	<body>
		<form action="BoardUpdate.do" method="post">
			<table border="1">
				<tr>
					<td>번호</td>
					<td><%= vo.getBoard_no() %></td>
				</tr>
				<tr>
					<td>제목</td>
					<td>
						<input type="text" name="board_title" value="<%= vo.getBoard_title() %>">
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea rows="10" cols="30" name="board_content"><%= vo.getBoard_content().replaceAll("<br>", "\n") %></textarea>
					</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><%= vo.getBoard_writer() %></td>
				</tr>
				<tr>
					<td>작성일</td>
					<td><%= vo.getBoard_date() %></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
						<input type="text" name="board_password">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="hidden" name="board_no" value="<%= vo.getBoard_no() %>">
						<input type="submit" value="수정">
						<input type="button" value="취소" onclick="location.href='BoardDetail.do?board_no=<%= vo.getBoard_no() %>'">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>