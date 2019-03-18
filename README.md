# Construction_Site_Clearing
This is a readme file with instructions to run the Construction Site Simulator.

The code of the simulator is available in GIT using the below GIT URLs -
Browser URL - https://github.com/ArpitSrv/Construction_Site_Clearing
Clone URL - https://github.com/ArpitSrv/Construction_Site_Clearing.git


Running the simulator -->

The simulator can be run by executing the SiteSimulationController.class file which will be located in 
'\Construction_Site_Clearing\bin\com\oracle\assignment\construction\controller\SiteSimulationController.class' 
location after build or compilation.
The java file is located in 
'\Construction_Site_Clearing\Construction_Site_Clearing\src\com\oracle\assignment\construction\controller\SiteSimulationController.java'
Any standard tool like eclipse or NetBeans can be used for easy build and running of the simulator.


Input File with Layout -->

An input file with the map layout should be provided. The squares of layout should not have space between letters.
The location of the input file containing the layout map is defined in LookupConfig.java with the key "INPUT_FILE".
Currently the input file location is given as 'C:\\'. It can be changed as per the convenience of user.
The input file will have to be placed at C:\ folder on the local machine of the user before running the simulator.
A sample input file is present in the git project as well.


Log path -->

The log path is defined in log4j.properties file with the key 'log' and the path is currently set at 'C:\\Users\\Public\\Documents'
Logging is done at DEBUG and INFO level. Only INFO level logs will be enabled in Production.
DEBUG level can be enabled in non production environments for developer's use.

