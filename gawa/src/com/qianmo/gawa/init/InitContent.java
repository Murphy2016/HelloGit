package com.qianmo.gawa.init;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.lx.util.Log;
import com.qianmo.gawa.ap.Ap;
import com.qianmo.gawa.ap.ApDao;
import com.qianmo.gawa.netlog.Netlog;
import com.qianmo.gawa.netlog.NetlogDao;
import com.server.report.server.DataProcessListener;
import com.server.report.server.ReportServer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class InitContent implements ApplicationListener<ContextRefreshedEvent> 
	 {
	
	@Resource
	private ApDao apDao;
	
	@Resource
	private NetlogDao netlogDao;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("start!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	//需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
	}
	
	/*public static ReportServer reportServer;
	 static{
	  try{
		  Log.println("report server start............");
	   reportServer = new ReportServer("/home/lixb/config/",true);
	   reportServer.setDataProcessListener(new DataProcessListener(){
		   public void unpack(int deviceId,int len,DataInputStream in_data) {
				 try{
				 int ptlId = in_data.readInt();
				 byte[] bs = new byte[len-4];
				 
				in_data.read(bs);
				
				 String jasonStr = new String(bs,"utf-8");
				 System.out.println(jasonStr);
				 switch(ptlId){
				 case 0:
					 //open
					 break;
					 
				 case 1:
					 //heart
					 break;
					 
				 case 2:
					 //netlog
					 
					 String newlogs = URLDecoder.decode(jasonStr);
						newlogs = newlogs.replace("=", "");
						//System.out.println(newlogs);
						String ret = "success";
						
						//TestUser user = (TestUser)JSONArray.toBean(testUser,TestUser.class);
						JSONArray netlogs1 = JSONArray.fromObject(newlogs);
						List<Netlog> netlogs2 = JSONArray.toList(netlogs1,Netlog.class);
						Netlog tmp = netlogs2.get(0);
						//dataBeat(tmp.getAp_code(),tmp.getAp_mac());
						for(Netlog log:netlogs2){
							try {
								String sessionid = log.getSession_id();
								sessionid = sessionid.replace("null", "34010026000001");
								log.setSession_id(sessionid);
								log.setAuth_area_code("34010026000001");
								//netlogDao.addNetlog(log);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								ret = "wrong data format!";
							}
						}
						
						
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 break;
					 
				 case 3:
					 //onoffline;
					 break;
				 }
				 }catch(Exception e){
					 e.printStackTrace();
				 }
			 }
	   });
	  
	   reportServer.start();
	   
	   System.out.println("ReportServer start!!!!!");
	  }catch(Exception exp){
	   Log.println(exp);
	  }
	 }
	 
		private void dataBeat(String ap_code,String ap_mac){
			
			List<Ap> result=null;
			
			try {
				result = apDao.findApByCode(ap_code,null);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("dataBeat first failed!!!!");
				
			}
			if(result!=null && result.size()>0){
				Ap ap = new Ap();
				ap.setEquipment_code(ap_code);
				ap.setMac(ap_mac);
				ap.setData_state(1);
				ap.setState(1);
				
				ap.setData_state_time(System.currentTimeMillis()/1000);
				ap.setState_time(System.currentTimeMillis()/1000);
				
				try {
					apDao.updateApDataState(ap);
					apDao.updateApAllState(ap);
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("dataBeat second failed!!!!");
				}
			}
		}*/
}
