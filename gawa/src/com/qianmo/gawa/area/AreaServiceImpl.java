package com.qianmo.gawa.area;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaDao areaDao;

	/**
	 * 
	 */
	@Override
	public List<Area>  findByPage(Integer offset,Integer rows) throws Exception{
		return (List<Area>)this.areaDao.findAreaByPage(offset,rows);
	}
	@Override
	public Area addArea(Area area) throws SQLException{
		area = (Area)this.areaDao.addArea(area);
		
		return area;
	}

}
