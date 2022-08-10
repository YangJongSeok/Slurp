<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
	<head>
	<title>Slurp</title>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   
	
	<!-- header -->
	<%@include file="../header.jsp" %>
	
	<style type="text/css">
		th{
			text-align: center;
			border-top: 1px solid e6e6e6;
			font-size: 17px;
			font-weight: normal;
			border-bottom: 1px solid e6e6e6;
		}
		table,td,tr{
			border-collapse: collapse;
			font-size: 15px;;
		}
		td{
			border-bottom: 1px solid #e6e6e6;
			color: black;
			padding: 20px;
		}
		input[type="text"] {
    		height: 30px !important;
    		width: 250px;
    		font-size: 18px;
		}
		textarea{
			font-size: 18px;
		}
		.btn1{
			color: black;
			font-family: 'Do Hyeon', sans-serif;
			font-size: 15px;
			font-weight: 100;
			cursor: pointer;
			border-radius: 5px;
			background-color: #f0f0f0;
			border: 0;
			padding: 5px 10px 5px 10px;
			margin-top: 20px;
			letter-spacing: 1px;
		}
		.btn1:hover{
			background-color: gray;
			color: white;
		}
		.btn1:focus{
			outline: 0;
			border: 0;
		}
	</style>

	</head>
	
	<body>
		
	
	
	<div id="page">
	
		<!-- 상단메뉴 시작 -->
		<%@include file="../topbar.jsp" %>
		<!-- 상단메뉴 끝 -->
		<div class="container" style="margin-top: 150px;">
			<div style="background-color: #f0f0f0; margin-bottom: 50px;">
			<h5 style="margin-left: 10px; letter-spacing: 2px;">이벤트 게시판</h5></div>
			<div style="margin-bottom: 50px;">
			
				<div style="width: 80%; margin-left: 107px; margin-bottom: 50px; min-height: 700px;">
					
					<!-- 이미지 선택 잘해야됨 화질깨짐 -->
					<img style="width: 100%;" src="${pageContext.request.contextPath}/resources/images/event/${eventDTO.eimg }">
					
					
					<hr>
					
					
					
				</div>
							
				
			
				<button class="btn1" style="margin-left: 20px; width: 50px;" onclick="location.href='eventList'">목록</button>
				
				<!-- 관리자로그인시 삭제버튼 출력 (하단 스크립트에 Modal 예제) -->
				<c:if test="${sessionScope.authority == 1 }">
				<button class="btn1" style="margin-left: 20px; width: 50px; float: right;" onclick="location.href='eventDelete?e_num=${eventDTO.e_num}'">삭제</button>
				</c:if>
			</div>
			
			
		</div>
		
		
		
		</div>
		<!-- footer -->
		<%@include file="../footer.jsp" %>

	</body>
	
	<!-- Modal 추가소스 -->
	<script src="resources/js/bootstrap-modal-wrapper-factory.js"></script>
	
	<script type="text/javascript">
			
			
			// Modal 사용 삭제 버튼 클릭시 알림창 출력
			function #() {
				BootstrapModalWrapperFactory.confirm({
			        title: "이벤트 게시물 삭제",
			        message: "정말 삭제 하시겠습니까?",
			        onConfirmAccept: function () {
			            location.href="#";
			        }
			    });
			}
			
		
			
	</script>
	
	
</html>
				
			


