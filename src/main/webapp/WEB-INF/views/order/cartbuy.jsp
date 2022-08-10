<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Slurp</title>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- header -->
	<%@include file="../header.jsp" %>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="${pageContext.request.contextPath }/resources/js/regist.js"></script>
	<!-- 결제api -->
 	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
 	<script type="text/javascript">
	// 뒤로가기 방지
 	window.history.forward();
 	function noBack(){
 		window.history.forward();
 	}
 	</script>
</head>
	
	<body>
	<!-- 뒤로가기 방지 -->
	<body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">
		
	<div class="colorlib-loader"></div>
	
	
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
					<div class="col-sm-10 offset-md-1">
						<div class="process-wrap">
							<div class="process text-center active">
								<p><span>01</span></p>
								<h3 style="font-size: 15px;">장바구니</h3>
							</div>
							<div class="process text-center active">
								<p><span>02</span></p>
								<h3 style="font-size: 15px;">결제 중</h3>
							</div>
							<div class="process text-center">
								<p><span>03</span></p>
								<h3 style="font-size: 15px;">결제 완료</h3>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-8">
						<form method="post" class="colorlib-form">
							<h2>결제 세부 정보</h2>
		              			<div class="row">
		              			
			              		<div class="col-md-12">
									<div class="form-group">
										<div class="radio">
											<c:if test="${membersDTO.mcheck == 0}">
												<input type="radio" value="same" name="memdirect"> 구매자 정보와 동일 
											</c:if>
										  <input type="radio" value="different" name="memdirect" checked="checked"> 직접 입력
										</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="form-group">
										<label for="name">구매자 이름 *</label>
										<input type="text" id="fname" class="form-control" placeholder="이름 입력" name="mname"> <!-- 이름 -->
									</div>
								</div>


									<div class="col-md-6">
										<div class="form-group">
											<label for="phone">전화번호('-'포함) *</label>
				                    	<input type="text" id="phone" class="form-control" placeholder="전화번호 입력" name="o_phone">  <!-- 전화번호 -->
				                  		</div>
				               		</div>
			               		
								<!-- 등록된 정보로 구매 -->
			               		<div id="sameaddr" class="col-md-12" style="display: none;">
									<div class="form-group">
										<label for="daar">주소 *</label>
										<input type="text" id="madd" class="form-control">
									</div>
								</div>
								
								<!-- 직접입력 구매 -->
								<div class="col-md-6" id="directaddr1">
									<div class="form-group">
										<label for="email">우편번호</label>
										<input type="text" id="sample6_postcode" class="form-control" placeholder="우편번호" name="o_add1">  <!-- 우편번호 -->
										<input type="button" id ="adds" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" class="form-control" > 
									</div>
								</div>
								<div class="col-md-6" id="directaddr2">
									<div class="form-group">
										<label for="email">상세주소</label>
										<input type="text" id="sample6_detailAddress" placeholder="상세주소" class="form-control"  name="o_add3">  <!-- 상세주소 -->
									</div>
								</div>
								
								<div class="col-md-6" id="directaddr3">
									<div class="form-group">
										<label for="email">주소</label>
										<input type="text" id="sample6_address" placeholder="주소" class="form-control"  name="o_add2">  <!-- 주소 -->
									</div>
								</div>
								<div class="col-md-6" id="directaddr4">
									<div class="form-group">
										<label for="email">참고항목</label>
										<input type="text" id="sample6_extraAddress" placeholder="참고항목" class="form-control" name="o_add4">  <!-- 참고항목 -->
									</div>
								</div>
									
								
								<div class="col-md-12">
									<div class="form-group">
										<label for="email">이메일 주소</label>
										<input type="text" id="email" class="form-control" placeholder="이메일 주소 입력" >  <!-- 이메일주소 -->
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label for="email">주문 시 요청사항</label>
										<input type="text" id="requests" class="form-control" placeholder="요청사항 입력"  name="o_request">  <!-- 요청사항 -->
									</div>
								</div>
								</div>
								</form>
							</div>
							

					<div class="col-lg-4">
						<div class="row">
							<div class="col-md-12">
								<div class="cart-detail">
									<h2>계산정보</h2>
									<ul>
										<li>
											<ul>
												<c:forEach items="${cartgoodsList }" var="cartgoodsList">
													<li><span>${cartgoodsList.gname } / ${cartgoodsList.gstock } 개</span> <span>+ ${cartgoodsList.gprice }</span></li>  <!-- 주문하는 상품들 -->
													<li><span><sup>${cartgoodsList.gcolor } / ${cartgoodsList.gsize }</sup></span></li>
												</c:forEach>
											</ul>
										</li>
										<li><span>배송료</span> <span>+ ${fee }</span></li>
										<c:choose>
											<c:when test="${membershipDTO.m_rating eq '일반회원' }">
												<li><span>적립</span> <span>+ 0</span></li>										
											</c:when>
											<c:when test="${membershipDTO.m_rating eq 'Bronze' }">
												<li><span>적립</span> <span>+ ${Math.round((sum) * 0.001)}</span></li>										
											</c:when>
											<c:when test="${membershipDTO.m_rating eq 'Silver' }">
												<li><span>적립</span> <span>+ ${Math.round((sum) * 0.005)}</span></li>										
											</c:when>
											<c:when test="${membershipDTO.m_rating eq 'Gold' }">
												<li><span>적립</span> <span>+ ${Math.round((sum) * 0.01)}</span></li>										
											</c:when>
											<c:when test="${membershipDTO.m_rating eq 'VIP' }">
												<li><span>적립</span> <span>+ ${Math.round((sum) * 0.05)}</span></li>										
											</c:when>
										</c:choose>
										<li><span>마일리지</span><span><input type="radio" name="mileage" id="use" value="use" > 사용<input type="radio" name="mileage" id="not_used" value="not_used" checked="checked" > 미사용</span></li>
										<li><span>사용할 마일리지</span><span><input type="text" class="form-control" name="m_mileage" id="m_mileage" disabled="disabled" style="height: 25px !important;" value="0"></span>
										<li><span>보유한 마일리지</span><span>${membershipDTO.m_mileage}</span></li>
										<li><span>합계</span> <span id="totalPrice">${sum+fee }</span></li>  <!-- 총합계 -->
									</ul>
								</div>
						   </div>

						   <div class="w-100"></div>

						   <div class="col-md-12">
								<div class="cart-detail">
									<h2>지불 방법</h2>  <!-- 지불방법 (카드, 계좌이체 등등) -->

									<div class="form-group">
										<div class="col-md-12">
											<div class="radio">
											   <label><input type="radio" value="card" name="payment" checked="checked"> 카드/카카오페이/페이코 </label>
											</div>
										</div>
									</div>
									<div class="form-group">													<!-- 삭제 시 여기부터 -->
										<div class="col-md-12">
											<div class="checkbox">
											   <label><input type="checkbox" id="agreeCheck" value="agree"> 결제 이용 약관에 동의</label>   <!-- 동의체크 (too much 일경우 삭제가능)-->
											</div>
										</div>
									</div>																		<!-- 삭제 시 여기까지 -->
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 text-center">
								<p><input type="button" value="주문하기" onclick="manyPurchase()" class="btn btn-primary"></p>  <!-- 수정 -->
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
$(document).ready(function() {
	
	// 기존정보와 동일한지 직접입력할건지 선택
	$("input:radio[name=memdirect]").click(function() {
		
		var memdirect = $('input:radio[name=memdirect]:checked').val();
		
		if(memdirect == "same") {
			// 정보출력
			$("#fname").val('${membersDTO.mname}');
			$("#phone").val('${membersDTO.mphone}');
			$("#email").val('${membersDTO.memail}');
			$("#madd").val('${membersDTO.madd}');
			// 내 주소는 보이게, 주소 입력창은 안보이게
			$("#sameaddr").css("display", "block");
			$("#directaddr1").css("display", "none");
			$("#directaddr2").css("display", "none");
			$("#directaddr3").css("display", "none");
			$("#directaddr4").css("display", "none");
		} else if(memdirect == "different") {
			// 주소 입력창은 보이게, 내 주소는 안보이게
			$("#sameaddr").css("display", "none");
			$("#directaddr1").css("display", "block");
			$("#directaddr2").css("display", "block");
			$("#directaddr3").css("display", "block");
			$("#directaddr4").css("display", "block");
		}
	});
	
	// 마일리지 사용
	$("#m_mileage").keyup(function() {
		// 입력한 마일리지
		var mileage = parseInt($("#m_mileage").val());
		// 보유 마일리지
		var m_mileage = parseInt('${membershipDTO.m_mileage}');
		
		if(mileage > m_mileage) {
			alert("보유한 마일리지보다 많습니다.");
			var mileage = $("#m_mileage").val("0");
		} else {
			if(isNaN(mileage)) {
				mileage = 0;
			}
			var fee = parseInt('${fee}');
			var sum = parseInt('${sum}');
			var totalPrice = sum + fee - mileage;
			$("#totalPrice").text(totalPrice);
		}
	});
	
});

