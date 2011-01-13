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
	private Nanny nanny = null;
	private BufferedReader input = null;
	private DataOutputStream output = null;

	private String inputBuffer  = "";
	private String outputBuffer = "";
	private boolean isConnected = false;
	private int ticks = 0;

	public int status = -1;

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
		this.clientSock.setKeepAlive(true);
		this.input = new BufferedReader(new InputStreamReader(this.clientSock.getInputStream()));
		this.output = new DataOutputStream(this.clientSock.getOutputStream());
		this.isConnected = true;
	}

	public void readFromDescriptor()
	{
		if ( !this.inputBuffer.isEmpty() ) {
			return;
		}

		try {
			if ( this.input.ready() ) {
				Game.logger.debug("Descriptor is ready! Reading a line!");
				this.inputBuffer = this.input.readLine();
				this.ticks = 0;
			}
		}
		catch ( Exception e )  {
			Game.logger.warn("Error reading from descriptor: " + e.getMessage());
			this.close();
		}
	}

	public void writeToBuffer(String s)
	{
		this.outputBuffer += s;
	}

	public void writeToBuffer(String s, boolean endOfLine)
	{
		if (endOfLine) {
			this.writeToBuffer(s + "\r\n");
		}
		else {
			this.writeToBuffer(s);
		}
	}

	public void writeToDescriptor()
	{
		if ( this.outputBuffer.isEmpty() ) {
			return;
		}

		try {
			this.output.writeBytes(this.outputBuffer);
			this.outputBuffer = "";
		}
		catch ( Exception e ) {
			Game.logger.warn("Problem writing to descriptor: " + e.getMessage());
			this.close();
		}
	}

	private void processInput()
	{
		if ( this.inputBuffer.isEmpty() ) {
			return;
		}
		
		/**
		 * The Nanny object is used to work new connections through the process
		 * of logging in or registering new users.
		 */
		if ( this.nanny != null ) {
			this.nanny.processWorkflow(this.inputBuffer);
			this.inputBuffer = "";
			return;
		}
	}

	private void sleepForAWhile()
	{
		try {
			Thread.sleep(Pulse.TICK);
			this.ticks += Pulse.TICK;

			if ( this.ticks > 3000 ) {
				if ( !this.isSocketConnected() ) {
					this.close();
				}
				
				this.ticks = 0;
			}
		}
		catch ( Exception e ) {}
	}

	private boolean isSocketConnected()
	{
		try {
			this.output.writeChar(0);
			return true;
		}
		catch ( Exception e ) {
			Game.logger.warn("Descriptor disconnected unexpectedly.");
			return false;
		}
	}

	public void close()
	{
		try {
			Game.logger.info("Closing Descriptor and Stopping Thread identified by '" + this.getName() + "'.");
			this.clientSock.close();
		}
		catch ( Exception e ) {}

		this.isConnected = false;
	}

	@Override
	public void run()
	{
		Game.logger.info("Spinning up new thread for Descriptor: " + this.clientSock.getRemoteSocketAddress().toString());
		this.nanny = new Nanny(this);
		this.nanny.sendWelcomeMessage();
		this.nanny.processWorkflow();
		
		while ( this.isConnected ) {
			this.readFromDescriptor();
			this.processInput();
			this.writeToDescriptor();
			this.sleepForAWhile();
		}
	}

	@Override
	public Object clone()
		throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
	}
}
