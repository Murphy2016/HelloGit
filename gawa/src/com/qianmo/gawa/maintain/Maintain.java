package com.qianmo.gawa.maintain;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */

public class Maintain implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int maintain_id;
	private String area_code;
	private String equipment_code;
	private String mac;
	
//	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date1;
	private String note;
	private Integer need_time;
	private String need_time_display;
	


	public String getNeed_time_display() {
		need_time_display= need_time.toString()+"分钟";
		return need_time_display;
	}



	public void setNeed_time_display(String need_time_display) {
		this.need_time_display = need_time_display;
	}

































	public Integer getMaintain_id() {
		return maintain_id;
	}

































	public String getEquipment_code() {
		return equipment_code;
	}

































	public void setEquipment_code(String equipment_code) {
		this.equipment_code = equipment_code;
	}

































	public void setMaintain_id(Integer maintain_id) {
		this.maintain_id = maintain_id;
	}

































	
































	
































	public String getArea_code() {
		return area_code;
	}

































	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

































	public String getMac() {
		return mac;
	}

































	public void setMac(String mac) {
		this.mac = mac;
	}

































	/*public String getDate1() {
		String ret = null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		if(date1 != null)
			ret = sdf.format(date1); 
		return ret;
	}*/
	public Date getDate1() {
		 
		return date1;
	}
































	public void setDate1(Date date1) {
		this.date1 = date1;
	}

































	public String getNote() {
		return note;
	}

































	public void setNote(String note) {
		this.note = note;
	}

































	public Integer getNeed_time() {
		return need_time;
	}

































	public void setNeed_time(Integer need_time) {
		this.need_time = need_time;
	}

































	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "Maintain [maintain_id=" + maintain_id + ", area_code=" + area_code + ", equipment_code="
				+ equipment_code + ", mac=" + mac + ", date1=" + date1 + ", note=" + note + ", need_time=" + need_time
				+ ", need_time_display=" + need_time_display + "]";
	}

	
	

	
}