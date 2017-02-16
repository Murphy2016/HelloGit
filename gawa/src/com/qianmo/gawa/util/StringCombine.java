package com.qianmo.gawa.util;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class StringCombine {

  public static final String combine(String[] strList){
    int n = strList == null?0:strList.length;
    if(n <= 0)
      return null;

    byte[][] bsList = new byte[n][];
    int len = 0;
    int count = 0;
    for(int i = 0; i<n; i++){
      if(strList[i] == null)
        continue;

      byte[] bs = strList[i].getBytes();
      if(bs != null){
        bsList[count] = bs;
        count++;
        len += bs.length;
      }
    }

    if(len <= 0)
      return null;

    byte[] rs = new byte[len];
    int off = 0;
    for(int i = 0; i<count; i++){
      int subLen = bsList[i].length;
      System.arraycopy(bsList[i],0,rs,off,subLen);
      off+= subLen;
    }

    return new String(rs);
  }

  public static final String combine(String str1,String str2){
    if(str1 == null)
      return str2;
    if(str2 == null)
      return str1;
    byte[] bs1 = str1.getBytes();
    byte[] bs2 = str2.getBytes();
    int len1 = bs1.length;
    int len2 = bs2.length;
    byte[] rs = new byte[len1+len2];
    System.arraycopy(bs1,0,rs,0,len1);
    System.arraycopy(bs2,0,rs,len1,len2);
    return new String(rs);
  }

  public static final String combine(String str1,char c){
    String str = new String(new char[]{c});
    return combine(str1,str);
  }

  public static final String combine(String str1,int v){
    return combine(str1,Integer.toString(v));
  }

}