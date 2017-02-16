package com.qianmo.gawa.ap;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.qianmo.gawa.ap.ApDao;
import com.qianmo.gawa.area.AreaDao;
import com.qianmo.gawa.netlog.NetlogDao;
import com.qianmo.gawa.onoffline.OnofflineDao;
import com.qianmo.gawa.operlog.OperlogDao;
import com.qianmo.gawa.util.Xml2Java;
import com.qianmo.gawa.util.ZipUtil;



@Component
public class ApServiceImpl implements ApService {
	
	@Autowired
	private ApDao apDao;
	
	public String addMsgToSession(HttpSession session){
		List<Ap> result11=null;
		try {
			result11 = apDao.findApAll();
			//result11 = apDao.findApByState(0);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Integer onlineCnt=0,offlineCnt=0,dataOnlineCnt=0,dataOfflineCnt=0,
				abnormalCnt=0,maintainCnt=0;
		
		for(Ap elem:result11){
			if(elem.getState()==1){
				onlineCnt++;
			}else{
				offlineCnt++;
			}
			if(elem.getData_state()==1){
				dataOnlineCnt++;
			}else{
				dataOfflineCnt++;
			}
			if(elem.getWorking_state()==3){
				abnormalCnt++;
			}else if(elem.getWorking_state()==4){
				maintainCnt++;
			}
		}
		
		String msg;
		if(result11.size()==0){
			msg = "当前没有任何设备！！！！！！！！";
		}else{
			msg = "有"+onlineCnt+"台设备服务在线，有"+offlineCnt+"台设备服务离线，有"+
					+dataOnlineCnt+"台设备数据在线，有"+dataOfflineCnt+
					"台设备数据离线，有"+abnormalCnt+"台设备异常状态，有"
					+maintainCnt+"台设备维修状态。    ";
		}
			
		session.setAttribute("scroll_begin", msg+msg+msg);
		return msg+msg+msg;
	}
	
}
