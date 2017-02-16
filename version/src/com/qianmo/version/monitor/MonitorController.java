package com.qianmo.version.monitor;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qianmo.version.util.HttpUtil;
import com.qianmo.version.util.ListUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="monitor")
public class MonitorController {
	
	private String[] hour = {"00:00","01:00","02:00","03:00","04:00","05:00"};
	private Integer[] count1={100,90,85,95,98,90};
	private Integer[] count2={90,80,95,85,100,80};
	private Integer[] count3={0,0,1,1,0,0};
	private HashMap<String,ArrayList> maplist = new HashMap<String,ArrayList>();
	private HashMap<String,ArrayList> maplistweek = new HashMap<String,ArrayList>();
	private HashMap<String,ArrayList> maplistmonth = new HashMap<String,ArrayList>();
	private HashMap<String,ArrayList> maplistperiod = new HashMap<String,ArrayList>();
	
	@RequestMapping(value="index")
	public ModelAndView index(HttpServletResponse response){
		ModelAndView mv = new ModelAndView("monitor/Monitor");
		
		
		return mv;
	}
	@RequestMapping(value="index1")
	public ModelAndView index1(HttpServletResponse response){
		ModelAndView mv = new ModelAndView("monitor/Monitorbs");
		
		
		return mv;
	}
	
	
	@RequestMapping(value="day")
	public ModelAndView day(HttpServletResponse response,String date,String device_id,
			String sn,String name,Integer page){
		ModelAndView mv = new ModelAndView("monitor/Monitor-day");
		if(page==null)
			page = 1;
		maplist.clear();
		String today = getToday();
		if(date==null || date.isEmpty()){
			date = today;
		}
		JSONObject info = getDayGloalInfo(date);
		
		Integer device_count=info.getInt("total_amt");
		Integer online_count=info.getInt("online_amt");
		Integer poweron_count=info.getInt("poweron_amt");
		Integer com_count=info.getInt("cmt_amt");
		Integer total_time=info.getInt("cmt_totaltime");
		Integer max_time=info.getInt("cmt_single_max_time");
		Integer min_time=info.getInt("cmt_single_min_time");
		
		/*ArrayList list = new ArrayList();
		DeviceInfo device1 = new DeviceInfo("设备000111",9999,"北京",116.46f,39.929f,"BT1609070110407");
		DeviceInfo device2 = new DeviceInfo("设备000112",9998,"北京",116.46f,39.929f,"BT1609070110408");
		list.add(device1);
		list.add(device2);*/

		ArrayList list = getDayDeviceList(date,device_id,sn,name);
		Integer count = list.size();
		Integer rows = 2;
		Integer pages = count/rows + count%rows;
		List list1 = ListUtil.getPageList(list,page,rows);
		mv.addObject("page",page);
		mv.addObject("pages",pages);
		
		mv.addObject("device_count", device_count);
		mv.addObject("online_count", online_count);
		mv.addObject("poweron_count", poweron_count);
		mv.addObject("com_count", com_count);
		
		mv.addObject("total_time", total_time);
		mv.addObject("max_time", max_time);
		mv.addObject("min_time", min_time);
		mv.addObject("list", list1);
		
		mv.addObject("date",date);
		mv.addObject("device_id",device_id);
		mv.addObject("sn",sn);
		mv.addObject("name",name);
		
		
		//mv.addObject("url","day");
		/*HashMap queryMap = new HashMap();
		queryMap.put("date", date);
		queryMap.put("device_id", device_id);
		queryMap.put("sn", sn);
		queryMap.put("name", name);
		mv.addObject("queryMap", queryMap);*/
		
		return mv;
	}
	@RequestMapping(value="week")
	public ModelAndView week(HttpServletResponse response,Integer page){
		ModelAndView mv = new ModelAndView("monitor/Monitor-week");
		if(page==null)
			page = 1;
		maplistweek.clear();
		JSONObject info = getWeekGloalInfo();
		
		Integer device_count=info.getInt("total_amt");
		Integer online_count=info.getInt("online_amt");
		Integer poweron_count=info.getInt("poweron_amt");
		Integer com_count=info.getInt("cmt_amt");
		Integer total_time=info.getInt("cmt_totaltime");
		Integer max_time=info.getInt("cmt_single_max_time");
		Integer min_time=info.getInt("cmt_single_min_time");
		
/*		ArrayList list = new ArrayList();
		TotalInfo info1 = new TotalInfo("2017-01-16",9999,8888,11111,12,9988,111100,11);
		TotalInfo info2 = new TotalInfo("2017-01-17",9997,8887,11110,13,9987,111103,10);
		
		list.add(info1);
		list.add(info2);*/
		JSONArray jarr = getWeekStatistic();
		Integer count = jarr.size();
		Integer rows = 2;
		Integer pages = count/rows + count%rows;
		List list1 = ListUtil.getPageList(jarr,page,rows);
		mv.addObject("page",page);
		mv.addObject("pages",pages);
		
		mv.addObject("device_count", device_count);
		mv.addObject("online_count", online_count);
		mv.addObject("poweron_count", poweron_count);
		mv.addObject("com_count", com_count);
		
		mv.addObject("total_time", total_time);
		mv.addObject("max_time", max_time);
		mv.addObject("min_time", min_time);
		mv.addObject("list",list1);
		
		return mv;
	}
	@RequestMapping(value="month")
	public ModelAndView month(HttpServletResponse response,String month,Integer page){
		ModelAndView mv = new ModelAndView("monitor/Monitor-month");
		if(page==null)
			page = 1;
		maplistmonth.clear();
		if(month==null || month.isEmpty())
			month = getThisMonth();
		JSONObject info = getMonthGloalInfo(month);
		
		Integer device_count=info.getInt("total_amt");
		Integer online_count=info.getInt("online_amt");
		Integer poweron_count=info.getInt("poweron_amt");
		Integer com_count=info.getInt("cmt_amt");
		Integer total_time=info.getInt("cmt_totaltime");
		Integer max_time=info.getInt("cmt_single_max_time");
		Integer min_time=info.getInt("cmt_single_min_time");
		
		/*ArrayList list = new ArrayList();
		TotalInfo info1 = new TotalInfo("2017-01-16",9999,8888,11111,12,9988,111100,11);
		TotalInfo info2 = new TotalInfo("2017-01-17",9997,8887,11110,13,9987,111103,10);
		
		list.add(info1);
		list.add(info2);*/
		JSONArray jarr = getMonthStatistic(month);
		
		Integer count = jarr.size();
		Integer rows = 2;
		Integer pages = count/rows + count%rows;
		List list1 = ListUtil.getPageList(jarr,page,rows);
		mv.addObject("page",page);
		mv.addObject("pages",pages);
		
		mv.addObject("device_count", device_count);
		mv.addObject("online_count", online_count);
		mv.addObject("poweron_count", poweron_count);
		mv.addObject("com_count", com_count);
		
		mv.addObject("total_time", total_time);
		mv.addObject("max_time", max_time);
		mv.addObject("min_time", min_time);
		mv.addObject("list",list1);
		mv.addObject("month",month);
		
		
		return mv;
	}
	@RequestMapping(value="period")
	public ModelAndView period(HttpServletResponse response,String start,String end,Integer page){
		ModelAndView mv = new ModelAndView("monitor/Monitor-period");
		if(page==null)
			page = 1;
		maplistperiod.clear();
		if(start==null || start.isEmpty())
			start = getDayWeekAgo();
		if(end==null || end.isEmpty())
			end = getToday();
		
		JSONObject info = getPeriodGloalInfo(start,end);
		
		Integer device_count=info.getInt("total_amt");
		Integer online_count=info.getInt("online_amt");
		Integer poweron_count=info.getInt("poweron_amt");
		Integer com_count=info.getInt("cmt_amt");
		Integer total_time=info.getInt("cmt_totaltime");
		Integer max_time=info.getInt("cmt_single_max_time");
		Integer min_time=info.getInt("cmt_single_min_time");
		
		/*ArrayList list = new ArrayList();
		TotalInfo info1 = new TotalInfo("2017-01-16",9999,8888,11111,12,9988,111100,11);
		TotalInfo info2 = new TotalInfo("2017-01-17",9997,8887,11110,13,9987,111103,10);
		
		list.add(info1);
		list.add(info2);*/
		
		JSONArray jarr = getPeriodStatistic(start, end);
		Integer count = jarr.size();
		Integer rows = 2;
		Integer pages = count/rows + count%rows;
		List list1 = ListUtil.getPageList(jarr,page,rows);
		mv.addObject("page",page);
		mv.addObject("pages",pages);
		
		mv.addObject("device_count", device_count);
		mv.addObject("online_count", online_count);
		mv.addObject("poweron_count", poweron_count);
		mv.addObject("com_count", com_count);
		
		mv.addObject("total_time", total_time);
		mv.addObject("max_time", max_time);
		mv.addObject("min_time", min_time);
		mv.addObject("list",list1);
		mv.addObject("start",start);
		mv.addObject("end",end);
		
		return mv;
	}
	
