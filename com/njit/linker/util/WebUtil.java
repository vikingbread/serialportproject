package com.njit.linker.util;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Administrator
 * webservie �����ṩ��
 *
 */
public class WebUtil {
	public boolean isURLValid(String URLString){
		HttpURLConnection http = null;
		boolean isValid = false;
		try {
			http = getHttpURLConnection(URLString);
			if(http!=null){
				http.disconnect();
				isValid = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isValid;
	}
	public HttpURLConnection getHttpURLConnection(String urlString) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
	
		http.setRequestMethod("GET");
		http.setConnectTimeout(2000);
		http.setInstanceFollowRedirects(true);
		http.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		http.setDefaultUseCaches(false);
		http.setDoOutput(true);
		try{
			http.connect();
		}catch(ConnectException e){
			System.out.println("�ڳ��Ի������ʱ�����쳣��");
			return null;
		}
		return http;
	}
}
