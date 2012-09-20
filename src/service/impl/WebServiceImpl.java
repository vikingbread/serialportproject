package com.njit.linker.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;


import com.njit.linker.service.WebService;
import com.njit.linker.util.UtilsPool;

//@Service("webService")
public class WebServiceImpl implements WebService {
	private final String baseURL;

	@Override
	public String submitMessage(String actionStr) throws IOException {
		System.out.println(this.getClass().getName()+ ".submitMessage : "+ actionStr);
		HttpURLConnection http = UtilsPool.getWebUtil().getHttpURLConnection(baseURL+actionStr);
		http.connect();// Á¬½Ó
		InputStreamReader isr = new InputStreamReader(http.getInputStream(),
				"utf-8");
		BufferedReader in = new BufferedReader(isr);

		String inputLine =null;
		String retStr = null;
		while ((inputLine = in.readLine()) != null) {
			retStr=inputLine;
		}
		in.close();
		http.disconnect();
		return retStr;
	}
	public WebServiceImpl(String url){
		baseURL = url;
	}

}
