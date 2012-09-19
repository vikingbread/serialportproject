package com.njit.linker.main;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

import javax.comm.CommPortIdentifier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.njit.linker.util.UtilsPool;

/**
 * <p>
 * ������
 * </p>
 * ��û������˿����Ƶ�����¿��� �Զ���������
 * 
 */
public class Starter {
	private static Log logger = LogFactory.getLog(Starter.class);

	/**
	 * @param <p>
	 *        1.Webservice Address [serialPort name]
	 *        </p>
	 * 
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage: java com.info.main.Linker WebAddress");
			System.exit(1);
		}

		String url = args[0];
		boolean isValid = UtilsPool.getWebUtil().isURLValid(url);
		if (!isValid) {
			System.out.println("��Ч��������ַ�������ԡ�");
			logger.error("��Ч��������ַ�������ԡ�");
			System.exit(1);
		}
		// String comName = args[1];
		String comName = getPortName1();
		if (comName == null) {
			comName = getPortName();
		}

		List<String> list = UtilsPool.getPortUtil().getPortIdentifiers();
		if (!list.contains(comName)) {
			System.out.println("��Ч�Ķ˿ڣ������ԡ�");
			logger.error("��Ч�Ķ˿ڣ������ԡ�");
			System.exit(1);
		}
		new WorkStation(url, comName);// ʵ��������
	}

	public static String getPortName1() {
		List<String> list = new ArrayList<String>();
		Enumeration<?> en = CommPortIdentifier.getPortIdentifiers();
		CommPortIdentifier portID;
		String name = null;
		
		while (en.hasMoreElements()) {
			portID = (CommPortIdentifier) en.nextElement();
			if (portID.getPortType() == 1) {
				list.add(portID.getName());
			}
		}
		if (list.size() == 0) {
			System.out.println("û�з����κζ˿ڣ�");
			logger.error("û�з����κζ˿ڣ�");
			System.exit(1);
		}else if(list.size()>1){
			System.out.println("���ֶ���˿�");
			return getAName(list);
		}
		
		return name;
	}

	private static String getAName(List<String> list) {
		Scanner sc = new Scanner(System.in);
		for(int i=0;i<list.size();i++){
			System.out.println(i+1+":"+list.get(i));
		}
		String read;
		int temp;
		while((read = sc.nextLine())!=null){
			if(!read.matches("\\d+")){
				System.out.println("����������"+1+"~"+list.size()+1+"֮��");
				continue;
			}
			temp = Integer.parseInt(read);
			if(temp-1>list.size()){
				System.out.println("����������"+1+"~"+list.size()+1+"֮��");
				continue;
			}else{
				return list.get(temp-1);
			}
			
		}
		return null;
	}
	@Deprecated
	public static String getPortName() {
		// List<String> list = new ArrayList<String>();
		Enumeration<?> en = CommPortIdentifier.getPortIdentifiers();
		CommPortIdentifier portID;
		String name = null;
		while (en.hasMoreElements()) {
			portID = (CommPortIdentifier) en.nextElement();
			if (portID.getPortType() == 1)
				// list.add(e)
				return portID.getName();// �򵥴��� ,������SerialPort��������
		}
	
		System.out.println("û�з����κζ˿ڣ�");
		logger.error("û�з����κζ˿ڣ�");
		System.exit(1);
		return name;
	}
}
