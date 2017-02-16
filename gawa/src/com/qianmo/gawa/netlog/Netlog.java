package com.qianmo.gawa.netlog;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */

@XmlRootElement
public class Netlog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long log_id;
	private Long area_id;
	private Long allap_id;
	private String session_id;
	private Long create_time;
	private String create_time_display;
	public String getCreate_time_display() {
		if(create_time==null) return "";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date dt = new Date(create_time * 1000); //system default is millisecond.
		String sDateTime = sdf.format(dt);
		return sDateTime;
		//return create_time_display;
	}




	public void setCreate_time_display(String create_time_display) {
		this.create_time_display = create_time_display;
	}




	private Long aabbcc;
	private Long create_time_save;
	private String auth_area_code;
	private String protocol_type="01";
	private String protocol_type_display;
	public String getProtocol_type_display() {
		protocol_type_display="HTTP";
		if(protocol_type.equals("01") || protocol_type.equals("1")){
			protocol_type_display="HTTP";
		}else if(protocol_type.equals("02") || protocol_type.equals("2")){
			protocol_type_display="WAP";
		}else if(protocol_type.equals("03") || protocol_type.equals("3")){
			protocol_type_display="SMTP";
		}else if(protocol_type.equals("04") || protocol_type.equals("4")){
			protocol_type_display="POP3";
		}else if(protocol_type.equals("05") || protocol_type.equals("5")){
			protocol_type_display="IMAP";
		}else if(protocol_type.equals("06") || protocol_type.equals("6")){
			protocol_type_display="NNTP";
		}else if(protocol_type.equals("07") || protocol_type.equals("7")){
			protocol_type_display="FTP";
		}else if(protocol_type.equals("08") || protocol_type.equals("8")){
			protocol_type_display="SFTP";
		}else if(protocol_type.equals("09") || protocol_type.equals("9")){
			protocol_type_display="TELNET";
		}else if(protocol_type.equals("10")){
			protocol_type_display="HTTPS";
		}else if(protocol_type.equals("11")){
			protocol_type_display="RSTP";
		}else if(protocol_type.equals("12")){
			protocol_type_display="MMS";
		}
		else if(protocol_type.equals("13")){
			protocol_type_display="WEP";
		}else if(protocol_type.equals("14")){
			protocol_type_display="WPA";
		}else if(protocol_type.equals("15")){
			protocol_type_display="PPTP";
		}else if(protocol_type.equals("16")){
			protocol_type_display="L2TP";
		}else if(protocol_type.equals("17")){
			protocol_type_display="SOCKS代理";
		}else if(protocol_type.equals("18")){
			protocol_type_display="Compo";
		}else if(protocol_type.equals("19")){
			protocol_type_display="Cmsmtp";
		}else if(protocol_type.equals("31")){
			protocol_type_display="QQ";
		}else if(protocol_type.equals("32")){
			protocol_type_display="SSDP";
		}else if(protocol_type.equals("33")){
			protocol_type_display="Thunder";
		}else if(protocol_type.equals("34")){
			protocol_type_display="MSN";
		}else if(protocol_type.equals("35")){
			protocol_type_display="IRC";
		}else if(protocol_type.equals("36")){
			protocol_type_display="DHCP";
		}else if(protocol_type.equals("37")){
			protocol_type_display="NETBIOS";
		}else if(protocol_type.equals("38")){
			protocol_type_display="ICMP";
		}else if(protocol_type.equals("39")){
			protocol_type_display="IGMP";
		}else if(protocol_type.equals("40")){
			protocol_type_display="TFTP";
		}else if(protocol_type.equals("41")){
			protocol_type_display="SMB";
		}else if(protocol_type.equals("42")){
			protocol_type_display="DNS";
		}else if(protocol_type.equals("91")){
			protocol_type_display="私有";
		}
		else {
			protocol_type_display="其他";
		}
		
		
		return protocol_type_display;
	}
	public void setProtocol_type_display(String protocol_type_display) {
		this.protocol_type_display = protocol_type_display;
	}




	private Long ip_lan;
	private Integer ip_port_lan;
	private Long ip4_wan;
	private String ip6_wan;
	private Integer ip4_port_start_wan;
	private Integer ip4_port_end_wan;
	private Integer ip6_port_start_wan;
	private Integer ip6_port_end_wan;
	private Long ip4_target_wan;
	private String ip6_target_wan;
	private Integer ip4_target_port_wan;
	private Integer ip6_target_port_wan;
	private String user_mac;
	private String ap_code;
	private String ap_mac;
	private String ap_longitude;
	private String ap_latitude;
	
	

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public Long getLog_id() {
		return log_id;
	}




	public void setLog_id(Long log_id) {
		this.log_id = log_id;
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




	public String getSession_id() {
		return session_id;
	}








	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}




	public Long getAabbcc() {
		return aabbcc;
	}
	public void setAabbcc(Long aabbcc) {
		this.aabbcc = aabbcc;
	}

	public Long  getCreate_time() {
		/*if(create_time==null) return "";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date dt = new Date(create_time * 1000); //system default is millisecond.
		String sDateTime = sdf.format(dt);
		return sDateTime;*/
		return create_time;
	}




	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
		/*if(create_time==null) return ;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date dt = new Date(create_time * 1000); //system default is millisecond.
		String sDateTime = sdf.format(dt);
		create_time_display = sDateTime;*/
		
	}
	




	public Long getCreate_time_save() {
		return create_time_save;
	}




	public void setCreate_time_save(Long create_time_save) {
		this.create_time_save = create_time_save;
	}




	public String getAuth_area_code() {
		return auth_area_code;
	}




	public void setAuth_area_code(String auth_area_code) {
		this.auth_area_code = auth_area_code;
	}




	public String getProtocol_type() {
		return protocol_type;
	}




	public void setProtocol_type(String protocol_type) {
		this.protocol_type = protocol_type;
	}




	public Long getIp_lan() {
		return ip_lan;
	}




	public void setIp_lan(Long ip_lan) {
		this.ip_lan = ip_lan;
	}




	public Integer getIp_port_lan() {
		return ip_port_lan;
	}




	public void setIp_port_lan(Integer ip_port_lan) {
		this.ip_port_lan = ip_port_lan;
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




	public Long getIp4_target_wan() {
		return ip4_target_wan;
	}




	public void setIp4_target_wan(Long ip4_target_wan) {
		this.ip4_target_wan = ip4_target_wan;
	}




	public String getIp6_target_wan() {
		return ip6_target_wan;
	}




	public void setIp6_target_wan(String ip6_target_wan) {
		this.ip6_target_wan = ip6_target_wan;
	}




	public Integer getIp4_target_port_wan() {
		return ip4_target_port_wan;
	}




	public void setIp4_target_port_wan(Integer ip4_target_port_wan) {
		this.ip4_target_port_wan = ip4_target_port_wan;
	}




	public Integer getIp6_target_port_wan() {
		return ip6_target_port_wan;
	}




	public void setIp6_target_port_wan(Integer ip6_target_port_wan) {
		this.ip6_target_port_wan = ip6_target_port_wan;
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


	
	public String toBcpString(){
		String ret="";
		ret += log_id + "\t" + area_id + "\t" + allap_id + "\t"
				+ session_id + "\t" + create_time + "\t" + create_time_display
				+  "\t" + create_time_save + "\t" + auth_area_code
				+ "\t" + protocol_type + "\t" + protocol_type_display + "\t"
				+ ip_lan + "\t" + ip_port_lan + "\t" + ip4_wan + "\t" + ip6_wan
				+ "\t" + ip4_port_start_wan + "\t" + ip4_port_end_wan
				+ "\t" + ip6_port_start_wan + "\t" + ip6_port_end_wan
				+ "\t" + ip4_target_wan + "\t" + ip6_target_wan + "\t"
				+ ip4_target_port_wan + "\t" + ip6_target_port_wan + "\t" + user_mac
				+ "\t" + ap_code + "\t" + ap_mac + "\t" + ap_longitude + "\t"
				+ ap_latitude ;
		
		return ret;
	}




	@Override
	public String toString() {
		return "Netlog [log_id=" + log_id + ", area_id=" + area_id + ", allap_id=" + allap_id + ", session_id="
				+ session_id + ", create_time=" + create_time + ", create_time_display=" + create_time_display
				+ ", aabbcc=" + aabbcc + ", create_time_save=" + create_time_save + ", auth_area_code=" + auth_area_code
				+ ", protocol_type=" + protocol_type + ", protocol_type_display=" + protocol_type_display + ", ip_lan="
				+ ip_lan + ", ip_port_lan=" + ip_port_lan + ", ip4_wan=" + ip4_wan + ", ip6_wan=" + ip6_wan
				+ ", ip4_port_start_wan=" + ip4_port_start_wan + ", ip4_port_end_wan=" + ip4_port_end_wan
				+ ", ip6_port_start_wan=" + ip6_port_start_wan + ", ip6_port_end_wan=" + ip6_port_end_wan
				+ ", ip4_target_wan=" + ip4_target_wan + ", ip6_target_wan=" + ip6_target_wan + ", ip4_target_port_wan="
				+ ip4_target_port_wan + ", ip6_target_port_wan=" + ip6_target_port_wan + ", user_mac=" + user_mac
				+ ", ap_code=" + ap_code + ", ap_mac=" + ap_mac + ", ap_longitude=" + ap_longitude + ", ap_latitude="
				+ ap_latitude + "]";
	}

	public static void main(String[] args){
		System.out.println("time="+System.currentTimeMillis());
	}
	


	
	

	
}