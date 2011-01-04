/**
 * Journey of Souls
 * Copyright (c) 2010 - 2011, Jeremy Privett
 * All rights reserved.
 *
 * Licensed under the New BSD License. See LICENSE.txt for details.
 */

package com.josmud;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import com.josmud.core.Database;

public class Game {

	public static Logger logger = Logger.getLogger("com.josmud");
	public static Properties config = new Properties();

	public static boolean isUp = false;
	
	/**
	 * This is the main function. This is where it all begins. Yeah.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		PropertyConfigurator.configure("config/log4j.properties");
		logger.info("Starting Journey of Souls ...");

		logger.info("Loading Game Configuration ...");
		try {
			config.load(new FileInputStream("config/josmud.properties"));
		}
		catch (IOException e) {
			logger.fatal("Unable to load 'josmud.properties' - Exiting.");
			System.exit(-1);
		}
		logger.info("Game Configuration loaded.");

		logger.info("(TODO) Initializing Database ...");
		logger.info("(TODO) Building Command Table ...");
		logger.info("(TODO) Building Socials Table ...");
		logger.info("(TODO) Building Race Table ...");
		logger.info("(TODO) Loading Game Areas ...");
		logger.info("(TODO) Loading Rooms ...");
		logger.info("(TODO) Linking Rooms and Loading Exits ...");
		logger.info("(TODO) Shuffling Room Positions ...");
		logger.info("(TODO) Initializing Short Direction Names ...");

		Database db = new Database();

		try {
			db.connect();
			db.doStuff();
			db.close();
		} catch ( Exception e ) {
			logger.fatal("DB Error: " + e.getMessage());
			System.exit(-1);
		}

		try {
			logger.info("Initializing Game Server Socket ...");
			Server gameServer = Server.getInstance();
			int serverPort = Integer.parseInt(config.getProperty("server.port"));
			gameServer.setPort(serverPort);
			gameServer.start();
		}
		catch ( Exception e ) {
			logger.fatal("Problem creating Server Socket: " + e.getMessage());
			System.exit(-1);
		}

		logger.info("(TODO) Starting Game Loop ...");
		logger.info("JoS is up and ready to rock on port " + config.getProperty("server.port") + "!");
	}

}
