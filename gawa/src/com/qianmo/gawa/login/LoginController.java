package com.qianmo.gawa.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qianmo.gawa.ap.ApDao;
import com.qianmo.gawa.ap.ApService;
import com.qianmo.gawa.operlog.Operlog;
import com.qianmo.gawa.operlog.OperlogDao;
import com.qianmo.gawa.operlog.OperlogService;
import com.qianmo.gawa.util.ExcelException;
import com.qianmo.gawa.util.ExcelUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
//import org.slf4j.Logger; 
//import org.slf4j.LoggerFactory; 
import org.apache.log4j.Logger;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	private static final Logger        logger = Logger.getLogger(LoginController.class);
	@Resource
	private LoginUserService loginUserService;
	
	@Resource
	private TestUserDao testUserDao;
	
	@Resource
	private ApDao apDao;
	
	@Resource
	private OperlogService operlogService;
	
	@Resource
	private ApService apService;
	
	
//	
//	@Resource
//	private SysUserService sysUserService;

	/*@RequestMapping(method = RequestMethod.GET)
	//public String logout(HttpSession session) {
	public void logout(HttpSession session,HttpServletResponse response) {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		//return "redirect:login.jsp";
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
		out.print("hello");
		out.flush();
		out.close();
		//return "hello";
	}*/
	
	@RequestMapping(value = "getdata", method = RequestMethod.POST)
	//public String logout(HttpSession session) {
	public void getdata(HttpSession session,HttpServletResponse response,
			Integer page,Integer rows) {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		//return "redirect:login.jsp";
		Integer offset = (page-1)*rows;
		List result = null;
		Integer total = null;
		try {
			result = testUserDao.findAll(offset,rows);
			total= testUserDao.findCount();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HashMap map = new HashMap();
		map.put("total", total);
		map.put("rows", result);
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
//@ResponseBody add this before return type can return string to browser directly.
	//,produces = "text/plain;charset=UTF-8" tell spring the return type when restful style.
	@RequestMapping(method = RequestMethod.POST)
	public  String login(LoginModel loginModel,String password1, HttpSession session,
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model) throws Exception {

			
			//return "hello";
			logger.info("日志打印!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			//String session11 = request.getSession();
			String sessionid11 = request.getSession().getId();
			request.getSession().setAttribute("userctx", loginModel.getUsername());//can put an object.
			
			HttpSession session1 = request.getSession();
			
			boolean allowLogin = true;
//			String message = MessageConfig.getMessage("I_10002");
			String message ="";
			
			LoginUser user = null;
			loginModel.setPasswd(password1);
			user = this.loginUserService.findByNameAndPassword(loginModel);
			if(user == null){
				allowLogin = false;
				System.out.println("user = null");
			}else{				
				//System.out.println("user = "+ loginModel.getUsername());
				String level = user.getPermission_level();
				request.getSession().setAttribute("userlevel", level);
				
				apService.addMsgToSession(session);
			}
			
			if (allowLogin){
				//return new ModelAndView("index00"); return "redirect:index";
				operlogService.addOperlogToDb(loginModel.getUsername(), "登录成功");
				return "redirect:login/index";
				//return "index";
				

				
			} else {
				operlogService.addOperlogToDb(loginModel.getUsername(), "登录失败");
				return "redirect:../WEB_AUTH/Login.jsp";
			}
			

	}
	
	
	@RequestMapping(value = "xingbo",method = RequestMethod.GET)
	public  String loginxingbo(LoginModel loginModel, HttpSession session,
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model) throws Exception {

			//return "hello";
			logger.info("日志打印!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			//String session11 = request.getSession();
			String sessionid11 = request.getSession().getId();
			request.getSession().setAttribute("userctx", "mine");//can put an object.
			
			HttpSession session1 = request.getSession();
			
			boolean allowLogin = true;
//			String message = MessageConfig.getMessage("I_10002");
			String message ="";
			
			LoginUser user = null;
			
			{				
				//System.out.println("user = "+ loginModel.getUsername());
				//String level = user.getPermission_level();
				request.getSession().setAttribute("userlevel", "1");
				
				apService.addMsgToSession(session);
			}
			
			if (allowLogin){
				//return new ModelAndView("index00"); return "redirect:index";
				return "redirect:../login/index";
				//return "index";
				

				
			} else {
				return "forward:index.jsp";
			}
			

	}
	
	@RequestMapping(value="logout",  method = RequestMethod.GET)
	public void logout(LoginModel loginModel, HttpSession session,
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model) throws Exception {
		
		String username = (String)session.getAttribute("userctx");
		
		operlogService.addOperlogToDb(username, "退出");
		request.getSession().removeAttribute("userctx");
		((HttpServletResponse) response).sendRedirect("/WEB_AUTH/Login.jsp");
		//return "forward:index.jsp";
	}
	
	
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index11(HttpSession session,HttpServletResponse response) {

		return new ModelAndView("indextreetab");
		//return new ModelAndView("redirect:area/index11");
	}
	
	
	@RequestMapping(value = "subindex", method = RequestMethod.GET)
	public ModelAndView index00(HttpSession session,HttpServletResponse response) {
		
		return new ModelAndView("subindex");
		//return new ModelAndView("redirect:area/index00");
		//return "forward:/area/index00";
	}
	@RequestMapping(value = "area", method = RequestMethod.GET)
	public String area(HttpSession session,HttpServletResponse response) {
		int a=0;
		a++;
		a=a+1;
		//return new ModelAndView("aaaff/index00");
		//return new ModelAndView("redirect:area/index00");
		return "redirect:/area/index";
	}
	@RequestMapping(value = "ap", method = RequestMethod.GET)
	public String ap(HttpSession session,HttpServletResponse response) {
		
		//return new ModelAndView("aaaff/index00");
		//return new ModelAndView("redirect:area/index00");
		return "redirect:/ap/index";
	}
	@RequestMapping(value = "apfix", method = RequestMethod.GET)
	public String apfix(HttpSession session,HttpServletResponse response) {
		
		//return new ModelAndView("aaaff/index00");
		//return new ModelAndView("redirect:area/index00");
		return "redirect:/apfix/index";
	}
	
	@RequestMapping(value = "apstate", method = RequestMethod.GET)
	public String apstate(HttpSession session,HttpServletResponse response) {
		
		//return new ModelAndView("aaaff/index00");
		//return new ModelAndView("redirect:area/index00");
		return "redirect:/ap/index1";
	}
	@RequestMapping(value = "maintain", method = RequestMethod.GET)
	public String maintain(HttpSession session,HttpServletResponse response) {
		
		//return new ModelAndView("aaaff/index00");
		//return new ModelAndView("redirect:area/index00");
		return "redirect:/maintain/index";
	}
	@RequestMapping(value = "netlog", method = RequestMethod.GET)
	public String netlog(HttpSession session,HttpServletResponse response) {
		
		//return new ModelAndView("aaaff/index00");
		//return new ModelAndView("redirect:area/index00");
		return "redirect:/netlog/index";
	}
	@RequestMapping(value = "notice", method = RequestMethod.GET)
	public String notice(HttpSession session,HttpServletResponse response) {
		
		//return new ModelAndView("aaaff/index00");
		//return new ModelAndView("redirect:area/index00");
		return "redirect:/notice/index";
	}
	@RequestMapping(value = "onoffline", method = RequestMethod.GET)
	public String onoffline(HttpSession session,HttpServletResponse response) {
		
		//return new ModelAndView("aaaff/index00");
		//return new ModelAndView("redirect:area/index00");
		return "redirect:/onoffline/index";
	}
	@RequestMapping(value = "org", method = RequestMethod.GET)
	public String org(HttpSession session,HttpServletResponse response) {
		
		//return new ModelAndView("aaaff/index00");
		//return new ModelAndView("redirect:area/index00");
		return "redirect:/org/index";
	}
	@RequestMapping(value = "statistic", method = RequestMethod.GET)
	public String statistic(HttpSession session,HttpServletResponse response) {
		
		//return new ModelAndView("aaaff/index00");
		//return new ModelAndView("redirect:area/index00");
		return "redirect:/statistic/index";
	}
	@RequestMapping(value = "statisticarea", method = RequestMethod.GET)
	public String statisticarea(HttpSession session,HttpServletResponse response) {
		
		//return new ModelAndView("aaaff/index00");
		//return new ModelAndView("redirect:area/index00");
		return "redirect:/statisticarea/index";
	}
	@RequestMapping(value = "statisticap", method = RequestMethod.GET)
	public String statisticap(HttpSession session,HttpServletResponse response) {
		
		//return new ModelAndView("aaaff/index00");
		//return new ModelAndView("redirect:area/index00");
		return "redirect:/statisticap/index";
	}
	@RequestMapping(value = "statisticrealname", method = RequestMethod.GET)
	public String statisticrealname(HttpSession session,HttpServletResponse response) {
		
		//return new ModelAndView("aaaff/index00");
		//return new ModelAndView("redirect:area/index00");
		return "redirect:/statisticrealname/index";
	}
	
	@RequestMapping(value = "operlog", method = RequestMethod.GET)
	public String operlog(HttpSession session,HttpServletResponse response) {
		
		//return new ModelAndView("aaaff/index00");
		//return new ModelAndView("redirect:area/index00");
		return "redirect:/operlog/index";
	}
	
	
	
	//exportTable
	@RequestMapping(value = "exportTable", method = RequestMethod.GET)
	//public String logout(HttpSession session) {
	public void exportTable(HttpSession session,HttpServletResponse response,
			Integer page,Integer rows) {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);

		List result = null;
		Integer total = null;
		try {
			result = testUserDao.findAll(0,50);
			total= testUserDao.findCount();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		LinkedHashMap<String, String> fieldMap = getLeadToFiledPublicQuestionBank();
		
		

		try {
			ExcelUtil.listToExcel(result, fieldMap, "用户", response);
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
	    superClassMap.put("firstname", "名字");  
	    superClassMap.put("lastname", "姓氏");  
	    superClassMap.put("phone", "电话");  
	    superClassMap.put("email", "电子邮件");  
	  
	    return superClassMap;  
	} 
	
	/*@RequestMapping(value = "user", method = RequestMethod.POST)
	@POST  
	@Path("/user")  
	@Consumes("application/json")  
	public void addRoom(TestUser testUser)  
	{  
	    System.out.println("test user name is:"+testUser.getFirstname());  
	    //RoomDAO.addRoom(room);  
	}*/
	
	@RequestMapping(value = "user", method = RequestMethod.POST)
	public void getuser(HttpServletRequest request,HttpSession session,HttpServletResponse response,
			@RequestBody JSONObject testUser) {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		//return "redirect:login.jsp";
		Integer ret = 0;
		ret++;
		TestUser user = (TestUser)JSONObject.toBean(testUser,TestUser.class);
		
		try {
			testUserDao.addTestUser(user);
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
	
	@RequestMapping(value = "userlist", method = RequestMethod.POST)
	public void getusers(HttpServletRequest request,HttpSession session,HttpServletResponse response,
			@RequestBody String testUsers) {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		//return "redirect:login.jsp";
		Integer ret = 0;
		ret++;
		//TestUser user = (TestUser)JSONArray.toBean(testUser,TestUser.class);
		JSONArray testUsers1 = JSONArray.fromObject(testUsers);
		List<TestUser> users = JSONArray.toList(testUsers1,TestUser.class);
		for(TestUser user:users){
			try {
				testUserDao.addTestUser(user);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
	
	@RequestMapping(value = "maptable", method = RequestMethod.POST)
	//public String logout(HttpSession session) {
	public void getMapdata(HttpSession session,HttpServletResponse response
			) {

		HashMap map = new HashMap();
		map.put("success", "success");
		Double data[] = {-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0};
		map.put("data", data);
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
	
	 public static String longToIP(long longIp) {  
	        StringBuffer sb = new StringBuffer("");  
	        // 直接右移24位  
	        sb.append(String.valueOf((longIp >>> 24)));  
	        sb.append(".");  
	        // 将高8位置0，然后右移16位  
	        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));  
	        sb.append(".");  
	        // 将高16位置0，然后右移8位  
	        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));  
	        sb.append(".");  
	        // 将高24位置0  
	        sb.append(String.valueOf((longIp & 0x000000FF)));  
	        return sb.toString();  
	    }  
	 public static void main(String[] args){
		// String ret = longToIP(3657696260L);
		 String aa="1111,222";
		 String bb=aa.split(",")[1];
		 System.out.println(bb);
	 }
}
