package com.njit.linker.service;

import java.io.IOException;
/**
 * @author Administrator
 * webservice�ӿ�
 *
 */
public interface WebService {
	/**
	 * 
	 * @param actionStr ������Ϣ
	 * @return ���������ص�����
	 */
	String submitMessage(String actionStr)throws IOException;
}
