package com.sunmoon.reservation.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunmoon.reservation.dao.DregisterDao;
import com.sunmoon.reservation.model.*;

@Service
public class DregisterService {

	private static final Logger logger = LoggerFactory.getLogger(DregisterService.class);
	private DregisterDao dregisterDao;
	
	@Autowired
	public void setDregister(DregisterDao dregisterDao) {
		this.dregisterDao = dregisterDao;
	}
	
	//의료진 등록
	public void sendDregister(String profile, String d_name, String d_tel, String email, String c_code, String d_field, String edu, String career) {
		logger.info("sendDregister/ service");
		dregisterDao.Dregister(profile, d_name, d_tel, email, c_code, d_field, edu, career);
	}
	//의료진 리스트 가져오기
	public List<Dentist> DList(int c_code){
		logger.info("Service - RequestMapping /DList.");
		return dregisterDao.DList(c_code);
	}
	//의료진 진료과 리스트 가져오기
	public List<D_field_List> d_field_List(int c_code,int d_code){
		logger.info("Service - RequestMapping /d_field_List.");
		return dregisterDao.d_field_List(c_code, d_code);
	}
	//당일날짜 환자 받기(patient_list.jps)
	public List<Today_PList> today_PList(int c_code,int d_code){
		logger.info("Service - RequestMapping /today_PList.");
		return dregisterDao.today_PList(c_code, d_code);
	}
	//환자 한명의 진료 기록 리스트 가져오기(patient_record.jps)
	public List<Patient_record> one_Patient_List(int p_code,int c_code){
		logger.info("Service - RequestMapping /One_Patient_List.");
		return dregisterDao.one_Patient_List(p_code, c_code);
	}
	//개인 진료 content 수정(patient_record.jsp)
	public void AlterContent(int p_num,
			String getContent/* , int m_code, String m_name, String dose,int frequency_day, int day */) {
		logger.info("Service - RequestMapping /AlterContent.");
		dregisterDao.AlterContent(p_num, getContent/* , m_code, m_name, dose, frequency_day, day */);
	}
	//개인 약 추가(patient_record.jsp)
	public void addMedicine(int p_num, int m_code, String m_name, String dose,int frequency_day, int day ) {
		logger.info("Service - RequestMapping /addMedicine.");
		dregisterDao.addMedicine(p_num, m_code, m_name, dose, frequency_day, day );
	}
	//DB에서 치과 select태그의 option 만들기(patient_transfer.jsp)
	public List<Clinic> makeOption () {
		logger.info("Service - RequestMapping /makeOption.");
		return dregisterDao.makeOption();
	}
	//선택한 병원과 진료과에 맞는 의료진 select-option 띄우기(patient_transfer.jsp)
	public List<Dentist> makeDentistOption(String selectClinic, String selectField){
		logger.info("Service - RequestMapping /makeDentistOption.");
		return dregisterDao.makeDentistOption(selectClinic, selectField);
	}
	//트랜스퍼할 환자 select 태그의 option 만들기(patient_transfer.jsp)
	public List<Today_PList> makePatientOption(int c_code){
		logger.info("Service - RequestMapping /makePatientOption.");
		return dregisterDao.makePatientOption(c_code);
	}
	//선택한 환자에 맞는 info가져오기_readonly(patient_record.jsp)
	public List<Today_PList> getPatientInformation(int c_code, String p_name, int p_code){
		logger.info("Service - RequestMapping /getPatientInformation.");
		return dregisterDao.getPatientInformation(c_code, p_name, p_code);
	}
	//환자 트랜스퍼 전송 버튼(patient_record.jsp)
	public void transferSendButton (int sendClinic, int sendDentist, String receiveClinic, String transfer_r_field, String receiveDentist, int p_code, String selectedStartTime, String selectedEndTime, int ReservationSelect, String selectedDate) {
		logger.info("Service - RequestMapping /transferSendButton.");
		dregisterDao.transferSendButton(sendClinic, sendDentist, receiveClinic, transfer_r_field, receiveDentist, p_code, selectedStartTime, selectedEndTime, ReservationSelect, selectedDate);
	}
	//받은 트랜스퍼
	public List<Patient> receiveTransfer(int c_code, int d_code){
		logger.info("Service - RequestMapping /receiveTransfer.");
		return dregisterDao.receiveTransfer(c_code, d_code);
	}
	//트랜스퍼할 진료 select 태그의 option 만들기(patient_transfer.jsp)
	public List<Today_PList> makeReservationOption(int c_code, int d_code, int selected_Patient){
		logger.info("Service - RequestMapping /makeReservationOption.");
		return dregisterDao.makeReservationOption(c_code, d_code, selected_Patient);
	}
	//선택한 진료에 맞는 info가져오기_readonly(patient_timeAlter.jsp)
	public List<Today_PList> getReservationInfo(int c_code, int num){
		logger.info("Service - RequestMapping /getReservationInfo.");
		return dregisterDao.getReservationInfo(c_code, num);
	}
	//약처방 값 가져오기(patient_record.jsp) 
	public List<Medicine> getPriscription(int  num){ 
		logger.info("Service - RequestMapping /getPriscription."); 
		return dregisterDao.getPriscription(num); 
	}
	//휴진 등록 버튼(close_notice.jsp)
	public void closeButton (String closeDate, String start_time, String end_time, String reason, int d_code) {
		logger.info("Service - RequestMapping /closeButton.");
		dregisterDao.closeButton(closeDate, start_time, end_time, reason, d_code);
	}
	 
}
