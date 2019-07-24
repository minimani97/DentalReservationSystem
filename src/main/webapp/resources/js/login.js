
/*$(document).ready(function() {
	setPatientInfo();
	if(document.location.href == "http://localhost:8090/reservation/home") {
		setPatientInfo();
	} else if (document.location.href == "http://localhost:8090/reservation/home2") {
		setPatientInfo();
	}

});*/

function saveUser() {
	
	var sendData = {
			id: $('#id').val(),
			password: $('#password').val(),
			group: $('input[name=user_type]:checked').val(),
			p_name: $('#fullname').val(),
			birth: $('#birth').val(),
			p_tel: $('#tel').val(),
			addr: $('#address').val()
	};
	
	$.ajax({
		url:'http://localhost:8090/reservation/saveUserInfo',
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

function goLogin() {
	
	var sendData = {
			id: $('#id').val(),
			password: $('#password').val(),
			group: $('input[name=user_type]:checked').val()
	};
	
	$.ajax({
		url:'http://localhost:8090/reservation/goLogin',
		method:'POST',
		data:JSON.stringify(sendData),
		processData:false,
		contentType:'application/json',
		success:function(data) {
			var myObj = data;
			var x;
			var id;
			
			for(x in myObj) {
				if(myObj[x].flg == 1) {
					if(myObj[x].group == 'P') {
						id = myObj[x].id;
						console.log(id);
						
						$(document).ready(function 
								se(){
							console.log("여길 들어왔니ㅣㅣㅣㅣㅣㅣ");
							
							var sendData = {id : id};
							
							$.ajax({
								url:'http://localhost:8090/reservation/sessionP',
								method:'POST',
								data:JSON.stringify(sendData),
								processData:false,
								contentType:'application/json',
								success:function(data) {
									console.log("세션 ㅇㅁㅇ!");
									window.location.href = "http://localhost:8090/reservation/homeForPatient";
								},
								error:function(data) {
									console.log("session ERROR!");
								}
							});
							
						});
										
					} else if(myObj[x].group == 'D') {
						id = myObj[x].id;
						console.log(id);
						
						$(document).ready(function 
								se(){
							console.log("닥터!!!!!!!!!!여길 들어왔니ㅣㅣㅣㅣㅣㅣ");
							
							var sendData = {id : id};
							
							$.ajax({
								url:'http://localhost:8090/reservation/sessionD',
								method:'POST',
								data:JSON.stringify(sendData),
								processData:false,
								contentType:'application/json',
								success:function(data) {
									console.log("세션 ㅇㅁㅇ!");
									window.location.href = "http://localhost:8090/reservation/dentist_main";
								},
								error:function(data) {
									console.log("session ERROR!");
								}
							});
							
						});
					} else {
						
						$(document).ready(function 
								se(){
							console.log("타니 여기를?");
							$.ajax({
								url:'http://localhost:8090/reservation/sessionA',
								method:'POST',
								processData:false,
								contentType:false,
								success:function(data) {
									console.log("세션 ㅇㅁㅇ!");
									window.location.href = "http://localhost:8090/reservation/admin";
								},
								error:function(data) {
									console.log("session ERROR!");
								}
							});
							
						});
					}
				} else {
					alert("아이디와 비밀번호를 다시 확인해주세요.");
				}
			}
			
		},
		error:function(data) {
			console.log("Dentist Code load ERROR!");
		}
	});
}



function logout(){
    console.log("logout called!!!!!!!!!!!!!!!!!!!!!!!!!!!!1!");
    
    var user_num  = 1;
    //console.log(" userNum 값000:" + user_num);

    $.ajax({
       url : 'http://localhost:8090/reservation/logout',
       method : 'POST',
       data : user_num,
       processData : false,
       contentType : false,
       success : function(data) {

          console.log(data);
          //console.log("값1:" + user_num);

          //loginCheckProc(data, user_num);
          alert("로그아웃 됩니다.");
          window.location.href = "http://localhost:8090/reservation/";
          /* if("${sessionScope.user_num}" != null){
             console.log("값2:" + "${sessionScope.user_num}");
             loginCheckProc(data, user_num);
             console.log("값3:" + "${sessionScope.user_num}");
             alert("로그아웃 됩니다.");
             //window.location.href = "http://localhost:8080/ssun/";
          } */

       },
       error : function(data, status, err) {
          //alert('error');
          console.log("error: " + data);
       }
    });         
    
 }

/*function setPatientInfo(){
	
	var id = "${sessionScope.id}";
	var p_name = "${sessionScope.p_name}";
	var p_code = "${sessionScope.p_code}";
	
	$("#idTag").val(id);
	$("#p_name").val(p_name);
	$("#p_code").val(p_code);
	
	console.log("세션값이당: " + id +", " + p_name+", " + p_code);
}*/

