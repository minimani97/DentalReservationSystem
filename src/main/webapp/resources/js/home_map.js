$(document).ready(function() {

	init();
	
})


function init() {
   if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(showPosition);
   } else {
      alert("Geolocation is not supported by this brower.");
   }
}

var nLat;
var nLng;

// 위치 표시
function showPosition(position) {
   nLat = position.coords.latitude;
   nLng = position.coords.longitude;
   console.log("nLag : " + nLat);
   console.log("nLng : " + nLng);

   myLocation(nLat, nLng);

}




function myLocation(nLat, nLng) {

   $.ajax({
      url : 'http://localhost:8090/reservation/clinicselect2',
      method : 'POST',
      processData : false,
      contentType : 'application/json',
      success : function(data) {

         var mapContainer = document.getElementById('map'), // 지도를 표시할 div
         mapOption = {
            center : new daum.maps.LatLng(nLat, nLng), // 지도의 중심좌표
            level : 3
         // 지도의 확대 레벨
         };
         
         var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성
         var myObj = data;
         var x;
         for (x in myObj) {

            var c_code = myObj[x].c_code;
            var c_name = myObj[x].c_name;
            var addr_y = myObj[x].c_y;
            var addr_x = myObj[x].c_x;
            var c_tel = myObj[x].c_tel;

            console.log("y좌표: " + addr_y);
            console.log("x좌표: " + addr_x);

            // 마커가 표시될 위치
            var markerPosition = new daum.maps.LatLng(addr_y,addr_x);
            var iwContent = c_name+"<br>"+c_tel;
            var iwRemoveable = true;
            console.log("마커위치: " + markerPosition);

            // 마커를 생성
            var marker = new daum.maps.Marker({
               position : markerPosition
            });

            // 마커가 지도 위에 표시되도록 설정
            marker.setMap(map);
            
            
             // 마커를 생성
             var marker = new daum.maps.Marker({
                 map: map, // 마커를 표시할 지도
                 position: markerPosition // 마커의 위치
             });

             // 마커에 표시할 인포윈도우를 생성
             var infowindow = new daum.maps.InfoWindow({
                 content: iwContent, // 인포윈도우에 표시할 내용
                 removable : iwRemoveable
             });

             // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록
             // 이벤트 리스너로는 클로저를 만들어 등록
             // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록
             daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
                          
             
          // 인포윈도우를 표시하는 클로저를 만드는 함수
             function makeOverListener(map, marker, infowindow) {
                 return function() {
                     infowindow.open(map, marker);
                 };
             }
             
         }
      }
   });
}




