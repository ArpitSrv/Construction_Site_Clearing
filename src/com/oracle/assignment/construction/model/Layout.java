package com.oracle.assignment.construction.model;

import org.apache.log4j.Logger;

import com.oracle.assignment.construction.calculation.CalculateUnitsOfOperations;
import com.oracle.assignment.construction.config.LookupConfig;
import com.oracle.assignment.construction.controller.SiteSimulationController;

/**
 * This class stores the map of the construction site
 * @author asrivasta212
 *
 */
public class Layout {
	
	static Logger log = Logger.getLogger(Layout.class.getName());
	private String[][] constructionSiteLayout;
	private int width;
	private int depth;
	private int numberOfProtectedTreesInSite;
	private int myLocationonLayputXAxis=-1;
	private int myLocationonLayputYAxis=0;
	
	/**
	 * Method to mark the area of the field as covered as the bulldozer moves
	 * The covered field squares are marked by 'x'
	 * 
	 * @param mapInput
	 * @param startX
	 * @param endX
	 * @param startY
	 * @param endY
	 * @return
	 */
	public void markFieldAsCovered(CalculateUnitsOfOperations calculateUnitsOfOperations,int startX, int endX, int startY, int endY) {
		
		final String METHOD_NAME = "markFieldAsCovered";
		log.info(METHOD_NAME+"::startY:"+startY);
		log.info(METHOD_NAME+"::endY:"+endY);
		log.info(METHOD_NAME+"::startX:"+startX);
		log.info(METHOD_NAME+"::endX:"+endX);
		if (startY == endY) {
			// For east to west
			if (startX > endX) {
				markFieldAsCoveredMovingEastToWest(calculateUnitsOfOperations,
						startX, endX, startY, endY);
			}
			// For west to east
			else if (startX < endX) {
				markFieldAsCoveredMovingWestToEast(calculateUnitsOfOperations,
						startX, endX, startY, endY);
			}
		} else if (startX == endX) {
			// For south to north
			if (startY > endY) {
				markFieldAsCoveredMovingSouthToNorth(calculateUnitsOfOperations,
						startX, endX, startY, endY);
			}
			// For north to south
			else if (startY < endY) {
				markFieldAsCoveredMovingNorthToSouth(calculateUnitsOfOperations,
						startX, endX, startY, endY);
			}
		}
//		for(int a=0; a<depth; a++){
//			for(int b=0; b<width;b++){
//				System.out.print(constructionSiteLayout[a][b]);
//			}
//			System.out.println();
//		}
	}

	/**
	 * Mark Field as covered when bulldozer moves from east to west
	 * 
	 * @param outputMap
	 * @param startX
	 * @param endX
	 * @param startY
	 * @param endY
	 */
	private void markFieldAsCoveredMovingEastToWest(CalculateUnitsOfOperations calculateUnitsOfOperations,
			int startX, int endX, int startY, int endY) {
		
		final String METHOD_NAME = "markFieldAsCoveredMovingEastToWest";
		log.info(METHOD_NAME+"::startY:"+startY);
		log.info(METHOD_NAME+"::endY:"+endY);
		log.info(METHOD_NAME+"::startX:"+startX);
		log.info(METHOD_NAME+"::endX:"+endX);
		while (startX > endX) {
			startX -= 1;
			// Satisfies 2nd condition for exit
			if (constructionSiteLayout[startY][startX].equals(LookupConfig.PROTECTED_TREE)) {
				SiteSimulationController.storeUserCommandsIssued(LookupConfig.END_OF_SIMULATION_CONDITION_2);
				SiteSimulationController.setIsEndSimulation(true);
				calculateUnitsOfOperations.destructionOfProtectedTreeIncreamenter();
				calculateUnitsOfOperations.addFuelUnits(constructionSiteLayout[startY][startX].charAt(0));
				break;
			}
			
			if (constructionSiteLayout[startY][startX].equals(LookupConfig.REMOVABLE_TREE)
					&& startX != endX)
				calculateUnitsOfOperations.repairingPaintDamageIncreamenter();
			
			if (!constructionSiteLayout[startY][startX].equals(LookupConfig.CLEARED_LAND))
				calculateUnitsOfOperations.unclearedLandDecrementor();
			
			calculateUnitsOfOperations.addFuelUnits(constructionSiteLayout[startY][startX].charAt(0));
			
			// Mark the land as cleared
			constructionSiteLayout[startY][startX] = LookupConfig.CLEARED_LAND;
		}

	}

