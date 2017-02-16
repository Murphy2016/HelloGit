package com.qianmo.gawa.maintain;

import java.sql.SQLException;
import java.util.List;





/***
 * 服务端 Dao
 * @author AndLiSoft
 *
 */
public interface MaintainDao {
	public List<Maintain> findMaintainByPage(Integer offset,Integer rows) throws SQLException;
	public Integer findMaintainCount() throws SQLException;
	public List<Maintain> findMaintainById(Integer id) throws SQLException;
	public List<Maintain> findMaintainByCode(String code) throws SQLException;
	public List<Maintain> findMaintainByDate(String date) throws SQLException;
	public Maintain addMaintain(Maintain maintain) throws SQLException;
	public Integer updateMaintain(Maintain maintain) throws SQLException;
	public Integer deleteMaintain(Long maintain_id) throws SQLException;
}

