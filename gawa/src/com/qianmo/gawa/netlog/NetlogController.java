package com.qianmo.gawa.netlog;

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
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.qianmo.gawa.ap.Ap;
import com.qianmo.gawa.ap.ApDao;
import com.qianmo.gawa.login.TestUser;
import com.qianmo.gawa.operlog.OperlogService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qianmo.gawa.util.ByteString;
import com.qianmo.gawa.util.Des;
import com.qianmo.gawa.util.Xml2Java;
import com.qianmo.gawa.util.ZipUtil;

@Controller
@RequestMapping(value = "/netlog")
public class NetlogController {


	@Resource
	private ApDao apDao;
	@Resource
	private NetlogDao netlogDao;
	@Resource
	private OperlogService operlogService;
	
	public final static Map iptablemap = new HashMap() {{    
		put("192.168.18.249","15000,15099");
		put("192.168.18.248","14900,14999");
		put("192.168.18.247","14800,14899");
		put("192.168.18.246","14700,14799");
		put("192.168.18.245","14600,14699");
		put("192.168.18.244","14500,14599");
		put("192.168.18.243","14400,14499");
		put("192.168.18.242","14300,14399");
		put("192.168.18.241","14200,14299");
		put("192.168.18.240","14100,14199");
		put("192.168.18.239","14000,14099");
		put("192.168.18.238","13900,13999");
		put("192.168.18.237","13800,13899");
		put("192.168.18.236","13700,13799");
		put("192.168.18.235","13600,13699");
		put("192.168.18.234","13500,13599");
		put("192.168.18.233","13400,13499");
		put("192.168.18.232","13300,13399");
		put("192.168.18.231","13200,13299");
		put("192.168.18.230","13100,13199");
		put("192.168.18.229","13000,13099");
		put("192.168.18.228","12900,12999");
		put("192.168.18.227","12800,12899");
		put("192.168.18.226","12700,12799");
		put("192.168.18.225","12600,12699");
		put("192.168.18.224","12500,12599");
		put("192.168.18.223","12400,12499");
		put("192.168.18.222","12300,12399");
		put("192.168.18.221","12200,12299");
		put("192.168.18.220","12100,12199");
		put("192.168.18.219","12000,12099");
		put("192.168.18.218","11900,11999");
		put("192.168.18.217","11800,11899");
		put("192.168.18.216","11700,11799");
		put("192.168.18.215","11600,11699");
		put("192.168.18.214","11500,11599");
		put("192.168.18.213","11400,11499");
		put("192.168.18.212","11300,11399");
		put("192.168.18.211","11200,11299");
		put("192.168.18.210","11100,11199");
		put("192.168.18.209","11000,11099");
		put("192.168.18.208","10900,10999");
		put("192.168.18.207","10800,10899");
		put("192.168.18.206","10700,10799");
		put("192.168.18.205","10600,10699");
		put("192.168.18.204","10500,10599");
		put("192.168.18.203","10400,10499");
		put("192.168.18.202","10300,10399");
		put("192.168.18.201","10200,10299");
		put("192.168.18.200","10100,10199");
		put("192.168.18.199","10000,10099");
		put("192.168.18.198","9900,9999");
		put("192.168.18.197","9800,9899");
		put("192.168.18.196","9700,9799");
		put("192.168.18.195","9600,9699");
		put("192.168.18.194","9500,9599");
		put("192.168.18.193","9400,9499");
		put("192.168.18.192","9300,9399");
		put("192.168.18.191","9200,9299");
		put("192.168.18.190","9100,9199");
		put("192.168.18.189","9000,9099");
		put("192.168.18.188","8900,8999");
		put("192.168.18.187","8800,8899");
		put("192.168.18.186","8700,8799");
		put("192.168.18.185","8600,8699");
		put("192.168.18.184","8500,8599");
		put("192.168.18.183","8400,8499");
		put("192.168.18.182","8300,8399");
		put("192.168.18.181","8200,8299");
		put("192.168.18.180","8100,8199");
		put("192.168.18.179","8000,8099");
		put("192.168.18.178","7900,7999");
		put("192.168.18.177","7800,7899");
		put("192.168.18.176","7700,7799");
		put("192.168.18.175","7600,7699");
		put("192.168.18.174","7500,7599");
		put("192.168.18.173","7400,7499");
		put("192.168.18.172","7300,7399");
		put("192.168.18.171","7200,7299");
		put("192.168.18.170","7100,7199");
		put("192.168.18.169","7000,7099");
		put("192.168.18.168","6900,6999");
		put("192.168.18.167","6800,6899");
		put("192.168.18.166","6700,6799");
		put("192.168.18.165","6600,6699");
		put("192.168.18.164","6500,6599");
		put("192.168.18.163","6400,6499");
		put("192.168.18.162","6300,6399");
		put("192.168.18.161","6200,6299");
		put("192.168.18.160","6100,6199");
		put("192.168.18.159","6000,6099");
		put("192.168.18.158","5900,5999");
		put("192.168.18.157","5800,5899");
		put("192.168.18.156","5700,5799");
		put("192.168.18.155","5600,5699");
		put("192.168.18.154","5500,5599");
		put("192.168.18.153","5400,5499");
		put("192.168.18.152","5300,5399");
		put("192.168.18.151","5200,5299");
		put("192.168.18.150","5100,5199");
		put("192.168.18.149","5000,5099");
		put("192.168.18.148","4900,4999");
		put("192.168.18.147","4800,4899");
		put("192.168.18.146","4700,4799");
		put("192.168.18.145","4600,4699");
		put("192.168.18.144","4500,4599");
		put("192.168.18.143","4400,4499");
		put("192.168.18.142","4300,4399");
		put("192.168.18.141","4200,4299");
		put("192.168.18.140","4100,4199");
		put("192.168.18.139","4000,4099");
		put("192.168.18.138","3900,3999");
		put("192.168.18.137","3800,3899");
		put("192.168.18.136","3700,3799");
		put("192.168.18.135","3600,3699");
		put("192.168.18.134","3500,3599");
		put("192.168.18.133","3400,3499");
		put("192.168.18.132","3300,3399");
		put("192.168.18.131","3200,3299");
		put("192.168.18.130","3100,3199");
		put("192.168.18.129","3000,3099");
		put("192.168.18.128","2900,2999");
		put("192.168.18.127","2800,2899");
		put("192.168.18.126","2700,2799");
		put("192.168.18.125","2600,2699");
		put("192.168.18.124","2500,2599");
		put("192.168.18.123","2400,2499");
		put("192.168.18.122","2300,2399");
		put("192.168.18.121","2200,2299");
		put("192.168.18.120","2100,2199");
		put("192.168.18.119","2000,2099");
		put("192.168.18.118","1900,1999");
		put("192.168.18.117","1800,1899");
		put("192.168.18.116","1700,1799");
		put("192.168.18.115","1600,1699");
		put("192.168.18.114","1500,1599");
		put("192.168.18.113","1400,1499");
		put("192.168.18.112","1300,1399");
		put("192.168.18.111","1200,1299");
		put("192.168.18.110","1100,1199");
		put("192.168.18.109","1000,1099");
		put("192.168.18.108","900,999");
		put("192.168.18.107","800,899");
		put("192.168.18.106","700,799");
		put("192.168.18.105","600,699");
		put("192.168.18.104","500,599");
		put("192.168.18.103","400,499");
		put("192.168.18.102","300,399");
		put("192.168.18.101","200,299");
		put("192.168.18.100","100,199");	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	}}; 
	
	

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session,HttpServletResponse response) {

		String username = (String)session.getAttribute("userctx");
		operlogService.addOperlogToDb(username, "浏览上网日志信息");
		return new ModelAndView("netlog/netloglist");
	}
	
	@RequestMapping(value = "getdata", method = RequestMethod.POST)
	//public String logout(HttpSession session) {
	public void getdata(HttpSession session,HttpServletResponse response,
			Integer page,Integer rows,Integer id,
			String code,String date1,String date2) {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		
		if(date1==null){
		    Date dt = new Date();   
		    //Date dt1 = new Date(dt.getTime()+24*3600*1000); 
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
		    date1=sdf.format(dt);
		    date2=sdf.format(dt);
		}
		
		if(page<1) page=1;
		Integer offset = (page-1)*rows;
		List result = null;
		Integer total = null;
		try {
			if(id==null  && code == null && date1==null && date2==null){
				result = netlogDao.findNetlogByPage(offset,rows);
				//total= netlogDao.findNetlogCount();
				Netlog netlog = (Netlog)result.get(0);
				total= (int) (netlog.getLog_id()-13);
			}else if(code!=null && date1==null){
				result = netlogDao.findNetlogByAreacode(code,offset,rows);
				total= netlogDao.findNetlogCountByAreacode(code);
			}else if( date1!=null && date2 !=null){
				result = netlogDao.findNetlogByDate(code,date1,date2,offset,rows);
				total= netlogDao.findNetlogCountByDate(code, date1, date2);
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
	
	///////////////////////zip file
	//do it every minute.
	//@Scheduled(cron = "0 * * * * ?")
	public void tozip() {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		List<Netlog> result = null;
		Integer total = null;
		try {
			
				result = (List<Netlog>)netlogDao.findNetlogByPage(1,10000);
				total= result.size();
				//total= netlogDao.findNetlogCount();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Netlogs netlogs = new Netlogs(result);
		String path = this.getClass().getClassLoader().getResource("").getPath();
		System.out.println(path);
		path = path.replace("WEB-INF/classes/", "");
		String path1 = path + "netlog/";
		Long time = System.currentTimeMillis()/1000;
		Xml2Java.beanToXML(netlogs,path1+"145-330000-"+time.toString()+"-00001-WA-SOURCE_NETLOG_0001-0.xml");

		ZipUtil.ZipMultiFile(path1, path+"145-353030334-330300-330300-netlog-00001.zip");
	}
	
	
	
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public void insertdata(HttpSession session,HttpServletResponse response,
			Netlog netlog) {
		HashMap map = new HashMap();
		try {
			if(netlog != null){
				netlog = netlogDao.addNetlog(netlog);//return null,but insert successfully.	
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
			Netlog netlog,Long id) {
		HashMap map = new HashMap();
		Integer ret = null;
		try {
			if(netlog != null){
				netlog.setLog_id(id);
				ret = netlogDao.updateNetlog(netlog);	
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
			Long netlog_id) {
		HashMap map = new HashMap();
		Integer ret = null;
		try {
			if(netlog_id != null){
				ret = netlogDao.deleteNetlog(netlog_id);	
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

//////////////receive data from 3rd party
	@RequestMapping(value = "netlog", method = RequestMethod.POST)
	public void getNetlog(HttpServletRequest request,HttpSession session,HttpServletResponse response,
			@RequestBody JSONObject netlog) {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		//return "redirect:login.jsp";
		Integer ret = 0;
		ret++;
		Netlog netlog1 = (Netlog)JSONObject.toBean(netlog,Netlog.class);
		try {
			netlogDao.addNetlog(netlog1);
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
	
	@RequestMapping(value = "netlogs", method = RequestMethod.POST)
	public void getNetlogs(HttpServletRequest request,HttpSession session,HttpServletResponse response,
			@RequestBody String netlogs) {
		//session.setAttribute(Constant.USER_SESSION_KEY, null);
		//return "redirect:login.jsp";
		final String finalnetlogs = netlogs;
	//new Thread(){
		//public void run(){
		String mynetlogs = finalnetlogs;
		
		System.out.println(finalnetlogs);
		/*mynetlogs = mynetlogs.replace("=", "");
		try {
			byte[] bytes = ByteString.stringToBytes(mynetlogs);
			mynetlogs = new String(bytes);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}*/
		String newlogs = URLDecoder.decode(mynetlogs);
		newlogs = newlogs.replace("=", "");
		//System.out.println(newlogs);
		String ret = "success";
		
		//TestUser user = (TestUser)JSONArray.toBean(testUser,TestUser.class);
		JSONArray netlogs1 = JSONArray.fromObject(newlogs);
		List<Netlog> netlogs2 = JSONArray.toList(netlogs1,Netlog.class);
		Netlog tmp = netlogs2.get(0);
		dataBeat(tmp.getAp_code(),tmp.getAp_mac());
		for(Netlog log:netlogs2){
			try {
				String sessionid = log.getSession_id();
				sessionid = sessionid.replace("null", "34010026000001");
				log.setSession_id(sessionid);
				log.setAuth_area_code("34010026000001");
				
				log.setIp4_target_port_wan(log.getIp4_port_start_wan());
				Long ip = log.getIp_lan();
				String ipstr = longToIP(ip);
				Integer ip4_port_start_wan=null;
				String tmpport = (String)iptablemap.get(ipstr);
				String tmpport1 = tmpport.split(",")[0];
				String tmpport2 = tmpport.split(",")[1];
				ip4_port_start_wan = Integer.valueOf(tmpport1);
				log.setIp4_port_start_wan(ip4_port_start_wan);
				Integer ip4_port_end_wan=null;
				ip4_port_end_wan = Integer.valueOf(tmpport2);
				log.setIp4_port_end_wan(ip4_port_end_wan);
				
				netlogDao.addNetlog(log);
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
	
	public static void main(String args[]){
		String json="[{\"allap_id\":0,\"ap_code\":\"35303033428F366060961\",\"ap_latitude\":\"0\",\"ap_longitude\":\"0\",\"ap_mac\":\"28-F3-66-06-09-61\",\"area_id\":0,\"auth_area_code\":\"\",\"create_time\":0,\"aabbcc\":0,\"ip4_port_end_wan\":8080,\"ip4_port_start_wan\":8080,\"ip4_target_port_wan\":8080,\"ip4_target_wan\":169874653,\"ip4_wan\":-1220725296,\"ip6_port_end_wan\":8080,\"ip6_port_start_wan\":8080,\"ip6_target_port_wan\":80,\"ip6_target_wan\":\"\",\"ip6_wan\":\"\",\"ip_lan\":3232240257,\"ip_port_lan\":8080,\"protocol_type\":\"1\",\"session_id\":\"611468055750\",\"user_mac\":\"f48b32784655\"}]";
		
		JSONArray netlogs1 = JSONArray.fromObject(json);
		List<Netlog> netlogs2 = JSONArray.toList(netlogs1,Netlog.class);
		for(Netlog log:netlogs2){
			int i=0;
			i=i;
		}
	
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
	
	private String ProtocalTypeToInteger(String type){
		String ret="";
		
		if(type.equals("HTTP")){
			ret = "01";
		}else if(type.equals("WAP")){
			ret = "02";
		}else if(type.equals("SMTP")){
			ret = "03";
		}else if(type.equals("POP3")){
			ret = "04";
		}else if(type.equals("IMAP")){
			ret = "05";
		}else if(type.equals("NNTP")){
			ret = "06";
		}else if(type.equals("FTP")){
			ret = "07";
		}else if(type.equals("SFTP")){
			ret = "08";
		}else if(type.equals("TELNET")){
			ret = "09";
		}else if(type.equals("HTTPS")){
			ret = "10";
		}else if(type.equals("RSTP")){
			ret = "11";
		}else if(type.equals("MMS")){
			ret = "12";
		}else if(type.equals("WEP")){
			ret = "13";
		}else if(type.equals("WPA")){
			ret = "14";
		}else if(type.equals("PPTP")){
			ret = "15";
		}else if(type.equals("L2TP")){
			ret = "16";
		}else if(type.equals("SOCKS")){
			ret = "17";
		}else if(type.equals("Compo")){
			ret = "18";
		}else if(type.equals("Cmsmtp")){
			ret = "19";
		}else if(type.equals("QQ")){
			ret = "31";
		}else if(type.equals("SSDP")){
			ret = "32";
		}else if(type.equals("Thunder")){
			ret = "33";
		}else if(type.equals("MSN")){
			ret = "34";
		}else if(type.equals("IRC")){
			ret = "35";
		}else if(type.equals("DHCP")){
			ret = "36";
		}else if(type.equals("NETBIOS")){
			ret = "37";
		}else if(type.equals("ICMP")){
			ret = "38";
		}else if(type.equals("IGMP")){
			ret = "39";
		}else if(type.equals("TFTP")){
			ret = "40";
		}else if(type.equals("SMB")){
			ret = "41";
		}else if(type.equals("DNS")){
			ret = "42";
		}else {
			ret = "99";
		}
			
			
		
		return ret;
	}

		
	
}
