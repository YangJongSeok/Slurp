<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Slurp</title>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- header -->
	<%@include file="../../header.jsp" %>
	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<style type="text/css">
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
		<%@include file="../../topbar.jsp" %>
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
					<h3>상품 이미지</h3>
						<div class="product-entry">
						
							<!-- 800x600 사진 -->
							<c:if test="${goodsDTO.gimg != null }">
								<img src="${pageContext.request.contextPath }/resources/images/goods/${goodsDTO.gimg}" class="img-fluid cover">
							</c:if>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="product-desc">
							<h2>상품명</h2> <!-- 상품 이름 -->
								<span style="font-size:20px;">${goodsDTO.gname}</span>
								<!-- 테이블 입력을 위한 데이터 hidden 처리 -->
								<input type="hidden" id="ccode" value="${goodsDTO.ccode }">
								<input type="hidden" id="gcode" value="${goodsDTO.gcode }">
							<div class="size-wrap">
								<div class="block-26 mb-2" >
									<h4>컬러</h4> 														<!-- 컬러 -->
										<span id="colorCountArea" id="the_color">
											<input type="radio" style="display: inline; width: 15%;" class="btn btn-primary btn-addtocart" id="gcolor" name="gcolor" value="red" > <span style="width: 10px;">red　</span>
											<input type="radio" style="display: inline; width: 15%;" class="btn btn-primary btn-addtocart" id="gcolor" name="gcolor" value="orange"><span style="width: 10px;">orange</span>
											<input type="radio" style="display: inline; width: 15%;" class="btn btn-primary btn-addtocart" id="gcolor" name="gcolor" value="pink"><span style="width: 10px;">pink</span>
											<br>
											<input type="radio" style="display: inline; width: 15%;" class="btn btn-primary btn-addtocart" id="gcolor" name="gcolor" value="ivory" ><span style="width: 10px;">ivory</span>
											<input type="radio" style="display: inline; width: 15%;" class="btn btn-primary btn-addtocart" id="gcolor" name="gcolor" value="brown" ><span style="width: 10px;">brown</span>
											<input type="radio" style="display: inline; width: 15%;" class="btn btn-primary btn-addtocart" id="gcolor" name="gcolor" value="yellow" ><span style="width: 10px;">yellow</span>
											<br>
											<input type="radio" style="display: inline; width: 15%;" class="btn btn-primary btn-addtocart" id="gcolor" name="gcolor" value="green" ><span style="width: 10px;">green</span>
											<input type="radio" style="display: inline; width: 15%;" class="btn btn-primary btn-addtocart" id="gcolor" name="gcolor" value="sky_blue" ><span style="width: 10px;">sky_blue</span>
											<input type="radio" style="display: inline; width: 15%;" class="btn btn-primary btn-addtocart" id="gcolor" name="gcolor" value="blue" ><span style="width: 10px;">blue</span>
											<br>
											<input type="radio" style="display: inline; width: 15%;" class="btn btn-primary btn-addtocart" id="gcolor" name="gcolor" value="navy" ><span style="width: 10px;">navy</span>
											<input type="radio" style="display: inline; width: 15%;" class="btn btn-primary btn-addtocart" id="gcolor" name="gcolor" value="purple" ><span style="width: 10px;">purple</span>
											<input type="radio" style="display: inline; width: 15%;" class="btn btn-primary btn-addtocart" id="gcolor" name="gcolor" value="gray" ><span style="width: 10px;">gray</span>
											<br>
											<input type="radio" style="display: inline; width: 15%;" class="btn btn-primary btn-addtocart" id="gcolor" name="gcolor" value="white" ><span style="width: 10px;">white</span>
											<input type="radio" style="display: inline; width: 15%;" class="btn btn-primary btn-addtocart" id="gcolor" name="gcolor" value="black" ><span style="width: 10px;">black</span>
										</span>
				            	</div>
					            <div class="block-26 mb-4" id="the_size">
									<h4>사이즈</h4>															<!-- 사이즈 -->
					               		<span id="sizeCountArea" id="the_size">
					               		<c:if test="${goodsDTO.gcate == 1 && goodsDTO.ggender == '남' || goodsDTO.gcate == 3 && goodsDTO.ggender == '남' }">
					               			<span>상의, 아우터</span><br>				
											<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="90">S 90 
											<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="95">M 95
											<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="100">L 100
											<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="105">XL 105
											<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="110">2XL 110
					               		</c:if>
										<c:if test="${goodsDTO.gcate == 1 && goodsDTO.ggender == '여' || goodsDTO.gcate == 3 && goodsDTO.ggender == '여' }">
											<span>상의, 아우터</span><br>				
											<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="85">S 85 
											<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="90">M 90
											<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="95">L 95
											<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="100">XL 100
											<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="105">2XL 105
										</c:if>
										<c:if test="${goodsDTO.gcate == 2 && goodsDTO.ggender == '남'}">
											<span>하의</span><br>				
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="28">S 28
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="30">M 30
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="32">L 32
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="34">XL 34
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="36">2XL 36
											</c:if>
										<c:if test="${goodsDTO.gcate == 2 && goodsDTO.ggender == '여'}">
											<span>하의</span><br>				
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="24">S 24
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="26">M 26
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="28">L 28
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="30">XL 30
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="32">2XL 32
										</c:if>
										<c:if test="${goodsDTO.gcate == 4 }">
											<span>신발</span><br>
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="230">230
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="235">235
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="240">240
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="245">245
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="250">250
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="255">255
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="260">260
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="265">265
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="270">270
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="275">275
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="280">280
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="285">285
												<input type="radio" style="display: inline; width: 20%;" class="btn btn-primary btn-addtocart" id="gsize" name="gsize" value="290">290
										</c:if>
									</span>
					            </div>
							</div>
		                     <div class="input-group mb-4"> <!-- 수량 + -->
		                     	<span class="input-group-btn">													 <!-- 수량 -->
		                        	<button type="button" class="quantity-left-minus btn" style="padding: 15px;" data-type="minus" data-field="">
		                           <i class="icon-minus2"></i>
		                          
		                        	</button>
		                        	
		                    		</span>
		                     	<input type="text" id="gstock" name="gstock" class="form-control input-number" value="0" min="0" max="100">
		                     	<span class="input-group-btn ml-1">
		                        	<button type="button" class="quantity-right-plus btn" style="padding: 15px;" data-type="plus" data-field="">
		                             <i class="icon-plus2"></i>
		                             
		                         </button>
		                         
		                         
		                     	</span>
		                  	</div>
		                  	<br>
		                  	<div>
		                  		<div class="total-wrap">
			                  		<div class="col-md-12">
											<!-- 재고 등록 버튼 ajax -->
											<button type="button" onclick="registGoodsCount()" class="btn btn-primary btn-addtocart" style="display: inline;">재고수량 추가 [+] / 감소 [-]</button>
											<p>재고수량 감소시 -를 입력하세요.</p>
									</div>
								</div>
							</div>
						
						
							<br> <br>
							
							<div class="total-wrap">
			                  		<div class="col-md-12">
											<!-- 재고 등록 버튼 ajax -->
											<button type="button" onclick="deleteGoodsCount()" class="btn btn-primary btn-addtocart" style="display: inline;">상품 삭제</button>
									</div>
								</div>
						 </div>
					</div>
				</div>


				<!-- 추가된 재고수량 목록표시 -->

				<div class="row">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-md-12 pills">
								<div class="bd-example bd-example-tabs">
								  <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">

								    <li class="nav-item">
								      <button type="button" class="btn btn-primary btn-addtocart" onclick="goodsCountState()" style="float:right;" >현재 재고 확인</button>
								    </li>
								   

								  </ul>

								  <div class="tab-content" id="pills-tabContent">
								    <div class="tab-pane border fade show active" id="pills-description" role="tabpanel" aria-labelledby="pills-description-tab" style="overflow: auto;">
											<table>
													<tbody id="stateArea" style="float:left;">
													
													</tbody>
											</table>
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
		<%@include file="../../footer.jsp" %>
		<!-- 하단메뉴 끝 -->
	</body>
	
