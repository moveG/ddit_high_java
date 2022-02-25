<%@page import="kr.or.ddit.cmm.vo.PagingVO"%>
<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<MemberVO> memList = (List<MemberVO>) request.getAttribute("memList");
	PagingVO pagingVO = (PagingVO) request.getAttribute("pagingVO");	

	String msg = request.getParameter("msg") == null 
			? "" 
			: request.getParameter("msg");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>회원 리스트</title>
		<style>
			table{
				margin : auto;
				border : 2px solid black;
				border-collapse : collapse;
				text-align : center;
			}
			th{
				height : 30px;
				background : orange;
				color : white;
			}
			td{
				width : 150px;
				height : 30px;
			}
			.ho{
				text-decoration : none;
			}
			.ho:hover{
				background : orange;
				color : white;
			}
			.ho:link, .ho:visited, .ho:active{
				color : black;
			}
		</style>
	</head>
	<body>
		<table border="1">
			<tr>
				<th>ID</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>주소</th>
			</tr>
<%
	int memSize = memList.size();
	if(memSize > 0){
		for(int i = 0; i < memSize; i++){
%>			
			<tr>
				<td><%= memList.get(i).getMemId() %></td>
				<td class="ho">
				<a class="ho" href="memDetail.do?memId=<%= memList.get(i).getMemId() %>">
				<%= memList.get(i).getMemName() %>
				</a>
				</td>
				<td><%= memList.get(i).getMemTel() %></td>
				<td><%= memList.get(i).getMemAddr() %></td>
			</tr>
<%
		}
%>
		<%if(pagingVO.getTotalCount() > 0) {%>
			<tr>
				<td colspan="4" align="center">
					<!-- 첫번째페이지 번호가 페이지 수보다 큰 경우(이젠페이지 존재함.) -->
					<%if(pagingVO.getFirstPageNo() > pagingVO.getPageSize()) { %>
					<a href="memList.do?pageNo=<%=pagingVO.getFirstPageNo() - pagingVO.getPageSize() %>">[이전]</a>

					<%} %>
					<%for(int pNo = pagingVO.getFirstPageNo(); pNo <= pagingVO.getLastPageNo(); pNo++) { %>
						<a <%if(pNo == pagingVO.getCurrentPageNo()){ %> style="color:orange;"<%} %> href="memList.do?pageNo=<%=pNo %>">[<%=pNo %>]</a>
					<%} %>

					<!-- 마지막페이지번호가 전체페이지 수보다 작은 경우(다음페이지 존재함.) -->
					<%if(pagingVO.getLastPageNo() < pagingVO.getTotalPageCount()) { %>
					<a href="memList.do?pageNo=<%=pagingVO.getFirstPageNo() + pagingVO.getPageSize() %>">[다음]</a>
					<%} %>
				</td>
			</tr>
		<%} %>
<%		
	}else{
%>			
			<tr>
				<td colspan="4">회원정보가 없습니다.</td>
			</tr>
<%
	}
%>
			<tr align="center">
				<td colspan="4"><a href="memInsert.do">[회원등록]</a></td>
			</tr>
		</table>
<%
	if(msg.equals("성공")){
%>		
	<script>alert('정상적으로 처리되었습니다.');</script>	
<%
	}
%>		
	</body>
</html>