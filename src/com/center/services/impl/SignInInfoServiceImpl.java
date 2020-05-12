package com.center.services.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.center.beans.SignInBean;
import com.center.dao.SignInDao;
import com.center.services.SignInInfoService;
@Service("signInService")
public class SignInInfoServiceImpl implements SignInInfoService{
	@Resource
	private SignInDao signInDao;
	@Override
	public List<SignInBean> selectByPhone(String phone) {
		// TODO Auto-generated method stub
		return signInDao.selectByPhone(phone);
	}
	@Override
	public int insertOneSignInfo(SignInBean signInBean) {
		// TODO Auto-generated method stub
		int i=signInDao.insertOneSignInfo(signInBean);
		return i;
	}
	@Override
	public List<SignInBean> select() {
		// TODO Auto-generated method stub
		return signInDao.select();
	}
	@Override
	public SignInBean selectLastOne() {
		// TODO Auto-generated method stub
		return signInDao.selectLastOne();
	}


}
