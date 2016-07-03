package org.jay.frame.exception;

public class PageNotFoundException extends GenericException{
	public PageNotFoundException(){
		super("404 page not found");
	}
	
	public PageNotFoundException(String msg){
		super(msg);
	}
	
}
