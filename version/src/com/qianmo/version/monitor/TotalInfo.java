package com.qianmo.version.monitor;

public class TotalInfo {
	private String date;
	private Integer total_count;
	private Integer poweron_count;
	private Integer poweron_total_time;
	private Integer poweron_aver_time;
	
	private Integer com_count;
	private Integer com_total_time;
	private Integer com_aver_time;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getTotal_count() {
		return total_count;
	}
	public void setTotal_count(Integer total_count) {
		this.total_count = total_count;
	}
	public Integer getPoweron_count() {
		return poweron_count;
	}
	public void setPoweron_count(Integer poweron_count) {
		this.poweron_count = poweron_count;
	}
	public Integer getPoweron_total_time() {
		return poweron_total_time;
	}
	public void setPoweron_total_time(Integer poweron_total_time) {
		this.poweron_total_time = poweron_total_time;
	}
	public Integer getPoweron_aver_time() {
		return poweron_aver_time;
	}
	public void setPoweron_aver_time(Integer poweron_aver_time) {
		this.poweron_aver_time = poweron_aver_time;
	}
	public Integer getCom_count() {
		return com_count;
	}
	public void setCom_count(Integer com_count) {
		this.com_count = com_count;
	}
	public Integer getCom_total_time() {
		return com_total_time;
	}
	public void setCom_total_time(Integer com_total_time) {
		this.com_total_time = com_total_time;
	}
	public Integer getCom_aver_time() {
		return com_aver_time;
	}
	public void setCom_aver_time(Integer com_aver_time) {
		this.com_aver_time = com_aver_time;
	}
	public TotalInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TotalInfo(String date, Integer total_count, Integer poweron_count, Integer poweron_total_time,
			Integer poweron_aver_time, Integer com_count, Integer com_total_time, Integer com_aver_time) {
		super();
		this.date = date;
		this.total_count = total_count;
		this.poweron_count = poweron_count;
		this.poweron_total_time = poweron_total_time;
		this.poweron_aver_time = poweron_aver_time;
		this.com_count = com_count;
		this.com_total_time = com_total_time;
		this.com_aver_time = com_aver_time;
	}
	
	
	
	
}
