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
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL)/* getPortType�������ض˿����� */
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
		 case SerialPortEvent.BI:/*Break interrupt,ͨѶ�ж�*/
		 case SerialPortEvent.OE:/*Overrun error����λ����*/
		 case SerialPortEvent.FE:/*Framing error����֡����*/
		 case SerialPortEvent.PE:/*Parity error��У�����*/
		 case SerialPortEvent.CD:/*Carrier detect���ز����*/
		 case SerialPortEvent.CTS:/*Clear to send���������*/
		 case SerialPortEvent.DSR:/*Data set ready�������豸����*/
		 case SerialPortEvent.RI:/*Ring indicator������ָʾ*/
		 case SerialPortEvent.OUTPUT_BUFFER_EMPTY:break;/*Output buffer is empty��������������*/
		 case SerialPortEvent.DATA_AVAILABLE:/*Data available at the serial port���˿��п������ݡ������������飬������ն�*/
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
