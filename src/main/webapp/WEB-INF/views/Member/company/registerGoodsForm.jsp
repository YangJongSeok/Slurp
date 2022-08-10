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
				<form action="goodsRegist" class="colorlib-form" style="margin: auto;" method="post" enctype="multipart/form-data" onsubmit="return goodsCheck()"> <!-- form 시작 -->
				<input type="hidden" id="ccode" name="ccode" value="${sessionScope.cCode }">
				<div class="row row-pb-lg">
					<div class="col-md-1"></div>
					<div class="col-md-5">
							<div>
								<div>
									<h6>* 등록할 상품 명</h6> <!-- 상품명 -->
										<input type="text" placeholder="등록할 상품 명 입력" size="60" id="gname" name="gname" maxlength="100" >
										<br>
										<br>
									<h6>* 상품 가격</h6>	<!-- 상품 가격  -->
										<input type="text" size="60" id="gprice" name="gprice"  placeholder="단위 : 원(숫자만 입력 가능)" pattern="[0-9]+">
										<br>
										<br>
									<h6>상품 설명(최대 2000자)</h6> <!-- 상품 설명  -->
										<textarea rows="7" cols="60" wrap="hard" name="gcontent" maxlength="2000" style="width: 415.5px; height: 350px; resize: none"></textarea>
										<br>
										<br>	
								</div>
							</div>
					</div> 
					<div class="col-md-1"></div>
					<div class="col-md-5">
							<div>
								<div>	
									<h6>* 성별</h6> <!-- 성별 --> <h6 style="float: right;">* : 필수입력사항</h6>
										남 <input type="radio" name="ggender" value="남">　　
										여 <input type="radio" name="ggender" value="여">
										<br>
										<hr>
									<h6>* 카테고리</h6> <!-- 카테고리 -->
										상의 <input type="radio" name="gcate" value="1">　
										하의 <input type="radio" name="gcate" value="2">　
										아우터 <input type="radio" name="gcate" value="3">　
										신발 <input type="radio" name="gcate" value="4">
										<br>
										<hr>
									<h6>* 상품 메인 이미지</h6>
										<input type="file" name="gfile" id="gfile" > 
									<br>
									<br>
									<hr>
									<h6>상품 설명 이미지</h6>
										* <input type="file" name="gfile1" id="gfile1"> 
										<br>
										<input type="file" name="gfile2" > 
										<br>
										<input type="file" name="gfile3" > 
										<br>
										<input type="file" name="gfile4" >
									<br>
									<br>
									<br>
								</div>
							</div>
					</div> 
					<div class="col-md-12" style="text-align: center;">
						<input type="submit" value="상품 등록" class="btn btn-primary"> 
					</div>
				</div>
				</form>	<!-- form 끝 -->
			</div>
		</div>
		</div>
		
		<!-- 하단메뉴 시작 -->
		<%@include file="../../footer.jsp" %>
		<!-- 하단메뉴 끝 -->
	</body>
<script type="text/javascript">
function goodsCheck() {
	var gender = $('input[name="ggender"]:checked').val();
	var cate = $('input[name="gcate"]:checked').val();
	var price = $("#gprice").val();
	var gfile = $("#gfile").val();
	var gfile1 = $("#gfile1").val();
	var gname = $("#gname").val();
	
	if(gname == "") {
		alert("상품 이름을 입력하세요.");
		return false;
	} else if(price == "") {
		alert("상품 가격을 입력하세요.");
		return false;
	} else if(gender == undefined) {
		alert("성별을 선택해주세요.");
		return false;
	} else if(cate == undefined) {
		alert("카테고리를 선택해주세요.");
		return false;
	} else if(gfile =="" ) {
		alert("메인 이미지를 첨부해주세요.");
		return false;
	} else if(gfile1 == "") {
		alert("상품설명이미지를 첨부해주세요.");
		return false;
	}
	
	
}
</script>
</html>