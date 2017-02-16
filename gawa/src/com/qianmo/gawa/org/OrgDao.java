package com.qianmo.gawa.org;

import java.sql.SQLException;
import java.util.List;





/***
 * 服务端 Dao
 * @author AndLiSoft
 *
 */
public interface OrgDao {
	public Org       findOrgById(Integer id) throws SQLException;
	
	public List<Org> findOrgByPage(Integer offset,Integer rows) throws SQLException;
	public Integer findOrgCount() throws SQLException;
	public List<Org> findOrgBySearch(String search) throws SQLException;
	public Org addOrg(Org Org) throws SQLException;
	public Integer updateOrg(Org Org) throws SQLException;
	
	public Integer deleteOrg(Long Org_id) throws SQLException;
}

