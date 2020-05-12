package com.center.services.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.center.dao.HtmlDao;
import com.center.services.HtmlService;

@Service("htmlService")
public class HtmlServiceImpl implements HtmlService{
	@Resource
	private HtmlDao htmlDao;
	
	@Override
	public int checkExistence(String page_id) {
		// TODO Auto-generated method stub
		if(htmlDao.selectByUserPageId(page_id)==null){
			return 0;
		}else{
			return 1;
		}
		
	}

	
}
