package com.njit.linker.service;

import javax.comm.SerialPort;
/**
 * 
 * @author Administrator
 *���ж˿ڽӿ���
 *
 */
public interface PortService {
	/**
	 * ���ڲ�������
	 */
	void setPortParams(SerialPort port);
	/**
	 * ��õ�ǰ����
	 * @return ��ǰ����
	 */
	SerialPort getPort();
	
}
