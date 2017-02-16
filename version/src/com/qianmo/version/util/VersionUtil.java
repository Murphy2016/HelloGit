package com.qianmo.version.util;

import java.util.GregorianCalendar;

import com.lx.util.StringCombine;
import com.lx.util.StringSpliter;

public class VersionUtil {
	public static int getVersionStrToInt(String version) throws Exception{
	    String[] str = StringSpliter.split(version,".");
	    int n = str.length;
	    int level = 0x10000;
	    int ver = 0;
	    for(int i = 0; i<n; i++){
	      ver += Integer.parseInt(str[i]) * level;
	      level >>= 8;
	    }
	    return ver;
	  }

	  public static String getVersionIntToStr(int version) throws Exception{
	    String ver = null;
	    int count = 0;
	    while(true){
	      int n = version & 0xff;
	      if(ver == null)
	        ver = Integer.toString(n);
	      else
	        ver = StringCombine.combine(new String[]{
	                                  Integer.toString(n),
	                                  ".",
	                                  ver
	                                  });
	      version >>>= 8;
	      count++;
	      if(count >= 3)
	        break;
	    }

	    return ver;
	  }


	  public static String getCurTimeStr(long time){
	          GregorianCalendar gc = new GregorianCalendar();
	          gc.setTimeInMillis(time);
	          int year = gc.get(GregorianCalendar.YEAR);
	          int month = gc.get(GregorianCalendar.MONTH)+1;
	          int day = gc.get(GregorianCalendar.DAY_OF_MONTH);
	          int hour = gc.get(GregorianCalendar.HOUR_OF_DAY);
	          int minute = gc.get(GregorianCalendar.MINUTE);
	          int second = gc.get(GregorianCalendar.SECOND);
	          int mils = gc.get(GregorianCalendar.MILLISECOND);

	          char[] cs = new char[23];
	          for(int off = 3; off>=0; off--){
	            cs[off] = (char)(year % 10 + 0x30);
	            year /= 10;
	          }

	          cs[4] = '-';

	          for(int off = 6; off>4; off--){
	            cs[off] = (char)(month % 10 +0x30);
	            month /= 10;
	          }

	          cs[7] = '-';

	          for(int off = 9; off>7; off--){
	            cs[off] = (char)(day % 10 +0x30);
	            day /= 10;
	          }

	          cs[10] = ' ';

	          for(int off = 12; off>10; off--){
	            cs[off] = (char)(hour % 10 +0x30);
	            hour /= 10;
	          }

	          cs[13] = ':';

	          for(int off = 15; off>13; off--){
	            cs[off] = (char)(minute % 10 +0x30);
	            minute /= 10;
	          }

	          cs[16] = ':';

	          for(int off = 18; off>16; off--){
	            cs[off] = (char)(second % 10 +0x30);
	            second /= 10;
	          }

	          cs[19] = ' ';

	          for(int off = 22; off>19; off--){
	            cs[off] = (char)(mils % 10 +0x30);
	            mils /= 10;
	          }

	          return new String(cs);
	        }
	  
	  public static void main(String[] args){
		  Integer id=197378;
		  String str="1.0.1";
		  try {
			String ret = getVersionIntToStr(id);			
			System.out.println(ret);
			Integer ret1 = getVersionStrToInt(str);
			System.out.println(ret1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
}
