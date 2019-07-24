package com.sunmoon.reservation.model;

public class Patient_record {
	
	private int num;
	private int p_code;
	private String p_name;
	private String date;
	private String birth;
	private String p_tel;
	private String f_name;
	private String d_name;
	private String extra_info;
	private String personal_info;
	private String content;
	private int m_code;
	private String m_name;
	private String dose;
	private int frequency_day;
	private int day;
	
	
	public int getM_code() {
		return m_code;
	}
	public void setM_code(int m_code) {
		this.m_code = m_code;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getDose() {
		return dose;
	}
	public void setDose(String dose) {
		this.dose = dose;
	}
	public int getFrequency_day() {
		return frequency_day;
	}
	public void setFrequency_day(int frequency_day) {
		this.frequency_day = frequency_day;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getP_code() {
		return p_code;
	}
	public void setP_code(int p_code) {
		this.p_code = p_code;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getP_tel() {
		return p_tel;
	}
	public void setP_tel(String p_tel) {
		this.p_tel = p_tel;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public String getExtra_info() {
		return extra_info;
	}
	public void setExtra_info(String extra_info) {
		this.extra_info = extra_info;
	}
	public String getPersonal_info() {
		return personal_info;
	}
	public void setPersonal_info(String personal_info) {
		this.personal_info = personal_info;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Patient_record [num=" + num + ", p_code=" + p_code + ", p_name=" + p_name + ", date=" + date
				+ ", birth=" + birth + ", p_tel=" + p_tel + ", f_name=" + f_name + ", d_name=" + d_name
				+ ", extra_info=" + extra_info + ", personal_info=" + personal_info + ", content=" + content
				+ ", m_code=" + m_code + ", m_name=" + m_name + ", dose=" + dose + ", frequency_day=" + frequency_day
				+ ", day=" + day + "]";
	}
	
}
