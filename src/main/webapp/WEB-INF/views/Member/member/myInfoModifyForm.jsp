<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
	<head>
	<title>Slurp</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="${pageContext.request.contextPath }/resources/js/regist.js"></script>
	<!-- header -->
	<%@include file="../../header.jsp" %>
	
	<style type="text/css">
		table{
			width: 100%;
			color: black;
		}
			
		td{
			background-color: white;
			font-size: 15px;
			padding: 15px 0;
		}
		
		tr{
			border-bottom: 1px solid silver;
		}
		
		th{
			font-size: 15px;
		}

		table,th,td{
			height: 30px;
		}
		
		.linemenu{
			color:  black;
			letter-spacing: 2px;
			margin-left: 15px;
			font-size: 20px;
		}
		.btn1{
			color: black;
			font-family: 'Do Hyeon', sans-serif;
			font-size: 13px;
			font-weight: 100;
			cursor: pointer;
			background-color: #f0f0f0;
			border: 1px black solid;
			padding: 5px;
		}
		.btn1:hover{
			background-color: gray;
			color: white;
		}
		.btn1:focus{
			outline: 0;
			border: 0;
		}
		.pwc{
		border-bottom: 0;
		}
		.pwctd{
			padding: 5px;
		}
		input{
			height: 20px;
			width: 200px;
			border-bottom: 1px solid gray;
			border-top: 0;
			border-left: 0;
			border-right: 0;
			font-size: 13px;
		}
		input:focus{
		outline: none;
		}
		label{
		    min-width: 200px;
		    display: inline-block;
		}
	</style> 
			
			
	
	</head>
	
	<body>
		
	
	
	<div id="page">
	
		<!-- 상단메뉴 시작 -->
		<%@include file="../../topbar.jsp" %>
		<!-- 상단메뉴 끝 -->
		
		<div style="margin: 150px 15% 150px 15%;">
		<div style="background-color: #f0f0f0; border-radius: 10px;"><h1 class="linemenu">회원 정보</h1></div>
		<div style="margin-bottom: 50px; margin-left: 10%; margin-right: 10%">
		
		<table>
		
			<tr>
				<td style="opacity: 0.5">아이디</td>
				<td colspan="2">
					<!-- 세션로그인 아이디 값을 넘겨주기위해 만듬, hidden처리  -->
					<input type="hidden" id="loginId" name="loginId" value="${sessionScope.loginId }">
					${sessionScope.loginId }  
				</td>
			</tr>
			<!-- jquery 이용 display : none -->
			<tr>
				<td style="opacity: 0.5">비밀번호</td>
				<td id="pw">*********************</td>
				<td id="pwc" style="display: none;">
				<div>
				<label for="password">현재 비밀번호</label><input type="text" id="mpw" name="mpw"><br>
				<label for="new password">신규 비밀번호</label><input type="password" id="modPw" name="modPw"><span class="error_next_box" id="error_pw"></span><br>
				<label for="new password confirm">신규 비밀번호 재입력</label><input type="password" id="checkModPw" name="checkModPw"><br>
				<button class="btn1" onclick="cancle()" style="width: 50px;">취소</button>
				<button class="btn1" style="width: 50px;" onclick="modifyPw()">완료</button>
				</div>
				</td>
				<td><button class="btn1" id="pwChange" onclick="pwChange()">비밀번호 변경</button></td>
			</tr>
			<tr>
				<td style="opacity: 0.5">이름</td>
				<td colspan="2">${memInfo.mname }</td>
			</tr>
			<tr>
				<td style="opacity: 0.5">이메일</td>
				<td colspan="2" id="email">${memInfo.memail }</td>
				<td id="emailc" style="display: none;">
				<div>
				<label for="new email">신규 이메일</label><input type="email" id="modEmail" name="modEmail"><span class="error_next_box" id="error_pw"></span><br>
				<button class="btn1" onclick="cancle1()" style="width: 50px;">취소</button>
				<button class="btn1" style="width: 50px;" onclick="modifyEmail()">완료</button>
				</div>
				</td>
				<td><button class="btn1" id="emailChange" onclick="emailChange()">이메일 변경</button></td>
			</tr>
			<tr>
				<td style="opacity: 0.5">휴대전화</td>
				<td colspan="2" id="phone">${memInfo.mphone }</td>
				<td id="phonec" style="display: none;">
				<div>
				<label for="new phone">신규 휴대전화</label><input type="text" id="modPhone" name="modPhone"><span class="error_next_box" id="error_pw"></span><br>
				<button class="btn1" onclick="cancle2()" style="width: 50px;">취소</button>
				<button class="btn1" style="width: 50px;" onclick="modifyPhone()">완료</button>
				</div>
				</td>
				<td><button class="btn1" id="phoneChange" onclick="phoneChange()">휴대전화 변경</button></td>
			</tr>
		</table>
		
		</div>
		
		<div style="background-color: #f0f0f0; border-radius: 10px;"><h1 class="linemenu">추가 회원 정보</h1></div>
		<div style="margin-bottom: 50px; margin-left: 10%; margin-right: 10%">
			<table>
				<tr>
					<td style="opacity: 0.5; width: 25%">생년월일</td>
					<td>${memInfo.mbirth }</td>
				</tr>
				<tr>
					<td style="opacity: 0.5; width: 25%">성별</td>
					<td>${memInfo.mgender }</td>
				</tr>
				<tr>
				<!-- !!!!!!!!!!!!!!!!!! 다음 주소 api 적용해야함 !!!!!!!!!!!!!!!!!! -->
					<td style="opacity: 0.5; width: 25%">주소</td>
					<td colspan="2" id="add">${memInfo.madd }</td>
					<td id="addc" style="display: none;">
					<div>
					<label for="new add">신규 주소</label>
					
					<div class="col-md-12" id="directaddr1">
						<div class="form-group">
							<label for="email">우편번호</label>
							<input type="text" id="sample6_postcode" class="form-control" placeholder="우편번호" name="o_add1" readonly="readonly">  <!-- 우편번호 -->
							<input type="button" style="height: 40px;" id ="adds" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" class="form-control" > 
						</div>
					</div>
					<div class="col-md-12" id="directaddr3">
						<div class="form-group">
							<label for="email">주소</label>
							<input type="text" id="sample6_address" placeholder="주소" class="form-control"  name="o_add2" readonly="readonly">  <!-- 주소 -->
						</div>
					</div>
					<div class="col-md-12" id="directaddr2">
						<div class="form-group">
							<label for="email">상세주소</label>
							<input type="text" style="width: 100%;" id="sample6_detailAddress" placeholder="상세주소" class="form-control"  name="o_add3">  <!-- 상세주소 -->
						</div>
					</div>
					
					<div class="col-md-12" id="directaddr4">
						<div class="form-group">
							<label for="email">참고항목</label>
							<input type="text" id="sample6_extraAddress" placeholder="참고항목" class="form-control" name="o_add4">  <!-- 참고항목 -->
						</div>
					</div>

					<button class="btn1" onclick="cancle3()" style="width: 50px;">취소</button>
					<button class="btn1" style="width: 50px;" onclick="modifyAddress()">완료</button>
					</div>
					</td>
					<td><button class="btn1" id="addressChange" onclick="addressChange()">주소 변경</button></td>
				</tr>
			</table>
		</div>
		</div>
	</div>
		
		
		<!-- footer -->
		<%@include file="../../footer.jsp" %>

	</body>
	
	<script type="text/javascript">
	/* 비밀번호 */
		function pwChange() {
			$("#pw").css("display","none");
			$("#pwc").css("display","");
			$("#pwChange").css("display","none");
		}
		
		function cancle() {
			$("#modPw").val("");
			$("#checkModPw").val("");
			$("#pw").css("display","");
			$("#pwc").css("display","none");
			$("#pwChange").css("display","");
		}
		
	/* 이메일 */
		function emailChange() {
			$("#email").css("display","none");
			$("#emailc").css("display","");
			$("#emailChange").css("display","none");
		}
		
		function cancle1() {
			$("#modEmail").val("");
			$("#email").css("display","");
			$("#emailc").css("display","none");
			$("#emailChange").css("display","");
		}
		
	/* 휴대전화 */
		function phoneChange() {
			$("#phone").css("display","none");
			$("#phonec").css("display","");
			$("#phoneChange").css("display","none");
		}
		
		function cancle2() {
			$("#modPhone").val("");
			$("#phone").css("display","");
			$("#phonec").css("display","none");
			$("#phoneChange").css("display","");
		}
		
	/* 주소 */
		function addressChange() {
			$("#add").css("display","none");
			$("#addc").css("display","");
			$("#addressChange").css("display","none");
		}
		
		function cancle3() {
			$("#sample6_postcode").val("");
			$("#sample6_address").val("");
			$("#sample6_detailAddress").val("");
			$("#sample6_extraAddress").val("");
			
			$("#modAdd").val("");
			$("#add").css("display","");
			$("#addc").css("display","none");
			$("#addressChange").css("display","");
		}
	
	<!-- 비밀번호 수정 ajax -->
	$(function(){
		$("#modPw").keyup(function(){
			var modPw = $("#modPw").val();
			if(modPw.length < 4 || modPw.length > 12){
				$("#error_pw").html("비밀번호는 4 ~ 12 자리입니다.")
			}else if(modPw.indexOf(" ") >= 0){
				$("#error_pw").html("비밀번호에 공백을 사용할 수 없습니다.")
			} else {
				$("#error_pw").html("");
			}
		})
	});
		function modifyPw(){
			console.log("modifyPw()") /* 작동여부 확인 */
			var loginId = $("#loginId").val();
			var mpw = $("#mpw").val();		
			var modPw = $("#modPw").val(); /* 바꿀 비밀번호 변수선언 */
			var checkModPw = $("#checkModPw").val(); /* 바꿀 비밀번호 한번 더 체크 변수선언 */
			console.log("loginId : " + loginId + " mpw : " + mpw) /* 변수에 잘 저장되나 확인 */
			console.log("modPw : " + modPw + "checkModPw : " + checkModPw) /* 변수에 잘 저장되나 확인 */
			
			/* 현재 비밀번호 일치하는지 확인 [로그인아이디 값을 넘겨서 결과값 받아옴] */
			$.ajax({
					type : "get",
					url : "pwCheck",
					data : {"mpw" : mpw,
							"mid" : loginId},
					success : function(checkResult){
						console.log("checkResult : " + checkResult);
						console.log(typeof(checkResult))
						
						if(checkResult == 0){ /* 현재 비밀번호가 다르면 결과값이 0 나옴*/
							/* 경고창 띄운 후 */
							alert("현재 비밀번호가 일치하지 않습니다!"); 
							
							/* 입력된 데이터 창 초기화 후 focus */
							$("#modPw").val("");
							$("#checkModPw").val("");
							$("#mpw").focus();
						} else { /* 현재 비밀번호가 같으면 결과값 1 나옴. 다음 단계 진행. */
							if(modPw != checkModPw) {	/* 신규 비밀번호와 재입력 비밀번호가 일치하지 않을시 */
								/* 경고창 띄운 후 */
								alert("비밀번호가 일치하지 않습니다!"); 
							
								/* 입력된 데이터 창 초기화 후 focus */
								$("#modPw").val("");
								$("#checkModPw").val("");
								$("#modPw").focus();
							} else {	/* 신규 비밀번호와 재입력 비밀번호가 일치하면 업데이트 진행. */
								$.ajax({
										type : "get",
										url : "modifyPw",
										data : {"mid" : loginId,
												"mpw" : modPw},
										success : function(modifyResult){
											console.log("modifyResult : " + modifyResult);
											alert("변경 완료되었습니다.");
											location.reload(); /* 비밀번호 변경완료되면 페이지 새로고침 */
										}
								});
							}
							
						}
					}
			});
			/* 
			if(modPw != checkModPw) { /* 비밀번호 일치하지 않을시 
				alert("비밀번호가 일치하지 않습니다!"); /* 경고창 띄운 후 
				/* 입력된 데이터 창 초기화 후 focus 
				$("#modPw").val("");
				$("#checkModPw").val("");
				$("#modPw").focus();
			} else {
				$.ajax({
						type : "get",
						url : ,
						data : {},
						dateType : ,
						success : function(result){
							
						}
				})
			}
			*/
		}
	
		<!-- 이메일 수정 ajax -->
		function modifyEmail(){
			console.log("modifyEmail()")/* 작동여부 확인 */
			var loginId = $("#loginId").val();
			var modEmail = $("#modEmail").val(); /* 바꿀 이메일 변수선언 */
			console.log("loginId : " + loginId + " modEmail : " + modEmail) /* 변수에 잘 저장되나 확인 */
			
			$.ajax({
				type : "get",
				url : "modifyEmail",
				data : {"memail" : modEmail,
						"mid" : loginId},
				success : function(modifyResult){
					console.log("modifyResult : " + modifyResult);
					alert("변경 완료되었습니다.");
					location.reload(); /* 이메일 변경완료되면 페이지 새로고침 */
				}
			})
		}
		
		<!-- 전화번호 수정 ajax -->
		function modifyPhone(){
			console.log("modifyPhone()")/* 작동여부 확인 */
			var loginId = $("#loginId").val();
			var modPhone = $("#modPhone").val(); /* 바꿀 전화번호 변수선언 */
			console.log("loginId : " + loginId + " modPhone : " + modPhone) /* 변수에 잘 저장되나 확인 */
			
			$.ajax({
				type : "get",
				url : "modifyPhone",
				data : {"mphone" : modPhone,
						"mid" : loginId},
				success : function(modifyResult){
					console.log("modifyResult : " + modifyResult);
					alert("변경 완료되었습니다.");
					location.reload(); /* 전화번호 변경완료되면 페이지 새로고침 */
				}
			})
		}
		
		<!-- 주소 수정 ajax -->
		function modifyAddress(){
			console.log("modifyAddress()")/* 작동여부 확인 */
			var loginId = $("#loginId").val();
			
			var postcode = $("#sample6_postcode").val();
			var address = $("#sample6_address").val();
			var detailAddress = $("#sample6_detailAddress").val();
			var extraAddress = $("#sample6_extraAddress").val();
			
			console.log("loginId : " + loginId) /* 변수에 잘 저장되나 확인 */
			
			$.ajax({
				type : "get",
				url : "modifyAddress",
				data : {"postcode" : postcode,
						"address" : address,
						"detailAddress" : detailAddress,
						"extraAddress" : extraAddress,
						"mid" : loginId},
				success : function(modifyResult){
					console.log("modifyResult : " + modifyResult);
					alert("변경 완료되었습니다.");
					location.reload(); /* 주소 변경완료되면 페이지 새로고침 */
				}
			})
		}
	</script>
</html>


