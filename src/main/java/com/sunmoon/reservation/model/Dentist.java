package com.sunmoon.reservation.model;

public class Dentist {
	
	private int d_code;
	private int c_code;
	private String profile;
	private String d_name;
	private String d_tel;
	private String email;
	private String edu;
	private String carrer;
	
	public int getD_code() {
		return d_code;
	}
	public void setD_code(int d_code) {
		this.d_code = d_code;
	}
	public int getC_code() {
		return c_code;
	}
	public void setC_code(int c_code) {
		this.c_code = c_code;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public String getD_tel() {
		return d_tel;
	}
	public void setD_tel(String d_tel) {
		this.d_tel = d_tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEdu() {
		return edu;
	}
	public void setEdu(String edu) {
		this.edu = edu;
	}
	public String getCarrer() {
		return carrer;
	}
	public void setCarrer(String carrer) {
		this.carrer = carrer;
	}
	
	@Override
	public String toString() {
		return "Dregister [d_code=" + d_code + ", c_code=" + c_code + ", profile=" + profile + ", d_name=" + d_name
				+ ", d_tel=" + d_tel + ", email=" + email + ", edu=" + edu + ", carrer=" + carrer + "]";
	}
}
