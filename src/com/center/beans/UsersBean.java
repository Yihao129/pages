package com.center.beans;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class UsersBean {

	private String email;
	private String password;
	private String nickName;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss") 
	private Date registerTime;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	@Override
	public String toString() {
		return "UsersBean [email=" + email + ", password=" + password
				+ ", nickName=" + nickName + ", registerTime=" + registerTime
				+ "]";
	}

	
	
	
}
