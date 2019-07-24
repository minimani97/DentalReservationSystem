package com.sunmoon.reservation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunmoon.reservation.model.PatientInfo;
import com.sunmoon.reservation.model.ReservationInfo;
import com.sunmoon.reservation.model.ReservationList;
import com.sunmoon.reservation.service.MypageService;

@RestController
public class MypageController {
	private MypageService mypageService;
	
	@Autowired
	public void setMypageService(MypageService mypageService) {
		this.mypageService = mypageService;
	}
	
	// ���̵� ��ϵ� ���� �ҷ�����
	@RequestMapping("/loadFamilyInfo")
	public List<PatientInfo> loadFamilyInfo(@RequestBody Map<String, Object> data) {
		int user_code = Integer.parseInt(data.get("user_code").toString());
		
		return mypageService.loadFamilyInfo(user_code);
	}
	
	// ��й�ȣ �����ϱ�
	@RequestMapping("/changePassword")
	public String changePassword(@RequestBody Map<String, Object> data) {
		int p_code = Integer.parseInt(data.get("user_code").toString());
		String password = data.get("password").toString();
		
		return mypageService.changePassword(p_code, password);
	}
	
	// ���� �����ϱ�
	@RequestMapping("/editMyInfo")
	public PatientInfo editMyInfo(@RequestBody Map<String, Object> data) {
		PatientInfo info = new PatientInfo();
		
		info.setP_code(Integer.parseInt(data.get("p_code").toString()));
		info.setAddr(data.get("addr").toString());
		info.setTel(data.get("tel").toString());
		
		return mypageService.editMyInfo(info);
	}
	
	// ���� ���� �ҷ�����
	@RequestMapping("/loadFamilyList")
	public List<PatientInfo> loadFamilyList(@RequestBody Map<String, Object> data) {
		int p_code = Integer.parseInt(data.get("user_code").toString());
		
		return mypageService.loadFamilyList(p_code);
	}
	
	// ���� ����ϱ�
	@ResponseBody
	@RequestMapping("/registerFamilyInfo")
	public void registerFamilyInfo(@RequestBody Map<String, Object> data) {
		PatientInfo info = new PatientInfo();
		
		info.setP_code(Integer.parseInt(data.get("user_code").toString()));
		info.setName(data.get("name").toString());
		info.setRelationship(data.get("relationship").toString());
		info.setBirth(data.get("birth").toString());
		info.setAddr(data.get("addr").toString());
		info.setTel(data.get("tel").toString());
		
		mypageService.registerFamilyInfo(info);
	}
	
	// ���� ���� �����ϱ�
	@ResponseBody
	@RequestMapping("/deleteFamilyInfo")
	public void deleteFamilyInfo(@RequestBody Map<String, Object> data) {
		int p_code = Integer.parseInt(data.get("p_code").toString());
		
		mypageService.deleteFamilyInfo(p_code);
	}
	
	// ���� ����Ʈ �ҷ�����
	@RequestMapping("/loadReservationList")
	public List<ReservationList> loadReservationList(@RequestBody Map<String, Object> data) {
		int p_code = Integer.parseInt(data.get("p_code").toString());
		
		return mypageService.loadReservationList(p_code);
	}
	
	// �ش� ���� ���� �ҷ�����
	@RequestMapping("/getReservationInformation")
	public List<ReservationInfo> getReservationInformation(@RequestBody Map<String, Object> data) {
		int num = Integer.parseInt(data.get("num").toString());
		
		return mypageService.getReservationInformation(num);
	}
	
	// �ش� ���� ����ϱ�
	@ResponseBody
	@RequestMapping("/cancelReservation")
	public void cancelReservation(@RequestBody Map<String, Object> data) {
		int num = Integer.parseInt(data.get("num").toString());
		
		mypageService.cancelReservation(num);
	}
	
	// ȯ�� Ȩ ȭ���� ���� �̸� ���⸦ ���� �̸� �� ��������
	@ResponseBody
	@RequestMapping(value="/loadUserName", produces="application/text;charset=utf8")
	public String loadUserName(@RequestBody Map<String, Object> data) {
		int user_code = Integer.parseInt(data.get("user_code").toString());
		
		return mypageService.loadUserName(user_code);
	}
}
