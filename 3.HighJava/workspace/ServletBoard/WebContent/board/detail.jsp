<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardVO vo = (BoardVO) request.getAttribute("vo");

	String msg = (String) request.getParameter("msg") == null
			? ""
			: (String) request.getParameter("msg");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Detail</title>
	</head>
	<body>
		<table border="1">
			<tr>
				<td>번호</td>
				<td><%= vo.getBoard_no() %></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><%= vo.getBoard_title() %></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><%= vo.getBoard_content().replaceAll("\r", "").replaceAll("\n", "<br>") %></td>
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
				<td>조회수</td>
				<td><%= vo.getBoard_hit() %></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="목록" onclick="location.href='BoardList.do'">
					<input type="button" value="수정" onclick="location.href='BoardUpdate.do?board_no=<%= vo.getBoard_no() %>'">
					<input type="button" value="삭제" onclick="location.href='BoardDelete.do?board_no=<%= vo.getBoard_no() %>'">
				</td>
			</tr>
		</table>
		
<%
	if(msg.equals("성공")){
%>
		<script>alert("정상적으로 수정되었습니다.");</script>
<%		
	}
%>	
	</body>
</html>