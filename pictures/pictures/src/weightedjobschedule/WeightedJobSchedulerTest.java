/*******************************************************************************
 * @file 	WeightedJobSchedulerTest.java
 * @author 	Michael Thompson (mjt106)
 * @date 	10/15/2019
 * @details	This file tests the WeightedJobScheduler class
 ******************************************************************************/

package weightedjobschedule;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import pictures.PhotoTime;

public class WeightedJobSchedulerTest
{
	private PhotoTime time1;
	private PhotoTime time2;
	private PhotoTime time3;
	private PhotoTime time4;

	private SortedSet<WeightedJobSchedulable> startList;
	private List<WeightedJobSchedulable> focusList;
	private List<Integer> valueList;
	
	private WeightedJobScheduler sampleObject;
	private WeightedJobScheduler.TestHook sampleTestObject;
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Before
	public void setUp()
	{
		time1 = PhotoTime.of("a", 1, 2, 1);
		time2 = PhotoTime.of("b", 3, 4, 2);
		time3 = PhotoTime.of("c", 1, 3, 2);
		time4 = PhotoTime.of("d", 2, 4, 1);
		
		sampleObject = new WeightedJobScheduler();
		sampleTestObject = sampleObject.new TestHook();
	}
	
	/*
	 * Structure basis: all conditions true
	 * Branch coverage: focusList(endIndex) not overlap job and totalValue > valueList(endIndex)
	 * 				    focusList(endIndex) overlaps job and totalValue <= valueList(endIndex)
	 * 				    focusList(endIndex) not overlap job and totalValue <= valueList(endIndex)
	 * Boundary coverage: totalValue > valueList(endIndex)
	 * 				      totalValue < valueList(endIndex)
	 * 					  totalValue = valueList(endIndex)
	 */
	@Test
	public void test_linkedBestPredecessor()
	{
		PhotoTime x1 = PhotoTime.of("x1", 1, 2, 1);
		PhotoTime x2 = PhotoTime.of("x2", 1, 2, 2);
		PhotoTime x3 = PhotoTime.of("x3", 1, 3, 1);
		PhotoTime x4 = PhotoTime.of("x4", 1, 4, 1);
		PhotoTime x5 = PhotoTime.of("x5", 1, 4, 2);
		PhotoTime x6 = PhotoTime.of("x6", 4, 5, 2);
		
		startList = new TreeSet<WeightedJobSchedulable>();
		startList.add(x1);
		startList.add(x2);
		startList.add(x3);
		startList.add(x4);
		startList.add(x5);
		startList.add(x6);
		
		focusList = new ArrayList<WeightedJobSchedulable>(startList);
		valueList = new ArrayList<Integer>(focusList.stream().map(n -> n.value()).collect(Collectors.toList()));
		
		List<Integer> returnList = new ArrayList<Integer>();
		returnList.add(1);
		returnList.add(2);
		returnList.add(1);
		returnList.add(1);
		returnList.add(2);
		returnList.add(4);	
		
		sampleTestObject.linkBestPredecessor(valueList, focusList, 5);
		assertEquals(valueList, returnList);
	}
	
	/*
	 * Structure basis: all conditions true
	 * Branch coverage: _valueList.get(i) > max is false
	 * Boundary coverage: _valueList.get(i) < max
	 * 					  _valueList.get(i) == max
	 */
	@Test
	public void test_maxValueIndex()
	{
		valueList = new ArrayList<Integer>();
		valueList.add(time1.value());
		valueList.add(time2.value());
		valueList.add(time3.value());
		valueList.add(time4.value());
		
		assertEquals(sampleTestObject.maxValueIndex(valueList), 1);
	}
	
	/*
	 * Structure basis: all conditions true
	 * Branch coverage: link.getLinkedObject == null
	 */
	@Test
	public void test_optimalSchedule_single_loop()
	{
		List<WeightedJobSchedulable> returnList = new ArrayList<WeightedJobSchedulable>();
		returnList.add(time2);
		returnList.add(time1);
		
		//all PhotoTimes link defaults to null
		time1.linkPredecessor(time2);
		
		assertEquals(sampleTestObject.optimalSchedule(time1), returnList);
	}
	
	/*
	 * Structure basis: all conditions true
	 * Boundary coverage: _sortedSet.size() > 0
	 */
	@Test
	public void test_weightedJobSchedule_filled_set()
	{
		SortedSet<WeightedJobSchedulable> s = new TreeSet<WeightedJobSchedulable>();
		s.add(time1);
		
		List<WeightedJobSchedulable> returnList = new ArrayList<WeightedJobSchedulable>();
		returnList.add(time1);
		
		assertEquals(WeightedJobScheduler.weightedJobSchedule(s), returnList);
	}
	
	/*
	 * Branch coverage: _sortedSet.size() <= 0
	 * Boundary coverage: _sortedSet.size() <= 0
	 */
	@Test
	public void test_weightedJobShcedule_empty_set()
	{
		WeightedJobScheduler.weightedJobSchedule(new TreeSet<WeightedJobSchedulable>());
	}
	
	/*
	 * Branch coverage: null pointer check
	 */
	@Test
	public void test_weightedJobSchedule_null()
	{
		exceptionRule.expect(NullPointerException.class);
		WeightedJobScheduler.weightedJobSchedule(null);
	}
}
