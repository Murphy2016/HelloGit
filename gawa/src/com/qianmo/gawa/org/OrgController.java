package com.qianmo.gawa.org;

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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.qianmo.gawa.operlog.Operlog;
import com.qianmo.gawa.operlog.OperlogDao;
import com.qianmo.gawa.operlog.OperlogService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping(value = "/org")
public class OrgController {


	
	@Resource
	private OrgDao orgDao;
	
	@Resource
	private OperlogService operlogService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session,HttpServletResponse response) {

		String username = (String)session.getAttribute("userctx");
		operlogService.addOperlogToDb(username, "浏览安全厂商信息");
		return new ModelAndView("org/orglist");
	}
	
	@RequestMapping(value = "getdata", method = RequestMethod.POST)
	//public String logout(HttpSession session) {
	public void getdata(HttpSession session,HttpServletResponse response,
			Integer page,Integer rows,String search) {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		String level = (String)session.getAttribute("userlevel");
		System.out.println("level="+level);
		Integer offset = (page-1)*rows;
		List result = null;
		Integer total = null;
		if(level.equals("0")){
			result = new ArrayList();
			total = 0;
		}else{
			try {
				if(search==null){
					result = orgDao.findOrgByPage(offset,rows);
					total= orgDao.findOrgCount();
				}else{
					result = orgDao.findOrgBySearch(search);
					total= result.size();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		HashMap map = new HashMap();
		map.put("total", total);
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
			Org org) {
		HashMap map = new HashMap();
		try {
			if(org != null){
				org = orgDao.addOrg(org);//return null,but insert successfully.	
				map.put("succeed", "succeed");
				
				String username = (String)session.getAttribute("userctx");
				operlogService.addOperlogToDb(username, "添加安全厂商信息："+org.toString());
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
			Org org,Long id) {
		HashMap map = new HashMap();
		Integer ret = null;
		try {
			if(org != null){
				org.setOrg_id(id);
				ret = orgDao.updateOrg(org);	
				map.put("ret", ret);
				
				String username = (String)session.getAttribute("userctx");
				operlogService.addOperlogToDb(username, "修改安全厂商信息："+org.toString());
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
			Long org_id) {
		HashMap map = new HashMap();
		Integer ret = null;
		try {
			if(org_id != null){
				Org org = orgDao.findOrgById(Integer.valueOf(org_id.toString()));
				ret = orgDao.deleteOrg(org_id);	
				map.put("success", "success");
				String username = (String)session.getAttribute("userctx");
				operlogService.addOperlogToDb(username, "添加安全厂商信息："+org.toString());
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
