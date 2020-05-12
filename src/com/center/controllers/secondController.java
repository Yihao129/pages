package com.center.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.center.beans.SignInBean;
import com.center.beans.UsersBean;
import com.center.dao.SignInDao;
import com.center.dao.UsersDao;
import com.center.services.SignInInfoService;
import com.center.services.UsersService;
import com.center.utilitize.function;

@Controller
public class secondController {
	@Resource
	private SignInInfoService signInInfoService;
	@Resource
	private UsersService usersService;
	
	@Resource
	private SignInDao SignInDao;
	
	@Resource
	private UsersDao usersDao;
	
	@RequestMapping("check_alr_answer")
	public @ResponseBody int check_alr_answer(int v1,int v2,int answer,HttpSession session){
		if(v1*v2==answer)
			{
				session.setAttribute("alr_ok", "true");
				return 1;
			}
		else return 0;
	}
	
	@RequestMapping("test")
	public @ResponseBody int test() {
		System.out.println(new function().check_sign_time());
		 return 0;
	}
	
	@RequestMapping("getShowMsg")
	public @ResponseBody JSONObject getShowMsg(HttpSession session){
		JSONObject jobj=new JSONObject();
		String phone=(String)session.getAttribute("login");
		UsersBean user=this.usersService.selectUsersByPhone(phone);
		jobj.put("signCount", this.signInInfoService.selectByPhone(phone).size());
		jobj.put("seriesCount", user.getSeries_count());
		jobj.put("points", user.getAccumulate_point());
		return jobj;
	}
	
	@RequestMapping("getTodayPoints")
	public @ResponseBody int getTodayPoints(HttpSession session){
		UsersBean user=this.usersService.selectUsersByPhone(session.getAttribute("login").toString());
		int r=user.getSeries_count();
		if(r<3){
			return 5;
		}
		return 6;
	}
	
	
	
	
	
	
	
	
}
