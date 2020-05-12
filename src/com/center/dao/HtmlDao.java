package com.center.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.center.beans.HtmlBean;


public interface HtmlDao {

	public int insert(HtmlBean bean);
	public HtmlBean selectByUserPageId(String page_id);
	public List<HtmlBean> selectByCreator(String creator);
	public int updateByPageId(@Param("creator")String creator, @Param("code")String code, @Param("page_id")String page_id, @Param("new_page_id")String new_page_id, @Param("topic")String topic, @Param("url")String url);
	public int deleteByPageId(String page_id);


}
