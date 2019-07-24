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
	
	//�Ƿ��� ���(dentist_register.jsp)
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
	
	//�Ƿ��� ��� �ޱ�(dentist_list.jps)
	@RequestMapping("/DList")
	public List<Dentist> DList(@RequestBody Map<String, Object> List) throws Exception {

		logger.info("Controller - RequestMapping /DList.");
		
		int c_code = Integer.parseInt(List.get("c_code").toString());

		return dregisterService.DList(c_code);
	}
	
	//�Ƿ��� ����� ��� �ޱ�(dentist_list.jps)
	@RequestMapping("/d_field_List")
	public List<D_field_List> d_field_List(@RequestBody Map<String, Object> List) throws Exception {

		logger.info("Controller - RequestMapping /d_field_List.");
			
		int c_code = Integer.parseInt(List.get("c_code").toString());
		int d_code = Integer.parseInt(List.get("d_code").toString());
			
		return dregisterService.d_field_List(c_code, d_code);
	}
	
	//���ϳ�¥ ȯ�� �ޱ�(patient_list.jps)
	@RequestMapping("/Today_PList")
	public List<Today_PList> today_PList(@RequestBody Map<String, Object> List) throws Exception {

		logger.info("Controller - RequestMapping /today_PList.");
				
		int c_code = Integer.parseInt(List.get("c_code").toString());
		int d_code = Integer.parseInt(List.get("d_code").toString());
				
		return dregisterService.today_PList(c_code, d_code);
	}
	
	//ȯ�� �Ѹ��� ���� ��� ����Ʈ ��������(patient_record.jps)
	@RequestMapping("/One_Patient_List")
	public List<Patient_record> one_Patient_List(@RequestBody Map<String, Object> List) throws Exception {

		logger.info("Controller - RequestMapping /one_Patient_List.");
				
		int p_code = Integer.parseInt(List.get("p_code").toString());
		int c_code = Integer.parseInt(List.get("c_code").toString());
				
		return dregisterService.one_Patient_List(p_code, c_code);
	}
	
	//���� ���� content ����(patient_record.jsp)
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
	
	//���� ���� content ����(patient_record.jsp)
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
	
	//DB���� ġ�� select�±��� option �����(patient_transfer.jsp)
	@RequestMapping("/makeOption")
	public List<Clinic> makeOption() throws Exception {

		logger.info("Controller - RequestMapping /makeOption.");
		
		return dregisterService.makeOption();
		 
	}
	
	//DB���� ġ�� select�±��� option �����(patient_transfer.jsp)
	@RequestMapping("/makeDentistOption")
	public List<Dentist> makeDentistOption(@RequestBody Map<String, Object> data) throws Exception {

		logger.info("Controller - RequestMapping /makeDentistOption.");
		String selectClinic = data.get("selectClinic").toString();
		String selectField = data.get("selectField").toString();
			logger.info("selectClinic + selectField ��:" + selectClinic+","+ selectField);
			
		return dregisterService.makeDentistOption(selectClinic, selectField);
			 
	}
	
	//Ʈ�������� ȯ�� select �±��� option �����(patient_transfer.jsp)
	@RequestMapping("/makePatientOption")
	public List<Today_PList> makePatientOption(@RequestBody Map<String, Object> data) throws Exception {

		logger.info("Controller - RequestMapping /makeDentistOption.");
		int c_code = Integer.parseInt(data.get("c_code").toString());
		
		return dregisterService.makePatientOption(c_code);
				 
	}
	//������ ȯ�ڿ� �´� info��������_readonly(patient_record.jsp)
	@RequestMapping("/getPatientInformation")
	public List<Today_PList> getPatientInformation(@RequestBody Map<String, Object> data) throws Exception {

		logger.info("Controller - RequestMapping /getPatientInformation.");
		int c_code = Integer.parseInt(data.get("c_code").toString());
		String p_name = data.get("p_name").toString();
		int p_code = Integer.parseInt(data.get("p_code").toString());
		
		return dregisterService.getPatientInformation(c_code, p_name, p_code);
				 
	}
	//ȯ�� Ʈ������ ���� ��ư(patient_record.jsp)
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
	//���� Ʈ������
	@RequestMapping("/receiveTransfer")
	public List<Patient> receiveTransfer(@RequestBody Map<String, Object> data) throws Exception {

		logger.info("Controller - RequestMapping /receiveTransfer.");
		int c_code = Integer.parseInt(data.get("c_code").toString());
		int d_code = Integer.parseInt(data.get("d_code").toString());
		
		return dregisterService.receiveTransfer(c_code, d_code);
				 
	}
	//Ʈ�������� ���� select �±��� option �����(patient_transfer.jsp)
	@RequestMapping("/makeReservationOption")
	public List<Today_PList> makeReservationOption(@RequestBody Map<String, Object> data) throws Exception {

		logger.info("Controller - RequestMapping /makeReservationOption.");
		int c_code = Integer.parseInt(data.get("c_code").toString());
		int d_code = Integer.parseInt(data.get("d_code").toString());
		int selected_Patient = Integer.parseInt(data.get("selected_Patient").toString());
		
		return dregisterService.makeReservationOption(c_code, d_code, selected_Patient);
				 
	}
	//������ ���ῡ �´� info��������_readonly(patient_timeAlter.jsp)
	@RequestMapping("/getReservationInfo")
	public List<Today_PList> getReservationInfo(@RequestBody Map<String, Object> data) throws Exception {

		logger.info("Controller - RequestMapping /getReservationInfo.");
		int c_code = Integer.parseInt(data.get("c_code").toString());
		int num = Integer.parseInt(data.get("num").toString());
		
		return dregisterService.getReservationInfo(c_code, num);
				 
	}
	
	//��ó�� �� ��������(patient_record.jsp)
	@RequestMapping("/getPriscription") public List<Medicine>
	getPriscription(@RequestBody Map<String, Object> data) throws Exception {
	  
	logger.info("Controller - RequestMapping /getPriscription."); int num =
	Integer.parseInt(data.get("num").toString());
	  
	return dregisterService.getPriscription(num);
	  
	}
	
	//���� ��� ��ư(close_notice.jsp)
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


