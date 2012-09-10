package com.njit.linker.test;

import java.io.*;
import java.util.*;
import javax.comm.*;

public class SimpleRead implements Runnable, SerialPortEventListener {
	static CommPortIdentifier portId;
	static Enumeration portList;
	InputStream inputStream;
	SerialPort serialPort;
	Thread readThread;
	public static void main(String[] args) {
		portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL)/* getPortType方法返回端口类型 */
			{
				System.out.println(portId.getName());
				SimpleRead reader = new SimpleRead();
			}
		}
		System.out.println("main end");
	}
	public SimpleRead() {
		try {
			serialPort = (SerialPort) portId.open("SimpleReadApp", 2000);
		}catch (PortInUseException e) {e.printStackTrace();}
		try {
			inputStream = serialPort.getInputStream();
		}catch (IOException e) {e.printStackTrace();}
		try {
			 serialPort.addEventListener(this);
		}catch (TooManyListenersException e) {}
		 serialPort.notifyOnDataAvailable(true);
		 try{
			 serialPort.setSerialPortParams(38400, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		 }catch (UnsupportedCommOperationException e) {}
		 readThread = new Thread(this);
		 readThread.start();
	}
	@Override
	public void serialEvent(SerialPortEvent arg0) {
		 switch(arg0.getEventType()) {
		 case SerialPortEvent.BI:/*Break interrupt,通讯中断*/
		 case SerialPortEvent.OE:/*Overrun error，溢位错误*/
		 case SerialPortEvent.FE:/*Framing error，传帧错误*/
		 case SerialPortEvent.PE:/*Parity error，校验错误*/
		 case SerialPortEvent.CD:/*Carrier detect，载波检测*/
		 case SerialPortEvent.CTS:/*Clear to send，清除发送*/
		 case SerialPortEvent.DSR:/*Data set ready，数据设备就绪*/
		 case SerialPortEvent.RI:/*Ring indicator，响铃指示*/
		 case SerialPortEvent.OUTPUT_BUFFER_EMPTY:break;/*Output buffer is empty，输出缓冲区清空*/
		 case SerialPortEvent.DATA_AVAILABLE:/*Data available at the serial port，端口有可用数据。读到缓冲数组，输出到终端*/
			 byte[] readBuffer = new byte[20];
			 try{
				 while(inputStream.available()>0){
					 int numBytes = inputStream.read(readBuffer);
				 }
				 System.out.println(new String(readBuffer));
				 
			 }catch(IOException e){e.printStackTrace();}
			 break;
		 }
	}

	@Override
	public void run() {
		try{
			Thread.sleep(20000);
			System.out.println("sleep ends");
		}catch(InterruptedException e){}
		
	}
	
}
