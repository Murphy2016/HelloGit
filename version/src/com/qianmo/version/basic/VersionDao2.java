package com.qianmo.version.basic;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface VersionDao2 {
	public Integer findWifiDeviceCount() throws SQLException;
	
	public List<Version> findAllWifiDevice() throws SQLException;
	
	
}
