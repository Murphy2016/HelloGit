package com.qianmo.gawa.statistic;

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
public class StatisticDaoImpl implements StatisticDao{
	@Resource
	private SqlMapClient sqlMapClient;
	
	@Override
	public List<Statistic> findStatisticAreaByPage(Integer offset,Integer rows) throws SQLException {
		Map map = new HashMap();
        map.put("offset", offset);
        map.put("rows", rows);
        
        
		return
		(List<Statistic>) sqlMapClient.queryForList("findStatisticByPage",map);
		
	}
	public List<Statistic> findStatisticUserByPage(Integer offset,Integer rows) throws SQLException {
		Map map = new HashMap();
        map.put("offset", offset);
        map.put("rows", rows);
		return
		(List<Statistic>) sqlMapClient.queryForList("findStatisticUserByPage",map);
		
	}
	public List<Statistic> findStatisticApByPage(Integer offset,Integer rows) throws SQLException {
		Map map = new HashMap();
        map.put("offset", offset);
        map.put("rows", rows);
		return
		(List<Statistic>) sqlMapClient.queryForList("findStatisticApByPage",map);
		
	}
	public List<Statistic> findStatisticRealnameByPage(Integer offset,Integer rows) throws SQLException {
		Map map = new HashMap();
        map.put("offset", offset);
        map.put("rows", rows);
		return
		(List<Statistic>) sqlMapClient.queryForList("findStatisticRealnameByPage",map);
		
	}
	public Integer findStatisticAreaCount() throws SQLException{
		return
				(Integer) sqlMapClient.queryForObject("findStatisticCount");
	}
	public Integer findStatisticUserCount() throws SQLException{
		return
				(Integer) sqlMapClient.queryForObject("findStatisticUserCount");
	}
	public Integer findStatisticApCount() throws SQLException{
		return
				(Integer) sqlMapClient.queryForObject("findStatisticApCount");
	}
	public Integer findStatisticRealnameCount() throws SQLException{
		return
				(Integer) sqlMapClient.queryForObject("findStatisticRealnameCount");
	}
	
	public List<Statistic> findStatisticAreaById(Integer id) throws SQLException{
		return
				(List<Statistic>) sqlMapClient.queryForList("findStatisticById",id);
	}
	public List<Statistic> findStatisticUserByDate(String mobileno,String date1,String date2)
			throws SQLException{
		Map map = new HashMap();
		
		if(mobileno != null){
			map.put("mobileno", mobileno);
		}
		/*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		
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
		Long ldate2 = ddate2.getTime()/1000;*/
		
		
		map.put("date1", date1);
		map.put("date2", date2);
		
		return
				(List<Statistic>) sqlMapClient.queryForList("findStatisticUserByDate",map);
	}
	
	public List<Statistic> findStatisticAreaByDate(String date1,String date2)
			throws SQLException{
		Map map = new HashMap();
		map.put("date1", date1);
		map.put("date2", date2);
		
		return
				(List<Statistic>) sqlMapClient.queryForList("findStatisticAreaByDate",map);
	}
	public List<Statistic> findStatisticApByDate(String date1,String date2)
			throws SQLException{
		Map map = new HashMap();
		map.put("date1", date1);
		map.put("date2", date2);
		
		return
				(List<Statistic>) sqlMapClient.queryForList("findStatisticApByDate",map);
	}
	public List<Statistic> findStatisticRealnameByDate(String date1,String date2)
			throws SQLException{
		Map map = new HashMap();
		map.put("date1", date1);
		map.put("date2", date2);
		
		return
				(List<Statistic>) sqlMapClient.queryForList("findStatisticRealnameByDate",map);
	}
	
	public Statistic addStatistic(Statistic statistic) throws SQLException{
		Statistic ret = null;
		if(statistic != null)
			ret = (Statistic)sqlMapClient.insert("addStatistic",statistic); 
		return ret;
	}
	public Statistic addApState(Statistic statistic) throws SQLException{
		Statistic ret = null;
		if(statistic != null)
			ret = (Statistic)sqlMapClient.insert("addApState",statistic); 
		return ret;
	}
	public Statistic addAreaState(Statistic statistic) throws SQLException{
		Statistic ret = null;
		if(statistic != null)
			ret = (Statistic)sqlMapClient.insert("addAreaState",statistic); 
		return ret;
	}
	public Statistic addUserState(Statistic statistic) throws SQLException
	{
		Statistic ret = null;
		if(statistic != null)
			ret = (Statistic)sqlMapClient.insert("addUserState",statistic); 
		return ret;
	}
	public Statistic addRealnameState(Statistic statistic) throws SQLException{
		Statistic ret = null;
		if(statistic != null)
			ret = (Statistic)sqlMapClient.insert("addRealnameState",statistic); 
		return ret;
	}
	
	
	
	
	
	
	
	public Integer updateStatistic(Statistic statistic) throws SQLException{
		Integer ret = null;
		if(statistic!=null)
			ret = sqlMapClient.update("updateStatisticById",statistic);
		return ret;
	}
	public Integer deleteStatistic(Long statistic_id) throws SQLException{
		Integer ret = null;
		if(statistic_id!=null)
			ret = sqlMapClient.delete("deleteStatistic",statistic_id);
		return ret;
	}


}
