package com.qianmo.gawa.onoffline;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.qianmo.gawa.netlog.Netlog;
import com.qianmo.gawa.util.BcpWrite;
import com.qianmo.gawa.util.Xml2Java;
import com.qianmo.gawa.util.ZipUtil;



@Component
public class OnofflineServiceImpl implements OnofflineService {
	@Autowired
	private OnofflineDao onofflineDao;

	/**
	 * 
	 */
	@Override
	//do it every day 00:00.
	@Scheduled(cron = "0 50 15 * * ?")
	//@Scheduled(cron = "0 10 * * * ?")
	public void Jobtozip() throws Exception{
		List<Onoffline> result = null;
		Integer total = null;
		try {
			
				result = (List<Onoffline>)onofflineDao.findOnofflineByPage(1,10000);
				total= result.size();
				//total= netlogDao.findNetlogCount();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Onofflines onofflines = new Onofflines(result);
		String path = this.getClass().getClassLoader().getResource("").getPath();
		System.out.println(path);
		path = path.replace("WEB-INF/classes/", "");
		String path1 = path + "onoffline/";
		Long time = System.currentTimeMillis()/1000;
		
		List<String> list = new ArrayList<String>();
		for(Onoffline elem:onofflines.getOnofflineList()){
			String tmp = elem.toBcpString();
			tmp = tmp.replace("\n", "");
			list.add(tmp);
		}
		//Xml2Java.beanToXML(onofflines,path1+"145-330000-"+time.toString()+"-00001-WA-SOURCE_ONOFFLINE_0001-0.xml");
		BcpWrite.BcpWriteFile(list,path1+"145-330000-"+time.toString()+"-00001-WA-SOURCE_ONOFFLINE_0001-0.bcp");
		ZipUtil.ZipMultiFile(path1, path+"zip/onoffline/"+"145-353030334-330300-330300-"+time.toString()+"-00001.zip");
	
	}
	
	
}
