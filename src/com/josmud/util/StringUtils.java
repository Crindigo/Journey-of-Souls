
package com.josmud.util;

/**
 *
 * @author Steven
 */
public class StringUtils {

	static public String implode(String separator, Object[] strs) {
		String out = "";
		if ( strs.length > 0 ) {
			// guess a default size here?
			//String str0 = String.valueOf(strs[0]);
			//int capacity = strs.length * str0.length() / 2;
			StringBuilder sb = new StringBuilder();
			sb.append(strs[0]);

			for ( int i = 1; i < strs.length; i++ ) {
				sb.append(separator).append(strs[i]);
			}

			out = sb.toString();
		}

		return out;
	}
}
