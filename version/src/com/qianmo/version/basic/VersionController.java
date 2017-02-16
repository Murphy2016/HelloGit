package com.qianmo.version.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lx.util.StringCombine;
import com.lx.util.StringSpliter;
import com.qianmo.version.util.ExcelException;
import com.qianmo.version.util.ExcelUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/version")
public class VersionController {
	
	@Resource
	private VersionDao versionDao;
	
	@Resource
	private VersionDao2 versionDao2;
	
	@RequestMapping(value="index")
	public ModelAndView index(HttpServletResponse response){
		ModelAndView mv = new ModelAndView("version/layout-info");
		
		
		
		try{
			Integer software_count = versionDao.findSoftwareCount();
			Integer device_count = versionDao.findDeviceCount();
			List<Version> list = versionDao.findGroupCount();
			List<Version> softwarelist = versionDao.findSoftwareGroupCount();
			Map<String,Integer> map00 = new HashMap<String,Integer>();
			for(Version elem:softwarelist){
				map00.put(elem.getSoftware_name(), elem.getCount());
				System.out.println("softwarename="+elem.getSoftware_name()+" cnt="+elem.getCount());
			}
			Map<String,Map> map0 = new HashMap<String,Map>();
			
			for(Version elem:list){
				Map tmpmap = map0.get(elem.getSoftware_name());
				if(tmpmap==null){
					tmpmap = new HashMap<String,Object>();
					map0.put(elem.getSoftware_name(), tmpmap);
				}
				List tmplist = (List)tmpmap.get("list");
				if(tmplist==null){
					tmplist = new ArrayList<Version>();
					tmpmap.put("list", tmplist);
				}
				Integer tmp = elem.getCount();
				Float tmp1 = tmp.floatValue();
				Integer softwarecnt = map00.get(elem.getSoftware_name());
				tmp1 = tmp1/softwarecnt;
				tmp1 = tmp1*100;
				//tmp = tmp1.intValue();
				tmp1 = ((float)Math.round(tmp1*100))/100;
				elem.setPercent(tmp1.toString()+"%");
				tmplist.add(elem);
			}
			for (String key:map0.keySet()){
				Map elem = map0.get(key);
				Integer tmpcnt=0;
				List list1 = (List)elem.get("list");
				String software_name = key;
				for(Object elem1:list1){
					Version elem11 = (Version)elem1;
					tmpcnt += elem11.getCount();
				}
				float tmpcnta = tmpcnt.floatValue();
				float percent1 = tmpcnta/device_count;
				percent1 = percent1*100;	
				float ttt = (Math.round(percent1*100));
				percent1 = (ttt)/100;
				
				System.out.println(percent1);
				float percent2 = 100-percent1;
				percent2 = ((float)Math.round(percent2*100)/100);
				elem.put("percent1", percent1);			
				elem.put("percent2",percent2);
				Integer softwarecnt = map00.get(key);
				elem.put("install", softwarecnt);
				elem.put("uninstall", device_count-softwarecnt);
				System.out.println("software_name="+software_name+" percent1="+percent1
						+"percent2="+percent2);
			}
			
			Map map = new HashMap();
			map.put("software_count", software_count);
			map.put("device_count", device_count);
			map.put("map0", map0);
			mv.addObject("cnt", map);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return mv;
	}
	@RequestMapping(value="search")
	public ModelAndView search(HttpServletResponse response,Version version){
		ModelAndView mv = new ModelAndView("version/layout-search");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = null,date2=null;
		version.setOffset((version.getPage()-1)*version.getRows());

		try {
			if(version.getTime1()!=null){
				date1 = format.parse(version.getTime1());
				version.setCreate_time(date1.getTime());
			}
			if(version.getTime2()!=null){
				date2 = format.parse(version.getTime2());
				version.setUpdate_time(date2.getTime());
				//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+date2.getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//Map map = new HashMap();
		//map.put("device_id", version.getDevice_id());
		try{
			Integer count = versionDao.findVersionCountBySearch(version);			
			Integer pages = count/version.getRows();
			if(count%version.getRows()>0)
				pages++;
			Integer devicecount = versionDao.findVersionDeviceCountBySearch(version);
			Integer softwarecount = versionDao.findVersionSoftwareCountBySearch(version);
			Integer versioncount = versionDao.findVersionVersionCountBySearch(version);
			List<Version> list = versionDao.findVersionBySearch(version);
			System.out.println(list.size());
			//Version ver = list.get(0);
			//System.out.println(ver.toString());
			Map mvmap = new HashMap();
			mvmap.put("version", version);
			mvmap.put("count", count);
			mvmap.put("devicecount", devicecount);
			mvmap.put("softwarecount", softwarecount);
			mvmap.put("versioncount", versioncount);
			mvmap.put("create_time", version.getTime1());
			mvmap.put("update_time", version.getTime2());
			mvmap.put("rows", list);
			mvmap.put("page", version.getPage());
			mvmap.put("pages", pages);
			mv.addObject("result", mvmap);
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return mv;
	}
	//just for get the list of not installed devices
	@RequestMapping(value="search2")
	public ModelAndView search2(HttpServletResponse response,Version version){
		ModelAndView mv = new ModelAndView("version/layout-search2");
		version.setOffset((version.getPage()-1)*version.getRows());
		
		try{
			Integer count = versionDao.findVersionCountBySearchNot(version);
			Integer pages = count/version.getRows()+1;
			List<Version> list = versionDao.findVersionBySearchNot(version);
			System.out.println(list.size());
			Map mvmap = new HashMap();
			mvmap.put("version", version);
			mvmap.put("count", count);
			mvmap.put("rows", list);
			mvmap.put("page", version.getPage());
			mvmap.put("pages", pages);
			mv.addObject("result", mvmap);
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return mv;
	}

	@RequestMapping(value = "getdata", method = RequestMethod.POST)
	public @ResponseBody String getdata(HttpServletResponse response,
			Integer device_id) {
		List result = null;
		try{
			result = versionDao.findVersionById(device_id);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HashMap map = new HashMap();
		map.put("total", result.size());
		map.put("rows", result);
		map.put("success", "success");
		//JSONArray jsonArray = JSONArray.fromObject( result);
		JSONObject jsonObject = JSONObject.fromObject(map);
	
		String ret = jsonObject.toString();
		return ret;
		
		/*response.setContentType("text/json; charset=utf-8");
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setHeader("Content-Encoding", "utf-8"); 
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-store"); 
		response.setHeader("Pragma", "no-cache"); 
		response.setDateHeader("Expires", 0); 
		response.setCharacterEncoding("utf-8");  
		
		
		out.print(jsonObject);
		System.out.println(jsonObject);
		out.flush();
		out.close();*/
		
	}
	//search
	@RequestMapping(value = "exportTable1", method = RequestMethod.GET)
	//public String logout(HttpSession session) {
	public void exportTable(HttpServletResponse response,Version version,String not) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = null,date2=null;
		List<Version> result = null;
		try {
			if(version.getTime1()!=null){
				date1 = format.parse(version.getTime1());
				version.setCreate_time(date1.getTime());
			}
			if(version.getTime2()!=null){
				date2 = format.parse(version.getTime2());
				version.setUpdate_time(date2.getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//Map map = new HashMap();
		//map.put("device_id", version.getDevice_id());
		try{
			version.setRows(null);
			if(not==null)
				result = versionDao.findVersionBySearch(version);
			else
				result = versionDao.findVersionBySearchNot(version);
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		try {
			List<Version> tmplist = versionDao2.findAllWifiDevice();
			Map<String,String> tmpmap = new HashMap<String,String>();
			for(Version elem:tmplist){
				tmpmap.put(elem.getBiz_num(), elem.getWifi_sn());
			}
			for(Version elem:result){
				String tmp = tmpmap.get(elem.getBiz_num());
				elem.setWifi_sn(tmp);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		LinkedHashMap<String, String> fieldMap = getLeadToFiledPublicQuestionBank();
		
		

		try {
			ExcelUtil.listToExcel(result, fieldMap, "版本搜索", response);
			//response.addHeader("Content-Disposition", "用户表"); 
		} catch (ExcelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        System.out.println("导出成功~~~~");
		
		
	}
	@RequestMapping(value = "exportTable2", method = RequestMethod.GET)
	//public String logout(HttpSession session) {
	public void exportTable2(HttpServletResponse response,Version version,String not) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = null,date2=null;
		List<Version> result = null;
		try {
			if(version.getTime1()!=null){
				date1 = format.parse(version.getTime1());
				version.setCreate_time(date1.getTime());
			}
			if(version.getTime2()!=null){
				date2 = format.parse(version.getTime2());
				version.setUpdate_time(date2.getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//Map map = new HashMap();
		//map.put("device_id", version.getDevice_id());
		String ids="";
		try{
			version.setRows(null);
			if(not==null)
				result = versionDao.findVersionBySearch(version);
			else
				result = versionDao.findVersionBySearchNot(version);
			List<String> idslist = new ArrayList<String>();
			for(Version elem:result){
				if(!idslist.contains(elem.getDevice_id().toString())){
					idslist.add(elem.getDevice_id().toString());
				}
			}
			 for(String elem:idslist){
				 if(!ids.equals(""))
					 ids += ",";
				 ids += elem;
				 
			 }
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		response.setContentType("application/x-msdownload;charset=UTF-8");
		try {
			response.setHeader("Content-Disposition","attachment;filename=" + 
					java.net.URLEncoder.encode("version.txt", "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setHeader("Content-Encoding", "utf-8"); 
		
		
		out.print(ids);
		
		out.flush();
		out.close();
		
		
	}
	
	public LinkedHashMap<String, String> getLeadToFiledPublicQuestionBank() {  
		  
	    LinkedHashMap<String, String> superClassMap = new LinkedHashMap<String, String>();  
	  
	    //superClassMap.put("id", "序号");  
	    superClassMap.put("device_id", "device_id");  	    
	    superClassMap.put("biz_num", "biz_num");  
	    superClassMap.put("wifi_sn", "wifi_sn");  
	    superClassMap.put("software_name", "software_name");  
	    superClassMap.put("version_str", "version"); 
	    superClassMap.put("update_time_str", "update_time");  
	    superClassMap.put("create_time_str", "create_time");  
	    
	   
	    
	    
	  
	    return superClassMap;  
	}
	
	@RequestMapping(value = "getversion", method = RequestMethod.POST)
	public @ResponseBody String gerversion(HttpServletResponse response,
			@RequestBody String json) {
		String ret="succeed";
		

		
		String newjson = URLDecoder.decode(json);
		newjson = newjson.replace("=", "");
		//System.out.println(newapopen);
		
		
		
		//TestUser user = (TestUser)JSONArray.toBean(testUser,TestUser.class);
		
		//JSONObject newjson1 = JSONObject.fromObject(newjson);
		JSONArray jarray = JSONArray.fromObject(newjson);
		String bizs="";
		//List bizs = new ArrayList();
		for(int i=0;i<jarray.size();i++){
			if(i>0)bizs+=",";
			JSONObject object = (JSONObject)jarray.get(i);
			bizs += "'"+object.getString("biz_num")+"'";
			//bizs.add(object.getString("biz_num"));
		}
		//String biz_num = (String)newjson1.get("biz_num");
		List<Version> result=null;
		try {
			result = versionDao.findVersionByBizs(bizs);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			ret = "Some errors occured";
			
		}
		
		Map map0 = new HashMap();
		
		for(Version elem:result){
			String biz = elem.getBiz_num();
			Map tmpmap = (Map)map0.get(biz);
			if(tmpmap==null){
				tmpmap = new HashMap();
				map0.put(biz, tmpmap);
			}
			tmpmap.put(elem.getSoftware_name(), elem.getVersion_str());
		}
		
		map0.put("success", ret);
		//JSONArray jsonArray = JSONArray.fromObject( result);
		JSONObject jsonObject = JSONObject.fromObject(map0);
		
		return jsonObject.toString();
		
		/*rintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setHeader("Cache-Control", "no-store"); 
		response.setHeader("Pragma", "no-cache"); 
		response.setDateHeader("Expires", 0); 
		out.print(jsonObject);
		out.flush();
		out.close();*/
		

	}
	
	@RequestMapping(value = "gethotlist", method = RequestMethod.POST)
	public @ResponseBody String gethotlist(HttpServletResponse response,
			@RequestBody String json) {
		String ret="succeed";
		

		
		String newjson = URLDecoder.decode(json);
		newjson = newjson.replace("=", "");
		//System.out.println(newapopen);
		
		
		
		
		
		JSONObject newjson1 = JSONObject.fromObject(newjson);
		
		String wifi_sn = (String)newjson1.get("wifi_sn");
		System.out.println("wifi_sn="+wifi_sn);
		List<Map> result=null;
		try {
			result = versionDao.findHotlist(wifi_sn);
			System.out.println("result.size="+result.size());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			ret = "Some errors occured";
			
		}
		
		Map map0 = new HashMap();
		ArrayList maplist = new ArrayList<Map>();
		
		for(Map elem:result){
			HashMap tmpmap = new HashMap();
			//map0.put(biz, tmpmap);
			maplist.add(tmpmap);
			tmpmap.put("name", elem.get("name"));
			tmpmap.put("password", elem.get("password"));
		}
		
		map0.put("success", ret);
		map0.put("list", maplist);
		//JSONArray jsonArray = JSONArray.fromObject( result);
		JSONObject jsonObject = JSONObject.fromObject(map0);
		
		return jsonObject.toString();
		

		

	}
	
	@RequestMapping(value = "sethotlist", method = RequestMethod.POST)
	public @ResponseBody String sethotlist(HttpServletResponse response,
			@RequestBody String json) {
		String ret="succeed";
		

		
		String newjson = URLDecoder.decode(json);
		newjson = newjson.replace("=", "");
		//System.out.println(newapopen);
		
		
		
		//TestUser user = (TestUser)JSONArray.toBean(testUser,TestUser.class);
		
		//JSONObject newjson1 = JSONObject.fromObject(newjson);
		JSONArray jarray = JSONArray.fromObject(newjson);
		String bizs="";
		//List bizs = new ArrayList();
		for(int i=0;i<jarray.size();i++){
			if(i>0)bizs+=",";
			JSONObject object = (JSONObject)jarray.get(i);
			String wifi_sn = object.getString("wifi_sn");
			String name = object.getString("name");
			String password = object.getString("password");

			Map map = new HashMap();
			map.put("wifi_sn", wifi_sn);
			map.put("name", name);
			map.put("password", password);
			
			try {
				versionDao.addHotlist(map);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ret = "Some errors occured";
			}
		}
		
		
		Map map0 = new HashMap();
		map0.put("success", ret);
		//JSONArray jsonArray = JSONArray.fromObject( result);
		JSONObject jsonObject = JSONObject.fromObject(map0);
		
		return jsonObject.toString();
		

		

	}
	
	@RequestMapping(value = "setbindlist", method = RequestMethod.POST)
	public @ResponseBody String setbindlist(HttpServletResponse response,
			@RequestBody String json) {
		String ret="succeed";
		

		
		String newjson = URLDecoder.decode(json);
		newjson = newjson.replace("=", "");
		//System.out.println(newapopen);
		
		
		
		//TestUser user = (TestUser)JSONArray.toBean(testUser,TestUser.class);
		
		//JSONObject newjson1 = JSONObject.fromObject(newjson);
		JSONArray jarray = JSONArray.fromObject(newjson);
		String bizs="";
		//List bizs = new ArrayList();
		for(int i=0;i<jarray.size();i++){
			if(i>0)bizs+=",";
			JSONObject object = (JSONObject)jarray.get(i);
			String wifi_sn = object.getString("wifi_sn");
			String sn = object.getString("sn");

			Map map = new HashMap();
			map.put("wifi_sn", wifi_sn);
			map.put("sn", sn);
			
			try {
				versionDao.addBindlist(map);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ret = "Some errors occured";
			}
		}
		
		
		Map map0 = new HashMap();
		map0.put("success", ret);
		//JSONArray jsonArray = JSONArray.fromObject( result);
		JSONObject jsonObject = JSONObject.fromObject(map0);
		
		return jsonObject.toString();

	}
	
	@RequestMapping(value = "getbindlist", method = RequestMethod.POST)
	public @ResponseBody String getbindlist(HttpServletResponse response,
			@RequestBody String json) {
		String ret="succeed";
		

		
		String newjson = URLDecoder.decode(json);
		newjson = newjson.replace("=", "");
		//System.out.println(newapopen);
		
		
		
		
		
		JSONObject newjson1 = JSONObject.fromObject(newjson);
		
		String wifi_sn = (String)newjson1.get("wifi_sn");
		System.out.println("wifi_sn="+wifi_sn);
		List<Map> result=null;
		try {
			result = versionDao.findBindlist(wifi_sn);
			System.out.println("result.size="+result.size());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			ret = "Some errors occured";
			
		}
		
		Map map0 = new HashMap();
		ArrayList maplist = new ArrayList<Map>();
		
		for(Map elem:result){
			HashMap tmpmap = new HashMap();
			//map0.put(biz, tmpmap);
			maplist.add(tmpmap);
			tmpmap.put("wifi_sn", elem.get("wifi_sn"));
			tmpmap.put("sn", elem.get("sn"));
		}
		
		map0.put("success", ret);
		map0.put("list", maplist);
		//JSONArray jsonArray = JSONArray.fromObject( result);
		JSONObject jsonObject = JSONObject.fromObject(map0);
		
		return jsonObject.toString();
		

		

	}

}
