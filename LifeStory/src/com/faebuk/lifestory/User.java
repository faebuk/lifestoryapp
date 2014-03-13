import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class User {
	
	int userid;
	String username = null;
	String email = null;
	String apikey = null;
	String createdat = null;
	Boolean loggedin = false;
	
	
	public User(){
		
	}
	
	protected Boolean login(String username, String password) throws IOException, ParseException {		
		String urlParameters = "username="+username+"&password="+password;
		URL url = new URL("http://faebuk.ch/lifestoryapp/v1/login");
		URLConnection conn = url.openConnection();
		
		conn.setDoOutput(true);
		
		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
		
		writer.write(urlParameters);
		writer.flush();
		
		String line;
		String lines = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		while ((line = reader.readLine()) != null) 
			lines = line;
		
		writer.close();
		reader.close();
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(lines);
		
		Boolean error = (Boolean) jsonObject.get("error");
		
		if (error){ 
			this.loggedin = false;
			return false;	
		}
		else{ 
			this.apikey = (String) jsonObject.get("apiKey");
			this.createdat = (String) jsonObject.get("createdAt");
			this.email = (String) jsonObject.get("email");
			this.username = (String) jsonObject.get("username");
			this.loggedin = true;
			return true;
		}
	}
	
	protected String getUsername(){
		return this.username;
	}
	
	protected String getEmail(){
		return this.email;
	}
	
	protected String getApiKey(){
		return this.apikey;
	}
	
	protected String getCreatedAt(){
		return this.createdat;
	}
	
	protected Boolean isLoggedIn(){
		if(this.loggedin)
			return true;
		return false;
	}
	
	protected ArrayList<Post> getPosts(){
		return null;
	}
}
