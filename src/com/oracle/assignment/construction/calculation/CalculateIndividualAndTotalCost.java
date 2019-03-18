package com.oracle.assignment.construction.calculation;

import org.apache.log4j.Logger;

import com.oracle.assignment.construction.config.LookupConfig;
import com.oracle.assignment.construction.model.Bulldozer;

/**
 * Class to calculate of costs of different operations during simulation.
 * Different Operational Costs are defined below -
 * 
 * 1)Item communication overhead per command sent to bulldozer 1 credit per unit
 * 2)operator fuel 1 credit per unit 3)uncleared square at end of simulation 3
 * credits per unit 4)destruction of protected tree repairing 10 credits per
 * unit 5)paint damage to bulldozer cleared tree(t) 2 credits per unit
 * 
 * @author Arpit Srivastava
 * 
 */

public class CalculateIndividualAndTotalCost {
	
	static Logger log = Logger.getLogger(CalculateIndividualAndTotalCost.class.getName());
	//Setting fixed multipliers for calculation of costs of all operations
	private final int communicationOverheadCostMultiplier = Integer
			.parseInt(LookupConfig.communicationOverheadCostMultiplier);
	private final int fuelUnitsCostMultiplier = Integer
			.parseInt(LookupConfig.fuelUnitsCostMultiplier);
	private final int unclearedSquareCostMultiplier = Integer
			.parseInt(LookupConfig.unclearedSquareCostMultiplier);
	private final int destructionOfProtectedTreeCostMultiplier = Integer
			.parseInt(LookupConfig.destructionOfProtectedTreeCostMultiplier);
	private final int repairingPaintDamageCostMultiplier = Integer
			.parseInt(LookupConfig.repairingPaintDamageCostMultiplier);

	private int totalCost = 0;
	private Bulldozer bulldozer;

	public CalculateIndividualAndTotalCost(Bulldozer bulldozer) {
		this.bulldozer = bulldozer;
	}

	// Getter Methods
	public int getCommunicationOverheadCost() {
		return bulldozer.getCalculateUnitOfOperations().getCommunicationOverhead()
				* communicationOverheadCostMultiplier;
	}

	public int getFuelCost() {
		return bulldozer.getCalculateUnitOfOperations().getTotalFuelUnits() * fuelUnitsCostMultiplier;
	}

	public int getUnclearedSquareCost() {
		return bulldozer.getCalculateUnitOfOperations().getUnclearedSquare()
				* unclearedSquareCostMultiplier;
	}

	public int getDestructionOfProtectedTreeCost() {
		return bulldozer.getCalculateUnitOfOperations().getDestructionOfProtectedTree()
				* destructionOfProtectedTreeCostMultiplier;
	}

	public int getRepairingPaintDamageCost() {
		return bulldozer.getCalculateUnitOfOperations().getRepairingPaintDamage()
				* repairingPaintDamageCostMultiplier;
	}

	public int getTotalCost() {
		totalCost = this.getCommunicationOverheadCost() + this.getFuelCost()
				+ this.getUnclearedSquareCost()
				+ this.getDestructionOfProtectedTreeCost()
				+ this.getRepairingPaintDamageCost();
		return this.totalCost;
	}

}
