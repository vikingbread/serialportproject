package com.njit.linker.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

import junit.framework.Assert;

import org.junit.Test;

import com.njit.linker.util.UtilsPool;

public class WebserviceTest {
	@Test
	public void getHttpConnetcion() throws IOException{
		HttpURLConnection httpURLConnection = UtilsPool.getWebUtil().getHttpURLConnection("http://127.0.0.1:8080/info/saveRecord");
		Assert.assertNotNull(httpURLConnection);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(),"utf8"));
		out.print("record.id=121&message=ee我不ee知道abcdef");// 传入参数
		out.close();

		InputStreamReader isr = new InputStreamReader(
				httpURLConnection.getInputStream(), "utf-8");
		BufferedReader in = new BufferedReader(isr);

		String inputLine;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("result.xml")));
		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
			bw.write(inputLine);
			bw.newLine();
		}
		bw.close();
		in.close();
		httpURLConnection.disconnect();
	}
	@Test
	public void isURLVilid(){
		boolean ret = UtilsPool.getWebUtil().isURLValid("http://127.0.0.1:8080/info/saveRecord");
		Assert.assertTrue(ret);
	}
}