	/**
	 * Mark Field as covered when bulldozer moves from west to east
	 * 
	 * @param outputMap
	 * @param startX
	 * @param endX
	 * @param startY
	 * @param endY
	 */
	private void markFieldAsCoveredMovingWestToEast(CalculateUnitsOfOperations calculateUnitsOfOperations,
			int startX, int endX, int startY, int endY) {
		
		final String METHOD_NAME = "markFieldAsCoveredMovingWestToEast";
		log.info(METHOD_NAME+"::startY:"+startY);
		log.info(METHOD_NAME+"::endY:"+endY);
		log.info(METHOD_NAME+"::startX:"+startX);
		log.info(METHOD_NAME+"::endX:"+endX);
		while (startX < endX) {
			startX += 1;
			// Satisfies 2nd condition for exit
			if (constructionSiteLayout[startY][startX].equals(LookupConfig.PROTECTED_TREE)) {
				SiteSimulationController.storeUserCommandsIssued(LookupConfig.END_OF_SIMULATION_CONDITION_2);
				SiteSimulationController.setIsEndSimulation(true);
				calculateUnitsOfOperations.destructionOfProtectedTreeIncreamenter();
				calculateUnitsOfOperations.addFuelUnits(constructionSiteLayout[startY][startX].charAt(0));
				break;
			}
			
			if (!constructionSiteLayout[startY][startX].equals(LookupConfig.CLEARED_LAND)
					&& !constructionSiteLayout[startY][startX]
							.equals(LookupConfig.PROTECTED_TREE))
				calculateUnitsOfOperations.unclearedLandDecrementor();
			
			if (constructionSiteLayout[startY][startX].equals(LookupConfig.REMOVABLE_TREE)
					&& startX != endX)
				calculateUnitsOfOperations.repairingPaintDamageIncreamenter();
			
			calculateUnitsOfOperations.addFuelUnits(constructionSiteLayout[startY][startX].charAt(0));
			
			// Mark the land as cleared
			constructionSiteLayout[startY][startX] = LookupConfig.CLEARED_LAND;
		}
		
	}

	/**
	 * Mark Field as covered when bulldozer moves from south to north
	 * 
	 * @param outputMap
	 * @param startX
	 * @param endX
	 * @param startY
	 * @param endY
	 */
	private void markFieldAsCoveredMovingSouthToNorth(
			CalculateUnitsOfOperations calculateUnitsOfOperations, int startX, int endX, int startY, int endY) {
		
		final String METHOD_NAME = "markFieldAsCoveredMovingSouthToNorth";
		log.info(METHOD_NAME+"::startY:"+startY);
		log.info(METHOD_NAME+"::endY:"+endY);
		log.info(METHOD_NAME+"::startX:"+startX);
		log.info(METHOD_NAME+"::endX:"+endX);
		while (startY > endY) {
			startY -= 1;
			// Satisfies 2nd condition for exit
			if (constructionSiteLayout[startY][startX].equals(LookupConfig.PROTECTED_TREE)) {
				SiteSimulationController.storeUserCommandsIssued(LookupConfig.END_OF_SIMULATION_CONDITION_2);
				SiteSimulationController.setIsEndSimulation(true);
				calculateUnitsOfOperations.destructionOfProtectedTreeIncreamenter();
				calculateUnitsOfOperations.addFuelUnits(constructionSiteLayout[startY][startX].charAt(0));
				break;
			}
			
			// startY != endY to rule out repairing paint damage when bulldozer
			// stops on removable t
			if (constructionSiteLayout[startY][startX].equals(LookupConfig.REMOVABLE_TREE)
					&& startY != endY)
				calculateUnitsOfOperations.repairingPaintDamageIncreamenter();
			
			if (!constructionSiteLayout[startY][startX].equals(LookupConfig.CLEARED_LAND)
					&& !constructionSiteLayout[startY][startX]
							.equals(LookupConfig.PROTECTED_TREE))
				calculateUnitsOfOperations.unclearedLandDecrementor();
			
			calculateUnitsOfOperations.addFuelUnits(constructionSiteLayout[startY][startX].charAt(0));
			
			// Mark the land as cleared
			constructionSiteLayout[startY][startX] = LookupConfig.CLEARED_LAND;
		}
		
	}

