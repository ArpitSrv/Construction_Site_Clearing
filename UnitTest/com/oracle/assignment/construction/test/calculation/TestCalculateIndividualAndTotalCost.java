package com.oracle.assignment.construction.test.calculation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.oracle.assignment.construction.calculation.CalculateIndividualAndTotalCost;
import com.oracle.assignment.construction.calculation.CalculateUnitsOfOperations;
import com.oracle.assignment.construction.controller.SiteSimulationControllerHelper;
import com.oracle.assignment.construction.model.Bulldozer;
import com.oracle.assignment.construction.model.Layout;

/**
 * Test class to test calculation of costs of different operations during
 * simulation. Different Operational Costs are defined below -
 * 
 * 1)Item communication overhead per command sent to bulldozer 1 credit per unit
 * 2)operator fuel 1 credit per unit 3)uncleared square at end of simulation 3
 * credits per unit 4)destruction of protected tree repairing 10 credits per
 * unit 5)paint damage to bulldozer cleared tree(t) 2 credits per unit
 * 
 * @author Arpit Srivastava
 * 
 */

public class TestCalculateIndividualAndTotalCost {

	protected String sampleInput[][] = {
			{ "o", "o", "t", "r", "T", "x", "o", "o", "o", "o" },
			{ "o", "o", "o", "o", "o", "o", "o", "T", "o", "o" },
			{ "r", "r", "r", "o", "o", "o", "o", "T", "o", "o" },
			{ "r", "r", "r", "r", "o", "o", "o", "o", "o", "o" },
			{ "r", "r", "r", "r", "r", "t", "o", "o", "o", "o" } };

	// Command is advance 6 quit
	private int sampleCommOverheadPerCommandCost = 1 * 1;
	private int sampleFuelUnitsCost = 9 * 1;
	private int sampleUnclearedSquareCost = 42 * 3;
	private int sampleDestructionOfProtectedTreeCost = 1 * 10;
	private int sampleRepairingPaintDamageCost = 1 * 2;
	private int sampleTotalCost = 1 + 9 + 126 + 10 + 2;

	Layout layout = SiteSimulationControllerHelper.populateLayoutFromInputFile();
	CalculateUnitsOfOperations calculateUnitOfOperations = new CalculateUnitsOfOperations();
	Bulldozer bulldozer = new Bulldozer(layout,calculateUnitOfOperations);
	CalculateIndividualAndTotalCost unitsOfOpsCost = new CalculateIndividualAndTotalCost(bulldozer);

	@Test
	public void test1() {

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

		assertEquals(sampleCommOverheadPerCommandCost,
				unitsOfOpsCost.getCommunicationOverheadCost());
		assertEquals(sampleFuelUnitsCost, unitsOfOpsCost.getFuelCost());
		assertEquals(sampleDestructionOfProtectedTreeCost,
				unitsOfOpsCost.getDestructionOfProtectedTreeCost());
		assertEquals(sampleRepairingPaintDamageCost,
				unitsOfOpsCost.getRepairingPaintDamageCost());
		assertEquals(sampleUnclearedSquareCost,
				unitsOfOpsCost.getUnclearedSquareCost());
		assertEquals(sampleTotalCost,
				unitsOfOpsCost.getTotalCost());
		
	}

}
