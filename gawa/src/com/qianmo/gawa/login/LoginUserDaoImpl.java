package com.qianmo.gawa.login;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.ibatis.sqlmap.client.SqlMapClient;
/***
 * 服务端 DaoImpl
 * @author AndLiSoft
 *
 */
@Repository
@Transactional
public class LoginUserDaoImpl implements LoginUserDao{
	@Resource
	private SqlMapClient sqlMapClient;
	
	@Override
	public LoginUser findByName(String username) throws SQLException {
		return (LoginUser) sqlMapClient.queryForObject("findByName", username);
	}
	
	@Override
	public LoginUser findByNameAndId(LoginModel loginModel) throws SQLException {
		
		
		Map map = new HashMap();
        map.put("user_name", loginModel.getUsername());
        map.put("id_card", loginModel.getId_card());
		
		return (LoginUser) sqlMapClient.queryForObject("findByNameAndId", map);
	}
	
	@Override
	public LoginUser findByNameAndPassword(LoginModel loginModel) throws SQLException {
		
		
		Map map = new HashMap();
        map.put("user_name", loginModel.getUsername());
        map.put("password", loginModel.getPasswd());
		
		return (LoginUser) sqlMapClient.queryForObject("findByNameAndPassword", map);
	}
	
	
	
	@Override
	public LoginUser findByKey(String key_no) throws SQLException {
		return (LoginUser) sqlMapClient.queryForObject("findByKey", key_no);
	}

}
