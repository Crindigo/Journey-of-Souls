/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josmud.constants;

/**
 *
 * @author jprivett
 */
public class Colors {
	public static final String ESC = Character.toString((char) 27);
	public static final String ANSI_WHITE = ESC + "[37m";
	public static final String ANSI_RED = ESC + "[31m";
	public static final String ANSI_GREEN = ESC + "[32m";
	public static final String ANSI_YELLOW = ESC + "[33m";
	public static final String ANSI_BLUE = ESC + "[34m";
	public static final String ANSI_MAGENTA = ESC + "[35m";
	public static final String ANSI_CYAN = ESC + "[36m";
	public static final String ANSI_BLACK = ESC + "[30m";
	public static final String ANSI_NORMAL = ESC + "[39m";

	public static final String ANSI_HIGH_WHITE = ESC + "[97m";
	public static final String ANSI_HIGH_RED = ESC + "[91m";
	public static final String ANSI_HIGH_GREEN = ESC + "[92m";
	public static final String ANSI_HIGH_YELLOW = ESC + "[93m";
	public static final String ANSI_HIGH_BLUE = ESC + "[94m";
	public static final String ANSI_HIGH_MAGENTA = ESC + "[95m";
	public static final String ANSI_HIGH_CYAN = ESC + "[96m";
	public static final String ANSI_HIGH_BLACK = ESC + "[90m";
	public static final String ANSI_HIGH_NORMAL = ESC + "[99m";

	public static final String ANSI_CLEAR = ESC + "[0m";
	public static final String ANSI_BOLD = ESC + "[1m";
	public static final String ANSI_ITALIC = ESC + "[3m";
	public static final String ANSI_UNDERLINE = ESC + "[4m";
	public static final String ANSI_BLINK = ESC + "[5m";

}
