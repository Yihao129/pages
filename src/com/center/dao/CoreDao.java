package com.center.dao;

import java.util.List;

import com.center.beans.CoreBean;

public interface CoreDao {
	
	public int insert(CoreBean bean);
	public List<CoreBean> selectByWord(String userName);

}
