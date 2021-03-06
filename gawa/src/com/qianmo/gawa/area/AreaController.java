package com.qianmo.gawa.area;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qianmo.gawa.operlog.Operlog;
import com.qianmo.gawa.operlog.OperlogDao;
import com.qianmo.gawa.operlog.OperlogService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping(value = "/area")
public class AreaController {

	@Resource
	private AreaService areaService;
	
	@Resource
	private AreaDao areaDao;
	@Resource
	private OperlogService operlogService;
//	

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session,
			HttpServletRequest request,			
			HttpServletResponse response,Model model) {
		
		/////////////session.setAttribute("scroll_begin", "miao miao miao~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		String sessionid = request.getSession().getId();
		System.out.println(sessionid);
		HttpSession session1 = request.getSession();
		String usrcnt = (String)session1.getAttribute("userctx");
		System.out.println(usrcnt+"**********************************");
		
		String username = (String)session.getAttribute("userctx");
		operlogService.addOperlogToDb(username, "浏览场所信息");
		
		ModelAndView mv = new ModelAndView("area/arealist");
		//mv.addObject("test11", "nice");
		//model.addAttribute("test11", "nice");
		
		return mv; 
	}
	
	@RequestMapping(value = "getdata", produces = "application/json;charset=UTF-8")
	//public String logout(HttpSession session) {
	public @ResponseBody  String getdata(HttpSession session,HttpServletResponse response,
			Integer page,Integer rows,String search,String date) {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		
		
		
		Integer offset = (page-1)*rows;
		List result = null;
		Integer total = null;
		try {
			if(search==null){
				result = areaDao.findAreaByPage(offset,rows);
				total= areaDao.findAreaCount();
			}else{
				result = areaDao.findAreaByAreacode(search);
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
		JSONObject jsonObject = JSONObject.fromObject(map);
		
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
		String ret = jsonObject.toString();
		System.out.println(ret);
		return ret;
		//return "hello";
	}
	//to get area_code and area_name
	@RequestMapping(value = "getdata01", method = RequestMethod.POST)
	//public String logout(HttpSession session) {
	public void getdata01(HttpSession session,HttpServletResponse response,
			Integer page,Integer rows,String search,String date) {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		Integer offset = (page-1)*rows;
		List result = null;
		Integer total = null;
		try {
			if(search==null){
				result = areaDao.findAreaByPage(offset,rows);
				total= areaDao.findAreaCount();
			}else{
				result = areaDao.findAreaByAreacode(search);
				total= result.size();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HashMap map = new HashMap();
		//map.put("total", total);
		map.put("rows", result);
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
	
	
	
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public void insertdata(HttpSession session,HttpServletResponse response,
			Area area) {
		HashMap map = new HashMap();
		try {
			if(area != null){
				area = areaDao.addArea(area);//return null,but insert successfully.	
				map.put("area_id", "succeed");
				
				String username = (String)session.getAttribute("userctx");
				operlogService.addOperlogToDb(username, "添加场所："+area.toString());
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
			Area area,Long id) {
		HashMap map = new HashMap();
		Integer ret = null;
		try {
			if(area != null){
				area.setArea_id(id);
				ret = areaDao.updateArea(area);	
				map.put("ret", ret);
				
				String username = (String)session.getAttribute("userctx");
				operlogService.addOperlogToDb(username, "修改场所："+area.toString());
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
	@RequestMapping(value = "updatestate", method = RequestMethod.POST)
	public void updatedatastate(HttpSession session,HttpServletResponse response,
			Area area,Long id) {
		HashMap map = new HashMap();
		Integer ret = null;
		try {
			if(area != null){
				area.setArea_id(id);
				ret = areaDao.updateAreaState(area);	
				map.put("success", "success");
				
				String username = (String)session.getAttribute("userctx");
				operlogService.addOperlogToDb(username, "修改场所："+area.toString());
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
			Long area_id) {
		HashMap map = new HashMap();
		Integer ret = null;
		try {
			if(area_id != null){
				Area area = areaDao.findAreaById(Integer.valueOf(area_id.toString())).get(0);
				ret = areaDao.deleteArea(area_id);	
				map.put("success", "success");
				
				String username = (String)session.getAttribute("userctx");
				operlogService.addOperlogToDb(username, "删除场所信息："+area.toString());
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
