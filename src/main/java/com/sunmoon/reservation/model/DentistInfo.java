package com.sunmoon.reservation.model;

import java.util.ArrayList;

public class DentistInfo {
	
	private String id;
	private String password;
	private int d_code;
	private String d_name;
	private String d_tel;
	private String email;
	private String profile;
	private ArrayList<String> fields;
	private int c_code;
	private String f_codes;
	private String edu;
	private String career;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getD_code() {
		return d_code;
	}
	public void setD_code(int d_code) {
		this.d_code = d_code;
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
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public ArrayList<String> getFields() {
		return fields;
	}
	public void setFields(ArrayList<String> fields) {
		this.fields = fields;
	}
	public int getC_code() {
		return c_code;
	}
	public void setC_code(int c_code) {
		this.c_code = c_code;
	}
	public String getF_codes() {
		return f_codes;
	}
	public void setF_codes(String f_codes) {
		this.f_codes = f_codes;
	}
	public String getEdu() {
		return edu;
	}
	public void setEdu(String edu) {
		this.edu = edu;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	@Override
	public String toString() {
		return "DentistInfo [id=" + id + ", password=" + password + ", d_code=" + d_code + ", d_name=" + d_name
				+ ", d_tel=" + d_tel + ", email=" + email + ", profile=" + profile + ", fields=" + fields + ", c_code="
				+ c_code + ", f_codes=" + f_codes + ", edu=" + edu + ", career=" + career + "]";
	}
	
}
