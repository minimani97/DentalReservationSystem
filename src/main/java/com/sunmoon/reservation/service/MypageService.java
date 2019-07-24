package com.sunmoon.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunmoon.reservation.dao.MypageDao;
import com.sunmoon.reservation.model.PatientInfo;
import com.sunmoon.reservation.model.ReservationInfo;
import com.sunmoon.reservation.model.ReservationList;

@Service
public class MypageService {
	private MypageDao mypageDao;
	
	@Autowired
	public void setMypageDao(MypageDao mypageDao) {
		this.mypageDao = mypageDao;
	}
	
	// 아이디에 등록된 가족 불러오기
	public List<PatientInfo> loadFamilyInfo(int user_code) {
		return mypageDao.loadFamilyInfo(user_code);
	}
	
	// 비밀번호 변경하기
	public String changePassword(int p_code, String password) {		
		return mypageDao.changePassword(p_code, password);
	}
	
	// 정보 수정하기
	public PatientInfo editMyInfo(PatientInfo info) {
		return mypageDao.editMyInfo(info);
	}
	
	// 가족 정보 불러오기
	public List<PatientInfo> loadFamilyList(int p_code) {
		return mypageDao.loadFamilyList(p_code);
	}
	
	// 가족 등록하기
	public void registerFamilyInfo(PatientInfo info) {
		mypageDao.registerFamilyInfo(info);
	}
	
	// 가족 정보 삭제하기
	public void deleteFamilyInfo(int p_code) {
		mypageDao.deleteFamilyInfo(p_code);
	}
	
	// 예약 리스트 불러오기
	public List<ReservationList> loadReservationList(int p_code) {
		return mypageDao.loadReservationList(p_code);
	}
	
	// 해당 예약 정보 불러오기
	public List<ReservationInfo> getReservationInformation(int num) {
		return mypageDao.getReservationInformation(num);
	}
	
	// 해당 예약 취소하기
	public void cancelReservation(int num) {
		mypageDao.cancelReservation(num);
	}
	
	// 환자 홈 화면의 유저 이름 띄우기를 위한 이름 값 가져오기
	public String loadUserName(int user_code) {
		return mypageDao.loadUserName(user_code);
	}
}
