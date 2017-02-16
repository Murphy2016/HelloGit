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
@RequestMapping(value="task")
public class TaskController1 {
	
	@RequestMapping(value="index")
	public ModelAndView index(HttpServletResponse response,String taskid,String taskname,
			String tasktype,String createtime,String updatetime,String keyword,Integer page){
		ModelAndView mv = new ModelAndView("task/Task");
		if(page==null)
			page=1;
		
		JSONArray jarr = getTaskList(taskid,taskname,tasktype,createtime,updatetime);
		Integer count = jarr.size();
		Integer rows = 2;
		Integer pages = count/rows + count%rows;
		List list1 = ListUtil.getPageList(jarr,page,rows);
		mv.addObject("page",page);
		mv.addObject("pages",pages);
	       
		String myItem = "test";
		mv.addObject("item",myItem);
		
		mv.addObject("list",list1);
		mv.addObject("taskid",taskid);
		mv.addObject("taskname",taskname);
		mv.addObject("tasktype",tasktype);
		mv.addObject("createtime",createtime);
		mv.addObject("updatetime",updatetime);
		mv.addObject("keyword",keyword);
		
		return mv;
	}

	@RequestMapping(value="create")
	public ModelAndView createtask(HttpServletResponse response){
		ModelAndView mv = new ModelAndView("task/TaskEdit");
		//JSONArray jarr = getWeekStatistic();
	    Integer taskid = getTaskid();
	    mv.addObject("taskid",taskid);
		//mv.addObject("list",jarr);
		
		return mv;
	}
	@RequestMapping(value="edit")
	public ModelAndView edittask(HttpServletResponse response,String taskid,String taskname,String tasktype,
			String taskcontent,String createtime,String updatetime,String taskdisc){
		ModelAndView mv = new ModelAndView("task/TaskEdit");
		//JSONArray jarr = getWeekStatistic();
	    
	    
		mv.addObject("taskid",taskid);
		mv.addObject("taskname",taskname);
		mv.addObject("tasktype",tasktype);
		mv.addObject("taskcontent",taskcontent);
		mv.addObject("createtime",createtime);
		mv.addObject("updatetime",updatetime);
		mv.addObject("taskdisc",taskdisc);
		
		return mv;
	}
	
	
	
	private Integer getTaskid(){
        Integer id = 12345;
        return id;
	}
	@RequestMapping(value="savetask")
	public ModelAndView savetask(HttpServletResponse response, Integer taskid,
                    String taskname,String tasktype,String taskcontent,String taskcomment){
		//ModelAndView mv = new ModelAndView("task/Task");
		
		//JSONArray jarr = getWeekStatistic();
		Integer ref = saveTask(taskid,taskname,taskname,taskcontent,taskcomment);
		ref = ref+1;
		System.out.println(taskname);
             
		//mv.addObject("taskid",taskid);
		
		//mv.addObject("list",jarr);
		
		return index(response,null,null,null,null,null,null,null);
	}
	private Integer saveTask(Integer taskid,String taskname,String tasktype,String taskcontent,
			String taskcomment){
		
		//save the task info to DB
		return 1;
	}

	
	public JSONArray getTaskList(String taskid,String taskname,
			String tasktype,String createtime,String updatetime){
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
			map.put("taskid", 10+i);
			map.put("taskname", "任务"+10+i);
			map.put("tasktype", "资源查看");
			map.put("taskcontent", "this is a task!");
			map.put("taskcreatetime", "2017-01-20 00:00:00");
			map.put("taskupdatetime", "2017-01-21 00:00:00");
			map.put("taskdisc", "this is the discription.");
			
			
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



