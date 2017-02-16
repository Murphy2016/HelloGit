package com.qianmo.gawa.login;



public interface LoginUserService {
	LoginUser findByName(String username) throws Exception;
	
	LoginUser findByNameAndId(LoginModel loginModel) throws Exception;
	
	LoginUser findByNameAndPassword(LoginModel loginModel) throws Exception;
	
	LoginUser findByKey(String key_no) throws Exception;
}
