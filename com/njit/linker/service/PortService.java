package com.njit.linker.service;

import javax.comm.SerialPort;
/**
 * 
 * @author Administrator
 *串行端口接口类
 *
 */
public interface PortService {
	/**
	 * 串口参数设置
	 */
	void setPortParams(SerialPort port);
	/**
	 * 获得当前串口
	 * @return 当前串口
	 */
	SerialPort getPort();
	
}
