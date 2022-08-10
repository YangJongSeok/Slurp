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
<link
	href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap"
	rel="stylesheet">


<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
<!-- Modal 추가소스 -->
<script src="resources/js/bootstrap-modal-wrapper-factory.js"></script>

<style type="text/css">
	#logo {
  font-size: 40px;
  margin: 0;
  padding: 0;
  font-weight: bold;
  position: relative;
  color: #88c8bc;
  text-decoration: none;
  width: 240px;
  height: 44px;
  cursor: pointer;
}

html {
	height: 100%;
}

#header {
	padding-top: 62px;
	padding-bottom: 20px;
	text-align: center;
}

body {
	margin: 0;
	height: 100%;
	background: #f5f6f7;
	font-family: 'Do Hyeon', sans-serif;
}

#wrapper {
	position: relative;
	height: 100%;
}

#content {
	position: absolute;
	transform: translate(-50%);
	width: 1000px;
	left: 50%;
	text-align: center;
}

table{
	width: 100%;
	
}

th{
	background-color: #88c8bc;
	font-size: 18px;
	border-bottom: 1px black solid;
	color: white;
	letter-spacing: 1px;
	font-family: fantasy;
}

td{
	background-color: white;
	
	
}

table,th,td{
	
	height: 30px;
}

.btn {
	color: black;
	font-family: 'Do Hyeon', sans-serif;
	font-size: 15px;
	font-weight: 100;
	cursor: pointer;
	margin-left: 5px;
	margin-top: 5px;
	margin-bottom: 5px;
	border-radius: 10px;
	background-color: white;
	border: 1px #88c8bc solid;
	padding-top: 3px;
	padding-bottom: 3px;
}

.btn:hover {
	background-color: #88c8bc;
	color: white;
}
	
	
.btn:focus {
	outline: 0;
	border: 0;
}

.pagebtn {
	font-family: 'Do Hyeon', sans-serif;
	background-color: white;
	border-radius: 5px;
	cursor: pointer;
	font-size: 15px;
	margin-top: 20px;
	border: 1px #88c8bc solid;
	padding-left: 10px;
	padding-right: 10px;
	
}

.pagebtn:focus{
	outline: 0;
	border: 0;
}

.pagebtnX {
	font-family: 'Do Hyeon', sans-serif;
	background-color: white;
	border-radius: 5px;
	font-size: 15px;
	margin-top: 20px;
	border: 1px #88c8bc solid;
	padding-left: 10px;
	padding-right: 10px;
	color: gray;
}
	
	
</style>

</head>
<body>
	<!-- header -->
	<div id="header">

		<h2 style="font-weight: bold;">
			<a href="home" id="logo">Slurp</a>
		</h2>
		<h4 style="font-weight: bold;">
			<a>관리자 전용</a>
		</h4>
	</div>
		

	<!-- wrapper -->
	<div id="wrapper">

		<!-- content -->
		<div id="content">

			<table>
				<tr>
					<th style="width: 20%;">업체코드</th>
					<th style="width: 30%;">업체명</th>
					<th style="width: 20%;">승인상태</th>
					<th style="width: 30%;"></th>
					
				</tr>
				<c:forEach items="${cList }" var="cl">
				<tr>
					<td>${cl.ccode }</td>
					<td>${cl.cname }</td>
					<td>승인필요</td>
					<td>
					<button onclick="accept('${cl.ccode}')" class="btn">승인</button>
					<button onclick="refusal('${cl.ccode}')" class="btn">거절</button>
					</td>
				</tr>
				</c:forEach>
			</table>
	
			
        		
        		
        		
        		
					
					<!-- 페이징 처리 -->
					<c:choose>
						<c:when test="${page.page <= 1 }">
							<button class="pagebtnX" disabled="disabled">이전</button>
						</c:when>
						<c:otherwise>
							<button class="pagebtn" onclick="location.href='companyCheckForm?page=${page.page - 1}'">이전</button>
						</c:otherwise>
					</c:choose>
					
					<c:forEach begin="${page.startpage}" end="${page.endpage}" var="pageNum" step="1">
						<c:choose>
							<c:when test="${page.page == pageNum}">
								<button class="pagebtnX" disabled="disabled">${pageNum}</button>
							</c:when>
							<c:otherwise>
								<button class="pagebtn" onclick="location.href='companyCheckForm?page=${pageNum}'">${pageNum}</button>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
					<c:choose>
						<c:when test="${page.page >= page.maxpage }">
							<button class="pagebtnX" disabled="disabled">다음</button>
						</c:when>
						<c:otherwise>
							<button class="pagebtn" onclick="location.href='companyCheckForm?page=${page.page + 1}'">다음</button>
						</c:otherwise>
					</c:choose>
				
				
		</div>
		<!-- content-->

	</div>
	<!-- wrapper -->
</body>
<script type="text/javascript">
		
		function accept(ccode) {
			BootstrapModalWrapperFactory.confirm({
		        title: "업체 승인",
		        message: "승인 하시겠습니까?",
		        onConfirmAccept: function () {
		            location.href="companyAccept?ccode="+ccode;
		        }
		        
		    });
		}
	
		function refusal(ccode) {
			BootstrapModalWrapperFactory.confirm({
		        title: "업체 승인",
		        message: "거절 하시겠습니까?",
		        onConfirmAccept: function () {
		            location.href="companyRefusal?ccode="+ccode;
		        }
		        
		    });
		}
</script>


</html>