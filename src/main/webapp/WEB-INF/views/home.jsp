<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
	<head>
	<title>Slurp</title>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- header -->
	<%@include file="header.jsp" %>
	
	<style type="text/css">
		.btn1{
			color: black;
			font-family: 'Do Hyeon', sans-serif;
			font-size: 15px;
			font-weight: 100;
			cursor: pointer;
			border-radius: 5px;
			background-color: #f0f0f0;
			border: 0;
			padding: 10px 20px;
			margin-top: 20px;
			letter-spacing: 1px;
		}
		.btn1:hover{
			background-color: gray;
			color: white;
		}
		.btn1:focus{
			outline: 0;
			border: 0;
		}
		.cover{
			width: 300px;
			height: 300px;
			object-fit: cover;
		}
	</style>
	</head>
	
	<body>
		
	
	
	<div id="page">
	
		<!-- 상단메뉴 시작 -->
		<%@include file="topbar.jsp" %>
		<!-- 상단메뉴 끝 -->
		
		<aside id="colorlib-hero" style="margin-top: 150px">
			<div class="flexslider">
				<ul class="slides">
			   	<li style="background-image: url(https://i.pinimg.com/originals/6e/2a/86/6e2a863936de10ecda1bfccae2e5bb61.jpg);">
			   		<div class="overlay"></div>
			   		<div class="container-fluid"></div>
			   	</li>
			   	<li style="background-image: url(https://image11.coupangcdn.com/image/cmg/oms/banner/be86f836-68bf-4137-a3a4-93c6aeaabfff_1904x560.png);">
			   		<div class="overlay"></div>
			   		<div class="container-fluid">
			   		</div>
			   	</li>
			   	<li style="background-image: url(https://bokinibio.com/web/upload/6fc591ca821d562da18532404c95cb28.jpg);">
			   		<div class="overlay"></div>
			   		<div class="container-fluid">
			   		</div>
			   	</li>
			  	</ul>
		  	</div>
		</aside>
		
		<div class="colorlib-product">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 offset-sm-2 text-center colorlib-heading">
						<h2>NEW</h2>
					</div>
				</div>
				<div class="row" id="newListArea">
					
				</div>
				<div class="row">
					<div class="col-md-12 text-center">
						<p><a href="news" class="btn1">더보기</a></p> <!-- 신상품목록으로 이동 -->
					</div>
				</div>
			</div>
			
			<hr> 

		<div class="colorlib-product">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 offset-sm-2 text-center colorlib-heading">
						<h2>BEST</h2>
					</div>
				</div>
				<div class="row" id="bestListArea">
					
				</div>
				<div class="row">
					<div class="col-md-12 text-center">
						<p><a href="best" class="btn1">더보기</a></p> <!-- 베스트상품목록으로 이동 -->
					</div>
				</div>
				
			</div>
		</div>
		</div>
		</div>
		<!-- footer -->
		<%@include file="footer.jsp" %>
</body>
	
	<script type="text/javascript">
	$(document).ready(function() {
		
		
		$.ajax({
			type : "post",
			url : "newList",
			dataType : "json",
			async : false,
			success : function(result) {
				var output = "";
				for(var i in result){
					output += "<div class='col-lg-3 mb-4 text-center'>"
					output += "<div class='product-entry border'>"
					output += "<a href='goodsView?gcode="+result[i].gcode+"' class='prod-img'>"
					output += "<img src='${pageContext.request.contextPath }/resources/images/goods/"+result[i].gimg+"' class='img-fluid cover' alt=''>"
					output += "</a>"
					output += "<div class='desc'>"
					output += "<h2><a href='goodsView?gcode="+result[i].gcode+"'>"+result[i].cname+"</a></h2>"
					output += "<span class='priceOriginal'>"+result[i].gname+"</span>"
					output += "<span class='price'>"+result[i].gprice+"</span>"
					output += "</div>"
					output += "</div>"
					output += "</div>"
				}
				$("#newListArea").html(output);
			}
		})
		
		$.ajax({
			type : "post",
			url : "bestList",
			dataType : "json",
			async : false,
			success : function(result) {
				var output = "";
				for(var i in result){
					output += "<div class='col-lg-3 mb-4 text-center'>"
					output += "<div class='product-entry border'>"
					output += "<a href='goodsView?gcode="+result[i].gcode+"' class='prod-img'>"
					output += "<img src='${pageContext.request.contextPath }/resources/images/goods/"+result[i].gimg+"' class='img-fluid cover' alt=''>"
					output += "</a>"
					output += "<div class='desc'>"
					output += "<h2><a href='goodsView?gcode="+result[i].gcode+"'>"+result[i].cname+"</a></h2>"
					output += "<span class='priceOriginal'>"+result[i].gname+"</span>"
					output += "<span class='price'>"+result[i].gprice+"</span>"
					output += "</div>"
					output += "</div>"
					output += "</div>"
				}
				$("#bestListArea").html(output);
			}
		})
	})
	</script>
	
	
</html>


