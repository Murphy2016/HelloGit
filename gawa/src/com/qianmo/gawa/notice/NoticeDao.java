package com.qianmo.gawa.notice;

import java.sql.SQLException;
import java.util.List;





/***
 * 服务端 Dao
 * @author AndLiSoft
 *
 */
public interface NoticeDao {
	public List<Notice> findNoticeByPage(Integer offset,Integer rows) throws SQLException;
	public Integer findNoticeCount() throws SQLException;
	public List<Notice> findNoticeById(Integer id) throws SQLException;
	public Notice addNotice(Notice notice) throws SQLException;
	public Integer updateNotice(Notice notice) throws SQLException;
	public Integer deleteNotice(Long notice_id) throws SQLException;
}

