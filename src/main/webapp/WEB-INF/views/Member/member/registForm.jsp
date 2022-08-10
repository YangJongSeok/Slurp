<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/regist.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
	integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	<%@ include file="../../../../resources/js/regist.js"%>
</script>
<script src="${pageContext.request.contextPath }/resources/js/regist.js"></script>
<style type="text/css">
input, select{
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

			<form action="memberJoin" method="post" onsubmit="return joinCheck()">
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
					<input type="password" name="mpw" id="mpw" class="int" maxlength="20">
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
					<input type="text" name="postcode" id="sample6_postcode" placeholder="우편번호" class="int" style="width: 80%" readonly="readonly">
						<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" class="btn"> 
					</span> 
					<span class="box int_postcode addr" style="width: 100%">
					<input type="text" name="address" id="sample6_address" placeholder="주소" class="int" readonly="readonly"><br>
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
			if(mid.length < 4 || mid.length > 12) {
				$("#error_id").html("아이디는 4 ~ 12 자리입니다.");
				$("#btnJoin").attr("disabled", "disabled");
			} else{
				$("#error_id").html("");
			}
			// 일반 아이디 중복체크 후 다시 입력하면 버튼 비활성화
			$("#btnJoin").attr("disabled", "disabled");
			$("#idCheck").text("아이디 중복체크를 해주세요.");
			$("#idCheck").css("color", "red");
		});
		
		$("#mpw").keyup(function(){
			var mpw = $("#mpw").val();
			console.log(mpw);
			if(mpw.length < 4 || mpw.length > 12){
				$("#error_pw").html("비밀번호는 4 ~ 12 자리입니다.")
				$("#btnJoin").attr("disabled", "disabled");
			} else {
				$("#error_pw").html("");
			}
		});
	});
	
	var idChecking = 0;
	// 일반회원 아이디 체크
	function midCheck() {
		var mid = $("#mid").val();
		
		// 일반회원 아이디 중복체크
		$.ajax({
			type : "post",
			url : "midCheck",
			data : { "mid" : mid },
			success : function(result) {
				if(result == "1") {
					alert('사용불가능합니다.')
					$("#btnJoin").attr("disabled", "disabled");
					$("#idCheck").text("아이디 중복체크를 해주세요.");
					$("#idCheck").css("color", "red");
					$("#mid").focus();
					idChecking = 1;
				} else if(result == "0") {
					if(mid.length < 4 || mid.length > 12) {
						$("#error_id").html("아이디는 4 ~ 12 자리입니다.");
						alert("아이디 자릿수를 확인해주세요.");
						return;
					} else {
						alert('사용가능합니다.')
						idChecking = 0;
						$("#idCheck").text("");
						$("#btnJoin").removeAttr("disabled");
					}
				}
			}
		});
	}
	
	// 회원가입 안되게 하는 기능
	function joinCheck() {
		var mgender = $("#gender").val();
		var mphone = $("#mobile").val();
		
		if(idChecking == 1) {
			alert("아이디를 확인해주세요");
			$("#mid").focus();
			return false
		}
		
		if(mphone.length > 13) {
			alert("전화번호 자릿수를 확인해주세요.");
			$("#mphone").focus();
			return false;
		}
		
		if($("#mpw").val() == ""){
			alert("비밀번호를 입력해주세요");
			$("#mpw").focus();
			return false;
		}
		
		if($("#mname").val() == ""){
			alert("이름을 입력해주세요");
			$("#mname").focus();
			return false;
		}
		
		if($("#gender").val() == "성별") {
			alert("성별을 입력해주세요");
			$("#mgender").focus();
			return false;
		}
		
		if($("#mobile").val() == "") {
			alert("전화번호를 입력해주세요");
			$("#mphone").focus();
			return false;
		}
		
		if($("#sample6_postcode").val() == "") {
			alert("우편번호를 입력해주세요");
			$("#sample6_postcode").focus();
			return false;
		}
		if($("#sample6_address").val() == "") {
			alert("주소를 입력해주세요");
			$("#sample6_address").focus();
			return false;
		}
		if($("#sample6_detailAddress").val() == "") {
			alert("상세주소를 입력해주세요");
			$("#sample6_detailAddress").focus();
			return false;
		}
	}
</script>

</html>