package com.qianmo.gawa.util;

public class ByteString {
	 public static final String bytesToString(byte[] bs){
		    try{
		      if(bs == null)
		        return null;

		      int len = bs.length;
		      String str = null;
		      for(int i = 0; i<len; i++){
		        int b = bs[i];
		        if(b < 0)
		          b += 256;
		        int h = b >> 4;
		        int l = b & 0xf;
		        char ch = '0';
		        if(h < 10)
		          ch += (char)h;
		        else
		          ch = (char)('A'+h-10);
		        str = StringCombine.combine(str,ch);
		        char cl = '0';
		        if(l < 10)
		          cl += (char)l;
		        else
		          cl = (char)('A'+l-10);
		        str = StringCombine.combine(str,cl);
		      }

		      return str;
		    }catch(Exception exp){}

		    return null;
		  }

		  public static final byte[] stringToBytes(String str){
		    try{
		    	//System.out.println("111111111111111111");
		      if(str == null)
		        return null;
		     // System.out.println("2222222222222222222");
		      str = str.trim();
		      if(str.equals(""))
		        return null;
		      //System.out.println("333333333333333333333");
		      int n = str.length();
		      int len = n/2;
		      byte[] bs = new byte[len];
		     // System.out.println("4444444444444444444444");
		      for(int i = 0; i<len; i++){
		    	 // System.out.println("555555555555555555555");
		        int h = str.charAt(2*i);
		        if(h >='0' && h <= '9')
		          h -= '0';
		        else
		          h -= 'A'-10;
		        int l = str.charAt(2*i+1);
		        if(l >='0' && l <= '9')
		          l -= '0';
		        else
		          l -= 'A'-10;
		        bs[i] = (byte)((h<<4)+l);
		      }
		      //System.out.println("66666666666666666666");
		      return bs;
		    }catch(Exception exp){}
		    //System.out.println("777777777777777777777");
		    return null;
		  }

}
