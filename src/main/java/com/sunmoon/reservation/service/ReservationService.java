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
	
	// ������ ������ ���� ���� �ҷ�����
	public List<FieldInfo> loadFieldInfo(String selectedClinic) {
		logger.info("Service_loadFieldInfo Function");
		return reservationDao.loadFieldInfo(selectedClinic);
	}
	
	// ������� �ڵ� �ҷ�����
	public int getFcode(String f_name) {
		logger.info("Service_getFcode Function");
		return reservationDao.getFcode(f_name);
	}
	
	// ������ ����о߿� ���� �Ƿ��� ���� �ҷ�����
	public List<DentistInfo> loadDentistInfo(String selectedClinic, String selectedField) {
		logger.info("Service_loadDentistInfo Function");
		return reservationDao.loadDentistInfo(selectedClinic, selectedField);
	}
	
	// �Ƿ��� �ڵ� �ҷ�����
	public int getDcode(String d_name) {
		logger.info("Service_getDcode Function");
		return reservationDao.getDcode(d_name);
	}
	
	// �̹� ����� �ð� ���� �ҷ�����
	public List<AlreadyReservedTimeInfo> getAlreadyReservedTimeInfo(String date, int d_code) {
		logger.info("Service_getAlreadyReservedTimeInfo Function");
		return reservationDao.getAlreadyReservedTimeInfo(date, d_code);
	}
	
	// ġ��/�������/�Ƿ��� �ڵ�� ġ����/��������/�Ƿ����� �ҷ�����
	public List<GetCFDName> getNames(int c_code, int f_code, int d_code) {
		logger.info("Service_getNames Function");
		return reservationDao.getNames(c_code, f_code, d_code);
	}
	
	// ��ϵ� ���� ���� ��������
	public List<PatientInfo> getFamilyList(int user_code) {
		logger.info("Service_getFamilyList Function");
		return reservationDao.getFamilyList(user_code);
	}
	
	// �ش� ȯ�� ���� �ҷ�����
	public List<PatientInfo> getPatientInfo(int p_code) {
		logger.info("Service_getPatientInfo Function");
		return reservationDao.getPatientInfo(p_code);
	}
	
	// ���� ���� �����ϱ�
	public void saveReservationInfo(ReservationInfo info) {
		logger.info("Service_saveReservationInfo Function");
		reservationDao.saveReservationInfo(info);
	}
}
