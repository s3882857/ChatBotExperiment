package com.example.restservice;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatBotController {

	private final AtomicLong counter = new AtomicLong();
	private final static Logger log = Logger.getLogger("fileLogger");
	
	@GetMapping("/Covid19ChatBot")
	public ChatBot AskQuestion(@RequestParam(value = "question", defaultValue = "What is covid") String question) {
		log.info("In GreetingController name = " + question);
		
		// On-forward the question from client to our ChatBot service.
		// For now this URL is one long string.
		// In future it needs to be broken down into the parameters these  
		// details should be stored in a database per client/customer and retrieved each time.
		// URL = https://covid19team14chatbot.azurewebsites.net
		// AI style = qnamaker
		// AI knowledge base = knowledebases
		// Knowledge base key = ba2405ec-542a-4f2c-8511-72e54caa3a6b
		// AI response type = generateAnswer
		String urlString = "https://covid19team14chatbot.azurewebsites.net/qnamaker/knowledgebases/ba2405ec-542a-4f2c-8511-72e54caa3a6b/generateAnswer";
		String jsonInputString = "";
		String authorizationKey = "";
		String formattedString = "";
		URL url = null;
		HttpURLConnection con = null;
				
		try {
			
			// On-forwards a POST request to the MZ Azure Chatbot.
			url = new URL(urlString);
			con = (HttpURLConnection) url.openConnection();
			
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("Authorization", authorizationKey);
			con.setDoOutput(true);
			
			jsonInputString = "{\"question\":\"" + question + "\"}";
			
			OutputStream os = con.getOutputStream();
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);	
			
			// Interprets the response from the Chatbot
		    JSONParser parser = new JSONParser();
		    JSONObject json = (JSONObject) parser.parse(new InputStreamReader(con.getInputStream(), "utf-8"));
		    
		    JSONArray jsonArray = (JSONArray)json.get("answers");
		    JSONObject jsonObj = (JSONObject) jsonArray.get(0);
		    
		    formattedString = jsonObj.get("answer").toString();

		}
		catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
		catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		catch (ParseException e) {
			System.out.println(e.getMessage());
		}
	
		return new ChatBot(counter.incrementAndGet(), formattedString);
	}
}