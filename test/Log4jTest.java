package com.njit.linker.test;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public class Log4jTest {
	public static void main(String[] args) {
	//	PropertyConfigurator.configure ( "test.properties" ) ;
		Log log = LogFactory.getLog(Log4jTest.class);
		
		log.error("this is a error test~~~");
		System.out.println(new Date());
	}
}
