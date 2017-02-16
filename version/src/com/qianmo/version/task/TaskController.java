package com.qianmo.version.task;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qianmo.version.util.HttpUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="task0")
public class TaskController {
	
	@RequestMapping(value="index")
	public ModelAndView index(HttpServletResponse response){
		ModelAndView mv = new ModelAndView("task/Task");
		
		JSONArray jarr = getWeekStatistic();
		
		
		mv.addObject("list",jarr);
		
		return mv;
	}
	
	public JSONArray getWeekStatistic(){
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
			map.put("tasktype", "推送");
			map.put("taskcontent", "this is a task!");
			map.put("taskcreatetime", "2017-01-20 00:00:00");
			map.put("taskupdatetime", "2017-01-21 00:00:00");
			map.put("taskdisc", "this is the discription.");
			
			
			list.add(map);
		}
		JSONArray jarr = JSONArray.fromObject(list);
		return jarr;
	}

}
