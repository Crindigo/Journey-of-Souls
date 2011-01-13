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
import javax.script.*;

public class Game extends Thread {

	public static Logger logger = Logger.getLogger("com.josmud");
	public static Properties config = new Properties();
	public static Compilable jsCompiler = (Compilable) (new ScriptEngineManager()).getEngineByName("JavaScript");

	public static boolean isUp = false;

	private Game() {}
	
	/**
	 * This is the main function. This is where it all begins. Yeah.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		
		logger.info("Loading Game Configuration ...");
		try {
			config.load(new FileInputStream("josmud.properties"));
		}
		catch (IOException e) {
			logger.fatal("Unable to load 'josmud.properties' - Exiting.");
			System.exit(-1);
		}
		logger.info("Game Configuration loaded.");

		logger.info("Starting " + config.getProperty("game.name") + " ...");

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
			Server gameServer = Server.getInstance();
			gameServer.setName("ServerThread");
			int serverPort = Integer.parseInt(config.getProperty("server.port"));
			gameServer.setPort(serverPort);
			gameServer.start();
		}
		catch ( Exception e ) {
			logger.fatal("Problem creating Server Socket: " + e.getMessage());
			System.exit(-1);
		}

		Game game = new Game();
		game.setName("GameLoopThread");
		game.start();

		logger.info(config.getProperty("game.shortname") + " is up and ready to rock on port " + config.getProperty("server.port") + "!");
	}
	
	public void gameLoop()
	{
		logger.info("Starting Game Loop ...");
	}

	@Override
	public void run()
	{
		this.gameLoop();
	}

	@Override
	public Object clone()
		throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
	}
}
