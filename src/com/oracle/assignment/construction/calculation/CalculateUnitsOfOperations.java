package com.oracle.assignment.construction.calculation;

import org.apache.log4j.Logger;

import com.oracle.assignment.construction.config.LookupConfig;

/**
 * Class to calculate units of different operations during simulation. Different
 * Operational Units are defined below -
 * 
 * 1)Item communication overhead per command sent to bulldozer 
 * 2)operator fuel
 * 3)uncleared square at end of simulation 
 * 4)destruction of protected tree repairing
 * 5)paint damage to bulldozer cleared tree(t)
 * 
 * Fuel Units are defined below -
 * 
 * Activity Fuel Usage Clearing plain land("o") 1 fuel unit 
 * Visiting any land * that has already been cleared("x") 1 fuel unit 
 * Clearing rocky land("r") 2 * fuel units 
 * Clearing land containing a tree("t") 2 fuel units
 * 
 * @author Arpit Srivastava
 * 
 */

public class CalculateUnitsOfOperations {
	
	static Logger log = Logger.getLogger(CalculateUnitsOfOperations.class.getName());
	private int communicationOverhead = 0;
	private int unclearedSquare = 0;
	private int destructionOfProtectedTree = 0;
	private int repairingPaintDamage = 0;
	private int totalFuelUnits = 0;
	private final int fuelUsageClearingPlainLand = Integer.parseInt(LookupConfig.fuelUsageClearingPlainLand);
	private final int fuelUsageClearingVisitedLand = Integer.parseInt(LookupConfig.fuelUsageClearingVisitedLand);
	private final int fuelUsageRockyLand = Integer.parseInt(LookupConfig.fuelUsageRockyLand);
	private final int fuelUsageClearingTreeLand = Integer.parseInt(LookupConfig.fuelUsageClearingTreeLand);
	private int totalSquaresMinusTotalNumberOfProtectedTreesInSite=0;
	
	
	public void incrementCommunicationOverhead() {
		communicationOverhead++;
	}

	public void repairingPaintDamageIncreamenter() {
		repairingPaintDamage++;
	}
	
	public void destructionOfProtectedTreeIncreamenter() {
		destructionOfProtectedTree++;		
	}
	
	public void unclearedLandDecrementor() {
		unclearedSquare--;
	}
	
	public void addFuelUnits(char nextSquare) {
		
		switch (nextSquare) {

		case 'o':
			this.totalFuelUnits+= fuelUsageClearingPlainLand;
			break;

		case 'r':
			this.totalFuelUnits += fuelUsageRockyLand;
			break;
			
		case 't':
			this.totalFuelUnits += fuelUsageClearingTreeLand;
			break;

		case 'T':
			this.totalFuelUnits += fuelUsageClearingTreeLand;
			break;

		case 'x':
			this.totalFuelUnits += fuelUsageClearingVisitedLand;
			break;

		}
	}
	
	// Getter Methods
	public int getCommunicationOverhead() {
		return communicationOverhead;
	}

	public void setCommunicationOverhead(int communicationOverhead) {
		this.communicationOverhead = communicationOverhead;
	}

	public int getRepairingPaintDamage() {
		return repairingPaintDamage;
	}

	public void setRepairingPaintDamage(int repairingPaintDamage) {
		this.repairingPaintDamage = repairingPaintDamage;
	}

	public int getTotalFuelUnits() {
		return totalFuelUnits;
	}

	public void setTotalFuelUnits(int totalFuelUnits) {
		this.totalFuelUnits = totalFuelUnits;
	}
	
	public int getUnclearedSquare() {
		//Uncleared square is a negative value, hence we add it to the layout size.
		//We also reduce the total by the number of protected trees in the layout.
		return getTotalSquaresMinusTotalNumberOfProtectedTreesInSite()+unclearedSquare;
	}

	public int getDestructionOfProtectedTree() {
		return destructionOfProtectedTree;
	}

	public void setDestructionOfProtectedTree(int destructionOfProtectedTree) {
		this.destructionOfProtectedTree = destructionOfProtectedTree;
	}
	
	public int getTotalSquaresMinusTotalNumberOfProtectedTreesInSite() {
		return totalSquaresMinusTotalNumberOfProtectedTreesInSite;
	}

	public void setTotalSquaresMinusTotalNumberOfProtectedTreesInSite(
			int totalSquaresMinusTotalNumberOfProtectedTreesInSite) {
		this.totalSquaresMinusTotalNumberOfProtectedTreesInSite = totalSquaresMinusTotalNumberOfProtectedTreesInSite;
	}

}