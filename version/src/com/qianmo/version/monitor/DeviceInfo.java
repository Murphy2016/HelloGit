package com.qianmo.version.monitor;

public class DeviceInfo {
	private String basic;
	private Integer total_time;
	private String position;
	private Float longitude;
	private Float latitude;
	private String sn;
	public String getBasic() {
		return basic;
	}
	public void setBasic(String basic) {
		this.basic = basic;
	}
	public Integer getTotal_time() {
		return total_time;
	}
	public void setTotal_time(Integer total_time) {
		this.total_time = total_time;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Float getLongitude() {
		return longitude;
	}
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	public Float getLatitude() {
		return latitude;
	}
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public DeviceInfo(String basic, Integer total_time, String position, Float longitude, Float latitude, String sn) {
		super();
		this.basic = basic;
		this.total_time = total_time;
		this.position = position;
		this.longitude = longitude;
		this.latitude = latitude;
		this.sn = sn;
	}
	public DeviceInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
