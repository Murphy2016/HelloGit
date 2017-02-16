package com.qianmo.gawa.org;

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
public class OrgDaoImpl implements OrgDao{
	@Resource
	private SqlMapClient sqlMapClient;
	
	@Override
	public Org       findOrgById(Integer id) throws SQLException{
		Org org=null;
		org = (Org)sqlMapClient.queryForObject("findOrgById",id);
		return org;
	}
	
	
	@Override
	public List<Org> findOrgByPage(Integer offset,Integer rows) throws SQLException {
		Map Map = new HashMap();
        Map.put("offset", offset);
        Map.put("rows", rows);
        
        
		return
		(List<Org>) sqlMapClient.queryForList("findOrgByPage",Map);
		
	}
	public Integer findOrgCount() throws SQLException{
		return
				(Integer) sqlMapClient.queryForObject("findOrgCount");
	}
	
	public List<Org> findOrgBySearch(String id) throws SQLException{
		return
				(List<Org>) sqlMapClient.queryForList("findOrgBySearch",id);
	}
	
	public Org addOrg(Org org) throws SQLException{
		Org ret = null;
		if(org != null)
			ret = (Org)sqlMapClient.insert("addOrg",org); 
		return ret;
	}
	
	public Integer updateOrg(Org org) throws SQLException{
		Integer ret = null;
		if(org!=null)
			ret = sqlMapClient.update("updateOrgById",org);
		return ret;
	}
	
	public Integer deleteOrg(Long org_id) throws SQLException{
		Integer ret = null;
		if(org_id!=null)
			ret = sqlMapClient.delete("deleteOrg",org_id);
		return ret;
	}


}
