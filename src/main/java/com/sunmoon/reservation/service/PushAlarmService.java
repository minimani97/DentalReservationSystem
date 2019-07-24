package com.sunmoon.reservation.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunmoon.reservation.dao.AdminDao;
import com.sunmoon.reservation.dao.PushAlarmDao;
import com.sunmoon.reservation.model.AdminInfo;
import com.sunmoon.reservation.model.ReservationInfo;


@Service
public class PushAlarmService {
	
	private PushAlarmDao pushAlarmDao;

	@Autowired
	public void setPushAlarmDao(PushAlarmDao pushAlarmDao) {
		this.pushAlarmDao = pushAlarmDao;
	}
	
	public List<ReservationInfo> pushInfoService() {
	
		return pushAlarmDao.pushInfoDao();
	}
	
	public List<ReservationInfo> allPushInfoService() {
		
		return pushAlarmDao.allPushInfoDao();
	}
	
}
