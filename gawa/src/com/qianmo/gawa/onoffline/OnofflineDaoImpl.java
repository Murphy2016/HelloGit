package com.qianmo.gawa.onoffline;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.ibatis.sqlmap.client.SqlMapClient;
/***
 * 服务端 DaoImpl
 * @author AndLiSoft
 *
 */
@Repository
@Transactional
public class OnofflineDaoImpl implements OnofflineDao{
	@Resource
	private SqlMapClient sqlMapClient;
	
	@Override
	public List<Onoffline> findOnofflineByPage(Integer offset,Integer rows) throws SQLException {
		Map Map = new HashMap();
        Map.put("offset", offset);
        Map.put("rows", rows);
        
        
		return
		(List<Onoffline>) sqlMapClient.queryForList("findOnofflineByPage",Map);
		
	}
	public Integer findOnofflineCount() throws SQLException{
		return
				(Integer) sqlMapClient.queryForObject("findOnofflineCount");
	}
	public  List<Map> findUserDayCount(Long start,Long end) throws SQLException{
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		
		return
				(List<Map>) sqlMapClient.queryForList("findUserDayCount",map);
	}
	public Integer findRealnameDayCount(Long start,Long end) throws SQLException{
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		
		return
				(Integer) sqlMapClient.queryForObject("findRealnameDayCount",map);
	}
	
	
	
	public List<Onoffline> findOnofflineById(Integer id) throws SQLException{
		return
				(List<Onoffline>) sqlMapClient.queryForList("findOnofflineById",id);
	}
	
	
	
	
	public Integer findOnofflineCountByIdentification(String identification)
			throws SQLException{
		
		return
				(Integer) sqlMapClient.queryForObject("findOnofflineCountByIdentification",identification);
	}
	public List<Onoffline> findOnofflineByIdentification(String identification,
			Integer offset,Integer rows) throws SQLException{
		Map map = new HashMap();
		map.put("identification", identification);
		map.put("offset", offset);
		map.put("rows", rows);
		return
				(List<Onoffline>) sqlMapClient.queryForList("findOnofflineByIdentification",map);
	}
	
	public Integer findOnofflineCountByDate(String date1,String date2,String identification)
			throws SQLException{
		Map map = new HashMap();
		if(identification != null){
			map.put("identification", identification);
		}
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		
		java.util.Date ddate1=null;
		try {
			ddate1 = sdf.parse(date1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Long ldate1 = ddate1.getTime()/1000;
		java.util.Date ddate2=null;
		try {
			ddate2=sdf.parse(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long ldate2 = ddate2.getTime()/1000+24*3600;
		
		
		map.put("date1", ldate1);
		map.put("date2", ldate2);
		return
				(Integer) sqlMapClient.queryForObject("findOnofflineCountByDate",map);
	}
	public List<Onoffline> findOnofflineByDate(String date1,String date2,
			String identification,Integer offset,Integer rows) throws SQLException{
		Map map = new HashMap();
		if(identification != null){
			map.put("identification", identification);
		}
		map.put("offset", offset);
		map.put("rows", rows);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		
		java.util.Date ddate1=null;
		try {
			ddate1 = sdf.parse(date1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Long ldate1 = ddate1.getTime()/1000;
		java.util.Date ddate2=null;
		try {
			ddate2=sdf.parse(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long ldate2 = ddate2.getTime()/1000+24*3600;
		
		
		map.put("date1", ldate1);
		map.put("date2", ldate2);
		
		
		return
				(List<Onoffline>) sqlMapClient.queryForList("findOnofflineByDate",map);
	}
	
	public Onoffline addOnoffline(Onoffline onoffline) throws SQLException{
		Onoffline ret = null;
		if(onoffline != null)
			ret = (Onoffline)sqlMapClient.insert("addOnoffline",onoffline); 
		return ret;
	}
	
	public Integer updateOnoffline(Onoffline onoffline) throws SQLException{
		Integer ret = null;
		if(onoffline!=null)
			ret = sqlMapClient.update("updateOnofflineById",onoffline);
		return ret;
	}
	public Integer updateOnofflineByUsermac(Onoffline onoffline) throws SQLException{
		Integer ret = null;
		if(onoffline!=null)
			ret = sqlMapClient.update("updateOnofflineByUsermac",onoffline);
		return ret;
	}
	public Integer deleteOnoffline(Long onoffline_id) throws SQLException{
		Integer ret = null;
		if(onoffline_id!=null)
			ret = sqlMapClient.delete("deleteOnoffline",onoffline_id);
		return ret;
	}
	public Integer deleteOnofflineByTime(String auth_name) throws SQLException{
		Integer ret = null;
		if(auth_name!=null){
			ret = sqlMapClient.delete("deleteOnofflineByTime",auth_name);
		}
		return ret;
	}

}
