package com.hprn.zylog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.hprn.zylog.model.LogData;
import com.hprn.zylog.model.LogInfo;


import sun.rmi.log.LogInputStream;

public class LogReader {
	
	private List<String> log;
	private String dataSource;
	
	public LogReader(List<String> log, String dataSource) {
		this.log = log;
		this.dataSource = dataSource;
	}
	
	public LogReader(String data, String dataSource) {
		List<String> msg = Arrays.asList(data.split("\\r\\n"));
		this.log = msg;
		this.dataSource = dataSource;
	}

	public LogData analyse() {
		// System.out.println("Starting analysing...");
		if (log == null) 
			throw new NullPointerException();
		
		LogData logData = new LogData();
		LogInfo logInfo = new LogInfo();
		logInfo.setDataSource(dataSource);
		
		int dataLine = 0;
		boolean isHeader = true;
		int dsCount = 0;
		
		for (String s : log) {
			dataLine++;
			// Check if end of log
			if (s.equals("End of Logs")) {
				//System.out.println("EOF");
				break;
			}
			
			// One data set consists of 3 lines
			// first 3 lines is header info
			if (dataLine > 3) {
				if (isHeader)
					isHeader = false;
				dataLine = 1;
			}
			
			if (!isHeader) {
				processDataLine(s, dataLine, logInfo);
				if (dataLine == 3) {
					logData.add(logInfo);
					dsCount++;
					logInfo = new LogInfo();
					logInfo.setDataSource(dataSource);
				}
					
			} else {
//				System.out.println("Skipping header: " + Integer.toString(dataLine));
			}
			
		}
		
		//System.out.println("Data set(s) received: " + Integer.toString(dsCount));
		
		return logData;
	}

	private void processDataLine(String s, int dataLine, LogInfo logInfo) {
		switch (dataLine) {
			case 1: 
				int no = Integer.parseInt(s.substring(0, 3).trim());
				logInfo.setNo(no);
				
				String dateTimeStr = s.substring(5, 24); 
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					cal.setTime(sdf.parse(dateTimeStr));
					logInfo.setDateTime(cal);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
				String source = s.substring(25, 47).trim(); 
				logInfo.setSource(source);
				
				String destination = s.substring(48, s.length()-1).trim(); 
				logInfo.setDestination(destination);
				break;
			case 2: 
				break;
			case 3: 
				logInfo.setNote(s.trim());
				break;
		}
	}

}
