package com.njit.linker.util;

import java.util.Enumeration;
import javax.comm.CommPortIdentifier;

/**
 *列出搜有串行端口
 * @author viking
 *
 */
public class ListSerial {

	public static void main(String[] args) {
		
		listPortChoices();
		
	}

	public static void listPortChoices() {
		Enumeration<?> en = CommPortIdentifier.getPortIdentifiers();
		CommPortIdentifier portID;

		while (en.hasMoreElements()) {
			portID = (CommPortIdentifier) en.nextElement();
			if (portID.getPortType() == 1)
				System.out.println(portID.getName());
		}

	}
}