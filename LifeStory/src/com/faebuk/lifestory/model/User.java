package com.faebuk.lifestory.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.os.AsyncTask;
import android.util.Log;


public class User extends  AsyncTask<String, Void, String>{
	
	int userid;
	String username = null;
	String email = null;
	String apikey = null;
	String createdat = null;
	Boolean loggedin = false;
	
	
	public User(){
		
	}
	
	@Override
	protected String doInBackground(String... params) {
		// Creating HTTP client
		HttpClient httpClient = new DefaultHttpClient();
		 
		// Creating HTTP Post
		HttpPost httpPost = new HttpPost("http://faebuk.ch/lifestoryapp/v1/login");
		
		// Building post parameters, key and value pair
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
		nameValuePair.add(new BasicNameValuePair("username", "faebuk"));
		nameValuePair.add(new BasicNameValuePair("password", "1234"));
		
		// Url Encoding the POST parameters
		try {
		    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
		}
		catch (UnsupportedEncodingException e) {
		    // writing error to Log
		    e.printStackTrace();
		}
		
		// Making HTTP Request
		try {
		    HttpResponse response = httpClient.execute(httpPost);
		 
		    // writing response to log
		    Log.d("Http Response:", response.toString());
		 
		} catch (ClientProtocolException e) {
		    // writing exception to log
		    e.printStackTrace();
		         
		} catch (IOException e) {
		    // writing exception to log
		    e.printStackTrace();
		}
		
		//return loggedin;
		
		/*String urlParameters = "username="+username+"&password="+password;
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
			this.userid = this.longToInt((Long) jsonObject.get("id_user")) ;
			this.loggedin = true;
			return true;
		}*/
		return null;
	}
	
//	public Boolean login(String username, String password) throws IOException, ParseException {		
//		
//		// Creating HTTP client
//		HttpClient httpClient = new DefaultHttpClient();
//		 
//		// Creating HTTP Post
//		HttpPost httpPost = new HttpPost("http://faebuk.ch/lifestoryapp/v1/login");
//		
//		// Building post parameters, key and value pair
//		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
//		nameValuePair.add(new BasicNameValuePair("username", "faebuk"));
//		nameValuePair.add(new BasicNameValuePair("password", "1234"));
//		
//		// Url Encoding the POST parameters
//		try {
//		    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
//		}
//		catch (UnsupportedEncodingException e) {
//		    // writing error to Log
//		    e.printStackTrace();
//		}
//		
//		// Making HTTP Request
//		try {
//		    HttpResponse response = httpClient.execute(httpPost);
//		 
//		    // writing response to log
//		    Log.d("Http Response:", response.toString());
//		 
//		} catch (ClientProtocolException e) {
//		    // writing exception to log
//		    e.printStackTrace();
//		         
//		} catch (IOException e) {
//		    // writing exception to log
//		    e.printStackTrace();
//		}
//		
//		return loggedin;
		
		/*String urlParameters = "username="+username+"&password="+password;
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
			this.userid = this.longToInt((Long) jsonObject.get("id_user")) ;
			this.loggedin = true;
			return true;
		}*/
	
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
	
	protected int getUserId(){
		return this.userid;
	}
	
	protected ArrayList<Post> getPosts(){
		return null;
	}
	
	public int longToInt(Long longVariable){
		return Integer.valueOf(longVariable.toString()); 
	}
}


