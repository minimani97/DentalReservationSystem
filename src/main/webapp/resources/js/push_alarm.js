
//내일 방문환자 데이터 가져오기
function pushMessageInfo() {

	console.log("pushMessage Function is called!");
	
	$.ajax({
		url : 'http://localhost:8090/reservation/fcmTest',
		method : 'POST',
		processData : false,
		contentType : 'application/json',
		success : function(data) {
			var myObj = data;
			
			for(x in myObj){
				var p_name = myObj[x].p_name;
				var date = myObj[x].date;
				var s_time = myObj[x].s_time;
				var c_name = myObj[x].c_name;
				
				console.log("p_name: "+p_name);
				console.log("date: "+date);
				console.log("s_time: "+s_time);
				console.log("c_name: "+c_name);
				
				pushMessage(p_name,date,s_time,c_name);
			}
					
				}
			});
	
}

//내일 방문할 환자에게만 푸시알림 보내기
function pushMessage(p_name,date,s_time,c_name) {

	console.log("pushMessageStart Function is called!");
	
	var sendData = {p_name: p_name, date: date, s_time: s_time, c_name: c_name};
	
	$.ajax({
		url : 'http://localhost:8090/reservation/sendPush',
		method : 'POST',
		data:JSON.stringify(sendData),
		processData : false,
		contentType : 'application/json',
		success : function(data) {
			
					
				}
			});
	
}



//해당병원에 내원한 모든 환자 정보 가져오기
function allPushInfo() {
	$('#login-modal').modal("hide");
	console.log("allPushInfo Function is called!");
	
	$.ajax({
		url : 'http://localhost:8090/reservation/allPushInfo',
		method : 'POST',
		processData : false,
		contentType : 'application/json',
		success : function(data) {
			var myObj = data;
			
			for(x in myObj){
				var token = myObj[x].token;				
				
				console.log("token: "+token);
				
				allPush(token);
			}
					
				}
			});
	
}



//내원한 모든 환자에게 푸시알림 보내기
function allPush(token) {

	console.log("allPush Function is called!");
	
	var title = $("#push_title").val();
	var text = $("#push_text").val();
	
	console.log("push_title : "+push_title);
	
	var sendData = {title: title, text: text, token: token};
	
	$.ajax({
		url : 'http://localhost:8090/reservation/allPush',
		method : 'POST',
		data:JSON.stringify(sendData),
		processData : false,
		contentType : 'application/json',
		success : function(data) {
			
					
				}
			});
	
}
