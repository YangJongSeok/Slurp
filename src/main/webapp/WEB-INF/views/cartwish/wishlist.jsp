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
</head>
	
	<body>
		
	<div class="colorlib-loader"></div>
	
	<div id="page">
	
		<!-- 상단메뉴 시작 -->
		<%@include file="../topbar.jsp" %>
		<!-- 상단메뉴 끝 -->
		
		<div class="colorlib-product">
			<div class="container">
				<div class="row">
						<div class="col-sm-8 offset-sm-2 text-center colorlib-heading colorlib-heading-sm">
							<h6>　</h6>
						</div>
					</div>
				
				<div class="row row-pb-lg">
					<div class="col-md-12">
						<div class="product-name d-flex">
							<div class="one-forth text-left px-4">
								<span>내가 찜한 상품</span> <!-- 상품명 -->
							</div>
							<div class="one-eight text-center">
								
							</div>
							<div class="one-eight text-center">
								
							</div>
							<div class="one-eight text-center"> 
								<span>가격</span>
							</div>
							<div class="one-eight text-center px-4">
								<span>삭제</span>
							</div>
						</div>
						
						<c:forEach items="${wishlist }" var="wishlist"> 
							<div class="product-cart d-flex">
								<div class="one-forth">
									<div class="product-img" style="background-image: url('${pageContext.request.contextPath }/resources/images/goods/${wishlist.gimg}');">
									</div> 
									<div class="display-tc">
										<h3>${wishlist.gname }</h3> <!-- 상품 명 -->
									</div>
								</div>
								<div class="one-eight text-center">
									<div class="display-tc">
										<span> </span>
									</div> <!-- 수량 -->
								</div>
								<div class="one-eight text-center">
									<div class="display-tc">
										<span class="price"> </span> <!-- 총 가격 -->
									</div>
								</div>
								<div class="one-eight text-center">
									<div class="display-tc">
										<span class="price" id="price">${wishlist.gprice }원</span> <!-- 가격 -->
									</div>
								</div>
								<div class="one-eight text-center">
									<div class="display-tc">
										<a href="#" onclick="wishDelete('${wishlist.gcode }', '${sessionScope.loginId}')" class="closed"></a> <!-- 취소 버튼 -->
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				
				<div class="col-md-12" style="text-align: center;">
					<input type="button" onclick="goCart('${sessionScope.loginId }')" value="장바구니로 이동" class="btn btn-primary">
				</div>
			</div>
		</div>
		</div>
		<!-- 하단메뉴 시작 -->
		<%@include file="../footer.jsp" %>
		<!-- 하단메뉴 끝 -->
	</body>

<script>
	// 장바구니 삭제
	function wishDelete(gcode, mid) {
		location.href="wishListDelete?mid="+mid+"&gcode="+gcode;
	}
	// 카트로 이동
	function goCart(mid) {
		location.href="shoppingCart?mid="+mid;
	}
</script>

</html>