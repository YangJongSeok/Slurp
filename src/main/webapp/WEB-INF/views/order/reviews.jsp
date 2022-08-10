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
	<link href="${pageContext.request.contextPath}/resources/css/StarsStyle.css" rel="stylesheet" type="text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
				<div class="row">
					<div class="col-lg-12">
						<form action="reviewUpLoad" method="post" class="colorlib-form" style="margin: auto;" enctype="multipart/form-data" onsubmit="return formCheck();"> <!-- form 시작 -->
							<input type="hidden" value="${goodsDTO.gcode }" name="gcode">
							<input type="hidden" value="${sessionScope.loginId }" name="mid">
							<input type="hidden" value="${o_code }" name="o_code">
							<input type="text" value="${url }" name="url">
							<h4>상품 리뷰</h4>
							<h6>구매하신 상품의 만족도를 마음껏 표현해주세요!</h6> <!-- 첫문단 -->
							<hr>
							<div class="total-wrap">
								<div class="row">
									<div class="col-sm-3" style="text-align: center;">
										<a href="#" class="prod-img">
											<img src="${pageContext.request.contextPath }/resources/images/goods/${goodsDTO.gimg }" class="img-fluid" alt=""> <!-- 구매된 상품의 이미지 -->
										</a>
									</div>
								
									<div class="col-sm-9">
										<h2>${goodsDTO.gname }</h2> <!-- 구매된 상품 명 -->
										<h6>${companyName }</h6> <!-- 구매된 상품 부가설명 -->
										<br>
										<h5>해당 상품의 별점을 남겨주세요!</h5>
										<div class="star-rating" style="margin: 35px">
											<input type="radio"  id="5-stars" name="r_grade" value="5" v-model="ratings" />
											<label for="5-stars" class="star pr-4" style="font-size: 50px">★</label>
											<input type="radio"  id="4-stars" name="r_grade" value="4" v-model="ratings" />
											<label for="4-stars" class="star" style="font-size: 50px">★</label>
											<input type="radio"  id="3-stars" name="r_grade" value="3" v-model="ratings" /> <!-- 별점 -->
											<label for="3-stars" class="star" style="font-size: 50px">★</label>
											<input type="radio"  id="2-stars" name="r_grade" value="2" v-model="ratings" />
											<label for="2-stars" class="star" style="font-size: 50px">★</label>
											<input type="radio"  id="1-stars" name="r_grade" value="1" v-model="ratings" />
											<label for="1-stars" class="star" style="font-size: 50px">★</label>
										</div>
										
									</div>
								</div>
							</div>
							<hr>
							<div class="total-wrap">
								<div class="row">
									<div class="col-sm-3">
										<h5>후기 작성(500자 이내)</h5>
									</div>
									<div class="col-sm-9">
										<textarea rows="11" maxlength="500" style="width: 55%; margin-top: 0px; margin-bottom: 0px; height: 160px; resize: none;" id="r_reply" name="r_reply"></textarea> <!-- 후기작성 -->
									</div>
								</div>
							</div>
							<hr>
							<div class="total-wrap">
								<div class="row">
									<div class="col-sm-3">
										<h5>사진 첨부</h5>
									</div>
									<div class="col-sm-9">
										<p>3개까지 추가가능합니다.</p>
										<input multiple="multiple" type="file" id="file" name="file" accept="image/*" maxlength="2"><br>
									</div>
								</div>
							</div>
							<br>
							<div style="text-align: center;">
								<input type="submit" value="작성 완료" class="btn btn-primary"> <!-- submit -->
							</div>
						</form> <!-- form 끝 -->
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
// 업로드 갯수 제한
$(document).ready(function() {
	
	$('#file').change(function(){
		var files = $('input[name="file"]')[0].files;
		for(var i= 0; i<files.length; i++){
			if(files.length > 3) {
				alert("파일첨부는 3개까지 가능합니다.\n다시 선택해주세요.");
				$('input[name="file"]').val("");
			}
		}
	});
});

// 서브밋 체크
function formCheck() {
	var rating = $('input[name="r_grade"]:checked').val();
	var reply = $("#r_reply").val();
	
	if(rating === undefined) {
		alert("별점을 선택해주세요");
		return false;
	}
	if(reply == "") {
		alert("후기를 작성해주세요");
		return false;
	}
}

function star() {
	var rating = $('input[name="r_grade"]:checked').val();
	var reply = $("#r_reply").val();
	console.log(reply)
	if(rating === undefined) {
		alert("별점을 선택해주세요");
		return false;
	}
	if(reply == "") {
		alert("후기를 작성해주세요");
		return false;
	}
}
</script>
</html>