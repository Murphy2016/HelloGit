package com.qianmo.version.login;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
public class SysUserDaoImpl implements SysUserDao{
	@Resource
	private SqlMapClient sqlMapClient;
	
	public SysUser findSysUserByName(String user_name,String password) throws SQLException{
		SysUser sysUser=null;
		Map map = new HashMap();
		map.put("user_name", user_name);
		map.put("password", password);
		
		
		sysUser = (SysUser)sqlMapClient.queryForObject("findSysUserByName", map);
		
		return sysUser;
	}

}
