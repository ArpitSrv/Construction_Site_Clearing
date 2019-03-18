package com.oracle.assignment.construction.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.oracle.assignment.construction.calculation.CalculateIndividualAndTotalCost;
import com.oracle.assignment.construction.calculation.CalculateUnitsOfOperations;
import com.oracle.assignment.construction.config.LookupConfig;
import com.oracle.assignment.construction.model.Bulldozer;
import com.oracle.assignment.construction.model.Layout;
import com.oracle.assignment.construction.utility.StringUtility;
import com.oracle.assignment.construction.utility.WrapperOfUnitAndCost;

/**
 * Helper class for performing implementation for controller class
 * 
 * @author Arpit Srivastava
 * 
 */
public class SiteSimulationControllerHelper {

	static Logger log = Logger.getLogger(SiteSimulationControllerHelper.class.getName());
	
	/**
	 * Method to take input from the user. User input is present in a file
	 * called userInput.txt which is placed on the location specified in
	 * LookupConfig.java file in INPUT_FILE parameter This method also helps to
	 * calculate the width and depth of the field
	 */

	public static Layout populateLayoutFromInputFile() {
		
		final String METHOD_NAME = "Layout";
		int width = 0, depth = 0;
		//Variable to check for null pointer only if the first line is not present in file
		int lineCounter=0;
		String userInputString = "";
		Layout newLayout = new Layout();
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					LookupConfig.INPUT_FILE));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			//Error handling if map is not given in input file.
			if(line == null && lineCounter==0){
				System.out.println(LookupConfig.FILE_WITH_NO_MAP);
				log.info(METHOD_NAME+LookupConfig.FILE_WITH_NO_MAP);
				System.exit(0);
			}else{
				log.debug(METHOD_NAME+"::next line from map :"+line);
				lineCounter++;
				width = line.length();
			}
			while (line != null) {
				sb.append(line);
				sb.append(",");
				line = br.readLine();
				depth++;
			}
			
			// Convert layout to String
			userInputString = sb.toString();
				
			log.debug(METHOD_NAME+"::user input map as string :"+userInputString);
			log.info(METHOD_NAME+"::calculateNumberOfProtectedTrees :"+calculateNumberOfProtectedTrees(userInputString));
			// Calculate and set the total number of protected trees in the map
			newLayout
					.setNumberOfProtectedTreesInSite(calculateNumberOfProtectedTrees(userInputString));
			log.info(METHOD_NAME+"::setNumberOfProtectedTreesInSite invoked successfully");
			// Display the map to the user
			displayLayoutMap(userInputString, width);
			log.info(METHOD_NAME+"::displayLayoutMap invoked successfully");
			// Converting the layout to a two dimensional array for
			// processing commands on the map
			String[][] userInputAsTwoDimArray = StringUtility
					.stringToDoubleDimentionalArrayString(userInputString);
			log.info(METHOD_NAME+"::userInputAsTwoDimArray received");
			// Set width,depth and layout of field in Layout
			newLayout.setConstructionSiteLayout(userInputAsTwoDimArray);
			newLayout.setWidth(width);
			newLayout.setDepth(depth);

		} catch (FileNotFoundException e) {
			System.out.println(LookupConfig.FILE_NOT_FOUND);
			log.info(METHOD_NAME+"::FileNotFoundException catch invoked");
			System.exit(0);
		} catch (IOException e) {
			System.out.println(LookupConfig.ILLEGAL_INPUT);
			log.info(METHOD_NAME+"::IOException catch invoked");
			System.exit(0);
		}

		return newLayout;

	}

	/**
	 * Display Layout Map on the simulator
	 * 
	 * @param inputMap
	 * @param width
	 */
	protected static void displayLayoutMap(String inputMap, int width) {
		
		final String METHOD_NAME = "displayLayoutMap";
		// Displaying input map to the user by adding spaces after every
		// character
		StringBuilder displayString = new StringBuilder();
		inputMap = inputMap.replaceAll(",", "\n");
		log.debug(METHOD_NAME+"::inputMap after replacing all ',' with '\n'"+inputMap);
		for (int i = 0; i < inputMap.length(); i++) {

			// Skip adding space for 1st character of every line
			if (i > 0 && i % (width + 1) != 0) {
				displayString.append(" ");
			}

			displayString.append(inputMap.charAt(i));
		}
		log.info(METHOD_NAME+"::displayString after formatting input map"+displayString);
		System.out.println(LookupConfig.WELCOME_MSG + displayString.toString());
		System.out.println(LookupConfig.INFO_MSG1);
	}

	/**
	 * Method to display the list of all commands issued
	 */
	protected static void displayListOfCommandsIssued(Bulldozer bulldozer) {
		final String METHOD_NAME = "displayListOfCommandsIssued";
		System.out.println(LookupConfig.INFO_MSG2);
		System.out.println();
		System.out.print(SiteSimulationController.getCommandsIssued());
		log.info(METHOD_NAME+"::Exiting this method");
	}

	/**
	 * Method to display operation unit and cost to the user
	 * 
	 * @param unitsAndCostsOfOperation
	 */
	protected static void displayOperationCost(
			List<WrapperOfUnitAndCost> unitsAndCostsOfOperation) {
		
		final String METHOD_NAME = "displayOperationCost";
		System.out.println(LookupConfig.INFO_MSG3);
		System.out.println(LookupConfig.ITEM + LookupConfig.QUANTITY
				+ LookupConfig.COST);

		for (WrapperOfUnitAndCost object : unitsAndCostsOfOperation) {
			if (object.getHeader().equals(LookupConfig.TOTAL)) {
				System.out.print(LookupConfig.SEPARATOR);
				System.out.print(object.getHeader() + object.getCost());
				break;
			}
			System.out.print(object.getHeader() + object.getUnit()
					+ LookupConfig.SPACE_BETWEEN_QTY_AND_COST
					+ object.getCost());
		}

		System.out.println(LookupConfig.INFO_MSG4);
		log.info(METHOD_NAME+"::Exiting this method");
	}

	/**
	 * Method to calculate the total number of protected trees on the map layout
	 * 
	 * @param inputMap
	 * @return
	 */
	private static int calculateNumberOfProtectedTrees(String inputMap) {
		final String METHOD_NAME = "calculateNumberOfProtectedTrees";
		int numberOfProtectedTrees = 0;
		for (int i = 0; i < inputMap.length(); i++) {
			if (inputMap.charAt(i) == 'T')
				numberOfProtectedTrees++;
		}
		log.debug(METHOD_NAME+"Increamenting number of protected trees"+numberOfProtectedTrees);
		log.info(METHOD_NAME+"::Exiting this method");
		return numberOfProtectedTrees;
	}
	
	/**
	 * Method to determine number of digits given with advance command
	 * @param input
	 * @param x
	 * @return
	 */
	protected static int determineNumberOfDigitsAfterAdvance(String input, int x) {
		final String METHOD_NAME = "determineNumberOfDigitsAfterAdvance";
		int numberOfDigitsAfterAdvance = 0;
		int nextCharAscii = 0;
		log.debug(METHOD_NAME+"::input.length():"+input.length());
		while (x < input.length()) {
			nextCharAscii = input.charAt(x);
			log.debug(METHOD_NAME+"::nextCharAscii:"+nextCharAscii);
			if (nextCharAscii > 47 && nextCharAscii < 58)
				numberOfDigitsAfterAdvance++;
			else if (numberOfDigitsAfterAdvance > 0
					&& !(nextCharAscii > 47 && nextCharAscii < 58))
				break;
			x++;
		}
		log.info(METHOD_NAME+"::Exiting this method");
		return numberOfDigitsAfterAdvance;
	}

	/**
	 * Method to form a ArrayList of WrapperOfUnitAndCost containing the name of
	 * operation, quantity and cost incurred for the operation.
	 * 
	 * @return
	 */
	protected static List<WrapperOfUnitAndCost> getCostOfOperations(
			Bulldozer bulldozer,
			CalculateUnitsOfOperations calculateUnitOfOperations,
			CalculateIndividualAndTotalCost calculateCost) {
		
		final String METHOD_NAME = "getCostOfOperations";
		List<WrapperOfUnitAndCost> unitsAndCostsOfAllOperations = new ArrayList<WrapperOfUnitAndCost>();
		unitsAndCostsOfAllOperations.add(new WrapperOfUnitAndCost(
				LookupConfig.COMMUNICATION_OVERHEAD, calculateUnitOfOperations
						.getCommunicationOverhead(), calculateCost
						.getCommunicationOverheadCost()));
		unitsAndCostsOfAllOperations.add(new WrapperOfUnitAndCost(
				LookupConfig.FUEL_USAGE, calculateUnitOfOperations
						.getTotalFuelUnits(), calculateCost.getFuelCost()));
		unitsAndCostsOfAllOperations.add(new WrapperOfUnitAndCost(
				LookupConfig.UNCLEARED_SQUARES, calculateUnitOfOperations
						.getUnclearedSquare(), calculateCost
						.getUnclearedSquareCost()));
		unitsAndCostsOfAllOperations.add(new WrapperOfUnitAndCost(
				LookupConfig.DESTRUCTION_OF_PROTECTED_TREE,
				calculateUnitOfOperations.getDestructionOfProtectedTree(),
				calculateCost.getDestructionOfProtectedTreeCost()));
		unitsAndCostsOfAllOperations.add(new WrapperOfUnitAndCost(
				LookupConfig.PAINT_DAMAGE_TO_BULLDOZER, calculateUnitOfOperations
						.getRepairingPaintDamage(), calculateCost
						.getRepairingPaintDamageCost()));
		unitsAndCostsOfAllOperations.add(new WrapperOfUnitAndCost(
				LookupConfig.TOTAL, 0, calculateCost.getTotalCost()));
		
		log.info(METHOD_NAME+"::Exiting this method");
		return unitsAndCostsOfAllOperations;
	}
}
