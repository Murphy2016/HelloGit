package com.qianmo.gawa.login;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.ibatis.sqlmap.client.SqlMapClient;
import com.qianmo.gawa.maintain.Maintain;
/***
 * 服务端 DaoImpl
 * @author AndLiSoft
 *
 */
@Repository
@Transactional
public class TestUserDaoImpl implements TestUserDao{
	@Resource
	private SqlMapClient sqlMapClient;
	
	@Override
	public List<TestUser> findAll(Integer offset,Integer rows) throws SQLException {
		Map map = new HashMap();
        map.put("offset", offset);
        map.put("rows", rows);
        
        
		return
		(List<TestUser>) sqlMapClient.queryForList("findAll",map);
		
	}
	public Integer findCount() {
		Integer result = 0;
		try {
			
					result = (Integer) sqlMapClient.queryForObject("findCount");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	public TestUser addTestUser(TestUser testUser) throws SQLException{
		TestUser ret = null;
		if(testUser != null)
			ret = (TestUser)sqlMapClient.insert("addTestUser",testUser); 
		return ret;
	}
	


}
