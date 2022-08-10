<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Slurp</title>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- header -->
	<%@include file="../header.jsp" %>
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
		<%@include file="../topbar.jsp" %>
		<!-- 상단메뉴 끝 -->
		
		<!-- 상품 리스트 시작 -->
		<div class="colorlib-product">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 offset-sm-2 text-center colorlib-heading colorlib-heading-sm">
						<h6>　</h6>
					</div>
				</div>
				<h6 style="color: gray;">Slurp | <label style="color: black;">NEW</label></h6>
				
				
				<div class="row row-pb-md">  
					<c:forEach items="${goodsnew }" var="gn">								
					<div class="col-md-3 col-lg-3 mb-4 text-center">	
						<div class="product-entry border">
							<a href="goodsView?gcode=${gn.gcode }" class="prod-img">
								<img class="img-fluid cover" src="${pageContext.request.contextPath }/resources/images/goods/${gn.gimg }" alt="">
							</a> <!-- 상품 사진 -->
							<div class="desc">
								<h2><a href="goodsView?gcode=${gn.gcode }">${gn.cname }</a></h2> <!-- 업체 명 -->
								<h6><a href="goodsView?gcode=${gn.gcode }">${gn.gname }</a></h6> <!-- 상품 명 -->
								<span class="price">${gn.gprice }</span> <!-- 상품 가격 -->
							</div>
						</div>
					</div> 												
					</c:forEach>	
				</div> 																			
				</div>																				
			</div>
		</div>																						
		
		<!-- 상품 리스트 끝 -->
		
		<!-- 하단메뉴 시작 -->
		<%@include file="../footer.jsp" %>
		<!-- 하단메뉴 끝 -->
	</body>

</html>