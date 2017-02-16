package com.qianmo.gawa.ap;





/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */

public class Ap implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long allap_id;
	private Long area_id;
	private String equipment_code;
	private String mac;
	private String area_code;
	private String longitude;
	private String latitude;
	private String floor1;
	private String station_info;
	private String subway_line_info;
	private String subway_vehical_info;
	private String subway_compartment_no;
	private String car_code;
	private Integer state;//服务是否在线
	private Long state_time;
	private Integer day_state;
	private String display_state;
	private Integer approval;
	private String display_approval;
	private Integer data_state;
	private String display_data_state;
	private Long data_state_time; 
	
	private Integer working_state=1;
	private String display_working_state;

	public Long getAllap_id() {
		return allap_id;
	}
	public void setAllap_id(Long allap_id) {
		this.allap_id = allap_id;
	}
	public Long getArea_id() {
		return area_id;
	}
	public void setArea_id(Long area_id) {
		this.area_id = area_id;
	}
	public String getEquipment_code() {
		return equipment_code;
	}
	public void setEquipment_code(String equipment_code) {
		this.equipment_code = equipment_code;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getFloor1() {
		return floor1;
	}
	public void setFloor1(String floor1) {
		this.floor1 = floor1;
	}
	public String getStation_info() {
		return station_info;
	}
	public void setStation_info(String station_info) {
		this.station_info = station_info;
	}
	public String getSubway_line_info() {
		return subway_line_info;
	}
	public void setSubway_line_info(String subway_line_info) {
		this.subway_line_info = subway_line_info;
	}
	public String getSubway_vehical_info() {
		return subway_vehical_info;
	}
	public void setSubway_vehical_info(String subway_vehical_info) {
		this.subway_vehical_info = subway_vehical_info;
	}
	public String getSubway_compartment_no() {
		return subway_compartment_no;
	}
	public void setSubway_compartment_no(String subway_compartment_no) {
		this.subway_compartment_no = subway_compartment_no;
	}
	public String getCar_code() {
		return car_code;
	}
	public void setCar_code(String car_code) {
		this.car_code = car_code;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		if(state==null)state=0;
		this.state = state;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDisplay_state() {
		if(state == null){
			display_state = "设备故障";
		}else if(state == 1){
			display_state = "服务在线";
		}else if(state == 0){
			display_state = "服务离线";
		}
		return display_state;
	}
	public void setDisplay_state(String display_state) {
		this.display_state = display_state;
	}
	public Integer getDay_state() {
		return day_state;
	}
	public void setDay_state(Integer day_state) {
		if(day_state==null)day_state=0;
		this.day_state = day_state;
	}
	public Long getState_time() {
		return state_time;
	}
	public void setState_time(Long state_time) {
		this.state_time = state_time;
	}
	public Integer getApproval() {
		return approval;
	}
	public void setApproval(Integer approval) {
		this.approval = approval;
	}
	public String getDisplay_approval() {
		if(approval==0){
			display_approval="未审批";
		}else{
			display_approval="已审批";
		}
		return display_approval;
	}
	public void setDisplay_approval(String display_approval) {
		this.display_approval = display_approval;
	}
	public Integer getData_state() {
		return data_state;
	}
	public void setData_state(Integer data_state) {
		if(data_state==null)data_state=0;
		this.data_state = data_state;
	}
	public String getDisplay_data_state() {
		if(data_state==0){
			display_data_state="数据离线";
		}else{
			display_data_state="数据在线";
		}
		return display_data_state;
	}
	public void setDisplay_data_state(String display_data_state) {
		this.display_data_state = display_data_state;
	}
	public Long getData_state_time() {
		return data_state_time;
	}
	public void setData_state_time(Long data_state_time) {
		this.data_state_time = data_state_time;
	}
	public Integer getWorking_state() {
		if(working_state==4){
			return working_state;
		}
		if( (state==1 && data_state==1) ||
				(state==1 && data_state==0)	){
			working_state=1;
		}else if(state==0 && data_state==0){
			working_state=2;
		}else{
			working_state=3;
		}
		return working_state;
	}
	public void setWorking_state(Integer working_state) {
		this.working_state = working_state;
	}
	public String getDisplay_working_state() {
		Integer tmp = getWorking_state();
		if(tmp==1){
			display_working_state="正常工作";
		}else if(tmp==2){
			display_working_state="离线状态";
		}else if(tmp==3){
			display_working_state="异常状态";
		}else {
			display_working_state="维修状态";
		}
		return display_working_state;
	}
	public void setDisplay_working_state(String display_working_state) {
		this.display_working_state = display_working_state;
	}
	@Override
	public String toString() {
		return  allap_id + "\t" + area_id + "\t" + equipment_code + "\t"
				+ mac + "\t" + area_code + "\t" + longitude + "\t" + latitude + "\t"
				+ floor1 + "\t" + station_info + "\t" + subway_line_info
				+ "\t" + subway_vehical_info + "\t" + subway_compartment_no
				+ "\t" + car_code + "\t" + state + "\t" + state_time + "\t"
				+ day_state + "\t" + display_state + "\t" + approval + "\t"
				+ display_approval + "\t" + data_state + "t" + display_data_state
				+ "\t" + data_state_time + "\t" + working_state
				+ "\t" + display_working_state ;
	}
	

	
	


	
	

	
}