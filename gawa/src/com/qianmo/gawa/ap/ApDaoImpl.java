package com.qianmo.gawa.ap;

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
public class ApDaoImpl implements ApDao{
	@Resource
	private SqlMapClient sqlMapClient;
	
	@Override
	public List<Ap> findApByPage(Integer offset,Integer rows) throws SQLException {
		Map map = new HashMap();
        map.put("offset", offset);
        map.put("rows", rows);
        
        
		return
		(List<Ap>) sqlMapClient.queryForList("findApByPage",map);
		
	}
	@Override
	public List<Ap> findApAll() throws SQLException {
        
        
		return
		(List<Ap>) sqlMapClient.queryForList("findApAll");
		
	}
	public Integer findApCount() throws SQLException{
		return
				(Integer) sqlMapClient.queryForObject("findApCount");
	}
	public Integer findApOnlineCount() throws SQLException{
		return
				(Integer) sqlMapClient.queryForObject("findApOnlineCount");
	}
	
	public List<Ap> findApById(Integer id) throws SQLException{
		return
				(List<Ap>) sqlMapClient.queryForList("findApById",id);
	}
	public List<Ap> findApByCode(String code,Integer state) throws SQLException{
		Map map = new HashMap();
		if(state != null){
			map.put("state", state);
			
		}
		map.put("code", code);
		
		
		return
				(List<Ap>) sqlMapClient.queryForList("findApByCode",map);
	}
	public List<Ap> findApByState(Integer state) throws SQLException{
		return
				(List<Ap>) sqlMapClient.queryForList("findApByState",state);
	}
	
	public Ap addAp(Ap Ap) throws SQLException{
		Ap ret = null;
		if(Ap != null)
			ret = (Ap)sqlMapClient.insert("addAp",Ap); 
		return ret;
	}
	
	public Integer updateAp(Ap Ap) throws SQLException{
		Integer ret = null;
		if(Ap!=null)
			ret = sqlMapClient.update("updateApById",Ap);
		return ret;
	}
	
	public Integer updateApStateById(Ap Ap) throws SQLException{
		Integer ret = null;
		if(Ap!=null)
			ret = sqlMapClient.update("updateApStateById",Ap);
		return ret;
	}
	
	public Integer updateApAllState(Ap Ap) throws SQLException{
		Integer ret = null;
		if(Ap!=null)
			ret = sqlMapClient.update("updateApAllStateByCode",Ap);
		return ret;
	}
	public Integer updateApState(Ap Ap) throws SQLException{
		Integer ret = null;
		if(Ap!=null)
			ret = sqlMapClient.update("updateApStateByCode",Ap);
		return ret;
	}
	public Integer updateApDataState(Ap Ap) throws SQLException{
		Integer ret = null;
		if(Ap!=null)
			ret = sqlMapClient.update("updateApDataStateByCode",Ap);
		return ret;
	}
	
	public Integer updateApStateByPeriod(Long cur_time, Integer period) throws SQLException{
		HashMap map = new HashMap();
		map.put("cur_time", cur_time);
		map.put("period", period);
		
		Integer ret = null;
		
		ret = sqlMapClient.update("updateApStateByPeriod",map);
		return ret;
	}
	public Integer updateApDataStateByPeriod(Long cur_time, Integer period) throws SQLException{
		HashMap map = new HashMap();
		map.put("cur_time", cur_time);
		map.put("period", period);
		
		Integer ret = null;
		
		ret = sqlMapClient.update("updateApDataStateByPeriod",map);
		return ret;
	}

	
	
	
	public Integer deleteAp(Long allap_id) throws SQLException{
		Integer ret = null;
		if(allap_id!=null)
			ret = sqlMapClient.delete("deleteAp",allap_id);
		return ret;
	}


}
