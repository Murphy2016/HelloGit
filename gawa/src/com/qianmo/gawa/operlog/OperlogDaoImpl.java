package com.qianmo.gawa.operlog;

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
public class OperlogDaoImpl implements OperlogDao{
	@Resource
	private SqlMapClient sqlMapClient;
	
	
	@Override
	public Integer       findSetting() throws SQLException{
		Integer ret=null;
		ret = (Integer)sqlMapClient.queryForObject("findSetting");
		return ret;
	}
	@Override
	public Integer updateSetting(Integer period) throws SQLException{
		Integer ret = null;
		if(period!=null)
			ret = sqlMapClient.update("updateSetting",period);
		return ret;
	}
	
	@Override
	public List<Operlog> findOperlogByPage(Integer offset,Integer rows) throws SQLException {
		Map map = new HashMap();
        map.put("offset", offset);
        map.put("rows", rows);
        
        
		return
		(List<Operlog>) sqlMapClient.queryForList("findOperlogByPage",map);
		
	}
	public Integer findOperlogCount() throws SQLException{
		return
				(Integer) sqlMapClient.queryForObject("findOperlogCount");
	}
	
	public List<Operlog> findOperlogById(Integer id) throws SQLException{
		return
				(List<Operlog>) sqlMapClient.queryForList("findOperlogById",id);
	}
	
	public Operlog addOperlog(Operlog operlog) throws SQLException{
		Operlog ret = null;
		if(operlog != null)
			ret = (Operlog)sqlMapClient.insert("addOperlog",operlog); 
		return ret;
	}
	
	public Integer updateOperlog(Operlog operlog) throws SQLException{
		Integer ret = null;
		if(operlog!=null)
			ret = sqlMapClient.update("updateOperlogById",operlog);
		return ret;
	}
	public Integer deleteOperlog(Integer oper_id) throws SQLException{
		Integer ret = null;
		if(oper_id!=null)
			ret = sqlMapClient.delete("deleteOperlog",oper_id);
		return ret;
	}

	public Integer deleteOperlogByTime(String add_time) throws SQLException{
		Integer ret = null;
		if(add_time!=null){
			ret = sqlMapClient.delete("deleteOperlogByTime",add_time);
		}
		return ret;
	}

}
