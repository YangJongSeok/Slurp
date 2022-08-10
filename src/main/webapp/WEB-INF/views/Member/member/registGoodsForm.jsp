<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Slurp</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="resources/css/regist.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
	integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	<%@ include file="../../../../resources/js/regist.js"%>
</script>
	<!-- 회원가입 스크립트 -->
	<script src="${pageContext.request.contextPath }/resources/js/memberJoin.js"></script>
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



			<form action="memberJoin" method="post">
				<!-- 아이디 -->
				<div>
					<h3 class="join_title">
						<label for="id">아이디 *</label>
					</h3>
					<span class="box int_id"> 
					<input type="text" name="mid" id="mid" class="int" maxlength="20">
						<input type="button" value="중복체크" onclick="midCheck()" class="btn">
					</span> 
					<span class="error_next_box" id="error_id"></span><br>
					<span class="error_next_box" id="idCheck"></span>
				</div>


				<!-- 비밀번호 -->
				<div>
					<h3 class="join_title">
						<label for="pswd1">비밀번호 *</label>
					</h3>
					<span class="box int_pass"> 
					<input type="text" name="mpw" id="mpw" class="int" maxlength="20">
					</span> 
					<span class="error_next_box" id="error_pw"></span>
				</div>
				

				<!-- 이름 -->
				<div>
					<h3 class="join_title">
						<label for="name">이름 *</label>
					</h3>
					<span class="box int_name"> 
					<input type="text" name="mname" id="mname" class="int" maxlength="20">
					</span> 
					<span class="error_next_box"></span>
				</div>


				<!-- 생년월일 -->
				<div>
					<h3 class="join_title">
						<label for="yy">생년월일 *</label>
					</h3>

					<div id="bir_wrap">
						
						<div id="bir">
							<span class="box"> <input type="date" name="mbirth"
								id="mbirth" class="int">
							</span>
						</div>

					</div>
					<span class="error_next_box"></span>
				</div>



				<!-- 성별 -->

				<div>
					<h3 class="join_title">
						<label for="gender">성별 *</label>
					</h3>
					<span class="box gender_code"> <select id="gender"
						name="mgender">
							<option>성별</option>
							<option value="남">남자</option>
							<option value="여">여자</option>
					</select>
					</span> <span class="error_next_box"></span>
				</div>

				<!-- 이메일 -->
				<div>
					<h3 class="join_title">
						<label for="email">이메일<span class="optional"> (선택)</span></label>
					</h3>
					<span class="box int_email"> <input type="text" id="memail" name="memail"
						class="int" maxlength="100" placeholder="선택입력">
					</span> <span class="error_next_box"></span>
				</div>

				<!-- 휴대전화번호 -->
				<div>
					<h3 class="join_title">
						<label for="phoneNo">휴대전화 *</label>
					</h3>
					<span class="box int_mobile"> <input type="tel"
						name="mphone" id="mobile" class="int" maxlength="16"
						placeholder="- 포함 입력">
					</span> <span class="error_next_box"></span>
				</div>

				<!-- 주소 -->
				<div>
					<h3 class="join_title">
						<label for="addr">주소 *</label>
					</h3>
					
					<span class="box int_postcode addr" style="width: 100%"> 
					<input type="text" name="postcode" id="sample6_postcode" placeholder="우편번호" class="int" style="width: 80%">
						<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" class="btn"> 
					</span> 
					<span class="box int_postcode addr" style="width: 100%"> <input
						type="text" name="address" id="sample6_address" placeholder="주소"
						class="int"><br>
					</span> 
					<span class="box int_postcode addr" style="width: 100%"> <input
						type="text" name="detailAddress" id="sample6_detailAddress" placeholder="상세주소"
						class="int">
					</span>
					<span class="box int_postcode addr" style="width: 100%"> <input
						type="text" name="extraAddress" id="sample6_extraAddress" placeholder="참고항목"
						class="int">
					</span> 
					<span class="error_next_box"></span>
				</div>

				<!-- 회원가입 버튼 -->
				<div class="btn_area">
					<input type="submit" id="btnJoin" disabled="disabled" style="margin-bottom: 20px;" value="가입하기">
				</div>
				
				<hr>
				<!-- 업체등록 버튼, 로그인 버튼 -->
				<div class="find_info">
					<a href="companyRegist">업체 등록</a> 
					<span class="bar" aria-hidden="true">|</span> 
					<a href="loginForm">로그인 하러가기</a>
				</div>
				
			</form>

		</div>
		<!-- content-->

	</div>
	<!-- wrapper -->
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#mid").keyup(function() {
			var mid = $("#mid").val();
			if(mid.length < 4 || mid.length > 12){
				$("#error_id").html("아이디는 4 ~ 12 자리입니다.")
			}else if(mid.indexOf(" ") >= 0){
				$("#error_id").html("아이디에 공백을 사용할 수 없습니다.")
			} else{
				$("#error_id").html("");
			}
			// 일반 아이디 중복체크 후 다시 입력하면 버튼 비활성화
			$("#btnJoin").attr("disabled", "disabled");
			$("#idCheck").text("아이디 중복체크를 해주세요.");
			$("#idCheck").css("color", "black");
		});
		
		$("#mpw").keyup(function(){
			var mpw = $("#mpw").val();
			if(mpw.length < 4 || mpw.length > 12){
				$("#error_pw").html("아이디는 4 ~ 12 자리입니다.")
			}else if(mpw.indexOf(" ") >= 0){
				$("#error_pw").html("비밀번호에 공백을 사용할 수 없습니다.")
			} else {
				$("#error_pw").html("");
			}
		})
		
	})
</script>


</html>