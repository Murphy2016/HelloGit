package com.qianmo.gawa.netlog;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.qianmo.gawa.ap.Ap;
import com.qianmo.gawa.ap.ApDao;
import com.qianmo.gawa.area.Area;
import com.qianmo.gawa.area.AreaDao;
import com.qianmo.gawa.org.Org;
import com.qianmo.gawa.org.OrgDao;
import com.qianmo.gawa.util.BcpWrite;
import com.qianmo.gawa.util.Xml2Java;
import com.qianmo.gawa.util.ZipUtil;



@Component
public class NetlogServiceImpl implements NetlogService {
	@Autowired
	private NetlogDao netlogDao;
	
	@Autowired
	private AreaDao areaDao;
	
	@Autowired
	private ApDao apDao;

	@Autowired
	private OrgDao orgDao;
	
	/**
	 * 
	 */
	@Override
	//do it every day 00:00.
	@Scheduled(cron = "0 49 15 * * ?")
	//@Scheduled(cron = "0 10 * * * ?")
	public void Jobtozip() throws Exception{
		List<Netlog> result = null;
		Integer total = null;
		try {
			
				result = (List<Netlog>)netlogDao.findNetlogByPage(1,10000);
				total= result.size();
				//total= netlogDao.findNetlogCount();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Netlogs netlogs = new Netlogs(result);
		String path = this.getClass().getClassLoader().getResource("").getPath();
		System.out.println(path);
		path = path.replace("WEB-INF/classes/", "");
		String path1 = path + "netlog/";
		Long time = System.currentTimeMillis()/1000;
		List<String> list = new ArrayList<String>();
		for(Netlog elem:netlogs.getNetlogList()){
			list.add(elem.toBcpString());
		}
		//Xml2Java.beanToXML(netlogs,path1+"145-330000-"+time.toString()+"-00001-WA-SOURCE_NETLOG_0001-0.xml");
		BcpWrite.BcpWriteFile(list,path1+"145-330000-"+time.toString()+"-00001-WA-SOURCE_NETLOG_0001-0.bcp");

		ZipUtil.ZipMultiFile(path1, path+"zip/netlog/"+"145-353030334-330300-330300-"+time.toString()+"-00001.zip");
	
	}
	
	@Scheduled(cron = "0 48 15 * * ?")
	public void areaInfoZip(){
		List<Area> result = null;
		Integer total = null;
		try {
			
				result = (List<Area>)areaDao.findAreaByPage(0, 1000);
				total= result.size();
				//total= netlogDao.findNetlogCount();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String path = this.getClass().getClassLoader().getResource("").getPath();
		System.out.println(path);
		path = path.replace("WEB-INF/classes/", "");
		String path1 = path + "areainfo/";
		Long time = System.currentTimeMillis()/1000;
		List<String> list = new ArrayList<String>();
		for(Area elem:result){
			list.add(elem.toString());
		}
		//Xml2Java.beanToXML(netlogs,path1+"145-330000-"+time.toString()+"-00001-WA-SOURCE_NETLOG_0001-0.xml");
		BcpWrite.BcpWriteFile(list,path1+"145-330000-"+time.toString()+"-00001-WA-SOURCE_AREAINFO_0001-0.bcp");

		ZipUtil.ZipMultiFile(path1, path+"zip/areainfo/"+"145-353030334-330300-330300-"+time.toString()+"-00001.zip");
	
		
		
	}
	
	@Scheduled(cron = "0 47 15 * * ?")
	public void apZip(){
		List<Ap> result = null;
		Integer total = null;
		try {
			
				result = (List<Ap>)apDao.findApByPage(0, 1000);
				total= result.size();
				//total= netlogDao.findNetlogCount();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String path = this.getClass().getClassLoader().getResource("").getPath();
		System.out.println(path);
		path = path.replace("WEB-INF/classes/", "");
		String path1 = path + "mobileap/";
		Long time = System.currentTimeMillis()/1000;
		List<String> list = new ArrayList<String>();
		for(Ap elem:result){
			list.add(elem.toString());
		}
		//Xml2Java.beanToXML(netlogs,path1+"145-330000-"+time.toString()+"-00001-WA-SOURCE_NETLOG_0001-0.xml");
		BcpWrite.BcpWriteFile(list,path1+"145-330000-"+time.toString()+"-00001-WA-SOURCE_MOBILEAP_0001-0.bcp");

		ZipUtil.ZipMultiFile(path1, path+"zip/mobileap/"+"145-353030334-330300-330300-"+time.toString()+"-00001.zip");
	
		
		
	}
	
	@Scheduled(cron = "0 46 15 * * ?")
	public void orgZip(){
		List<Org> result = null;
		Integer total = null;
		try {
			
				result = (List<Org>)orgDao.findOrgByPage(0, 1000);
				total= result.size();
				//total= netlogDao.findNetlogCount();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String path = this.getClass().getClassLoader().getResource("").getPath();
		System.out.println(path);
		path = path.replace("WEB-INF/classes/", "");
		String path1 = path + "safefactory/";
		Long time = System.currentTimeMillis()/1000;
		List<String> list = new ArrayList<String>();
		for(Org elem:result){
			list.add(elem.toString());
		}
		//Xml2Java.beanToXML(netlogs,path1+"145-330000-"+time.toString()+"-00001-WA-SOURCE_NETLOG_0001-0.xml");
		BcpWrite.BcpWriteFile(list,path1+"145-330000-"+time.toString()+"-00001-WA-SOURCE_SAFEFACTORY_0001-0.bcp");

		ZipUtil.ZipMultiFile(path1, path+"zip/safefactory/"+"145-353030334-330300-330300-"+time.toString()+"-00001.zip");
	
		
		
	}
	
	@Scheduled(cron = "0 45 15 * * ?")
	public void fixapZip(){
		
		String path = this.getClass().getClassLoader().getResource("").getPath();
		System.out.println(path);
		path = path.replace("WEB-INF/classes/", "");
		String path1 = path + "fixedap/";
		Long time = System.currentTimeMillis()/1000;
		List<String> list = new ArrayList<String>();
		
		//Xml2Java.beanToXML(netlogs,path1+"145-330000-"+time.toString()+"-00001-WA-SOURCE_NETLOG_0001-0.xml");
		//BcpWrite.BcpWriteFile(list,path1+"145-330000-"+time.toString()+"-00001-WA-SOURCE_SAFEFACTORY_0001-0.bcp");

		ZipUtil.ZipMultiFile(path1, path+"zip/fixedap/"+"145-353030334-330300-330300-"+time.toString()+"-00001.zip");
	
		
		
	}
	
	
}
