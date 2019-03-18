package com.oracle.assignment.construction.model;

import org.apache.log4j.Logger;

import com.oracle.assignment.construction.calculation.CalculateUnitsOfOperations;
import com.oracle.assignment.construction.controller.SiteSimulationController;

public class Bulldozer {

	enum direction {
		EAST, WEST, NORTH, SOUTH
	}
	
	static Logger log = Logger.getLogger(Bulldozer.class.getName());
	private direction directionOfBulldozer;
	protected Layout layout;
	protected CalculateUnitsOfOperations calculateUnitOfOperations;

	/**
	 * Constructor
	 * 
	 * @param layout
	 */
	public Bulldozer(Layout layout,
			CalculateUnitsOfOperations calculateUnitOfOperations) {
		
		final String METHOD_NAME = "Bulldozer";
		this.layout = layout;
		this.calculateUnitOfOperations = calculateUnitOfOperations;
		log.info(METHOD_NAME+"::Layout and calculateUnitOfOperations have been set in Bulldozer");
		// The bulldozer will be at the north west corner of the field facing
		// east at the starting of the simulation
		directionOfBulldozer = direction.EAST;
		log.debug(METHOD_NAME+"::Initial value of directionOfBulldozer"+directionOfBulldozer);
	}

	/**
	 * Method to perform processing on the Advance command given by the user
	 * 
	 * @param numberOfSquares
	 */
	public void processAdvance(int numberOfSquares) {
		
		final String METHOD_NAME = "processAdvance";
		log.debug(METHOD_NAME+"::getTotalSquaresMinusTotalNumberOfProtectedTreesInSite()"+layout
				.getTotalSquaresMinusTotalNumberOfProtectedTreesInSite());
		//Set (total squares)-(number of protected trees) for calculation of uncleared land
		calculateUnitOfOperations
				.setTotalSquaresMinusTotalNumberOfProtectedTreesInSite(layout
						.getTotalSquaresMinusTotalNumberOfProtectedTreesInSite());
		//Increment communication overhead for the command issued
		calculateUnitOfOperations.incrementCommunicationOverhead();
		log.debug(METHOD_NAME+"::communication overhead increamented");
		int startXAxis = layout.getLocationOfBulldozerXAxis();
		int endXAxis = 0;
		int startYAxis = layout.getLocationOfBulldozerYAxis();
		int endYAxis = 0;
		log.debug(METHOD_NAME+"::startXAxis: "+startXAxis);
		log.debug(METHOD_NAME+"::startYAxis: "+startYAxis);
		try {
			log.info(METHOD_NAME+"::directionOfBulldozer in try block: "+directionOfBulldozer);
			if (directionOfBulldozer.equals(direction.WEST)) {
				traverseWest(layout, numberOfSquares, startXAxis, endXAxis,
						startYAxis, endYAxis);
			}

			else if (directionOfBulldozer.equals(direction.EAST)) {
				traverseEast(layout, numberOfSquares, startXAxis, endXAxis,
						startYAxis, endYAxis);
			}

			else if (directionOfBulldozer.equals(direction.NORTH)) {
				traverseNorth(layout, numberOfSquares, startXAxis, endXAxis,
						startYAxis, endYAxis);
			}

			else if (directionOfBulldozer.equals(direction.SOUTH)) {
				traverseSouth(layout, numberOfSquares, startXAxis, endXAxis,
						startYAxis, endYAxis);
			}

		} catch (Exception e) {
			log.info(METHOD_NAME+"::Inside catch block");
			SiteSimulationController.setIsEndSimulation(true);
		}

	}

	/**
	 * Method to perform processing on left command
	 * 
	 */

	public void processLeftCommand() {
		final String METHOD_NAME = "processLeftCommand";
		calculateUnitOfOperations.incrementCommunicationOverhead();
		log.debug(METHOD_NAME+"::Communication Overhead increamented");
		if (directionOfBulldozer.equals(direction.WEST))
			directionOfBulldozer = direction.SOUTH;
		else if (directionOfBulldozer.equals(direction.EAST))
			directionOfBulldozer = direction.NORTH;
		else if (directionOfBulldozer.equals(direction.SOUTH))
			directionOfBulldozer = direction.EAST;
		else if (directionOfBulldozer.equals(direction.NORTH))
			directionOfBulldozer = direction.WEST;
		log.info(METHOD_NAME+"::directionOfBulldozer while exiting:"+directionOfBulldozer);
	}

	/**
	 * Method to perform processing on right command
	 * 
	 */

	public void processRightCommand() {
		final String METHOD_NAME = "processRightCommand";
		calculateUnitOfOperations.incrementCommunicationOverhead();
		log.debug(METHOD_NAME+"::Communication Overhead increamented");
		if (directionOfBulldozer.equals(direction.WEST))
			directionOfBulldozer = direction.NORTH;
		else if (directionOfBulldozer.equals(direction.EAST))
			directionOfBulldozer = direction.SOUTH;
		else if (directionOfBulldozer.equals(direction.SOUTH))
			directionOfBulldozer = direction.WEST;
		else if (directionOfBulldozer.equals(direction.NORTH))
			directionOfBulldozer = direction.EAST;
		log.info(METHOD_NAME+"::directionOfBulldozer while exiting:"+directionOfBulldozer);
	}

	/**
	 * Method to perform processing on advance command when direction is west
	 * 
	 */

