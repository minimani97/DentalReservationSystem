package com.sunmoon.reservation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunmoon.reservation.model.AlreadyReservedTimeInfo;
import com.sunmoon.reservation.model.DentistInfo;
import com.sunmoon.reservation.service.ScheduleService;

@RestController
public class ScheduleController {
	private ScheduleService scheduleService;
	
	@Autowired
	public void setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}
	
	// ���� ���� �Ƿ��� ����Ʈ ��������
	@RequestMapping("/loadDentistList")
	public List<DentistInfo> loadDentistList(@RequestBody Map<String, Object> data) {
		int c_code = Integer.parseInt(data.get("c_code").toString());
		
		return scheduleService.loadDentistList(c_code);
	}
	
	// ������ �ҷ�����
	@RequestMapping("/getMySchedule")
	public List<AlreadyReservedTimeInfo> getMySchedule(@RequestBody Map<String, Object> data) {
		String date = data.get("date").toString();
		int d_code = Integer.parseInt(data.get("d_code").toString());
		
		return scheduleService.getMySchedule(date, d_code);
	}
}
