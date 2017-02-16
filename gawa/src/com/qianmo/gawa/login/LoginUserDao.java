package com.qianmo.gawa.login;

import java.sql.SQLException;





/***
 * 服务端 Dao
 * @author AndLiSoft
 *
 */
public interface LoginUserDao {
	public LoginUser findByName(String username) throws SQLException;
	
	public LoginUser findByNameAndId(LoginModel loginModel) throws SQLException;
	
	public LoginUser findByNameAndPassword(LoginModel loginModel) throws SQLException;
	
	public LoginUser findByKey(String key_no) throws SQLException;
}
