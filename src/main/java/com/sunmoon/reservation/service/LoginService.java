package com.sunmoon.reservation.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunmoon.reservation.dao.LoginDao;
import com.sunmoon.reservation.model.DentistInfo;
import com.sunmoon.reservation.model.UserInfo;

@Service
public class LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
	private LoginDao loginDao;
	   
	@Autowired
	public void setMypageDao(LoginDao loginDao) {
		this.loginDao = loginDao;   
	}
	
	public void saveUserInfo(UserInfo info) {
		logger.info("Service_saveUserInfo Function");
		loginDao.saveUserInfo(info);
	}
	
	public List<UserInfo> goLogin(UserInfo info) {
		logger.info("Service_goLogin Function");
		return loginDao.goLogin(info);
	}
	
	public UserInfo sessionP(String id){
		   return loginDao.sessionP(id);
		}

	public DentistInfo sessionD(String id){
		logger.info("여기는 서비스 서비스다 오바 Function");
		logger.info(id);
		   return loginDao.sessionD(id);
		}
	
}
