<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="colorlib-nav" role="navigation"  >
			<div class="top-menu fixed-top" id="top-menu" style="background-color: white; opacity: 0.9">
				<div class="container">
				
				<div class="find_info" style="float: right;">
				<c:if test="${sessionScope.loginId != null}">
					<c:if test="${sessionScope.access_token == null }">
					${sessionScope.loginId } 님 환영합니다 ! 
					<a href="logout" style="margin-left: 10px;"> 로그아웃 </a> | 
					</c:if>
					<c:if test="${sessionScope.access_token != null }">
					<img src="resources/images/kakaoLogo.png" style="width: 20px; margin-right: 3px;">
					${sessionScope.nickname } 님 환영합니다 !
					<a href="kakaoLogout" style="margin-left: 10px;"> 로그아웃 </a> | 
					</c:if>
				</c:if>
					<a href="http://pf.kakao.com/_czAms" target="_blank"><img src="${pageContext.request.contextPath }/resources/images/plusfriendlogo.png" style="width: 80px; margin-right: 3px;"></a>
				</div>
					<div class="row" style="height: 60px">
						<div class="col-sm-7 col-md-9">
							<div id="colorlib-logo" class="logo"><a href="home">Slurp</a></div>
						</div>
		         </div>
					<div class="row">
						<div class="col-sm-12 text-left menu-1">
							<ul>
								<li>
								<a href="best">Best</a>
								</li>
								<li>
								<a href="news">New</a>
								</li>
								<li class="has-dropdown">
									<a href="#">남성</a>
									<ul class="dropdown" style="margin-top: 30px;">
										<li><a href="man_top">상의</a></li>
										<li><a href="man_pants">하의</a></li>
										<li><a href="man_outer">아우터</a></li>
										<li><a href="man_shoes">신발</a></li>
										
									</ul>
								</li>
								<li class="has-dropdown">
									<a href="#">여성</a>
									<ul class="dropdown" style="margin-top: 30px;">
										<li><a href="woman_top">상의</a></li>
										<li><a href="woman_pants">하의</a></li>
										<li><a href="woman_outer">아우터</a></li>
										<li><a href="woman_shoes">신발</a></li>
										
									</ul>
								</li>
								
								<li class="has-dropdown">
									<a href="#">브랜드관</a>
									<ul class="dropdown" style="margin-top: 30px;">
										<li><a href="musinsa" style="font-size: 13px;">무신사 스탠다드</a></li> <!-- 18 -->
										<li><a href="nike">나이키</a></li> <!-- 19 -->
										<li><a href="adidas">아디다스</a></li> <!-- 20 -->
										<li><a href="zara">자라</a></li>
										<li><a href="giordano">지오다노</a></li>
									</ul>
								</li>
								<li class="has-dropdown">
									<a href="#">고객센터</a>
									<ul class="dropdown" style="margin-top: 30px;">
										<li><a href="qnaListForm">Q&A</a></li>
										<li><a href="eventList">이벤트 게시판</a></li>
										<li><a href="faqListForm">FAQ</a></li>
									</ul>
								</li>
								
								
								<c:choose>
									<c:when test="${sessionScope.authority == 1 }">
										<li class="cart"><a href="companyCheckForm"> 업체승인</a></li> 
										<li class="cart"><a href="adminReportForm"> 신고내역</a></li>
									</c:when>
									<c:when test="${sessionScope.authority == 2 and ccheck == 1 }"> 
										<li class="cart"><a href="companyManageForm?cid=${sessionScope.loginId }"> 상품관리</a></li>
										<li class="cart"><a href="registerGoodsForm"> 상품등록</a></li>
										<li class="cart"><a href="companyDeliveryCheck?cid=${sessionScope.loginId }"> 주문접수</a></li>
									</c:when>
									<c:when test="${sessionScope.authority == 2 and ccheck == 0 }"> 
										<li class="cart"><a href="#" onclick="checkFalse()"> 상품관리</a></li>
										<li class="cart"><a href="#" onclick="checkFalse()"> 상품등록</a></li>
										<li class="cart"><a href="#" onclick="checkFalse()"> 주문접수</a></li>
									</c:when>
									<c:when test="${sessionScope.authority == 3 }">
										<li class="cart"><a href="shoppingCart?mid=${sessionScope.loginId }"> 장바구니</a></li>
										<li class="cart"><a href="wishList?mid=${sessionScope.loginId }">찜목록</a></li>
										<li class="cart"><a href="showMemberInfo?mid=${sessionScope.loginId}"> 마이페이지</a></li>
									</c:when>
									<c:otherwise>
										<li class="cart"><a href="registForm"> 회원가입</a></li>
										<li class="cart"><a href="loginForm"> 로그인</a></li>
									</c:otherwise>
								</c:choose>
								
							</ul>
						</div>
					</div>
				</div>
			</div>
			
		</nav>
<script>
	function checkFalse() {
		alert("관리자 승인 후 이용가능합니다.");
	}
</script>