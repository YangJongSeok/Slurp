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
	<%@include file="../header.jsp" %>
	
	<style type="text/css">
		table,td,tr{
			border-collapse: collapse;
			font-size: 15px;
		}
		td{
			border-bottom: 1px solid #e6e6e6;
			color: black;
			padding: 5px;
		}
		input[type="text"] {
    		height: 30px !important;
    		width: 250px;
		}
		.btn1{
			color: black;
			font-family: 'Do Hyeon', sans-serif;
			font-size: 15px;
			font-weight: 100;
			cursor: pointer;
			border-radius: 5px;
			background-color: #f0f0f0;
			border: 0;
			padding: 5px 10px 5px 10px;
			margin-top: 20px;
		}
		.btn1:hover{
			background-color: gray;
			color: white;
		}
		.btn1:focus{
			outline: 0;
			border: 0;
		}
		a{
			color: black;
		}
		pre{
			font-family: 'Noto Sans KR', sans-serif;
		}
		.qicon{
			background-color: darkgray;
    		width: 25px;
    		height: 25px;
    		text-align: center;
    		border-radius: 50%;
    		color: white;
    		line-height: 26px;
    		position: absolute;
		}
		.aicon{
			background-color: #88c8bc;
    		width: 25px;
    		height: 25px;
    		text-align: center;
    		border-radius: 50%;
    		color: white;
    		line-height: 26px;
    		position: absolute;
		}
	</style>

	</head>
	
	<body>
		
	
	
	<div id="page">
	
		<!-- 상단메뉴 시작 -->
		<%@include file="../topbar.jsp" %>
		<!-- 상단메뉴 끝 -->
		<div class="container" style="margin-top: 150px;">
			<div style="background-color: #f0f0f0;">
			<h5 style="margin-left: 10px; letter-spacing: 5px;">Q&A</h5></div>
			<div style="min-height: 300px;">
				
				<table style="width: 100%;">
					<c:forEach items="${qList }" var="ql" varStatus="status">
					<tr>
						<!-- 글번호 -->
						<td style="text-align: center;">
							${ql.qnum }
						</td>
						
						<td style="width: 50%;">
							<!-- 글제목 -->
							<span style="margin-left: 20px;">
								<c:if test="${ql.scheck == '비밀'}">
									<c:choose>
										<c:when test="${sessionScope.loginId == ql.mid || sessionScope.authority == 1 }">
											<span class="icon-lock"></span><a href="#" onclick="none('${ql.qnum}')">
											${ql.qtitle }
											</a>
										</c:when>
										<c:otherwise>
											<span class="icon-lock"></span><span>비밀글은 작성자와 관리자만 볼 수 있습니다.</span>
										</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${ql.scheck == '공개'}">
									<a href="#" onclick="none('${ql.qnum}')">
										${ql.qtitle }
									</a>
								</c:if>
								
							</span>
						</td>
						
						<td style="width: 15%;">
							<!-- 작성자 -->
							<span>${ql.mid }</span>
						</td>
						
						<td style="width: 15%;">
							<!-- 작성일 -->
							<span>${ql.qdate}</span>
						</td>
						
						<td>
						<!-- 답변체크 -->
							<!-- 대기상태 -->
							<c:if test="${ql.qcheck == '대기' }">
							<span style="float: right; margin-right: 20px;" id="qcheck">
							답변 대기
							</span>
							</c:if>
							<!-- 완료상태 -->
							<c:if test="${ql.qcheck == '완료' }">
							<span style="float: right; margin-right: 20px; color: #88c8bc;" id="qcheck">
							답변 완료
							</span>
							</c:if>
						</td>
					</tr>
					
					<!-- 가려진 display -->
					<tr style="display: none;" id="${ql.qnum }">
						<td style="background-color: #fafafa;"></td>
						<!-- 질문내용 -->
						<td style="padding: 20px; background-color: #fafafa;">
							<div>
								<div class="qicon">Q</div> <!-- qna 아이콘 -->
								<pre style="padding-left: 30px; white-space: pre-wrap;">${ql.qcontent }</pre>
							</div>
						</td>
						<td style="padding: 20px; background-color: #fafafa;"colspan="3">
									
							<div id="qnaReplyArea${ql.qnum }">
								<!-- Ajax로 답변내용 출력 -->
							</div>
							
						</td>
					</tr>
					</c:forEach>
				</table>
				
				
			<!-- 검색 기능 -->
			<form action="qnaSearch" style="position:  absolute;">
				<select style="height: 30px;" name="type">
					<option value="title">제목</option>
					<option value="content">내용</option>
					<option value="tcon">제목/내용</option>
				</select>
				<input type="text" name="text">
				<button class="btn1">검색</button>
			</form>
			
			<!-- 회원 작성 -->
			<c:if test="${sessionScope.authority == 3 }">
			<button style="float: right;" class="btn1" onclick="location.href='qnaWriteForm'">작성하기</button>
			</c:if>
			</div>
			
			
			
			<div class="row" style="margin-top: 70px;">
					<div class="col-md-12 text-center">
						<div class="block-27">
		               <ul>
			               <!-- 페이징 처리 -->
						<c:choose>
							<c:when test="${page.page <= 1 }">
								<li><a><i class="fas fa-chevron-left"></i></a></li>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${type != null && text != null }">
										<li><a href="qnaSearch?page=${page.page - 1}&&type=${type}&&text=${text}"><i class="fas fa-chevron-left"></i></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="qnaListForm?page=${page.page - 1}"><i class="fas fa-chevron-left"></i></a></li>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					
						<c:forEach begin="${page.startpage}" end="${page.endpage}" var="pageNum" step="1">
							<c:choose>
								<c:when test="${page.page == pageNum}">
								<li><a style="background-color: #cccccc;">${pageNum}</a></li>
								</c:when>
								<c:otherwise>
									<c:choose>
									<c:when test="${type != null && text != null }">
										<li><a href="qnaSearch?page=${pageNum}&&type=${type}&&text=${text}">${pageNum}</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="qnaListForm?page=${pageNum}">${pageNum}</a></li>
									</c:otherwise>
									</c:choose>																	
								</c:otherwise>
							</c:choose>
						</c:forEach>
					
						<c:choose>
							<c:when test="${page.page >= page.maxpage }">
								<li><a><i class="fas fa-chevron-right"></i></a></li>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${type != null && text != null }">
										<li><a href="qnaSearch?page=${page.page + 1}&&type=${type}&&text=${text}"><i class="fas fa-chevron-right"></i></a></li>
									</c:when>
									<c:otherwise>
									<li><a href="qnaListForm?page=${page.page + 1}"><i class="fas fa-chevron-right"></i></a></li>
									</c:otherwise>
								</c:choose>				
							</c:otherwise>
						</c:choose>
						
		               </ul>
		            </div>
					</div>
				</div>
			
		</div>
		
		
		
		</div>
		<!-- footer -->
		<%@include file="../footer.jsp" %>

	</body>
	<script type="text/javascript">
	
		function none(qnum) {
			
			if($('#'+qnum).css("display") == "none"){
				$('#'+qnum).css("display","");
					
					/* 질문 제목 누르면 답변 불러오는 메소드 */
					$.ajax({
						type : "post",
						url : "qnaReplyList",
						data : {"qnum" : qnum},
						success : function(result){
							var output = "";
							console.log(result.qr_num);
							 
							if(result.qr_num == 1){
								console.log("result != ''")
								output += "<div class='aicon'>A</div>" <!-- qna 아이콘 -->
								output += "<pre style='padding-left: 30px; white-space: pre-wrap;'>"+result.qr_content+"</pre>"
								<!-- 삭제버튼 --> <!-- 답변내용 없을시 작성해야되니 스크립트로 display none -->
								output += "<c:if test='${sessionScope.authority == 1 }'>"
								output += "<button style='float: right;' class='btn1' id='deleteBtn${ql.qnum }' onclick='getQnaReplyModify(" +result.qr_qnum + ")'>수정</button>"
								output += "</c:if>"
							}else{
								console.log("result == ''")
								$('#deleteBtn'+qnum).css("display","none")   // 답변내용 있을 때 삭제버튼 display none
								/* $('#qr_content').css("display","none") */
								output += "<input type='hidden' id='qr_qnum' value="+ result.qr_qnum +">"
								output += "<c:if test='${sessionScope.authority == 1 }'>"  <!-- // 관리자만 답변작성 할 수 있도록 -->
								output += "<textarea cols='50' rows='3' id='qr_content' name='qr_content' style='resize: none;'></textarea>"
								output += "<button type='submit' style='float: right;' class='btn1' onclick='qnaReplyRegist()'>등록</button>"  <!-- // 등록할때 작성된 내용없으면 비활성화 시켜야됩니다. -->
								output += "</c:if>"
							}
							$("#qnaReplyArea"+qnum).html(output);
						}
					})
			}else{
				$('#'+qnum).css("display","none")
			}
				
		}
		
		/* 질문에 답변 등록 메소드 */
		function qnaReplyRegist(){
			var qr_content = $("#qr_content").val();
			var qr_qnum = $("#qr_qnum").val();
			var aid = '${sessionScope.loginId}'
			console.log("글번호 : " + qr_qnum + "글 내용 : " + qr_content);
			
			$.ajax({
					type : "get",
					url : "qnaReplyRegist",
					data : {"qr_qnum" : qr_qnum,
							"qr_content" : qr_content,
							"aid" : aid },
					success : function(result){
						location.reload();
						$("#qcheck").html("답변완료");
					}
			});
		}
		/* 답변 수정 텍스트창 띄우는 메소드 */
		function getQnaReplyModify(qnum){
			
					$.ajax({
						type : "post",
						url : "getQnaReplyModify",
						data : {"qnum" : qnum},
						success : function(result){
							var output = "";
							console.log(result.qr_num);
							 
							if(result.qr_num == 1){
								console.log("result != ''")
								output += "<input type='hidden' id='qr_qnum' value="+ result.qr_qnum +">"
								output += "<textarea cols='50' rows='3' id='qr_content' name='qr_content' style='resize: none; padding-left: 10px;' >" + result.qr_content +"</textarea>"
								<!-- 삭제버튼 --> <!-- 답변내용 없을시 작성해야되니 스크립트로 display none -->
								output += "<c:if test='${sessionScope.authority == 1 }'>"
								output += "<button type='submit' style='float: right;' class='btn1' id='deleteBtn${ql.qnum }' onclick='qnaReplyModify()'>확인</button>"
								output += "</c:if>"
							}
							$("#qnaReplyArea"+qnum).html(output);
						}
					})
			}
				
		
		/* 답변 수정 실행하는 메소드 */
		function qnaReplyModify(){
			var qr_content = $("#qr_content").val();
			var qr_qnum = $("#qr_qnum").val();
			var aid = '${sessionScope.loginId}'
			console.log("글번호 : " + qr_qnum + "글 내용 : " + qr_content);
			
			$.ajax({
					type : "get",
					url : "qnaReplyModify",
					data : {"qr_qnum" : qr_qnum,
							"qr_content" : qr_content,
							"aid" : aid },
					success : function(result){
						location.reload();
					}
			});
		}
	</script>
</html>


