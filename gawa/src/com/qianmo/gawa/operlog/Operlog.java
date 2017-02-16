package com.qianmo.gawa.operlog;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */

public class Operlog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int oper_id;
	private String username;
	private String operation;
	private Date add_time;
	public int getOper_id() {
		return oper_id;
	}
	public void setOper_id(int oper_id) {
		this.oper_id = oper_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Date getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Operlog(int oper_id, String username, String operation, Date add_time) {
		super();
		this.oper_id = oper_id;
		this.username = username;
		this.operation = operation;
		this.add_time = add_time;
	}
	public Operlog() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
	
	











	
}