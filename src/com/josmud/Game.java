package com.josmud;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Game {

	public static Logger logger = Logger.getLogger("com.josmud");
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		logger.info("Starting Journey of Souls ...");
	}

}
