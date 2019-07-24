$(document).ready(function() {
	
	$('input:radio[name=customRadio]').click(function() {
		
		$('#register-form').html("");
		$('#register-form').attr('style', '');
		$('#register-form').attr('style', 'margin-top: 30px');
		
		if($('input[name=customRadio]:checked').val() == "patient") {
			
			var txt = "";
			
			txt += "<div class='form-group mb-3'><label for='simpleinput'>ID</label><input type='text' id='id' class='form-control'><div><label id='isDuplicateId' class='duplicate-alert-label'></label><input type='button' class='btn btn-primary' onclick='isDuplicateId()' style='float:right;margin-top: 5px;' value='ID 중복확인'></div></div>";
			txt += "<div class='form-group mb-3' style='margin-top: 50px;'><label for='simpleinput'>비밀번호</label><input type='password' id='password' class='form-control'></div>";
			txt += "<div class='form-group mb-3'><label for='simpleinput'>이름</label><input type='text' id='name' class='form-control'></div>";
			txt += "<div class='form-group mb-3'><label for='simpleinput'>생년월일</label><input type='date' id='birth' class='form-control'></div>";
			txt += "<div class='form-group mb-3'><label for='simpleinput'>주소</label><input type='text' id='address' class='form-control'><span class='font-13 text-muted'>상세하게 주소를 적지 않으셔도 괜찮습니다.</span></div>";
			txt += "<div class='form-group'><label>전화번호</label><input type='tel' id='register-tel' class='form-control' data-toggle='input-mask' data-mask-format='000-0000-0000' maxlength='13'><span class='font-13 text-muted'>'01x-xxxx-xxxx' 형식으로 입력해주세요.</span></div>";
			txt += "<button type='button' id='register-btn' class='btn btn-primary' onclick='patientUserRegister()' style='float:right'>가입하기</button>";
			
			$('#register-form').html(txt);
			
		} 
		else if($('input[name=customRadio]:checked').val() == "dentist") {
			
			var txt = "";
			
			txt += "<div class='form-group mb-3'><label for='simpleinput'>ID</label><input type='text' id='id' class='form-control'><div><label id='isDuplicateId' class='duplicate-alert-label'></label><input type='button' class='btn btn-primary' onclick='isDuplicateId()' style='float:right;margin-top: 5px;' value='ID 중복확인'></div></div></div>";
			txt += "<div class='form-group mb-3' style='margin-top: 50px;'><label for='simpleinput'>비밀번호</label><input type='password' id='password' class='form-control'></div>";
			txt += "<div class='form-group mb-3'><label for='simpleinput'>이름</label><input type='text' id='name' class='form-control'></div>";
			txt += "<div class='form-group mb-3'><label for='example-fileinput'>대표 사진</label><input type='file' id='profile' class='form-control-file'></div>";
			txt += "<div class='form-group mb-3'><label>전화번호</label><input type='text' id='register-tel' class='form-control' data-toggle='input-mask' data-mask-format='000-0000-0000' maxlength='13'><span class='font-13 text-muted' oninput='checkValues()'>'01x-xxxx-xxxx' 형식으로 입력해주세요.</span></div>";
			txt += "<div class='form-group mb-3'><label for='example-email'>이메일</label><input type='email' id='email' class='form-control'></div>";
			txt += "<div class='form-group mb-3'><label for='example-select'>소속병원</label><select class='form-control' id='clinic-list'><option value=''></option></select></div>";
			
			$('#register-form').html(txt);
			
			// 병원 목록 불러오기
			getClinicList();
			
			txt = "";
			txt += "<div class='form-group mb-3'><h4 class='header-title mt-5 mt-sm-0'>진료과목</h4><p class='text-muted'>담당하시는 진료과목을 모두 선택해주세요.</p><div class='mt-2'>";
			txt += "<div class='custom-control custom-checkbox'><input type='checkbox' class='custom-control-input' name=field-list id='customCheck1' value='1'><label class='custom-control-label' for='customCheck1'>임플란트</label></div>";
			txt += "<div class='custom-control custom-checkbox'><input type='checkbox' class='custom-control-input' name=field-list id='customCheck2' value='2'><label class='custom-control-label' for='customCheck2'>틀니</label></div>";
			txt += "<div class='custom-control custom-checkbox'><input type='checkbox' class='custom-control-input' name=field-list id='customCheck3' value='3'><label class='custom-control-label' for='customCheck3'>치아성형</label></div>";
			txt += "<div class='custom-control custom-checkbox'><input type='checkbox' class='custom-control-input' name=field-list id='customCheck4' value='4'><label class='custom-control-label' for='customCheck4'>턱관절 치료</label></div>";
			txt += "<div class='custom-control custom-checkbox'><input type='checkbox' class='custom-control-input' name=field-list id='customCheck5' value='5'><label class='custom-control-label' for='customCheck5'>교정</label></div>";
			txt += "<div class='custom-control custom-checkbox'><input type='checkbox' class='custom-control-input' name=field-list id='customCheck6' value='6'><label class='custom-control-label' for='customCheck6'>치아미백</label></div>";
			txt += "<div class='custom-control custom-checkbox'><input type='checkbox' class='custom-control-input' name=field-list id='customCheck7' value='7'><label class='custom-control-label' for='customCheck7'>보철치료</label></div>";
			txt += "<div class='custom-control custom-checkbox'><input type='checkbox' class='custom-control-input' name=field-list id='customCheck8' value='8'><label class='custom-control-label' for='customCheck8'>신경치료</label></div>";
			txt += "<div class='custom-control custom-checkbox'><input type='checkbox' class='custom-control-input' name=field-list id='customCheck9' value='9'><label class='custom-control-label' for='customCheck9'>치주치료</label></div>";
			txt += "<div class='custom-control custom-checkbox'><input type='checkbox' class='custom-control-input' name=field-list id='customCheck10' value='10'><label class='custom-control-label' for='customCheck10'>사랑니발치</label></div>";
			txt += "<div class='custom-control custom-checkbox'><input type='checkbox' class='custom-control-input' name=field-list id='customCheck11' value='11'><label class='custom-control-label' for='customCheck11'>스케일링</label></div>";
			txt += "<div class='custom-control custom-checkbox'><input type='checkbox' class='custom-control-input' name=field-list id='customCheck12' value='12'><label class='custom-control-label' for='customCheck12'>검진</label></div></div></div>";
			txt += "<div class='form-group mb-3'><label for='example-textarea'>학력</label><textarea class='form-control' id='academic-ability' rows='5'></textarea></div>";
			txt += "<div class='form-group mb-3'><label for='example-textarea'>경력</label><textarea class='form-control' id='career' rows='5'></textarea></div>";
			txt += "<button type='button' id='register-btn' class='btn btn-primary' onclick='dentistUserRegister()' style='float:right'>가입하기</button>";
			
			$('#register-form').append(txt);
		}
	});
	
});

