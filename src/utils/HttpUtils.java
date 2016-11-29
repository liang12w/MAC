package utils;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/** 
* @author  Louis Liu 
* @date Create Date：Nov 28, 2016 7:28:23 PM 
**/
public class HttpUtils {
	public  static String getHttpResult(String urlget){
		   String result="";
		   try {
		     URL url = new URL(urlget);
		        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		        httpURLConnection.setDoOutput(true);
		        httpURLConnection.setDoInput(true);
		        httpURLConnection.setConnectTimeout(10000);
		       httpURLConnection.setRequestMethod("GET");
		       httpURLConnection.connect();  
		        InputStreamReader  bis = new InputStreamReader(httpURLConnection.getInputStream(),"utf-8");
		        int c = 0;
		        while((c = bis.read()) != -1){        
		         result=result+(char)c;   
		        }
		   }catch (Exception e) {
		    //e.printStackTrace(); 
		    System.out.println(urlget + "  HTTP通信失败！");
		    result = "http error";
		  }
		   
		    return result;
		  }
}
