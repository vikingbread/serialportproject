package com.njit.linker.main;

import java.util.Enumeration;
import java.util.List;

import javax.comm.CommPortIdentifier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.njit.linker.util.UtilsPool;
/**
 * <p>启动类</p>
 * 在没有输入端口名称的情况下可以
 * 自动搜索串口,但是不能保证是正确的端口。
 * 			
 */
public class Starter {
	private static Log logger = LogFactory.getLog(Starter.class); 
	
	/**
	 * @param <p>1.Webservice Address [serialPort name]</p>
	 * 			
	 */
	public static void main(String[] args) {
		if(args.length<1){
			System.out.println("Usage: Linker WebAddress");
			System.exit(1);
		}
		
		String url = args[0];
		boolean isValid = UtilsPool.getWebUtil().isURLValid(url);
		if(!isValid){
			System.out.println("无效的主机地址！请重试。");
			logger.error("无效的主机地址！请重试。");
			System.exit(1);
		}
		String comName = args[1];
		if(comName==null){
		 comName = getPortName() ;
		}
		
		List<String> list = UtilsPool.getPortUtil().getPortIdentifiers();
		if(!list.contains(comName)){
			System.out.println("无效的端口！请重试。");
			logger.error("无效的端口！请重试。");
			System.exit(1);
		}
		new WorkStation(url, comName);//实例化主类
	}
	public static String getPortName() {
		Enumeration<?> en = CommPortIdentifier.getPortIdentifiers();
		CommPortIdentifier portID;
		String str = null;
		while (en.hasMoreElements()) {
			portID = (CommPortIdentifier) en.nextElement();
			if (portID.getPortType() == 1)
				return portID.getName();//简单处理搜索到SerialPort立即返回
		}
		System.out.println("没有发现任何端口！");
		logger.error("没有发现任何端口！");
		System.exit(1);
		return str;
	}
}
