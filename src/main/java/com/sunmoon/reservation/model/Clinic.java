package com.sunmoon.reservation.model;

public class Clinic {

	private int c_code;
	private String c_name;
	private double addr_y;
	private double addr_x;
	
	public int getC_code() {
		return c_code;
	}
	public void setC_code(int c_code) {
		this.c_code = c_code;
	}
	public double getAddr_y() {
		return addr_y;
	}
	public void setAddr_y(double addr_y) {
		this.addr_y = addr_y;
	}
	public double getAddr_x() {
		return addr_x;
	}
	public void setAddr_x(double addr_x) {
		this.addr_x = addr_x;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	
	@Override
	public String toString() {
		return "Clinic [c_code=" + c_code + ", c_name=" + c_name + ", addr_y=" + addr_y + ", addr_x=" + addr_x + "]";
	}
	
}
