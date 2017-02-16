package com.qianmo.gawa.util;

import java.net.URL;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;
public class JavaHttpsExample {
	public static void main(String[] args)
			  throws Exception
			  {
			    String httpsURL = "https://101.201.53.158:8443/gawa/login/xingbo";
			    URL myurl = new URL(httpsURL);
			    HttpsURLConnection con = (HttpsURLConnection)myurl.openConnection();
			    InputStream ins = con.getInputStream();
			    InputStreamReader isr = new InputStreamReader(ins);
			    BufferedReader in = new BufferedReader(isr);

			    String inputLine;

			    while ((inputLine = in.readLine()) != null)
			    {
			      System.out.println(inputLine);
			    }

			    in.close();
			  }
}
