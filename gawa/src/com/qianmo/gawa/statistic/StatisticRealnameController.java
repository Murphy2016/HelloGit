package com.qianmo.gawa.statistic;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.qianmo.gawa.operlog.OperlogService;
import com.qianmo.gawa.util.ExcelException;
import com.qianmo.gawa.util.ExcelUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping(value = "/statisticrealname")
public class StatisticRealnameController {


	
	@Resource
	private StatisticDao statisticDao;
	@Resource
	private OperlogService operlogService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session,HttpServletResponse response) {

		String username = (String)session.getAttribute("userctx");
		operlogService.addOperlogToDb(username, "浏览上网实名率统计");
		return new ModelAndView("statistic/statisticrealnamelist");
	}
	
	@RequestMapping(value = "getdata", method = RequestMethod.POST)
	//public String logout(HttpSession session) {
	public void getdata(HttpSession session,HttpServletResponse response,
			Integer page,Integer rows,Integer id,
			String date1,String date2) {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		Integer offset = (page-1)*rows;
		List result = null;
		Integer total = null;
		try {
			if(date1==null){
				result = statisticDao.findStatisticRealnameByPage(offset,rows);
				total= statisticDao.findStatisticRealnameCount();
			}else if( date1!=null && date2!=null){
				result = statisticDao.findStatisticRealnameByDate(date1,date2);
				total= result.size();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List date = new ArrayList();
		List count = new ArrayList();
		for(Object elem:result){
			Statistic e = (Statistic)elem;
			date.add(e.getDate1());
			count.add(e.getCount());
		}
		HashMap map = new HashMap();
		
		map.put("total",total);
		map.put("rows", result);
		/*map.put("success", "success");
		
		map.put("date", date);
		map.put("count", count)*/;
		//JSONArray jsonArray = JSONArray.fromObject( result);
		JSONObject jsonObject = JSONObject.fromObject(map);
		
		response.setContentType("text/json; charset=utf-8");
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
		out.close();
		//return "hello";
	}
	//for map 
	@RequestMapping(value = "getdata1", method = RequestMethod.POST)
	//public String logout(HttpSession session) {
	public void getdata1(HttpSession session,HttpServletResponse response,
			Integer page,Integer rows,Integer id,
			String date1,String date2) {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		
		List result = null;
		
		try {
			 if(date1!=null && date2!=null){
				 result = statisticDao.findStatisticRealnameByDate(date1,date2);
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List date = new ArrayList();
		List count = new ArrayList();
		List count1 = new ArrayList();
		for(Object elem:result){
			Statistic e = (Statistic)elem;
			date.add(e.getDate1());
			count.add(100);
			count1.add(100);
		}
		HashMap map = new HashMap();
		
		
		map.put("success", "success");
		
		map.put("date", date);
		map.put("count", count);
		map.put("count1", count1);
		//JSONArray jsonArray = JSONArray.fromObject( result);
		JSONObject jsonObject = JSONObject.fromObject(map);
		
		response.setContentType("text/json; charset=utf-8");
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
		out.close();
		//return "hello";
	}
	
	
	@RequestMapping(value = "exportTable", method = RequestMethod.GET)
	//public String logout(HttpSession session) {
	public void exportTable(HttpSession session,HttpServletResponse response,
			Integer page,Integer rows,String mobileno,
			String date1,String date2) {
		//Integer offset = (page-1)*rows;
		List result = null;
		Integer total = null;
		try {
			if(mobileno==null){
				result = statisticDao.findStatisticRealnameByPage(0,1000);
				total= statisticDao.findStatisticRealnameCount();
			}else if(mobileno!=null && date1!=null && date2!=null){
				result = statisticDao.findStatisticUserByDate(mobileno,date1,date2);
				total= result.size();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		LinkedHashMap<String, String> fieldMap = getLeadToFiledPublicQuestionBank();
		
		

		try {
			ExcelUtil.listToExcel(result, fieldMap, "实名认证", response);
			//response.addHeader("Content-Disposition", "用户表"); 
		} catch (ExcelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        System.out.println("导出成功~~~~");
		
		
	}
	public LinkedHashMap<String, String> getLeadToFiledPublicQuestionBank() {  
		  
	    LinkedHashMap<String, String> superClassMap = new LinkedHashMap<String, String>();  
	  
	    //superClassMap.put("id", "序号");  
	    superClassMap.put("displaydate", "日期");  
	    
	    superClassMap.put("count", "实名认证总数");  
	    superClassMap.put("bus", "公共交通工具");  
	   
	    
	    
	  
	    return superClassMap;  
	}
	
	
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public void insertdata(HttpSession session,HttpServletResponse response,
			Statistic statistic) {
		HashMap map = new HashMap();
		try {
			if(statistic != null){
				statistic = statisticDao.addStatistic(statistic);//return null,but insert successfully.	
				map.put("succeed", "succeed");
			}else{
				map.put("errorMsg", "Some errors occured");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		JSONObject jsonObject = JSONObject.fromObject(map);
		
		response.setContentType("text/json; charset=utf-8");
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
		//System.out.println(jsonObject);
		out.flush();
		out.close();
	}
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public void updatedata(HttpSession session,HttpServletResponse response,
			Statistic statistic,Long id) {
		HashMap map = new HashMap();
		Integer ret = null;
		try {
			if(statistic != null){
				statistic.setStatistic_id(id);
				ret = statisticDao.updateStatistic(statistic);	
				map.put("ret", ret);
			}else{
				map.put("errorMsg", "Some errors occured");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		JSONObject jsonObject = JSONObject.fromObject(map);
		
		response.setContentType("text/json; charset=utf-8");
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
		//System.out.println(jsonObject);
		out.flush();
		out.close();
	}
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void deletedata(HttpSession session,HttpServletResponse response,
			Long statistic_id) {
		HashMap map = new HashMap();
		Integer ret = null;
		try {
			if(statistic_id != null){
				ret = statisticDao.deleteStatistic(statistic_id);	
				map.put("success", "success");
			}else{
				map.put("errorMsg", "Some errors occured");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		JSONObject jsonObject = JSONObject.fromObject(map);
		
		response.setContentType("text/json; charset=utf-8");
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
		//System.out.println(jsonObject);
		out.flush();
		out.close();
	}



		
	
}
