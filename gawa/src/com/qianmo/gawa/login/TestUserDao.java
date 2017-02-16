package com.qianmo.gawa.login;

import java.sql.SQLException;
import java.util.List;

import com.qianmo.gawa.maintain.Maintain;





/***
 * 服务端 Dao
 * @author AndLiSoft
 *
 */
public interface TestUserDao {
	public List<TestUser> findAll(Integer offset,Integer rows) throws SQLException;
	public Integer findCount() throws SQLException;
	public TestUser addTestUser(TestUser testUser) throws SQLException;


}

