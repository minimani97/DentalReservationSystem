$(document).ready(function() {
	$(".wizard-item").click(function() {return false;});
	
	init();
	
	// 날짜 선택하면 그 날짜에 해당하는 스케줄표 띄우기
	$('#datepicker-out').on('changeDate', function(e) {
		
		var getDate = e.date;
		
		var date = moment(getDate, "YYYY-MM-DD").format("YYYY-MM-DD");
		$('#selectedDate').val(date);
		
		var selYear = date.split('-')[0];
		var selMonth = date.split('-')[1];
		var selDay = date.split('-')[2];
		
		var selDate = new Date();
		selDate.setFullYear(selYear, selMonth-1, selDay);
		var todayDate = new Date();
		console.log("선택날짜: " + selDate);
		console.log("오늘날짜: " + todayDate);
		
		var selDateToString = selYear+"-"+selMonth+"-"+selDay;
		var todayDateToString = moment(todayDate, "YYYY-MM-DD").format("YYYY-MM-DD");
		console.log("A: " + selDateToString);
		console.log("B: " + todayDateToString);
		
		var avaliable;
		if(todayDate > selDate) {
			avaliable = false;
			alert("예약은 오늘 날짜 기준으로 다음 날부터 가능합니다.");
		} 
		else if(selDateToString == todayDateToString) {
			avaliable = false;
			alert("당일 예약은 유선으로만 가능합니다.");
		} 
		else {
			avaliable = true;
		}
		
		var txt = "<div id='calendar' style='margin-left: 20px; margin-right: 20px'></div>";
		$('#schedule-area').html(txt);
		
		$('#calendar').fullCalendar({
			defaultView: 'agendaDay',
			header: {
				left: "",
				center: "title",
				right: ""
			},
			allDaySlot: false,
			slotDuration: "00:05",
			minTime: "09:00:00",
			maxTime: "21:00:00",
			eventClick: function(calEvent, jsEvent, view) {
				console.log('start time: ' + calEvent.start._i + ' / end time: ' + calEvent.end._i);
				
				if($(this).css('background-color') == "rgb(136, 173, 58)" || $(this).css('background-color') == "rgb(173, 58, 58)") {
					return false;
				} 
				else {
					$('.avaliable-time').css('background-color', "rgb(58, 135, 173)");
					$(this).css('background-color', "rgb(58, 59, 173)");
					
					var startTime = calEvent.start._i.split("T")[1];
					$('#selectedStartTime').val(startTime);
					var endTime = calEvent.end._i.split("T")[1];
					$('#selectedEndTime').val(endTime);
					
					$('#next-btn').attr('disabled', false);
				}
			}
		});
		
		$('#calendar').fullCalendar('gotoDate', date);
		
		var selectedField = $('#selectedField').val();
		
		loadSchedule(date, selectedField, avaliable);
	});
})

function getMapInfo() {

   $.ajax({
            url : 'http://localhost:8090/reservation/clinicselect',
            method : 'POST',
            processData : false,
            contentType : 'application/json',
            success : function(data) {

               var mapContainer = document.getElementById('map'), // 지도를 표시할 div
               mapOption = {
                  center : new daum.maps.LatLng(33.452470,126.571028), // 지도의 중심좌표
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

                  
                  // 마커가 표시될 위치
                  var markerPosition = new daum.maps.LatLng(addr_y,addr_x);
                  var iwContent = '<a style="text-align:center">'+c_name+'</a><br><input type="button" class="btn btn-primary" style="text-align:center" onclick=selectClinic("'+c_code+'") value="선택하기"/>';
                  var iwRemoveable = true;
                  

                  // 마커를 생성합니다
                  var marker = new daum.maps.Marker({
                     position : markerPosition
                  });

                  // 마커가 지도 위에 표시되도록 설정
                  marker.setMap(map);
                  
                  
                      // 마커를 생성합니다
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
                      daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
                      daum.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
                      
                      
                      
                   // 인포윈도우를 표시하는 클로저를 만드는 함수
                      function makeOverListener(map, marker, infowindow) {
                          return function() {
                              infowindow.open(map, marker);
                          };
                      }

                      // 인포윈도우를 닫는 클로저를 만드는 함수
                      function makeOutListener(infowindow) {
                          return function() {
                              infowindow.close();
                          };
                      }
               
                  }// for문 끝
                  
                  
               daum.maps.event.addListener(marker, 'click', function() {
                     // 마커 위에 인포윈도우를 표시
                     $('#selectedClinic').val(c_code);
               });

         
            }
         });

}


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

            console.log("y좌표: " + addr_y);
            console.log("x좌표: " + addr_x);

            // 마커가 표시될 위치
            var markerPosition = new daum.maps.LatLng(addr_y,addr_x);
            var iwContent = '<a style="text-align:center">'+c_name+'</a><br><input type="button" class="btn btn-primary" style="text-align:center" onclick=selectClinic("'+c_code+'") value="선택하기"/>';
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


