package com.njit.linker.service;
/**
 * @author viking 
 * 字符串分析接口
 *
 */
public interface MessageAnalysisService {
	
	/**
	 * 分析从串口收到的信息
	 * @param receivedBuffer 收到的字符数组
	 * @return 经过处理的字符串
	 */
	String analysisReceivedBuffer(byte[] receivedBuffer);
	
	/**
	 * 分析从webservice收到的信息
	 * @param returnStr 返回的信心
	 * @return 经过处理的字符串
	 */
	byte[] analysisReturnString(String returnStr);
}