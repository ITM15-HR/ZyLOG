package com.hprn.zylog.model;

import java.util.ArrayList;
import java.util.List;

public class LogData {
	
	private List<LogInfo> zyLogs = new ArrayList<LogInfo>();
	
	public void add(LogInfo logInfo) {
		zyLogs.add(logInfo);
	}
	
	public LogInfo get(String id) {
		for (LogInfo logInfo : zyLogs) {
			if (logInfo.getId().equals(id)) {
				return logInfo;
			}
		}
		return null;
	}

	public int count() {
		return zyLogs.size();
	}

	public LogInfo getByIndex(int index) {
		if (index >= zyLogs.size()) 
			throw new IndexOutOfBoundsException();
		
		return zyLogs.get(index);
	}
	
	public void addLogs(LogData logData) {
		if (logData == null)
			throw new NullPointerException();
		
		for (int i = 0; i < logData.count(); i++) {
			zyLogs.add(logData.getByIndex(i));
		}
	}
	
}
