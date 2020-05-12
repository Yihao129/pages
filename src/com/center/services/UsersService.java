package com.center.services;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.center.beans.UsersBean;
import com.center.utilitize.SqlExecuteException;
import com.taobao.api.ApiException;

public interface UsersService {
	UsersBean selectUsersByEmail(String email);
	
	public UsersBean checkLogin(Map<String,String> map);
	public List<UsersBean> select();
	public int insert(UsersBean userInfo);
	
	public abstract void getCheckCode(String phone,HttpSession session) throws ApiException,
	SqlExecuteException;
	public boolean checkYesterdayIfSigned(String phone)throws ParseException;
	
	public int incSeriesCount(String phone);
	
	public int addAccumulatePoint(String phone,int number);
	public List<UsersBean> selectInfoForRankTable();
}
