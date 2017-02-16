package com.qianmo.gawa.netlog;

import java.sql.SQLException;
import java.util.List;





/***
 * 服务端 Dao
 * @author AndLiSoft
 *
 */
public interface NetlogDao {
	public List<Netlog> findNetlogByPage(Integer offset,Integer rows) throws SQLException;
	public Integer findNetlogCount() throws SQLException;
	public List<Netlog> findNetlogById(Integer id) throws SQLException;
	
	public Integer findNetlogCountByAreacode(String  area_code) throws SQLException;
	public List<Netlog> findNetlogByAreacode(String  area_code,Integer offset,Integer rows) throws SQLException;
	public Integer findNetlogCountByDate(String  area_code,String date1,String date2) throws SQLException;
	public List<Netlog> findNetlogByDate(String area_code,String date1,String date2
			,Integer offset,Integer rows) throws SQLException;
	
	
	public Netlog addNetlog(Netlog netlog) throws SQLException;
	public Integer updateNetlog(Netlog netlog) throws SQLException;
	public Integer deleteNetlog(Long netlog) throws SQLException;
	public Integer deleteNetlogByTime(String create_time) throws SQLException;
}

