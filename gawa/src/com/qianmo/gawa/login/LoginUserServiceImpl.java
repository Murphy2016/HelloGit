package com.qianmo.gawa.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class LoginUserServiceImpl implements LoginUserService {
	@Autowired
	private LoginUserDao loginUserDao;

	/**
	 * 
	 */
	public LoginUser findByName(String username) throws Exception {
		return this.loginUserDao.findByName(username);
	}
	
	public LoginUser findByNameAndId(LoginModel loginModel) throws Exception {
		
		
		return this.loginUserDao.findByNameAndId(loginModel);
	}
	
	public LoginUser findByNameAndPassword(LoginModel loginModel) throws Exception {
		
		
		return this.loginUserDao.findByNameAndPassword(loginModel);
	}
	
	
	public LoginUser findByKey(String key_no) throws Exception {
		return this.loginUserDao.findByKey(key_no);
	}
}
