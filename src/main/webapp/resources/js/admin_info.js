$(document).ready(function() {	
	dentistInfo();
	patientInfo();
	clinicInfo();
});



function dentistInfo () {
		console.log("dentistInfo Function is called!");


		$.ajax({
			url : 'http://localhost:8090/reservation/dentistInfo',
			method : 'POST',
			processData : false,
			contentType : 'application/json',
			success : function(data) {
						var myObj = data;
						var txt = "";
						var x;
					
						  txt +="<thead>";
						  txt +="<tr>";
						  txt +="<th>의료진명</th>";
						  txt +="<th>소속병원</th>";
						  txt +="<th>전화번호</th>";
						  txt +="<th>이메일</th>";
						  txt +="</tr>";
						  txt +="</thead>";

						for (x in myObj) {
							var d_name = myObj[x].d_name;
							var c_name = myObj[x].c_name;
							var d_tel = myObj[x].d_tel;
							var email = myObj[x].email;
							
							console.log("email: "+email);

							 txt +="<tbody>";
							  txt +="<tr>";
							  txt +="<td>"+d_name+"</td>";
							  txt +="<td>"+c_name+"</td>";
							  txt +="<td>"+d_tel+"</td>";
							  txt +="<td>"+email+"</td>";	 
							  txt +="</tr>";
						}
						$("#d_table").append(txt);
					},
					error : function(data) {
						console.log("dentistInfo information load ERROR!");
					}
				});
	
}






function patientInfo () {
		console.log("patientInfo Function is called!");

		$.ajax({
			url : 'http://localhost:8090/reservation/patientInfo',
			method : 'POST',
			processData : false,
			contentType : 'application/json',
			success : function(data) {
						var myObj = data;
						var txt = "";
						var x;
						
						  txt +="<thead>";
						  txt +="<tr>";
						  txt +="<th>아이디</th>";
						  txt +="<th>이름</th>";
						  txt +="<th>생년월일</th>";
						  txt +="<th>전화번호</th>";
						  txt +="<th>주소</th>";
						  txt +="</tr>";
						  txt +="</thead>";
                   
                   
						 

						for (x in myObj) {
							var id = myObj[x].p_id;
							var p_name = myObj[x].p_name;
							var birth = myObj[x].p_birth;
							var p_tel = myObj[x].p_tel;
							var addr = myObj[x].p_addr;
							
							console.log("addr: "+addr);

							 txt +="<tbody>";
							  txt +="<tr>";
							  txt +="<td>"+id+"</td>";
							  txt +="<td>"+p_name+"</td>";
							  txt +="<td>"+birth+"</td>";
							  txt +="<td>"+p_tel+"</td>";	 
							 txt +="<td>"+addr+"</td>";
							  txt +="</tr>";
							  
						}
			
						$("#p_table").append(txt);
					},
					error : function(data) {
						console.log("patientInfo information load ERROR!");
					}
		
				});

}





function clinicInfo() {
	console.log("clinicInfo Function is called!");

	$.ajax({
		url : 'http://localhost:8090/reservation/clinicInfo',
		method : 'POST',
		processData : false,
		success : function(data) {
					var myObj = data;
					var txt = "";
					var x;
					
					
					  txt +="<thead>";
					  txt +="<tr>";
					  txt +="<th>병원명</th>";
					  txt +="<th>전화번호</th>";
					  txt +="<th>병원주소</th>";
					  txt +="<th>승인여부</th>";
					  txt +="</tr>";
					  txt +="</thead>";
					  var i=0;
					  
					for (x in myObj) {
						var c_name = myObj[x].c_name;
						var c_tel = myObj[x].c_tel;
						var c_address = myObj[x].c_address;
						var certify = myObj[x].certify;
						  var id = "#certifyOff"+i;
						console.log("id: "+id);

						 txt +="<tbody>";
						  txt +="<tr>";
						  txt +="<td>"+c_name+"</td>";
						  txt +="<td>"+c_tel+"</td>";
						  txt +="<td>"+c_address+"</td>";
						  if(certify == 'N'){
						  txt +="<td id='certifyOff"+i+"'  onclick=sendCertify(\""+c_name+"\",\""+id+"\")  type='button' class='btn btn-primary'" +
						  		"style='WIDTH: 75pt; HEIGHT: 20pt text-align:center'>승인요청</td>";
						  }
						  else if(certify == 'Y'){
					      txt +="<td type='button' class='btn btn-primary2' style='WIDTH: 75pt; HEIGHT: 38pt; text-align:center' disabled='disabled'>승인완료</td>";
						  }
						  txt +="</tr>";
						  i++;
					}
					$("#c_table").append(txt);
				},
				error : function(data) {
					console.log("clinicInfo information load ERROR!");
				}
			});

}

function sendCertify(c_name,id) {

	$(id).removeClass('btn btn-primary').addClass('btn btn-primary2').html('승인완료');	
	
	var sendData = {
			c_name : c_name
	};

	
	$.ajax({
		url : 'http://localhost:8090/reservation/sendCertify',
		method : 'POST',
		data : JSON.stringify(sendData),
		processData : false,
		contentType : 'application/json',
		success : function(data) {
			alert("승인 완료되었습니다.");
		}
	});
	
}



