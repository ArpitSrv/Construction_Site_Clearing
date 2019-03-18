package com.oracle.assignment.construction.test.controller;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.oracle.assignment.construction.calculation.CalculateUnitsOfOperations;
import com.oracle.assignment.construction.controller.SiteSimulationController;
import com.oracle.assignment.construction.controller.SiteSimulationControllerHelper;
import com.oracle.assignment.construction.model.Bulldozer;
import com.oracle.assignment.construction.model.Layout;

public class TestEndOfSimulation {

	// sampleInput1 to test end of simulation in case there is an attempt to
	// navigate beyond the boundaries of the site;
	protected String sampleInput1 = "a 11";
	// sampleInput2 there is an attempt to remove a tree that is protected
	protected String sampleInput2 = "a 8 r a 3";
	// sampleInput3 to test end of simulation in case of 'q' present in the
	// input
	protected String sampleInput3 = "a 4 l r q r";
	
	Layout layout = SiteSimulationControllerHelper.populateLayoutFromInputFile();
	CalculateUnitsOfOperations calculateUnitOfOperations = new CalculateUnitsOfOperations();
	Bulldozer bulldozer = new Bulldozer(layout,calculateUnitOfOperations);
	
	@Test
	public void testInput1() throws IOException {
		boolean test1 = SiteSimulationController.processUserCommand(bulldozer, sampleInput1);
		assertTrue(test1);
	}

	@Test
	public void testInput2() throws IOException {
		boolean test2 = SiteSimulationController.processUserCommand(bulldozer, sampleInput2);
		assertTrue(test2);

	}

	@Test
	public void testInput3() throws IOException {
		boolean test3 = SiteSimulationController.processUserCommand(bulldozer, sampleInput3);
		assertTrue(test3);

	}

}
