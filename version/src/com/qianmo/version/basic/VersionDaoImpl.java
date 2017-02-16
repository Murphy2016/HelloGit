package com.qianmo.version.basic;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
public class VersionDaoImpl implements VersionDao{
	@Resource
	private SqlMapClient sqlMapClient;
	
	public List<Version> findVersionById(Integer device_id) throws SQLException{
		List<Version> versionlist=null;
		versionlist = (List<Version>)sqlMapClient.queryForList("findVersionById",device_id);
		return versionlist;
	}
	public List<Version> findVersionByPage(Integer offset, Integer rows) throws SQLException{
		List<Version> ret = null;
		Map map = new HashMap();
		map.put("offset", offset);
		map.put("rows", rows);
		
		ret = (List<Version>)sqlMapClient.queryForList("findVersionByPage",map);
		return ret;
	}
	public Integer findVersionCount() throws SQLException{
		Integer ret=null;
		ret = (Integer)sqlMapClient.queryForObject("findVersionCount");
		return ret;
	}
	public Integer findSoftwareCount() throws SQLException{
		Integer ret=null;
		ret = (Integer)sqlMapClient.queryForObject("findSoftwareCount");
		return ret;
	}
	public Integer findDeviceCount() throws SQLException{
		Integer ret=null;
		ret = (Integer)sqlMapClient.queryForObject("findDeviceCount");
		return ret;
	}
	public List<Version> findVersionByBizs(String biz_nums) throws SQLException{
		

		List<Version> ret = (List<Version>)sqlMapClient.queryForList("findVersionByBizs",biz_nums);
		return ret;
	}
	public List<Version> findGroupCount() throws SQLException{

		
		List<Version> ret = (List<Version>)sqlMapClient.queryForList("findGroupCount");
		return ret;
	}
	public List<Version> findSoftwareGroupCount() throws SQLException{
		List<Version> ret = (List<Version>)sqlMapClient.queryForList("findSoftwareGroupCount");
		return ret;
	}
	public Integer findVersionCountBySearch(Version version) throws SQLException{
		Integer ret=null;
		ret = (Integer)sqlMapClient.queryForObject("findVersionCountBySearch",version);
		return ret;
	}
	public Integer findVersionDeviceCountBySearch(Version version) throws SQLException{
		Integer ret=null;
		ret = (Integer)sqlMapClient.queryForObject("findVersionDeviceCountBySearch",version);
		return ret;
	}
	public Integer findVersionSoftwareCountBySearch(Version version) throws SQLException{
		Integer ret=null;
		ret = (Integer)sqlMapClient.queryForObject("findVersionSoftwareCountBySearch",version);
		return ret;
	}
	public Integer findVersionVersionCountBySearch(Version version) throws SQLException{
		Integer ret=null;
		ret = (Integer)sqlMapClient.queryForObject("findVersionVersionCountBySearch",version);
		return ret;
	}

	public List<Version> findVersionBySearch(Version version) throws SQLException{
		List<Version> ret = (List<Version>)sqlMapClient.queryForList("findVersionBySearch",version);
		return ret;
	}
	
	public Integer findVersionCountBySearchNot(Version version) throws SQLException{
		Integer ret=null;
		ret = (Integer)sqlMapClient.queryForObject("findVersionCountBySearchNot",version);
		return ret;
	}
	public List<Version> findVersionBySearchNot(Version version) throws SQLException{
		List<Version> ret = (List<Version>)sqlMapClient.queryForList("findVersionBySearchNot",version);
		return ret;
	}
	
	public List<Map> findHotlist(String wifi_sn) throws SQLException{
		List<Map> ret = null;
		
		ret = (List<Map>)sqlMapClient.queryForList("findHotlist",wifi_sn);
		
		return ret;
	}
	public List<Map> findBindlist(String wifi_sn) throws SQLException{
		List<Map> ret = null;
		
		ret = (List<Map>)sqlMapClient.queryForList("findBindlist",wifi_sn);
		
		return ret;
	}
	
	public Version addVersion(Version version) throws SQLException{
		Version ret=null;
		ret = (Version)sqlMapClient.insert("addVersion",version); 
		return ret;
	}
	public Integer updateVersion(Version version) throws SQLException{
		Integer ret=null;
		ret = sqlMapClient.update("updateVersionById",version);
		return ret;
	}
	public Integer deleteVersion(Integer device_id) throws SQLException{
		Integer ret=null;
		ret = sqlMapClient.delete("deleteVersionById",device_id);
		return ret;
	}
	public void addHotlist(Map hotlist) throws SQLException{
		
		sqlMapClient.insert("addHotlist", hotlist);
		
	}
	public void addBindlist(Map bindlist) throws SQLException{
		
		sqlMapClient.insert("addBindlist", bindlist);
	}
}
