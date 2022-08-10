<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
	<title>Slurp</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- header -->
	<%@include file="../header.jsp" %>
	
	<script type="text/javascript">
	// 뒤로가기 방지
 	window.history.forward();
 	function noBack(){
 		window.history.forward();
 	}
 	// 새로고침 방지
 	function noEvent() {
 	    if (event.keyCode == 116) {
 	        event.keyCode= 2;
 	        return false;
 	    }
 	    else if(event.ctrlKey && (event.keyCode==78 || event.keyCode == 82))
 	    {
 	        return false;
 	    }
 	}
 	document.onkeydown = noEvent;

	</script>
	
	</head>
	<body>
	<!-- 뒤로가기 방지 -->
	<body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">
	<!-- 새로고침 방지 -->
	<body oncontextmenu="return false">


	<div class="colorlib-loader"></div>

	<div id="page">
	
		<!-- 상단메뉴 시작 -->
		<%@include file="../topbar.jsp" %>
		<!-- 상단메뉴 끝 -->
	
		<nav class="colorlib-nav" role="navigation">
			<div class="top-menu">
				<div class="container">
					<div class="row">
						<div class="col-sm-7 col-md-9">
							<div id="colorlib-logo"><a href="index.html">Footwear</a></div>
						</div>
						<div class="col-sm-5 col-md-3">
			            <form action="#" class="search-wrap">
			               <div class="form-group">
			                  <input type="search" class="form-control search" placeholder="Search">
			                  <button class="btn btn-primary submit-search text-center" type="submit"><i class="icon-search"></i></button>
			               </div>
			            </form>
			         </div>
		         </div>
				</div>
			</div>
		</nav>

		<div class="breadcrumbs">
			<div class="container">
				<div class="row">
					<div class="col">
						<p class="bread"><span><a href="index.html">Home</a></span> / <span>Purchase Complete</span></p>
					</div>
				</div>
			</div>
		</div>


		<div class="colorlib-product">
			<div class="container">
				<div class="row row-pb-lg">
					<div class="col-sm-10 offset-md-1">
						<div class="process-wrap">
							<div class="process text-center active">
								<p><span>01</span></p>
								<h3>장바구니</h3>
							</div>
							<div class="process text-center active">
								<p><span>02</span></p>
								<h3>결제 중</h3>
							</div>
							<div class="process text-center active">
								<p><span>03</span></p>
								<h3>결제 완료</h3>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-10 offset-sm-1 text-center">
						<p class="icon-addcart"><span><i class="icon-check"></i></span></p>
						<h2 class="mb-4">구매해 주셔서 감사합니다. 주문이 완료되었습니다.</h2>
						<p>
							<a href="home"class="btn btn-primary btn-outline-primary">Home</a>
						</p>
					</div>
				</div>
			</div>
		</div>

		<!-- footer -->
		<%@include file="../footer.jsp" %>
	</body>
</html>

