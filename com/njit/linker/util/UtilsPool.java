package com.njit.linker.util;
/**
 *<p> 常用的常量工具池 </p>
 * 存放PortUtil&WebUtil
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
