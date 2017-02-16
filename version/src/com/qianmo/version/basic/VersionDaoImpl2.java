package com.qianmo.version.basic;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
public class VersionDaoImpl2 implements VersionDao2{
	@Resource
	private SqlMapClient sqlMapClient2;
	
	public Integer findWifiDeviceCount() throws SQLException{
		Integer ret=null;
		ret = (Integer)sqlMapClient2.queryForObject("findWifiDeviceCount");
		return ret;
	}
	
	public List<Version> findAllWifiDevice() throws SQLException{
		List<Version> ret = null;
		
		
		ret = (List<Version>)sqlMapClient2.queryForList("findAllWifiDevice");
		return ret;
	}
	
}
