package com.qianmo.gawa.ap;

import java.sql.SQLException;
import java.util.List;





/***
 * 服务端 Dao
 * @author AndLiSoft
 *
 */
public interface ApDao {
	public List<Ap> findApByPage(Integer offset,Integer rows) throws SQLException;
	public List<Ap> findApAll() throws SQLException;
	public Integer findApCount() throws SQLException;
	public Integer findApOnlineCount() throws SQLException;
	public List<Ap> findApById(Integer id) throws SQLException;
	public List<Ap> findApByCode(String code,Integer state) throws SQLException;
	public List<Ap> findApByState(Integer state) throws SQLException;
	public Ap addAp(Ap Ap) throws SQLException;
	public Integer updateAp(Ap Ap) throws SQLException;
	public Integer updateApStateById(Ap Ap) throws SQLException;
	public Integer updateApAllState(Ap Ap) throws SQLException;
	public Integer updateApState(Ap Ap) throws SQLException;
	public Integer updateApDataState(Ap Ap) throws SQLException;
	
	public Integer updateApStateByPeriod(Long cur_time, Integer period) throws SQLException;
	public Integer updateApDataStateByPeriod(Long cur_time, Integer period) throws SQLException;
	
	public Integer deleteAp(Long allap_id) throws SQLException;
}

