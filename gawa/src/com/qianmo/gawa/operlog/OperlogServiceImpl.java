package com.qianmo.gawa.operlog;

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
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qianmo.gawa.ap.ApDao;
import com.qianmo.gawa.area.AreaDao;
import com.qianmo.gawa.netlog.NetlogDao;
import com.qianmo.gawa.onoffline.OnofflineDao;
import com.qianmo.gawa.operlog.OperlogDao;
import com.qianmo.gawa.util.DesUtil;
import com.qianmo.gawa.util.Xml2Java;
import com.qianmo.gawa.util.ZipUtil;



@Component
public class OperlogServiceImpl implements OperlogService {
	
	@Autowired
	private OperlogDao operlogDao;
	
	@Override
	public void addOperlogToDb(String username, String operation){
		Operlog operlog = new Operlog();
		operlog.setUsername(username);
		String tmp = DesUtil.encrypt(operation);
		operlog.setOperation(tmp);
		try {
			operlogDao.addOperlog(operlog);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
