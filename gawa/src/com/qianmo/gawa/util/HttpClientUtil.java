package com.qianmo.gawa.util;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
import java.util.Map.Entry;  
import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.NameValuePair;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils;  

public class HttpClientUtil {
	public static void main(String[] args){
		String url="";
		Map map = new HashMap();
		map.put("", "");
		doPost(url,map,"utf-8");
	}
	
	public static String doPost(String url,Map<String,String> map,String charset){  
        HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        try{  
            httpClient = new SSLClient();  
            httpPost = new HttpPost(url);  
            //设置参数  
            List<NameValuePair> list = new ArrayList<NameValuePair>();  
            Iterator iterator = map.entrySet().iterator();  
            while(iterator.hasNext()){  
                Entry<String,String> elem = (Entry<String, String>) iterator.next();  
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
            }  
            if(list.size() > 0){  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);  
                httpPost.setEntity(entity);  
            }  
            HttpResponse response = httpClient.execute(httpPost);  
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,charset);  
                }  
            }  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return result;  
    }
	
	public String post(String url,String parameters) {  
		 long startTime = 0L;  
	     long endTime = 0L;  
	     int status = 0;  
		HttpClient httpClient = null;  
		HttpPost httpPost = null;  
		String result = null; 
        String body = null;  
        System.out.println("parameters:" + parameters);  
        try {
			httpClient = new SSLClient();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
        httpPost = new HttpPost(url);
        
        if (httpPost != null & parameters != null  
                && !"".equals(parameters.trim())) {  
            try {  
  
                // 建立一个NameValuePair数组，用于存储欲传送的参数  
            	httpPost.addHeader("Content-type","application/json; charset=utf-8");  
            	httpPost.setHeader("Accept", "application/json"); 
            	HttpEntity entity = new StringEntity(parameters);
            	httpPost.setEntity(entity);  
                startTime = System.currentTimeMillis();  
  
                HttpResponse response = httpClient.execute(httpPost);  
                  
                endTime = System.currentTimeMillis();  
                int statusCode = response.getStatusLine().getStatusCode();  
                  
                System.out.println("statusCode:" + statusCode);  
                System.out.println("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));  
                if (statusCode != 200) {  
                	System.out.println("Method failed:" + response.getStatusLine());  
                    status = 1;  
                }  
  
                // Read the response body  
                body = EntityUtils.toString(response.getEntity());
                System.out.println(body);
  
            } catch (Exception e) {  
                // 网络错误  
                status = 3;  
            } finally {  
            	System.out.println("调用接口状态：" + status);  
            }  
  
        }  
        return body;  
    }
}
