package com.qianmo.version.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

public class ListUtil {
	public static List getPageList(ArrayList list,Integer page,Integer rows){
		List list1;
		Integer end = (page-1)*rows+rows<=list.size()?(page-1)*rows+rows:list.size();
		list1 = list.subList((page-1)*rows, end);
		return list1;
	}
	public static List getPageList(JSONArray list,Integer page,Integer rows){
		List list1;
		Integer end = (page-1)*rows+rows<=list.size()?(page-1)*rows+rows:list.size();
		list1 = list.subList((page-1)*rows, end);
		return list1;
	}
}
