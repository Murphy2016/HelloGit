package com.qianmo.gawa.login;


import java.util.List;



/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */

public class TestUser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String firstname;
	private String lastname;
	private String phone;
	private String email;	


	
	
	
	
	public String getFirstname() {
		return firstname;
	}






	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}






	public String getLastname() {
		return lastname;
	}






	public void setLastname(String lastname) {
		this.lastname = lastname;
	}






	public String getPhone() {
		return phone;
	}






	public void setPhone(String phone) {
		this.phone = phone;
	}






	public String getEmail() {
		return email;
	}






	public void setEmail(String email) {
		this.email = email;
	}






	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

	
}