<script>
		$(document).ready(function(){

			 /******************** 수량 +++ ***********************/
		var quantitiy=0;
		   $('.quantity-right-plus').click(function(e){
		        
		        e.preventDefault();
		        var quantity = parseInt($('#gstock').val());
		            $('#gstock').val(quantity + 1);
		    });

		     $('.quantity-left-minus').click(function(e){
		        e.preventDefault();
		        var quantity = parseInt($('#gstock').val());
		      
		            $('#gstock').val(quantity - 1);
		    });
		});
		
			/***************** 재고수량 추가 *******************/
			function registGoodsCount(){
				var ccode = $("#ccode").val();
				var gcode = $("#gcode").val(); 
				var gstock = $("#gstock").val(); 
				var gsize = $('input[name="gsize"]:checked').val();
				var gcolor = $('input[name="gcolor"]:checked').val();
				console.log("업체코드 : " + ccode + " 상품코드 : " + gcode + " 수량 : " + gstock + " 사이즈 : " + gsize + " 색상 : " + gcolor)
				if(gsize != undefined && gcolor != undefined) {
					$.ajax({
							type : "get",
							url : "registGoodsCount",
							data : {"ccode" : ccode, 
									"gcode" : gcode,
									"gstock" : gstock,
									"gsize" : gsize,
									"gcolor" : gcolor},
							success : function(result){
								console.log(result);
								if(result == 1){
									location.reload();
								} else{
									alert("재고 등록에 실패하였습니다!");
								}
							}
					});
				} else {
					alert("색상 / 사이즈를 선택해주세요");
				}
			};
			
			/***************** 재고수량 감소 *******************/
			function registGoodsCount2(){
				
				var ccode = $("#ccode").val();
				var gcode = $("#gcode").val(); 
				var gstock = $("#gstock1").val(); 
				var gsize = $('input[name="gsize"]:checked').val();
				var gcolor = $('input[name="gcolor"]:checked').val();
				console.log("업체코드 : " + ccode + " 상품코드 : " + gcode + " 수량 : " + gstock + " 사이즈 : " + gsize + " 색상 : " + gcolor)
				if(gsize != undefined && gcolor != undefined) {
					$.ajax({
							type : "get",
							url : "registGoodsCount",
							data : {"ccode" : ccode, 
									"gcode" : gcode,
									"gstock" : gstock,
									"gsize" : gsize,
									"gcolor" : gcolor},
							success : function(result){
								console.log(result);
								if(result == 1){
									location.reload();
								} else{
									alert("재고 등록에 실패하였습니다!");
								}
							}
					});
				} else {
					alert("색상 / 사이즈를 선택해주세요");
				}
			};
		
		
		function goodsCountState(){
			var gcode = $("#gcode").val(); 
			$.ajax({
					type : "get",
					url : "goodsCountState",
					data : {"gcode" : gcode},
					success : function(result){
						var output = "";
						console.log(result);
						if(result != null){
							for(var i in result){
								output += "<tr>";
								output += "<td style='width=30%;'>" + result[i].gcolor +"</td>";
								output += "<td style='width=40%;'> [ "+ result[i].gsize +" ] </td>";
								output += "<td>" + result[i].gstock + " 개</td>";
								output += "</tr>";
							}
							$("#stateArea").html(output);
						}
					}
			});
		};
		
		function deleteGoodsCount(){
			var gcode = $("#gcode").val();
			console.log("삭제요청");
			location.href="deleteGoodsCount?gcode="+gcode;
		}
	</script>
</html>