function selectClinic(num){
   var hidNum = num;
    $("#selectedClinic").val(hidNum);
    $('#next-btn').attr('disabled', false);
}


function sample5_execDaumPostcode() {
   // 주소-좌표 변환 객체를 생성
   var geocoder = new daum.maps.services.Geocoder();

   new daum.Postcode({
      oncomplete : function(data) {
         var addr = data.address; // 최종 주소 변수

         // 주소 정보를 해당 필드에 넣음
         document.getElementById("sample5_address").value = addr;
         // 주소로 상세 정보를 검색
         geocoder.addressSearch(data.address, function(results,
               status) {
            // 정상적으로 검색이 완료됐으면
            if (status === daum.maps.services.Status.OK) {

               var result = results[0]; // 첫번째 결과의 값을 활용

               // 해당 주소에 대한 좌표를 받아서
               var coords = new daum.maps.LatLng(result.y,
                     result.x);

               // 지도 중심을 변경
               myLocation(result.y, result.x);

            }
         });
      }
   }).open();
}

// 선택한 치과의 진료 과목 불러오기
function loadFieldInfo() {	
	var selectedClinic = $('#selectedClinic').val();
	
	$('#fieldInfo').html("");
	
	var sendData = {selectedClinic: selectedClinic};
	
	$.ajax({
		url:'http://localhost:8090/reservation/loadFieldInfo',
		method:'POST',
		data:JSON.stringify(sendData),
		processData:false,
		contentType:'application/json',
		success: function(data){
			var myObj = data;
			var txt = "" ;
			var x;
			var cnt = 1;
			
			for(x in myObj) {
				txt += "<div class='col-md-6 col-lg-3'><!-- Simple card --><div id='field-card-"+cnt+"' class='field-card-out card d-block'><div id='field-card-in' class='card-body'>";
				txt += "<h4 id=field-name-"+cnt+" class='card-title'>"+myObj[x].fieldName+"</h4><br>";
				txt += "<a id=field-select-btn-"+cnt+" href='javascript: selectField("+cnt+");' class='btn btn-primary'>선택</a></div><!-- end card-body--></div><!-- end card--></div>";
				
				cnt++;
			}
			
			$("#fieldInfo").append(txt);
		},
		error: function(data){
			console.log("Field information load ERROR!");
		}
	});
}

// 진료과목 선택하기
function selectField(cnt) {	
	$('.field-card-out').removeAttr("style");
	$('#field-card-'+cnt).css("border", '2px solid #727CF5');
	
	$('#next-btn').attr('disabled', false);
	
	$('#calendar').html("");
	
	var id_value = "field-name-"+cnt;
	
	var selectField = $('#'+id_value).text();
	console.log("선택한 진료과목: " + selectField);
	
	// 진료 과목 코드 불러오기
	var sendData = {f_name: selectField}
	
	$.ajax({
		url:'http://localhost:8090/reservation/getFcode',
		method:'POST',
		data:JSON.stringify(sendData),
		processData:false,
		contentType:'application/json',
		success:function(data) {
			var f_code = data;
			
			$('#selectedField').val(f_code);
		},
		error:function(data) {
			console.log("Field Code load ERROR!");
		}
	});
}

