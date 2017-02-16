package com.qianmo.gawa.area;

import java.sql.SQLException;
import java.util.List;





/***
 * 服务端 Dao
 * @author AndLiSoft
 *
 */
public interface AreaDao {
	public List<Area> findAreaByPage(Integer offset,Integer rows) throws SQLException;
	public Integer findAreaCount() throws SQLException;
	public Integer findAreaOnlineCount() throws SQLException;
	
	
	public List<Area> findAreaById(Integer id) throws SQLException;
	public List<Area> findAreaByAreacode(String areacode) throws SQLException;
	public Area addArea(Area area) throws SQLException;
	public Integer updateArea(Area area) throws SQLException;
	public Integer updateAreaState(Area area) throws SQLException;
	public Integer deleteArea(Long area_id) throws SQLException;
}

