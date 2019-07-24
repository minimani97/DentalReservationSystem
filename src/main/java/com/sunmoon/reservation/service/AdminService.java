package com.sunmoon.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunmoon.reservation.dao.AdminDao;
import com.sunmoon.reservation.model.AdminInfo;


@Service
public class AdminService {
	
	private AdminDao adminDao;

	@Autowired
	public void setAdiminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	public List<AdminInfo> dentistInfoService() {
		return adminDao.dentistInfoDao();
	}
	
	public List<AdminInfo> PatientInfoService() {
		return adminDao.PatientInfoDao();
	}
	
	public List<AdminInfo> ClinicInfoService() {
		return adminDao.ClinicInfoDao();
	}
	
	public void CertifyService(String c_name) {
		adminDao.CertifyDao(c_name);
	}
	
	
}
