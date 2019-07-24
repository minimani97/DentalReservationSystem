package com.sunmoon.reservation.model;

public class D_field_List {
	private int d_code;
	private String f_name;
	private int d_field;
	private int c_code;
	
	public int getD_code() {
		return d_code;
	}
	public void setD_code(int d_code) {
		this.d_code = d_code;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public int getD_field() {
		return d_field;
	}
	public void setD_field(int d_field) {
		this.d_field = d_field;
	}
	public int getC_code() {
		return c_code;
	}
	public void setC_code(int c_code) {
		this.c_code = c_code;
	}
	@Override
	public String toString() {
		return "D_field [d_code=" + d_code + ", f_name=" + f_name + ", d_field=" + d_field + ", c_code=" + c_code + "]";
	}
	
}
