package com.qianmo.gawa.login;

import java.util.Date;
import java.util.List;



/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */

public class LoginUser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5037357681979649205L;
	
	private String user_id;
	private String user_name;
	private String password;
	private String phone;	
	private String rel_name;
	private int statu;
	private String user_level;
	private String permission_level;
	private String expression;
	private String parent_id;
	private String province;
	private String id_card;
	
	private String company;
	private String email;
	private String birth_date;
	private String mobile_no;
	private String area_no;
	private String address;
	private String key_no;
	
	private String photo;
	private String education_paper;
	private String major_paper;
	private String work_proof;
	private String other_proof;

	


	private Date create_time;
	
	
	
	
	
	
	

	
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getEducation_paper() {
		return education_paper;
	}

	public void setEducation_paper(String education_paper) {
		this.education_paper = education_paper;
	}

	public String getMajor_paper() {
		return major_paper;
	}

	public void setMajor_paper(String major_paper) {
		this.major_paper = major_paper;
	}

	public String getWork_proof() {
		return work_proof;
	}

	public void setWork_proof(String work_proof) {
		this.work_proof = work_proof;
	}

	public String getOther_proof() {
		return other_proof;
	}

	public void setOther_proof(String other_proof) {
		this.other_proof = other_proof;
	}

	public String getKey_no() {
		return key_no;
	}

	public void setKey_no(String key_no) {
		this.key_no = key_no;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getArea_no() {
		return area_no;
	}

	public void setArea_no(String area_no) {
		this.area_no = area_no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}


	public String getUser_level() {
		return user_level;
	}

	public void setUser_level(String user_level) {
		this.user_level = user_level;
	}

	public String getPermission_level() {
		return permission_level;
	}

	public void setPermission_level(String permission_level) {
		this.permission_level = permission_level;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRel_name() {
		return rel_name;
	}

	public void setRel_name(String rel_name) {
		this.rel_name = rel_name;
	}

	public int getStatu() {
		return statu;
	}

	public void setStatu(int statu) {
		this.statu = statu;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

	
}