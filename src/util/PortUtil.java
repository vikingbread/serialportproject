package com.njit.linker.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.comm.CommPortIdentifier;

/**
 * @author Administrator
 * 查找搜有才在的端口
 *
 */
public class PortUtil {
	public List<String> getPortIdentifiers(){
		List list = new ArrayList();
		CommPortIdentifier portId;
		Enumeration portIdentifiers = CommPortIdentifier.getPortIdentifiers();
		while (portIdentifiers.hasMoreElements()) {
			portId = (CommPortIdentifier) portIdentifiers.nextElement();
			list.add(portId.getName());
		}
		return list;
	}
}