function getClinicList() {
	$.ajax({
		url:'http://localhost:8090/reservation/getClinicList',
		method:'POST',
		processData:false,
		contentType:false,
		success:function(data) {
			var myObj = data;
			var x;
			var txt = "";
			
			for(x in myObj) {
				txt += "<option value='"+myObj[x].c_code+"'>"+myObj[x].c_name+"</option>";
			}
						
			$('#clinic-list').append(txt);
		},
		error:function(data) {
			console.log("Clinic List load ERROR!");
		}
	});
}

function isDuplicateId() {
	var inputIdValue = $('#id').val();
		
	$('#isDuplicateId').hide();
	
	if(inputIdValue == "") {
		alert('아이디를 입력해주세요!');
	} else {
		
		var sendData = {id: inputIdValue};		
		$.ajax({
			url:'http://localhost:8090/reservation/isDuplicateId',
			method:'POST',
			data:JSON.stringify(sendData),
			processData:false,
			contentType:'application/json',
			success:function(data) {
				if(data == 1) {
					$('#isDuplicateId').text("이미 존재하는 아이디입니다.");
					$('#isDuplicateId').attr('style', 'color: #c42b2b');
					$('#isDuplicateId').show();
				} else {
					$('#isDuplicateId').text("사용가능한 아이디입니다.");
					$('#isDuplicateId').attr('style', 'color: #2e9b29');
					$('#isDuplicateId').show();
				}
			},
			error:function(data) {
				console.log("isDuplicateId ERROR!");
			}
		});
		
	}
}

