package com.qianmo.gawa.operlog;

import java.sql.SQLException;
import java.util.List;





/***
 * 服务端 Dao
 * @author AndLiSoft
 *
 */
public interface OperlogDao {
	public Integer       findSetting() throws SQLException;
	public Integer updateSetting(Integer period) throws SQLException;
	public List<Operlog> findOperlogByPage(Integer offset,Integer rows) throws SQLException;
	public Integer findOperlogCount() throws SQLException;
	public List<Operlog> findOperlogById(Integer id) throws SQLException;
	public Operlog addOperlog(Operlog operlog) throws SQLException;
	public Integer updateOperlog(Operlog operlog) throws SQLException;
	public Integer deleteOperlog(Integer oper_id) throws SQLException;
	public Integer deleteOperlogByTime(String add_time) throws SQLException;
}

