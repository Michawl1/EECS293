/*******************************************************************************
 * @file 	PhotoTime.java
 * @author 	Michael Thompson (mjt106)
 * @date 	10/15/2019
 * @details	This file tests the PhotoTime class
 ******************************************************************************/

package pictures;


import weightedjobschedule.WeightedJobSchedulable;
import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PhotoTimeTest 
{
	private PhotoTime sampleObject;
	private PhotoTime.TestHook sampleTestObject;
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception
	{
		sampleObject = PhotoTime.of("A", 1, 2, 1);
		sampleTestObject = sampleObject.new TestHook();
	}
	
	/*
	 * Structured basis: no errors thrown
	 * Good data: no errors thrown
	 */
	@Test
	public void test_constructor()
	{
		sampleTestObject.PhotoTimeConstructor("A", LocalTime.ofSecondOfDay(1), LocalTime.ofSecondOfDay(2), 1);
	}
	
	/*
	 * Structured basis: no errors thrown
	 * Good data: no errors thrown
	 * Branch Coverage: value > 0
	 * Boundary: value > 0
	 */
	@Test
	public void test_validatePriority_positive_value()
	{
		sampleTestObject.validatePriority(1);
	}
	
	/*
	 * Structured basis: no errors thrown
	 * Good Data: no errors thrown
	 * Branch Coverage: value = 0
	 * Boundary: value = 0
	 */
	@Test
	public void test_validatePriority_zero_value()
	{
		sampleTestObject.validatePriority(0);
	}
	
	/*
	 * Bad data: AssertionError thrown
	 * Branch Coverage: value < 0
	 * Boundary: value < 0
	 */
	@Test
	public void test_validatePriority_negative_value()
	{
		exceptionRule.expect(AssertionError.class);
		sampleTestObject.validatePriority(-1);
	}
	
	/*
	 * Structure basis: no errors thrown
	 * Good Data: no errors thrown
	 * Boundary coverage: _startTime.isBefore(_endTime)
	 */
	@Test
	public void test_validateStartEndTime_in_order()
	{
		sampleTestObject.validateStartEndTIme(LocalTime.ofSecondOfDay(1), LocalTime.ofSecondOfDay(2));
	}
	
	/*
	 * Bad data: AsertionError thrown
	 * Boundary coverage: _startTime.isAfter(_endTime)
	 */
	@Test
	public void test_validateStartEndTime_reverse_order()
	{
		exceptionRule.expect(AssertionError.class);
		sampleTestObject.validateStartEndTIme(LocalTime.ofSecondOfDay(2), LocalTime.ofSecondOfDay(1));
	}
	
	/*
	 * Bad data: AssertionError thrown
	 * Boundary coverage: _startTime.equals(_endTime)
	 */
	@Test
	public void test_validateStartEndTime_equal_order()
	{
		exceptionRule.expect(AssertionError.class);
		sampleTestObject.validateStartEndTIme(LocalTime.ofSecondOfDay(1), LocalTime.ofSecondOfDay(1));
	}

	/*
	 * Structure basis: no errors thrown
	 * Good Data: no errors thrown
	 */
	@Test
	public void test_builder1_valid_inputs()
	{
		PhotoTime.of("A", LocalTime.ofSecondOfDay(1), LocalTime.ofSecondOfDay(2), 1);
	}
	
	/*
	 * Branch Coverage: _landMark null
	 */
	@Test
	public void test_builder1_landMark_null()
	{
		exceptionRule.expect(NullPointerException.class);
		PhotoTime.of(null, LocalTime.ofSecondOfDay(1), LocalTime.ofSecondOfDay(2), 1);
	}
	
	/*
	 * Branch Coverage: _startTime null
	 */
	@Test
	public void test_builder1_startTime_null()
	{
		exceptionRule.expect(NullPointerException.class);
		PhotoTime.of("a", null, LocalTime.ofSecondOfDay(2), 1);
	}
	
	/*
	 * Branch Coverage: _endTime null
	 */
	@Test
	public void test_builder1_endTime_null()
	{
		exceptionRule.expect(NullPointerException.class);
		PhotoTime.of("a", LocalTime.ofSecondOfDay(1), null, 1);
	}
	
	/*
	 * Branch Coverage: _priority null
	 */
	@Test
	public void test_builder1_priority_null()
	{
		exceptionRule.expect(NullPointerException.class);
		PhotoTime.of("a", LocalTime.ofSecondOfDay(1), LocalTime.ofSecondOfDay(2), null);
	}
	
	/*
	 * Branch Coverage: _landMark null
	 */
	@Test
	public void test_builder2_landMark_null()
	{
		exceptionRule.expect(NullPointerException.class);
		PhotoTime.of(null, 1, 2, 1);
	}
	
	/*
	 * Branch Coverage: _startTime null
	 */
	@Test
	public void test_builder2_startTime_null()
	{
		exceptionRule.expect(NullPointerException.class);
		PhotoTime.of("a", null, 2, 1);
	}
	
	/*
	 * Branch Coverage: _endTime null
	 */
	@Test
	public void test_builder2_endTime_null()
	{
		exceptionRule.expect(NullPointerException.class);
		PhotoTime.of("a", 1, null, 1);
	}
	
	/*
	 * Branch Coverage: _priority null
	 */
	@Test
	public void test_builder2_priority_null()
	{
		exceptionRule.expect(NullPointerException.class);
		PhotoTime.of("a", 1, 2, null);
	}
	
	/*
	 * Structure basis: getValue is the same as the constructed value
	 */
	@Test
	public void test_value()
	{
		assertEquals(Integer.valueOf(1), sampleObject.value());
	}
	
	/*
	 * Structure basis: getLinkedObject is the same as the passed in value
	 */
	@Test
	public void test_linkedPredecessor()
	{
		PhotoTime temp = PhotoTime.of("b", 2, 3, 4);
		sampleObject.linkPredecessor(temp);
		
		assertEquals(temp, sampleObject.linkedObject());
	}
	
	/*
	 * Structure basis: one overlaps two
	 * Boundary Coverage: one.endtime is after two.starttime
	 * 					  one.starttime is before two.endtime
	 */
	@Test
	public void test_doesOverlap_code_coverage()
	{
		PhotoTime one = PhotoTime.of("a", 1, 3, 1);
		PhotoTime two = PhotoTime.of("b", 2, 4, 1);
		
		assertTrue(one.doesOverlap(two));
	}
	
	/*
	 * Structure basis: one does not overlap two
	 * Boundary Coverage: one.endtime is after two.starttime
	 * 					  one.starttime is after two.endtime
	 */
	@Test
	public void test_doesOverlap_code_compound1()
	{
		PhotoTime one = PhotoTime.of("a", 3, 4, 1);
		PhotoTime two = PhotoTime.of("b", 1, 2, 1);
		
		assertFalse(one.doesOverlap(two));
	}
	
	/*
	 * Structure basis: one does not overlap two
	 * Boundary Coverage: one.endtime is before two.starttime
	 * 					  one.starttime is before two.endtime
	 */
	@Test
	public void test_doesOverlap_code_compound2()
	{
		PhotoTime one = PhotoTime.of("a", 1, 2, 1);
		PhotoTime two = PhotoTime.of("b", 3, 4, 1);
		
		assertFalse(one.doesOverlap(two));
	}
	
	/*
	 * Structure basis: one overlaps two
	 * Boundary Coverage: one.endtime equals two.starttime
	 */
	@Test
	public void test_doesOverlap_equals_oneEnd_twoStart()
	{
		PhotoTime one = PhotoTime.of("a", 1, 2, 1);
		PhotoTime two = PhotoTime.of("b", 2, 3, 1);
		
		assertTrue(one.doesOverlap(two));
	}
	
	/*
	 * Structure basis: one overlaps two
	 * Boundary Coverage: one.starttime equals two.endtime
	 */
	@Test
	public void test_doesOverlap_equals_oneStart_twoEnd()
	{
		PhotoTime one = PhotoTime.of("a", 2, 3, 1);
		PhotoTime two = PhotoTime.of("b", 1, 2, 1);
		
		assertTrue(one.doesOverlap(two));
	}
	
	/*
	 * Structure basis: one is not the same type as two
	 * Boundary Coverage: one.getClass is not two.getClass
	 */
	@Test
	public void test_doesOverlap_assertion()
	{
		/*
		 * test class used to test types for assertions
		 */
		class helperClass implements weightedjobschedule.WeightedJobSchedulable
		{

			@Override
			public Integer value() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public WeightedJobSchedulable linkedObject() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean doesOverlap(WeightedJobSchedulable _otherWeightedJobSchedulable) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void linkPredecessor(WeightedJobSchedulable _newLink) {
				// TODO Auto-generated method stub
				
			}
			
		}
		
		PhotoTime one = PhotoTime.of("a", 1, 2, 1);
		helperClass two = new helperClass();
		
		exceptionRule.expect(AssertionError.class);
		one.doesOverlap(two);
	}
	
	/*
	 * Structure basis: no errors thrown
	 * Good Data: no errors thrown
	 */
	@Test
	public void test_linkObject_validInput()
	{
		sampleObject.linkPredecessor(PhotoTime.of("b", 1, 2, 1));
	}
	
	/*
	 * Branch coverage: passed value is null
	 */
	@Test
	public void test_linkedObject_nullInput()
	{
		exceptionRule.expect(NullPointerException.class);
		sampleObject.linkPredecessor(null);
	}
	
	/*
	 * Branch coverage: passed value is null
	 */
	@Test
	public void test_compareTo_null()
	{
		exceptionRule.expect(NullPointerException.class);
		sampleObject.compareTo(null);
	}
	
	/*
	 * Branch coverage: one.endtime isBefore two.endtime
	 */
	@Test
	public void test_compareTo_oneBeforeTwo()
	{
		PhotoTime one = PhotoTime.of("a", 1, 2, 1);
		PhotoTime two = PhotoTime.of("b", 1, 3, 1);
		
		assertEquals(-1, one.compareTo(two));
	}
	
	/*
	 * Branch coverage: !one.endtime isBefore two.endtime
	 * 				    one.endtime isAfter two.endtime
	 */
	@Test
	public void test_compareTo_oneAfterTwo()
	{
		PhotoTime one = PhotoTime.of("a", 1, 3, 1);
		PhotoTime two = PhotoTime.of("b", 1, 2, 1);
		
		assertEquals(1, one.compareTo(two));
	}
	
	/*
	 * Branch coverage: one.value is greater than two.value
	 * Boundary coverage: one.value is greater than two.value
	 */
	@Test
	public void test_compareTo_oneValue_greaterThan_twoValue()
	{
		PhotoTime one = PhotoTime.of("a", 1, 2, 1);
		PhotoTime two = PhotoTime.of("b", 1, 2, 2);
		
		assertEquals(-1, one.compareTo(two));
	}
	
	/*
	 * Branch coverage: one.value is less than two.value
	 * Boundary coverage: one.value is less than two.value
	 */
	@Test
	public void test_compareTo_oneValue_lessThan_twoValue()
	{
		PhotoTime one = PhotoTime.of("a", 1, 2, 2);
		PhotoTime two = PhotoTime.of("b", 1, 2, 1);
		
		assertEquals(1, one.compareTo(two));
	}
	
	/*
	 * Branch coverage: one.value is equal two.value
	 * Boundary coverage: one.value is equal two.value
	 */
	@Test
	public void test_compareTo_oneValue_equal_twoValue_one_better_name()
	{
		PhotoTime one = PhotoTime.of("a", 1, 2, 1);
		PhotoTime two = PhotoTime.of("b", 1, 2, 1);
		
		assertEquals(-1, one.compareTo(two));
	}
}
