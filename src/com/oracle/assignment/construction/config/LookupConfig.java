package com.oracle.assignment.construction.config;

public class LookupConfig {

	//Input File Path
	public static final String INPUT_FILE = "C:\\userInput.txt";

	//INFO Messages
	public static final String WELCOME_MSG = "Welcome to the Aconex site clearing simulator. This is a map of the site: \n\n";
	public static final String INFO_MSG1 = "\nThe bulldozer is currently located at the Northern edge of the site,\n immediately to the West of the site, and facing East.\n";
	public static final String INFO_MSG2 = "\nThe simulation has ended at your request. These are the commands you issued:";	
	public static final String INFO_MSG3 ="\n\nThe costs for this land clearing operation were:";
	public static final String INFO_MSG4 ="\n\nThankyou for using the Aconex site clearing simulator.";
	public static final String INPUT_CHOICES ="(l)eft, (r)ight, (a)dvance <n>, (q)uit: ";
	public static final String END_OF_SIMULATION_CONDITION_1="\nBulldozer moved out of field at your command";
	public static final String END_OF_SIMULATION_CONDITION_2="\nThe Bulldozer ran over a protected tree at your command";
	public static final String UNKNOWN_COMMAND="\nUnknown command entered. Please enter a valid command\n";
	public static final String ZERO_ADVANCE_COMMAND_DURING_BEGGINING="\nPlease enter more than 0 squares with advance command to move forward";
	public static final String ILLEGAL_INPUT="\nIllegal Input. Program will be terminating now.";
	public static final String FILE_NOT_FOUND="\nThe file with layout could not be found. Program will be terminating now.";
	public static final String FILE_WITH_NO_MAP="\nInput File with map is empty. Please provide the map in input file.";
	
	//Field Square Representation
	public static final String PROTECTED_TREE ="T";
	public static final String REMOVABLE_TREE ="t";
	public static final String PLAIN_LAND ="o";
	public static final String ROUGH_LAND ="r";
	public static final String CLEARED_LAND ="x";
	
	//Fixed Cost of Operations
	public static final String communicationOverheadCostMultiplier = "1";
	public static final String fuelUnitsCostMultiplier = "1";
	public static final String unclearedSquareCostMultiplier = "3";
	public static final String destructionOfProtectedTreeCostMultiplier = "10";
	public static final String repairingPaintDamageCostMultiplier = "2";
	
	//Fixed Fuel Units for types of land cleared
	public static final String fuelUsageClearingPlainLand = "1";
	public static final String fuelUsageClearingVisitedLand = "1";
	public static final String fuelUsageRockyLand = "2";
	public static final String fuelUsageClearingTreeLand = "2";
	
	//OUTPUT Messages
	public static final String ITEM = "\nItem\t\t\t\t";
	public static final String QUANTITY = "Quantity\t";
	public static final String COST = "Cost";	
	public static final String COMMUNICATION_OVERHEAD ="\nCommunication Overhead\t\t   ";
	public static final String FUEL_USAGE ="\nFuel Usage\t\t\t   ";
	public static final String UNCLEARED_SQUARES ="\nUncleared Squares\t\t   ";
	public static final String DESTRUCTION_OF_PROTECTED_TREE ="\nDestruction of Protected Tree\t   ";
	public static final String PAINT_DAMAGE_TO_BULLDOZER ="\nPaint Damage to Bulldozer\t   ";
	public static final String SEPARATOR ="\n----";
	public static final String TOTAL ="\nTotal\t\t\t\t\t\t ";
	public static final String SPACE_BETWEEN_QTY_AND_COST="\t\t ";
	
	
	
}
