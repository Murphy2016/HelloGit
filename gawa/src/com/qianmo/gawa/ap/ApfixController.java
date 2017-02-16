package com.qianmo.gawa.ap;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.qianmo.gawa.onoffline.Onoffline;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping(value = "/apfix")
public class ApfixController {

	private static Timer timer = null;
	
	@Resource
	private ApDao apDao;
//	

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session,HttpServletResponse response) {

		return new ModelAndView("ap/aplistfix");
	}
	@RequestMapping(value = "index1", method = RequestMethod.GET)
	public ModelAndView index1(HttpSession session,HttpServletResponse response) {

		return new ModelAndView("ap/apstatelist");
	}
	
	@RequestMapping(value = "getdata", method = RequestMethod.POST)
	//public String logout(HttpSession session) {
	public void getdata(HttpSession session,HttpServletResponse response,
			Integer page,Integer rows,Integer id,String code,Integer state) {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		Integer offset = (page-1)*rows;
		List result = new ArrayList();
		Integer total = 0;
		/*try {
			if(code==null && state == null){
				result = apDao.findApByPage(offset,rows);
				total= apDao.findApCount();
			}else if(code !=null){
				result = apDao.findApByCode(code);
				total= result.size();
			}else{
				result = apDao.findApByState(state);
				total= result.size();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
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
			Ap ap) {
		HashMap map = new HashMap();
		try {
			if(ap != null){
				ap = apDao.addAp(ap);//return null,but insert successfully.	
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
			Ap ap,Long id) {
		HashMap map = new HashMap();
		Integer ret = null;
		try {
			if(ap != null){
				ap.setAllap_id(id);
				ret = apDao.updateAp(ap);	
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
			Long ap_id) {
		HashMap map = new HashMap();
		Integer ret = null;
		try {
			if(ap_id != null){
				ret = apDao.deleteAp(ap_id);	
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
	@RequestMapping(value = "apopen", method = RequestMethod.POST)
	public void apopen(HttpServletRequest request,HttpSession session,HttpServletResponse response,
			@RequestBody String apopen) {

		HashMap map = new HashMap();
		String ret="succeed";
		System.out.println(apopen);
		String newapopen = URLDecoder.decode(apopen);
		newapopen = newapopen.replace("=", "");
		System.out.println(newapopen);
		
		
		
		//TestUser user = (TestUser)JSONArray.toBean(testUser,TestUser.class);
		JSONObject newapopen1 = JSONObject.fromObject(newapopen);
		String ap_code = (String)newapopen1.get("ap_code");
		String ap_mac = (String)newapopen1.get("ap_mac");
		
		List<Ap> result=null;
		try {
			result = apDao.findApByCode(ap_code,null);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			ret = "Some errors occured";
			
		}
		if(result!=null && result.size()==0){
			Ap ap = new Ap();
			ap.setEquipment_code(ap_code);
			ap.setMac(ap_mac);
			ap.setState(0);
			ap.setDay_state(0);
			ap.setArea_code("34010026000001");
			ap.setState_time(System.currentTimeMillis()/1000);
			try {
				apDao.addAp(ap);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ret = "Some errors occured";
			}
		}
		
		
		
		
		map.put("success", ret);
		//JSONArray jsonArray = JSONArray.fromObject( result);
		JSONObject jsonObject = JSONObject.fromObject(map);
		
		PrintWriter out=null;
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
		out.close();
		
		heartbeat(request,session,response,apopen);
	}

	@RequestMapping(value = "heartbeat", method = RequestMethod.POST)
	public void heartbeat(HttpServletRequest request,HttpSession session,HttpServletResponse response,
			@RequestBody String heartbeat) {
		HashMap map = new HashMap();
		String ret="succeed";
		System.out.println(heartbeat);
		heartbeat = URLDecoder.decode(heartbeat);
		heartbeat = heartbeat.replace("=", "");
		System.out.println(heartbeat);
		
		
		
		//TestUser user = (TestUser)JSONArray.toBean(testUser,TestUser.class);
		JSONObject newheartbeat = JSONObject.fromObject(heartbeat);
		String ap_code = (String)newheartbeat.get("ap_code");
		String ap_mac = (String)newheartbeat.get("ap_mac");
		
		List<Ap> result=null;
		
		try {
			result = apDao.findApByCode(ap_code,null);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			ret = "Some errors occured";
			
		}
		if(result!=null && result.size()>0){
			Ap ap = new Ap();
			ap.setEquipment_code(ap_code);
			ap.setMac(ap_mac);
			ap.setState(1);
			ap.setDay_state(1);
			ap.setState_time(System.currentTimeMillis()/1000);
			
			try {
				apDao.updateApAllState(ap);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ret = "Some errors occured";
			}
		}
		
		
		
		
		map.put("success", ret);
		//JSONArray jsonArray = JSONArray.fromObject( result);
		JSONObject jsonObject = JSONObject.fromObject(map);
		
		PrintWriter out=null;
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
		out.close();
		
	}

		
	
}
