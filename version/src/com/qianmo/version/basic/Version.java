package com.qianmo.version.basic;

import com.qianmo.version.util.VersionUtil;

public class Version {
	private Integer device_id;
	private String biz_num;
	private String software_name;
	private Integer version;
	private String version_str="";
	private Integer flag;
	private Long update_time;
	private String update_time_str;
	private Long create_time;
	private String create_time_str;
	private String time1;
	private String time2;
	private Integer count=0;
	private Integer page=1;
	private Integer offset=0;
	private Integer rows=50;
	private String percent;
	private String percent1;
	private String wifi_sn;
	public Integer getDevice_id() {
		return device_id;
	}
	public void setDevice_id(Integer device_id) {
		this.device_id = device_id;
	}
	public String getBiz_num() {
		return biz_num;
	}
	public void setBiz_num(String biz_num) {
		this.biz_num = biz_num;
	}
	public String getSoftware_name() {
		return software_name;
	}
	public void setSoftware_name(String software_name) {
		this.software_name = software_name;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
		try {
			this.version_str = VersionUtil.getVersionIntToStr(version);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String getVersion_str() {
		return version_str;
	}
	public void setVersion_str(String version_str) {
		this.version_str = version_str;
		try {
			this.version = VersionUtil.getVersionStrToInt(version_str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Long getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Long update_time) {
		this.update_time = update_time;
		this.update_time_str = VersionUtil.getCurTimeStr(update_time);
	}
	public Long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
		this.create_time_str = VersionUtil.getCurTimeStr(create_time);
	}
	
	
	public String getUpdate_time_str() {
		return update_time_str;
	}
	public void setUpdate_time_str(String update_time_str) {
		this.update_time_str = update_time_str;
	}
	public String getCreate_time_str() {
		return create_time_str;
	}
	public void setCreate_time_str(String create_time_str) {
		this.create_time_str = create_time_str;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public String getPercent1() {
		return percent1;
	}
	public void setPercent1(String percent1) {
		this.percent1 = percent1;
	}
	
	
	
	public String getTime1() {
		return time1;
	}
	public void setTime1(String time1) {
		this.time1 = time1;
	}
	public String getTime2() {
		return time2;
	}
	public void setTime2(String time2) {
		this.time2 = time2;
	}
	
	
	
	public String getWifi_sn() {
		return wifi_sn;
	}
	public void setWifi_sn(String wifi_sn) {
		this.wifi_sn = wifi_sn;
	}
	@Override
	public String toString() {
		return "Version [device_id=" + device_id + ", biz_num=" + biz_num + ", software_name=" + software_name
				+ ", version=" + version + ", version_str=" + version_str + ", flag=" + flag + ", update_time="
				+ update_time + ", update_time_str=" + update_time_str + ", create_time=" + create_time
				+ ", create_time_str=" + create_time_str + ", time1=" + time1 + ", time2=" + time2 + ", count=" + count
				+ ", page=" + page + ", rows=" + rows + ", percent=" + percent + ", percent1=" + percent1 + "]";
	}
	
	
	
}
