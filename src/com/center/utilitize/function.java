package com.center.utilitize;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.ResponseBody;

import com.center.beans.UsersBean;
import com.center.services.UsersService;

public class function {
	@Resource
	private UsersService usersService;
	
	public boolean check_phone_existed(String phone){
		UsersBean bean=usersService.selectUsersByPhone(phone);
		if(bean==null)
			return false;
		
		return true;
	}
	
	public int check_sign_time(){
		Date date=new Date();
		int h=date.getHours();
		int m=date.getMinutes();
		if(h==6){
			return 1;
		}
		if(h==7 && m<=20){
			return 1;
		}
		return 0;
	}

}
