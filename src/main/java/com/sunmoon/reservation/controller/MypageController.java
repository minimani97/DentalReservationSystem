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
	
	// 아이디에 등록된 가족 불러오기
	@RequestMapping("/loadFamilyInfo")
	public List<PatientInfo> loadFamilyInfo(@RequestBody Map<String, Object> data) {
		int user_code = Integer.parseInt(data.get("user_code").toString());
		
		return mypageService.loadFamilyInfo(user_code);
	}
	
	// 비밀번호 변경하기
	@RequestMapping("/changePassword")
	public String changePassword(@RequestBody Map<String, Object> data) {
		int p_code = Integer.parseInt(data.get("user_code").toString());
		String password = data.get("password").toString();
		
		return mypageService.changePassword(p_code, password);
	}
	
	// 정보 수정하기
	@RequestMapping("/editMyInfo")
	public PatientInfo editMyInfo(@RequestBody Map<String, Object> data) {
		PatientInfo info = new PatientInfo();
		
		info.setP_code(Integer.parseInt(data.get("p_code").toString()));
		info.setAddr(data.get("addr").toString());
		info.setTel(data.get("tel").toString());
		
		return mypageService.editMyInfo(info);
	}
	
	// 가족 정보 불러오기
	@RequestMapping("/loadFamilyList")
	public List<PatientInfo> loadFamilyList(@RequestBody Map<String, Object> data) {
		int p_code = Integer.parseInt(data.get("user_code").toString());
		
		return mypageService.loadFamilyList(p_code);
	}
	
	// 가족 등록하기
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
	
	// 가족 정보 삭제하기
	@ResponseBody
	@RequestMapping("/deleteFamilyInfo")
	public void deleteFamilyInfo(@RequestBody Map<String, Object> data) {
		int p_code = Integer.parseInt(data.get("p_code").toString());
		
		mypageService.deleteFamilyInfo(p_code);
	}
	
	// 예약 리스트 불러오기
	@RequestMapping("/loadReservationList")
	public List<ReservationList> loadReservationList(@RequestBody Map<String, Object> data) {
		int p_code = Integer.parseInt(data.get("p_code").toString());
		
		return mypageService.loadReservationList(p_code);
	}
	
	// 해당 예약 정보 불러오기
	@RequestMapping("/getReservationInformation")
	public List<ReservationInfo> getReservationInformation(@RequestBody Map<String, Object> data) {
		int num = Integer.parseInt(data.get("num").toString());
		
		return mypageService.getReservationInformation(num);
	}
	
	// 해당 예약 취소하기
	@ResponseBody
	@RequestMapping("/cancelReservation")
	public void cancelReservation(@RequestBody Map<String, Object> data) {
		int num = Integer.parseInt(data.get("num").toString());
		
		mypageService.cancelReservation(num);
	}
	
	// 환자 홈 화면의 유저 이름 띄우기를 위한 이름 값 가져오기
	@ResponseBody
	@RequestMapping(value="/loadUserName", produces="application/text;charset=utf8")
	public String loadUserName(@RequestBody Map<String, Object> data) {
		int user_code = Integer.parseInt(data.get("user_code").toString());
		
		return mypageService.loadUserName(user_code);
	}
}
