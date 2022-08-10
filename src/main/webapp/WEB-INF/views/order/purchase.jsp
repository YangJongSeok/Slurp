<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Slurp</title>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- header -->
	<%@include file="../header.jsp" %>
	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<style type="text/css">
	.gdtimg{
		text-align: center;
	}
	.cover{
			width: 500px;
			height: 500px;
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
		
		<div class="colorlib-product">
			<div class="container">
			<div class="row">
					<div class="col-sm-8 offset-sm-2 text-center colorlib-heading colorlib-heading-sm">
						<h6>　</h6>
					</div>
				</div>
				<div class="row row-pb-lg product-detail-wrap">
					<div class="col-sm-8">
						<div class="product-entry">
							<!-- 800x600 사진 -->
							<img class="cover" src="${pageContext.request.contextPath }/resources/images/goods/${goodsView.gimg }" class="img-fluid" alt="상품이미지"> <!-- 상품 이미지1 -->
						</div>
					</div>
					<div class="col-sm-4">
						<div class="product-desc">
							<h3>${goodsView.gname }</h3> <!-- 성품 이름 -->
							<p class="price">
								<span>${goodsView.gprice }</span>  <!-- 가격 -->
					   				<c:choose>
					   					<c:when test="${rating == 5 }">
					   						<span class="rate">
							   					<i class="icon-star-full"></i>
							   					<i class="icon-star-full"></i>
							   					<i class="icon-star-full"></i> <!-- 별점 아이콘 --> 	<!-- full = 채워진 별 / -->
							   					<i class="icon-star-full"></i> 				 	<!-- half = 반만 채워진 별 / -->
							   					<i class="icon-star-full"></i>						<!-- empty = 비워진 별 / -->
							   					(${replyGoodsCount } Rating)
				   							</span>
					   					</c:when>
					   					<c:when test="${rating >= 4.5 }">
					   						<span class="rate">
							   					<i class="icon-star-full"></i>
							   					<i class="icon-star-full"></i>
							   					<i class="icon-star-full"></i> <!-- 별점 아이콘 --> 	<!-- full = 채워진 별 / -->
							   					<i class="icon-star-full"></i> 				 	<!-- half = 반만 채워진 별 / -->
							   					<i class="icon-star-half"></i>						<!-- empty = 비워진 별 / -->
							   					(${replyGoodsCount } Rating)
				   							</span>
					   					</c:when>
					   					<c:when test="${rating >= 4 }">
					   						<span class="rate">
							   					<i class="icon-star-full"></i>
							   					<i class="icon-star-full"></i>
							   					<i class="icon-star-full"></i> <!-- 별점 아이콘 --> 	<!-- full = 채워진 별 / -->
							   					<i class="icon-star-full"></i> 				 	<!-- half = 반만 채워진 별 / -->
							   					<i class="icon-star-empty"></i>						<!-- empty = 비워진 별 / -->
							   					(${replyGoodsCount } Rating)
				   							</span>
					   					</c:when>
					   					<c:when test="${rating >= 3.5 }">
					   						<span class="rate">
							   					<i class="icon-star-full"></i>
							   					<i class="icon-star-full"></i>
							   					<i class="icon-star-full"></i> <!-- 별점 아이콘 --> 	<!-- full = 채워진 별 / -->
							   					<i class="icon-star-half"></i> 				 	<!-- half = 반만 채워진 별 / -->
							   					<i class="icon-star-empty"></i>						<!-- empty = 비워진 별 / -->
							   					(${replyGoodsCount } Rating)
				   							</span>
					   					</c:when>
				   						<c:when test="${rating >= 3 }">
					   						<span class="rate">
							   					<i class="icon-star-full"></i>
							   					<i class="icon-star-full"></i>
							   					<i class="icon-star-full"></i> <!-- 별점 아이콘 --> 	<!-- full = 채워진 별 / -->
							   					<i class="icon-star-empty"></i> 				 	<!-- half = 반만 채워진 별 / -->
							   					<i class="icon-star-empty"></i>						<!-- empty = 비워진 별 / -->
							   					(${replyGoodsCount } Rating)
				   							</span>
					   					</c:when>
					   					<c:when test="${rating >= 2.5}">
					   						<span class="rate">
							   					<i class="icon-star-full"></i>
							   					<i class="icon-star-full"></i>
							   					<i class="icon-star-half"></i> <!-- 별점 아이콘 --> 	<!-- full = 채워진 별 / -->
							   					<i class="icon-star-empty"></i> 				 	<!-- half = 반만 채워진 별 / -->
							   					<i class="icon-star-empty"></i>
							   					(${replyGoodsCount } Rating)					    <!-- empty = 비워진 별 / -->
				   							</span>
					   					</c:when>
					   					<c:when test="${rating >= 2 }">
					   						<span class="rate">
							   					<i class="icon-star-full"></i>
							   					<i class="icon-star-full"></i>
							   					<i class="icon-star-empty"></i> <!-- 별점 아이콘 --> 	<!-- full = 채워진 별 / -->
							   					<i class="icon-star-empty"></i> 				 	<!-- half = 반만 채워진 별 / -->
							   					<i class="icon-star-empty"></i>						<!-- empty = 비워진 별 / -->
							   					(${replyGoodsCount } Rating)
				   							</span>
					   					</c:when>
					   					<c:when test="${rating >= 1.5}">
					   						<span class="rate">
							   					<i class="icon-star-full"></i>
							   					<i class="icon-star-half"></i>
							   					<i class="icon-star-empty"></i> <!-- 별점 아이콘 --> 	<!-- full = 채워진 별 / -->
							   					<i class="icon-star-empty"></i> 				 	<!-- half = 반만 채워진 별 / -->
							   					<i class="icon-star-empty"></i>
							   					(${replyGoodsCount } Rating)					    <!-- empty = 비워진 별 / -->
				   							</span>
					   					</c:when>
					   					<c:when test="${rating >= 1}">
					   						<span class="rate">
							   					<i class="icon-star-full"></i>
							   					<i class="icon-star-empty"></i>
							   					<i class="icon-star-empty"></i> <!-- 별점 아이콘 --> 	<!-- full = 채워진 별 / -->
							   					<i class="icon-star-empty"></i> 				 	<!-- half = 반만 채워진 별 / -->
							   					<i class="icon-star-empty"></i>
							   					(${replyGoodsCount } Rating)					    <!-- empty = 비워진 별 / -->
				   							</span>
					   					</c:when>
					   				</c:choose>
							</p>
							<div class="size-wrap">
									<c:choose>
										<c:when test="${goodsColor.size() > 0}">
											<div class="block-26 mb-2">
												<h4>컬러</h4>
												<c:forEach items="${goodsColor }" var="goodsColor">
													<input type="button" style="display: inline; width: 30%;" class="btn btnColor" value="${goodsColor.gcolor }" onclick="getGoodsSize('${goodsView.gcode }', '${goodsColor.gcolor}', this)">
												</c:forEach>
							            	</div>
								            <div class="block-26 mb-4">
												<h4>사이즈</h4>
							               		<span id="sizeCountArea">
													컬러를 선택해주세요.
												</span>
								            </div>
										</c:when>
										<c:when test="${goodsColor.size() <= 0}">
											<h1>물품 등록중입니다.</h1>
										</c:when>
									</c:choose>
						</div>
                     <div class="input-group mb-4">
                     	<span class="input-group-btn">
                        	<button type="button" id="minusBtn" class="quantity-left-minus btn" style="padding: 15px;" data-type="minus" data-field="">
                           <i class="icon-minus2"></i>
                        	</button>
                    		</span>
                     	<input type="text" id="quantity" name="quantity" class="form-control input-number" value="1" min="1" max="100">
                     	<span class="input-group-btn ml-1">
                        	<button type="button" id="plusBtn" class="quantity-right-plus btn" style="padding: 15px;" data-type="plus" data-field="">
                             <i class="icon-plus2"></i>
                         </button>
                     	</span>
                  	</div>
                  	<div>
                  		<div class="total-wrap">
	                  		<div class="col-md-12">
									<input type="button" id="purchase" onclick="purchase('${sessionScope.loginId }', '${sessionScope.authority }', '${goodsView.gprice}')" class="btn btn-primary btn-addtocart" value="구매하기" style="display: inline;"> <!-- 수정 -->
									<input type="button" id="wish" onclick="wishListAdd('${sessionScope.loginId}', '${goodsView.gcode }', '${sessionScope.authority }')" class="btn btn-primary btn-addtocart" value="찜하기" style="display: inline;">  <!-- 수정 -->
									<input type="button" id="cart" onclick="cartListadd()" class="btn btn-primary btn-addtocart" value="장바구니에 담기" style="display: inline;">
								</div>
							</div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-md-12 pills">
								<div id="desreview" class="bd-example bd-example-tabs">
								  <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
								    <li class="nav-item">
								      <a class="nav-link active show" id="pills-description-tab" data-toggle="pill" href="#pills-description" role="tab" aria-controls="pills-description" aria-expanded="true">상품 설명</a>
								    </li>
								    <li class="nav-item">
								      <a class="nav-link" id="pills-review-tab" data-toggle="pill" href="#pills-review" role="tab" aria-controls="pills-review" aria-expanded="true">상품 리뷰</a>
								    </li>
								  </ul>

								  <div class="tab-content" id="pills-tabContent">
								    <div class="tab-pane border fade show active" id="pills-description" role="tabpanel" aria-labelledby="pills-description-tab" style="overflow: auto;">
								    	<c:if test="${goodsView.gcontent != null}">
											<textarea rows="10" cols="100" disabled="disabled" style="resize: none; background-color: white; border-color: white;">${goodsView.gcontent}</textarea>
								    	</c:if>
										<c:choose>
											<c:when test="${goodsView.gdtimg4 != null }">
												<p class="gdtimg"><img src="${pageContext.request.contextPath }/resources/images/goods/${goodsView.gdtimg1 }"></p>
												<p class="gdtimg"><img src="${pageContext.request.contextPath }/resources/images/goods/${goodsView.gdtimg2 }"></p>
												<p class="gdtimg"><img src="${pageContext.request.contextPath }/resources/images/goods/${goodsView.gdtimg3 }"></p>
												<p class="gdtimg"><img src="${pageContext.request.contextPath }/resources/images/goods/${goodsView.gdtimg4 }"></p>
											</c:when>
											<c:when test="${goodsView.gdtimg3 != null }">
												<p class="gdtimg"><img src="${pageContext.request.contextPath }/resources/images/goods/${goodsView.gdtimg1 }"></p>
												<p class="gdtimg"><img src="${pageContext.request.contextPath }/resources/images/goods/${goodsView.gdtimg2 }"></p>
												<p class="gdtimg"><img src="${pageContext.request.contextPath }/resources/images/goods/${goodsView.gdtimg3 }"></p>
											</c:when>
											<c:when test="${goodsView.gdtimg2 != null }">
												<p class="gdtimg"><img src="${pageContext.request.contextPath }/resources/images/goods/${goodsView.gdtimg1 }"></p>
												<p class="gdtimg"><img src="${pageContext.request.contextPath }/resources/images/goods/${goodsView.gdtimg2 }"></p>
											</c:when>
											<c:when test="${goodsView.gdtimg1 != null }">
												<p class="gdtimg"><img src="${pageContext.request.contextPath }/resources/images/goods/${goodsView.gdtimg1 }"></p>
											</c:when>
										</c:choose>
								    </div>

									<div class="tab-pane border fade" id="pills-review" role="tabpanel" aria-labelledby="pills-review-tab">
								      <div class="row">
								   		<div class="col-md-8">
								   			<div class="total-wrap">
								   				<div class="row">
								   		 			<div class="col-sm-8">
								   		 				<h3 class="head">댓글 (${replyGoodsCount })</h3> <!-- 총 댓글자 수 -->						   				
								   		 			</div>
								   		 		</div>
								   		  	</div>
								   		  	<c:if test="${replygoodsDTO == null}">
								   		  		<h4>등록된 리뷰가 없습니다.</h4>
								   		  	</c:if>
								   		  	<c:forEach items="${replygoodsDTO }" var="replygoodsDTO">
								   		  	<div class="review">
										   		<div class="desc">
										   			<h4>
										   				<span class="text-left">${replygoodsDTO.mid }</span> <!-- 구매인 아이디 -->
										   				<span class="text-right"></span> <!-- 댓글 작성일자 -->
										   			</h4>
										   			<p class="star">
										   				<c:choose>
										   					<c:when test="${replygoodsDTO.r_grade == 5 }">
										   						<span>
												   					<i class="icon-star-full"></i>
												   					<i class="icon-star-full"></i>
												   					<i class="icon-star-full"></i> <!-- 별점 아이콘 --> 	<!-- full = 채워진 별 / -->
												   					<i class="icon-star-full"></i> 				 	<!-- half = 반만 채워진 별 / -->
												   					<i class="icon-star-full"></i>						<!-- empty = 비워진 별 / -->
									   							</span>
										   					</c:when>
										   					<c:when test="${replygoodsDTO.r_grade == 4 }">
										   						<span>
												   					<i class="icon-star-full"></i>
												   					<i class="icon-star-full"></i>
												   					<i class="icon-star-full"></i> <!-- 별점 아이콘 --> 	<!-- full = 채워진 별 / -->
												   					<i class="icon-star-full"></i> 				 	<!-- half = 반만 채워진 별 / -->
												   					<i class="icon-star-empty"></i>						<!-- empty = 비워진 별 / -->
									   							</span>
										   					</c:when>
									   						<c:when test="${replygoodsDTO.r_grade == 3 }">
										   						<span>
												   					<i class="icon-star-full"></i>
												   					<i class="icon-star-full"></i>
												   					<i class="icon-star-full"></i> <!-- 별점 아이콘 --> 	<!-- full = 채워진 별 / -->
												   					<i class="icon-star-empty"></i> 				 	<!-- half = 반만 채워진 별 / -->
												   					<i class="icon-star-empty"></i>						<!-- empty = 비워진 별 / -->
									   							</span>
										   					</c:when>
										   					<c:when test="${replygoodsDTO.r_grade == 2 }">
										   						<span>
												   					<i class="icon-star-full"></i>
												   					<i class="icon-star-full"></i>
												   					<i class="icon-star-empty"></i> <!-- 별점 아이콘 --> 	<!-- full = 채워진 별 / -->
												   					<i class="icon-star-empty"></i> 				 	<!-- half = 반만 채워진 별 / -->
												   					<i class="icon-star-empty"></i>						<!-- empty = 비워진 별 / -->
									   							</span>
										   					</c:when>
										   					<c:when test="${replygoodsDTO.r_grade == 1}">
										   						<span>
												   					<i class="icon-star-full"></i>
												   					<i class="icon-star-empty"></i>
												   					<i class="icon-star-empty"></i> <!-- 별점 아이콘 --> 	<!-- full = 채워진 별 / -->
												   					<i class="icon-star-empty"></i> 				 	<!-- half = 반만 채워진 별 / -->
												   					<i class="icon-star-empty"></i>						<!-- empty = 비워진 별 / -->
									   							</span>
										   					</c:when>
										   				</c:choose>
										   				<!-- 신고 -->
									   					<span class="text-right"><a href="#" onclick="replyDeclaration('${replygoodsDTO.r_num}', '${sessionScope.loginId }', '${sessionScope.authority }')" class="reply"><i class="icon-reply"></i></a></span>
										   			</p>
										   			<c:choose>
										   				<c:when test="${replygoodsDTO.r_img3 != null}">
											   				<p>
													   			<a href="${pageContext.request.contextPath }/resources/images/review/${replygoodsDTO.r_img1}" class="image-popup-no-margins"><img style="width: 40px" src="${pageContext.request.contextPath }/resources/images/review/${replygoodsDTO.r_img1}"></a>
													   			<a href="${pageContext.request.contextPath }/resources/images/review/${replygoodsDTO.r_img2}" class="image-popup-no-margins"><img style="width: 40px" src="${pageContext.request.contextPath }/resources/images/review/${replygoodsDTO.r_img2}"></a>
													   			<a href="${pageContext.request.contextPath }/resources/images/review/${replygoodsDTO.r_img3}" class="image-popup-no-margins"><img style="width: 40px" src="${pageContext.request.contextPath }/resources/images/review/${replygoodsDTO.r_img3}"></a>
												   			</p>
										   				</c:when>
										   				<c:when test="${replygoodsDTO.r_img2 != null}">
											   				<p>
													   			<a href="${pageContext.request.contextPath }/resources/images/review/${replygoodsDTO.r_img1}" class="image-popup-no-margins"><img style="width: 40px" src="${pageContext.request.contextPath }/resources/images/review/${replygoodsDTO.r_img1}"></a>
													   			<a href="${pageContext.request.contextPath }/resources/images/review/${replygoodsDTO.r_img2}" class="image-popup-no-margins"><img style="width: 40px" src="${pageContext.request.contextPath }/resources/images/review/${replygoodsDTO.r_img2}"></a>
												   			</p>
										   				</c:when>
										   				<c:when test="${replygoodsDTO.r_img1 != null}">
											   				<p>
													   			<a href="${pageContext.request.contextPath }/resources/images/review/${replygoodsDTO.r_img1}" class="image-popup-no-margins"><img style="width: 40px" src="${pageContext.request.contextPath }/resources/images/review/${replygoodsDTO.r_img1}"></a>
												   			</p>
										   				</c:when>
										   			</c:choose>
										   			<p>${replygoodsDTO.r_reply }</p> <!-- 구매자 댓글내용 -->
										   		</div>
										   	</div>
								   		  	</c:forEach>
								   		  	
								   		  	<div style="text-align: center;">
								   		  	
											<!-- 페이징 처리 -->
											<c:choose>
												<c:when test="${page.page <= 1 }">
													<button class="pagebtnX btn-primary" style="background-color: #616161; border-color: #616161;" disabled="disabled">이전</button>
												</c:when>
												<c:otherwise>
													<button class="pagebtn btn-primary" onclick="location.href='goodsView?gcode=${goodsView.gcode }&page=${page.page - 1}'">이전</button>
												</c:otherwise>
											</c:choose>
											
											<c:forEach begin="${page.startpage}" end="${page.endpage}" var="pageNum" step="1">
												<c:choose>
													<c:when test="${page.page == pageNum}">
														<button class="pagebtnX btn-primary" disabled="disabled">${pageNum}</button>
													</c:when>
													<c:otherwise>
														<button class="pagebtn btn-primary" onclick="location.href='goodsView?gcode=${goodsView.gcode }&page=${pageNum}'">${pageNum}</button>
													</c:otherwise>
												</c:choose>
											</c:forEach>
											
											<c:choose>
												<c:when test="${page.page >= page.maxpage }">
													<button class="pagebtnX btn-primary" style="background-color: #616161; border-color: #616161;" disabled="disabled">다음</button>
												</c:when>
												<c:otherwise>
													<button class="pagebtn btn-primary" onclick="location.href='goodsView?gcode=${goodsView.gcode }&page=${page.page + 1}'">다음</button>
												</c:otherwise>
											</c:choose>
											<!-- 페이징 -->
											</div>
								   		</div>
								   		<div class="col-md-4">
								   			<div class="rating-wrap">
									   			<h3 class="head">평점</h3>
									   			<div class="wrap">
										   			<p class="star">
										   				<a style="cursor: pointer;">
											   				<span>
											   					<i class="icon-star-full"></i>
											   					<i class="icon-star-full"></i>
											   					<i class="icon-star-full"></i> <!-- 별점 아이콘 --> <!-- 별점 아이콘 5점대 순위 -->
											   					<i class="icon-star-full"></i>
											   					<i class="icon-star-full"></i>
											   					<!-- 별점 아이콘 5점대 비율 -->
										   					</span>
										   					<span>${ratingList.get(0) } Reviews</span> <!-- 별점 아이콘 5점대 댓글자 수 -->
										   				</a>
										   			</p>
										   			<p class="star">
										   				<a style="cursor: pointer;">
											   				<span>
											   					<i class="icon-star-full"></i>
											   					<i class="icon-star-full"></i>
											   					<i class="icon-star-full"></i> <!-- 별점 아이콘 --> <!-- 별점 아이콘 4점대 순위 -->
											   					<i class="icon-star-full"></i>
											   					<i class="icon-star-empty"></i>
											   					<!-- 별점 아이콘 4점대 비율 -->
										   					</span>
										   					<span>${ratingList.get(1) } Reviews</span> <!-- 별점 아이콘 4점대 댓글자 수 -->
									   					</a>
										   			</p>
										   			<p class="star">
										   				<a style="cursor: pointer;">
											   				<span>
											   					<i class="icon-star-full"></i>
											   					<i class="icon-star-full"></i>
											   					<i class="icon-star-full"></i> <!-- 별점 아이콘 --> <!-- 별점 아이콘 3점대 순위 -->
											   					<i class="icon-star-empty"></i>
											   					<i class="icon-star-empty"></i>
											   					<!-- 별점 아이콘 3점대 비율 -->
										   					</span>
										   					<span>${ratingList.get(2) } Reviews</span> <!-- 별점 아이콘 3점대 댓글자 수 -->
										   				</a>
										   			</p>
										   			<p class="star">
										   				<a style="cursor: pointer;">
											   				<span>
											   					<i class="icon-star-full"></i>
											   					<i class="icon-star-full"></i>
											   					<i class="icon-star-empty"></i> <!-- 별점 아이콘 -->  <!-- 별점 아이콘 2점대 순위 -->
											   					<i class="icon-star-empty"></i>
											   					<i class="icon-star-empty"></i>
											   					<!-- 별점 아이콘 2점대 비율 -->
										   					</span>
										   					<span>${ratingList.get(3) } Reviews</span> <!-- 별점 아이콘 2점대 댓글자 수 -->
										   				</a>
										   			</p>
										   			<p class="star">
										   				<a style="cursor: pointer;">
											   				<span>
											   					<i class="icon-star-full"></i>
											   					<i class="icon-star-empty"></i>
											   					<i class="icon-star-empty"></i> <!-- 별점 아이콘 -->  <!-- 별점 아이콘 1점대 순위 -->
											   					<i class="icon-star-empty"></i>
											   					<i class="icon-star-empty"></i>
											   					<!-- 별점 아이콘 1점대 비율 -->
										   					</span>
										   					<span>${ratingList.get(4) } Reviews</span> <!-- 별점 아이콘 1점대 댓글자 수 -->
										   				</a>
										   			</p>
										   		</div>
									   		</div>
								   		</div>
								   	</div>
								    </div>
								  </div>
								</div>
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
	
<script type="text/javascript">

$(document).ready(function(){
	var quantitiy=0;
	$('.quantity-right-plus').click(function(e){
		e.preventDefault();
		var quantity = parseInt($('#quantity').val());
		$('#quantity').val(quantity + 1);
		});
	$('.quantity-left-minus').click(function(e){
		e.preventDefault();
		var quantity = parseInt($('#quantity').val());
		if(quantity>0){
			$('#quantity').val(quantity - 1);
			}
		});
	var pageReview = ${pageReview}
		if(pageReview == "1") {
			$("#pills-review-tab").click();
			$("#pills-review-tab").focus();
		}
		
		// 이미지 확대
		$('.image-popup-no-margins').magnificPopup({
		      type: 'image',
		      closeOnContentClick: true,
		      closeBtnInside: false,
		      fixedContentPos: true,
		      mainClass: 'mfp-no-margins mfp-with-zoom', // class to remove default margin from left and right side
		      image: {
		          verticalFit: true
		      },
		      zoom: {
		          enabled: true,
		          duration: 300 // don't foget to change the duration also in CSS
		      }
		  });
		
		if(${goodsColor.size() <= 0}) {
			$("#purchase").css("display", "none");
			$("#cart").css("display", "none");
			$("#wish").css("display", "none");
			$("#minusBtn").css("display", "none");
			$("#plusBtn").css("display", "none");
			$("#quantity").css("display", "none");
		}
		
	});

var color = "";
var size = "";

// 컬러 선택 (사이즈불러오기, 컬러변수에 값넣기)
	function getGoodsSize(gcode, gcolor, obj) {
		var output = "";
		color = $(obj).val();
		
		// 원상복구
		$(".btnColor").removeClass("btn-primary");
		$(".btnColor").css("color", "black");
		$(".btnsize").removeClass("btn-primary");
		$(".btnSize").css("color", "black");
		// 클릭시
		$(obj).css("color", "white");
		$(obj).addClass("btn-primary");
		$("#purchase").val("구매하기");
		size = "";
		
		$.ajax({
			type : "post",
			url : "getGoodsSize",
			data : { "gcode" : gcode,
					 "gcolor" : gcolor  },
			dataType : "json",
			success : function(result) {
				for(var i in result) {
					output += "<input type='button' style='display: inline;' class='btn btnSize' onclick='sizeSelect(this)' value='"+result[i].gsize+"'> " 			
				}
				$("#sizeCountArea").html(output);
			}
		});
	}
	
	// 사이즈 선택(재고확인하기, 사이즈 변수에 값넣기)
	function sizeSelect(obj) {
		var gcode = '${goodsView.gcode}';
		
		size = $(obj).val();
		
		// 원상복구
		$(".btnSize").removeClass("btn-primary");
		$(".btnSize").css("color", "black");
		// 클릭시
		$(obj).addClass("btn-primary");
		$(obj).css("color", "white");
		
		$.ajax({
			type : "post",
			url : "goodsCount",
			data : { "gcode" : gcode,
					 "color" : color,
					 "size" : size },
			success : function(result) {
				// 재고가 한개이상 있는지
				if(result < 1) {
					$("#purchase").val("품절");
				} else {
					$("#purchase").val("구매하기");
				}
			}
		});
	}
	
	// 바로구매
	function purchase(id, authority, gprice) {
		var gname = '${goodsView.gname}';
		var purchaseBtn = $("#purchase").val();
		var gstock = $("#quantity").val();
		var gcode = '${goodsView.gcode}';
		
		// 버튼이 품절이면 상품 구매불가
		if(purchaseBtn == "품절") {
			alert("품절상품입니다.");
			return false;
		}
		
		if(id == "") {
			alert("로그인 후 이용가능합니다.");
		} else if(authority != "3") {
			alert("일반회원만 구매 가능합니다.")
		} else if(color == "" || size == ""){
			alert("색상/사이즈를 선택해주세요.");
		} else if(id != "" && authority == "3" && color != "" && size != "") {
			// 재고 수량
			$.ajax({
				type : "post",
				url : "goodsCount",
				data : { "gcode" : gcode,
						 "color" : color,
						 "size" : size },
				success : function(result) {
					// 재고가 한개이상 있는지
					if(result < gstock) {
						alert("구매수량이 남은수량보다 많습니다.\n남은수량은 "+result+"개 입니다.");
						return false;
					} else {
						// 구매 이동
						location.href="goodsDirectPurchase?gcode=${goodsView.gcode}&mid="+id+"&color="+color+"&size="+size+"&gstock="+gstock;
					}
				}
			});
		}
		
	}

	// 찜목록 추가
	function wishListAdd(loginId, gcode, authority) {

		if(loginId == "") {
			alert("로그인 후 이용가능합니다.");
		} else if(authority != "3") {
			alert("일반회원만 가능합니다.");
		} else {
			$.ajax({
				type : "post",
				url : "wishListAdd",
				data : { "mid" : loginId,
						 "gcode" : gcode },
				success : function(result) {
					if(result == "0") {
						alert("이미 등록되어 있습니다.");
					} else {
						alert("찜목록에 추가되었습니다.");
					}
				} 
			});		
		}
		
	}
	
	// 장바구니 추가
	function cartListadd() {
		var mid = '${sessionScope.loginId}';
		var authority = '${sessionScope.authority}';
		var gcode = '${goodsView.gcode}';
		var gstock = $("#quantity").val();
		var gprice = '${goodsView.gprice}';
		var purchaseBtn = $("#purchase").val();
		
		// 버튼이 품절이면 장바구니 불가
		if(purchaseBtn == "품절") {
			alert("품절상품은 담을수 없습니다.");
			return false;
		}
		
		// 장바구니 추가 전 체크
		if(mid == "") {
			alert("로그인 후 이용가능합니다.");
		} else if(authority != "3") {
			alert("일반회원만 이용가능합니다.")
		} else if(color == "" || size == ""){
			alert("색상/사이즈를 선택해주세요.");
		} else {
			// 재고 수량
			$.ajax({
				type : "post",
				url : "goodsCount",
				data : { "gcode" : gcode,
						 "color" : color,
						 "size" : size },
				success : function(result) {
					// 재고가 한개이상 있는지
					if(result < gstock) {
						alert("구매수량이 남은수량보다 많습니다.\n남은수량은 "+result+"개 입니다.");
						return false;
					} else {
						// 장바구니 추가
						$.ajax({
							type : "post",
							url : "cartListAdd",
							data : { "mid" : mid,
									 "gcode" : gcode,
									 "gstock" : gstock,
									 "color" : color,
								     "size" : size,
									 "gprice" : gprice },
							success : function(result) {
								if(result == "0") {
									alert("이미 장바구니에 등록되어있습니다.");
								} else if(result == "1"){
									alert("장바구니에 등록되었습니다.");
								} else {
									alert("장바구니 추가에 실패하였습니다.\n관리자에게 문의바랍니다.");
								}
							} 
						});
					}
				}
			});
		}	
	}
	// 신고하기
	function replyDeclaration(r_num, mid, authority ) {
		var rr_content = prompt('신고사유를 입력하세요');
		if(mid == "") {
			alert("로그인 후 이용가능합니다");
		} else if (authority != "3") {
			alert("일반회원만 이용가능합니다.");
		} else if(rr_content == null){
			alert("신고사유를 입력해주세요.");
		} else {
			$.ajax({
				type : "post",
				url : "declaration",
				data : { "r_num" : r_num,
						 "mid" : mid,
						 "rr_content" : rr_content },
				success : function(result) {
					if(result == "0") {
						alert("이미 신고하셨습니다.");
					} else {
						alert("신고 접수되었습니다.");
					}
				}
			});
		}
	}
</script>
</html>