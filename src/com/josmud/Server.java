package com.josmud;

import java.net.*;
import java.util.HashMap;

public class Server extends Thread {

	public int totalConnections = 0;
	public HashMap descriptors = new HashMap();

	private int port = 0;
	private ServerSocket sock = null;
	private Socket clientSock = null;
	
	private static Server reference = null;
	
	private Server() {}
	
	public static synchronized Server getInstance()
	{
		if ( reference == null ) {
			reference = new Server();
		}
		
		return reference;
	}

	public void setPort(int port)
	{
		this.port = port;
	}

	@Override
	public void run()
	{
		
	}
	
	@Override
	public Object clone()
		throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
	}
}
