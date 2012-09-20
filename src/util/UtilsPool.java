package com.njit.linker.util;
/**
 *<p> ���õĳ������߳� </p>
 * ���PortUtil&WebUtil
 */
public class UtilsPool {
	private static final PortUtil portUtil = new PortUtil();
	private static final WebUtil webUtil = new WebUtil();
	
	public static PortUtil getPortUtil(){
		return portUtil;
	}
	public static WebUtil getWebUtil(){
		return webUtil;
	}
}
