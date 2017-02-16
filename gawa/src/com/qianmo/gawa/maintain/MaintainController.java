package com.qianmo.gawa.maintain;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import org.springframework.web.servlet.ModelAndView;

import com.qianmo.gawa.operlog.Operlog;
import com.qianmo.gawa.operlog.OperlogDao;
import com.qianmo.gawa.operlog.OperlogService;
import com.qianmo.gawa.util.DateJsonValueProcessor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


@Controller
@RequestMapping(value = "/maintain")
public class MaintainController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	@Resource
	private MaintainDao maintainDao;
//	

	@Resource
	private OperlogService operlogService;
	
	   
	  
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session,HttpServletResponse response) {

		String username = (String)session.getAttribute("userctx");
		operlogService.addOperlogToDb(username, "浏览维修信息");
		return new ModelAndView("maintain/maintainlist");
	}
	
	@RequestMapping(value = "getdata", method = RequestMethod.POST)
	//public String logout(HttpSession session) {
	public void getdata(HttpSession session,HttpServletResponse response,
			Integer page,Integer rows,Integer id,String code,String date) {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		Integer offset = (page-1)*rows;
		List result = null;
		Integer total = null;
		try {
			if(id==null && code == null && date == null){
				result = maintainDao.findMaintainByPage(offset,rows);
				total= maintainDao.findMaintainCount();
			}else if(id != null){
				result = maintainDao.findMaintainById(id);
				total= result.size();
			}else if(code != null){
				result = maintainDao.findMaintainByCode(code);
				total= result.size();
			}else if(date != null){
				result = maintainDao.findMaintainByDate(date);
				total= result.size();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HashMap map = new HashMap();
		map.put("total", total);
		map.put("rows", result);
		//JSONArray jsonArray = JSONArray.fromObject( result);
		JsonConfig config = new JsonConfig(); 
		config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd")); 
		JSONObject jsonObject = JSONObject.fromObject(map,config);
		
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
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public void insertdata(HttpSession session,HttpServletResponse response,
			Maintain maintain) {
		HashMap map = new HashMap();
		try {
			if(maintain != null){
				maintain = maintainDao.addMaintain(maintain);//return null,but insert successfully.	
				map.put("succeed", "succeed");
				
				String username = (String)session.getAttribute("userctx");
				operlogService.addOperlogToDb(username, "添加维修记录："+maintain.toString());
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
			Maintain maintain,Integer id) {
		HashMap map = new HashMap();
		Integer ret = null;
		try {
			if(maintain != null){
				maintain.setMaintain_id(id);
				ret = maintainDao.updateMaintain(maintain);	
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
			Long maintain_id) {
		HashMap map = new HashMap();
		Integer ret = null;
		try {
			if(maintain_id != null){
				ret = maintainDao.deleteMaintain(maintain_id);	
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
