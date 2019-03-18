package com.oracle.assignment.construction.controller;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.oracle.assignment.construction.calculation.CalculateIndividualAndTotalCost;
import com.oracle.assignment.construction.calculation.CalculateUnitsOfOperations;
import com.oracle.assignment.construction.config.LookupConfig;
import com.oracle.assignment.construction.model.Bulldozer;
import com.oracle.assignment.construction.model.Layout;
import com.oracle.assignment.construction.utility.WrapperOfUnitAndCost;

/**
 * This class has been created to take the inputs from user including a) Field
 * map input from file in mapInput() method b) User input during simulation in
 * userRuntimeInput() method
 * 
 * @author Arpit Srivastava
 * 
 */

public class SiteSimulationController {

	static Logger log = Logger.getLogger(SiteSimulationController.class.getName());
	private static boolean endSimulation;
	static StringBuilder commandList = new StringBuilder();

	/**
	 * Main method of Controller
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		final String METHOD_NAME = "main";
		// Read the layout given by the user from file
		Layout layOut = SiteSimulationControllerHelper
				.populateLayoutFromInputFile();
		log.info(METHOD_NAME+"::Layout object created successfully");
		// Object to calculate unit of all operations at runtime
		CalculateUnitsOfOperations unitOfOperations = new CalculateUnitsOfOperations();
		log.info(METHOD_NAME+"::CalculateUnitsOfOperations object created successfully");
		// Object of Bulldozer with the layout as an argument
		Bulldozer bulldozer = new Bulldozer(layOut, unitOfOperations);
		log.info(METHOD_NAME+"::Bulldozer object created successfully");
		// Obtain user commands and move Bulldozer based on the commands,
		// calculating units of operation at runtime
		moveBullDozerBasedonUserCommands(bulldozer);
		log.info(METHOD_NAME+"::moveBullDozerBasedonUserCommands invoked successfully");
		// Display list of all commands issued
		SiteSimulationControllerHelper.displayListOfCommandsIssued(bulldozer);
		log.info(METHOD_NAME+"::displayListOfCommandsIssued invoked successfully");
		// Object to calculate cost of all operations
		CalculateIndividualAndTotalCost costOfAllOperations = new CalculateIndividualAndTotalCost(
				bulldozer);
		log.info(METHOD_NAME+"::CalculateIndividualAndTotalCost object created successfully");
		// Display the units and cost of operations during this simulation
		List<WrapperOfUnitAndCost> costsOfAllOperations = SiteSimulationControllerHelper
				.getCostOfOperations(bulldozer, unitOfOperations,
						costOfAllOperations);
		log.info(METHOD_NAME+"::getCostOfOperations invoked successfully");
		SiteSimulationControllerHelper
				.displayOperationCost(costsOfAllOperations);
		log.info(METHOD_NAME+"::displayOperationCost invoked successfully");
	}

	/**
	 * Method to invoke commands from the user and then move the bulldozer on
	 * the layout based on the user command at runtime
	 * 
	 * @param bulldozer
	 */
	private static void moveBullDozerBasedonUserCommands(Bulldozer bulldozer) {
		
		final String METHOD_NAME = "moveBullDozerBasedonUserCommands";
		// Set the value of End Simulation to false in the starting of
		// simulation
		boolean isSimulationToBeStopped = false;
		// Take commands from the user
		Scanner scanner = new Scanner(System.in);
		String inputCommand = "";
		try {
			while (!isSimulationToBeStopped) {

				System.out.print(LookupConfig.INPUT_CHOICES);
				inputCommand = scanner.nextLine();
				log.info(METHOD_NAME+"::input command entered by user :: "+inputCommand);
				isSimulationToBeStopped = processUserCommand(bulldozer,
						inputCommand);
				log.info(METHOD_NAME+"::processUserCommand invoked successfully");
				log.debug(METHOD_NAME+"::value of isSimulationToBeStopped :: "+isSimulationToBeStopped);
			}
			scanner.close();
		} catch (Exception e) {
			log.info(METHOD_NAME+"::catch block has been invoked");
			System.out.println(LookupConfig.ILLEGAL_INPUT);
			SiteSimulationController.setIsEndSimulation(true);
		}
	}

