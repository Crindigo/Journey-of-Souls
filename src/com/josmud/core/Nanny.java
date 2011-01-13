/**
 * Journey of Souls
 * Copyright (c) 2010 - 2011, Jeremy Privett
 * All rights reserved.
 *
 * Licensed under the New BSD License. See LICENSE.txt for details.
 */

package com.josmud.core;

import com.josmud.constants.*;

/**
 *
 * @author jprivett
 */
public final class Nanny {
	private Descriptor descriptor = null;

	public Nanny(Descriptor d)
	{
		this.descriptor = d;
		this.descriptor.status = ConnectionStatus.GET_USERNAME;
	}

	public void processWorkflow()
	{
		this.processWorkflow(null);
	}

	public void processWorkflow(String input)
	{
		switch ( this.descriptor.status )
		{
			case ConnectionStatus.GET_USERNAME:
				this.descriptor.writeToBuffer("What does yo' usahname be? ");
			break;
		}
	}

	public void sendWelcomeMessage()
	{
		this.descriptor.writeToBuffer("Hi. Welcome to JoS. Don't be a " + Colors.ANSI_RED + "stupid troll" + Colors.ANSI_CLEAR + " and have a pleasant stay.", true);
	}
}
