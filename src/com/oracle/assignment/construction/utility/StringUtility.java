package com.oracle.assignment.construction.utility;

import org.apache.log4j.Logger;

import com.oracle.assignment.construction.config.LookupConfig;

public class StringUtility {
	
	static Logger log = Logger.getLogger(StringUtility.class.getName());
	/**
	 * Method to convert string to 2-d string array
	 * Sample Input - "ab,cd"
	 * Output for Sample - { { "a", "b" }, { "c", "d" } } 
	 * @param str
	 * @return
	 */
	public static String[][] stringToDoubleDimentionalArrayString(String str){
		
		final String METHOD_NAME = "stringToDoubleDimentionalArrayString";
		String[][] convertedString = null;
		if(str != null && str.length()!=0){
			String[] rows = str.split(",");
			convertedString = new String[rows.length][];
			int r = 0;
			for (String row : rows) {
				// The zero width negative lookahead prevents the pattern
				// matching at the start of the input,
				// so you don't get a leading empty string (space in beginning).
				convertedString[r++] = row.split("(?!^)");
			}
		}else{
			log.info(METHOD_NAME+"::String is having issues");
			System.out.println(LookupConfig.ILLEGAL_INPUT);
			System.exit(0);
		}
		
		log.info(METHOD_NAME+"::Exiting this method");			
		return convertedString;
	}
}
