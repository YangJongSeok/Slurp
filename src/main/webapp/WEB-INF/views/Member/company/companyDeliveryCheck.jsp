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
	font-size: 20px;
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
	width: 20%;
	margin-left: 5px;
	border-radius: 10px;
	background-color: white;
	border: 1px #88c8bc solid;
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
			<a>주문 확인</a>
		</h4>
	</div>
		

	<!-- wrapper -->
	<div id="wrapper">

		<!-- content -->
		<div id="content">

			<table>
				<tr>
					<th style="width: 15%;">주문번호</th>
					<th style="width: 45%;">제품명</th>
					<th style="width: 30%;">주소</th>
					<th style="width: 10%;"></th>
				</tr>
				<c:forEach items="${orderlistDTO }" var="orderlistDTO">
				<tr>
					<td style="width: 15%;">${orderlistDTO.o_code }</td>
					<td style="width: 45%;">${orderlistDTO.gname } / ${orderlistDTO.gcolor } / ${orderlistDTO.gsize} / ${orderlistDTO.o_quantity } 개</td>
					<td style="width: 20%;">${orderlistDTO.o_add }</td>
					<td style="width: 20%; text-align: center;">
						<input type="button" style="width: 60px;" value="접수" onclick="receipt('${orderlistDTO.o_code}', '${sessionScope.loginId }')" class="btn">
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
							<button class="pagebtn" onclick="location.href='companyDeliveryCheck?cid=${sessionScope.loginId }&page=${page.page - 1}'">이전</button>
						</c:otherwise>
					</c:choose>
					
					<c:forEach begin="${page.startpage}" end="${page.endpage}" var="pageNum" step="1">
						<c:choose>
							<c:when test="${page.page == pageNum}">
								<button class="pagebtnX" disabled="disabled">${pageNum}</button>
							</c:when>
							<c:otherwise>
								<button class="pagebtn" onclick="location.href='companyDeliveryCheck?cid=${sessionScope.loginId }&page=${pageNum}'">${pageNum}</button>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
					<c:choose>
						<c:when test="${page.page >= page.maxpage }">
							<button class="pagebtnX" disabled="disabled">다음</button>
						</c:when>
						<c:otherwise>
							<button class="pagebtn" onclick="location.href='companyDeliveryCheck?cid=${sessionScope.loginId }&page=${page.page + 1}'">다음</button>
						</c:otherwise>
					</c:choose>
				
				
		</div>
		<!-- content-->

	</div>
	<!-- wrapper -->
</body>

<script type="text/javascript">
	// 주문 접수
	function receipt(o_code, cid) {
		BootstrapModalWrapperFactory.confirm({
			title: "주문 접수",
			message: "주문접수 하시겠습니까?",
					onConfirmAccept: function () {
						location.href="deliveryCheck?o_code="+o_code+"&cid="+cid;
		        }
		    });
	}
</script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script><!-- jQuery CDN --->

</html>