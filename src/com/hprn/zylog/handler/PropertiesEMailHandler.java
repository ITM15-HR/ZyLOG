package com.hprn.zylog.handler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesEMailHandler {
	
	public static final String EMAIL_HOST = "email_host";
	public static final String EMAIL_PORT = "email_port";
	public static final String EMAIL_USER = "email_user";
	public static final String EMAIL_PASSWORD = "email_password";
	public static final String EMAIL_SUBJECT = "email_subject";
	public static final String EMAIL_MSGCOUNT = "email_msgcount";
	
	private String filename; 
	private Properties properties = new Properties();;
	
	public PropertiesEMailHandler(String filename) {
		this.filename = filename;
	}
	
	public void load() {
		InputStream input = null;

		try {
			input = new FileInputStream(filename);
			// load a properties file
			properties.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void save() {
		OutputStream output = null;
		try {
			output = new FileOutputStream(filename);
			// save properties to project root folder
			properties.store(output, null);
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	public Properties getProperties() {
		return properties;
	}
	
	public void setProperty(String key, String value) {
		properties.setProperty(key, value);
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
