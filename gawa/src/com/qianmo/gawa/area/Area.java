package com.qianmo.gawa.area;





/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */

public class Area implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long area_id;
	private Long org_id;
	private String area_code;
	private String area_name;
	private String area_address;
	private String area_longitude;
	private String area_latitude;
	private String area_sevice_type;
	private String business_nature;
	private String law_owner_name;
	private String owner_id_type;
	private String owner_id_no;
	private String phone_no;
	private String start_time;
	private String end_time;
	private Integer access_type;
	private String net_provider;
	private String account_ip;
	private String organization_code;
	private Integer state;
	private String display_state;

	
	public String getDisplay_state() {
		if(state !=null && state==1){
			display_state = "营业";
		}else{
			display_state = "停业";
		}
		return display_state;
	}

	public void setDisplay_state(String display_state) {
		this.display_state = display_state;
		if(display_state.equals("营业")){
			state=1;
		}else{
			state=0;
		}
	}

	public Long getArea_id() {
		return area_id;
	}


	public void setArea_id(Long area_id) {
		this.area_id = area_id;
	}


	public Long getOrg_id() {
		return org_id;
	}


	public void setOrg_id(Long org_id) {
		this.org_id = org_id;
	}


	public String getArea_code() {
		return area_code;
	}


	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getArea_address() {
		return area_address;
	}


	public void setArea_address(String area_address) {
		this.area_address = area_address;
	}

	public String getArea_longitude() {
		return area_longitude;
	}

	public void setArea_longitude(String area_longitude) {
		this.area_longitude = area_longitude;
	}

	public String getArea_latitude() {
		return area_latitude;
	}

	public void setArea_latitude(String area_latitude) {
		this.area_latitude = area_latitude;
	}

	public String getArea_sevice_type() {
		return "交通工具";
	}

	public void setArea_sevice_type(String area_sevice_type) {
		this.area_sevice_type = area_sevice_type;
	}

	public String getBusiness_nature() {
		return "经营";
	}

	public void setBusiness_nature(String business_nature) {
		this.business_nature = business_nature;
	}

	public String getLaw_owner_name() {
		return "";
	}
	public void setLaw_owner_name(String law_owner_name) {
		this.law_owner_name = law_owner_name;
	}

	public String getOwner_id_type() {
		return "";
	}

	public void setOwner_id_type(String owner_id_type) {
		this.owner_id_type = owner_id_type;
	}


	public String getOwner_id_no() {
		return "";
	}

	public void setOwner_id_no(String owner_id_no) {
		this.owner_id_no = owner_id_no;
	}

	public String getPhone_no() {
		return "";
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getStart_time() {
		return "";
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return "";
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getAccess_type() {
		return "";
	}

	public void setAccess_type(Integer access_type) {
		this.access_type = access_type;
	}


	public String getNet_provider() {
		return "";
	}


	public void setNet_provider(String net_provider) {
		this.net_provider = net_provider;
	}


	public String getAccount_ip() {
		return "";
	}

	public void setAccount_ip(String account_ip) {
		this.account_ip = account_ip;
	}

	public String getOrganization_code() {
		return organization_code;
	}

	public void setOrganization_code(String organization_code) {
		this.organization_code = organization_code;
	}

	public Integer getState() {
		return state;
		
	}

	public void setState(Integer state) {
		this.state = state;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return  area_id + "\t" + org_id + "\t" + area_code + "\t"
				+ area_name + "\t" + area_address + "\t" + area_longitude
				+ "\t" + area_latitude + "\t" + area_sevice_type + "\t"
				+ business_nature + "\t" + law_owner_name + "\t" + owner_id_type
				+ "\t" + owner_id_no + "\t" + phone_no + "\t" + start_time
				+ "\t" + end_time + "\t" + access_type + "\t" + net_provider
				+ "\t" + account_ip + "\t" + organization_code + "\t" + state
				+ "\t" + display_state ;
	}
	
	
	
}