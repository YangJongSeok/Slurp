<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Slurp</title>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- header -->
	<%@include file="../../header.jsp" %>
	<style type="text/css">
		.cover{
		width: 300px;
		height: 300px;
		object-fit: cover;
		}
	</style>	
	
</head>
	
	<body>
		
	<div class="colorlib-loader"></div>
	
	<div id="page">
	
		<!-- 상단메뉴 시작 -->
		<%@include file="../../topbar.jsp" %>
		<!-- 상단메뉴 끝 -->
		
		
		
		<!-- 상품 리스트 시작 -->
		<div class="colorlib-product">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 offset-sm-2 text-center colorlib-heading colorlib-heading-sm">
						<h6>　</h6>
					</div>
				</div>
					<div  class="col-md-12">
						<div class="total-wrap">
							<div class="row">
								<div class="col-sm-8">
									<h6 style="color: gray;" >Slurp | 남성 | 아우터
									<c:if test="${type == 1 }">| 인기순</c:if>
									<c:if test="${type == 2 }">| 최신순</c:if>
									<c:if test="${type == 3 }">| 낮은가격순</c:if>
									<c:if test="${type == 4 }">| 높은가격순</c:if>
									</h6> <!-- 메뉴 -->
								</div>
								<div class="col-sm-4" style="text-align: right;">
									<h7><a href="man_outer_best">인기순</a> | <a href="man_outer_new">최신순</a> | <a href="man_outer_row">낮은 가격순</a> | <a href="man_outer_high">높은 가격순</a><h7> <!-- 순위 정렬 -->
								</div>
							</div>
						</div>
						
					</div>
						
				<div class="row row-pb-md">  
					<c:forEach items="${manouter }" var="mo">								
					<div class="col-md-3 col-lg-3 mb-4 text-center">	
						<div class="product-entry border">
							<a href="goodsView?gcode=${mo.gcode }" class="prod-img">
								<img src="${pageContext.request.contextPath }/resources/images/goods/${mo.gimg}" class="img-fluid cover" alt="">
							</a> <!-- 상품 사진 -->
							<div class="desc">
								<h2><a href="goodsView?gcode=${mo.gcode }">${mo.cname }</a></h2> <!-- 업체 명 -->
								<h6><a href="goodsView?gcode=${mo.gcode }">${mo.gname }</a></h6> <!-- 상품 명 -->
								<span class="price">${mo.gprice }</span> <!-- 상품 가격 -->
							</div>
						</div>
					</div> 												
					</c:forEach>	
				</div> 																			
					
																	
					
				</div>																				
				<div class="row">
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
									<c:when test="${type == 1}"> <!-- 정렬별 페이징 -->
										<li><a href="man_outer_best?page=${page.page - 1}"><i class="fas fa-chevron-left"></i></a></li>
									</c:when>
									<c:when test="${type == 2}"> <!-- 정렬별 페이징 -->
										<li><a href="man_outer_new?page=${page.page - 1}"><i class="fas fa-chevron-left"></i></a></li>
									</c:when>
									<c:when test="${type == 3}"> <!-- 정렬별 페이징 -->
										<li><a href="man_outer_row?page=${page.page - 1}"><i class="fas fa-chevron-left"></i></a></li>
									</c:when>
									<c:when test="${type == 4}"> <!-- 정렬별 페이징 -->
										<li><a href="man_outer_high?page=${page.page - 1}"><i class="fas fa-chevron-left"></i></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="man_outer?page=${page.page - 1}"><i class="fas fa-chevron-left"></i></a></li>
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
									<c:when test="${type == 1}"> <!-- 정렬별 페이징 -->
										<li><a href="man_outer_best?page=${pageNum}">${pageNum}</a></li>
									</c:when>
									<c:when test="${type == 2}"> <!-- 정렬별 페이징 -->
										<li><a href="man_outer_new?page=${pageNum}">${pageNum}</a></li>
									</c:when>
									<c:when test="${type == 3}"> <!-- 정렬별 페이징 -->
										<li><a href="man_outer_row?page=${pageNum}">${pageNum}</a></li>
									</c:when>
									<c:when test="${type == 4}"> <!-- 정렬별 페이징 -->
										<li><a href="man_outer_high?page=${pageNum}">${pageNum}</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="man_outer?page=${pageNum}">${pageNum}</a></li>
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
								<c:when test="${type == 1}"> <!-- 정렬별 페이징 -->
										<li><a href="man_outer_best?page=${page.page + 1}"><i class="fas fa-chevron-right"></i></a></li>
								</c:when>
								<c:when test="${type == 2}"> <!-- 정렬별 페이징 -->
										<li><a href="man_outer_new?page=${page.page + 1}"><i class="fas fa-chevron-right"></i></a></li>
								</c:when>
								<c:when test="${type == 3}"> <!-- 정렬별 페이징 -->
										<li><a href="man_outer_row?page=${page.page + 1}"><i class="fas fa-chevron-right"></i></a></li>
								</c:when>
								<c:when test="${type == 4}"> <!-- 정렬별 페이징 -->
										<li><a href="man_outer_high?page=${page.page + 1}"><i class="fas fa-chevron-right"></i></a></li>
								</c:when>
								<c:otherwise>
									<li><a href="man_outer?page=${page.page + 1}"><i class="fas fa-chevron-right"></i></a></li>
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
		
		<!-- 상품 리스트 끝 -->
		
		<!-- 하단메뉴 시작 -->
		<%@include file="../../footer.jsp" %>
		<!-- 하단메뉴 끝 -->
	</body>

</html>