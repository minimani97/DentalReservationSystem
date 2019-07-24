<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta charset="utf-8">
<title>Home</title>
</head>
<body>

	<div id="map" style="width: 700px; height: 500px;"></div>
	<p>현재 위치에서 검색</p>
	<input type="button" onclick="init()" value="내 위치로 검색">
	<br>
	<input type="text" id="sample5_address" placeholder="주소">
	<input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색">

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=214630e88c9f141416bd0733325ee211&libraries=services"></script>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

	<script>
		//현재 위치
		window.onload = map_initialize1();
		function init() {
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(showPosition);
			} else {
				alert("Geolocation is not supported by this brower.");
			}
		}
		//window.onload = init();

		var nLat;
		var nLng;

		//위치 표시
		function showPosition(position) {
			nLat = position.coords.latitude; //-0.0095104
			nLng = position.coords.longitude; //+0.0040756
			console.log("nLag : " + nLat);
			console.log("nLng : " + nLng);

			map_initialize2(nLat, nLng);

		}
		function map_initialize1(nLat, nLng) {

			var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
			mapOption = {
				center : new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
				level : 4
			// 지도의 확대 레벨
			};

			var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
			
			marker_position(map);
		}
		
		function map_initialize2(nLat, nLng) {

			var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
			mapOption = {
				center : new daum.maps.LatLng(nLat, nLng), // 지도의 중심좌표
				level : 4
			// 지도의 확대 레벨
			};

			var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

			marker_position(map);
		}

		function marker_position(map) {

			// 마커를 표시할 위치와 title 객체 배열입니다 
			var positions = [ {
				content : '<div>치과a</div>',
				latlng : new daum.maps.LatLng(36.800289, 127.074898)
			}, {
				content : '<div>치과b</div>',
				latlng : new daum.maps.LatLng(36.797481, 127.076933)
			}, {
				content : '<div>치과c</div>',
				latlng : new daum.maps.LatLng(36.800170, 127.072544)
			}, {
				content : '<div>치과d</div>',
				latlng : new daum.maps.LatLng(36.797387, 127.070624)
			}, {
				content : '<div>치과e</div>',
				latlng : new daum.maps.LatLng(37.539447, 127.003890)
			} ];

			// 마커 이미지의 이미지 주소입니다
			var imageSrc = "https://cdn1.iconfinder.com/data/icons/ecommerce-61/48/eccomerce_-_location-512.png";

			for (var i = 0; i < positions.length; i++) {

				// 마커 이미지의 이미지 크기 입니다
				var imageSize = new daum.maps.Size(30, 35);

				// 마커 이미지를 생성합니다    
				var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize);

				// 마커를 생성합니다
				var marker = new daum.maps.Marker({
					map : map, // 마커를 표시할 지도
					position : positions[i].latlng, // 마커를 표시할 위치
					title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
					image : markerImage,
					clickable : true
				// 마커 이미지 
				});

				// 마커에 표시할 인포윈도우를 생성합니다 
				var infowindow = new daum.maps.InfoWindow({
					content : positions[i].content
				// 인포윈도우에 표시할 내용
				});

				// 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
				// 이벤트 리스너로는 클로저를 만들어 등록합니다 
				// for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
				daum.maps.event.addListener(marker, 'mouseover',
						makeOverListener(map, marker, infowindow));
				daum.maps.event.addListener(marker, 'mouseout',
						makeOutListener(infowindow));
			}

			// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
			function makeOverListener(map, marker, infowindow) {
				return function() {
					infowindow.open(map, marker);
				};
			}

			// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
			function makeOutListener(infowindow) {
				return function() {
					infowindow.close();
				};
			}

			daum.maps.event.addListener(marker, 'click', function() {
				//선택한 병원 정보 저장
			});

		}


	</script>
	
	
	
	<script>
	
	 //주소-좌표 변환 객체를 생성
    var geocoder = new daum.maps.services.Geocoder();
	
	 function sample5_execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                var addr = data.address; // 최종 주소 변수

	                // 주소 정보를 해당 필드에 넣는다.
	                document.getElementById("sample5_address").value = addr;
	                // 주소로 상세 정보를 검색
	                geocoder.addressSearch(data.address, function(results, status) {
	                    // 정상적으로 검색이 완료됐으면
	                    if (status === daum.maps.services.Status.OK) {

	                        var result = results[0]; //첫번째 결과의 값을 활용
	                        
	                        map_initialize2(result.y, result.x);
	                });
	            }
	        }).open();
	    }
	</script>

</body>
</html>
