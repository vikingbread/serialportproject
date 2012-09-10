package com.njit.linker.main;

import java.util.Enumeration;
import java.util.List;

import javax.comm.CommPortIdentifier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.njit.linker.util.UtilsPool;
/**
 * <p>������</p>
 * ��û������˿����Ƶ�����¿���
 * �Զ���������,���ǲ��ܱ�֤����ȷ�Ķ˿ڡ�
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
			System.out.println("��Ч��������ַ�������ԡ�");
			logger.error("��Ч��������ַ�������ԡ�");
			System.exit(1);
		}
		String comName = args[1];
		if(comName==null){
		 comName = getPortName() ;
		}
		
		List<String> list = UtilsPool.getPortUtil().getPortIdentifiers();
		if(!list.contains(comName)){
			System.out.println("��Ч�Ķ˿ڣ������ԡ�");
			logger.error("��Ч�Ķ˿ڣ������ԡ�");
			System.exit(1);
		}
		new WorkStation(url, comName);//ʵ��������
	}
	public static String getPortName() {
		Enumeration<?> en = CommPortIdentifier.getPortIdentifiers();
		CommPortIdentifier portID;
		String str = null;
		while (en.hasMoreElements()) {
			portID = (CommPortIdentifier) en.nextElement();
			if (portID.getPortType() == 1)
				return portID.getName();//�򵥴���������SerialPort��������
		}
		System.out.println("û�з����κζ˿ڣ�");
		logger.error("û�з����κζ˿ڣ�");
		System.exit(1);
		return str;
	}
}