	@RequestMapping(value="map")
	public ModelAndView map(HttpServletResponse response,String longitude,String latitude){
		ModelAndView mv = new ModelAndView("monitor/map");
		
		mv.addObject("longitude",longitude);
		mv.addObject("latitude",latitude);
		return mv;
	}
	
	@RequestMapping(value="detail")
	public ModelAndView detail(HttpServletResponse response){
		ModelAndView mv = new ModelAndView("monitor/Monitor-detail");
		
		
		return mv;
	}
	@RequestMapping(value="detail1")
	public ModelAndView detail1(HttpServletResponse response,
			String wifi_sn,String biz_num,String day,String ip,String longitude,
			String latitude,String online_total_time){
		ModelAndView mv = new ModelAndView("monitor/Monitorbs-detail");
		
		/*String state="在线";
		String device_id="24397589";
		       wifi_sn="234859043";
		String ip="192.168.0.1";
		String last_com="2017-01-07 12:00:00";*/
		
		//mv.addObject("state",state);
		mv.addObject("device_id",biz_num);
		mv.addObject("sn",wifi_sn);
		mv.addObject("ip",ip);
		mv.addObject("last_com",day);
		
		
		return mv;
	}
	
	@RequestMapping(value = "gettreedata", method = RequestMethod.POST)
	public @ResponseBody String gettreedata(HttpServletResponse response,
			Integer device_id) {
		List result = null;
		/*try{
			//result = versionDao.findVersionById(device_id);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		
		
		
		//HashMap map = new HashMap();
		HashMap tree = getTreeMap();
		List list = new ArrayList();
		list.add(tree);
		//map.put("rows", result);
		//map.put("success", "success");
		//JSONArray jsonArray = JSONArray.fromObject( result);
		JSONArray jsonObject = JSONArray.fromObject(list);
	
		String ret = jsonObject.toString();
		return ret;
		
	}
	
	private  HashMap getTreeMap(){
		HashMap map = new HashMap();
		map.put("id", 1);
		map.put("text", "我的电脑");
		//map.put("checked", true);
		List<HashMap> list = new ArrayList<HashMap>();
		for(int i=0;i<5;i++){
			HashMap tmpmap = getTreeElem(2,"文件夹"+i);
			List<HashMap> list1 = new ArrayList<HashMap>();
			for(int j=0;j<5;j++){
				HashMap tmpmap1 = getTreeElem(2,"文件"+i+j);
				pushChild(list1,tmpmap1);
			}
			addChild(tmpmap,list1);
			pushChild(list,tmpmap);
		}
		addChild(map,list);
		
		return map;
	}
	private HashMap getTreeElem(Integer id,String text){
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("text", text);		
		
		return map;
	}
	private void addChild(HashMap elem, List children){
		elem.put("children", children);
	}
	private void pushChild(List list, HashMap elem){
		list.add(elem);
	}
	
	
	
	@RequestMapping(value = "getmapdata", method = RequestMethod.POST)
	public @ResponseBody String getmapdata(HttpServletResponse response,
			String sn,String date,String type,String month,String start,String end) {
		List result = null;
		String today = getToday();
		if(date==null || date.isEmpty())
			date=today;
		
		String tmpsn = sn.replace("hicontainer", "");
		HashMap<String,ArrayList> mapret=null;
		ArrayList hourlist=null;
		ArrayList count1list=null;
		ArrayList count2list=null;
		if(tmpsn.equals("0")){
			 mapret = getDayGlobalStatistic(date);
			 hourlist = mapret.get("hourlist");
			 count1list = mapret.get("onlinecnt");
			 count2list = mapret.get("offlinecnt");
		}else if(tmpsn.equals("week")){
			
			 hourlist = maplistweek.get("daylist");
			 count1list = maplistweek.get("daypower");
			 count2list = maplistweek.get("daycmt");
		}else if(tmpsn.equals("month")){
			
			 hourlist = maplistmonth.get("monthlist");
			 count1list = maplistmonth.get("monthpower");
			 count2list = maplistmonth.get("monthcmt");
		}
		else if(tmpsn.equals("period")){
			
			 hourlist = maplistperiod.get("periodlist");
			 count1list = maplistperiod.get("periodpower");
			 count2list = maplistperiod.get("periodcmt");
		}
		else{
			//one device's table.
			hourlist = maplist.get(tmpsn+"hour");
			count1list = maplist.get(tmpsn+"power");
			count2list = maplist.get(tmpsn+"online");
		}
		
		
		System.out.println("sn="+sn);
		
		HashMap map = new HashMap();

		map.put("sn", sn);
		map.put("hour", hourlist);
		map.put("count1", count1list);
		map.put("count2", count2list);
		map.put("success", "success");
		//JSONArray jsonArray = JSONArray.fromObject( result);
		JSONObject jsonObject = JSONObject.fromObject(map);
	
		String ret = jsonObject.toString();
		return ret;
		
	}
	
	@RequestMapping(value = "getmapdata1", method = RequestMethod.POST)
	public @ResponseBody String getmapdata1(HttpServletResponse response,
			String sn) {
		List result = null;
		/*try{
			//result = versionDao.findVersionById(device_id);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		
		
		
		System.out.println("sn="+sn);
		
		HashMap map = new HashMap();

		map.put("sn", sn);
		map.put("hour", hour);
		
		map.put("count1", count3);
		map.put("success", "success");
		//JSONArray jsonArray = JSONArray.fromObject( result);
		JSONObject jsonObject = JSONObject.fromObject(map);
	
		String ret = jsonObject.toString();
		return ret;
		
	}
	
	@RequestMapping(value = "getcmdret", method = RequestMethod.POST)
	public @ResponseBody String getcmdret(HttpServletResponse response,
			String command) {
		
		
		System.out.println("command="+command);
		
		HashMap map = new HashMap();

		map.put("command", command);
		
		map.put("success", "success");
		JSONObject jsonObject = JSONObject.fromObject(map);
	
		String ret = jsonObject.toString();
		return ret;
		
	}
	
	
	public  JSONObject getDayGloalInfo(String day){
		String url="http://101.201.53.142:8089/qmbg_busoa/deviceMonitering/getDeviceMonitoringStatisBySearchDay";
		
		//String cnt = "{\"searchday\":\"2017-01-18\"}";
		//String cnt = "{\"searchday\":"+"\""+day+"\"}";
		HashMap map = new HashMap();
		map.put("searchday", day);
		JSONObject jsonObject = JSONObject.fromObject(map);
		String cnt = jsonObject.toString();
		String res = HttpUtil.doPost(url,cnt);
		System.out.println(res);
		JSONObject jsonret = JSONObject.fromObject(res);
		Integer status = jsonret.getInt("status");
		if(status==1){
			JSONObject data = jsonret.getJSONObject("data");
			return data;
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
	public String getDayWeekAgo(){
		Date today=new Date();
		Date date = new Date(today.getTime()-7*24*3600*1000);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String stoday = dateFormat.format(date);
		System.out.println("Today is "+stoday);
		return stoday;
	}
	public String getThisMonth(){
		Date today=new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		String stoday = dateFormat.format(today);
		System.out.println("This month is "+stoday);
		return stoday;
	}
	public HashMap<String,ArrayList> getDayGlobalStatistic(String day){
		HashMap<String,ArrayList> mapresult = null;
		String url="http://101.201.53.142:8089/qmbg_busoa/deviceMonitering/getDeviceMonitoringOnlineGeneralByDay";
		
		HashMap map = new HashMap();
		map.put("searchday", day);	
		JSONObject jsonObject = JSONObject.fromObject(map);
		String cnt = jsonObject.toString();
		String res = HttpUtil.doPost(url,cnt);
		System.out.println(res);
		JSONObject jsonret = JSONObject.fromObject(res);
		Integer status = jsonret.getInt("status");
		if(status==1){
			mapresult = new HashMap<String,ArrayList>();
			ArrayList hourlist = new ArrayList();
			ArrayList onlinecnt = new ArrayList();
			ArrayList offlinecnt = new ArrayList();
			mapresult.put("hourlist", hourlist);
			mapresult.put("onlinecnt", onlinecnt);
			mapresult.put("offlinecnt", offlinecnt);
			JSONArray data = jsonret.getJSONArray("data");
			for(Object elem:data){
				JSONObject elem1 = (JSONObject)elem;
				hourlist.add(elem1.getInt("hour"));
				onlinecnt.add(elem1.getInt("online_amt"));
				offlinecnt.add(elem1.getInt("offline_amt"));
			}
			
			return mapresult;
		}
		return null;
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
				resultlist.add(mapelem);
				
				JSONArray jsonarray = elem1.getJSONArray("list");
				ArrayList hourlist = new ArrayList();
				ArrayList poweronstate = new ArrayList();
				ArrayList onlinestate = new ArrayList();
				maplist.put(wifi_sn+"hour", hourlist);
				maplist.put(wifi_sn+"power", poweronstate);
				maplist.put(wifi_sn+"online", onlinestate);
				
				for(Object elem00:jsonarray){
					JSONObject jo = (JSONObject)elem00;
					hourlist.add(jo.getString("time"));
					poweronstate.add(jo.getInt("poweron"));
					onlinestate.add(jo.getInt("device_online"));
				}
				
			}
			
			return resultlist;
		}
		return null;
		
	}
	
	public  JSONObject getWeekGloalInfo(){
		String url="http://101.201.53.142:8089/qmbg_busoa/deviceMonitering/getDeviceMonitoringStatisByCurrentWeek";
		
		//String cnt = "{\"searchday\":\"2017-01-18\"}";
		//String cnt = "{\"searchday\":"+"\""+day+"\"}";
		HashMap map = new HashMap();
		//map.put("searchday", day);
		JSONObject jsonObject = JSONObject.fromObject(map);
		String cnt = jsonObject.toString();
		String res = HttpUtil.doPost(url,cnt);
		System.out.println(res);
		JSONObject jsonret = JSONObject.fromObject(res);
		Integer status = jsonret.getInt("status");
		if(status==1){
			JSONObject data = jsonret.getJSONObject("data");
			return data;
		}
		return null;
	}
	
	public JSONArray getWeekStatistic(){
		HashMap<String,ArrayList> mapresult = null;
		String url="http://101.201.53.142:8089/qmbg_busoa/deviceMonitering/getDeviceMonitoringDetailByCurrentWeek";
		
		HashMap map = new HashMap();
		//map.put("searchday", day);	
		JSONObject jsonObject = JSONObject.fromObject(map);
		String cnt = jsonObject.toString();
		String res = HttpUtil.doPost(url,cnt);
		System.out.println(res);
		JSONObject jsonret = JSONObject.fromObject(res);
		Integer status = jsonret.getInt("status");
		if(status==1){
			mapresult = new HashMap<String,ArrayList>();
			ArrayList daylist = new ArrayList();
			ArrayList powercnt = new ArrayList();
			ArrayList cmtcnt = new ArrayList();
			maplistweek.put("daylist", daylist);
			maplistweek.put("daypower", powercnt);
			maplistweek.put("daycmt", cmtcnt);
			JSONArray data = jsonret.getJSONArray("data");
			for(Object elem:data){
				JSONObject elem1 = (JSONObject)elem;
				daylist.add(elem1.getString("day"));
				powercnt.add(elem1.getInt("poweron_amt"));
				cmtcnt.add(elem1.getInt("cmt_amt"));
			}
			
			return data;
		}
		return null;
	}
	public  JSONObject getMonthGloalInfo(String month){
		String url="http://101.201.53.142:8089/qmbg_busoa/deviceMonitering/getDeviceMonitoringStatisByMonth";
		
		//String cnt = "{\"searchday\":\"2017-01-18\"}";
		//String cnt = "{\"searchday\":"+"\""+day+"\"}";
		HashMap map = new HashMap();
		map.put("month", month);
		JSONObject jsonObject = JSONObject.fromObject(map);
		String cnt = jsonObject.toString();
		String res = HttpUtil.doPost(url,cnt);
		System.out.println(res);
		JSONObject jsonret = JSONObject.fromObject(res);
		Integer status = jsonret.getInt("status");
		if(status==1){
			JSONObject data = jsonret.getJSONObject("data");
			return data;
		}
		return null;
	}
	
	public JSONArray getMonthStatistic(String month){
		HashMap<String,ArrayList> mapresult = null;
		String url="http://101.201.53.142:8089/qmbg_busoa/deviceMonitering/getDeviceMonitoringDetailByMonth";
		
		HashMap map = new HashMap();
		map.put("month", month);	
		JSONObject jsonObject = JSONObject.fromObject(map);
		String cnt = jsonObject.toString();
		String res = HttpUtil.doPost(url,cnt);
		System.out.println(res);
		JSONObject jsonret = JSONObject.fromObject(res);
		Integer status = jsonret.getInt("status");
		if(status==1){
			mapresult = new HashMap<String,ArrayList>();
			ArrayList monthlist = new ArrayList();
			ArrayList powercnt = new ArrayList();
			ArrayList cmtcnt = new ArrayList();
			maplistmonth.put("monthlist", monthlist);
			maplistmonth.put("monthpower", powercnt);
			maplistmonth.put("monthcmt", cmtcnt);
			JSONArray data = jsonret.getJSONArray("data");
			for(Object elem:data){
				JSONObject elem1 = (JSONObject)elem;
				monthlist.add(elem1.getString("day"));
				powercnt.add(elem1.getInt("poweron_amt"));
				cmtcnt.add(elem1.getInt("cmt_amt"));
			}
			
			return data;
		}
		return null;
	}
	
	public  JSONObject getPeriodGloalInfo(String start_day,String end_day){
		String url="http://101.201.53.142:8089/qmbg_busoa/deviceMonitering/getDeviceMonitoringStatisByDateRange";
		
		//String cnt = "{\"searchday\":\"2017-01-18\"}";
		//String cnt = "{\"searchday\":"+"\""+day+"\"}";
		HashMap map = new HashMap();
		map.put("start_day", start_day);
		map.put("end_day", end_day);
		JSONObject jsonObject = JSONObject.fromObject(map);
		String cnt = jsonObject.toString();
		String res = HttpUtil.doPost(url,cnt);
		System.out.println(res);
		JSONObject jsonret = JSONObject.fromObject(res);
		Integer status = jsonret.getInt("status");
		if(status==1){
			JSONObject data = jsonret.getJSONObject("data");
			return data;
		}
		return null;
	}
	
	public JSONArray getPeriodStatistic(String start_day,String end_day){
		HashMap<String,ArrayList> mapresult = null;
		String url="http://101.201.53.142:8089/qmbg_busoa/deviceMonitering/getDeviceMonitoringDetailByDateRange";
		
		HashMap map = new HashMap();
		map.put("start_day", start_day);
		map.put("end_day", end_day);	
		JSONObject jsonObject = JSONObject.fromObject(map);
		String cnt = jsonObject.toString();
		String res = HttpUtil.doPost(url,cnt);
		System.out.println(res);
		JSONObject jsonret = JSONObject.fromObject(res);
		Integer status = jsonret.getInt("status");
		if(status==1){
			mapresult = new HashMap<String,ArrayList>();
			ArrayList periodlist = new ArrayList();
			ArrayList powercnt = new ArrayList();
			ArrayList cmtcnt = new ArrayList();
			maplistperiod.put("periodlist", periodlist);
			maplistperiod.put("periodpower", powercnt);
			maplistperiod.put("periodcmt", cmtcnt);
			JSONArray data = jsonret.getJSONArray("data");
			for(Object elem:data){
				JSONObject elem1 = (JSONObject)elem;
				periodlist.add(elem1.getString("day"));
				powercnt.add(elem1.getInt("poweron_amt"));
				cmtcnt.add(elem1.getInt("cmt_amt"));
			}
			
			return data;
		}
		return null;
	}
	
}
