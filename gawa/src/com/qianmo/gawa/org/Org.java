package com.qianmo.gawa.org;





/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */

public class Org implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long org_id            ; 
	private String name1           ; 
	private String code1           ; 
	private String address         ; 
	private String contactor       ; 
	private String contactor_tel   ; 
	private String contactor_mail  ;
	public Long getOrg_id() {
		return org_id;
	}
	public void setOrg_id(Long org_id) {
		this.org_id = org_id;
	}
	
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getCode1() {
		return code1;
	}
	public void setCode1(String code1) {
		this.code1 = code1;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactor() {
		return contactor;
	}
	public void setContactor(String contactor) {
		this.contactor = contactor;
	}
	public String getContactor_tel() {
		return contactor_tel;
	}
	public void setContactor_tel(String contactor_tel) {
		this.contactor_tel = contactor_tel;
	}
	public String getContactor_mail() {
		return contactor_mail;
	}
	public void setContactor_mail(String contactor_mail) {
		this.contactor_mail = contactor_mail;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return org_id + "\t" + name1 + "\t" + code1 + "\t" + address
				+ "\t" + contactor + "\t" + contactor_tel + "\t" + contactor_mail
				;
	} 
	


	
	


	
	

	
}