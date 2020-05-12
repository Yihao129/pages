package com.center.beans;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CoreBean {

	private String word;
	private String info;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss") 
	private String time;
	private int content_type;
	private String author;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getContent_type() {
		return content_type;
	}
	public void setContent_type(int content_type) {
		this.content_type = content_type;
	}
	@Override
		public String toString() {
		return "CoreBean [word=" + word + ", info=" + info + ", time=" + time
				+ ", content_type=" + content_type + ", author=" + author + "]";
	}

	
	
	
}
