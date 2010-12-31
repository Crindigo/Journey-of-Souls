package com.josmud;

import java.net.*;
import java.util.HashMap;
import java.util.List;

public class Server extends Thread {
	
	private static Server reference = null;
	
	private Server() {}
	
	public static synchronized Server getInstance()
	{
		if ( reference == null ) {
			reference = new Server();
		}
		
		return reference;
	}
	
	public Object clone()
		throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
	}
}
