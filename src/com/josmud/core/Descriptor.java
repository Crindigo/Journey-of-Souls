/**
 * Journey of Souls
 * Copyright (c) 2010 - 2011, Jeremy Privett
 * All rights reserved.
 *
 * Licensed under the New BSD License. See LICENSE.txt for details.
 */

package com.josmud.core;

import java.io.*;
import java.net.*;

import com.josmud.Game;
import com.josmud.constants.*;

public class Descriptor extends Thread {

	private Socket clientSock = null;
	private BufferedReader input = null;
	private DataOutputStream output = null;

	/**
	 * Constructor creates a new Descriptor and attaches the client socket to
	 * it. Also gets the input and output streams for the client socket. We
	 * allow the IOException to bubble up if it happens to prevent a thread
	 * from being spun up for a failed client connect.
	 *
	 * @param clientSock
	 * @throws IOException
	 */
	public Descriptor(Socket clientSock)
		throws IOException
	{
		this.clientSock = clientSock;
		this.input = new BufferedReader(new InputStreamReader(this.clientSock.getInputStream()));
		this.output = new DataOutputStream(this.clientSock.getOutputStream());
	}

	@Override
	public void run()
	{
		Game.logger.info("Spinning up new thread for Descriptor: " + this.clientSock.getRemoteSocketAddress().toString());
		
		try {
			String s = this.input.readLine();
			this.output.writeChars("Thanks for connecting! You said: " + s + "\r\n");
			this.input.close();
			this.output.close();
			this.clientSock.close();
		}
		catch ( Exception e ) {
			Game.logger.warn("Lost connection to Descriptor?");
		}
	}

	@Override
	public Object clone()
		throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
	}
}
