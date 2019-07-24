package com.sunmoon.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunmoon.reservation.dao.ScheduleDao;
import com.sunmoon.reservation.model.AlreadyReservedTimeInfo;
import com.sunmoon.reservation.model.DentistInfo;

@Service
public class ScheduleService {
	private ScheduleDao scheduleDao;
	
	@Autowired
	public void setScheduleDao(ScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}
	
	// 같은 병원 의료진 리스트 가져오기
	public List<DentistInfo> loadDentistList(int c_code) {
		return scheduleDao.loadDentistList(c_code);
	}
	
	// 스케줄 불러오기
	public List<AlreadyReservedTimeInfo> getMySchedule(String date, int d_code) {
		return scheduleDao.getMySchedule(date, d_code);
	}
}
