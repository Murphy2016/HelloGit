package com.qianmo.gawa.notice;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */

public class Notice implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int notice_id;
	private String content;
	private Date date1;
	private String reply;
	private Date reply_date;
	

	
	













	public int getNotice_id() {
		return notice_id;
	}

















	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}

















	public String getContent() {
		return content;
	}

















	public void setContent(String content) {
		this.content = content;
	}

















	public Date getDate1() {
		/*String ret = null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		if(date1 != null)
			ret = sdf.format(date1); 
		return ret;*/
		return date1;
	}

















	public void setDate1(Date date1) {
		this.date1 = date1;
	}

















	public String getReply() {
		return reply;
	}

















	public void setReply(String reply) {
		this.reply = reply;
	}

















	public Date getReply_date() {
		/*String ret = null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		if(reply_date != null)
			ret = sdf.format(reply_date); 
		return ret;*/
		return reply_date;
	}

















	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}

















	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

	
}