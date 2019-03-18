package com.oracle.assignment.construction.test.utility;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.oracle.assignment.construction.utility.StringUtility;

public class TestStringUtility {

	String[][] array = { { "a", "b" }, { "c", "d" } };
	String str = "ab,cd";
	// assigning the values
	protected void setUp() {
		// To-DO
	}

	@Test
	public void testStringToStringDoubleArray() throws IOException {
		String[][] result = StringUtility.stringToDoubleDimentionalArrayString(str);
		for(int i=0;i<2;i++){
			assertEquals(array[i][i],result[i][i]);
		}
		
	}
	
}
