package com.qianmo.gawa.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.springframework.util.StringUtils;

/**
 *@author HuangJian
 *@date 2013-8-12
 **/
public class HttpUtil {

	private static final String APPLICATION_JSON = "application/json";
    
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
    
    public static String doPost(String url,Map<String,String> paraMap){
    	return doPost(url, paraMap, null);
    }
    
	public static String doPost(String url,Map<String,String> paraMap,String session){
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		
		HttpPost httpost = new HttpPost(url);
		String res;
		try{
			if(!StringUtils.isEmpty(session)){
				httpost.setHeader("Cookie", session);
			}
			
			List <NameValuePair> nvps = new ArrayList <NameValuePair>();
			if(paraMap!=null&&paraMap.size()>0){
				for(String key:paraMap.keySet()){
//					System.out.println(paraMap.get(key));
					nvps.add(new BasicNameValuePair(key, paraMap.get(key)));
				}
			}
			
			httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
			httpost.addHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity=response.getEntity();
			InputStream  in=entity.getContent();
			
			StringBuffer buff = new StringBuffer();
			byte[] b = new byte[1024];
			for (int n; (n = in.read(b)) != -1;) {
				buff.append(new String(b, 0, n));
			}
			res=buff.toString();
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			httpclient.getConnectionManager().shutdown();
		}
		
		return res;
	}
	
	public static String doPost(String url,String content){
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		
		HttpPost httpost = new HttpPost(url);
		String res;
		try{
			
			httpost.setEntity(new StringEntity(content));
			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity=response.getEntity();
			InputStream  in=entity.getContent();
			
			StringBuffer buff = new StringBuffer();
			byte[] b = new byte[1024];
			for (int n; (n = in.read(b)) != -1;) {
				buff.append(new String(b, 0, n));
			}
			res=buff.toString();
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			httpclient.getConnectionManager().shutdown();
		}
		
		return res;
	}
	

	public static String doGet(String url){

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		String res="";
		try{
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity=response.getEntity();
			InputStream  in=entity.getContent();
			
			StringBuffer buff = new StringBuffer();
			byte[] b = new byte[1024];
			for (int n; (n = in.read(b)) != -1;) {
				buff.append(new String(b, 0, n));
			}
			res=buff.toString();
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			httpclient.getConnectionManager().shutdown();
		}
		
		return res;
	
	}
	
	public static String doGet(String url,Map<String,Object> para){

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		String res="";
		
		if(!url.contains("?")){
			url=url+"?1=";
		}
		for(String key:para.keySet()){
			url=url+"&"+key+"="+para.get(key);
		}
		try{
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity=response.getEntity();
			InputStream  in=entity.getContent();
			
			StringBuffer buff = new StringBuffer();
			byte[] b = new byte[1024];
			for (int n; (n = in.read(b)) != -1;) {
				buff.append(new String(b, 0, n));
			}
			res=buff.toString();
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			httpclient.getConnectionManager().shutdown();
		}
		
		return res;
	
	} 
	
	public static String[] getProxy(int num){
	//	String url="http://www.kuaidaili.com/api/getproxy/?orderid=981544950353963&browser=1&protocol=1&sp1=1&sort=0&dedup=0&sep=1";
		String url="http://219.234.88.39/olnavy/sms/commitSmsList.htm?smsstr=[{'clientid':'weixin','createtime':1419300043310,'id':0,'message':'新任务提醒:ff  开始时间：2014-12-23 10:00:35  结束时间：2014-12-31 00:00:00  查看详情：null','result':0,'sendtime':0,'telnumber':'15311449605','userkey':'zgjjyxh'}]";
		
		//url=url+"&num="+num;
		System.out.println(url);
		String s=doGet(url);
		return s.split("\r\n");
	}
	private static void test1(){
		//String url="http://127.0.0.1:8080/callback/invest/preinsert";
		String url="http://101.201.53.158:8099/callback/invest/preinsert";
		Map map = new HashMap();
		map.put("phone", "13912345678");
		map.put("channel", "HK05");
		map.put("param", "user_id_189123");
		Long time = System.currentTimeMillis();
		map.put("timestamp", time.toString());
		
		String sign="phone="+"13912345678"+"&channel="+"HK05"
		+"&param="+"user_id_189123"+"&timestamp="+time.toString()
		+"&key=6VYRpsffvZdPvNR1RHeYlwF8VXPmQe818u7ZC94dwAx";
		
		System.out.println(sign);
		String md5sign = MD5Util.MD5(sign);
		
		map.put("sign", md5sign);
		
		
		String res = doPost(url,map);
		System.out.println(res);
	}
	private static void test2(){
		//String url="http://127.0.0.1:8080/callback/invest/postinsert";
		String url="http://101.201.53.158:8099/callback/invest/postinsert";
		Map map = new HashMap();
		map.put("investAmountFlag", "1000");
		map.put("investCapital", "2000");
		map.put("investCount", "36");
		map.put("phone", "13912345678");
		map.put("channel", "HK05");
		map.put("param", "user_id_189123");
		Long time = System.currentTimeMillis();
		map.put("timestamp", time.toString());
		
		
		
		String sign="investAmountFlag="+1000+
				"&investCapital="+2000
				+"&investCount="+36
				+"&param="+"user_id_189123"
				+"&phone="+"13912345678"
				
				+"&timestamp="+time.toString()
				+"&key=6VYRpsffvZdPvNR1RHeYlwF8VXPmQe818u7ZC94dwAx";
		
		System.out.println(sign);
		String md5sign = MD5Util.MD5(sign);
		
		map.put("sign", md5sign);
		
		
		String res = doPost(url,map);
		System.out.println(res);
	}
	public static void test3(){
		String url="http://101.201.53.158:8077/gawa/ap/apopen";
		String cnt = "{\"ap_code\":\"88888\",\"ap_mac\":\"77777\"}";
		String res = doPost(url,cnt);
		System.out.println(res);
	}
	public static void test4(){
		String url="http://127.0.0.1:8080/yiqichongWeb/device/newdevice";
		String cnt = "{\"device_no\":\"88888\",\"ap_mac\":\"77777\"}";
		String res = doPost(url,cnt);
		System.out.println(res);
	}
	public static void test5(){
		//String url="http://127.0.0.1:8080/version/version/getversion";
		String url="http://cloud.1000mob.com/version/getversion";
		String cnt = "[{\"biz_num\":\"000004970\"},{\"biz_num\":\"000011870\"}]";
		String res = doPost(url,cnt);
		System.out.println(res);
	}
	public static void test6(){
		String url="http://127.0.0.1:8080/version/version/gethotlist";
		//String url="http://cloud.1000mob.com/version/gethotlist";
		String cnt = "{\"sn\":\"000004970\"}";
		String res = doPost(url,cnt);
		System.out.println(res);
	}
	public static void main(String[] args){
		//String [] ss=getProxy(100);
		//System.out.println(ss);
		test6();
	}
}

