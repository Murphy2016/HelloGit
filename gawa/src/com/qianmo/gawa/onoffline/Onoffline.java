package com.qianmo.gawa.onoffline;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */
@XmlRootElement
public class Onoffline implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long   onoff_id;
	private Long   area_id;
	private Long   allap_id;
	private String auth_type;
	private String auth_type_display;
	public String getAuth_type_display() {
		auth_type_display="手机号";
		if(auth_type.equals("1020004")){
			auth_type_display="手机号";
		}
		return auth_type_display;
	}
	public void setAuth_type_display(String auth_type_display) {
		this.auth_type_display = auth_type_display;
	}

	private String auth_name;
	private String auth_area_code;
	private Integer auth_area_type;
	private String auth_area_type_display;
	public String getAuth_area_type_display() {
		auth_area_type_display = "公共交通工具";
		if(auth_area_type==6){
			auth_area_type_display = "公共交通工具";
		}
		return auth_area_type_display;
	}
	public void setAuth_area_type_display(String auth_area_type_display) {
		this.auth_area_type_display = auth_area_type_display;
	}

	private String identification_type;
	private String identification_type_display;
	public String getIdentification_type_display() {
		identification_type_display="手机号";
		if(identification_type.equals("990")){
			identification_type_display="手机号";
		}
		return identification_type_display;
	}
	public void setIdentification_type_display(String identification_type_display) {
		this.identification_type_display = identification_type_display;
	}

	private String identification;
	private String app_company_name;
	private String app_software_name;
	private String app_version;
	private String app_id;
	private Long   auth_time;
	private Long   de_auth_time;
	private String   auth_time_display;
	private String   de_auth_time_display;
	private Long   auth_time_save;
	private Long   de_auth_time_save;
	private Long   ip_lan;
	private Long   ip4_wan;
	private String ip6_wan;
	private Integer ip4_port_start_wan;
	private Integer ip4_port_end_wan;
	private Integer ip6_port_start_wan;
	private Integer ip6_port_end_wan;
	private String user_mac;
	private String ap_code;
	private String ap_mac;
	private String ap_longitude;
	private String ap_latitude;
	private String session_id;
	private String ap_signal_strength;
	private String location_x;
	private String location_y;
	private String name1;
	private String imsi;
	private String imei;
	private String os_name;
	private String brand;
	private String model;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	





	public String getAuth_time_display() {
		if(auth_time==null) return "";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date dt = new Date(auth_time * 1000); //system default is millisecond.
		String sDateTime = sdf.format(dt);
		return sDateTime;
		//return auth_time_display;
	}
	public void setAuth_time_display(String auth_time_display) {
		this.auth_time_display = auth_time_display;
	}
	public String getDe_auth_time_display() {
		if(de_auth_time==null || de_auth_time==0) return "";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date dt = new Date(de_auth_time * 1000); //system default is millisecond.
		String sDateTime = sdf.format(dt);
		return sDateTime;
		//return de_auth_time_display;
	}
	public void setDe_auth_time_display(String de_auth_time_display) {
		this.de_auth_time_display = de_auth_time_display;
	}







	public Long getOnoff_id() {
		return onoff_id;
	}

	public void setOnoff_id(Long onoff_id) {
		this.onoff_id = onoff_id;
	}

	public Long getArea_id() {
		return area_id;
	}

	public void setArea_id(Long area_id) {
		this.area_id = area_id;
	}

	public Long getAllap_id() {
		return allap_id;
	}

	public void setAllap_id(Long allap_id) {
		this.allap_id = allap_id;
	}

	public String getAuth_type() {
		return auth_type;
	}

	public void setAuth_type(String auth_type) {
		this.auth_type = auth_type;
	}

	public String getAuth_name() {
		return auth_name;
	}

	public void setAuth_name(String auth_name) {
		this.auth_name = auth_name;
	}

	public String getAuth_area_code() {
		return auth_area_code;
	}

	public void setAuth_area_code(String auth_area_code) {
		this.auth_area_code = auth_area_code;
	}

	public Integer getAuth_area_type() {
		return auth_area_type;
	}

	public void setAuth_area_type(Integer auth_area_type) {
		this.auth_area_type = auth_area_type;
	}

	public String getIdentification_type() {
		return identification_type;
	}

	public void setIdentification_type(String identification_type) {
		this.identification_type = identification_type;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getApp_company_name() {
		return app_company_name;
	}

	public void setApp_company_name(String app_company_name) {
		this.app_company_name = app_company_name;
	}

	public String getApp_software_name() {
		return app_software_name;
	}

	public void setApp_software_name(String app_software_name) {
		this.app_software_name = app_software_name;
	}

	public String getApp_version() {
		return app_version;
	}

	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public Long getAuth_time() {
		/*if(auth_time==null) return "";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date dt = new Date(auth_time * 1000); //system default is millisecond.
		String sDateTime = sdf.format(dt);
		return sDateTime;*/
		return auth_time;
	}

	public void setAuth_time(Long auth_time) {
		this.auth_time = auth_time;
		/*this.auth_time_save = auth_time;
		if(auth_time==null) return ;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date dt = new Date(auth_time * 1000); //system default is millisecond.
		String sDateTime = sdf.format(dt);
		auth_time_display = sDateTime;*/
	}

	public Long getDe_auth_time() {
		/*if(de_auth_time==null || de_auth_time==0) return "";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date dt = new Date(de_auth_time * 1000); //system default is millisecond.
		String sDateTime = sdf.format(dt);
		return sDateTime;*/
		return de_auth_time;
	}

	public void setDe_auth_time(Long de_auth_time) {
		this.de_auth_time = de_auth_time;
		/*this.de_auth_time_save = de_auth_time;
		if(de_auth_time==null) return ;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date dt = new Date(de_auth_time * 1000); //system default is millisecond.
		String sDateTime = sdf.format(dt);
		de_auth_time_display = sDateTime;*/
	}
	
	
	

	public Long getAuth_time_save() {
		return auth_time_save;
	}

	public void setAuth_time_save(Long auth_time_save) {
		this.auth_time_save = auth_time_save;
	}

	public Long getDe_auth_time_save() {
		return de_auth_time_save;
	}

	public void setDe_auth_time_save(Long de_auth_time_save) {
		this.de_auth_time_save = de_auth_time_save;
	}

	public Long getIp_lan() {
		return ip_lan;
	}

	public void setIp_lan(Long ip_lan) {
		this.ip_lan = ip_lan;
	}

	public Long getIp4_wan() {
		return ip4_wan;
	}

	public void setIp4_wan(Long ip4_wan) {
		this.ip4_wan = ip4_wan;
	}

	public String getIp6_wan() {
		return ip6_wan;
	}

	public void setIp6_wan(String ip6_wan) {
		this.ip6_wan = ip6_wan;
	}

	public Integer getIp4_port_start_wan() {
		return ip4_port_start_wan;
	}

	public void setIp4_port_start_wan(Integer ip4_port_start_wan) {
		this.ip4_port_start_wan = ip4_port_start_wan;
	}

	public Integer getIp4_port_end_wan() {
		return ip4_port_end_wan;
	}

	public void setIp4_port_end_wan(Integer ip4_port_end_wan) {
		this.ip4_port_end_wan = ip4_port_end_wan;
	}

	public Integer getIp6_port_start_wan() {
		return ip6_port_start_wan;
	}

	public void setIp6_port_start_wan(Integer ip6_port_start_wan) {
		this.ip6_port_start_wan = ip6_port_start_wan;
	}

	public Integer getIp6_port_end_wan() {
		return ip6_port_end_wan;
	}

	public void setIp6_port_end_wan(Integer ip6_port_end_wan) {
		this.ip6_port_end_wan = ip6_port_end_wan;
	}

	public String getUser_mac() {
		return user_mac;
	}

	public void setUser_mac(String user_mac) {
		this.user_mac = user_mac;
	}

	public String getAp_code() {
		return ap_code;
	}

	public void setAp_code(String ap_code) {
		this.ap_code = ap_code;
	}

	public String getAp_mac() {
		return ap_mac;
	}

	public void setAp_mac(String ap_mac) {
		this.ap_mac = ap_mac;
	}

	public String getAp_longitude() {
		return ap_longitude;
	}

	public void setAp_longitude(String ap_longitude) {
		this.ap_longitude = ap_longitude;
	}

	public String getAp_latitude() {
		return ap_latitude;
	}

	public void setAp_latitude(String ap_latitude) {
		this.ap_latitude = ap_latitude;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getAp_signal_strength() {
		return ap_signal_strength;
	}

	public void setAp_signal_strength(String ap_signal_strength) {
		this.ap_signal_strength = ap_signal_strength;
	}

	public String getLocation_x() {
		return location_x;
	}

	public void setLocation_x(String location_x) {
		this.location_x = location_x;
	}

	public String getLocation_y() {
		return location_y;
	}

	public void setLocation_y(String location_y) {
		this.location_y = location_y;
	}



	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getOs_name() {
		return os_name;
	}

	public void setOs_name(String os_name) {
		this.os_name = os_name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	} 
	
	public String toBcpString(){
		return  onoff_id + "\t" + area_id + "\t" + allap_id + "\t"
				+ auth_type + "\t" + auth_type_display + "\t" + auth_name
				+ "\t" + auth_area_code + "\t" + auth_area_type
				+ "\t" + auth_area_type_display + "\t" + identification_type
				+ "\t" + identification_type_display + "\t" + identification
				+ "\t" + app_company_name + "\t" + app_software_name
				+ "\t" + app_version + "\t" + app_id + "\t" + auth_time + "\t"
				+ de_auth_time + "\t" + auth_time_display + "\t"
				+ de_auth_time_display + "\t" + auth_time_save + "\t"
				+ de_auth_time_save + "\t" + ip_lan + "\t" + ip4_wan + "\t" + ip6_wan
				+ "\t" + ip4_port_start_wan + "\t" + ip4_port_end_wan
				+ "\t" + ip6_port_start_wan + "\t" + ip6_port_end_wan
				+ "\t" + user_mac + "\t" + ap_code + "\t" + ap_mac + "\t"
				+ ap_longitude + "\t" + ap_latitude + "\t" + session_id + "\t"
				+ ap_signal_strength + "\t" + location_x + "\t" + location_y + "\t" + name1
				+ "\t" + imsi + "\t" + imei + "\t" + os_name + "\t" + brand + "\t" + model
				;
	}
	@Override
	public String toString() {
		return "Onoffline [onoff_id=" + onoff_id + ", area_id=" + area_id + ", allap_id=" + allap_id + ", auth_type="
				+ auth_type + ", auth_type_display=" + auth_type_display + ", auth_name=" + auth_name
				+ ", auth_area_code=" + auth_area_code + ", auth_area_type=" + auth_area_type
				+ ", auth_area_type_display=" + auth_area_type_display + ", identification_type=" + identification_type
				+ ", identification_type_display=" + identification_type_display + ", identification=" + identification
				+ ", app_company_name=" + app_company_name + ", app_software_name=" + app_software_name
				+ ", app_version=" + app_version + ", app_id=" + app_id + ", auth_time=" + auth_time + ", de_auth_time="
				+ de_auth_time + ", auth_time_display=" + auth_time_display + ", de_auth_time_display="
				+ de_auth_time_display + ", auth_time_save=" + auth_time_save + ", de_auth_time_save="
				+ de_auth_time_save + ", ip_lan=" + ip_lan + ", ip4_wan=" + ip4_wan + ", ip6_wan=" + ip6_wan
				+ ", ip4_port_start_wan=" + ip4_port_start_wan + ", ip4_port_end_wan=" + ip4_port_end_wan
				+ ", ip6_port_start_wan=" + ip6_port_start_wan + ", ip6_port_end_wan=" + ip6_port_end_wan
				+ ", user_mac=" + user_mac + ", ap_code=" + ap_code + ", ap_mac=" + ap_mac + ", ap_longitude="
				+ ap_longitude + ", ap_latitude=" + ap_latitude + ", session_id=" + session_id + ", ap_signal_strength="
				+ ap_signal_strength + ", location_x=" + location_x + ", location_y=" + location_y + ", name1=" + name1
				+ ", imsi=" + imsi + ", imei=" + imei + ", os_name=" + os_name + ", brand=" + brand + ", model=" + model
				+ "]";
	}

	
	


	
	

	
}