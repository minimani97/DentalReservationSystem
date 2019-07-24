$(document).ready(function() {
	//One_Patient_List();
	makeOption();
	
	// 날짜 선택하면 그 날짜에 해당하는 스케줄표 띄우기
	$('#datepicker-out').on('changeDate', function(e) {
		
		var getDate = e.date;
		
		var date = moment(getDate, "YYYY-MM-DD").format("YYYY-MM-DD");
		$('#selectedDate').val(date);
		
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
		
		//var selectedField = $('#selectedField').val();
		var selectedField = $("#FieldSelect").val();
		
		console.log("selectedField ㅇㅅㅇ? : " + selectedField);
		
		loadSchedule(date, selectedField);
	});
});

//해당 날짜의 스케쥴 불러오기
function loadSchedule(date, selectedField) {
	console.log("Date is " + date);
	
	var lunchTime = {title: "점심시간", start: date+"T13:00:00", end: date+"T14:00:00", color: "#88ad3a"};
	$('#calendar').fullCalendar('renderEvent', lunchTime);

	var d_code = $('#DentistSelect').val().split("/")[1];
	
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

//휴진 등록 버튼(close_notice.jsp)
function closeButton(/*c_code*/){
	console.log("closeButton function called-");
	
	var closeDate = $("#closeDate").val();
	var day = closeDate.split("/")[1];
	var month = closeDate.split("/")[0];
	var year = closeDate.split("/")[2];
	closeDate = year+":"+month+":"+day;
	
	var start_time = $("#start_time").val();
	var end_time = $("#end_time").val();
	
	var reason = $("#reason").val();
	var c_code = $("#c_code").val();
	var d_code = $("#d_code").val();
	
	var sendData = { closeDate : closeDate,
					 start_time : start_time,
					 end_time : end_time,
					 reason : reason,
					 c_code : c_code,	
					 d_code : d_code 		 };
	
	$.ajax({
	       url : 'http://localhost:8090/reservation/closeButton',
	       method : 'POST',
	       data : JSON.stringify(sendData),
	       processData : false,
	       contentType : 'application/json',
	       success : function(data) {
	    	  
	    	   alert("휴진 시간이 등록되었습니다.");
	    	   location.reload();
	       },
	       error : function(data, status, err) {
	          //alert('error');
	          console.log("error: " + data);
	       }
	    });
}

//선택한 진료에 맞는 info가져오기_readonly(patient_timeAlter.jsp)
function getReservationInfo(/*c_code*/){
	console.log("getReservationInfo function called-");
	
	var ReservationSelect = $("#ReservationSelect").val();
	var p_name = ReservationSelect.split("/")[0];
	var num = ReservationSelect.split("/")[1];
	var c_code = $("#c_code").val();
	var sendData = { p_name : p_name,
					 c_code : c_code,
					 num : num 			};
	
	$.ajax({
	       url : 'http://localhost:8090/reservation/getReservationInfo',
	       method : 'POST',
	       data : JSON.stringify(sendData),
	       processData : false,
	       contentType : 'application/json',
	       success : function(data) {
	    	  
	    	   $("#validationTooltip01").val(data[0].p_name);
	    	   $("#validationTooltip02").val(data[0].birth);
	    	   $("#validationTooltip03").val(data[0].p_tel);
	    	   $("#validationTooltip04").val(data[0].f_name);
	    	   $("#validationTooltip05").val(data[0].extra_info);
	    	   $("#validationTooltip06").val(data[0].content);
	       },
	       error : function(data, status, err) {
	          //alert('error');
	          console.log("error: " + data);
	       }
	    });
}


//트랜스퍼할 진료 select 태그의 option 만들기(patient_transfer.jsp)
function makeReservationOption(/*c_code*/){
	console.log("makeReservationOption function called-");
	
	var c_code = $("#c_code").val();
	var d_code = $("#d_code").val();
	var selected_Patient = $("#selected_Patient").val();
	console.log("환자 선택된 환쟈ㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑ!!!!!!!!!!!: " + selected_Patient);
	
	
	var sendData = { c_code : c_code,
					 d_code : d_code,
					 selected_Patient : selected_Patient  };

	$.ajax({
	       url : 'http://localhost:8090/reservation/makeReservationOption',
	       method : 'POST',
	       data : JSON.stringify(sendData),
	       processData : false,
	       contentType : 'application/json',
	       success : function(data) {
	    	   
	    	   var txt ="";
	    	   $("#ReservationSelect").html("");
	    	   txt+="<option data-select2-id='3' id='123' >Select</option>";
	    	   
	    	   var x;
	    	   for(x in data){
	    		   
	    		   txt+="<option value='"+data[x].p_name+"/"+data[x].num+"'>"+data[x].p_name+"("+data[x].date+"/  "+data[x].p_tel+")"+"</option>";
	    		   
	    	   }
	    	   $("#ReservationSelect").append(txt);

	       },
	       error : function(data, status, err) {
	          //alert('error');
	          console.log("error: " + data);
	       }
	    });
}


//받은 트랜스퍼
function receiveTransfer(/*c_code*/){
	console.log("receiveTransfer function called-");
	
	var c_code = $("#c_code").val();
	var d_code = $("#d_code").val();
	var sendData = { c_code : c_code,
					 d_code : d_code };

	$.ajax({
	       url : 'http://localhost:8090/reservation/receiveTransfer',
	       method : 'POST',
	       data : JSON.stringify(sendData),
	       processData : false,
	       contentType : 'application/json',
	       success : function(data) {
	    	   
	    	   var x;
	    	   var txt ="";
	    	   for(x in data){
	    		   
	    		    txt+="<tr>";
	    		    txt+="<td><button type='button' class='btn btn-primary btn-rounded' onclick='javascript:goPatient_record("+data[x].p_code+");'>"+data[x].p_name+"</button></td>";
	    			txt+="<td>"+data[x].birth+"</td>";
	    			txt+="<td>"+data[x].p_tel+"</td>";
	    			txt+="</tr>";
                   
                   /*console.log("success data: " + JSON.stringify(data));*/
   				   console.log("성공");
                          
	    	   }
	    	   
	    	   $("#transferList").html(txt);
	    	   
	    	   
	       },
	       error : function(data, status, err) {
	          //alert('error');
	          console.log("error: " + data);
	       }
	    });
}



//환자 트랜스퍼 전송 버튼(patient_record.jsp)
function transferSendButton(/*c_code, p_code*/){
	console.log("transferSendButton function called-");
	
	var sendClinic = $("#c_code").val();
	var sendDentist = $("#d_code").val();
	
	var receiveClinic = $("#ClinicSelect").val().split("/")[1];
	var transfer_r_field = $("#FieldSelect").val();
	var receiveDentist = $("#DentistSelect").val().split("/")[1];
	var p_code = $("#PatientSelect").val().split("/")[1];
	
	var selectedStartTime = $("#selectedStartTime").val();
	var s_hour = selectedStartTime.split(":")[0];
	var s_minute = selectedStartTime.split(":")[1];
	selectedStartTime = s_hour +":"+ s_minute;
	
	var selectedEndTime = $("#selectedEndTime").val();
	var e_hour = selectedEndTime.split(":")[0];
	var e_minute = selectedEndTime.split(":")[1];
	selectedEndTime = e_hour +":"+ e_minute;
	
	var ReservationSelect = $("#ReservationSelect").val().split("/")[1];
	var selectedDate = $("#selectedDate").val();
	
	console.log("receiveClinic: "+ receiveClinic);
	//고칠 진료 num 받아와야해
	var sendData = { sendClinic : sendClinic,
					 sendDentist : sendDentist,
					 receiveClinic : receiveClinic,
					 transfer_r_field : transfer_r_field,
					 receiveDentist : receiveDentist,
					 p_code : p_code,
					 selectedStartTime : selectedStartTime,
					 selectedEndTime : selectedEndTime ,
					 ReservationSelect : ReservationSelect,
					 selectedDate : selectedDate			};

	$.ajax({
	       url : 'http://localhost:8090/reservation/transferSendButton',
	       method : 'POST',
	       data : JSON.stringify(sendData),
	       processData : false,
	       contentType : 'application/json',
	       success : function(data) {
	    	   
	    	  alert("'"+$("#ClinicSelect").val().split("/")[0] + "'의 '" + $("#DentistSelect").val().split("/")[0] +"'님께 환자 진료내역을 전송하였습니다.");
	    	  location.reload(); 
	       },
	       error : function(data, status, err) {
	          //alert('error');
	          console.log("error: " + data);
	       }
	    });
}

//선택한 환자에 맞는 info가져오기_readonly(patient_transfer.jsp)
function getPatientInformation(/*c_code*/){
	console.log("getPatientInformation function called-");
	
	/*var PatientSelect = $("#PatientSelect").val().split("/")[1];
	console.log("오잉: " + PatientSelect);*/
	$("#selected_Patient").val($("#PatientSelect").val().split("/")[1]);
	var selected_Patient = $("#PatientSelect").val().split("/")[1];
	console.log("selected_Patient: " + selected_Patient);
	
	
	var selectPatient = $("#PatientSelect").val();
	var p_name = selectPatient.split("/")[0];
	var p_code = selectPatient.split("/")[1];
	var c_code = $("#c_code").val();
	var sendData = { p_name : p_name,
					 c_code : c_code,
					 p_code : p_code };

	$.ajax({
	       url : 'http://localhost:8090/reservation/getPatientInformation',
	       method : 'POST',
	       data : JSON.stringify(sendData),
	       processData : false,
	       contentType : 'application/json',
	       success : function(data) {
	    	   
	    	   //console.log(data);
	    	   console.log("data: " + data[0].p_name+","+data[0].birth+","+data[0].p_tel+","+data[0].f_name+","+data[0].extra_info+","+data[0].content);
	    	   
	    	   $("#validationTooltip01").val(data[0].p_name);
	    	   $("#validationTooltip02").val(data[0].birth);
	    	   $("#validationTooltip03").val(data[0].p_tel);
	    	   $("#validationTooltip04").val(data[0].f_name);
	    	   $("#validationTooltip05").val(data[0].extra_info);
	    	   $("#validationTooltip06").val(data[0].content);
	       },
	       error : function(data, status, err) {
	          //alert('error');
	          console.log("error: " + data);
	       }
	    });
}

//트랜스퍼할 환자 select 태그의 option 만들기(patient_transfer.jsp)
function makePatientOption(){
	console.log("makePatientOption function called-");
	
	var c_code = $("#c_code").val();
	var sendData = { c_code : c_code };

	$.ajax({
	       url : 'http://localhost:8090/reservation/makePatientOption',
	       method : 'POST',
	       data : JSON.stringify(sendData),
	       processData : false,
	       contentType : 'application/json',
	       success : function(data) {
	    	   
	    	   var x;
	    	   var txt ="";
	    	   for(x in data){
	    		   
	    		   txt+="<option value='"+data[x].p_name+"/"+data[x].p_code+"'>"+data[x].p_name+"("+data[x].id+")"+"</option>";
	    		   
	    	   }
	    	   $("#PatientSelect").append(txt);

	       },
	       error : function(data, status, err) {
	          //alert('error');
	          console.log("error: " + data);
	       }
	    });
}

//선택한 병원과 진료과에 맞는 의료진 select-option 띄우기(patient_transfer.jsp)
function makeDentistOption(){
	//location.reload();
	console.log("makeDentistOption function called-");

	var selectClinic = $("#ClinicSelect").val().split("/")[0];
	var selectField = $("#FieldSelect").val(); 
	
	var sendData = { selectClinic: selectClinic,
					 selectField : selectField };
	
	$.ajax({
	       url : 'http://localhost:8090/reservation/makeDentistOption',
	       method : 'POST',
	       data : JSON.stringify(sendData),
	       processData : false,
	       contentType : 'application/json',
	       success : function(data) {
	    	   
	    	   $("#DentistSelect").html("");
	    	   var x;
	    	   var txt ="";
	    	   txt+="<option data-select2-id='3' id='123' >Select</option>";
	    	   for(x in data){
	    		   
	    		   txt+="<option value='"+data[x].d_name+"/"+data[x].d_code+"'>"+data[x].d_name+"</option>";
	    		   console.log("의료진 코드당: " + data[x].d_name+"/"+data[x].d_code);

	    	   }
	    	   $("#DentistSelect").append(txt);

	       },
	       error : function(data, status, err) {
	          //alert('error');
	          console.log("error: " + data);
	       }
	    });
}

//DB에서 치과 select태그의 option 만들기(patient_transfer.jsp)
function makeOption(){
	console.log("makeOption function called-");

	$.ajax({
	       url : 'http://localhost:8090/reservation/makeOption',
	       method : 'POST',
	       processData : false,
	       contentType : 'application/json',
	       success : function(data) {

	    	   var x;
	    	   var txt ="";
	    	   for(x in data){
	    		   
	    		   txt+="<option value='"+data[x].c_name+"/"+data[x].c_code+"'>"+data[x].c_name+"</option>";

	    	   }
	    	   $("#ClinicSelect").append(txt);

	       },
	       error : function(data, status, err) {
	          //alert('error');
	          console.log("error: " + data);
	       }
	    });
}

//개인 약 추가(patient_record.jsp)
function addMedicine(/*c_code*/num){
	console.log("addMedicine function called-");
	if($("#m_code").val() != "" && $("#m_name").val() != "" && $("#dose").val() != "" &&
			$("#frequency_day").val() != "" && $("#day").val() != ""){
		console.log("안들어왔잖아 ㅠㅠ");
		var c_code = $("#c_code").val();
		var p_num = num;
		var m_code = $("#m_code").val();
		var m_name = $("#m_name").val()
		var dose = $("#dose").val();
		var frequency_day = $("#frequency_day").val();
		var day = $("#day").val();
		var sendData = {
				c_code : c_code,
				p_num : p_num,
				m_code : m_code,
				m_name : m_name,
				dose : dose,
				frequency_day : frequency_day,
				day : day
										};
		console.log("흐음...: " + p_num+","+m_code+","+m_name+","+dose+","+frequency_day+","+day);
		$.ajax({
		       url : 'http://localhost:8090/reservation/addMedicine',
		       method : 'POST',
		       data : JSON.stringify(sendData),
		       processData : false,
		       contentType : 'application/json',
		       success : function(data) {
		    	   /*
		    	   alert("진단 내용이 수정되었습니다.");*/
		    	   location.reload();
		    	   
		       },
		       error : function(data, status, err) {
		          console.log("error: " + data);
		       }
		    });
		
	};
}


//개인 진료 content 수정(patient_record.jsp)
function AlterContent(num){
	console.log("AlterContent function called-");
	
	var c_code = $("#c_code").val();
	var p_num = num;
	var getContent = $("#con").val();
	getContent = getContent.replace(/(?:\r\n|\r|\n)/g, '<br>');

	var sendData = {
			c_code : c_code,
			p_num : p_num,
			getContent : getContent
									};
	
	console.log("호잉?: " + p_num+","+getContent);
	
	$.ajax({
	       url : 'http://localhost:8090/reservation/AlterContent',
	       method : 'POST',
	       data : JSON.stringify(sendData),
	       processData : false,
	       contentType : 'application/json',
	       success : function(data) {
	    	   
	    	   alert("진단 내용이 수정되었습니다.");
	    	   location.reload();
	    	   
	       },
	       error : function(data, status, err) {
	          //alert('error');
	          console.log("error: " + data);
	       }
	    });
}

function goPatient_record(p_code){
	
	window.location.href="http://localhost:8090/reservation/patient_record?" + p_code;
	
}

function DBgetContent(num, date, p_name, content){
	
	console.log("DBgetContent function called-");
	
	console.log("String 값: " + num+ ","+date+","+p_name+","+content);

	$("#DBcontentBox").html("");
	
	var newContent = content.replace(/<br>/g, '\r\n');
	   
	var Boxtext ="";
	
	   		Boxtext+="<h6>"+p_name+"님의 "+date+" 진료</h6>";
	   		Boxtext+="<hr>";
	   		Boxtext+="<textarea rows='6' cols='62' id='con'>"+newContent+"</textarea>";
	   		Boxtext+="<hr>";
	   		Boxtext+="<table style='width:100% ' id = 'hoho'>";
	   		Boxtext+="<tr>";
	   		Boxtext+="<th>코드</th>";
	   		Boxtext+="<th>약품명</th> ";
	   		Boxtext+="<th>투약량</th>";
	   		Boxtext+="<th>횟수</th>";
	   		Boxtext+="<th>일수</th>";
	   		Boxtext+="</tr>";
	   		Boxtext+="<tr>";
	   		Boxtext+="<td><input type='text' id='m_code' class='form-control' maxlength='25' data-toggle='maxlength' required></td>";
	   		Boxtext+="<td><input type='text' id='m_name' class='form-control' maxlength='25' data-toggle='maxlength' required></td>";
	   		Boxtext+="<td><input type='text' id='dose' class='form-control' maxlength='25' data-toggle='maxlength' required></td>";
		    Boxtext+="<td><input type='text' id='frequency_day' class='form-control' maxlength='25' data-toggle='maxlength' required></td>";
		    Boxtext+="<td><input type='text' id='day' class='form-control' maxlength='25' data-toggle='maxlength' required></td>";
		    Boxtext+="</tr>";
		    
		    $(document).ready(function getPriscription(){
		    	console.log("getPriscriptiont function called-");

		    	var sendData = { num : num }; 
		    	
		    	$.ajax({
		    	       url : 'http://localhost:8090/reservation/getPriscription',
		    	       method : 'POST',
		    	       data : JSON.stringify(sendData),
		    	       processData : false,
		    	       contentType : 'application/json',
		    	       success : function(data) {
		    	    	   
		    	    	   var x;
		    	    	   var a="";
		    	    	   for(x in data){
		    	    		   
		    	    		   a+="<tr>";
				    		   a+="<td>"+data[x].m_code+"</td>";
				    		   a+="<td>"+data[x].m_name+"</td>";
				    		   a+="<td>"+data[x].dose+"</td>";
				    		   a+="<td>"+data[x].frequency_day+"</td>";
				    		   a+="<td>"+data[x].day+"</td>";
				    		   a+="</tr>";
		                              
		    	    	   }
		    	    	   $("#hoho").append(a);

		    	       },
		    	       error : function(data, status, err) {
		    	          //alert('error');
		    	          console.log("error: " + data);
		    	       }
		    	    });
		    });
		    Boxtext+="</table>";
           
	   $("#DBcontentBox").html(Boxtext);
	   
	 $("#modalButton").html("");
	 var alter = "";
	 		alter+="<button type='button' class='btn btn-light' data-dismiss='modal'>닫기</button>";
	   		alter+="<button type='button' class='btn btn-primary' onclick='javascript:AlterContent("+num+"),addMedicine("+num+")'>수정</button>";
	
	 $("#modalButton").append(alter);
}

//환자 개인의 진료 내역 리스트 받기(patient_record.jsp)
function One_Patient_List(){
	console.log("One_Patient_List function called-");

	var thisPageURL = document.location.href;
	var p_code = thisPageURL.split("?")[1];
	
	var c_code = $("#c_code").val();
	var sendData = {
			c_code : c_code,
			p_code : p_code
							};
	
	$.ajax({
	       url : 'http://localhost:8090/reservation/One_Patient_List',
	       method : 'POST',
	       data : JSON.stringify(sendData),
	       processData : false,
	       contentType : 'application/json',
	       success : function(data) {
	    	   
	    	   var x;
	    	   var txt ="";
	    	   for(x in data){
	    		   
	    		    txt+="<tr>";
	    		    txt+="<td>"+data[x].p_name+"</td>";
	    		    txt+="<td>"+data[x].date+"</td>";
	    			txt+="<td>"+data[x].f_name+"</td>";
	    			txt+="<td>"+data[x].d_name+"</td>";
	    			txt+="<td>"+data[x].extra_info+"</td>";
	    			txt+="<td>"+data[x].personal_info+"</td>";
	    			txt+="<td><div class='button-list'><button type='button' class='btn btn-primary'" +  //("+data[x].num+","+data[x].date+","+data[x].p_name+",'"+data[x].content+"',"+data[x].m_code+",'"+m_name+"','"+dose+"',"+fre+","+day+")
					" onclick='javascript:DBgetContent(\""+data[x].num+"\",\""+data[x].date+"\",\""+data[x].p_name+"\",\""+data[x].content+"\")' data-toggle='modal' data-target='#myModal' >진단 내용</button></div></td>";
	    			txt+="</tr>";

   				   console.log("성공");  
	    	   }
	    	   $("#onePatientList").append(txt);

	       },
	       error : function(data, status, err) {
	          //alert('error');
	          console.log("error: " + data);
	       }
	    });
}

//당일날짜 환자 받기(patient_list.jps)
function Today_PList(){
	console.log("Today_PList function called-");
	
	var c_code = $("#c_code").val();
	var d_code = $("#d_code").val();
	var sendData = {
			c_code : c_code,
			d_code : d_code,
							};
	
	$.ajax({
	       url : 'http://localhost:8090/reservation/Today_PList',
	       method : 'POST',
	       data : JSON.stringify(sendData),
	       processData : false,
	       contentType : 'application/json',
	       success : function(data) {
	    	   
	    	   var x;
	    	   var txt ="";
	    	   for(x in data){
	    		   
	    		    txt+="<tr>";
	    		    txt+="<td><button type='button' class='btn btn-primary btn-rounded' onclick='javascript:goPatient_record("+data[x].p_code+");'>"+data[x].p_name+"</button></td>";
	    		    txt+="<td>"+data[x].id+"</td>";
	    		    txt+="<td>"+data[x].birth+"</td>";
	    			txt+="<td>"+data[x].p_tel+"</td>";
	    			/*txt+="<td>"+data[x].f_name+"</td>";
	    			txt+="<td>"+data[x].extra_info+"</td>";
	    			txt+="<td>"+data[x].personal_info+"</td>";*/
	    			txt+="</tr>";
                   
                   /*console.log("success data: " + JSON.stringify(data));*/
   				   console.log("성공");
                          
	    	   }
	    	   
	    	   $("#todayList").html(txt);

	       },
	       error : function(data, status, err) {
	          //alert('error');
	          console.log("error: " + data);
	       }
	    });
}

//진료과 목록 가져오기(dentist_list.jsp)
function d_field_List(d_code){
	console.log("d_field_List function called-");

	var id = "#d_fieldList" + d_code;
	var c_code = $("#c_code").val();
	var sendData = {
			c_code : c_code,
			d_code : d_code,
			};
	
	$.ajax({
	       url : 'http://localhost:8090/reservation/d_field_List',
	       method : 'POST',
	       data : JSON.stringify(sendData),
	       processData : false,
	       contentType : 'application/json',
	       success : function(data) {
	    	   
	    	   var x;
	    	   var txt ="";
	    	   for(x in data){
	    		   
	    		   txt+= "▶"+ data[x].f_name +"<br>";
                   
                   console.log("success data: " + JSON.stringify(data));
                          
	    	   }
	    	   
	    	   $(id).append(txt);

	       },
	       error : function(data, status, err) {
	          //alert('error');
	          console.log("error: " + data);
	       }
	    });
}

//의사 목록 가져오기(dentist_list.jsp)
function DList(){
	console.log("DList function called-");
	
	var c_code = $("#c_code").val();
	var sendData = {c_code : c_code};
	
	$.ajax({
	       url : 'http://localhost:8090/reservation/DList',
	       method : 'POST',
	       data : JSON.stringify(sendData),
	       processData : false,
	       contentType : 'application/json',
	       success : function(data) {
	    	   
	    	   var x;
	    	   var txt ="";
	    	   for(x in data){
	    		   
	    		   var d_code = data[x].d_code;
	    		   console.log(d_code);
	    		   txt+="<div class='card d-block'>";
	    		   if(data[x].profile == null) {
	    			   txt+="<img class='card-img-top' src='resources/image/no-profile.jpg' alt='Card image cap'>";
	    		   } else {
	    			   txt+="<img class='card-img-top' src='http://localhost:8090/reservation/resources/dentistProfile/"+data[x].profile+"' alt='Card image cap'>";
	    		   }
	    		   txt+="<div class='card-body'>";
	    		   txt+="<h5 class='card-title'>"+data[x].d_name+"</h5>";
	    		   txt+="-전화번호: <p class='card-text'>"+data[x].d_tel+"</p>";
	    		   txt+="-이메일: <p class='card-text'>"+data[x].email+"</p>";
	    		   /*txt+="-진료과: <p class='card-text' id='d_fieldList'></p>";*/
	    		   txt+="-진료과: <p class='card-text' id='d_fieldList"+data[x].d_code+"'>";
	    		   txt+="<script>";
	    		   txt+="d_field_List("+data[x].d_code+")";
	    		   txt+="</script>";
	    		   txt+="</p>";
	    		   txt+="</div>";
	    		   txt+="</div> <!-- end card-->";
                   
                   /*console.log("success data: " + JSON.stringify(data));*/
   				   console.log("성공");
                          
	    	   }
	    	   
	    	   $("#mediTeamList").html(txt);

	       },
	       error : function(data, status, err) {
	          //alert('error');
	          console.log("error: " + data);
	       }
	    });
}


//의사 등록(dentist_register.jsp)
function Dregister(){
	console.log("Dregister function called-");
	
	var profile = $("#example-fileinput").val();
	var d_name = $("#d_name").val();
	var d_tel = $("#d_tel").val();
	var email = $("#email").val();
	var c_code = $("#c_code").val();
	var d_field = $("#d_field").val();
	var edu = $("#edu").val();
	var career = $("#career").val();
	
	if (d_name == ""){
		alert("이름을 적어주세요 :)");
	}else if (d_tel == ""){
		alert("전화번호를 적어주세요 :)");
	}else if (email == ""){
		alert("이메일을 적어주세요 :)");
	}else{
		var sendData = {
			profile : profile,
			d_name : d_name,
			d_tel : d_tel,
			email : email,
			c_code : c_code,
			d_field : d_field,
			edu : edu,
			career : career
		};
		
		console.log("=====Dregister값: " + profile +"/"+ d_name + "/"+ d_tel + "/"+ email + "/"+ d_field + "/"+ c_code+ "/"+ edu + "/"+ career);
		
		$.ajax({
			url : 'http://localhost:8090/reservation/DRegister',
			method : 'POST',
			data : JSON.stringify(sendData),
			processData : false,
			contentType : 'application/json',
			success : function(data) {
				
				console.log("success data: " + JSON.stringify(data));
				console.log("성공");

			},
			error : function(data, status, err) {
				// alert('error');
				console.log("error: " + data);
			}
		});
	}
}
