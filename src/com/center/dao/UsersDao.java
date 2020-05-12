package com.center.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.center.beans.UsersBean;

public interface UsersDao {
	public UsersBean selectUsersByEmail(String email);
	
	public UsersBean checkLogin(Map<String,String> map);
	public List<UsersBean> select();
	public int insert(UsersBean userInfo);
	public int incSereisCount(@Param("newSeriesCount")int newSeriesCount,@Param("phone")String phone);
	public int incAccumulatePoint(@Param("newAccumulatePoint")int newAccumulatePoint,@Param("phone")String phone);            
	public List<UsersBean> selectInfoForRankTable();

}
