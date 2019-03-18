package com.oracle.assignment.construction.test.calculation;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.oracle.assignment.construction.controller.SiteSimulationControllerHelper;
import com.oracle.assignment.construction.model.Layout;

/*This test is used to assert the length and breadth of the field from the map input
 * 
 */

public class TestSizeOfField {
	protected int length=10;
	protected int breadth=5;
	Layout layout = SiteSimulationControllerHelper.populateLayoutFromInputFile();
		   
	@Test
   public void testFieldSize() {
		assertEquals(length, layout.getWidth());
		assertEquals(breadth, layout.getDepth());
   }
}
