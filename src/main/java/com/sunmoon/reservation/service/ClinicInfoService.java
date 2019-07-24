package com.sunmoon.reservation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunmoon.reservation.dao.ClinicInfoDao;

@Service
public class ClinicInfoService {
	private static final Logger logger = LoggerFactory.getLogger(ClinicInfoService.class);
	private ClinicInfoDao clinicInfoDao;
	
	@Autowired
	public void setClinicInfoDao(ClinicInfoDao clinicInfoDao) {
		this.clinicInfoDao = clinicInfoDao;
	}
	
	public void setInfoService(String c_name, String c_tel, String addr_y, String addr_x, String c_license, String c_address) {
		clinicInfoDao.setInfoDao(c_name, c_tel, addr_y, addr_x, c_license, c_address);
	}

}
