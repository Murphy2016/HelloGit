package com.qianmo.gawa.util;
import java.util.ArrayList;
import java.util.HashMap;  
import java.util.Map;

import com.qianmo.gawa.netlog.Netlog;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject; 

public class TestMain {
	private String url = "https://101.201.53.158:8443/gawa/netlog/netlogs";  
    private String charset = "utf-8";  
    private HttpClientUtil httpClientUtil = null;  
      
    public TestMain(){  
        httpClientUtil = new HttpClientUtil();  
    }  
      
    public void test(){  
        /*String httpOrgCreateTest = url + "httpOrg/create";  
        Map<String,String> createMap = new HashMap<String,String>();  
        createMap.put("authuser","*****");  
        createMap.put("authpass","*****");  
        createMap.put("orgkey","****");  
        createMap.put("orgname","****");  
        String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest,createMap,charset);  
        System.out.println("result:"+httpOrgCreateTestRtn); */ 
    	String url="https://101.201.53.158:8443/gawa/netlog/netlogs";
    	String body = null;
    	Netlog netlog = new Netlog();
    	ArrayList list = new ArrayList();
    	list.add(netlog);
    	JSONArray jsonArray = JSONArray.fromObject(list);
    	
    			
    	httpClientUtil.post(url, jsonArray.toString());
    }  
      
    public static void main(String[] args){  
        TestMain main = new TestMain();  
        main.test();  
    }
}
