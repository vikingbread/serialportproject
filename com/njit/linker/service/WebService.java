package com.njit.linker.service;

import java.io.IOException;
/**
 * @author Administrator
 * webservice接口
 *
 */
public interface WebService {
	/**
	 * 
	 * @param actionStr 请求信息
	 * @return 服务器返回的数据
	 */
	String submitMessage(String actionStr)throws IOException;
}
