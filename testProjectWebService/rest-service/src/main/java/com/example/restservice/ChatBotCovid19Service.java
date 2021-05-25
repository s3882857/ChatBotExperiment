package com.example.restservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.util.logging.*;

/*
 * This is the starting point for a spring rest service
 * See ChatBotController for full details on what this does.
 */
@SpringBootApplication
public class ChatBotCovid19Service {

	private final static Logger log = Logger.getLogger("fileLogger");
	static private FileHandler fileHandler;
	static private SimpleFormatter formatterTxt;
	
	public static void main(String[] args) {
		
		try {
			fileHandler = new FileHandler("app.log.txt", true);
			formatterTxt = new SimpleFormatter(); 
			fileHandler.setFormatter(formatterTxt);
			log.addHandler(fileHandler);
			
		} catch (SecurityException | IOException e) {
			System.out.println(e.getMessage());
		}        
        
        log.info("Start In TestWebProjectApplication");
        
		SpringApplication.run(ChatBotCovid19Service.class, args);
		
	}
}
