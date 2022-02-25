<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<BoardVO> list = (List<BoardVO>) request.getAttribute("list");
	
	String msg = (String) request.getParameter("msg") == null
			? ""
			: (String) request.getParameter("msg");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Board</title>
		<style>
		
		</style>
	</head>
	<body id="body">
		<h2>게시판</h2>
		<table border="1">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
<%
	int boardSize = list.size();
	if(boardSize > 0){
		for(int i = 0; i < boardSize; i++){
%>
			<tr>
				<td><%= list.get(i).getBoard_no() %></td>
				<td>
				<a href="BoardDetail.do?board_no=<%= list.get(i).getBoard_no() %>">
				<%= list.get(i).getBoard_title() %>
				</a>
				</td>
				<td><%= list.get(i).getBoard_writer() %></td>
				<td><%= list.get(i).getBoard_date() %></td>
				<td><%= list.get(i).getBoard_hit() %></td>			
			</tr>
<%
		}
	}else{
%>
			<tr>
				<td colspan="5">게시글이 존재하지 않습니다.</td>
			</tr>
<%		
	}
%>
			<tr align="center">
				<td colspan="5">
				<input type="button" value="게시글 등록" onclick="location.href='BoardInsert.do'">
				</td>
			</tr>
		</table>

<%
	if(msg.equals("성공")){
%>
		<script>alert("정상적으로 처리되었습니다.");</script>
<%		
	}
%>		
	</body>
</html>