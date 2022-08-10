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
			padding: 10px;
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
			font-family: 'Noto Sans KR', sans-serif;
			white-space: pre-wrap;
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
			<h5 style="margin-left: 10px; letter-spacing: 2px;">자주묻는질문</h5></div>
	
				<div style="min-height: 300px;">
			
				<table style="width: 100%;">
					<c:forEach items="${fList }" var="fl">
					<tr>
						<td style="text-align: center; width: 7%;">${fl.fnum }</td>
						<td>
							<span style="margin-left: 20px;">
								<a href="javascript:void(0);" onclick="none('${fl.fnum}')">
								
								<!-- FAQ 카테고리 분류 -->
									[<c:choose>
										<c:when test="${fl.fcate == 1 }">배송문의</c:when>
										<c:when test="${fl.fcate == 2 }">반품/교환</c:when>
										<c:when test="${fl.fcate == 3 }">주문/결제</c:when>
									</c:choose>] ${fl.ftitle }
								</a>
							</span>
						
						<!-- 본문열기 -->
							<span style="float: right; margin-right: 20px;">
								<a href="javascript:void(0);" onclick="none('${fl.fnum}')"><i class="fas fa-chevron-down"></i></a>
							</span>
						</td>
					</tr>
					
					<!-- 질문내용 display -->
					<tr style="display: none; width: 7%;" id="${fl.fnum }">
						<td style="background-color: #fafafa;"></td>
						<td style="padding: 20px; background-color: #fafafa;">
							<div>
								<pre>${fl.fcontent}</pre>
							</div>
						</td>
					</tr>
					</c:forEach>
				</table>
				
			
			<!-- 검색기능 -->
			<form action="faqSearch" style="position:  absolute;">
				<select style="height: 30px;" name="type">
					<option value="title">제목</option>
					<option value="content">내용</option>
					<option value="tcon">제목/내용</option>
				</select>
				<input type="text" name="text">
				<button class="btn1">검색</button>
			</form>
			
			<!-- 관리자만 작성가능 -->
			<c:if test="${sessionScope.authority == 1 }">
			<button style="float: right;" class="btn1" onclick="location.href='faqWriteForm'">작성하기</button>
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
								<c:choose>
									<c:when test="${type != null && text != null }">
										<li><a href="faqSearch?page=${page.page - 1}&&type=${type}&&text=${text}"><i class="fas fa-chevron-left"></i></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="faqListForm?page=${page.page - 1}"><i class="fas fa-chevron-left"></i></a></li>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					
						<c:forEach begin="${page.startpage}" end="${page.endpage}" var="pageNum" step="1">
							<c:choose>
								<c:when test="${page.page == pageNum}">
								<li><a style="background-color: #cccccc;">${pageNum}</a></li>
								</c:when>
								<c:otherwise>
									<c:choose>
									<c:when test="${type != null && text != null }">
										<li><a href="faqSearch?page=${pageNum}&&type=${type}&&text=${text}">${pageNum}</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="faqListForm?page=${pageNum}">${pageNum}</a></li>
									</c:otherwise>
									</c:choose>																	
								</c:otherwise>
							</c:choose>
						</c:forEach>
					
						<c:choose>
							<c:when test="${page.page >= page.maxpage }">
								<li><a><i class="fas fa-chevron-right"></i></a></li>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${type != null && text != null }">
										<li><a href="faqSearch?page=${page.page + 1}&&type=${type}&&text=${text}"><i class="fas fa-chevron-right"></i></a></li>
									</c:when>
									<c:otherwise>
									<li><a href="faqListForm?page=${page.page + 1}"><i class="fas fa-chevron-right"></i></a></li>
									</c:otherwise>
								</c:choose>				
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
	
		function none(fnum) {
			
			if($('#'+fnum).css("display") == "none"){
				$('#'+fnum).css("display","");
			}else{
				$('#'+fnum).css("display","none")
			}
				
		}
		
	</script>
	
	
</html>


