$(document).ready(function() {
	loadFamilyInfo();
	
	if(window.location.href == "http://localhost:8090/reservation/mypage") {
		loadFamilyList();
		console.log("LOAD FAMILY LIST");
	}
	
	if(window.location.href == "http://localhost:8090/reservation/reservationList") {
		console.log("LOAD RESERVATION LIST");
	}
	
});

// 아이디에 등록된 가족 불러오기
function loadFamilyInfo() {
	var user_code = $('#user_code').val();
	
	var sendData = {user_code: user_code};
	$.ajax({
		url:'http://localhost:8090/reservation/loadFamilyInfo',
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
			console.log("Reservation Info send ERROR!");
		}
	});
}

// 해당 환자 정보 불러오기
function getPatientInfo(p_code) {
	
	$('#change-myinfo-btn').attr('disabled', false);
	
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
				$('#phone-num').val(myObj[x].tel);
				$('#address').val(myObj[x].addr);
				
				$('#selectedMember').val(myObj[x].p_code);
			}
			
		},
		error:function(data) {
			console.log("Get Patient Info ERROR!");
		}
	});
}

// 비밀번호 일치하는지 판단하기
function checkPassword() {
	var inputed = $('#change-password').val();
	var reinputed = $('#check-password').val();
	
	 if(inputed == "" && reinputed == "") {
         $('#change-password-btn').attr('disabled', true);
         $('.invalid-feedback').hide();
         /*$('#confirmPassword').removeClass('is-invalid');
         $('#confirmPassword').attr('area-invalid', false);*/
       }else if(reinputed == "" && (inputed != reinputed || inputed == reinputed)) {
         $('#change-password-btn').attr('disabled', true);
         $('.invalid-feedback').show();
         /*$('#confirmPassword').addClass('is-invalid');
         $('#confirmPassword').attr('area-invalid', true);*/
       }else if(inputed == reinputed){
         $('#change-password-btn').attr('disabled', false);
         $('.invalid-feedback').hide();
         /*$('#confirmPassword').removeClass('is-invalid');
         $('#confirmPassword').attr('area-invalid', false);*/
       }else if(inputed != reinputed){
         $('#change-password-btn').attr('disabled', true);
         $('.invalid-feedback').show();
         /*$('#confirmPassword').addClass('is-invalid');
         $('#confirmPassword').attr('area-invalid', true);*/
       } a
}

// 비밀번호 변경하기
function changePassword() {
	var user_code = $('#user_code').val();
	var password = $('#check-password').val();
	
	var sendData = {
			user_code: user_code,
			password: password
	};
	$.ajax({
		url:'http://localhost:8090/reservation/changePassword',
		method:'POST',
		data:JSON.stringify(sendData),
		processData:false,
		contentType:'application/json',
		success:function(data) {
			alert("비밀번호가 변경되었습니다.");
			
			$('#change-password').val("");
			$('#check-password').val("");
			$('#change-password-btn').attr('disabled', true);
		},
		error:function(data) {
			console.log("Password Change ERROR!");
		}
	});
}

// 정보 수정하기
function editMyInfo() {
	var p_code = $('#selectedMember').val();
	var addr = $('#address').val();
	var tel = $('#phone-num').val();
	
	var sendData = {
			p_code: p_code,
			addr: addr,
			tel: tel
	};
	$.ajax({
		url:'http://localhost:8090/reservation/editMyInfo',
		method:'POST',
		data:JSON.stringify(sendData),
		processData:false,
		contentType:'application/json',
		success:function(data) {
			alert("정보가 수정되었습니다.");
		},
		error:function(data) {
			console.log("My Information change ERROR!");
		}
	});
}

// 가족 정보 불러오기
function loadFamilyList() {
	var user_code = $('#user_code').val();
	console.log("유저번호: " + user_code);
	
	var sendData = {user_code: user_code};
	$.ajax({
		url:'http://localhost:8090/reservation/loadFamilyList',
		method:'POST',
		data:JSON.stringify(sendData),
		processData:false,
		contentType:'application/json',
		success:function(data) {
			var myObj = data;
			var x;
			var txt = "";
			
			for(x in myObj) {
				
				if(myObj[x].relationship == "본인") {
					txt = "<tr><td>"+myObj[x].name+"</td><td>"+myObj[x].birth+"</td><td>"+myObj[x].relationship+"</td><td><input type='button' style='float: right' id='change-password-btn' class='btn btn-primary' onclick='confirmDeleteFamilyInfo("+myObj[x].p_code+")' value='삭제' disabled></td></tr>";
				} else {
					txt = "<tr><td>"+myObj[x].name+"</td><td>"+myObj[x].birth+"</td><td>"+myObj[x].relationship+"</td><td><input type='button' style='float: right' id='change-password-btn' class='btn btn-primary' onclick='confirmDeleteFamilyInfo("+myObj[x].p_code+")' value='삭제'></td></tr>";
				}
				
				$('#family-list-tbody').append(txt);
				
			}
		},
		error:function(data) {
			console.log("Family Information load ERROR!");
		}
	});
}

