package com.njit.linker.test;

import org.junit.Test;

import com.njit.linker.main.WorkStation;
import com.njit.linker.service.impl.PortServiceImpl;

public class PortTest {

	public static void main(String[] args) {
	//	new PortServiceImpl("COM2");
	//	new WorkStation(null, "COM2");
		new WorkStation("http://127.0.0.1:8080/info/", "COM2");
	}	
	
 @Test 
 public void testPort(){
	 
//	 WorkStation ws = new WorkStation(null,"COM2");
	new Thread(new Runnable() {
		
		@Override
		public void run() {
		///	 new PortServiceImpl("COM2");
		}
	}).start();
//	 new com.njit.linker.service.impl.SimpleRead("COM2");
 }
}
