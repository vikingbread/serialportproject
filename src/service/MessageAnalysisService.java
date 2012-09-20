package com.njit.linker.service;
/**
 * @author viking 
 * �ַ��������ӿ�
 *
 */
public interface MessageAnalysisService {
	
	/**
	 * �����Ӵ����յ�����Ϣ
	 * @param receivedBuffer �յ����ַ�����
	 * @return ����������ַ���
	 */
	String analysisReceivedBuffer(byte[] receivedBuffer);
	
	/**
	 * ������webservice�յ�����Ϣ
	 * @param returnStr ���ص�����
	 * @return ����������ַ���
	 */
	byte[] analysisReturnString(String returnStr);
}