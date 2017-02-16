package com.qianmo.version.login;

import java.sql.SQLException;

public interface SysUserDao {
	public SysUser findSysUserByName(String user_name,String password) throws SQLException;
}
