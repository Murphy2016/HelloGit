package com.qianmo.gawa.statistic;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.qianmo.gawa.ap.ApDao;
import com.qianmo.gawa.area.AreaDao;
import com.qianmo.gawa.netlog.NetlogDao;
import com.qianmo.gawa.onoffline.OnofflineDao;
import com.qianmo.gawa.operlog.OperlogDao;
import com.qianmo.gawa.util.Xml2Java;
import com.qianmo.gawa.util.ZipUtil;



@Component
public class StatisticServiceImpl implements StatisticService {
	@Autowired
	private StatisticDao statisticDao;
	@Autowired
	private ApDao apDao;
	@Autowired
	private AreaDao areaDao;
	@Autowired
	private OnofflineDao onofflineDao;
	@Resource
	private OperlogDao operlogDao;
	@Resource
	private NetlogDao netlogDao;

	/**
	 * 
	 */
	@Override
	//do it every day 00:00.
	@Scheduled(cron = "0 0 0 * * ?")
	public void updateApState() throws Exception{
			Integer count1 = apDao.findApCount();
			Integer count2 = apDao.findApOnlineCount();
			Statistic statistic = new Statistic();
			statistic.setCount(count1);
			statistic.setService_online(count2);
			statistic.setData_online(count2);
			statistic.setService_offline(count1-count2);
			statistic.setDate_offline(count1-count2);
			statistic.setWorking(count1);

			Date d=new Date();			
			statistic.setDate1(new Date(d.getTime() - (long)24 * 60 * 60 * 1000));
			statisticDao.addApState(statistic);
			System.out.println("addApState finished!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void updateAreaState() throws Exception{
		Integer count1 = areaDao.findAreaCount();
		Integer count2 = areaDao.findAreaOnlineCount();
		Statistic statistic = new Statistic();
		statistic.setCount(count1);
		statistic.setWorking(count2);
		statistic.setUnworking(count1-count2);
		
		Date d=new Date();			
		statistic.setDate1(new Date(d.getTime() - (long)24 * 60 * 60 * 1000));
		statisticDao.addAreaState(statistic);
		System.out.println("addAreaState finished!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void updateUserState() throws Exception{
		Long start=null,end=null;
		Date date = new Date();  
	    end = date.getTime()/1000;
	    start = end - 24*3600;
		
	    List<Map> thelist = onofflineDao.findUserDayCount(start, end);
	    
	    if(thelist!=null && thelist.size()>0){
	    	for(Map map:thelist){
	    		Statistic statistic = new Statistic();
	    		statistic.setMobileno((String)map.get("mobileno"));
	    		Long tmpCount = (Long)map.get("count");
	    		statistic.setCount(Integer.valueOf(tmpCount.toString()));
	    		Date d=new Date();			
				statistic.setDate1(new Date(d.getTime() - (long)24 * 60 * 60 * 1000));
	    		statisticDao.addUserState(statistic);
	    	}
	    }else{
	    	Statistic statistic = new Statistic();
    		statistic.setMobileno("");
    		Long tmpCount = 0L;
    		statistic.setCount(Integer.valueOf(tmpCount.toString()));
    		Date d=new Date();			
			statistic.setDate1(new Date(d.getTime() - (long)24 * 60 * 60 * 1000));
    		statisticDao.addUserState(statistic);
	    }
	    
	    
	    
		
		System.out.println("addUserState finished!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void updateRealnameState() throws Exception{
		Long start=null,end=null;
		Date date = new Date();  
	    end = date.getTime()/1000;
	    start = end - 24*3600;
		
	    Integer count = onofflineDao.findRealnameDayCount(start, end);
	    
		  
		Statistic statistic = new Statistic();
		statistic.setCount(count);
		statistic.setBus(count);
		Date d=new Date();			
		statistic.setDate1(new Date(d.getTime() - (long)24 * 60 * 60 * 1000));
		statisticDao.addRealnameState(statistic);
	    	
	    
	    
	    
	    
		
		System.out.println("addRealnameState finished!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
		
		
	}
	
	@Scheduled(cron = "0 0,10,20,30,40,50 * * * ?")
	public void updateApOnlineState() throws Exception{
		Long cur_time=null;
		Date date = new Date();  
		cur_time = date.getTime()/1000;
	    Integer period = 10*60;
		
	    apDao.updateApStateByPeriod(cur_time, period);
	    
	    	
	    
	    
	    
	    
		
		System.out.println("updateApOnlineState finished!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
		
		
	}
	
	@Scheduled(cron = "0 0/5 * * * ?")
	public void updateApDataOnlineState() throws Exception{
		Long cur_time=null;
		Date date = new Date();  
		cur_time = date.getTime()/1000;
	    Integer period = 10*60;
		
	    apDao.updateApDataStateByPeriod(cur_time, period);
	    
		System.out.println("updateApDataOnlineState finished!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
		
		
	}
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void deleteOperlog() throws Exception{
		Integer period=7;
		try {
			period = operlogDao.findSetting();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date d = new Date();
		Date date = new Date(d.getTime() - (long)period*24 * 60 * 60 * 1000);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String str=sdf.format(date);
		
		operlogDao.deleteOperlogByTime(str);
	}
	
	@Scheduled(cron = "0 0 2 * * ?")
	public void deleteNetlog() throws Exception{
		Date d = new Date();
		Date date = new Date(d.getTime() - (long)24 * 60 * 60 * 1000);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String str=sdf.format(date);
		
		netlogDao.deleteNetlogByTime(str);
	}
	
	@Scheduled(cron = "0 0 3 * * ?")
	public void deleteOnoffine() throws Exception{
		Date d = new Date();
		Date date = new Date(d.getTime() - (long)24 * 60 * 60 * 1000);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String str=sdf.format(date);
		
		onofflineDao.deleteOnofflineByTime(str);
	}
}
