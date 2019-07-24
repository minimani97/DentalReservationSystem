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
	
	// ���� ���� �Ƿ��� ����Ʈ ��������
	public List<DentistInfo> loadDentistList(int c_code) {
		return scheduleDao.loadDentistList(c_code);
	}
	
	// ������ �ҷ�����
	public List<AlreadyReservedTimeInfo> getMySchedule(String date, int d_code) {
		return scheduleDao.getMySchedule(date, d_code);
	}
}
