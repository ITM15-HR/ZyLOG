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
	
	public LogData filterBySource(String filter) {
		LogData filteredData = new LogData();
		
		for (LogInfo logInfo : zyLogs) {
			if (logInfo.getSource() != null)
				if (logInfo.getSource().contains(filter))
					filteredData.add(logInfo);
		}
		
		return filteredData;
	}
	
	public LogData filterByDestination(String filter) {
		LogData filteredData = new LogData();
		
		for (LogInfo logInfo : zyLogs) {
			if (logInfo.getDestination() != null)
				if (logInfo.getDestination().contains(filter))
					filteredData.add(logInfo);
		}
		
		return filteredData;
	}
	
	public LogData filterByPriority(String filter) {
		LogData filteredData = new LogData();
		
		for (LogInfo logInfo : zyLogs) {
			if (logInfo.getPriority() != null)
				if (logInfo.getPriority().contains(filter))
					filteredData.add(logInfo);
		}
		
		return filteredData;
	}
	
	public LogData filterByCategory(String filter) {
		LogData filteredData = new LogData();
		
		for (LogInfo logInfo : zyLogs) {
			if (logInfo.getCategory() != null)
				if (logInfo.getCategory().contains(filter))
					filteredData.add(logInfo);
		}
		
		return filteredData;
	}
	
	public LogData filterByNote(String filter) {
		LogData filteredData = new LogData();
		
		for (LogInfo logInfo : zyLogs) {
			if (logInfo.getNote() != null)
				if (logInfo.getNote().contains(filter))
					filteredData.add(logInfo);
		}
		
		return filteredData;
	}
	
	public LogData filterByMessage(String filter) {
		LogData filteredData = new LogData();
		
		for (LogInfo logInfo : zyLogs) {
			if (logInfo.getMessage() != null)
				if (logInfo.getMessage().contains(filter))
					filteredData.add(logInfo);
		}
		
		return filteredData;
	}

	public List<String> getSources(boolean unique) {
		List<String> sources = new ArrayList<String>();
		for (LogInfo logInfo : zyLogs) {
			String[] s = logInfo.getSource().split(":");
			if (unique) {
				if (sources.contains(s[0]))
					continue;
			}
			sources.add(s[0]);
		}
		return sources;
	}
	
	public List<String> getDestinations(boolean unique) {
		List<String> sources = new ArrayList<String>();
		for (LogInfo logInfo : zyLogs) {
			String[] s = logInfo.getDestination().split(":");
			if (unique) {
				if (sources.contains(s[0]))
					continue;
			}
			sources.add(s[0]);
		}
		return sources;
	}
	
	public List<String> getPriorities(boolean unique) {
		List<String> sources = new ArrayList<String>();
		for (LogInfo logInfo : zyLogs) {
			String s = logInfo.getPriority();
			if (unique) {
				if (sources.contains(s))
					continue;
			}
			sources.add(s);
		}
		return sources;
	}
	
	public List<String> getCategories(boolean unique) {
		List<String> sources = new ArrayList<String>();
		for (LogInfo logInfo : zyLogs) {
			String s = logInfo.getCategory();
			if (unique) {
				if (sources.contains(s))
					continue;
			}
			sources.add(s);
		}
		return sources;
	}
	
	public List<String> getNotes(boolean unique) {
		List<String> sources = new ArrayList<String>();
		for (LogInfo logInfo : zyLogs) {
			String s = logInfo.getNote();
			if (unique) {
				if (sources.contains(s))
					continue;
			}
			sources.add(s);
		}
		return sources;
	}
	
	public List<String> getMessages(boolean unique) {
		List<String> sources = new ArrayList<String>();
		for (LogInfo logInfo : zyLogs) {
			String s = logInfo.getMessage();
			if (unique) {
				if (sources.contains(s))
					continue;
			}
			sources.add(s);
		}
		return sources;
	}
	
}
