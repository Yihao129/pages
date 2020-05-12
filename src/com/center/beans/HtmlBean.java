package com.center.beans;

public class HtmlBean {
	public String creator;
	public String code;
	public String page_id;
	public String topic;
	public String url;
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPage_id() {
		return page_id;
	}
	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "HtmlBean [creator=" + creator + ", code=" + code + ", page_id="
				+ page_id + ", topic=" + topic + ", url=" + url + "]";
	}
	
	
	
	
}
