package com.qianmo.gawa.statistic;

import java.sql.SQLException;
import java.util.List;





/***
 * 服务端 Dao
 * @author AndLiSoft
 *
 */
public interface StatisticDao {
	public List<Statistic> findStatisticAreaByPage(Integer offset,Integer rows) throws SQLException;
	public List<Statistic> findStatisticUserByPage(Integer offset,Integer rows) throws SQLException;
	public List<Statistic> findStatisticApByPage(Integer offset,Integer rows) throws SQLException;
	public List<Statistic> findStatisticRealnameByPage(Integer offset,Integer rows) throws SQLException;
	public Integer findStatisticAreaCount() throws SQLException;
	public Integer findStatisticUserCount() throws SQLException;
	public Integer findStatisticApCount() throws SQLException;
	public Integer findStatisticRealnameCount() throws SQLException;
	public List<Statistic> findStatisticAreaById(Integer id) throws SQLException;
	public List<Statistic> findStatisticUserByDate(String mobileno,String date1,String date2) throws SQLException;
	public List<Statistic> findStatisticAreaByDate(String date1,String date2) throws SQLException;
	public List<Statistic> findStatisticApByDate(String date1,String date2) throws SQLException;
	public List<Statistic> findStatisticRealnameByDate(String date1,String date2) throws SQLException;
	
	public Statistic addStatistic(Statistic statistic) throws SQLException;
	public Statistic addApState(Statistic statistic) throws SQLException;
	public Statistic addAreaState(Statistic statistic) throws SQLException;	
	public Statistic addUserState(Statistic statistic) throws SQLException;	
	public Statistic addRealnameState(Statistic statistic) throws SQLException;
	
	
	public Integer updateStatistic(Statistic statistic) throws SQLException;
	public Integer deleteStatistic(Long statistic_id) throws SQLException;
}

