package com.sunmoon.reservation.model;

public class UserInfo {

	private String id;
	private String password;
	private String group;
	
	private String p_name;
	private String birth;
	private String p_tel;
	private String addr;
	
	private int p_code;
	
	private int flg;
	
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
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getFlg() {
		return flg;
	}
	public void setFlg(int flg) {
		this.flg = flg;
	}
	public int getP_code() {
		return p_code;
	}
	public void setP_code(int p_code) {
		this.p_code = p_code;
	}
	
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", password=" + password + ", group=" + group + ", p_name=" + p_name + ", birth="
				+ birth + ", p_tel=" + p_tel + ", addr=" + addr + ", p_code=" + p_code + ", flg=" + flg + "]";
	}
	
	
	
}



/*
userInfo.setId(rs.getString("id"));
					userInfo.setPassword(rs.getString("password"));
					userInfo.setP_code(rs.getInt("p_code"));
					userInfo.setP_name(rs.getString("p_name"));
					userInfo.setBirth(rs.getString("birth"));
					userInfo.setAddr(rs.getString("addr"));
					userInfo.setP_tel(rs.getString("p_tel"));
*/