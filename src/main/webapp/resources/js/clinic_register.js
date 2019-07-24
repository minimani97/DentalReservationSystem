
function sample5_execDaumPostcode2() {
	 var geocoder = new daum.maps.services.Geocoder();
	 
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = data.address; // 최종 주소 변수
            
            
            // 주소 정보를 해당 필드에 넣는다.
            document.getElementById("c_address").value = addr;
            // 주소로 상세 정보를 검색
            geocoder.addressSearch(data.address, function(results, status) {
                // 정상적으로 검색이 완료됐으면
                if (status === daum.maps.services.Status.OK) {

                    var result = results[0]; //첫번째 결과의 값을 활용

                    // 해당 주소에 대한 좌표를 받아서
                    var coords = new daum.maps.LatLng(result.y, result.x);
                    
                    //controller에 y와 x좌표 넣기
                    $("#hide_addr_y").val(result.y);
                    $('#hide_addr_x').val(result.x);
                    $("#c_address").val(addr);
                }
            });
        }
    }).open();
}

function clinic_register(){
	
	
	var c_name = $("#c_name").val();
	var c_tel = $("#c_tel").val();
	var c_address = $("#c_address").val();
	var addr_y = $("#hide_addr_y").val();
	var addr_x = $("#hide_addr_x").val();
	var c_license = $("#c_license").val();
	console.log("c_address: "+c_address);
	
	var sendData = {c_name: c_name, c_tel: c_tel, addr_y: addr_y, addr_x: addr_x, c_license: c_license, c_address: c_address};
	
		$.ajax({
			url:'http://localhost:8090/reservation/registerInfo',
			method:'POST',
			data:JSON.stringify(sendData),
			processData:false,
			contentType:'application/json',
			success:function(){
				alert("병원 등록 신청이 완료되었습니다. 관리자의 승인 이후에 정식 등록됩니다.");
				location.href = "http://localhost:8090/reservation/";
			}
		});

	
}

