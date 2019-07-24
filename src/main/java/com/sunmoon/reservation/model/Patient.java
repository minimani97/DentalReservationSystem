package com.sunmoon.reservation.model;

public class Patient {
	private int p_code;
	private String id;
	private String p_name;
	private String birth;
	private String family;
	private String addr;
	private String p_tel;
	
	public int getP_code() {
		return p_code;
	}
	public void setP_code(int p_code) {
		this.p_code = p_code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getP_tel() {
		return p_tel;
	}
	public void setP_tel(String p_tel) {
		this.p_tel = p_tel;
	}
	
	@Override
	public String toString() {
		return "Patient [p_code=" + p_code + ", id=" + id + ", p_name=" + p_name + ", birth=" + birth + ", family="
				+ family + ", addr=" + addr + ", p_tel=" + p_tel + "]";
	}
	
}
