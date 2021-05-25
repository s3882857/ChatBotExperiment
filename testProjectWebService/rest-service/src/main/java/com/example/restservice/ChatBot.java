package com.example.restservice;

import java.util.logging.Logger;

public class ChatBot {

	private final long id;
	private final String content;
	private final static Logger log = Logger.getLogger("fileLogger");
	
	public ChatBot(long id, String content) {
		log.info("In Greeting id = " + id + " content = " + content);
		this.id = id;
		this.content = content;
	}

	public long getId() {
		log.info("In Greeting getId() id = " + id);
		return id;
	}

	public String getContent() {
		log.info("In Greeting getContent() content = " + content);
		return content;
	}
}