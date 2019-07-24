package com.sunmoon.reservation.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunmoon.reservation.model.*;
import com.sunmoon.reservation.service.DregisterService;

@RestController
public class DregisterController {
	private static final Logger logger = LoggerFactory.getLogger(DregisterController.class);
	private DregisterService dregisterService;
	
	@Autowired
	public void setDRegisterService(DregisterService dregisterService) {
		this.dregisterService = dregisterService;
	}
	
	//의료진 등록(dentist_register.jsp)
	@RequestMapping("/DRegister")
	public void sendDRegister(@RequestBody Map<String, Object> Dregister) throws Exception {

		logger.info("Controller - RequestMapping /sendDRegister.");
        
		String profile = Dregister.get("profile").toString();
		String d_name = Dregister.get("d_name").toString();
		String d_tel = Dregister.get("d_tel").toString();
		String email = Dregister.get("email").toString();
		String c_code = Dregister.get("c_code").toString();
		String d_field = Dregister.get("d_field").toString();
		String edu = Dregister.get("edu").toString();
		String career = Dregister.get("career").toString();
		
		dregisterService.sendDregister(profile, d_name, d_tel, email, c_code, d_field, edu, career);
		 
	}
	
	//의료진 목록 받기(dentist_list.jps)
	@RequestMapping("/DList")
	public List<Dentist> DList(@RequestBody Map<String, Object> List) throws Exception {

		logger.info("Controller - RequestMapping /DList.");
		
		int c_code = Integer.parseInt(List.get("c_code").toString());

		return dregisterService.DList(c_code);
	}
	
	//의료진 진료과 목록 받기(dentist_list.jps)
	@RequestMapping("/d_field_List")
	public List<D_field_List> d_field_List(@RequestBody Map<String, Object> List) throws Exception {

		logger.info("Controller - RequestMapping /d_field_List.");
			
		int c_code = Integer.parseInt(List.get("c_code").toString());
		int d_code = Integer.parseInt(List.get("d_code").toString());
			
		return dregisterService.d_field_List(c_code, d_code);
	}
	
	//당일날짜 환자 받기(patient_list.jps)
	@RequestMapping("/Today_PList")
	public List<Today_PList> today_PList(@RequestBody Map<String, Object> List) throws Exception {

		logger.info("Controller - RequestMapping /today_PList.");
				
		int c_code = Integer.parseInt(List.get("c_code").toString());
		int d_code = Integer.parseInt(List.get("d_code").toString());
				
		return dregisterService.today_PList(c_code, d_code);
	}
	
	//환자 한명의 진료 기록 리스트 가져오기(patient_record.jps)
	@RequestMapping("/One_Patient_List")
	public List<Patient_record> one_Patient_List(@RequestBody Map<String, Object> List) throws Exception {

		logger.info("Controller - RequestMapping /one_Patient_List.");
				
		int p_code = Integer.parseInt(List.get("p_code").toString());
		int c_code = Integer.parseInt(List.get("c_code").toString());
				
		return dregisterService.one_Patient_List(p_code, c_code);
	}
	
	//개인 진료 content 수정(patient_record.jsp)
	@RequestMapping("/AlterContent")
	public void AlterContent(@RequestBody Map<String, Object> data) throws Exception {

		logger.info("Controller - RequestMapping /AlterContent.");
        
		int p_num = Integer.parseInt(data.get("p_num").toString());
		String getContent = data.get("getContent").toString();
		/*
		 * int m_code = Integer.parseInt(data.get("m_code").toString()); String m_name =
		 * data.get("m_name").toString(); String dose = data.get("dose").toString(); int
		 * frequency_day = Integer.parseInt(data.get("frequency_day").toString()); int
		 * day = Integer.parseInt(data.get("day").toString());
		 */
		
		dregisterService.AlterContent(p_num, getContent/* , m_code, m_name, dose, frequency_day, day */);
		 
	}
	
	//개인 진료 content 수정(patient_record.jsp)
	@RequestMapping("/addMedicine")
	public void addMedicine(@RequestBody Map<String, Object> data) throws Exception {

		logger.info("Controller - RequestMapping /addMedicine.");
	        
		int p_num = Integer.parseInt(data.get("p_num").toString());
		int m_code = Integer.parseInt(data.get("m_code").toString()); 
		String m_name = data.get("m_name").toString();
		String dose = data.get("dose").toString(); 
		int frequency_day = Integer.parseInt(data.get("frequency_day").toString()); 
		int day = Integer.parseInt(data.get("day").toString());
			 
		dregisterService.addMedicine(p_num , m_code, m_name, dose, frequency_day, day);
			 
		}
	
	//DB에서 치과 select태그의 option 만들기(patient_transfer.jsp)
	@RequestMapping("/makeOption")
	public List<Clinic> makeOption() throws Exception {

		logger.info("Controller - RequestMapping /makeOption.");
		
		return dregisterService.makeOption();
		 
	}
	
