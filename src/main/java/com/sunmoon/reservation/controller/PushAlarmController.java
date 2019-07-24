package com.sunmoon.reservation.controller;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.sunmoon.reservation.model.AdminInfo;
import com.sunmoon.reservation.model.ReservationInfo;
import com.sunmoon.reservation.service.AdminService;
import com.sunmoon.reservation.service.PushAlarmService;

@RestController
public class PushAlarmController {
	private static final Logger logger = LoggerFactory.getLogger(PushAlarmController.class);
	private PushAlarmService pushAlarmService;

	@Autowired
	public void setPushAlarmService(PushAlarmService pushAlarmService) {
		this.pushAlarmService = pushAlarmService;
	}
	
	//내일 예약환자 정보 가져오기
	@RequestMapping("/fcmTest")
	public List<ReservationInfo> fcmTest() throws Exception {
		
		return pushAlarmService.pushInfoService();

	}
	
	//내일 예약환자 푸시알림
	@RequestMapping(value="/sendPush")
	public void sendPushMessage(@RequestBody Map<String, Object> data)  {
		
		System.out.println("in sendPushMessage controller");
		
		String p_name = data.get("p_name").toString();
		String date = data.get("date").toString();
		String s_time = data.get("s_time").toString();
		String c_name = data.get("c_name").toString();
		String token = data.get("token").toString();
		
		
try {    
		
		String path = "C:\\Users\\yelong\\Downloads\\retest-f2877-firebase-adminsdk-xw1g2-57722f5508.json";           
		String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
		String[] SCOPES = { MESSAGING_SCOPE };
    
		GoogleCredential googleCredential = GoogleCredential
                        	.fromStream(new FileInputStream(path))
                        	.createScoped(Arrays.asList(SCOPES));
		googleCredential.refreshToken();
                        
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type" , MediaType.APPLICATION_JSON_VALUE);
		headers.add("Authorization", "Bearer " + googleCredential.getAccessToken());
    
		JSONObject notification = new JSONObject();
		notification.put("body", p_name+"님, 내일 "+c_name+" 진료가 "+s_time+" 에  예약되어 있습니다.");
		notification.put("title", "쫀떡알림");
    
		JSONObject message = new JSONObject();
		message.put("token", token);
		message.put("notification", notification);
    
		JSONObject jsonParams = new JSONObject();
		jsonParams.put("message", message);
    
		HttpEntity<JSONObject> httpEntity = new HttpEntity<JSONObject>(jsonParams, headers);
		RestTemplate rt = new RestTemplate();            
    
		ResponseEntity<String> res = rt.exchange("https://fcm.googleapis.com/v1/projects/retest-f2877/messages:send"
				, HttpMethod.POST
				, httpEntity
				, String.class);

		if (res.getStatusCode() != HttpStatus.OK) {
			logger.debug("FCM-Exception");
			logger.debug(res.getStatusCode().toString());
			logger.debug(res.getHeaders().toString());
			logger.debug(res.getBody().toString());
        
		} else {
			logger.debug(res.getStatusCode().toString());
			logger.debug(res.getHeaders().toString());
			logger.debug(res.getBody().toLowerCase());
        
		}
	} catch (Exception e) {
		e.printStackTrace();
	}

		
	}
	

	
	
	//해당병원에 내원한 모든 환자정보 가져오기
	@RequestMapping("/allPushInfo")
	public List<ReservationInfo> allPushInfo() throws Exception {
		
		return pushAlarmService.allPushInfoService();

	}
	
	
	
	//내원한 모든 환자에게 푸시알림
	@RequestMapping(value="/allPush")
	public void allSendPushMessage(@RequestBody Map<String, Object> data)  {
		
		System.out.println("in allSendPushMessage controller");
		
		String title = data.get("title").toString();
		String text = data.get("text").toString();
		String token = data.get("token").toString();
		
		
try {    
		
		String path = "C:\\Users\\SeungMin\\Downloads\\retest-f2877-firebase-adminsdk-xw1g2-57722f5508.json";           
		String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
		String[] SCOPES = { MESSAGING_SCOPE };
    
		GoogleCredential googleCredential = GoogleCredential
                        	.fromStream(new FileInputStream(path))
                        	.createScoped(Arrays.asList(SCOPES));
		googleCredential.refreshToken();
                        
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type" , MediaType.APPLICATION_JSON_VALUE);
		headers.add("Authorization", "Bearer " + googleCredential.getAccessToken());
    
		JSONObject notification = new JSONObject();
		notification.put("body", text);
		notification.put("title", title);
    
		JSONObject message = new JSONObject();
		message.put("token", token);
		message.put("notification", notification);
    
		JSONObject jsonParams = new JSONObject();
		jsonParams.put("message", message);
    
		HttpEntity<JSONObject> httpEntity = new HttpEntity<JSONObject>(jsonParams, headers);
		RestTemplate rt = new RestTemplate();            
    
		ResponseEntity<String> res = rt.exchange("https://fcm.googleapis.com/v1/projects/retest-f2877/messages:send"
				, HttpMethod.POST
				, httpEntity
				, String.class);

		if (res.getStatusCode() != HttpStatus.OK) {
			logger.debug("FCM-Exception");
			logger.debug(res.getStatusCode().toString());
			logger.debug(res.getHeaders().toString());
			logger.debug(res.getBody().toString());
        
		} else {
			logger.debug(res.getStatusCode().toString());
			logger.debug(res.getHeaders().toString());
			logger.debug(res.getBody().toLowerCase());
        
		}
	} catch (Exception e) {
		e.printStackTrace();
	}

		
	}

	
	
}