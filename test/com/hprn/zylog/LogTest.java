package com.hprn.zylog;
import java.io.FileNotFoundException;
import java.util.List;

import com.hprn.zylog.LogReader;
import com.hprn.zylog.handler.LogEMailHandler;
import com.hprn.zylog.handler.LogFileHandler;
import com.hprn.zylog.handler.PropertiesEMailHandler;
import com.hprn.zylog.model.LogData;
import com.hprn.zylog.model.LogInfo;

import junit.framework.TestCase;


public class LogTest extends TestCase {
	
	public void testLogAdd() {
		LogInfo logInfo = new LogInfo();
		logInfo.setNo(1);
		System.out.println(logInfo.getId());
		assertEquals(1, logInfo.getNo());
	}
	
	public void testGetLog() {
		LogInfo logInfo = new LogInfo();
		logInfo.setNo(1);
		LogData logData = new LogData(); 
		logData.add(logInfo);
		LogInfo loadedLog = logData.get(logInfo.getId());
		System.out.println(logInfo.getId());
		assertEquals(loadedLog.getId(), logInfo.getId());
	}
	
	//@Test(expected=FileNotFoundException.class)
	public void testProcessFile() {
		String logFilename = "E:\\ZyLog.txt";  //"C:\\_tmp\\ZyLog.txt"
		LogFileHandler logFile = new LogFileHandler(logFilename);
		try {
			List<String> logData = logFile.read();
			LogReader logReader = new LogReader(logData, logFilename);
			LogData logs = logReader.analyse();
			System.out.println(logs.getByIndex(0).getNote());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testEMailTest() {
		PropertiesEMailHandler prop = new PropertiesEMailHandler("mailconfig.properties");
		prop.load();
		LogEMailHandler mail = new LogEMailHandler(prop.getProperty(PropertiesEMailHandler.EMAIL_HOST), 
				Integer.parseInt(prop.getProperty(PropertiesEMailHandler.EMAIL_PORT)),
				prop.getProperty(PropertiesEMailHandler.EMAIL_USER),
				prop.getProperty(PropertiesEMailHandler.EMAIL_PASSWORD),
				prop.getProperty(PropertiesEMailHandler.EMAIL_SUBJECT),
				Integer.parseInt(prop.getProperty(PropertiesEMailHandler.EMAIL_MSGCOUNT)));
		LogData logs = mail.check();
		System.out.println(logs.getByIndex(0).getNo());
		System.out.println(logs.getByIndex(0).getSource());
		System.out.println(logs.getByIndex(0).getDestination());
		System.out.println(logs.getByIndex(0).getNote());
		System.out.println(logs.getByIndex(0).getDataSource());
	}
	
	public void testPropertiesSave() {
		PropertiesEMailHandler properties = new PropertiesEMailHandler("mailconfig.properties");
		// set the properties value
		properties.setProperty(PropertiesEMailHandler.EMAIL_HOST, "mail.server.com");
		properties.setProperty(PropertiesEMailHandler.EMAIL_PORT, "993");
		properties.setProperty(PropertiesEMailHandler.EMAIL_USER, "mail@server.com");
		properties.setProperty(PropertiesEMailHandler.EMAIL_PASSWORD, "YourPass");
		properties.setProperty(PropertiesEMailHandler.EMAIL_SUBJECT, "YourSubject");
		properties.setProperty(PropertiesEMailHandler.EMAIL_MSGCOUNT, "250");
		properties.save();
	}
	
}
