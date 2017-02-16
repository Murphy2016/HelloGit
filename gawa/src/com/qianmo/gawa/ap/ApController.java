package com.qianmo.gawa.ap;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.lx.util.Log;
import com.qianmo.gawa.area.AreaDao;
import com.qianmo.gawa.onoffline.Onoffline;
import com.qianmo.gawa.operlog.Operlog;
import com.qianmo.gawa.operlog.OperlogDao;
import com.qianmo.gawa.operlog.OperlogService;
import com.qianmo.gawa.util.ByteString;
import com.qianmo.gawa.util.Des;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping(value = "/ap")
public class ApController  {
	
	
	
	
	
	
	
	

	private static Timer timer = null;
	
	@Resource
	private ApDao apDao;
	
	@Resource
	private AreaDao areaDao;
	@Resource
	private OperlogDao operlogDao;
	
	@Resource
	private ApService apService;
	@Resource
	private OperlogService operlogService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session,HttpServletResponse response) {
		String username = (String)session.getAttribute("userctx");
		operlogService.addOperlogToDb(username, "浏览AP设备信息页面");
		return new ModelAndView("ap/aplist");
	}
	@RequestMapping(value = "index1", method = RequestMethod.GET)
	public ModelAndView index1(HttpSession session,HttpServletResponse response) {
		String username = (String)session.getAttribute("userctx");
		operlogService.addOperlogToDb(username, "浏览AP设备状态页面");
		apService.addMsgToSession(session);
			
		
		return new ModelAndView("ap/apstatelist");
	}
	
	@RequestMapping(value = "getdata", method = RequestMethod.POST)
	//public String logout(HttpSession session) {
	public void getdata(HttpSession session,HttpServletResponse response,
			Integer page,Integer rows,Integer id,String code,Integer state) {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		Integer offset = (page-1)*rows;
		if(offset<0){
			offset=0;
		}
		List result = null;
		Integer total = null;
		
		apService.addMsgToSession(session);
		try {
			if(code==null && state == null){
				result = apDao.findApByPage(offset,rows);
				total= apDao.findApCount();
			}else if(code !=null){
				result = apDao.findApByCode(code,state);
				total= result.size();
				Integer end = offset+rows<=total?offset+rows:total;
				if(offset>end) offset = end - rows;
				result = result.subList(offset, end);
				
			}else{
				result = apDao.findApByState(state);
				total= result.size();
				Integer end = offset+rows<=total?offset+rows:total;
				if(offset>end) offset = end - rows;
				result = result.subList(offset, end);
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
	
	@RequestMapping(value = "getmsg", method = RequestMethod.POST)
	//public String logout(HttpSession session) {
	public void getmsg(HttpSession session,HttpServletResponse response
			) {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		
		String msg = apService.addMsgToSession(session);
		
		HashMap map = new HashMap();
		map.put("msg", msg);
		
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
	
	
	//to get area_code and area_name
		@RequestMapping(value = "getdata01", method = RequestMethod.POST)
		//public String logout(HttpSession session) {
		public void getdata01(HttpSession session,HttpServletResponse response,
				Integer page,Integer rows,String search,String date) {
			//session.setAttribute(Constant.USER_SESSION_KEY, null);
			//Integer offset = (page-1)*rows;
			List result = null;
			Integer total = null;
			try {
				
					result = areaDao.findAreaByPage(0,10000);
					total= areaDao.findAreaCount();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			HashMap map = new HashMap();
			//map.put("total", total);
			map.put("success", "success");
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
				
				String username = (String)session.getAttribute("userctx");
				
				operlogService.addOperlogToDb(username, "添加AP："+ap.toString());
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
				
				String username = (String)session.getAttribute("userctx");
				
				operlogService.addOperlogToDb(username, "修改AP："+ap.toString());
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
	@RequestMapping(value = "update01", method = RequestMethod.POST)
	public void updatedata01(HttpSession session,HttpServletResponse response,
			Ap ap,Long id) {
		HashMap map = new HashMap();
		Integer ret = null;
		try {
			if(ap != null){
				ap.setAllap_id(id);
				ret = apDao.updateApStateById(ap);	
				map.put("ret", ret);
				
				String username = (String)session.getAttribute("userctx");
				
				operlogService.addOperlogToDb(username, "修改AP："+ap.toString());
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
				Ap ap = apDao.findApById(Integer.valueOf(ap_id.toString())).get(0);
				ret = apDao.deleteAp(ap_id);	
				map.put("success", "success");
				String username = (String)session.getAttribute("userctx");
				
				operlogService.addOperlogToDb(username, "删除AP："+ap.toString());
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
		String ret="succeed";
		/*apopen = apopen.replace("=", "");
		System.out.println("apopen from device:"+apopen);
		try {
		byte[] bytes = ByteString.stringToBytes(apopen);
		System.out.println("after ByteString.stringToBytes");
		
			apopen = new String(bytes,"UTF-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			System.out.println(e2.getMessage());
		}*/
		System.out.println("apopen from device:"+apopen);
		
		String newapopen = URLDecoder.decode(apopen);
		newapopen = newapopen.replace("=", "");
		//System.out.println(newapopen);
		
		
		
		//TestUser user = (TestUser)JSONArray.toBean(testUser,TestUser.class);
		JSONObject newapopen1 = JSONObject.fromObject(newapopen);
		String ap_code = (String)newapopen1.get("ap_code");
		String ap_mac = (String)newapopen1.get("ap_mac");
		String area_code="34010026000001";
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
			//ap.setArea_code("34010026000001");
			ap.setState_time(System.currentTimeMillis()/1000);
			try {
				apDao.addAp(ap);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ret = "Some errors occured";
			}
		}else if(result!=null && result.size()>0){
			Ap ap = result.get(0);
			if(ap!=null && ap.getArea_code()!=null && !ap.getArea_code().isEmpty()){
				area_code = ap.getArea_code();
			}
		}
		
		
		Map map = new HashMap();
		
		map.put("success", ret);
		map.put("area_code", area_code);
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
		/*heartbeat = heartbeat.replace("=", "");
		try {
			byte[] bytes = ByteString.stringToBytes(heartbeat);
			heartbeat = new String(bytes);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}*/
		heartbeat = URLDecoder.decode(heartbeat);
		heartbeat = heartbeat.replace("=", "");
		//System.out.println(heartbeat);
		
		
		
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
	
	@RequestMapping(value = "apopen1", method = RequestMethod.POST)
	public void apopen1(HttpServletRequest request,HttpSession session,HttpServletResponse response,
			@RequestBody String apopen) {
		String ret="succeed";
		String newapopen = URLDecoder.decode(apopen);
		apopen = apopen.replace("=", "");
		System.out.println("apopen from device:"+apopen);
		try {
		byte[] bytes = ByteString.stringToBytes(apopen);
		System.out.println("after ByteString.stringToBytes");
		
			apopen = new String(bytes,"UTF-8");
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			System.out.println(e2.getMessage());
		}
		System.out.println("apopen from device:"+apopen);
		
		try {
			response.getOutputStream().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*String newapopen = URLDecoder.decode(apopen);
		newapopen = newapopen.replace("=", "");
		//System.out.println(newapopen);
		
		
		
		//TestUser user = (TestUser)JSONArray.toBean(testUser,TestUser.class);
		JSONObject newapopen1 = JSONObject.fromObject(newapopen);
		String ap_code = (String)newapopen1.get("ap_code");
		String ap_mac = (String)newapopen1.get("ap_mac");
		String area_code="34010026000001";
		
		System.out.println("apopen1,JSONObject parsing succeed!!!");
		
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
			//ap.setArea_code("34010026000001");
			ap.setState_time(System.currentTimeMillis()/1000);
			try {
				apDao.addAp(ap);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ret = "Some errors occured";
			}
		}else if(result!=null && result.size()>0){
			Ap ap = result.get(0);
			if(ap!=null && ap.getArea_code()!=null && !ap.getArea_code().isEmpty()){
				area_code = ap.getArea_code();
			}
		}
		
		
		Map map = new HashMap();
		
		map.put("success", ret);
		map.put("area_code", area_code);
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
		out.close();*/
		
		//heartbeat(request,session,response,apopen);
	}

		
	
}
