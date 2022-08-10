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
					<div class="col-md-10 offset-md-1">
						<div class="process-wrap">
							<div class="process text-center active">
								<p><span>01</span></p>
								<h3 style="font-size: 15px;">장바구니</h3>
							</div>
							<div class="process text-center">
								<p><span>02</span></p>
								<h3 style="font-size: 15px;">결제 중</h3>    <!-- 진행 과정 -->
							</div>
							<div class="process text-center">
								<p><span>03</span></p>
								<h3 style="font-size: 15px;">결제 완료</h3>
							</div>
						</div>
					</div>
				</div>
				<div class="row row-pb-lg">
					<div class="col-md-12">
						<div class="product-name d-flex">
							<div class="one-forth text-left px-4">
								<span>장바구니에 담은 상품</span> <!-- 상품명 -->
							</div>
							<div class="one-eight text-center"> 
								<span>가격</span>
							</div>
							<div class="one-eight text-center">
								<span>수량</span>
							</div>
							<div class="one-eight text-center">
								<span>총 가격</span>
							</div>
							<div class="one-eight text-center px-4">
								<span>삭제</span>
							</div>
						</div>
						
						<c:if test="${sum == 0 }">
								<div class="product-cart">
									<p style="text-align: center;">장바구니가 비어있습니다.</p>
								</div>
						</c:if>
						

						<c:forEach items="${cartgoodsDTO }" var="cartgoodsDTO">
						<div class="product-cart d-flex">
							<div class="one-forth">
								<div class="product-img" style="background-image: url('${pageContext.request.contextPath }/resources/images/goods/${cartgoodsDTO.gimg}');">
								</div> 
								<div class="display-tc">
									<h3>${cartgoodsDTO.gname} / ${cartgoodsDTO.gcolor } / ${cartgoodsDTO.gsize }</h3> <!-- 상품 명 -->
								</div>
							</div>
							<div class="one-eight text-center">
								<div class="display-tc">
									<span class="price" id="price">${Math.round(cartgoodsDTO.gprice / cartgoodsDTO.gstock) }</span> <!-- 가격 -->
								</div>
							</div>
							<div class="one-eight text-center">
								<div class="display-tc">
									<input type="number" id="${cartgoodsDTO.cg_code }quantity" name="quantity" class="form-control input-number text-center" value="${cartgoodsDTO.gstock }" min="1" max="100" onchange="stockChange('${cartgoodsDTO.cg_code }', '${cartgoodsDTO.gcode}', '${cartgoodsDTO.gcolor }', '${cartgoodsDTO.gsize }', '${Math.round(cartgoodsDTO.gprice / cartgoodsDTO.gstock) }', '${cartgoodsDTO.gprice }')">
								</div> <!-- 수량 -->
							</div>
							<div class="one-eight text-center">
								<div class="display-tc">
									<span id="${cartgoodsDTO.cg_code }total" class="price">${cartgoodsDTO.gprice }</span> <!-- 총 가격 -->
								</div>
							</div>
							<div class="one-eight text-center">
								<div class="display-tc">
									<a href="#" class="closed" onclick="cartDelete('${cartgoodsDTO.cg_code }', '${sessionScope.loginId}')"></a> <!-- 취소 버튼 -->
								</div>
							</div>
						</div>
					</c:forEach>
						
					</div>
				</div>
				
				<div class="row row-pb-lg">
					<div class="col-md-12">
						<div class="total-wrap">
							<div class="row">
								<div class="col-sm-3 text-center">
								</div>
								<div class="col-sm-6 text-center">
									<div class="total">
										<div class="sub">
											<p><span>상품 금액 :</span><span> +<span id="sum">${sum }</span>원</span></p>
											<p><span>배달료 : </span><span>+<span id="fee">${fee }</span>원</span></p>
											<c:choose>
												<c:when test="${membershipDTO.m_rating eq '일반회원' }">
													<p><span>적립 : </span> <span> 0% (일반회원) </span></p> <!-- 수정 -->
												</c:when>
												<c:when test="${membershipDTO.m_rating eq 'Bronze' }">
													<p><span>적립 : </span> <span> 0.1% (브론즈) </span></p> <!-- 수정 -->
												</c:when>
												<c:when test="${membershipDTO.m_rating eq 'Silver' }">
													<p><span>적립 : </span> <span> 0.5% (실버) </span></p> <!-- 수정 -->
												</c:when>
												<c:when test="${membershipDTO.m_rating eq 'Gold' }">
													<p><span>적립 : </span> <span> 1% (골드) </span></p> <!-- 수정 -->
												</c:when>
												<c:when test="${membershipDTO.m_rating eq 'VIP' }">
													<p><span>적립 : </span> <span> 5% (VIP) </span></p> <!-- 수정 -->
												</c:when>
											</c:choose>
										</div>
										<div class="grand-total">
											<p><span><strong>총 가격 : </strong></span> <span id="totalPrice"> ${totalPrice } </span></p>
										</div>
									</div>
								</div>
								<div class="col-sm-3 text-center">
								</div>
								
								<input type="button" value="결제 진행" onclick="purchase('${sessionScope.loginId }')" class="btn btn-primary" style="margin: auto; margin-top: 10px;">
							</div>
						</div>
					</div>
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
	function cartDelete(cg_code, mid) {
		location.href='cartDelete?mid='+mid+'&cg_code='+cg_code; 
	}
	
	// 장바구니 수량 변경
	function stockChange(cg_code, gcode, gcolor, gsize, gprice, origprice) {
		
		var mid = '${sessionScope.loginId}'; // 아이디
		var gstock = $("#"+cg_code+"quantity").val(); // 제품하나의 수량
 		var sum = $("#sum").text(); // 모든 물품의 합 가격
 		var fee = $("#fee").text(); // 배달료
		
 		// 가격 계산
 		// origprice = 원래가격 빼기용
		sum = sum - origprice + (gprice * gstock); // 총가격
		
		// origprice = 버튼에 다시 세팅용
		origprice = gprice * gstock; 
		
		$.ajax({
			type : "post",
			url : "stockChange",
			data : { "cg_code" : cg_code,
					 "mid" : mid,
					 "gcode" : gcode,
					 "gcolor" : gcolor,
					 "gsize" : gsize,
					 "gstock" : gstock,
					 "gprice" : gprice
 					},
			success : function(result) {
				if(result == "1") {
					$("#"+cg_code+"total").html(gprice*gstock);
					// 배달료 붙을 때
	 				if(sum > 0 && sum < 50000) {
	 					fee = 2500;
	 					$("#fee").text(fee);
	 					$("#sum").text(sum);
	 					$("#totalPrice").text(sum+fee);
					// 배달료 안붙을 때
	 				} else if (sum >= 50000) {
	 					// 배달료가 있었다가 없어질 때
	 					if(fee == 2500) {
	 						$("#fee").text("0");
			 				$("#sum").text(sum);
			 				$("#totalPrice").text(sum);
			 			// 계속 배달료 없을 때
	 					} else {
	 						$("#fee").text("0");
			 				$("#sum").text(sum);
			 				$("#totalPrice").text(sum);
	 					}
	 				}
	 				$("#"+cg_code+"quantity").removeAttr("onchange");
	 				$("#"+cg_code+"quantity").attr("onchange", "stockChange('"+cg_code+"', '"+gcode+"', '"+gcolor+"', '"+gsize+"', '"+gprice+"', '"+origprice+"')");
				} else {
					alert("수량을 확인해주세요.\n남은수량은 "+result+" 개 입니다.");
				}
			}
		});
	}
	
	// 여러건 결제페이지 이동
	function purchase(mid) {
		var totalPrice = '${totalPrice }';
		
		if(totalPrice > 0) {
			location.href="goodsManyPurchase?mid="+mid;
		} else {
			alert("장바구니에 물건이 없습니다.");
		} 
	}
</script>
</html>