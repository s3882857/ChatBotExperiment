import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/*
 * This is an all in one conversation with the Chatbot service.
 * Sends a POST request to MZ Azure chatbot. Interprets the response
 * and display it to the user. Console driven only.
 * Feel free to use this as a guide.
 * The Service at the moment is free, however that will
 * not last much longer. If your are trying to use this you may
 * have to investigate getting another QnA AI Service free account through
 * Microsoft. The account will only last a little over a month. This was setup
 * sometime in April 2021.
 */
public class ChatBot {

	private Scanner sc;
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ChatBot cb = new ChatBot();
	}
	
	public ChatBot() {
		
		String urlString = "https://covid19team14chatbot.azurewebsites.net/qnamaker/knowledgebases/ba2405ec-542a-4f2c-8511-72e54caa3a6b/generateAnswer";
		boolean running = true;
		String userQuestion = "";
		String jsonInputString = "";
		String formattedString = "";
		String authorizationKey = "";
		String line = "";
		URL url = null;
		HttpURLConnection con = null;
		this.sc = new Scanner(System.in);
		System.out.println("Hi I am the Covid 19 ChatBot");
		int maxChars = 120;
			
		while(running == true) {
		
			try {
				url = new URL(urlString);
				con = (HttpURLConnection) url.openConnection();
				
				con.setRequestMethod("POST");
				con.setRequestProperty("Content-Type", "application/json; utf-8");
				con.setRequestProperty("Accept", "application/json");
				con.setRequestProperty("Authorization", authorizationKey);
				con.setDoOutput(true);
				
				System.out.println("Ask me a question");
				
				userQuestion = this.sc.nextLine();
		
				if(!userQuestion.equalsIgnoreCase("bye")) {
					
					jsonInputString = "{\"question\":\"" + userQuestion + "\"}";
					
					OutputStream os = con.getOutputStream();
					byte[] input = jsonInputString.getBytes("utf-8");
					os.write(input, 0, input.length);	
					
				    JSONParser parser = new JSONParser();
				    JSONObject json = (JSONObject) parser.parse(new InputStreamReader(con.getInputStream(), "utf-8"));
				    
				    JSONArray jsonArray = (JSONArray)json.get("answers");
				    JSONObject jsonObj = (JSONObject) jsonArray.get(0);
				    
				    formattedString = jsonObj.get("answer").toString();
				   	
				    for (int i = 0; i<formattedString.length();i++) {
				    	
				    	if(i!=formattedString.length()-1) {
				    		line += formattedString.substring(i,i+1);
				    	}
				    	
				    	if(line.length()==maxChars) {
				    		System.out.println(line);
				    		line = "";
				    	}
				    					    	
				    }
				    
				    System.out.println(line);
				    line = "";

				}
				else {
					running = false;
				}
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
		}

	}
}
