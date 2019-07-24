package com.sunmoon.reservation.model;

public class Medicine {
	
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
	
	@Override
	public String toString() {
		return "medicine [m_code=" + m_code + ", m_name=" + m_name + ", dose=" + dose + ", frequency_day="
				+ frequency_day + ", day=" + day + "]";
	}
}
