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
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	<%@ include file="../../../../resources/js/regist.js"%>
</script>
<style type="text/css">
input{
	font-family: 'Noto Sans KR', sans-serif;
}
</style>
</head>
<body>
	<!-- header -->
	<div id="header">
		<h2 style="font-weight: bold;">
			<a href="home" id="logo">Slurp</a><span style="font-size: 15px; color: gray;"> for Company</span>
		</h2>
	</div>
	<!-- wrapper -->
	<div id="wrapper">

		<!-- content -->
		<div id="content">

			<form action="companyJoin" method="post" onsubmit="return joinCheck()">
				<!-- 아이디 -->
				<div>
					<h3 class="join_title">
						<label for="id">아이디 *</label>
					</h3>
					<span class="box int_id"> 
					<input type="text" name="cid" id="cid" class="int" maxlength="20">
						<input type="button" value="중복체크" onclick="cidCheck()" class="btn">
					</span> 
					<span class="error_next_box" id="error_id"></span><br>
					<span class="error_next_box" id="idCheck"></span>
				</div>


				<!-- 비밀번호 -->
				<div>
					<h3 class="join_title">
						<label for="pw">비밀번호 *</label>
					</h3>
					<span class="box int_pass"> <input type="password" name="cpw"
						id="cpw" class="int" maxlength="20">
					</span> <span class="error_next_box" id="error_pw"></span>
				</div>
				<!-- 이름 -->
				<div>
					<h3 class="join_title">
						<label for="name">업체명 *</label>
					</h3>
					<span class="box int_name"> <input type="text" name="cname"
						id="cname" class="int" maxlength="30">
					</span> <span class="error_next_box"></span>
				</div>

				<!-- 전화번호 -->
				<div>
					<h3 class="join_title">
						<label for="phoneNo">전화번호 *</label>
					</h3>
					<span class="box int_mobile"> <input type="tel"
						name="ctel" id="mobile" class="int" maxlength="16"
						placeholder="- 포함 입력">
					</span> <span class="error_next_box"></span>
				</div>

				<!-- 주소 -->
				<div>
					<h3 class="join_title">
						<label for="addr">업체 주소 *</label>
					</h3>
					<span class="box int_postcode addr" style="width: 100%"> <input
						type="text" name="postcode" id="sample6_postcode" placeholder="우편번호" class="int"
						style="width: 80%" readonly="readonly">
						<input type="button" onclick="sample6_execDaumPostcode()"
						value="우편번호 찾기" class="btn"> <br> <span
						class="error_next_box"></span>
					</span> <span class="box int_postcode addr" style="width: 100%"> <input
						type="text" name="address" id="sample6_address" placeholder="주소"
						class="int" readonly="readonly"><br>
					</span> <span class="box int_postcode addr" style="width: 100%"> <input
						type="text" name="detailAddress" id="sample6_detailAddress" placeholder="상세주소"
						class="int">
					</span> <span class="box int_postcode addr" style="width: 100%"> <input
						type="text" name="extraAddress" id="sample6_extraAddress" placeholder="참고항목"
						class="int">
					</span> 
				</div>

				<!-- 업체등록 버튼 -->
				<div class="btn_area">
					<input type="submit" id="btnJoin" style="margin-bottom: 20px;" value="등록하기" disabled="disabled">
				</div>
				<hr>
				<!-- 유저가입 버튼, 로그인 버튼 -->
				<div class="find_info">
					<a href="registForm">회원가입</a> 
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
		$("#cid").keyup(function() {
			var cid = $("#cid").val();
			if(cid.length < 4 || cid.length > 12){
				$("#error_id").html("아이디는 4 ~ 12 자리입니다.")
			}else if(cid.indexOf(" ") >= 0){
				$("#error_id").html("아이디에 공백을 사용할 수 없습니다.")
			}else{
				$("#error_id").html("");
			}
			// 일반 아이디 중복체크 후 다시 입력하면 버튼 비활성화
			$("#btnJoin").attr("disabled", "disabled");
			$("#idCheck").text("아이디 중복체크를 해주세요.");
			$("#idCheck").css("color", "red");
		});
		
		$("#cpw").keyup(function(){
			var cpw = $("#cpw").val();
			if(cpw.length < 4 || cpw.length > 12){
				$("#error_pw").html("비밀번호는 4 ~ 12 자리입니다.");
			}else if(cpw.indexOf(" ") >= 0){
				$("#error_pw").html("비밀번호에 공백을 사용할 수 없습니다.");
			} else {
				$("#error_pw").html("");
			}
		});

	});
	
	var idChecking = 0;
	// 업체회원 아이디 체크
	function cidCheck() {
		var cid = $("#cid").val();
		console.log(cid);
		
		// 업체회원 아이디 중복체크
		$.ajax({
			type : "post",
			url : "cidCheck",
			data : { "cid" : cid },
			success : function(result) {
				if(result == "1") {
					alert('사용불가능합니다.')
					$("#btnJoin").attr("disabled", "disabled");
					$("#idCheck").text("아이디 중복체크를 해주세요.");
					$("#idCheck").css("color", "red");
					$("#cid").focus();
					idChecking = 1;
				} else if(result == "0") {
					if(cid.length < 4 || cid.length > 12) {
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
	
	function joinCheck() {
		var mphone = $("#mobile").val();
		
		if(idChecking == 1) {
			alert("아이디를 확인해주세요.");
			$("#mid").focus();
			return false;
		}
		
		if($("#cpw").val() == ""){
			alert("비밀번호를 입력해주세요");
			$("#mpw").focus();
			return false;
		}
		
		if(mphone.length > 13) {
			alert("전화번호 자릿수를 확인해주세요.");
			$("#mphone").focus();
			return false;
		}
		
		if($("#cname").val() == ""){
			alert("업체명을 입력해주세요");
			$("#mname").focus();
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