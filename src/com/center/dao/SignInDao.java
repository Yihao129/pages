package com.center.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.center.beans.SignInBean;

public interface SignInDao {
	public List<SignInBean> selectByPhone(String phone);
	public int insertOneSignInfo(SignInBean signInBean);
	public List<SignInBean>select();
	public SignInBean selectLastOne();
	public  SignInBean selectCerntainDayCertainUserSignInfo(@Param("phone") String phone,@Param("time1") Date time1,@Param("time2") Date time2);           





}
