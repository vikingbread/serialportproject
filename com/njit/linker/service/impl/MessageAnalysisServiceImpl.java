package com.njit.linker.service.impl;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.njit.linker.service.MessageAnalysisService;

/**
 * @author Administrator
 * �ַ���������  <br/>
 * �ɽ����յ���byteת��Ϊlong��longת��Ϊbyte
 *
 *
 */
public class MessageAnalysisServiceImpl implements MessageAnalysisService {
	private static Log logger = LogFactory.getLog(MessageAnalysisServiceImpl.class);
	private long userId =2682683403L;

	@Override
	public String analysisReceivedBuffer(byte[] receivedBuffer) {
		String str;
		StringBuilder sb;
		long l;
		if(receivedBuffer[0]==0x52&&receivedBuffer[1]==-97){//�Զ���ʶ���ַ���Э��
			sb = new StringBuilder("queryUserInfo?user.id=");
			byte[] bytes = Arrays.copyOfRange(receivedBuffer, 1, receivedBuffer.length);
			str = convertByteArrayToLongString(bytes);
			l = Long.parseLong(str, 2);
			return sb.append(l).toString();
		}
		if(receivedBuffer[0]==0x51){
			sb = new StringBuilder("saveRecord?user.id=").append(userId).append("&record.balance=");
			byte[] bytes = Arrays.copyOfRange(receivedBuffer, 1, 3);
			str = convertByteArrayToLongString(bytes);
			l = Long.parseLong(str, 2);
			return sb.append(l).toString();
		}
		System.out.println(this.getClass().getName()+".analysisReceivedBuffer : �յ�δ��ʶ�����Ϣ��");
		logger.warn("�յ�δ��ʶ�����Ϣ��"+receivedBuffer);
		 for(int i =0;i<receivedBuffer.length;i++){
			 System.out.print(receivedBuffer[i] +" ");
		 }
		 System.out.println();
		return null;
	}
	@Override
	public byte[] analysisReturnString(String returnStr) {
		String[] strs = returnStr.split(",");
		String binaryString = convertStringArrayToBinaryString(strs);
		byte[] bytes = convertBinaryStringToByteArray(binaryString);
		return bytes;
	}

	private byte[] convertBinaryStringToByteArray(String binaryString) {
		if(binaryString.length()%8!=0){
			throw new IllegalArgumentException("�����ַ����ĳ��ȴ���");
		}
		int length = binaryString.length()/8;
		byte[] bytes = new byte[length];
		int temp;
		for(int i=0;i<length;i++){
			temp = Integer.parseInt(binaryString.substring(8*i, 8*i+8), 2);
			bytes[i]=(byte)temp;
			
		}
		return bytes;
	}
	
	private String convertStringArrayToBinaryString(String[] strs) {
		StringBuilder sb = new StringBuilder();
		if(strs == null || strs.length==0){
			return "";
		}
		String str ;
		int length ;
		for(int i=0;i<strs.length;i++){
			str = Long.toBinaryString(new Long(strs[i]));
			length = str.length()%8==0?0:8-str.length()%8;//����պ���8�������� ��length=0
				for(int j=0;j<length;j++){
					sb.append(0);//����8�������� ���0
				}
			sb.append(str);
		}
		return sb.toString();
	}


	private String convertByteArrayToLongString(byte[] bs) {
		String intBinaryStr = "";
		if (bs == null || bs.length == 0) {
			return "";
		}
		if (bs[0] < 0) {
			intBinaryStr = Integer.toBinaryString(bs[0]).substring(24);
		} else {
			intBinaryStr = Integer.toBinaryString(bs[0]);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 8 - intBinaryStr.length(); i++) {//��0
				sb.append(0);
			}
			intBinaryStr = sb.append(intBinaryStr).toString();
		}
		if (bs.length > 1) {
			intBinaryStr += convertByteArrayToLongString(Arrays.copyOfRange(bs, 1, bs.length));
		}
		return intBinaryStr;
	}
}