// 선택한 진료 과목에 대한 의료진 정보 불러오기
function loadDentistInfo() {	
	$('#next-btn').attr('disabled', true);
	$('#dentistInfo').text("");
	
	var selectedClinic = $('#selectedClinic').val();
	var selectedField = $('#selectedField').val();
	
	console.log("selectedField: " + selectedField);
	
	var sendData = {selectedClinic: selectedClinic, selectedField: selectedField};
	
	$.ajax({
		url:'http://localhost:8090/reservation/loadDentistInfo',
		method:'POST',
		data:JSON.stringify(sendData),
		processData:false,
		contentType:'application/json',
		success: function(data){
			var myObj = data;
			var txt = "";
			var x;
			var cnt = 1;
			
			for(x in myObj) {
				var d_code = myObj[x].d_code;
				var d_name = myObj[x].d_name;
				var tel = myObj[x].d_tel;
				var email = myObj[x].email;
				var profile = myObj[x].profile;
								
				txt += "<div class='col-md-6 col-lg-3'>";
				txt += "<!-- Simple card --><div id='dentist-info-card-"+cnt+"' class='dentist-info-card card d-block'>";
				if(profile == null) {
					txt += "<img class='card-img-top' src='resources/image/no-profile.jpg' alt='Card image cap'>";
				} else {
					txt += "<img class='card-img-top' src='http://localhost:8090/reservation/resources/dentistProfile/"+profile+"' alt='Card image cap'>";
				}
				txt += "<div class='card-body'>";
				txt += "<h4 id=d-name-"+cnt+" class='text-align-center card-title'>"+d_name+"</h4>";
				txt += "<p class='card-text'>▪ Tel : "+tel+"<br>▪ Email : "+email+"</p>";
				txt += "<div class='select-btn-out'><a href='javascript: selectDentist("+cnt+");' id='d-select-btn-"+cnt+"' class='select-btn-in btn btn-primary'>선택</a>";
				txt += "</div></div><!-- end card-body --></div><!-- end card --></div><!-- end col -->";
				
				cnt++;
			}
			$('#dentistInfo').append(txt);
		},
		error: function(data){
			console.log("MediTeam information load ERROR!");
		}
	});
}

// 이전 페이지 이동 함수
function goToPreviousPage() {
	console.log("이전단계 버튼 클릭!");
	
}

// 다음페이지 이동 함수
function goToNextPage() {
	console.log("다음단계 버튼 클릭!");
	
	if($('#first').is(".active") === true) {
		$('#next-btn').attr('disabled', true);
		loadFieldInfo();
	}
	
	if($('#selectedField').val() == "") {
		if($('#second').is(".show") === true) {
			$('#next-btn').attr('disabled', true);
		}
	}
	else {
		if($('#second').is(".show") === true) {
			loadDentistInfo();
		}
	}
	
	if($('#third').is(".show") === true) {
		if ($('#selectedDate').val() == "" && $('#selectedStartTime').val() == "") {
			$('#next-btn').attr('disabled', true);
		}
	}
	
	if($('#fourth').is(".show") === true) {
		$('#next-btn').attr('disabled', true);
		getFamilyList();
	}
	
	if($('#fifth').is(".show") === true) {
		sendReservationInfo();
	}
	
	if($('#sixth').is(".show") === true) {
		saveReservationInfo();
		$('#previous-btn').hide();
		$('#next-btn').hide();
	}
}

// 의료진 선택하기
function selectDentist(cnt) {	
	$('.dentist-info-card').removeAttr("style");
	$('#dentist-info-card-'+cnt).css("border", '2px solid #727CF5');
	
	$('#next-btn').attr('disabled', false);
	
	var id_value = "d-name-"+cnt;
	
	var selectDentist = $('#' + id_value).text();
	
	// 의료진 코드 불러오기
	var sendData = {d_name : selectDentist};
	$.ajax({
		url:'http://localhost:8090/reservation/getDcode',
		method:'POST',
		data:JSON.stringify(sendData),
		processData:false,
		contentType:'application/json',
		success:function(data) {
			var d_code = data;
			
			$('#selectedDentist').val(d_code);
		},
		error:function(data) {
			console.log("Dentist Code load ERROR!");
		}
	});
}

