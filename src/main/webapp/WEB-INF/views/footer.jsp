<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <footer id="colorlib-footer" role="contentinfo">
			<div class="container" style="height: 300px;">
				<div class="row ">
					<div class="col footer-col colorlib-widget">
						<h4>About Slurp</h4>
						<p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life</p>
						<p>
							<ul class="colorlib-social-icons">
								<li><a href="#"><i class="fab fa-twitter-square"></i></a></li>
								<li><a href="#"><i class="fab fa-facebook-square"></i></a></li>
								<li><a href="#"><i class="fab fa-youtube"></i></a></li>
								<li><a href="#"><i class="fab fa-instagram"></i></a></li>
							</ul>
						</p>
					</div>
					<div class="col footer-col colorlib-widget">
						<h4>Customer Care</h4>
						<p>
							<ul class="colorlib-footer-links">
								<li><a href="#">Contact</a></li>
								<li><a href="#">Returns/Exchange</a></li>
								<li><a href="#">Gift Voucher</a></li>
								<li><a href="#">Wishlist</a></li>
								<li><a href="#">Special</a></li>
								<li><a href="#">Customer Services</a></li>
								<li><a href="#">Site maps</a></li>
							</ul>
						</p>
					</div>
					<div class="col footer-col colorlib-widget">
						<h4>Information</h4>
						<p>
							<ul class="colorlib-footer-links">
								<li><a href="#">About us</a></li>
								<li><a href="#">Delivery Information</a></li>
								<li><a href="#">Privacy Policy</a></li>
								<li><a href="#">Support</a></li>
								<li><a href="#">Order Tracking</a></li>
							</ul>
						</p>
					</div>

					<div class="col footer-col">
						<h4>News</h4>
						<ul class="colorlib-footer-links">
							<li><a href="#">Blog</a></li>
							<li><a href="#">Press</a></li>
							<li><a href="#">Exhibitions</a></li>
						</ul>
					</div>

					<div class="col footer-col">
						<h4 style="margin-bottom: 5px;">Contact Information</h4>
					<div class="item">
					
                    <div id="map" style="min-height: 300px; width: 300px;"></div>
                    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=58c9e56bfe542986298bdd36cf43dbaf"></script>
                    <script>
                        var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
                        var options = { //지도를 생성할 때 필요한 기본 옵션
                            center: new kakao.maps.LatLng(37.438901850118754, 126.67509884457442), // 학원 중심 좌표
                            level: 3 // 확대, 축소 정도
                        };
                        var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
                        
                     	// 마커가 표시될 위치
                        var markerPosition  = new kakao.maps.LatLng(37.438901850118754, 126.67509884457442);
                        
                     	// 마커 생성
                        var marker = new kakao.maps.Marker({
                            position: markerPosition
                        });
                     	
                        // 마커가 지도 위에 표시되도록 설정
                        marker.setMap(map);
                        
                     	// 마커에 커서가 오버됐을 때 마커 위에 표시할 인포윈도우를 생성합니다
                        var iwContent = '<div style="padding:5px;">인천일보아카데미</div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

                        // 인포윈도우를 생성합니다
                        var infowindow = new kakao.maps.InfoWindow({
                            content : iwContent
                        });

                        // 마커에 마우스오버 이벤트를 등록합니다
                        kakao.maps.event.addListener(marker, 'mouseover', function() {
                          // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
                            infowindow.open(map, marker);
                        });

                        // 마커에 마우스아웃 이벤트를 등록합니다
                        kakao.maps.event.addListener(marker, 'mouseout', function() {
                            // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
                            infowindow.close();
                        });
                    </script>
                    
                    <div style="text-align: center; margin-top: 5px;">
                    <p>인천광역시 미추홀구 매소홀로488번길 6-32 태승빌딩 5층</p>
                    </div>
                    
            		</div>
    				</div>
						
					</div>
				</div>
			</div>
			
		</footer>
	</div>

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="fas fa-chevron-up" style="margin-top: 15px;"></i></a>
	</div>
	
	</div>
	
	<!-- jQuery -->
	<script src="${pageContext.request.contextPath }/resources/js/jquery.min.js"></script>
   <!-- popper -->
   <script src="${pageContext.request.contextPath }/resources/js/popper.min.js"></script>
   <!-- bootstrap 4.1 -->
   <script src="${pageContext.request.contextPath }/resources/js/bootstrap.min.js"></script>
   <!-- jQuery easing -->
   <script src="${pageContext.request.contextPath }/resources/js/jquery.easing.1.3.js"></script>
	<!-- Waypoints -->
	<script src="${pageContext.request.contextPath }/resources/js/jquery.waypoints.min.js"></script>
	<!-- Flexslider -->
	<script src="${pageContext.request.contextPath }/resources/js/jquery.flexslider-min.js"></script>
	<!-- Owl carousel -->
	<script src="${pageContext.request.contextPath }/resources/js/owl.carousel.min.js"></script>
	<!-- Magnific Popup -->
	<script src="${pageContext.request.contextPath }/resources/js/jquery.magnific-popup.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/js/magnific-popup-options.js"></script>
	<!-- Date Picker -->
	<script src="${pageContext.request.contextPath }/resources/js/bootstrap-datepicker.js"></script>
	<!-- Stellar Parallax -->
	<script src="${pageContext.request.contextPath }/resources/js/jquery.stellar.min.js"></script>
	<!-- Main -->
	<script src="${pageContext.request.contextPath }/resources/js/main.js"></script>