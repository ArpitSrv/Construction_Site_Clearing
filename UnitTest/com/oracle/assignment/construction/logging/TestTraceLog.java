package com.oracle.assignment.construction.logging;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;


public class TestTraceLog {

	
	 static Logger log = Logger.getLogger(TestTraceLog.class.getName());
	   
	   public static void main(String[] args)throws IOException,SQLException{
	      log.debug("Hello this is a debug message");
	      log.info("Hello this is an info message");
	   }
}
