package com.qianmo.version.task;

import java.util.ArrayList;
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
@RequestMapping(value="taskext")
public class TaskextController {
	
	@RequestMapping(value="index")
	public ModelAndView index(HttpServletResponse response,String taskextid,String taskextname,
			String taskextdest,String taskextstate,String starttime,String endtime,Integer page){
		ModelAndView mv = new ModelAndView("task/Taskext");
		if(page==null)
			page=1;
		
		JSONArray jarr = getTaskextList();
		Integer count = jarr.size();
		Integer rows = 2;
		Integer pages = count/rows + count%rows;
		List list1 = ListUtil.getPageList(jarr,page,rows);
		mv.addObject("page",page);
		mv.addObject("pages",pages);
	       
		
		mv.addObject("taskextid",taskextid);
		mv.addObject("taskextname",taskextname);
		mv.addObject("taskextdest",taskextdest);
		mv.addObject("taskextstate",taskextstate);
		mv.addObject("starttime",starttime);
		mv.addObject("endtime",endtime);
		
		mv.addObject("list",list1);
		
		return mv;
	}

	@RequestMapping(value="create")
	public ModelAndView createtask(HttpServletResponse response){
		ModelAndView mv = new ModelAndView("task/TaskextEdit");
		//JSONArray jarr = getWeekStatistic();
	    Integer taskid = getTaskid();
	    mv.addObject("taskid",taskid);
		//mv.addObject("list",jarr);
		
		return mv;
	}
	@RequestMapping(value="edit")
	public ModelAndView edittaskext(HttpServletResponse response,String taskextid,String taskextname,
			String taskextdest,String taskextstate,String taskextperiod,
			String taskextcontent,String taskextcomment,
			String starttime,String endtime){
		ModelAndView mv = new ModelAndView("task/TaskextEdit");
		
	    
	    mv.addObject("taskextid",taskextid);
	    mv.addObject("taskextname",taskextname);
	    mv.addObject("taskextdest",taskextdest);
	    mv.addObject("taskextstate",taskextstate);
	    mv.addObject("taskextperiod",taskextperiod);
	    mv.addObject("taskextcontent",taskextcontent);
	    mv.addObject("taskextcomment",taskextcomment);
		
		
		return mv;
	}
	
	
	@RequestMapping(value="detail")
	public ModelAndView detail(HttpServletResponse response,String taskextid,String taskextname,
			String groupname,String devicename,String state,String keyword,Integer page){
		ModelAndView mv = new ModelAndView("task/TaskextDetail");
		if(page==null)
			page=1;
		//JSONArray jarr = getWeekStatistic();
	    
	    
	    JSONArray jarr = getTaskextDetail(taskextid,groupname,devicename,state,keyword);
	    Integer count = jarr.size();
		Integer rows = 2;
		Integer pages = count/rows + count%rows;
		List list1 = ListUtil.getPageList(jarr,page,rows);
		mv.addObject("page",page);
		mv.addObject("pages",pages);
	    
	    mv.addObject("groupname",groupname);
	    mv.addObject("devicename",devicename);
	    mv.addObject("state",state);
	    mv.addObject("keyword",keyword);
		
		mv.addObject("number",taskextid);
		mv.addObject("name",taskextname);
		mv.addObject("result","It's over!");
		mv.addObject("comment","It's comment.");
		mv.addObject("period","2017-02-02 2017-02-20");
		
		mv.addObject("list",list1);
		
		return mv;
	}
	private Integer getTaskid(){
        Integer id = 12345;
        return id;
	}
	@RequestMapping(value="save")
	public ModelAndView savetaskext(HttpServletResponse response, String taskextid,String taskextname,
			String taskextdest,String taskextstate,String taskextperiod,
			String taskextcontent,String taskextcomment,
			String startdate,String enddate){
		//ModelAndView mv = new ModelAndView("task/Task");
		
		//JSONArray jarr = getWeekStatistic();
		Integer ref = saveTaskext( taskextid, taskextname,
				 taskextdest, taskextstate, taskextperiod,
				 taskextcontent, taskextcomment);
		
		System.out.println(taskextname);
             
		//mv.addObject("taskid",taskid);
		
		//mv.addObject("list",jarr);
		
		return index(response,null,null,null,null,null,null,null);
	}
	private Integer saveTaskext(String taskextid,String taskextname,
			String taskextdest,String taskextstate,String taskextperiod,
			String taskextcontent,String taskextcomment){
		
		//save the task info to DB
		return 1;
	}

	
	public JSONArray getTaskextList(){
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
			map.put("taskextid", 10+i);
			map.put("taskextname", "任务单"+10+i);
			map.put("taskextdest", "设备小组"+i);
			map.put("taskextstate", "running");
			map.put("taskextperiod", "2017-01-20 2017-02-20");
			map.put("taskextcontent", "copy all to remote host.");
			map.put("taskextcomment", "none.");
			
			
			list.add(map);
		}
		JSONArray jarr = JSONArray.fromObject(list);
		return jarr;
	}
	
	public JSONArray getTaskextDetail(String taskextid,String groupname,String devicename,
			String state,String keyword){
		ArrayList list = new ArrayList();
		for(int i=0;i<3;i++){
			HashMap map = new HashMap();
			map.put("filename", "file"+10+i);
			map.put("filesize", 10+i*123+456);
			map.put("groupname", "group"+(i+1));
			map.put("devicename", "device"+(i+10));
			map.put("needtime", "34:28");
			map.put("percent", "80%");
			
			
			
			list.add(map);
		}
		JSONArray jarr = JSONArray.fromObject(list);
		return jarr;
	}
	
	
	public static void main(String[] args ){
		
	    System.out.println("hello world");
	    String test = "hello world";
	    String[] ret = test.split(" ");
	    System.out.println(ret[0]);
	    System.out.println(ret[1]);
	}//main
}