	private void traverseWest(Layout layout, int numberOfSquares,
			int startXAxis, int endXAxis, int startYAxis, int endYAxis) {
		
		final String METHOD_NAME = "traverseWest";
		log.info(METHOD_NAME+"::startXAxis - numberOfSquares:"+(startXAxis - numberOfSquares));
		// Set the new location of X Axis as the bulldozer is moving along x
		// axis
		layout.setLocationOfBulldozerXAxis(startXAxis - numberOfSquares);
		
		log.info(METHOD_NAME+"::layout.getLocationOfBulldozerXAxis:"+layout.getLocationOfBulldozerXAxis());
		/*
		 * We need to mark the layout as cleared in case bulldozer moves through
		 * it. The below conditional set will help mark the layout as cleared in
		 * case the bulldozer moves out of the layout
		 */
		endXAxis = layout.getLocationOfBulldozerXAxis() > 0 ? layout
				.getLocationOfBulldozerXAxis() : 0;
		// While moving west x axis remains constant, hence endXAxis is equal to
		// startXAxis
		endYAxis = startYAxis;
		layout.markFieldAsCovered(calculateUnitOfOperations, startXAxis,
				endXAxis, startYAxis, endYAxis);
	}

	/**
	 * Method to perform processing on advance command when direction is east
	 * 
	 */

	private void traverseEast(Layout layout, int numberOfSquares,
			int startXAxis, int endXAxis, int startYAxis, int endYAxis) {
		
		final String METHOD_NAME = "traverseEast";
		log.info(METHOD_NAME+"::startXAxis - numberOfSquares:"+(startXAxis + numberOfSquares));
		// Set the new location of X Axis as the bulldozer is moving along x
		// axis
		layout.setLocationOfBulldozerXAxis(startXAxis + numberOfSquares);
		
		log.info(METHOD_NAME+"::layout.getLocationOfBulldozerXAxis:"+layout.getLocationOfBulldozerXAxis());
		log.info(METHOD_NAME+"::layout.getWidth():"+layout.getWidth());
		/*
		 * We need to mark the layout as cleared in case bulldozer moves through
		 * it. The below conditional set will help mark the layout as cleared in
		 * case the bulldozer moves out of the layout
		 */
		endXAxis = layout.getLocationOfBulldozerXAxis() <= layout.getWidth() ? layout
				.getLocationOfBulldozerXAxis() : layout.getWidth();

		// While moving east x axis remains constant, hence endXAxis is equal to
		// startXAxis
		endYAxis = startYAxis;
		layout.markFieldAsCovered(calculateUnitOfOperations, startXAxis,
				endXAxis, startYAxis, endYAxis);
	}

	/**
	 * Method to perform processing on advance command when direction is north
	 * 
	 */

	private void traverseNorth(Layout layout, int numberOfSquares,
			int startXAxis, int endXAxis, int startYAxis, int endYAxis) {
		
		final String METHOD_NAME = "traverseNorth";
		log.info(METHOD_NAME+"::startXAxis - numberOfSquares:"+(startYAxis - numberOfSquares));
		// Set the new location of Y Axis as the bulldozer is moving along y
		// axis
		layout.setLocationOfBulldozerYAxis(startYAxis - numberOfSquares);
		
		log.info(METHOD_NAME+"::layout.getLocationOfBulldozerYAxis:"+layout.getLocationOfBulldozerYAxis());
		// To mark the covered map as tracked in case bulldozer
		// moves out of map
		endYAxis = layout.getLocationOfBulldozerYAxis() > 0 ? layout
				.getLocationOfBulldozerYAxis() : 0;
		// While moving north x axis remains constant, hence endXAxis is equal
		// to startXAxis
		endXAxis = startXAxis;
		layout.markFieldAsCovered(calculateUnitOfOperations, startXAxis,
				endXAxis, startYAxis, endYAxis);
	}

	/**
	 * Method to perform processing on advance command when direction is south
	 * 
	 */

	private void traverseSouth(Layout layout, int numberOfSquares,
			int startXAxis, int endXAxis, int startYAxis, int endYAxis) {
		
		final String METHOD_NAME = "traverseSouth";
		log.info(METHOD_NAME+"::startXAxis - numberOfSquares:"+(startYAxis + numberOfSquares));
		// Set the new location of Y Axis as the bulldozer is moving along y
		// axis
		layout.setLocationOfBulldozerYAxis(startYAxis + numberOfSquares);
		
		log.info(METHOD_NAME+"::layout.getLocationOfBulldozerYAxis:"+layout.getLocationOfBulldozerYAxis());
		log.info(METHOD_NAME+"::layout.getWidth():"+layout.getDepth());
		// To mark the covered map as tracked in case bulldozer
		// moves out of map
		endYAxis = layout.getLocationOfBulldozerYAxis() <= layout.getDepth() ? layout
				.getLocationOfBulldozerYAxis() : layout.getDepth();

		// While moving north x axis remains constant, hence endXAxis is equal
		// to startXAxis
		endXAxis = startXAxis;
		layout.markFieldAsCovered(calculateUnitOfOperations, startXAxis,
				endXAxis, startYAxis, endYAxis);

	}

	// Getters and Setters

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public CalculateUnitsOfOperations getCalculateUnitOfOperations() {
		return calculateUnitOfOperations;
	}
}
