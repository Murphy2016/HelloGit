package com.qianmo.gawa.operlog;

import javax.servlet.http.HttpSession;

public interface OperlogService {
	
	public void addOperlogToDb(String username, String operation);
	

}
