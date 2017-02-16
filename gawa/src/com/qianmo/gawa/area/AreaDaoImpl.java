package com.qianmo.gawa.area;

import java.sql.SQLException;
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
public class AreaDaoImpl implements AreaDao{
	@Resource
	private SqlMapClient sqlMapClient;
	
	@Override
	public List<Area> findAreaByPage(Integer offset,Integer rows) throws SQLException {
		Map map = new HashMap();
        map.put("offset", offset);
        map.put("rows", rows);
        
        
		return
		(List<Area>) sqlMapClient.queryForList("findAreaByPage",map);
		
	}
	public Integer findAreaCount() throws SQLException{
		return
				(Integer) sqlMapClient.queryForObject("findAreaCount");
	}
	public Integer findAreaOnlineCount() throws SQLException{
		return
				(Integer) sqlMapClient.queryForObject("findAreaOnlineCount");
	}
	
	
	
	
	public List<Area> findAreaById(Integer id) throws SQLException{
		return
				(List<Area>) sqlMapClient.queryForList("findAreaById",id);
	}
	public List<Area> findAreaByAreacode(String areacode) throws SQLException{
		return
				(List<Area>) sqlMapClient.queryForList("findAreaByAreacode",areacode);
	}
	public Area addArea(Area area) throws SQLException{
		Area ret = null;
		if(area != null)
			ret = (Area)sqlMapClient.insert("addArea",area); 
		return ret;
	}
	
	public Integer updateArea(Area area) throws SQLException{
		Integer ret = null;
		if(area!=null)
			ret = sqlMapClient.update("updateAreaById",area);
		return ret;
	}
	public Integer updateAreaState(Area area) throws SQLException{
		Integer ret = null;
		if(area!=null)
			ret = sqlMapClient.update("updateAreastateById",area);
		return ret;
	}
	public Integer deleteArea(Long area_id) throws SQLException{
		Integer ret = null;
		if(area_id!=null)
			ret = sqlMapClient.delete("deleteArea",area_id);
		return ret;
	}


}