// 해당 날짜의 스케쥴 불러오기
function loadSchedule(date, selectedField, avaliable) {
	console.log("Date is " + date);
	
	if(avaliable == false) {
		var lunchTime = {title: "예약불가", start: date+"T09:00:00", end: date+"T21:00:00", color: "#ad3a3a"};
		$('#calendar').fullCalendar('renderEvent', lunchTime);
		
		return;
	}
	
	var lunchTime = {title: "점심시간", start: date+"T13:00:00", end: date+"T14:00:00", color: "#88ad3a"};
	$('#calendar').fullCalendar('renderEvent', lunchTime);
	
	var d_code = $('#selectedDentist').val();
	
	// DB에서 이미 예약된 시간 불러오기
	var sendData = {date: date, d_code};
	$.ajax({
		url:'http://localhost:8090/reservation/getAlreadyReservedTime',
		method:'POST',
		data:JSON.stringify(sendData),
		dataType:'JSON',
		processData:false,
		contentType:'application/json',
		success:function(data) {
			var myObj = data;
			var x;
			
			var cnt = 1;
			
			// 스케일링 선택 시
			if(selectedField == 11) {
				
				for(var hour=9; hour<21; hour++) {
					for(var min=0; min<60; min=min+10) {
						
						if(hour == 13) continue;
						
						var tmpTime = "";
						
						if(hour == 9) {
							if(min == 0) tmpTime = "0"+hour+":0"+min;
							else tmpTime = "0"+hour+":"+min;
						}
						else {
							if(min == 0) tmpTime = hour+":0"+min;
							else tmpTime = hour+":"+min;
						}
						
						tmpTime = tmpTime + ":00";
						
						if(min == 50) {
							var end_time = String(Number(tmpTime.split(":")[0])+1) + ":00:00";
							
							var event = {title: "예약가능", start: date+"T"+tmpTime, end: date+"T"+end_time, className: "avaliable-time"};
							$('#calendar').fullCalendar('renderEvent', event);
						}
						else {
							var end_time = tmpTime.split(":")[0] + ":" + String(Number(tmpTime.split(":")[1])+10) + ":00";
							
							var event = {title: "예약가능", start: date+"T"+tmpTime, end: date+"T"+end_time, className: "avaliable-time"};
							$('#calendar').fullCalendar('renderEvent', event);
						}
						
					}
				}
				
			}
			// 스케일링을 제외한 나머지 진료과목 선택 시
			else {
				for(var hour=9; hour<21; hour++) {
					for(var min=0; min<60; min=min+10) {
						
						if(hour == 13) continue;
						
						var tmpTime = "";
						
						if(hour == 9) {
							if(min == 0) tmpTime = "0"+hour+":0"+min;
							else tmpTime = "0"+hour+":"+min;
						}
						else {
							if(min == 0) tmpTime = hour+":0"+min;
							else tmpTime = hour+":"+min;
						}
						
						tmpTime = tmpTime + ":00";
						
						if(min == 0) {
							var end_time = tmpTime.split(":")[0] + ":30:00";
							
							var event = {title: "예약가능", start: date+"T"+tmpTime, end: date+"T"+end_time, className: "avaliable-time"};
							$('#calendar').fullCalendar('renderEvent', event);
						}
						else if(min == 30) {
							var end_time = String(Number(tmpTime.split(":")[0])+1) + ":00:00";
							
							var event = {title: "예약가능", start: date+"T"+tmpTime, end: date+"T"+end_time, className: "avaliable-time"};
							$('#calendar').fullCalendar('renderEvent', event);
						}
						else {}
						
					}
				}
			}
			
			// 모조리 띄워놓은 일정 중 이미 예약된 시간은 예약불가로 바꿔 띄우기
			
			// 해당 일에 예약이 아무것도 없을 경우
			if(data == "") { return false; }
			// 해당 일에 예약이 하나라도 존재할 경우
			else {
				
				// DB에서 예약 시간 모두 불러오기
				for(x in myObj) {
					var s_time = myObj[x].startTime + ":00";
					var e_time = myObj[x].endTime + ":00";
					
					var events = $('#calendar').fullCalendar('clientEvents');
					var length = events.length;
					console.log($('#calendar').fullCalendar('clientEvents'));
					
					for(var i=0; i<length; i++) {
						var start = events[i].start._i.split("T")[1];
						var end = events[i].end._i.split("T")[1];
						
						if(s_time == start && e_time == end) {
							
							$('#calendar').fullCalendar('removeEvents', events[i]._id);
							console.log("EVENTS[i] : " + events[i]._id);
							
							var newEvent = {title: "예약불가", start: date+"T"+start, end: date+"T"+end, color: "#ad3a3a"};
							$('#calendar').fullCalendar('renderEvent', newEvent);

							console.log("^!^start : " + events[i].start._i.split("T")[1]);
							console.log("^!^end : " + events[i].end._i.split("T")[1]);
							
						}
					}
				}
			}
			
		},
		error:function(data) {
			console.log("Already Reserved Time Info load ERROR!");
		}
	});
}

function checkWriteAllInfo() {
	var name = $('#name').val();
	var birth = $('#birth').val();
	var tel = $('#phone_num').val();
	var addr = $('#address').val();
	
	if(name != "" && birth != "" && tel != "" && addr != "") {
		$('#next-btn').attr('disabled', false);
	} else {
		$('#next-btn').attr('disabled', true);
	}
}

