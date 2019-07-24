package com.sunmoon.reservation.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunmoon.reservation.model.AlreadyReservedTimeInfo;
import com.sunmoon.reservation.model.ClinicMapInfo;
import com.sunmoon.reservation.model.DentistInfo;
import com.sunmoon.reservation.model.PatientInfo;
import com.sunmoon.reservation.model.FieldInfo;
import com.sunmoon.reservation.model.GetCFDName;
import com.sunmoon.reservation.model.ReservationInfo;
import com.sunmoon.reservation.service.ReservationService;

@RestController
public class ReservationController {
	private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);
	private ReservationService reservationService;
	
	@Autowired
	public void setReservationService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@RequestMapping("/clinicselect")
	public List<ClinicMapInfo> getMapInfo() {
	      
	   logger.info("Controller_getMapInfo Function");
	   return reservationService.getMapInfoService();
	}
	  
	@RequestMapping("/clinicselect2")
	public List<ClinicMapInfo> myLocation() {
	      
	   logger.info("Controller_getMapInfo Function");
	   return reservationService.getMapInfoService();
    }
	
	// ������ ������ ���� ���� �ҷ�����
	@RequestMapping("/loadFieldInfo")
	public List<FieldInfo> loadFieldInfo(@RequestBody Map<String, Object> data) {
		logger.info("Controller_loadFieldInfo Function");
		
		String selectedClinic = data.get("selectedClinic").toString();
		
		return reservationService.loadFieldInfo(selectedClinic);
	}
	
	// ������� �ڵ� �ҷ�����
	@RequestMapping("/getFcode")
	public int getFcode(@RequestBody Map<String, Object> data) {
		logger.info("Controller_getFcode Function");
		
		String f_name = data.get("f_name").toString();
		
		return reservationService.getFcode(f_name);
	}
	
	// ������ ����о߿� ���� �Ƿ��� ���� �ҷ�����
	@RequestMapping("/loadDentistInfo")
	public List<DentistInfo> loadDentistInfo(@RequestBody Map<String, Object> data) {
		logger.info("Controller_loadDentistInfo Function");
		
		String selectedClinic = data.get("selectedClinic").toString();
		String selectedField = data.get("selectedField").toString();
		
		return reservationService.loadDentistInfo(selectedClinic, selectedField);
	}
	
	// �Ƿ��� �ڵ� �ҷ�����
	@RequestMapping("/getDcode")
	public int getDcode(@RequestBody Map<String, Object> data) {
		logger.info("Controller_getDcode Function");
		
		String d_name = data.get("d_name").toString();
		
		return reservationService.getDcode(d_name);
	}
	
	// �̹� ����� �ð� ���� �ҷ�����
	@RequestMapping("/getAlreadyReservedTime")
	public List<AlreadyReservedTimeInfo> getAlreadyReservedTimeInfo(@RequestBody Map<String, Object> data) {
		logger.info("Controller_getAlreadyReservedTimeInfo Function");
		
		String date = data.get("date").toString();
		int d_code = Integer.parseInt(data.get("d_code").toString());
		
		return reservationService.getAlreadyReservedTimeInfo(date, d_code);
	}
	
	// ġ��/�������/�Ƿ��� �ڵ�� ġ����/��������/�Ƿ����� �ҷ�����
	@RequestMapping("/getNames")
	public List<GetCFDName> getNames(@RequestBody Map<String, Object> data) {
		logger.info("Controller_getNames Function");
		
		int c_code = Integer.parseInt(data.get("c_code").toString());
		int f_code = Integer.parseInt(data.get("f_code").toString());
		int d_code = Integer.parseInt(data.get("d_code").toString());
		
		return reservationService.getNames(c_code, f_code, d_code);
	}
	
	// ��ϵ� ���� ���� ��������
	@RequestMapping("/getFamilyList")
	public List<PatientInfo> getFamilyList(@RequestBody Map<String, Object> data) {
		logger.info("Controller_getFamilyList Function");
		
		int user_code = Integer.parseInt(data.get("user_code").toString());
		
		return reservationService.getFamilyList(user_code);
	}
	
	// �ش� ȯ�� ���� �ҷ�����
	@RequestMapping("/getPatientInfo")
	public List<PatientInfo> getPatientInfo(@RequestBody Map<String, Object> data) {
		logger.info("Controller_getPatientInfo Function");
		
		int p_code = Integer.parseInt(data.get("p_code").toString());
		
		return reservationService.getPatientInfo(p_code);
	}
	
	// ���� ���� �����ϱ�
	@RequestMapping("/saveReservationInfo")
	public void saveReservationInfo(@RequestBody Map<String, Object> data) {
		logger.info("Controller_saveReservationInfo Function");
		
		ReservationInfo info = new ReservationInfo();
		info.setP_code(Integer.parseInt(data.get("p_code").toString()));
		info.setP_name(data.get("p_name").toString());
		info.setDate(data.get("date").toString());
		info.setS_time(data.get("s_time").toString());
		info.setE_time(data.get("e_time").toString());
		info.setR_field(Integer.parseInt(data.get("r_field").toString()));
		info.setC_code(Integer.parseInt(data.get("c_code").toString()));
		info.setD_code(Integer.parseInt(data.get("d_code").toString()));
		info.setExtra_info(data.get("extra_info").toString());
		info.setPersonal_info(data.get("personal_info").toString());
		
		reservationService.saveReservationInfo(info);
	}
}
