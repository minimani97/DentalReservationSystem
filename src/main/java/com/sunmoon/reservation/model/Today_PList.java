package com.sunmoon.reservation.model;

public class Today_PList {

	private String id;
	private int num;
	private int p_code;
	private String p_name;
	private String birth;
	private String date;
	private String p_tel;
	private int r_field;
	private String f_name;
	private int d_code;
	private String d_name;
	private String extra_info;
	private String personal_info;
	private String content;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getR_field() {
		return r_field;
	}
	public void setR_field(int r_field) {
		this.r_field = r_field;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
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
	
	@Override
	public String toString() {
		return "Today_PList [id=" + id + ", num=" + num + ", p_code=" + p_code + ", p_name=" + p_name + ", birth="
				+ birth + ", date=" + date + ", p_tel=" + p_tel + ", r_field=" + r_field + ", f_name=" + f_name
				+ ", d_code=" + d_code + ", d_name=" + d_name + ", extra_info=" + extra_info + ", personal_info="
				+ personal_info + ", content=" + content + "]";
	}
}
