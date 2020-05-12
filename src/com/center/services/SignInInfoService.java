package com.center.services;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.center.beans.SignInBean;

public interface SignInInfoService {
	public List<SignInBean> selectByPhone(String phone);
	public int insertOneSignInfo(SignInBean signInBean);
	public List<SignInBean>select();
	public SignInBean selectLastOne();

}
