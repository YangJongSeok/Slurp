<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Slurp</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">

<!-- notosans -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100&display=swap" rel="stylesheet">

<link rel="stylesheet" href="resources/css/regist.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
	integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	<%@ include file="../../../../resources/js/regist.js"%>
</script>
<style type="text/css">
input {
	font-family: 'Noto Sans KR', sans-serif;
}
</style>

</head>
<body>
	<!-- header -->
	<div id="header">

		<h2 style="font-weight: bold;">
			<a href="home" id="logo">Slurp</a>
		</h2>
	</div>


	<!-- wrapper -->
	<div id="wrapper">

		<!-- content -->
		<div id="content">



			<form action="memberLogin" method="post" onsubmit="return loginCheck()">
				<!-- 아이디 -->
				<div>
					<h3 class="join_title">
						<label for="id">아이디</label>
					</h3>
					<span class="box int_id"> 
					<input type="text" name="mid" id="mid" class="int" maxlength="20">
					</span> 
					
				</div>


				<!-- 비밀번호 -->
				<div>
					<h3 class="join_title">
						<label for="pw">비밀번호</label>
					</h3>
					<span class="box int_pass"> 
					<input type="password" name="mpw" id="mpw" class="int" maxlength="20" autocomplete="off">
					</span> 
					
				</div>
				
				<span class="error_next_box" id="error_login">${msg }</span>
				
				

				<!-- 로그인 버튼 -->
				<div class="btn_area">
					<button type="submit" id="btnJoin" style="margin-bottom: 20px;">
						<span>로그인</span>
					</button>
				</div>
				<a href="https://kauth.kakao.com/oauth/authorize?client_id=61c5d95039b92ab8746d78d46afce77b&redirect_uri=http://182.219.216.178:8090/Slurp/login&response_type=code" style="margin-left: 20%;">
					<img src="resources/images/kakao_login_medium_wide.png">
				</a>
				
				<hr>
				<!-- 업체,관리자 로그인 , 회원가입 버튼 -->
				<div class="find_info">
					<a href="companyLoginForm">업체 로그인</a> 
					<span class="bar" aria-hidden="true">|</span> 
					<a href="adminLoginForm">관리자 로그인</a> 
					<span class="bar" aria-hidden="true">|</span> 
					<a href="registForm">회원가입</a>
				</div>
				
				
				
				

			</form>

		</div>
		<!-- content-->

	</div>
	<!-- wrapper -->
</body>
<script type="text/javascript">
	$(document).ready(function() {
		
	})
	
	function loginCheck() {
			var inputId = $("#mid").val();
			var inputPw = $("#mpw").val();
			if(inputId == ""){
				$("#error_login").html("아이디를 입력해주세요.")
				return false;
			}else if(inputPw == ""){
				$("#error_login").html("비밀번호를 입력해주세요.")
				return false;
			}else{
				$("#error_login").html("")
			}
		}
</script>


</html>