	/**
	 * Method to process user's commands and return if the simulation should be
	 * stopped. This method derives whether the simulation should be ended or
	 * not and passes on the information to the main simulation method. Below
	 * are the 3 scenarios for ending the simulation - 1) there is an attempt to
	 * navigate beyond the boundaries of the site; 2) there is an attempt to
	 * remove a tree that is protected; 3) the trainee enters the quit command.
	 * Direction is followed as - North-(Up) West-(Left) EAST-(Right)
	 * South-(Down)
	 * 
	 * @param bulldozer
	 * @param inputCommand
	 * @return isEndSimlation
	 */
	public static boolean processUserCommand(Bulldozer bulldozer,
			String inputCommand) {
		
		final String METHOD_NAME = "processUserCommand";
		String input = inputCommand.replaceAll("\\s+", "");
		log.debug(METHOD_NAME+"::input value :"+input);
		boolean zeroAtBeginningOfSimulation = false;
		log.debug(METHOD_NAME+"::length of input :"+input.length());
		// Navigate the field according to the input from the user
		for (int pointerPositionInUserCommand = 0; pointerPositionInUserCommand < input
				.length() && !getIsEndSimulation(); pointerPositionInUserCommand++) {
			
			log.debug(METHOD_NAME+"::pointerPositionInUserCommand :"+pointerPositionInUserCommand);
			char ch = input.charAt(pointerPositionInUserCommand);
			log.info(METHOD_NAME+"::current character is :"+ch);
			switch (ch) {
			case 'a':
				try {
					// Find the number of digits given after advance which will
					// help determine number of squares to advance on layout
					int numberOfDigitsAfterAdvance = SiteSimulationControllerHelper
							.determineNumberOfDigitsAfterAdvance(input,
									pointerPositionInUserCommand);
					log.info(METHOD_NAME+"::determineNumberOfDigitsAfterAdvance invoked successfully");
					log.info(METHOD_NAME+"::numberOfDigitsAfterAdvance is :"+numberOfDigitsAfterAdvance);
					// In case an invalid input is given with advance command
					if (numberOfDigitsAfterAdvance == 0) {
						log.info(METHOD_NAME+"::numberOfDigitsAfterAdvance is 0");
						System.out.println(LookupConfig.UNKNOWN_COMMAND);
						pointerPositionInUserCommand++;
						break;
					}

					// Determine number of squares to advance using number of
					// digits to advance and the current pointer position of
					// command
					String numberToAdvance = input.substring(
							pointerPositionInUserCommand + 1,
							pointerPositionInUserCommand + 1 + numberOfDigitsAfterAdvance);
					int numberOfSquaresToAdvance = Integer.parseInt(numberToAdvance);
					log.debug(METHOD_NAME+"::numberToAdvance :"+numberToAdvance);
					// In case 0 is given as input with advance command at the
					// beginning of simulation Eg.-"a 0"
					if (numberOfDigitsAfterAdvance == 1
							&& numberOfSquaresToAdvance == 0
							&& bulldozer.getLayout()
									.getLocationOfBulldozerXAxis() == -1) {
						log.info(METHOD_NAME+"::a 0 command has been given at the beggining of simulation");
						System.out
								.println(LookupConfig.ZERO_ADVANCE_COMMAND_DURING_BEGGINING);
						zeroAtBeginningOfSimulation = true;
					}
					log.debug(METHOD_NAME+"value of zeroAtBeginningOfSimulation :"+zeroAtBeginningOfSimulation);
					// Add advance instruction to the set of commands issued by
					// user
					storeUserCommandsIssued("advance " + numberOfSquaresToAdvance + ", ");
					log.info(METHOD_NAME+"::storeUserCommandsIssued");
					// Advance the bulldozer by given number of squares on
					// layout
					bulldozer.processAdvance(numberOfSquaresToAdvance);
					log.debug(METHOD_NAME+"processAdvance has been invoked successfully");
					// Below if condition will check exit
					// condition 1.
					// Exclude this invoke in case user gave 'a 0' command at
					// the beginning of simulation
					log.debug(METHOD_NAME+"::getLocationOfBulldozerXAxis"+bulldozer.getLayout().getLocationOfBulldozerXAxis());
					log.debug(METHOD_NAME+"::getWidth"+bulldozer.getLayout().getWidth());
					log.debug(METHOD_NAME+"::getLocationOfBulldozerYAxis"+bulldozer.getLayout().getLocationOfBulldozerYAxis());
					log.debug(METHOD_NAME+"::getDepth"+bulldozer.getLayout().getDepth());
					log.debug(METHOD_NAME+"::zeroAtBeginningOfSimulation"+zeroAtBeginningOfSimulation);
					if (((bulldozer.getLayout().getLocationOfBulldozerXAxis() < 0 || bulldozer
							.getLayout().getLocationOfBulldozerXAxis() >= bulldozer
							.getLayout().getWidth())
							|| (bulldozer.getLayout()
									.getLocationOfBulldozerYAxis() < 0 || bulldozer
									.getLayout().getLocationOfBulldozerYAxis() >= bulldozer
									.getLayout().getDepth()))
							&& !zeroAtBeginningOfSimulation) {
						// Satisfies 1st condition for exit
						storeUserCommandsIssued(LookupConfig.END_OF_SIMULATION_CONDITION_1);
						setIsEndSimulation(true);
					}

					// Set zeroAtBeginningOfSimulation to false for next command
					if (zeroAtBeginningOfSimulation)
						zeroAtBeginningOfSimulation = false;

				} catch (IndexOutOfBoundsException e) {
					log.info(METHOD_NAME+"::In catch block of advance");
					setIsEndSimulation(true);
				}
				// Skip the number of characters given by the user as number
				// with advance command
				pointerPositionInUserCommand = pointerPositionInUserCommand
						+ SiteSimulationControllerHelper
								.determineNumberOfDigitsAfterAdvance(input,
										pointerPositionInUserCommand);
				log.debug(METHOD_NAME+"::pointerPositionInUserCommand"+pointerPositionInUserCommand);
				break;

			case 'l':
				storeUserCommandsIssued("turn left, ");
				bulldozer.processLeftCommand();
				log.info(METHOD_NAME+"::processLeftCommand issued successfully");
				break;

			case 'r':
				storeUserCommandsIssued("turn right, ");
				bulldozer.processRightCommand();
				log.info(METHOD_NAME+"::processRightCommand issued successfully");
				break;

			case 'q': // Satisfies 3rd condition for exit
				setIsEndSimulation(true);
				storeUserCommandsIssued("quit");
				log.info(METHOD_NAME+"::quit command issued successfully");
				break;

			default:
				System.out.println(LookupConfig.UNKNOWN_COMMAND);
				log.info(METHOD_NAME+"::flow is in default");
				break;
			}

		}

		return getIsEndSimulation();

	}

	public static void storeUserCommandsIssued(String nextCommand) {
		commandList = commandList.append(nextCommand);
	}

	public static String getCommandsIssued() {
		return commandList.toString();
	}

	public static void setIsEndSimulation(Boolean flag) {
		endSimulation = true;
	}

	private static boolean getIsEndSimulation() {
		return endSimulation;
	}
}
