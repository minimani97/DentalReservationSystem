package com.sunmoon.reservation.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunmoon.reservation.dao.RegisterDao;
import com.sunmoon.reservation.model.ClinicMapInfo;
import com.sunmoon.reservation.model.DentistInfo;
import com.sunmoon.reservation.model.PatientInfo;

@Service
public class RegisterService {
	private static final Logger logger = LoggerFactory.getLogger(RegisterService.class);
	private RegisterDao registerDao;
	
	@Autowired
	public void registerDao(RegisterDao registerDao) {
		this.registerDao = registerDao;
	}
	
	public List<ClinicMapInfo> getClinicList() {
		return registerDao.getClinicList();
	}
	
	public int isDuplicateId(String inputIdValue) {
		return registerDao.isDuplicateId(inputIdValue);
	}
	
	public void patientUserRegister(PatientInfo info) {
		registerDao.patientUserRegister(info);
	}
	
	public void dentistUserRegister(DentistInfo info) {
		registerDao.dentistUserRegister(info);
	}
}
