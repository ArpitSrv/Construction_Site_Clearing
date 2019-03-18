package com.oracle.assignment.construction.test.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.oracle.assignment.construction.controller.SiteSimulationControllerHelper;
import com.oracle.assignment.construction.model.Layout;

/*This test is used to assert the field map given as an input from file
 * 
 */

public class TestInput {

	protected String sampleInput[][] = {
			{ "o", "o", "t", "o", "o", "o", "o", "o", "o", "o" },
			{ "o", "o", "o", "o", "o", "o", "o", "T", "o", "o" },
			{ "r", "r", "r", "o", "o", "o", "o", "T", "o", "o" },
			{ "r", "r", "r", "r", "o", "o", "o", "o", "o", "o" },
			{ "r", "r", "r", "r", "r", "t", "o", "o", "o", "o" } };
	Layout layout = SiteSimulationControllerHelper.populateLayoutFromInputFile();

	// assigning the values
	protected void setUp() {
		// To-DO
	}

	@Test
	public void testInput() throws IOException {
		String[][] result = layout.getConstructionSiteLayout();
		for (int i = 0; i < layout.getDepth(); i++) {
			for (int j = 0; j < layout.getWidth(); j++) {
				assertEquals(result[i][j], sampleInput[i][j]);
			}
		}

	}

}
