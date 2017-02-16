package com.qianmo.version.group;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qianmo.version.util.HttpUtil;
import com.qianmo.version.util.ListUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="group")
public class GroupController {
	
	@RequestMapping(value="index")
	public ModelAndView index(HttpServletResponse response,String groupname,String groupcount,
			String createtime,String lastcomtime,Integer page){
		ModelAndView mv = new ModelAndView("group/Group");
		if(page==null)
			page=1;
		JSONArray jarr = getGroupList();
		Integer count = jarr.size();
		Integer rows = 2;
		Integer pages = count/rows + count%rows;
		List list1 = ListUtil.getPageList(jarr,page,rows);
		mv.addObject("page",page);
		mv.addObject("pages",pages);
		
		mv.addObject("groupname",groupname);
		mv.addObject("groupcount",groupcount);
		mv.addObject("createtime",createtime);
		mv.addObject("lastcomtime",lastcomtime);
		
		mv.addObject("list",list1);
		
		return mv;
	}
	@RequestMapping(value="devicelist")
	public ModelAndView devicelist(HttpServletResponse response,Integer page,
			String keyword,Boolean online,Boolean offline){
		ModelAndView mv = new ModelAndView("group/GroupDeviceList");
		if(page==null)
			page=1;
		JSONArray jarr = getDeviceList();
		Integer count = jarr.size();
		Integer rows = 2;
		Integer pages = count/rows + count%rows;
		List list1 = ListUtil.getPageList(jarr,page,rows);
		mv.addObject("page",page);
		mv.addObject("pages",pages);
	       

		mv.addObject("keyword",keyword);
		mv.addObject("online",online);
		mv.addObject("offline",offline);
		mv.addObject("list",list1);
		
		return mv;
	}
	
	@RequestMapping(value="create")
	public ModelAndView create(HttpServletResponse response,Integer page){
		ModelAndView mv = new ModelAndView("group/GroupEdit");
		if(page==null)
			page=1;
		JSONArray jarr = getDeviceList();
		Integer count = jarr.size();
		Integer rows = 2;
		Integer pages = count/rows + count%rows;
		List list1 = ListUtil.getPageList(jarr,page,rows);
		mv.addObject("page",page);
		mv.addObject("pages",pages);
	       

		
		mv.addObject("list",list1);
		
		return mv;
	}
	@RequestMapping(value="edit")
	public ModelAndView edit(HttpServletResponse response,String groupname,Integer page,
			String groupcomment,
			String keyword,Boolean online,Boolean offline){
		ModelAndView mv = new ModelAndView("group/GroupEdit");
		if(page==null)
			page=1;
		
		JSONArray jarr = getDeviceList();
		Integer count = jarr.size();
		Integer rows = 2;
		Integer pages = count/rows + count%rows;
		List list1 = ListUtil.getPageList(jarr,page,rows);
		mv.addObject("page",page);
		mv.addObject("pages",pages);
	       
		
		mv.addObject("groupname",groupname);
		mv.addObject("keyword",keyword);
		mv.addObject("online",online);
		mv.addObject("offline",offline);
		mv.addObject("groupcomment",groupcomment);
		
		mv.addObject("list",list1);
		
		return mv;
	}
	@RequestMapping(value="save")
	public ModelAndView save(HttpServletResponse response, String groupname,String ids){
		//ModelAndView mv = new ModelAndView("task/Task");
		
		//JSONArray jarr = getWeekStatistic();
		
             
		//mv.addObject("taskid",taskid);
		
		//mv.addObject("list",jarr);
		
		return index(response,null,null,null,null,null);
	}
	
