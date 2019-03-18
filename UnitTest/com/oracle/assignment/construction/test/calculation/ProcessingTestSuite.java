package com.oracle.assignment.construction.test.calculation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   TestCalculateIndividualAndTotalCost.class,
   TestMarkFieldAsCovered.class,
   TestSizeOfField.class,
   TestUnitsOfOperations.class
})

public class ProcessingTestSuite {

}
