package com.qianmo.gawa.onoffline;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;





/***
 * 服务端 Dao
 * @author AndLiSoft
 *
 */
public interface OnofflineDao {
	public List<Onoffline> findOnofflineByPage(Integer offset,Integer rows) throws SQLException;
	public Integer findOnofflineCount() throws SQLException;	
	public List<Map> findUserDayCount(Long start,Long end) throws SQLException;	
	public Integer findRealnameDayCount(Long start,Long end) throws SQLException;
	
	public List<Onoffline> findOnofflineById(Integer id) throws SQLException;
	
	public Integer findOnofflineCountByIdentification(String identification) throws SQLException;
	public List<Onoffline> findOnofflineByIdentification(String identification,
			Integer offset,Integer rows) throws SQLException;
	public Integer findOnofflineCountByDate(String date1,String date2,String identification) throws SQLException;
	public List<Onoffline> findOnofflineByDate(String date1,String date2,
			String identification,Integer offset,Integer rows) throws SQLException;
	
	
	public Onoffline addOnoffline(Onoffline onoffline) throws SQLException;
	public Integer updateOnoffline(Onoffline onoffline) throws SQLException;
	public Integer updateOnofflineByUsermac(Onoffline onoffline) throws SQLException;
	public Integer deleteOnoffline(Long onoffline_id) throws SQLException;
	public Integer deleteOnofflineByTime(String auth_name) throws SQLException;	
}

