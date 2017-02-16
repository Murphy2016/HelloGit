package com.qianmo.gawa.notice;

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
public class NoticeDaoImpl implements NoticeDao{
	@Resource
	private SqlMapClient sqlMapClient;
	
	@Override
	public List<Notice> findNoticeByPage(Integer offset,Integer rows) throws SQLException {
		Map map = new HashMap();
        map.put("offset", offset);
        map.put("rows", rows);
        
        
		return
		(List<Notice>) sqlMapClient.queryForList("findNoticeByPage",map);
		
	}
	public Integer findNoticeCount() throws SQLException{
		return
				(Integer) sqlMapClient.queryForObject("findNoticeCount");
	}
	
	public List<Notice> findNoticeById(Integer id) throws SQLException{
		return
				(List<Notice>) sqlMapClient.queryForList("findNoticeById",id);
	}
	
	public Notice addNotice(Notice notice) throws SQLException{
		Notice ret = null;
		if(notice != null)
			ret = (Notice)sqlMapClient.insert("addNotice",notice); 
		return ret;
	}
	
	public Integer updateNotice(Notice notice) throws SQLException{
		Integer ret = null;
		if(notice!=null)
			ret = sqlMapClient.update("updateNoticeById",notice);
		return ret;
	}
	public Integer deleteNotice(Long notice_id) throws SQLException{
		Integer ret = null;
		if(notice_id!=null)
			ret = sqlMapClient.delete("deleteNotice",notice_id);
		return ret;
	}


}
