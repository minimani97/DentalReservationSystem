package com.sunmoon.reservation.model;

public class AlreadyReservedTimeInfo {
	private String startTime;
	private String endTime;
	private String patient_name;
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}	
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	
	@Override
	public String toString() {
		return "AlreadyReservedTimeInfo [startTime=" + startTime + ", endTime=" + endTime + "]";
	}
}
