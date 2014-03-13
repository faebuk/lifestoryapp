package com.faebuk.lifestory.model;

import java.util.Date;


public class Post {
	
	int postid;
	String datum;
	String title;
	String description;
	Media media;
	User user;
	
	public Post(){
		
	}
	
	protected String getTitle(){
		return null;
	}
	
	protected String getDescription(){
		return null;
	}
	
	protected Media getMedia(){
		return null;
	}
	
	protected User getUser(){
		return null;
	}
	
	protected Date getDate(){
		return null;
	}

}
