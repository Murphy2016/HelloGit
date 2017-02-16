package com.qianmo.version.basic;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface VersionDao {
	public List<Version> findVersionById(Integer device_id) throws SQLException;
	public List<Version> findVersionByPage(Integer offset, Integer rows) throws SQLException;
	public Version addVersion(Version version) throws SQLException;
	public Integer findVersionCount() throws SQLException;
	public Integer findSoftwareCount() throws SQLException;
	public Integer findDeviceCount() throws SQLException;
	public List<Version> findVersionByBizs(String biz_nums) throws SQLException;
	public List<Version> findGroupCount() throws SQLException;
	public List<Version> findSoftwareGroupCount() throws SQLException;
	public Integer findVersionCountBySearch(Version version) throws SQLException;
	public Integer findVersionDeviceCountBySearch(Version version) throws SQLException;
	public Integer findVersionSoftwareCountBySearch(Version version) throws SQLException;
	public Integer findVersionVersionCountBySearch(Version version) throws SQLException;
	public List<Version> findVersionBySearch(Version version) throws SQLException;
	
	public Integer findVersionCountBySearchNot(Version version) throws SQLException;
	public List<Version> findVersionBySearchNot(Version version) throws SQLException;
	
	public List<Map> findHotlist(String wifi_sn) throws SQLException;
	public List<Map> findBindlist(String wifi_sn) throws SQLException;
	
	public Integer updateVersion(Version version) throws SQLException;
	public Integer deleteVersion(Integer device_id) throws SQLException;
	
	public void addHotlist(Map hotlist) throws SQLException;
	
	public void addBindlist(Map bindlist) throws SQLException;
	
}
