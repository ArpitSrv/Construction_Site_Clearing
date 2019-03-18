package com.oracle.assignment.construction.test.calculation;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.oracle.assignment.construction.calculation.CalculateUnitsOfOperations;
import com.oracle.assignment.construction.controller.SiteSimulationControllerHelper;
import com.oracle.assignment.construction.model.Bulldozer;
import com.oracle.assignment.construction.model.Layout;

/**
 * Test class to test units of different operations during simulation. Different
 * Operational Units are defined below -
 * 
 * Item communication overhead per command sent to bulldozer operator fuel
 * uncleared square at end of simulation destruction of protected tree repairing
 * paint damage to bulldozer cleared tree(t)
 * 
 * @author asrivasta212
 * 
 */

public class TestUnitsOfOperations {

	protected String sampleInput[][] = {
			{ "o", "o", "t", "r", "T", "x", "o", "o", "o", "o" },
			{ "o", "o", "o", "o", "o", "o", "o", "T", "o", "o" },
			{ "r", "r", "r", "o", "o", "o", "o", "T", "o", "o" },
			{ "r", "r", "r", "r", "o", "o", "o", "o", "o", "o" },
			{ "r", "r", "r", "r", "r", "t", "o", "o", "o", "o" } };

	// Command is advance 6 quit
	protected int sampleCommOverheadPerCommand = 1;
	protected int sampleFuelUnits = 9;
	protected int sampleUnclearedSquare = 42;
	protected int sampleDestructionOfProtectedTree = 1;
	protected int sampleRepairingPaintDamage = 1;

	Layout layout = SiteSimulationControllerHelper.populateLayoutFromInputFile();
	CalculateUnitsOfOperations calculateUnitOfOperations = new CalculateUnitsOfOperations();
	Bulldozer bulldozer = new Bulldozer(layout,calculateUnitOfOperations);
	
	@Test
	public void testCommOverheadPerCommand() throws IOException {
		// Simulated Input
		// Setting number of protected trees in site and increamenting
		// communication overhead for 'a 6' command
		bulldozer.getLayout().setNumberOfProtectedTreesInSite(3);
		calculateUnitOfOperations.incrementCommunicationOverhead();

		// Move 1
		calculateUnitOfOperations.addFuelUnits(sampleInput[0][0].charAt(0));
		calculateUnitOfOperations.unclearedLandDecrementor();

		// Move 2
		calculateUnitOfOperations.addFuelUnits(sampleInput[0][1].charAt(0));
		calculateUnitOfOperations.unclearedLandDecrementor();

		// Move 3
		calculateUnitOfOperations.addFuelUnits(sampleInput[0][2].charAt(0));
		calculateUnitOfOperations.repairingPaintDamageIncreamenter();
		calculateUnitOfOperations.unclearedLandDecrementor();

		// Move 4
		calculateUnitOfOperations.addFuelUnits(sampleInput[0][3].charAt(0));
		calculateUnitOfOperations.unclearedLandDecrementor();

		// Move 5
		calculateUnitOfOperations.addFuelUnits(sampleInput[0][4].charAt(0));
		calculateUnitOfOperations.destructionOfProtectedTreeIncreamenter();
		calculateUnitOfOperations.unclearedLandDecrementor();

		// Move 6
		calculateUnitOfOperations.addFuelUnits(sampleInput[0][5].charAt(0));

		assertEquals(sampleCommOverheadPerCommand,
				calculateUnitOfOperations.getCommunicationOverhead());
		assertEquals(sampleFuelUnits, calculateUnitOfOperations.getTotalFuelUnits());
		assertEquals(sampleDestructionOfProtectedTree, calculateUnitOfOperations
				.getDestructionOfProtectedTree());
		assertEquals(sampleRepairingPaintDamage,
				calculateUnitOfOperations.getRepairingPaintDamage());
		assertEquals(sampleUnclearedSquare, calculateUnitOfOperations
				.getUnclearedSquare());

	}

}
