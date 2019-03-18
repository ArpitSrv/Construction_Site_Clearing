package com.oracle.assignment.construction.test.calculation;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.oracle.assignment.construction.calculation.CalculateUnitsOfOperations;
import com.oracle.assignment.construction.controller.SiteSimulationControllerHelper;
import com.oracle.assignment.construction.model.Bulldozer;
import com.oracle.assignment.construction.model.Layout;

public class TestMarkFieldAsCovered {
	// Map
	protected String sampleInput[][] = {
			{ "o", "o", "t", "o", "o", "o", "o", "o", "o", "o" },
			{ "o", "o", "o", "o", "o", "o", "o", "T", "o", "o" },
			{ "r", "r", "r", "o", "o", "o", "o", "T", "o", "o" },
			{ "r", "r", "r", "r", "o", "o", "o", "o", "o", "o" },
			{ "r", "r", "r", "r", "r", "t", "o", "o", "o", "o" } };

	protected String sampleOutput[][] = {
			{ "x", "x", "x", "x", "o", "o", "o", "o", "o", "o" },
			{ "o", "o", "o", "o", "o", "o", "o", "T", "o", "o" },
			{ "r", "r", "r", "o", "o", "o", "o", "T", "o", "o" },
			{ "r", "r", "r", "r", "o", "o", "o", "o", "o", "o" },
			{ "r", "r", "r", "r", "r", "t", "o", "o", "o", "o" } };
	// User Input
	protected int startXAxis = -1;
	protected int endXAxis = 3;
	protected int startYAxis = 0;
	protected int endYAxis = 0;

	Layout layout = SiteSimulationControllerHelper.populateLayoutFromInputFile();
	CalculateUnitsOfOperations calculateUnitOfOperations = new CalculateUnitsOfOperations();
	Bulldozer bulldozer = new Bulldozer(layout,calculateUnitOfOperations);
	
	@Test
	public void testInput() throws IOException {
		layout.markFieldAsCovered(calculateUnitOfOperations, startXAxis, endXAxis,startYAxis, endYAxis);
		String[][] result = bulldozer.getLayout().getConstructionSiteLayout();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 10; j++) {
				assertEquals(sampleOutput[i][j], result[i][j]);
			}
		}

	}

}