function patientUserRegister() {
	
	if($('#id').val() == "") {
		alert('아이디를 입력해주세요.');
	} else if($('#isDuplicateId').text() == "") {
		alert('아이디 중복확인을 해주세요.');
	} else if($('#isDuplicateId').text() == "이미 존재하는 아이디입니다.") {
		alert('아이디 중복 여부를 확인해주세요.')
	} else if($('#password').val() == "") {
		alert('비밀번호를 입력해주세요.');
	} else if($('#name').val() == "") {
		alert('이름을 입력해주세요.');
	} else if($('#birth').val() == "") {
		alert('생년월일을 입력해주세요.');
	} else if($('#address').val() == "") {
		alert('주소를 입력해주세요.');
	} else if($('#register-tel').val() == "") {
		alert('전화번호를 입력해주세요.');
	} else if($('#register-tel').val().split("-").length != 3) {
		alert('전화번호를 01x-xxxx-xxxx 형식으로 입력해주세요.')
	} else {
		
		var id = $('#id').val();
		var password = $('#password').val();
		var name = $('#name').val();
		var birth = $('#birth').val();
		var addr = $('#address').val();
		var tel = $('#register-tel').val();
		
		var sendData = {
			id: id,
			password: password,
			name: name,
			birth: birth,
			addr: addr,
			tel: tel
		};
		$.ajax({
			url:'http://localhost:8090/reservation/patientUserRegister',
			method:'POST',
			data:JSON.stringify(sendData),
			processData:false,
			contentType:'application/json',
			success:function(data) {
				alert('가입이 완료되었습니다.');
				window.location.href="http://localhost:8090/reservation/";
			},
			error:function(data) {
				console.log("User register ERROR!");
			}
		});
	}
}

function dentistUserRegister() {
		
	if($('#id').val() == "") {
		alert('아이디를 입력해주세요.');
	} else if($('#isDuplicateId').text() == "") {
		alert('아이디 중복확인을 해주세요.');
	} else if($('#isDuplicateId').text() == "이미 존재하는 아이디입니다.") {
		alert('아이디 중복 여부를 확인해주세요.')
	} else if($('#password').val() == "") {
		alert('비밀번호를 입력해주세요.');
	} else if($('#name').val() == "") {
		alert('이름을 입력해주세요.');
	} else if($('#register-tel').val() == "") {
		alert('전화번호를 입력해주세요.');
	} else if($('#register-tel').val().split("-").length != 3) {
		alert('전화번호를 01x-xxxx-xxxx 형식으로 입력해주세요.')
	} else if($('#email').val() == "") {
		alert('이메일을 입력해주세요.');
	} else if($('select[name=clinic-list]').val() == "") {
		alert('소속 병원을 선택해주세요.');
	} else if($('input:checkbox[name=field-list]:checked()').length == 0) {
		alert('담당하시는 진료과목을 하나 이상 선택해주세요.');
	} else {
		
		var formData = new FormData();
		formData.append('id', $('#id').val());
		formData.append('password', $('#password').val());
		formData.append('name', $('#name').val());
		formData.append('profile', $('#profile')[0].files[0]);
		formData.append('tel', $('#register-tel').val());
		formData.append('email', $('#email').val());
		formData.append('clinic', $("#clinic-list").find(":selected").val());
		
		var str = "";
		$('input:checkbox[name=field-list]:checked').each(function(index) {
			str += $(this).val() + ",";
		});
		
		formData.append('field', str);
		formData.append('edu', $('#academic-ability').val().replace(/(?:\r\n|\r|\n)/g, '<br>'));
		formData.append('career', $('#career').val());
		
		$.ajax({
			url:'http://localhost:8090/reservation/dentistUserRegister',
			method:'POST',
			data:formData,
			processData:false,
			contentType:false,
			success:function(data) {
				alert('가입이 완료되었습니다.');
				window.location.href="http://localhost:8090/reservation/";
			},
			error:function(data) {
				console.log("User register ERROR!");
			}
		});
	}
	
}