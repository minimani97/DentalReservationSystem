package com.sunmoon.reservation.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunmoon.reservation.dao.ReservationDao;
import com.sunmoon.reservation.model.AlreadyReservedTimeInfo;
import com.sunmoon.reservation.model.ClinicMapInfo;
import com.sunmoon.reservation.model.DentistInfo;
import com.sunmoon.reservation.model.PatientInfo;
import com.sunmoon.reservation.model.FieldInfo;
import com.sunmoon.reservation.model.GetCFDName;
import com.sunmoon.reservation.model.ReservationInfo;

@Service
public class ReservationService {
	private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);
	private ReservationDao reservationDao;
	
	@Autowired
	public void setReservationDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}
	
	public List<ClinicMapInfo> getMapInfoService() {
	    return reservationDao.getMapInfo();
	}
	
	// 선택한 병원의 진료 과목 불러오기
	public List<FieldInfo> loadFieldInfo(String selectedClinic) {
		logger.info("Service_loadFieldInfo Function");
		return reservationDao.loadFieldInfo(selectedClinic);
	}
	
	// 진료과목 코드 불러오기
	public int getFcode(String f_name) {
		logger.info("Service_getFcode Function");
		return reservationDao.getFcode(f_name);
	}
	
	// 선택한 진료분야에 따른 의료진 정보 불러오기
	public List<DentistInfo> loadDentistInfo(String selectedClinic, String selectedField) {
		logger.info("Service_loadDentistInfo Function");
		return reservationDao.loadDentistInfo(selectedClinic, selectedField);
	}
	
	// 의료진 코드 불러오기
	public int getDcode(String d_name) {
		logger.info("Service_getDcode Function");
		return reservationDao.getDcode(d_name);
	}
	
	// 이미 예약된 시간 정보 불러오기
	public List<AlreadyReservedTimeInfo> getAlreadyReservedTimeInfo(String date, int d_code) {
		logger.info("Service_getAlreadyReservedTimeInfo Function");
		return reservationDao.getAlreadyReservedTimeInfo(date, d_code);
	}
	
	// 치과/진료과목/의료진 코드로 치과명/진료과목명/의료진명 불러오기
	public List<GetCFDName> getNames(int c_code, int f_code, int d_code) {
		logger.info("Service_getNames Function");
		return reservationDao.getNames(c_code, f_code, d_code);
	}
	
	// 등록된 가족 정보 가져오기
	public List<PatientInfo> getFamilyList(int user_code) {
		logger.info("Service_getFamilyList Function");
		return reservationDao.getFamilyList(user_code);
	}
	
	// 해당 환자 정보 불러오기
	public List<PatientInfo> getPatientInfo(int p_code) {
		logger.info("Service_getPatientInfo Function");
		return reservationDao.getPatientInfo(p_code);
	}
	
	// 예약 정보 저장하기
	public void saveReservationInfo(ReservationInfo info) {
		logger.info("Service_saveReservationInfo Function");
		reservationDao.saveReservationInfo(info);
	}
}