// 장바구니 결제
function manyPurchase() {
	// 결제를 위한 회원정보
	var id = '${sessionScope.loginId}';
	var name = $("#fname").val();
	var phone = $("#phone").val();
	var email = $("#email").val();
	var authority = '${sessionScope.authority}';
	var gprice = $("#totalPrice").text();
	var postcode = "";
	var addr = "";
	
	// 우편번호 자르기 위함 
	var memdirect = $('input:radio[name=memdirect]:checked').val();
	
	// 결제 동의 체크
	var agree = $('input:checkbox[id="agreeCheck"]:checked').val();
	
	// 테이블 추가를 위한 정보
	// 요청사항 / 성별
	var o_request = $("#requests").val();
	var mgender = '${membersDTO.mgender}';
	
	// 나이 계산
	// 내 생일년도
	var age1 = '${membersDTO.mbirth}'.substring(0,4);
	// 지금 년도
	var age2 = new Date().getFullYear();
	// 실제 나이 구하기	
	var o_age = age2 - age1 + 1;

	// 배송지 구매자정보 (same) / 직접입력 (different)
	if(memdirect == "same") {
		// 회원 주소 가져오기
		var madd = $("#madd").val();
		// 우편번호 자르기
		postcode = madd.substring(1, 6);
		// 상세주소 자르기
		addr = madd.substring(8);
	} else if (memdirect == "different") {
		postcode = $("#sample6_postcode").val();
		addr = $("#sample6_address").val() + " " + $("#sample6_detailAddress").val() + " " + $("#sample6_extraAddress").val();
		madd = "[" + $("#sample6_postcode").val() + "] " + $("#sample6_address").val() + " " + $("#sample6_detailAddress").val() + " " + $("#sample6_extraAddress").val();
	}
	
	// 필수항목 체크
	if(agree != "agree") {
		alert("약관 동의에 체크해주세요.");
		return false;
	}
	if(name == "") {
		alert("이름을 입력해주세요.");
		return false;
	}
	if(phone == "") {
		alert("연락처를 입력해주세요.");
		return false;
	}
	if(addr == "" || postcode == "") {
		alert("주소를 입력해주세요.");
		return false;
	}
	// 입력한 마일리지
	var mileage = parseInt($("#m_mileage").val());
	// 보유 마일리지
	var m_mileage = parseInt('${membershipDTO.m_mileage}');
	
	if(mileage > m_mileage) {
		alert("마일리지를 확인해주세요.");
		return false;
	} else if(isNaN(mileage)) {
		mileage = 0;
	}
	
	if(authority == 3) {
		//가맹점 식별코드
		IMP.init('imp25612012');
		IMP.request_pay({
		    pg : 'kcp', //inicis
		    pay_method : 'card',
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name : 'Slurp' , //결제창에서 보여질 이름
		    amount : gprice, //실제 결제되는 가격
		    buyer_email : email,
		    buyer_name : name,
		    buyer_tel : phone,
		    buyer_addr : addr,
		    buyer_postcode : postcode
		}, function(rsp) {
			console.log(rsp);
		    if ( rsp.success ) {
		    	var msg = '결제가 완료되었습니다.';
				location.href="cartbuySuccess?mid="+id+"&o_request="+o_request+"&o_age="+o_age+"&mgender="+mgender+"&o_add="+madd+"&o_phone="+phone+"&mname="+name+"&mileage="+mileage;
		    } else {
		    	 var msg = '결제에 실패하였습니다.\n';
		         msg += '에러내용 : ' + rsp.error_msg;
		    }
		    alert(msg);
		});
	} else {
		alert("일반회원만 구입 할 수 있습니다.");
	}
}

$(function() {
    $("#use").click(function() {
        $("#m_mileage").attr('disabled', false); // radio 사용 체크 시 마일리지 입력 칸 활성화
       
    });                                         // 177~178번줄에 해당

    $("#not_used").click(function() {
        $("#m_mileage").attr('disabled', true); // radio 미사용 체크 시 마일리지 입력 칸 비활성화
        $("#m_mileage").val("0");
        var mileage = parseInt($("#m_mileage").val());
		var fee = parseInt('${fee}');
		var sum = parseInt('${sum}');
		var totalPrice = sum + fee - mileage;
		$("#totalPrice").text(totalPrice);
    });
});

</script>
</html>