	/**
	 * Mark Field as covered when bulldozer moves from north to south
	 * 
	 * @param outputMap
	 * @param startX
	 * @param endX
	 * @param startY
	 * @param endY
	 */
	private void markFieldAsCoveredMovingNorthToSouth(
			CalculateUnitsOfOperations calculateUnitsOfOperations, int startX, int endX, int startY, int endY) {
		
		final String METHOD_NAME = "markFieldAsCoveredMovingNorthToSouth";
		log.info(METHOD_NAME+"::startY:"+startY);
		log.info(METHOD_NAME+"::endY:"+endY);
		log.info(METHOD_NAME+"::startX:"+startX);
		log.info(METHOD_NAME+"::endX:"+endX);
		while (startY < endY) {
			startY += 1;
			// Satisfies 2nd condition for exit
			if (constructionSiteLayout[startY][startX].equals(LookupConfig.PROTECTED_TREE)) {
				SiteSimulationController.storeUserCommandsIssued(LookupConfig.END_OF_SIMULATION_CONDITION_2);
				SiteSimulationController.setIsEndSimulation(true);
				calculateUnitsOfOperations.destructionOfProtectedTreeIncreamenter();
				calculateUnitsOfOperations.addFuelUnits(constructionSiteLayout[startY][startX].charAt(0));
				break;
			}
			
			if (constructionSiteLayout[startY][startX].equals(LookupConfig.REMOVABLE_TREE)
					&& startY != endY)
				calculateUnitsOfOperations.repairingPaintDamageIncreamenter();
			
			if (!constructionSiteLayout[startY][startX].equals(LookupConfig.CLEARED_LAND)
					&& !constructionSiteLayout[startY][startX]
							.equals(LookupConfig.PROTECTED_TREE))
				calculateUnitsOfOperations.unclearedLandDecrementor();
			
			calculateUnitsOfOperations.addFuelUnits(constructionSiteLayout[startY][startX].charAt(0));
			
			// Mark the land as cleared
			constructionSiteLayout[startY][startX] = LookupConfig.CLEARED_LAND;
		}
		
	}
	
	public int getLocationOfBulldozerXAxis() {
		return myLocationonLayputXAxis;
	}

	public void setLocationOfBulldozerXAxis(int myLocationonLayputXAxis) {
		this.myLocationonLayputXAxis = myLocationonLayputXAxis;
	}

	public int getLocationOfBulldozerYAxis() {
		return myLocationonLayputYAxis;
	}

	public void setLocationOfBulldozerYAxis(int locationOfBulldozerYAxis) {
		this.myLocationonLayputYAxis = locationOfBulldozerYAxis;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String[][] getConstructionSiteLayout() {
		return constructionSiteLayout;
	}

	public void setConstructionSiteLayout(String[][] constructionSiteLayout) {
		this.constructionSiteLayout = constructionSiteLayout;
	}
	
	public int getTotalSquaresMinusTotalNumberOfProtectedTreesInSite() {
		return (width*depth)- numberOfProtectedTreesInSite;
	}
	
	public void setNumberOfProtectedTreesInSite(int numberOfProtectedTreesInSite) {
		this.numberOfProtectedTreesInSite = numberOfProtectedTreesInSite;
	}
	
	
	
}
