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
 * 启动类
 * </p>
 * 在没有输入端口名称的情况下可以 自动搜索串口
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
			System.out.println("无效的主机地址！请重试。");
			logger.error("无效的主机地址！请重试。");
			System.exit(1);
		}
		// String comName = args[1];
		String comName = getPortName1();
		if (comName == null) {
			comName = getPortName();
		}

		List<String> list = UtilsPool.getPortUtil().getPortIdentifiers();
		if (!list.contains(comName)) {
			System.out.println("无效的端口！请重试。");
			logger.error("无效的端口！请重试。");
			System.exit(1);
		}
		new WorkStation(url, comName);// 实例化主类
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
			System.out.println("没有发现任何端口！");
			logger.error("没有发现任何端口！");
			System.exit(1);
		}else if(list.size()>1){
			System.out.println("发现多个端口");
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
				System.out.println("输入数字在"+1+"~"+list.size()+1+"之间");
				continue;
			}
			temp = Integer.parseInt(read);
			if(temp-1>list.size()){
				System.out.println("输入数字在"+1+"~"+list.size()+1+"之间");
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
				return portID.getName();// 简单处理 ,搜索到SerialPort立即返回
		}
	
		System.out.println("没有发现任何端口！");
		logger.error("没有发现任何端口！");
		System.exit(1);
		return name;
	}
}
