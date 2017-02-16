package com.qianmo.gawa.area;

import java.sql.SQLException;
import java.util.List;

public interface AreaService {
	

	
	public List<Area>  findByPage(Integer offset,Integer rows) throws Exception;
	public Area addArea(Area area) throws SQLException;
}
