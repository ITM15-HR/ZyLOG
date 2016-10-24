package com.hprn.zylog.handler;


import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Folder;
import javax.mail.Header;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import com.hprn.zylog.LogReader;
import com.hprn.zylog.model.LogData;


public class LogEMailHandler {
	
	private String host;
	private int port;
	private String user;
	private String password;
	private String partOfSubject;
	private int messageCount;
	
	public LogEMailHandler(String host, int port, String user, String password, String partOfSubject, int messageCount) {
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
		this.partOfSubject = partOfSubject;
		if (messageCount < 0)
			messageCount = 0;
		this.messageCount = messageCount;
		
	}
	
	public LogData check() {
		try {

			//create properties field
			Properties properties = new Properties();
			
			properties.put("mail.imap.host", host);
			properties.put("mail.imap.port", Integer.toString(port));
			
			// SSL setting
			properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			properties.setProperty("mail.imap.socketFactory.fallback", "false");
			properties.setProperty("mail.imap.socketFactory.port", Integer.toString(port));
			      
			Session emailSession = Session.getDefaultInstance(properties);
			  
			//create the POP3 store object and connect with the pop server
			Store store = emailSession.getStore("imap");
			
			store.connect(host, user, password);
			
			//create the folder object and open it
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);
			
			// Define search term
			/*SearchTerm term = new SearchTerm() {
			public boolean match(Message message) {
				try {
					if (message.getSubject() != null)
						if (message.getSubject().contains(partOfSubject)) {
							System.out.println("Subject matches: " + partOfSubject);
							return true;
						}
				} catch (MessagingException ex) {
					ex.printStackTrace();
				}
				return false;
			}
			};*/
			
			// search for subject
			//Message[] messages = emailFolder.search(term);
			
			// get all messages
			//Message[] messages = emailFolder.getMessages();
			
			//get last X messages or all if messageCount == 0
			int msgCount = emailFolder.getMessageCount();
			if (messageCount == 0)
			messageCount = msgCount - 1;
			Message[] messages = emailFolder.getMessages(msgCount-messageCount, msgCount);
			
//			System.out.println("messages.length---" + messages.length);
			
			int mailCounter = 0;
			LogData logs = new LogData();
			for (int i = 0, n = messages.length; i < n; i++) {
				Message message = messages[i];
				if (message.getSubject().equals(partOfSubject)) {
					mailCounter++;
					String date = "";
					for (@SuppressWarnings("rawtypes")Enumeration e = message.getAllHeaders() ; e.hasMoreElements() ;) {
				        Header header = (Header) e.nextElement();
				        if (header.getName().equals("Date"))
				        	date = header.getValue();
				     }
					LogReader logReader = new LogReader(message.getContent().toString()
			    			, "E-Mail: " + message.getSubject() + " / " + date);
			    	LogData currentLog = logReader.analyse();
			    	logs.addLogs(currentLog);
			    }
			}
			
//			System.out.println("Mails found: " + Integer.toString(mailCounter));
//			System.out.println("Log dataset(s): " + Integer.toString(logs.count()));
			
			//close the store and folder objects
			emailFolder.close(false);
			store.close();
			return logs;
		} catch (NoSuchProviderException e) {
		   e.printStackTrace();
		} catch (MessagingException e) {
		   e.printStackTrace();
		} catch (Exception e) {
		   e.printStackTrace();
		}
		return null;
	}

}
