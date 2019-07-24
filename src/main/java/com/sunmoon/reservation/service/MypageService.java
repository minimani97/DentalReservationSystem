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
	
	// ���̵� ��ϵ� ���� �ҷ�����
	public List<PatientInfo> loadFamilyInfo(int user_code) {
		return mypageDao.loadFamilyInfo(user_code);
	}
	
	// ��й�ȣ �����ϱ�
	public String changePassword(int p_code, String password) {		
		return mypageDao.changePassword(p_code, password);
	}
	
	// ���� �����ϱ�
	public PatientInfo editMyInfo(PatientInfo info) {
		return mypageDao.editMyInfo(info);
	}
	
	// ���� ���� �ҷ�����
	public List<PatientInfo> loadFamilyList(int p_code) {
		return mypageDao.loadFamilyList(p_code);
	}
	
	// ���� ����ϱ�
	public void registerFamilyInfo(PatientInfo info) {
		mypageDao.registerFamilyInfo(info);
	}
	
	// ���� ���� �����ϱ�
	public void deleteFamilyInfo(int p_code) {
		mypageDao.deleteFamilyInfo(p_code);
	}
	
	// ���� ����Ʈ �ҷ�����
	public List<ReservationList> loadReservationList(int p_code) {
		return mypageDao.loadReservationList(p_code);
	}
	
	// �ش� ���� ���� �ҷ�����
	public List<ReservationInfo> getReservationInformation(int num) {
		return mypageDao.getReservationInformation(num);
	}
	
	// �ش� ���� ����ϱ�
	public void cancelReservation(int num) {
		mypageDao.cancelReservation(num);
	}
	
	// ȯ�� Ȩ ȭ���� ���� �̸� ���⸦ ���� �̸� �� ��������
	public String loadUserName(int user_code) {
		return mypageDao.loadUserName(user_code);
	}
}
