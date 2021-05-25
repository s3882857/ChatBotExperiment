
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Test {

	private Scanner sc;
	
	public static void main(String[] args) {
		Test test = new Test();
	}
	
	public Test() {
		
		boolean running = true;
		
		// The service running locally on PC. Need to build a professional
		// service to run on the cloud. Our own hosting within MZ Azure??
		// Github will not suit requirements also slow. Need dedicated service.
		String urlString = "http://localhost:8080/Covid19ChatBot?question=";
		String userQuestion = "";
		URL url = null;
		HttpURLConnection con = null;
		this.sc = new Scanner(System.in);
		
		System.out.println("Hi I am the Covid 19 ChatBot");
					
		while(running == true) {
			
			try {
				
				System.out.println("\nAsk me a question");
				userQuestion = this.sc.nextLine();
				
				// Exit condition.
				if(!userQuestion.equalsIgnoreCase("bye")) {
					
					// replace all the spaces between each word as this cannot be parsed 
					// otherwise.
					// This is a GET request so add all data to the URL string request.
					url = new URL(urlString + userQuestion.replaceAll(" ", "%20") );
					con = (HttpURLConnection) url.openConnection();
					
					con.setRequestMethod("GET");
					con.setRequestProperty("Content-Type", "application/json; utf-8");
					con.setRequestProperty("Accept", "application/json");
					con.setDoOutput(true);
					
					// Get the response and display it.
					formatDisplayResponse(con);
					
				}
				else {
					// exit question loop
					running = false;
				}
				
				
			}
			catch (MalformedURLException e) {
				System.out.println("In MalformedURLException " + e.getMessage());
			}
			catch(IOException ioe) {
				System.out.println("In IOexception " + ioe.getMessage());
			}
			catch (ParseException e) {
				System.out.println("In ParseException " + e.getMessage());
			}
			
		}
	}
	
	// Using JSON this parses the response data and display it 
	// on multiple lines, otherwise it will display at one long
	// sentence across the console.
	public void formatDisplayResponse(HttpURLConnection con) throws ParseException,IOException {
		
		String contentResponse = "";
		String line = "";
		int maxChars = 120;
		
	    JSONParser parser = new JSONParser();
	    JSONObject json = (JSONObject) parser.parse(new InputStreamReader(con.getInputStream(), "utf-8"));
	    	    
	    contentResponse = json.get("content").toString();
	    
	    for (int i = 0; i<contentResponse.length();i++) {
	    	
	    	if(i!=contentResponse.length()-1) {
	    		line += contentResponse.substring(i,i+1);
	    	}
	    	
	    	if(line.length()==maxChars) {
	    		System.out.println(line);
	    		line = "";
	    	}
	    					    	
	    }
	    
	    System.out.println(line);
	    line = "";
	    
	}
}

/*
inputString = "{\"id\":1,\"content\":\"Hello, World!\"}";
System.out.println("In TEST inputString = " + inputString);	
OutputStream os = con.getOutputStream();
byte[] input = inputString.getBytes("utf-8");
os.write(input, 0, input.length);	
System.out.println("Message sent");

try {
	
	BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8")); 

    StringBuilder response = new StringBuilder();
    System.out.println("Getting response ");
    while ((line = in.readLine()) != null) {
        response.append(line);
    }
    System.out.println("Got response");
    System.out.println(response.toString());

}
catch(IOException ioe) {
	System.out.println(ioe.getMessage());
}
    		    JSONArray jsonArray = (JSONArray)json.get("content");
			    JSONObject jsonObj = (JSONObject) jsonArray.get(0);
*/