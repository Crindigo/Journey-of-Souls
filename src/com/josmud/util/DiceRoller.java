package com.josmud.util;

import java.util.Random;

public class DiceRoller {
	
	protected static Random r = new Random();
	
	/**
	 * Returns a summed result of a set of thrown dice. Accepts a String in the
	 * format of 'XdY' where X is the number of dice you want to roll and Y is
	 * the size of the die.
	 * 
	 * @param dice
	 * @return
	 */
	public static int rollDice(String dice)
	{
		int result = 0;
		
		if ( !dice.contains("d") ) {
			return 0;
		}
		
		String[] diceData = dice.split("d", 2);
		int number = Integer.parseInt(diceData[0]);
		int size   = Integer.parseInt(diceData[1]);
		
		if ( size == 0 ) {
			result = 0;
		}
		else if ( size == 1 ) {
			result = number;
		}
		else {
			for ( int j = 0; j < number; ++j ) {
				result += r.nextInt(size) + 1;
			}
		}
		
		return result;
	}
	
	/**
	 * Returns an array of results of a set of thrown dice. Accepts an array of
	 * Strings in the format of 'XdY' where X is the number of dice you want
	 * to roll and Y is the size of the die.
	 * 
	 * @param dice
	 * @return
	 */
	public static int[] rollDice(String[] dice)
	{
		if ( dice == null || dice.length == 0 ) {
			return new int[] {0};
		}
		
		int[] results = new int[dice.length];
		
		for ( int i = 0; i < dice.length; ++i ) {
			results[i] = rollDice(dice[i]);
		}
		
		return results;
	}
	
	/**
	 * Returns a summed result of a set of thrown dice. Accepts an array of
	 * Strings in the format of 'XdY' where X is the number of dice you want
	 * to roll and Y is the size of the die.
	 * 
	 * @param dice
	 * @return
	 */
	public static int rollDiceWithSum(String[] dice)
	{
		if ( dice == null || dice.length == 0 ) {
			return 0;
		}
		
		int sum = 0;
		
		for ( int i = 0; i < dice.length; ++i )
		{
			sum += rollDice(dice[i]);
		}
		
		return sum;
	}
	
}
