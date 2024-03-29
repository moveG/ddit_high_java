<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css">
		
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

 		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>회원 수정 페이지</title>
		<style>
			.container{
		 		margin-left : 32%;
		 		width : 700px;
		 	}
			#buttonbox{
				float : right;
			}
			.item{
				width : 200px;
				vertical-align : middle;
			}
			.container th, .container td{
				height : 55px;
				vertical-align : middle;
			}
			.star{
		 		color : red;
		 	}
		 	#address{
		 		display : inline-block;
		 		width : 353px;
		 	}
		 	.pic{
		 		display : inline-block;
				width : 132px;
				height : 170px;
			}
			#piclabel{
				vertical-align : bottom;
				background : rgb(25, 135, 84);
		 		border-color : rgb(25, 135, 84);
		 		color : white;
		 		width : 90px;
		 		height : 38px;
		 		padding : 7px 12px 5px 12px;
		 		border-radius : 0.25rem;
			}
			.btn-success{
		 		vertical-align : top;
		 	}
		 	#piclabel:hover{
		 		background : rgb(21, 115, 71);
		 		border-color : rgb(21, 115, 71);
		 	}
		</style>
		
		<script>
			$(function(){
				pwdvalue = "";
				pwdreg = "";
				emailvalue = "";
				emailreg = "";
				
				//비밀번호 정규식
				$('#pwd').on('keyup', function(){
					pwdvalue = $(this).val().trim();
					pwdreg = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#$@!]).{8,20}$/;
					
					if(pwdreg.test(pwdvalue) == true){
						$(this).css('border', '2px solid blue');
						if(emailreg.test(emailvalue) == true){
							$('#update').prop("disabled", false);
						}
					}else if(pwdreg.test(pwdvalue) == false){
						$(this).css('border', '2px solid red');
						$('#update').prop("disabled", true);
					}
				})
					
				//이메일 정규식
				$('#email').on('keyup', function(){
					emailvalue = $(this).val().trim();
					emailreg = /^[a-zA-Z0-9-_]+@[a-zA-Z]+(\.[a-zA-Z]+){1,2}$/;
					
					if(emailreg.test(emailvalue) == true){
						$(this).css('border', '2px solid blue');
						if(pwdreg.test(pwdvalue) == true){
							$('#update').prop("disabled", false);
						}
					}else if(emailreg.test(emailvalue) == false){
						$(this).css('border', '2px solid red');
						$('#update').prop("disabled", true);
					}
				})
				
				//전화번호 정규식
				$('#phone').on('keyup', function(){
					hpvalue = $(this).val().trim();
					hpreg = /^\d{3}-\d{3,4}-\d{4}$/;
					
					if(hpreg.test(hpvalue)){
						$(this).css('border', '2px solid blue');
					}else{
						$(this).css('border', '2px solid red');
					}
				})
					
				$("#picture").on("change", function() {
					var picture = $(this).val().split("\\").pop();
					
					if(picture == ""){
						$('#pic').attr('src', '/ServletMemberBoard/picture/${vo.picture}');
					}else{
						$('#pic').attr('src', '/ServletMemberBoard/picture/' + picture);						
					}
				});
				
				if("${vo.authority}" == "ROLE_MANAGER"){
					$('#authority1').prop('checked', true); 
				}else if("${vo.authority}" == "ROLE_USER"){
					$('#authority2').prop('checked', true); 
				}else if("${vo.authority}" == "ROLE_ADMIN"){
					$('#authority3').prop('checked', true); 
				}else{
					$('#authority2').prop('checked', true); 
				}
				
				if("${vo.enabled}" == "0"){
					$('#enabled1').prop('checked', true); 
				}else if("${vo.enabled}" == "1"){
					$('#enabled2').prop('checked', true); 
				}else if("${vo.enabled}" == "2"){
					$('#enabled3').prop('checked', true); 
				}else{
					$('#enabled2').prop('checked', true); 
				}
				
				if("${vo.picture}" == null){
					$('#pic').attr('alt', '등록된 사진이 없습니다'); 
				}else{
					$('#pic').attr('alt', '${vo.picture}'); 
				}
				
				$('#update').on('click', function(){
					var flag = confirm('수정하시겠습니까?'); 
					if(flag){
						$('#updateform').submit();
					}else{
						return;
					}
				})
			})
		</script>
	</head>
	<body>
		<div class="container mt-3">
			<h2 style="cursor:pointer;" onclick="location.href='MemberUpdate.do?id=${vo.id}'">회원 수정 페이지</h2>            
			<form id="updateform" action="MemberUpdate.do" method="post">	
				<table class="table table-hover" class="form-group">
					<thead>
						<tr>
							<th class="item">항목</th>
							<th>내용</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="item mb-3">사진</td>
							<td>
								<img id="pic" class="pic" alt="" src="/ServletMemberBoard/picture/${vo.picture}">
								<input class="picture" type="file" id="picture" placeholder="Enter picture" name="picture" style="display:none;">
								&nbsp;
								<label style="cursor:pointer;" class="picture" id="piclabel" for="picture">파일선택</label>
							</td>
						</tr>
						<tr>
							<td class="item">이름</td>
							<td>${vo.name}</td>
						</tr>
						<tr>
							<td class="item">아이디</td>
							<td>${vo.id}</td>
						</tr>
						<tr>
							<td class="item"><span class="star">*</span>패스워드</td>
							<td>
							<input class="form-control" type="password" id="pwd" placeholder="Enter password" name="pwd" value="${vo.pwd}">
							<span id='pwdspan'></span>
							</td>
						</tr>
						<tr>
							<td class="item"><span class="star">*</span>이메일</td>
							<td><input class="form-control" type="text" id="email" placeholder="Enter email" name="email" value="${vo.email}"></td>
						</tr>
						<tr>
							<td class="item">연락처</td>
							<td><input class="form-control" type="text" id="phone" placeholder="Enter phoneNumber" name="phone" value="${vo.phone}"></td>
						</tr>
						<tr>
							<td class="item">주소</td>
							<td>
							<input class="form-control" type="text" id="address" placeholder="Enter address" name="address" value="${vo.address}" readOnly>
							&nbsp;
							<button type="button" class="btn btn-success" onclick="sample6_execDaumPostcode()">주소검색</button>
							</td>
						</tr>
						<tr>
							<td class="item">입사일</td>
							<td>
							<input style="cursor:pointer;" type="date" class="form-control" id="regdate" placeholder="Enter regdate" name="regdate" value='<fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd"/>'>
							</td>
						</tr>
						<tr>
							<td class="item">권한</td>
							<td>
								<input type="radio" id="authority1" name="authority" value="ROLE_MANAGER"> 매니저&nbsp;&nbsp;
								<input type="radio" id="authority2" name="authority" value="ROLE_USER"> 직원&nbsp;&nbsp;
								<input type="radio" id="authority3" name="authority" value="ROLE_ADMIN"> 관리자
							</td>
						</tr>
						<tr>
							<td class="item">상태</td>
							<td>
								<input type="radio" id="enabled1" name="enabled" value="0"> 퇴사&nbsp;&nbsp;
								<input type="radio" id="enabled2" name="enabled" value="1"> 재직&nbsp;&nbsp;
								<input type="radio" id="enabled3" name="enabled" value="2"> 휴직	
							</td>
						</tr>
						<tr>
							<td class="item">등록자</td>
							<td>
							<input class="form-control" type="text" id="register" placeholder="Enter register" name="register" value="${vo.register}"></td>
						</tr>
					</tbody>
				</table>
				
				<div id="buttonbox">
					<input type="hidden" name="id" value="${vo.id}">
					<button id="update" type="button" class="btn btn-primary">수정</button>
					<button type="button" class="btn btn-primary" onclick="location.href='MemberDetail.do?id=${vo.id}'">취소</button>
				</div>
			</form>	
		</div>
		
		<script>
			function sample6_execDaumPostcode() {
				new daum.Postcode({
					oncomplete: function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
						
						// 각 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var addr = ''; // 주소 변수
						var extraAddr = ''; // 참고항목 변수
						
						//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
						if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
						    addr = data.roadAddress;
						} else { // 사용자가 지번 주소를 선택했을 경우(J)
						    addr = data.jibunAddress;
						}
						
						// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
						if(data.userSelectedType === 'R'){
						    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
						    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
						        extraAddr += data.bname;
						    }
						    // 건물명이 있고, 공동주택일 경우 추가한다.
						    if(data.buildingName !== '' && data.apartment === 'Y'){
						        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
						    }
						    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						    if(extraAddr !== ''){
						        extraAddr = ' (' + extraAddr + ')';
						    }
						    // 조합된 참고항목을 해당 필드에 넣는다.
						    document.getElementById("address").value = "(" + data.zonecode + ") " + addr + " " + extraAddr + " ";
						
						} else {
							document.getElementById("address").value = "(" + data.zonecode + ") " + addr + " ";
						}
						
						// readonly 해제
						document.getElementById("address").readOnly = false;
						
						// 커서를 상세주소 필드로 이동한다.
						document.getElementById("address").focus();
					}
				}).open();
			}
		</script>
	</body>
</html>