package com.josmud;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class Game {

	public static Logger logger = Logger.getLogger("com.josmud");
	public static Properties config = new Properties();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		logger.info("Starting Journey of Souls ...");

		logger.info("Loading Game Configuration ...");
		try {
			config.load(new FileInputStream("josmud.properties"));
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
		logger.info("(TODO) Initializing Game Server Socket ...");
		logger.info("(TODO) Starting Game Loop ...");
		logger.info("(TODO) JoS is up and ready to rock on port 4444!");
	}

}
