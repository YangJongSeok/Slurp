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
		table,td,tr{
			border-collapse: collapse;
			font-size: 15px;
		}
		td{
			border-bottom: 1px solid #e6e6e6;
			color: black;
			padding: 7px;
		}
		input[type="text"] {
    		height: 30px !important;
    		width: 250px;
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
		}
		.btn1:hover{
			background-color: gray;
			color: white;
		}
		.btn1:focus{
			outline: 0;
			border: 0;
		}
		a{
			color: black;
		}
		pre{
			font-family: 'Do Hyeon', sans-serif;
			font-size: 15px;
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
	
			<div style="min-height: 700px;">

				<table style="width: 100%">
				
				<!-- *이벤트 게시물 없을때* -->
				<c:choose>
					<c:when test="${eventList == null }">
						<tr>
							<td colspan="3" style="text-align: center; font-size: 20px;">진행 중인 이벤트가 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${eventList }" var="eventList">
							<tr>
								<td style="width: 15%; text-align: center;">${eventList.e_num }</td><!-- 글번호 -->
								<td><a href="eventView?e_num=${eventList.e_num }">${eventList.etitle }</a></td>  				   <!-- 글제목 -->
							</tr>
						</c:forEach>
					</c:otherwise>					
				</c:choose>
					
				</table>
				
			
			
			<!-- 관리자만 작성가능 -->
			<c:if test="${sessionScope.authority == 1 }">
			<button style="float: right;" class="btn1" onclick="location.href='eventWriteForm'">작성하기</button>
			</c:if>
			
			</div>
			
			<div class="row" style="margin-top: 70px;">
					<div class="col-md-12 text-center">
						<div class="block-27">
		               <ul>
			               <!-- 페이징 처리 -->
						<c:choose>
							<c:when test="${page.page <= 1 }">
								<li><a><i class="fas fa-chevron-left"></i></a></li>
							</c:when>
							<c:otherwise>

										<li><a href="#?page=${page.page - 1}"><i class="fas fa-chevron-left"></i></a></li>

							</c:otherwise>
						</c:choose>
					
						<c:forEach begin="${page.startpage}" end="${page.endpage}" var="pageNum" step="1">
							<c:choose>
								<c:when test="${page.page == pageNum}">
								<li><a style="background-color: #cccccc;">${pageNum}</a></li>
								</c:when>
								<c:otherwise>

										<li><a href="#?page=${pageNum}">${pageNum}</a></li>
																
								</c:otherwise>
							</c:choose>
						</c:forEach>
					
						<c:choose>
							<c:when test="${page.page >= page.maxpage }">
								<li><a><i class="fas fa-chevron-right"></i></a></li>
							</c:when>
							<c:otherwise>

									<li><a href="#?page=${page.page + 1}"><i class="fas fa-chevron-right"></i></a></li>
		
							</c:otherwise>
						</c:choose>
						
		               </ul>
		            </div>
					</div>
				</div>
			
		</div>
		
		
		
		</div>
		<!-- footer -->
		<%@include file="../footer.jsp" %>

	</body>
	
	<script type="text/javascript">
	
		
	</script>
	
	
</html>


