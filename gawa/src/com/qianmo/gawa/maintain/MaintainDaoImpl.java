package com.qianmo.gawa.maintain;

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
public class MaintainDaoImpl implements MaintainDao{
	@Resource
	private SqlMapClient sqlMapClient;
	
	@Override
	public List<Maintain> findMaintainByPage(Integer offset,Integer rows) throws SQLException {
		Map map = new HashMap();
        map.put("offset", offset);
        map.put("rows", rows);
        
        
		return
		(List<Maintain>) sqlMapClient.queryForList("findMaintainByPage",map);
		
	}
	public Integer findMaintainCount() throws SQLException{
		return
			(Integer) sqlMapClient.queryForObject("findMaintainCount");
	}
	
	public List<Maintain> findMaintainById(Integer id) throws SQLException{
		return
			(List<Maintain>) sqlMapClient.queryForList("findMaintainById",id);
	}
	public List<Maintain> findMaintainByCode(String code) throws SQLException{
		return 
			(List<Maintain>) sqlMapClient.queryForList("findMaintainByCode",code);
	}
	public List<Maintain> findMaintainByDate(String date) throws SQLException{
		return 
			(List<Maintain>) sqlMapClient.queryForList("findMaintainByDate",date);
	}
	
	public Maintain addMaintain(Maintain maintain) throws SQLException{
		Maintain ret = null;
		if(maintain != null)
			ret = (Maintain)sqlMapClient.insert("addMaintain",maintain); 
		return ret;
	}
	
	public Integer updateMaintain(Maintain maintain) throws SQLException{
		Integer ret = null;
		if(maintain!=null)
			ret = sqlMapClient.update("updateMaintainById",maintain);
		return ret;
	}
	public Integer deleteMaintain(Long maintain_id) throws SQLException{
		Integer ret = null;
		if(maintain_id!=null)
			ret = sqlMapClient.delete("deleteMaintain",maintain_id);
		return ret;
	}


}
