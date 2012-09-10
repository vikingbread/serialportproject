package com.njit.linker.service.impl;

import java.util.TooManyListenersException;
import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.SerialPortEventListener;
import javax.comm.UnsupportedCommOperationException;


import com.njit.linker.service.PortService;
import com.njit.linker.util.SerialParameters;
/**
 * @author Administrator
 * 串口初始化类实现PortService接口
 *
 */
public class PortServiceImpl implements PortService {
	private SerialPort port;
	 
	public PortServiceImpl(String comName,SerialPortEventListener listener) {
		
		CommPortIdentifier portID;
		try {
			portID = CommPortIdentifier.getPortIdentifier(comName);
			port = (SerialPort) portID.open("PortListener", 2000);
		} catch (NoSuchPortException e) {
			e.printStackTrace();
		} catch (PortInUseException e) {
			e.printStackTrace();
		}
		port.notifyOnDataAvailable(true);
		setPortParams(port);
		try {
			port.addEventListener(listener);
		} catch (TooManyListenersException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setPortParams(SerialPort port) {
		SerialParameters portParm = new SerialParameters();
		try {
			port.setSerialPortParams(portParm.getBaudRate(),
					portParm.getDataBits(), portParm.getStopBits(),
					portParm.getParity());
		} catch (UnsupportedCommOperationException e) {
			e.printStackTrace();
		}
	}

	public SerialPort getPort() {
		return port;
	}
}