// 가족 등록하기
function registerFamilyInfo() {
	var name = $('#register-name').val();
	var relationship = $('#register-relationship').val();
	var birth = $('#register-birth').val();
	var addr = $('#register-addr').val();
	var tel = $('#register-tel').val();
	var user_code = $('#user_code').val();
	
	var sendData = {
		name : name,
		relationship : relationship,
		birth : birth,
		addr : addr,
		tel : tel,
		user_code : user_code
	};
	
	$.ajax({
		url:'http://localhost:8090/reservation/registerFamilyInfo',
		method:'POST',
		data:JSON.stringify(sendData),
		processData:false,
		contentType:'application/json',
		success:function(data) {
			$('#register-cancel-btn').trigger("click");
			alert("가족 등록이 완료되었습니다.");
			window.location.reload();
		},
		error:function(data) {
			console.log("Register Family Information ERROR!");
		}
	});
	
}

function confirmDeleteFamilyInfo(p_code){
	if(confirm("정말 삭제하시겠습니까?") == true) {
		deleteFamilyInfo(p_code);
	} else {
		return;
	}
}

// 가족 정보 삭제하기
function deleteFamilyInfo(p_code) {
	
	var sendData = {p_code: p_code};
	$.ajax({
		url:'http://localhost:8090/reservation/deleteFamilyInfo',
		method:'POST',
		data:JSON.stringify(sendData),
		processData:false,
		contentType:'application/json',
		success:function(data) {
			alert("가족 정보가 삭제되었습니다.");
			window.location.reload();
		},
		error:function(data) {
			console.log("Family Information delete ERROR!");
		}
	});
	
}

// 해당 환자 예약리스트 불러오기
function loadReservationList(p_code) {
	$("#reservation-list-tbody").html("");
	$('#selectedMember').val(p_code);
	
	var sendData = {p_code: p_code};
	$.ajax({
		url:'http://localhost:8090/reservation/loadReservationList',
		method:'POST',
		data:JSON.stringify(sendData),
		processData:false,
		contentType:'application/json',
		success:function(data) {
			var myObj = data;
			var x;
			var txt = "";
			var cnt = 1;
			
			for(x in myObj) {
				txt += "<tr><td>"+cnt+"</td>";
				txt += "<td>"+myObj[x].date+"<br>"+myObj[x].s_time+" ~ "+myObj[x].e_time+"</td>";
				txt += "<td>"+myObj[x].c_name+"</td>";
				txt += "<td>"+myObj[x].f_name+"</td>";
				txt += "<td><input type='button' style='float: right' id='detail-info-btn' class='btn btn-primary' onclick='getReservationInformation("+myObj[x].num+")' data-toggle='modal' data-target='#myModal' value='자세히보기'></td></tr>";

				cnt++;
			}			
			$("#reservation-list-tbody").append(txt);
		},
		error:function(data) {
			console.log("Reservation List load ERROR!");
		}
	});
	
}

function getReservationInformation(num) {
	var sendData = {num: num};
	$.ajax({
		url:'http://localhost:8090/reservation/getReservationInformation',
		method:'POST',
		data:JSON.stringify(sendData),
		processData:false,
		contentType:'application/json',
		success:function(data) {
			var myObj = data;
			var x;
			var r_date;
			
			for(x in myObj) {
				$('#check_clinic').val(myObj[x].c_name);
				$('#check_field').val(myObj[x].f_name);
				$('#check_dentist').val(myObj[x].d_name);
				var dateTime = myObj[x].date + "   " + myObj[x].s_time + " ~ " + myObj[x].e_time;
				$('#check_dateTime').val(dateTime);
				$('#check_additionalInfo').val(myObj[x].extra_info);
				if(myObj.personal_info == 'Y') $('#check_info_concent').val("동의");
				else $('#check_info_concent').val("비동의");
				
				r_date = myObj[x].date;
				var year = r_date.split("-")[0];
				var month = r_date.split("-")[1] - 1;
				var day = r_date.split("-")[2];
				
				var today = new Date();
				var endDate = new Date(year, month, day);

				if(today <= endDate) {
					$('#cancel-reservation-btn').attr('disabled', false);
					$('#cancel-reservation-btn').attr('onclick', 'confirmReservation('+myObj[x].num+')');
				} else {
					$('#cancel-reservation-btn').attr('disabled', true);
				}
			}
			
		},
		error:function(data) {
			console.log("Reservation Information load ERROR!");
		}
	});
}

function confirmReservation(num){
	if(confirm("정말 예약을 취소하시겠습니까?") == true) {
		cancelReservation(num);
	} else {
		return;
	}
}

function cancelReservation(num) {
	var sendData = {num: num};
	$.ajax({
		url:'http://localhost:8090/reservation/cancelReservation',
		method:'POST',
		data:JSON.stringify(sendData),
		processData:false,
		contentType:'application/json',
		success:function(data) {
			alert("예약이 취소되었습니다.");
			window.location.reload();
		},
		error:function(data) {
			console.log("Reservation cancel ERROR!");
		}
	});
}