	@RequestMapping(value="detail")
	public ModelAndView detail(HttpServletResponse response,String groupname,
			String keyword,Boolean online,Boolean offline,Integer page){
		ModelAndView mv = new ModelAndView("group/GroupDetail");
		if(page==null)
			page=1;
		
		String today = getToday();
		ArrayList list = getDayDeviceList(today,null,null,null); 
		Integer count = list.size();
		Integer rows = 2;
		Integer pages = count/rows + count%rows;
		List list1 = ListUtil.getPageList(list,page,rows);
		mv.addObject("page",page);
		mv.addObject("pages",pages);
		
		if(groupname==null)
			groupname = "testgroup";
		mv.addObject("name",groupname);
		mv.addObject("comment","none");
		mv.addObject("keyword",keyword);
		mv.addObject("online",online);
		mv.addObject("offline",offline);
		mv.addObject("list",list1);
		
		
		return mv;
	}
	
	public JSONArray getGroupList(){
		/*HashMap<String,ArrayList> mapresult = null;
		String url="http://101.201.53.142:8089/qmbg_busoa/deviceMonitering/getDeviceMonitoringDetailByCurrentWeek";
		
		HashMap map = new HashMap();
		JSONObject jsonObject = JSONObject.fromObject(map);
		String cnt = jsonObject.toString();
		String res = HttpUtil.doPost(url,cnt);
		System.out.println(res);
		JSONObject jsonret = JSONObject.fromObject(res);
		Integer status = jsonret.getInt("status");
		if(status==1){
			mapresult = new HashMap<String,ArrayList>();
			JSONArray data = jsonret.getJSONArray("data");
			
			return data;
		}*/
		ArrayList list = new ArrayList();
		for(int i=0;i<3;i++){
			HashMap map = new HashMap();
			map.put("groupname", "group"+(i+1));
			map.put("devicecount", (i+2)*123+321);
			map.put("onlinecount", (i+2)*111+321);
			map.put("comcount", (i+2)*99+321);
			map.put("lastcom", "2017-02-06 12:00:00");
			
			
			
			list.add(map);
		}
		JSONArray jarr = JSONArray.fromObject(list);
		return jarr;
	}
	public JSONArray getDeviceList(){
		ArrayList list = new ArrayList();
		for(int i=0;i<3;i++){
			HashMap map = new HashMap();
			map.put("deviceid", 10+i);
			map.put("sn", "sn000"+10+i*2);
			
			
			
			list.add(map);
		}
		JSONArray jarr = JSONArray.fromObject(list);
		return jarr;
	}
	
	public  ArrayList getDayDeviceList(String day,String device_id,String sn,String name){
		
		String url="http://101.201.53.142:8089/qmbg_busoa/deviceMonitering/getDeviceMonitoringDetailByDay";
		HashMap map = new HashMap();
		map.put("searchday", day);
		map.put("dotvalue", 100);
		JSONObject jsonObject = JSONObject.fromObject(map);
		String cnt = jsonObject.toString();
		String res = HttpUtil.doPost(url,cnt);
		System.out.println(res);
		JSONObject jsonret = JSONObject.fromObject(res);
		Integer status = jsonret.getInt("status");
		if(status==1){			
			ArrayList resultlist = new ArrayList();
			
			
			JSONArray data = jsonret.getJSONArray("data");
			for(Object elem:data){
				JSONObject elem1 = (JSONObject)elem;
				HashMap mapelem = new HashMap();
				String wifi_sn = elem1.getString("wifi_sn");
				mapelem.put("wifi_sn", wifi_sn);
				mapelem.put("biz_num", elem1.getString("biz_num"));
				mapelem.put("day", elem1.getString("day"));
				mapelem.put("ip", elem1.getString("ip"));
				mapelem.put("longitude", elem1.getString("lngtude"));
				mapelem.put("latitude", elem1.getString("lattude"));
				mapelem.put("online_total_time", elem1.getInt("online_total_time"));
				mapelem.put("state", "在线");
				mapelem.put("lastcom", "2017-02-10");
				resultlist.add(mapelem);
				
				
				
			}
			
			return resultlist;
		}
		return null;
		
	}
	public String getToday(){
		Date today=new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String stoday = dateFormat.format(today);
		System.out.println("Today is "+stoday);
		return stoday;
	}
	
}
