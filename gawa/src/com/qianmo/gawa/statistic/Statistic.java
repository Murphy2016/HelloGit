package com.qianmo.gawa.statistic;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */

public class Statistic implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long statistic_id;
	
	
	private Integer total=0;
	private Integer working=0;
	private Integer unworking=0;
	
	private Integer id;
	private String mobileno;
	private Integer count=0;
	private Integer mac=0;
	private Integer imsi=0;
	private Integer uid=0;
	private Date date1;
	private String displaydate;
	private Integer service_online=0;
	private Integer service_offline=0;
	private Integer data_online=0;
	private Integer date_offline=0;
	
	private Integer maintain=0;
	private Integer exception=0;
	private Integer other=0;
	private Integer bus=0;
	
	private String realname_rate="100%";
	
	
	public String getDisplaydate() {
		return displaydate;
	}
	public void setDisplaydate(String displaydate) {
		this.displaydate = displaydate;
	}
	public Long getStatistic_id() {
		return statistic_id;
	}
	public void setStatistic_id(Long statistic_id) {
		this.statistic_id = statistic_id;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getWorking() {
		return working;
	}
	public void setWorking(Integer working) {
		this.working = working;
	}
	public Integer getUnworking() {
		return unworking;
	}
	public void setUnworking(Integer unworking) {
		this.unworking = unworking;
	}
	public String getDate1() {
		if(date1==null) return "";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		String sDateTime = sdf.format(date1);
		return sDateTime;
	}
	public void setDate1(Date date1) {
		this.date1 = date1;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		String sDateTime = sdf.format(date1);
		displaydate = sDateTime;
		
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getMac() {
		return mac;
	}
	public void setMac(Integer mac) {
		this.mac = mac;
	}
	public Integer getImsi() {
		return imsi;
	}
	public void setImsi(Integer imsi) {
		this.imsi = imsi;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	
	
	public Integer getService_online() {
		return service_online;
	}
	public void setService_online(Integer service_online) {
		this.service_online = service_online;
	}
	public Integer getService_offline() {
		return service_offline;
	}
	public void setService_offline(Integer service_offline) {
		this.service_offline = service_offline;
	}
	public Integer getData_online() {
		return data_online;
	}
	public void setData_online(Integer data_online) {
		this.data_online = data_online;
	}
	public Integer getDate_offline() {
		return date_offline;
	}
	public void setDate_offline(Integer date_offline) {
		this.date_offline = date_offline;
	}
	public Integer getMaintain() {
		return maintain;
	}
	public void setMaintain(Integer maintain) {
		this.maintain = maintain;
	}
	public Integer getException() {
		return exception;
	}
	public void setException(Integer exception) {
		this.exception = exception;
	}
	public Integer getOther() {
		return other;
	}
	public void setOther(Integer other) {
		this.other = other;
	}
	
	
	
	
	
	public Integer getBus() {
		return bus;
	}
	public void setBus(Integer bus) {
		this.bus = bus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRealname_rate() {
		return realname_rate;
	}
	public void setRealname_rate(String realname_rate) {
		this.realname_rate = realname_rate;
	}
	

	
}