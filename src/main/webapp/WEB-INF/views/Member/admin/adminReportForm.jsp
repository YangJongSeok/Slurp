<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Slurp</title>
<!-- 폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<!-- css -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
<!-- query / bootstrap -->
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
    
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
	font-family: 'Do Hyeon', sans-serif;
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

table {
	width: 100%;
}

th {
	background-color: #88c8bc;
	font-size: 18px;
	border-bottom: 1px black solid;
	color: white;
	letter-spacing: 1px;
	font-family: fantasy;
	text-align: left;
	padding-left: 20px;
}

td {
	background-color: white;
}

table, th, td {
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
	width: 60px;
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

.pagebtn:focus {
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

pre {
	font-family: 'Do Hyeon', sans-serif;
	font-size: 15px;
	white-space: pre-wrap;
	text-align: left;
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
					<th style="width: 20%;">신고 번호</th>
					<th style="width: 60%;">신고 내용</th>
					<th style="width: 20%;">신고자</th>
				</tr>

				<c:forEach items="${rList }" var="rl">

					<tr>
						<td>${rl.rr_num }</td>
						<td>
						<pre style="padding-top: 10px;"><a style="cursor: pointer;" onclick="none('${rl.rr_num}')">${rl.rr_content }</a></pre>
						</td>
						<td>${rl.mid }</td>
					</tr>
					<!-- 가려진 display -->
					<tr style="display: none;" id="${rl.rr_num }">
						<!-- Ajax -->
						
					</tr>
				</c:forEach>
			</table>

			<!-- 페이징 처리 -->
			<c:choose>
				<c:when test="${page.page <= 1 }">
					<button class="pagebtnX" disabled="disabled">이전</button>
				</c:when>
				<c:otherwise>
					<button class="pagebtn"
						onclick="location.href='adminReportForm?page=${page.page - 1}'">이전</button>
				</c:otherwise>
			</c:choose>

			<c:forEach begin="${page.startpage}" end="${page.endpage}"
				var="pageNum" step="1">
				<c:choose>
					<c:when test="${page.page == pageNum}">
						<button class="pagebtnX" disabled="disabled">${pageNum}</button>
					</c:when>
					<c:otherwise>
						<button class="pagebtn"
							onclick="location.href='adminReportForm?page=${pageNum}'">${pageNum}</button>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:choose>
				<c:when test="${page.page >= page.maxpage }">
					<button class="pagebtnX" disabled="disabled">다음</button>
				</c:when>
				<c:otherwise>
					<button class="pagebtn"
						onclick="location.href='adminReportForm?page=${page.page + 1}'">다음</button>
				</c:otherwise>
			</c:choose>


		</div>
		<!-- content-->

	</div>
	<!-- wrapper -->
</body>
<script type="text/javascript">
		
		function none(rr_num) {
			if($("#"+rr_num).css("display") == "none"){
				$("#"+rr_num).css("display","");

				$.ajax({
					type : "post",
					url : "goodsReplyAjax",
					data : {"rr_num" : rr_num},
					success : function(result) {
						var output = "";
						output += "<td style='background-color: #fafafa; width: 20%'>";
						
						if(result.r_img3 != null){
							output += "<a href='${pageContext.request.contextPath }/resources/images/review/"+result.r_img1+"'><img style='width: 50px' src='${pageContext.request.contextPath }/resources/images/review/"+result.r_img1+"'></a>";
							output += "<a href='${pageContext.request.contextPath }/resources/images/review/"+result.r_img2+"'><img style='width: 50px' src='${pageContext.request.contextPath }/resources/images/review/"+result.r_img2+"'></a>";
							output += "<a href='${pageContext.request.contextPath }/resources/images/review/"+result.r_img3+"'><img style='width: 50px' src='${pageContext.request.contextPath }/resources/images/review/"+result.r_img3+"'></a>";
						}else if(result.r_img2 != null){
							output += "<a href='${pageContext.request.contextPath }/resources/images/review/"+result.r_img1+"' class='image-popup-no-margins'><img style='width: 40px' src='${pageContext.request.contextPath }/resources/images/review/"+result.r_img1+"'></a>";
							output += "<a href='${pageContext.request.contextPath }/resources/images/review/"+result.r_img2+"' class='image-popup-no-margins'><img style='width: 40px' src='${pageContext.request.contextPath }/resources/images/review/"+result.r_img2+"'></a>";
						}else if(result.r_img1 != null){
							output += "<a href='${pageContext.request.contextPath }/resources/images/review/"+result.r_img1+"' class='image-popup-no-margins'><img style='width: 40px' src='${pageContext.request.contextPath }/resources/images/review/"+result.r_img1+"'></a>";
						}
						output += "</td>";
						output += "<td style='padding: 20px; background-color: #fafafa;' colspan='2'>";
						output += "<pre style='padding-left: 30px;'>"+result.r_reply+"</pre>";
						output += "<div style='float: right;'>";
						output += "<button class='btn' onclick='reportAccess("+result.r_num+")'>승인</button>";
						output += "<button class='btn' onclick='reportRefusal("+result.r_num+")'>거절</button>";
						output += "</div>";
						output += "</td>";
						
					$("#"+rr_num).html(output);
					}
				})
				
				
			}else{
				$("#"+rr_num).css("display","none");
			}
		}
		
		// 승인하면 평가,신고 둘 다 삭제
		function reportAccess(r_num) {
			BootstrapModalWrapperFactory.confirm({
		        title: "신고 승인수락",
		        message: "승인 하시겠습니까?",
		        onConfirmAccept: function () {
		            location.href="reportAccess?r_num="+r_num;
		        }
		        
		    });
		}

		// 거절하면 신고만 삭제
		function reportRefusal(r_num) {
			BootstrapModalWrapperFactory.confirm({
		        title: "신고 승인거절",
		        message: "거절 하시겠습니까?",
		        onConfirmAccept: function () {
		            location.href="reportRefusal?r_num="+r_num;;
		        }
		        
		    });
		}
</script>

</html>