package com.qianmo.gawa.netlog;

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
public class NetlogDaoImpl implements NetlogDao{
	@Resource
	private SqlMapClient sqlMapClient;
	
	@Override
	public List<Netlog> findNetlogByPage(Integer offset,Integer rows) throws SQLException {
		Map Map = new HashMap();
        Map.put("offset", offset);
        Map.put("rows", rows);
        
        
		return
		(List<Netlog>) sqlMapClient.queryForList("findNetlogByPage",Map);
		
	}
	public Integer findNetlogCount() throws SQLException{
		return
				(Integer) sqlMapClient.queryForObject("findNetlogCount");
	}
	
	public List<Netlog> findNetlogById(Integer id) throws SQLException{
		return
				(List<Netlog>) sqlMapClient.queryForList("findNetlogById",id);
	}
	
	
	
	public Integer findNetlogCountByAreacode(String  area_code) throws SQLException{
		return
				(Integer) sqlMapClient.queryForObject("findNetlogCountByAreacode",area_code);
	}
	public List<Netlog> findNetlogByAreacode(String  area_code,Integer offset,Integer rows) throws SQLException{
		Map map = new HashMap();
		map.put("area_code", area_code);
		map.put("offset", offset);
		map.put("rows", rows);
		return
				(List<Netlog>) sqlMapClient.queryForList("findNetlogByAreacode",map);
	}
	public Integer findNetlogCountByDate(String  area_code,String date1,
			String date2) throws SQLException{
		Map map = new HashMap();
		if(area_code != null){
			map.put("area_code", area_code);
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
				(Integer) sqlMapClient.queryForObject("findNetlogCountByDate",map);
	}
	public List<Netlog> findNetlogByDate(String area_code,String date1,
			String date2,Integer offset,Integer rows)
			throws SQLException{
		Map map = new HashMap();
		
		if(area_code != null){
			map.put("area_code", area_code);
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
		map.put("offset", offset);
		map.put("rows", rows);
		
		return
				(List<Netlog>) sqlMapClient.queryForList("findNetlogByDate",map);
	}
	
	public Netlog addNetlog(Netlog netlog) throws SQLException{
		Netlog ret = null;
		if(netlog != null)
			ret = (Netlog)sqlMapClient.insert("addNetlog",netlog); 
		return ret;
	}
	
	public Integer updateNetlog(Netlog netlog) throws SQLException{
		Integer ret = null;
		if(netlog!=null)
			ret = sqlMapClient.update("updateNetlogById",netlog);
		return ret;
	}
	public Integer deleteNetlog(Long netlog_id) throws SQLException{
		Integer ret = null;
		if(netlog_id!=null)
			ret = sqlMapClient.delete("deleteNetlog",netlog_id);
		return ret;
	}
	public Integer deleteNetlogByTime(String create_time) throws SQLException{
		Integer ret = null;
		if(create_time!=null){
			ret = sqlMapClient.delete("deleteNetlogByTime",create_time);
		}
		return ret;
	}

}