	//DB에서 치과 select태그의 option 만들기(patient_transfer.jsp)
	@RequestMapping("/makeDentistOption")
	public List<Dentist> makeDentistOption(@RequestBody Map<String, Object> data) throws Exception {

		logger.info("Controller - RequestMapping /makeDentistOption.");
		String selectClinic = data.get("selectClinic").toString();
		String selectField = data.get("selectField").toString();
			logger.info("selectClinic + selectField 값:" + selectClinic+","+ selectField);
			
		return dregisterService.makeDentistOption(selectClinic, selectField);
			 
	}
	
	//트랜스퍼할 환자 select 태그의 option 만들기(patient_transfer.jsp)
	@RequestMapping("/makePatientOption")
	public List<Today_PList> makePatientOption(@RequestBody Map<String, Object> data) throws Exception {

		logger.info("Controller - RequestMapping /makeDentistOption.");
		int c_code = Integer.parseInt(data.get("c_code").toString());
		
		return dregisterService.makePatientOption(c_code);
				 
	}
	//선택한 환자에 맞는 info가져오기_readonly(patient_record.jsp)
	@RequestMapping("/getPatientInformation")
	public List<Today_PList> getPatientInformation(@RequestBody Map<String, Object> data) throws Exception {

		logger.info("Controller - RequestMapping /getPatientInformation.");
		int c_code = Integer.parseInt(data.get("c_code").toString());
		String p_name = data.get("p_name").toString();
		int p_code = Integer.parseInt(data.get("p_code").toString());
		
		return dregisterService.getPatientInformation(c_code, p_name, p_code);
				 
	}
	//환자 트랜스퍼 전송 버튼(patient_record.jsp)
	@RequestMapping("/transferSendButton")
	public void transferSendButton(@RequestBody Map<String, Object> data) throws Exception {

		logger.info("Controller - RequestMapping /transferSendButton.");
        
		int sendClinic = Integer.parseInt(data.get("sendClinic").toString());
		int sendDentist = Integer.parseInt(data.get("sendDentist").toString());
		String receiveClinic = data.get("receiveClinic").toString();
		String transfer_r_field = data.get("transfer_r_field").toString();
		String receiveDentist = data.get("receiveDentist").toString();
		int p_code = Integer.parseInt(data.get("p_code").toString());
		String selectedStartTime = data.get("selectedStartTime").toString();
		String selectedEndTime = data.get("selectedEndTime").toString();
		int ReservationSelect = Integer.parseInt(data.get("ReservationSelect").toString());
		String selectedDate = data.get("selectedDate").toString();
		
		dregisterService.transferSendButton(sendClinic, sendDentist, receiveClinic, transfer_r_field, receiveDentist, p_code, selectedStartTime, selectedEndTime, ReservationSelect, selectedDate);
		 
	}
	//받은 트랜스퍼
	@RequestMapping("/receiveTransfer")
	public List<Patient> receiveTransfer(@RequestBody Map<String, Object> data) throws Exception {

		logger.info("Controller - RequestMapping /receiveTransfer.");
		int c_code = Integer.parseInt(data.get("c_code").toString());
		int d_code = Integer.parseInt(data.get("d_code").toString());
		
		return dregisterService.receiveTransfer(c_code, d_code);
				 
	}
	//트랜스퍼할 진료 select 태그의 option 만들기(patient_transfer.jsp)
	@RequestMapping("/makeReservationOption")
	public List<Today_PList> makeReservationOption(@RequestBody Map<String, Object> data) throws Exception {

		logger.info("Controller - RequestMapping /makeReservationOption.");
		int c_code = Integer.parseInt(data.get("c_code").toString());
		int d_code = Integer.parseInt(data.get("d_code").toString());
		int selected_Patient = Integer.parseInt(data.get("selected_Patient").toString());
		
		return dregisterService.makeReservationOption(c_code, d_code, selected_Patient);
				 
	}
	//선택한 진료에 맞는 info가져오기_readonly(patient_timeAlter.jsp)
	@RequestMapping("/getReservationInfo")
	public List<Today_PList> getReservationInfo(@RequestBody Map<String, Object> data) throws Exception {

		logger.info("Controller - RequestMapping /getReservationInfo.");
		int c_code = Integer.parseInt(data.get("c_code").toString());
		int num = Integer.parseInt(data.get("num").toString());
		
		return dregisterService.getReservationInfo(c_code, num);
				 
	}
	
	//약처방 값 가져오기(patient_record.jsp)
	@RequestMapping("/getPriscription") public List<Medicine>
	getPriscription(@RequestBody Map<String, Object> data) throws Exception {
	  
	logger.info("Controller - RequestMapping /getPriscription."); int num =
	Integer.parseInt(data.get("num").toString());
	  
	return dregisterService.getPriscription(num);
	  
	}
	
	//휴진 등록 버튼(close_notice.jsp)
	@RequestMapping("/closeButton")
	public void closeButton(@RequestBody Map<String, Object> data) throws Exception {

		logger.info("Controller - RequestMapping /closeButton.");
        
		String closeDate = data.get("closeDate").toString();
		String start_time = data.get("start_time").toString();
		String end_time = data.get("end_time").toString();
		String reason = data.get("reason").toString();
		int d_code = Integer.parseInt(data.get("d_code").toString());
		
		dregisterService.closeButton(closeDate, start_time, end_time, reason, d_code);
		 
	}
	 
}


