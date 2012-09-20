package com.njit.linker.main;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.njit.linker.service.MessageAnalysisService;
import com.njit.linker.service.PortService;
import com.njit.linker.service.WebService;
import com.njit.linker.service.impl.MessageAnalysisServiceImpl;
import com.njit.linker.service.impl.PortServiceImpl;
import com.njit.linker.service.impl.WebServiceImpl;
/**
 * @author Administrator
 * 核心类 <br/>
 * 负责调用各种接口 进行，消息的处理
 *
 */
public class WorkStation implements SerialPortEventListener{
	private static Log logger = LogFactory.getLog(WorkStation.class);
	private PortService portService;
	private WebService webService;
	private InputStream inputStream;
	private MessageAnalysisService messageAnalysisService;
	private DataOutputStream dataOutputStream ;
	
	public WorkStation(String serviceUrl, String com){
		webService = new WebServiceImpl(serviceUrl);
		messageAnalysisService = new MessageAnalysisServiceImpl();
		portService = new PortServiceImpl(com,this);
		SerialPort port = portService.getPort();
		try {
			inputStream = port.getInputStream();
			dataOutputStream = new DataOutputStream(port.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void serialEvent(SerialPortEvent arg0) {
		 switch(arg0.getEventType()) {
		 case SerialPortEvent.BI:System.out.println("Break interrupt,通讯中断");break;
		 case SerialPortEvent.OE:System.out.println("Overrun error，溢位错误");break;
		 case SerialPortEvent.FE:System.out.println("Framing error，传帧错误");break;
		 case SerialPortEvent.PE:System.out.println("Parity error，校验错误");break;
		 case SerialPortEvent.CD:System.out.println("Carrier detect，载波检测");break;
		 case SerialPortEvent.CTS:System.out.println("Clear to send，清除发送");break;
		 case SerialPortEvent.DSR:System.out.println("Data set ready，数据设备就绪");break;
		 case SerialPortEvent.RI:System.out.println("Ring indicator，响铃指示");break;
		 case SerialPortEvent.OUTPUT_BUFFER_EMPTY:System.out.println("Output buffer is empty，输出缓冲区清空");break;
		 case SerialPortEvent.DATA_AVAILABLE:/*Data available at the serial port，端口有可用数据*/
			 try {
				 Thread.sleep(100);
			 } catch (InterruptedException e1) {
				 e1.printStackTrace();
			 }
			 byte[] receivedBuffer = new byte[5];
			 int numBytes = 0;
			 try{
				 while(inputStream.available()>0){
					numBytes = inputStream.read(receivedBuffer);
				 }
				 if(numBytes>0){
					 analysisBuffer(receivedBuffer);
				 }
				 
			 }catch(IOException e){
				 	logger.error("端口出现异常", e);
				 	e.printStackTrace();
				 }
			 break;
		 }
	}

	private void analysisBuffer(byte[] receivedBuffer) throws IOException {
		String actionStr = messageAnalysisService.analysisReceivedBuffer(receivedBuffer);
		if(actionStr==null){
			return;
		}
		 String returnStr = webService.submitMessage(actionStr);
		 if(returnStr==null){
			 return;
		 }
		 System.out.println(this.getClass().getName()+"serialEvent : "+returnStr);
		
		 byte[] bytes = messageAnalysisService.analysisReturnString(returnStr);
		 sendBufferToPort(bytes);
	}
	private void sendBufferToPort(byte[] bytes){
		try {
				for(int i=0;i<bytes.length;i++){
				dataOutputStream.writeByte(bytes[i]);
			}
				dataOutputStream.flush();
			} catch (IOException e) {
				logger.error("向串口发送数据时发生异常。", e);
				e.printStackTrace();
		}
		System.out.println("sendbuffer");
	}
}
