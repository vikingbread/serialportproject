package com.njit.linker.util;

import javax.comm.SerialPort;
/**
 * @author Administrator
 * 串口端口参数类
 *
 */
public class SerialParameters {
	//默认参数
	private final  int DEFAULT_BAUDRATE = 19200;
	private final  int DEFAULT_DATEBITS = SerialPort.DATABITS_8;
	private final  int DEFAULT_PARITY = SerialPort.PARITY_NONE;
	private final  int DEFAULT_STOPBITS = SerialPort.STOPBITS_1;
	
	//private String portName = ""	
	private int baudRate ;
	private int dataBits;
	private int stopBits;
	private int parity;
	
	public SerialParameters(int baudRate,int dataBits,int stopBits,int parity) {
		this.baudRate = baudRate;
		this.dataBits = dataBits;
		this.stopBits = stopBits;
		this.parity = parity;
		
	}
	
	public SerialParameters() {
		this.baudRate = DEFAULT_BAUDRATE;
		this.dataBits = DEFAULT_DATEBITS;
		this.stopBits = DEFAULT_STOPBITS;
		this.parity = DEFAULT_PARITY;
	}
	
	public int getBaudRate() {
		return baudRate;
	}
	public void setBaudRate(int baudRate) {
		this.baudRate = baudRate;
	}
	public int getDataBits() {
		return dataBits;
	}
	public void setDataBits(int dataBits) {
		this.dataBits = dataBits;
	}
	public int getStopBits() {
		return stopBits;
	}
	public void setStopBits(int stopBits) {
		this.stopBits = stopBits;
	}
	public int getParity() {
		return parity;
	}
	public void setParity(int parity) {
		this.parity = parity;
	}

}
