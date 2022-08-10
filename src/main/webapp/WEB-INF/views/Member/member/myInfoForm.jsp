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
	<%@include file="../../header.jsp" %>
	
	<style type="text/css">
		table{
			width: 100%;
			color: black;
		}
	
		th{
			
			font-size: 15px;
			border-bottom: 1px silver solid;
			letter-spacing: 1px;
			font-family: fantasy;
		}
			

		td{
			background-color: white;
			font-size: 15px;
			border-bottom: 1px silver solid;
		}

		table,th,td{
			height: 30px;
		}
		
		.linemenu{
			color:  black;
			letter-spacing: 2px;
			margin-left: 15px;
			font-size: 20px;
		}
		.btn1{
			color: black;
			font-family: 'Do Hyeon', sans-serif;
			font-size: 13px;
			font-weight: 100;
			cursor: pointer;
			width: 10%;
			margin-left: 45%;
			margin-top: 10px;
			border-radius: 10px;
			background-color: #f0f0f0;
			border: 1px black solid;
		}
		.btn1:hover{
			background-color: gray;
			color: white;
		}
		.btn1:focus{
			outline: 0;
			border: 0;
		}
		.infofont{
			font-size: 15px;
			border-bottom: 0;
		}
		.img1{
			width: 100px;
		}
	</style> 
	</head>
	<body>
	<div id="page">
	
		<!-- 상단메뉴 시작 -->
		<%@include file="../../topbar.jsp" %>
		<!-- 상단메뉴 끝 -->
		
		<div class="container" style="margin-top: 150px;">
		<div style="background-color: #f0f0f0; border-radius: 10px;"><h2 class="linemenu">마이페이지</h2></div>
		<div style="margin-bottom: 50px; margin-left: 5%;">
		
		<h3>
		<c:if test="${sessionScope.access_token == null }">
		<h3>${sessionScope.loginId } 님 <a href="myInfoModifyForm?mid=${sessionScope.loginId}" style="font-size: 12px; margin-left: 20px;">회원정보변경</a><a href="#" onclick="memberDelete()" style="font-size: 12px; margin-left: 20px;">회원탈퇴</a></h3>
		</c:if>
		<c:if test="${sessionScope.access_token != null }">
	 	${sessionScope.nickname } 
	 	<img src="${pageContext.request.contextPath }/resources/images/kakaoLogo.png" style="width: 30px;">
	 	<a href="#" onclick="memberDelete()" style="font-size: 12px; margin-left: 20px;">회원탈퇴</a>
		</c:if>
		</h3>
		<table style="width: 20%;">
			<tr>
				<td class="infofont">회원등급 :</td>
				<td class="infofont">${memInfo.m_rating }</td>
			</tr>
			<tr>
				<td class="infofont">마일리지 :</td>
				<td class="infofont">${memInfo.m_mileage }</td>
			</tr>
		</table>
		</div>
		
		<div style="margin-bottom: 50px; min-height: 500px;">
		<div style="background-color: #f0f0f0; border-radius: 10px;"><h1 class="linemenu">주문 내역</h1></div>
		<div style="margin-left: 5%; margin-right: 5%;">
		<table>
				<tr>
					<th colspan="2">상품정보</th>
					<th>주문일자</th>
					<th>주문번호</th>
					<th>주문금액</th>
					<th>주문상태</th>
				</tr>
			<c:forEach items="${orderList }" var="orderList" begin="0" end="4" step="1">
				<tr>
					<td style="width: 15%"><a href="goodsView?gcode=${orderList.gcode}"><img src="${pageContext.request.contextPath }/resources/images/goods/${orderList.gimg}" class="img1"></a></td>
					<td style="width: 20%"><a href="goodsView?gcode=${orderList.gcode}">${orderList.gname }</a></td>
					<td style="width: 15%">${orderList.o_date }</td>
					<td style="width: 20%">${orderList.o_code }</td>
					<td style="width: 15%">${orderList.o_gprice } 원</td>
					<c:choose>
						<c:when test="${orderList.o_state == '주문접수' }">
							<td style="width: 15%">${orderList.o_state }</td> <!-- 후기작성 페이지로 링크 걸어야함 -->
						</c:when>
						<c:when test="${orderList.o_state == '배송시작' }">
							<td style="width: 15%; text-align: left;"><input type="button" onclick="buyConfirmed('${orderList.o_code }')" class="btn" value="구매확정"></td>
						</c:when>
						<c:when test="${orderList.o_state == '구매완료' }">
							<td style="width: 15%; text-align: left;"><input type="button" style="display: inline" onclick="review('${orderList.gcode}', '${orderList.o_code }')" class="btn" value="후기작성"></td>
						</c:when>
						<c:otherwise>
							<td style="width: 15%">${orderList.o_state }</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
				
			<!-- 5개 이상일떄 표시 -->
			<tr>
				<td colspan="7" style="border-bottom: 0;"><button class="btn1" onclick="orderlistPlus()">자세히보기</button></td>
			</tr>
		</table>
		</div>
		</div>
		
		<div style="background-color: #f0f0f0; border-radius: 10px;"><h1 class="linemenu">내 질문 (최근 5건)</h1></div>
		<div style="margin-left: 5%; margin-right: 5%; min-height: 300px;">
		<table>
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>답변여부</th>
			</tr>
			<c:forEach items="${qnaList }" var="qnaList" begin="0" end="5" step="1">
				<tr>
					<td style="width: 20%">${qnaList.qnum }</td>
					<td style="width: 40%"> <a href="qnaListForm">${qnaList.qtitle }</a> </td>
					<td style="width: 40%">${qnaList.qcheck }</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		</div>
		
		</div>
		<!-- footer -->
		<%@include file="../../footer.jsp" %>

	</body>
<script type="text/javascript">
function buyConfirmed(o_code) {
	var confirmed = confirm("구매 확정 하시겠습니까?");
	var mid = '${sessionScope.loginId}';
	
	if(confirmed) {
		alert("구매 확정 되었습니다.");
 		$.ajax({
			type : "post",
			url : "buyConfirmed",
			data : {"mid" : mid,
					"o_code" : o_code },
			success : function(result) {
				if(result == "1") {
					location.href="showMemberInfo?mid="+mid;
				} 
			}
		});
 	}
}

// 리뷰작성 페이지 이동
function review(gcode, o_code) {
	var mid = '${sessionScope.loginId}';
	
	location.href="review?mid="+mid+"&gcode="+gcode+"&o_code="+o_code;
}
// 주문내역 자세히보기
function orderlistPlus() {
	var mid = '${sessionScope.loginId}';
	
	location.href="orderlistPlus?mid="+mid;
}

// 회원탈퇴
function memberDelete() {
	var mid = '${sessionScope.loginId}';
	alert("회원탈퇴시 멤버쉽을 포함한 모든 정보와 구매내역이 사라집니다.")
	var deleteCheck = confirm("탈퇴 하시겠습니까?");
	if(deleteCheck) {
		location.href="memberDelete?mid="+mid;
	}
	
}
</script>
</html>


