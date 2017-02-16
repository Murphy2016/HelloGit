package com.qianmo.gawa.onoffline;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

import com.qianmo.gawa.ap.Ap;
import com.qianmo.gawa.ap.ApDao;
import com.qianmo.gawa.netlog.Netlog;
import com.qianmo.gawa.netlog.Netlogs;
import com.qianmo.gawa.operlog.OperlogService;
import com.qianmo.gawa.util.ByteString;
import com.qianmo.gawa.util.Des;
import com.qianmo.gawa.util.Xml2Java;
import com.qianmo.gawa.util.ZipUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping(value = "/onoffline")
public class OnofflineController {


	@Resource
	private ApDao apDao;
	@Resource
	private OnofflineDao onofflineDao;
	@Resource
	private OperlogService operlogService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session,HttpServletResponse response) {

		String username = (String)session.getAttribute("userctx");
		operlogService.addOperlogToDb(username, "浏览终端上下线信息");
		return new ModelAndView("onoffline/onofflinelist");
	}
	
	@RequestMapping(value = "getdata", method = RequestMethod.POST)
	//public String logout(HttpSession session) {
	public void getdata(HttpSession session,HttpServletResponse response,
			Integer page,Integer rows,Integer id,String code,String date1,String date2) {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		if(date1==null){
		    Date dt = new Date(); 
		    //Date dt1 = new Date(dt.getTime()+24*3600*1000); 
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
		    date1=sdf.format(dt);
		    date2=sdf.format(dt);
		}
		
		Integer offset = (page-1)*rows;
		if(offset<0){
			offset=0;
		}
		List result = null;
		Integer total = null;
		try {
			if(id==null && code == null && date1==null && date2==null){
				result = onofflineDao.findOnofflineByPage(offset,rows);
				Onoffline onoffline = (Onoffline)result.get(0);
				//total= onofflineDao.findOnofflineCount();
				total = (int) (onoffline.getOnoff_id()-39);
			}else if(code!=null && date1==null){
				result = onofflineDao.findOnofflineByIdentification(code,offset,rows);
				total= onofflineDao.findOnofflineCountByIdentification(code);
			}
			else if( date1!=null && date2 !=null){
				result = onofflineDao.findOnofflineByDate(date1,date2,code,offset,rows);
				total= onofflineDao.findOnofflineCountByDate(date1, date2, code);
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
		
	}
	
	///////////////////////zip file
	
	public void tozip() {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		List<Onoffline> result = null;
		Integer total = null;
		try {
			
				result = (List<Onoffline>)onofflineDao.findOnofflineByPage(1,10000);
				total= result.size();
				//total= netlogDao.findNetlogCount();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Onofflines onofflinelogs = new Onofflines(result);
		String path = this.getClass().getClassLoader().getResource("").getPath();
		System.out.println(path);
		path = path.replace("WEB-INF/classes/", "");
		String path1 = path + "onoffline/";
		Long time = System.currentTimeMillis()/1000;
		Xml2Java.beanToXML(onofflinelogs,path1+"145-330000-"+time.toString()+"-00001-WA-SOURCE_ONOFFLINELOG_0001-0.xml");

		ZipUtil.ZipMultiFile(path1, path+"145-353030334-330300-330300-onoffline-00001.zip");
	}
	
	
	
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public void insertdata(HttpSession session,HttpServletResponse response,
			Onoffline onoffline) {
		HashMap map = new HashMap();
		try {
			if(onoffline != null){
				onoffline = onofflineDao.addOnoffline(onoffline);//return null,but insert successfully.	
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
			Onoffline onoffline,Long id) {
		HashMap map = new HashMap();
		Integer ret = null;
		try {
			if(onoffline != null){
				onoffline.setOnoff_id(id);
				ret = onofflineDao.updateOnoffline(onoffline);	
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
			Long onoff_id) {
		HashMap map = new HashMap();
		Integer ret = null;
		try {
			if(onoff_id != null){
				ret = onofflineDao.deleteOnoffline(onoff_id);	
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
///////////////////////////receive data from 3rd party
	@RequestMapping(value = "onoffline", method = RequestMethod.POST)
	public void getOnofflinelog(HttpServletRequest request,HttpSession session,HttpServletResponse response,
			@RequestBody JSONObject onoffline) {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		//return "redirect:login.jsp";
		Integer ret = 0;
		ret++;
		Onoffline onoffline1 = (Onoffline)JSONObject.toBean(onoffline,Onoffline.class);
		try {
			onofflineDao.addOnoffline(onoffline1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HashMap map = new HashMap();
		map.put("success", "success");
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
		//return "hello";
	}
	
	@RequestMapping(value = "onofflines", method = RequestMethod.POST)
	public void getonofflines(HttpServletRequest request,HttpSession session,HttpServletResponse response,
			@RequestBody String onofflines) {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		//return "redirect:login.jsp";
		System.out.println(onofflines);
		final String finalonofflines = onofflines;
		//new Thread(){
			//public void run(){
		String myonofflines = finalonofflines;
		
		/*myonofflines = myonofflines.replace("=", "");
		try {
			byte[] bytes = ByteString.stringToBytes(myonofflines);
			myonofflines = new String(bytes);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}*/
		String newonofflines = URLDecoder.decode(myonofflines);
		newonofflines = newonofflines.replace("=", "");
		//System.out.println(newonofflines);
		String ret = "success";
		
		
		//TestUser user = (TestUser)JSONArray.toBean(testUser,TestUser.class);
		JSONArray onofflines1 = JSONArray.fromObject(newonofflines);
		List<Onoffline> onofflines2 = JSONArray.toList(onofflines1,Onoffline.class);
		Onoffline tmp = onofflines2.get(0);
		dataBeat(tmp.getAp_code(),tmp.getAp_mac());
		for(Onoffline onoffline:onofflines2){
			try {
				String sessionid = onoffline.getSession_id();
				sessionid = sessionid.replace("null", "34010026000001");
				onoffline.setSession_id(sessionid);
				onoffline.setAuth_area_code("34010026000001");
				String user_mac = onoffline.getUser_mac();
				user_mac = user_mac.replace(":", "-");
				user_mac = user_mac.toUpperCase();
				onoffline.setUser_mac(user_mac);
				onofflineDao.addOnoffline(onoffline);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				ret = "wrong data format!";
			}
		}
		//};
		//}.start();
		
		HashMap map = new HashMap();
		map.put("success", "success");
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
		//return "hello";
	}
	
	@RequestMapping(value = "offlines", method = RequestMethod.POST)
	public void getofflines(HttpServletRequest request,HttpSession session,HttpServletResponse response,
			@RequestBody String onofflines) {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		//return "redirect:login.jsp";
		System.out.println("----------------------------getofflines------------------------");
		System.out.println(onofflines);
		final String finalonofflines = onofflines;
		//new Thread(){
			//public void run(){
		String myonofflines = finalonofflines;
		
		/*myonofflines = myonofflines.replace("=", "");
		try {
			byte[] bytes = ByteString.stringToBytes(myonofflines);
			myonofflines = new String(bytes);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}*/
		String newonofflines = URLDecoder.decode(myonofflines);
		newonofflines = newonofflines.replace("=", "");
		//System.out.println(newonofflines);
		String ret = "success";
		
		
		//TestUser user = (TestUser)JSONArray.toBean(testUser,TestUser.class);
		JSONArray onofflines1 = JSONArray.fromObject(newonofflines);
		List<HashMap> onofflines2 = JSONArray.toList(onofflines1,HashMap.class);
		//HashMap tmp = onofflines2.get(0);
		//dataBeat(tmp.getAp_code(),tmp.getAp_mac());
		for(HashMap onoffline:onofflines2){
			try {
				Onoffline onoffline11 = new Onoffline();
				String user_mac = (String)onoffline.get("user_mac");
				onoffline11.setUser_mac(user_mac);
				String de_auth_time = (String)onoffline.get("de_auth_time");
				onoffline11.setDe_auth_time(Long.valueOf(de_auth_time));
				onofflineDao.updateOnofflineByUsermac(onoffline11);
				System.out.println("updateOnofflineByUsermac succeeded, ap_mac="+user_mac);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				ret = "wrong data format!";
				System.out.println("updateOnofflineByUsermac failed, wrong data format!");
			}
		}
		//};
		//}.start();
		
		HashMap map = new HashMap();
		map.put("success", "success");
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
		//return "hello";
	}

private void dataBeat(String ap_code,String ap_mac){
		
		List<Ap> result=null;
		
		try {
			result = apDao.findApByCode(ap_code,null);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("dataBeat first failed!!!!");
			
		}
		if(result!=null && result.size()>0){
			Ap ap = new Ap();
			ap.setEquipment_code(ap_code);
			ap.setMac(ap_mac);
			ap.setData_state(1);
			ap.setState(1);
			
			ap.setData_state_time(System.currentTimeMillis()/1000);
			ap.setState_time(System.currentTimeMillis()/1000);
			
			try {
				apDao.updateApDataState(ap);
				apDao.updateApAllState(ap);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("dataBeat second failed!!!!");
			}
		}
	}
	
		
	
}