function sendReservationInfo() {
	var selectedClinic = $('#selectedClinic').val();
	var selectedField = $('#selectedField').val();
	var selectedDentist = $('#selectedDentist').val();
	var selectedDate = $('#selectedDate').val();
	var selectedStartTime = $('#selectedStartTime').val();
	var selectedEndTime = $('#selectedEndTime').val();
	
	var name = $('#name').val();
	var birth = $('#birth').val();
	var tel = $('#phone_num').val();
	var addr = $('#address').val();
	var extra_info = $('#additional-info').val();
	var personal_info_concent = "";
	
	if($('#customCheck1').prop("checked")) {
		personal_info_concent = "Y";
		$('#check_info_concent').val("동의");
	} else {
		personal_info_concent = "N";
		$('#check_info_concent').val("비동의");
	}
	
	var selectedDateTime = selectedDate + "   " + selectedStartTime.split(":")[0] + ":" + selectedStartTime.split(":")[1] + " ~ " + selectedEndTime.split(":")[0] + ":" + selectedEndTime.split(":")[1];
	
	//$('#check_clinic').val(selectedClinic);
	//$('#check_field').val(selectedField);
	//$('#check_dentist').val(selectedDentist);
	$('#check_dateTime').val(selectedDateTime);
	
	$('#check_name').val(name);
	$('#check_birth').val(birth);
	$('#check_phoneNum').val(tel);
	$('#check_address').val(addr);
	$('#check_additionalInfo').val(extra_info);
	
	// DB에서 치과/진료과목/의료진 코드로 해당 치과명/의료진명/진료과목명 불러오기
	var sendData = {
			c_code : selectedClinic,
			f_code : selectedField,
			d_code : selectedDentist	
	};
	$.ajax({
		url:'http://localhost:8090/reservation/getNames',
		method:'POST',
		data:JSON.stringify(sendData),
		processData:false,
		contentType:'application/json',
		success:function(data) {
			var myObj = data;
			var x;
			
			for(x in myObj) {
				$('#check_clinic').val(myObj[x].c_name);
				$('#check_field').val(myObj[x].f_name);
				$('#check_dentist').val(myObj[x].d_name);
			}
		},
		error:function(data) {
			console.log("Reservation Info send ERROR!");
		}
	});
}

// 예약 정보 디비에 저장하기
function saveReservationInfo() {
	console.log("저장한다~디비에~");
	
	var personal_info;
	if($('#check_info_concent').val() == "동의") {
		personal_info = "Y";
	} else {
		personal_info = "N";
	}
	
	var s_time = $('#selectedStartTime').val().split(":")[0] + ":" + $('#selectedStartTime').val().split(":")[1];
	var e_time = $('#selectedEndTime').val().split(":")[0] + ":" + $('#selectedEndTime').val().split(":")[1];
	
	var sendData = {
		p_code: $('#selectedPatient').val(),
		p_name: $('#name').val(),
		date: $('#selectedDate').val(),
		s_time: s_time,
		e_time: e_time,
		r_field: $('#selectedField').val(),
		c_code: $('#selectedClinic').val(),
		d_code: $('#selectedDentist').val(),
		extra_info: $('#check_additionalInfo').val(),
		personal_info: personal_info
	};
	
	$.ajax({
		url:'http://localhost:8090/reservation/saveReservationInfo',
		method:'POST',
		data:JSON.stringify(sendData),
		processData:false,
		contentType:'application/json',
		success:function(data) {
			console.log("DB 저장 성공vV");
		},
		error:function(data) {
			console.log("Get Family Info ERROR!");
		}
	});
	
}

// 로그인한 회원의 가족 이름 가져오기
function getFamilyList() {
	var user_code = $('#user_code').val();
	
	var sendData = {user_code: user_code};
	$.ajax({
		url:'http://localhost:8090/reservation/getFamilyList',
		method:'POST',
		data:JSON.stringify(sendData),
		processData:false,
		contentType:'application/json',
		success:function(data) {
			var myObj = data;
			var x;
			var txt = "";
			
			for(x in myObj) {
				
				txt += "<option value='"+myObj[x].p_code+"'>"+myObj[x].name+"</option>";
				
			}
			
			$('#family-list').append(txt);
		},
		error:function(data) {
			console.log("Get Family Info ERROR!");
		}
	})
}

// 해당 환자 정보 가져오기
function getPatientInfo(p_code) {
	
	$('#next-btn').attr('disabled', false);
	
	var sendData = {p_code: p_code};
	$.ajax({
		url:'http://localhost:8090/reservation/getPatientInfo',
		method:'POST',
		data:JSON.stringify(sendData),
		processData:false,
		contentType:'application/json',
		success:function(data) {
			var myObj = data;
			var x;
			
			for(x in myObj) {
				
				$('#name').val(myObj[x].name);
				$('#birth').val(myObj[x].birth);
				$('#phone_num').val(myObj[x].tel);
				$('#address').val(myObj[x].addr);
				
				$('#selectedPatient').val(myObj[x].p_code);
			}
			
		},
		error:function(data) {
			console.log("Get Patient Info ERROR!");
		}
	});
}