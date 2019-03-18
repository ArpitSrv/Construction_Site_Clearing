package com.oracle.assignment.construction.test.TestSuiteRunner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.oracle.assignment.construction.test.calculation.ProcessingTestSuite;
import com.oracle.assignment.construction.test.controller.InputOutputTestSuite;
import com.oracle.assignment.construction.test.utility.TestStringUtility;

public class TestRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(InputOutputTestSuite.class,ProcessingTestSuite.class, TestStringUtility.class);

      for (Failure failure : result.getFailures()) {
         System.out.println("\nOutput of Test Suite Run is : "+failure.toString());
      }
		
      if(result.wasSuccessful())
    	  System.out.println("\nTest Suite ran successfully ");
   }
} 