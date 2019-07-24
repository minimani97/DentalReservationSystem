$(document).ready(function() {
	loadDentistList();
	
	// 날짜 선택하면 그 날짜에 해당하는 스케줄표 띄우기
	$('#datepicker-out').on('changeDate', function(e) {
		
		var getDate = e.date;
		
		var date = moment(getDate, "YYYY-MM-DD").format("YYYY-MM-DD");		
		
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
		});
		
		$('#calendar').fullCalendar('gotoDate', date);
		
		var selectedField = $('#selectedField').val();
		
		loadSchedule(date);
	});
	$('#other-datepicker-out').on('changeDate', function(e) {
		
		var getDate = e.date;
		
		var date = moment(getDate, "YYYY-MM-DD").format("YYYY-MM-DD");		
				
		var txt = "<div id='other-calendar' style='margin-left: 20px; margin-right: 20px'></div>";
		$('#other-schedule-area').html(txt);
		
		$('#other-calendar').fullCalendar({
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
		});
		
		$('#other-calendar').fullCalendar('gotoDate', date);
		
		var selectedField = $('#selectedField').val();
		
		loadSchedule(date);
	});
});

// 같은 병원 의료진 리스트 가져오기
function loadDentistList() {
	var c_code = $('#user_clinic').val();
	var user_code = $('#user_code').val();
	
	var sendData = {c_code: c_code};
	$.ajax({
		url:'http://localhost:8090/reservation/loadDentistList',
		method:'POST',
		data:JSON.stringify(sendData),
		processData:false,
		contentType:'application/json',
		success:function(data) {
			var myObj = data;
			var x;
			var txt = "";
			
			for(x in myObj) {
				if(myObj[x].d_code != user_code) {
					txt += "<option value='"+myObj[x].d_code+"'>"+myObj[x].d_name+"</option>";
				}
			}
			
			$('#dentist-list').append(txt);
		},
		error:function(data) {
			console.log("Reservation Info send ERROR!");
		}
	});
}

// 스케줄 불러오기
function loadSchedule(date) {
	var lunchTime = {title: "점심시간", start: date+"T13:00:00", end: date+"T14:00:00", color: "#88ad3a"};
		
	var sendData;
	
	if($('#secondTab').is(".active") === true) {
		$('#other-calendar').fullCalendar('renderEvent', lunchTime);
		
		var d_code = $("#selected-other-dentist").val();
		sendData = {date: date, d_code: d_code};
	} else {
		$('#calendar').fullCalendar('renderEvent', lunchTime);
		
		var user_code = $('#user_code').val();
		sendData = {date: date, d_code: user_code};
	}
	
	
	$.ajax({
		url:'http://localhost:8090/reservation/getMySchedule',
		method:'POST',
		data:JSON.stringify(sendData),
		dataType:'JSON',
		processData:false,
		contentType:'application/json',
		success:function(data) {
			var myObj = data;
			var x;
			
			for(x in myObj) {
				var s_time = myObj[x].startTime + ":00";
				var e_time = myObj[x].endTime + ":00";
				var title = myObj[x].patient_name + " 환자";
				
				var newEvent = {title: title, start: date+"T"+s_time, end: date+"T"+e_time};
				
				if($('#secondTab').is(".active") === true) {
					$('#other-calendar').fullCalendar('renderEvent', newEvent);
				} else {
					$('#calendar').fullCalendar('renderEvent', newEvent);
				}
				
			}
		},
		error:function(data) {
			
		}
	});
}

function clickDentistName(d_code) {
	$('#content-area').show();
	$('#selected-other-dentist').val(d_code);
}
