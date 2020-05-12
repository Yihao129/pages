package com.center.beans;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SignInBean {
	private int id;
	private String user_phone;
	
	 
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss") 
	private Date sign_in_time;
	

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}


	public Date getSign_in_time() {
		return sign_in_time;
	}
	public void setSign_in_time(Date sign_in_time) {
		this.sign_in_time = sign_in_time;
	}
	@Override
	public String toString() {
		return "signInBean [id=" + id + ", user_phone=" + user_phone + ", sign_in_time=" + sign_in_time + "]";
	}
	

}
