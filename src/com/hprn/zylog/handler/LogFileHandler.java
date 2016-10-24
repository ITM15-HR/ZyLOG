package com.hprn.zylog.handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LogFileHandler {

	private String filename;
	
	public LogFileHandler(String filename) {
		this.filename = filename;
	}
	
	public boolean isValid() {
		return true;
	}
	
	public List<String> read() throws FileNotFoundException {
		Scanner sc = new Scanner(new File(filename));
		List<String> lines = new ArrayList<String>();
		while (sc.hasNextLine()) {
		  lines.add(sc.nextLine());
		}
		sc.close();
		
		return lines;
	}
	
}
