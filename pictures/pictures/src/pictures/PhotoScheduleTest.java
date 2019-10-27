/*******************************************************************************
 * @file 	PhotoScheduleTest.java
 * @author 	Michael Thompson (mjt106)
 * @date 	10/15/2019
 * @details	This file tests the PhotoSchedule class
 ******************************************************************************/

package pictures;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PhotoScheduleTest 
{
	private PhotoTime time1;
	private PhotoTime time2;
	private PhotoTime time3;
	
	private SortedSet<PhotoTime> s1;
	
	private PhotoSchedule sampleObject;
	private PhotoSchedule.TestHook sampleTestObject;
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception
	{
		time1 = PhotoTime.of("a", 1, 2, 1);
		time2 = PhotoTime.of("b", 1, 3, 1);
		time3 = PhotoTime.of("c", 2, 3, 1);
		
		s1 = new TreeSet<PhotoTime>();
		s1.add(time1);
		s1.add(time2);
		s1.add(time3);
		
		sampleObject = PhotoSchedule.of(s1, LocalTime.ofSecondOfDay(0), LocalTime.ofSecondOfDay(4));
		sampleTestObject = sampleObject.new TestHook();
	}
	
	/*
	 * Structure basis: not empty set passed in
	 */
	@Test
	public void test_photoSchedule_allTrue()
	{
		sampleTestObject.PhotoScheduleConstructor(s1, LocalTime.ofSecondOfDay(1), LocalTime.ofSecondOfDay(2));
	}
	
	/*
	 * Structure basis: empty set passed in
	 */
	@Test
	public void test_photoSchedule_emptySet()
	{
		SortedSet<PhotoTime> s2 = new TreeSet<PhotoTime>();
		
		sampleTestObject.PhotoScheduleConstructor(s2, LocalTime.ofSecondOfDay(1), LocalTime.ofSecondOfDay(2));
	}
	
	/*
	 * Structure basis: all conditions true
	 * Boundary coverage: startTime isBefore endTime
	 */
	@Test
	public void test_validateStartEndTime_start_before_end()
	{
		sampleTestObject.validateStartEndTime(LocalTime.ofSecondOfDay(1), LocalTime.ofSecondOfDay(2));
	}
	
	/*
	 * Branch coverage: startTime isAfter endTime
	 * Boundary coverage: startTiem isAfter endTime
	 */
	@Test
	public void test_validateStartEndTime_start_after_end()
	{
		exceptionRule.expect(AssertionError.class);
		sampleTestObject.validateStartEndTime(LocalTime.ofSecondOfDay(2), LocalTime.ofSecondOfDay(1));
	}
	
	/*
	 * Branch coverage: startTime equals endTime
	 * Boundary coverage: startTime equals endTime
	 */
	@Test
	public void test_validateStartEndTime_start_equals_end()
	{
		exceptionRule.expect(AssertionError.class);
		sampleTestObject.validateStartEndTime(LocalTime.ofSecondOfDay(1), LocalTime.ofSecondOfDay(1));
	}
	
	/*
	 * Structure basis: all inputs valid
	 */
	@Test
	public void test_builder1_validInputs()
	{
		PhotoSchedule.of(s1, LocalTime.ofSecondOfDay(1), LocalTime.ofSecondOfDay(2));
	}
	
	/*
	 * Branch coverage: photoTimes is null
	 */
	@Test
	public void test_builder1_photoTimes_null()
	{
		exceptionRule.expect(NullPointerException.class);
		PhotoSchedule.of(null, LocalTime.ofSecondOfDay(1), LocalTime.ofSecondOfDay(2));
	}
	
	/*
	 * Branch coverage: startTime is null
	 */
	@Test
	public void test_builder1_startTime_null()
	{
		exceptionRule.expect(NullPointerException.class);
		PhotoSchedule.of(s1, null, LocalTime.ofSecondOfDay(2));
	}
	
	/*
	 * Branch coverage: endTime is null
	 */
	@Test
	public void test_builder1_endTime_null()
	{
		exceptionRule.expect(NullPointerException.class);
		PhotoSchedule.of(s1, LocalTime.ofSecondOfDay(1), null);
	}
	
	/*
	 * Structure basis: all inputs valid
	 */
	@Test
	public void test_builder2_validInputs()
	{
		PhotoSchedule.of(LocalTime.ofSecondOfDay(1), LocalTime.ofSecondOfDay(2));
	}
	
	/*
	 * Branch coverage: startTime is null
	 */
	@Test
	public void test_builder2_startTime_null()
	{
		exceptionRule.expect(NullPointerException.class);
		PhotoSchedule.of(null, LocalTime.ofSecondOfDay(2));
	}
	
	/*
	 * Branch coverage: endTime is null
	 */
	@Test
	public void test_builder2_endTime_null()
	{
		exceptionRule.expect(NullPointerException.class);
		PhotoSchedule.of(LocalTime.ofSecondOfDay(1), null);
	}
	
	/*
	 * Branch coverage: photo is null
	 */
	@Test
	public void test_addPhotoTime_null()
	{
		exceptionRule.expect(NullPointerException.class);
		sampleObject.addPhotoTime(null);
	}
	
	/*
	 * Structure basis: all inputs are true
	 * Boundary coverage: PhotoTime.startTime isAfter PhotoSchedule.startTime
	 * 					  PhotoTime.endTime isBefore PhotoSchedule.endTime
	 */
	@Test
	public void test_addPhotoTime_all_true()
	{
		PhotoSchedule tempSch = PhotoSchedule.of(LocalTime.ofSecondOfDay(2), LocalTime.ofSecondOfDay(5));
		PhotoTime tempTime = PhotoTime.of("a", 3, 4, 1);
		
		assertTrue(tempSch.addPhotoTime(tempTime));
	}
	
	/*
	 * Branch coverage: !PhotoTime.startTime isAfter PhotoSchedule.startTime and
	 * 				    PhotoTime.endTime isBefore PhotoSchedule.endTime
	 * Boundary coverage: PhotoTime.startTime isBefore PhotoSchedule.startTime
	 * 					  PhotoTime.endTime isBefore PhotoSchedule.endTime
	 */
	@Test
	public void test_addPhotoTime_bad_start_time()
	{
		PhotoSchedule tempSch = PhotoSchedule.of(LocalTime.ofSecondOfDay(2), LocalTime.ofSecondOfDay(5));
		PhotoTime tempTime = PhotoTime.of("a", 1, 4, 1);
		
		assertFalse(tempSch.addPhotoTime(tempTime));
	}
	
	/*
	 * Branch coverage: PhotoTime.startTime isAfter PhotoSchedule.startTime and
	 * 				    !PhotoTime.endTime isBefore PhotoSchedule.endTime
	 * Boundary coverage: PhotoTime.startTime isAfter PhotoSchedule.startTime
	 * 					  PhotoTime.endTime isAfter PhotoSchedule.endTime
	 */
	@Test
	public void test_addPhotoTime_bad_end_time()
	{
		PhotoSchedule tempSch = PhotoSchedule.of(LocalTime.ofSecondOfDay(2), LocalTime.ofSecondOfDay(5));
		PhotoTime tempTime = PhotoTime.of("a", 3, 6, 1);
		
		assertFalse(tempSch.addPhotoTime(tempTime));
	}
	
	/*
	 * Branch coverage: !PhotoTime.startTime isAfter PhotoSchedule.startTime and
	 * 				    !PhotoTime.endTime isBefore PhotoSchedule.endTime
	 * Boundary coverage: PhotoTime.startTime isBefore PhotoSchedule.startTime
	 * 					  PhotoTime.endTime isAfter PhotoSchedule.endTime
	 */
	@Test
	public void test_addPhotoTime_bad_both_times()
	{
		PhotoSchedule tempSch = PhotoSchedule.of(LocalTime.ofSecondOfDay(2), LocalTime.ofSecondOfDay(5));
		PhotoTime tempTime = PhotoTime.of("a", 1, 6, 1);
		
		assertFalse(tempSch.addPhotoTime(tempTime));
	}
	
	/*
	 * Boundary coverage: PhotoTime.startTime equals PhotoSchedule.startTime
	 */
	@Test
	public void test_addPhotoTime_equal_start()
	{
		PhotoSchedule tempSch = PhotoSchedule.of(LocalTime.ofSecondOfDay(1), LocalTime.ofSecondOfDay(3));
		PhotoTime tempTime = PhotoTime.of("a", 1, 2, 1);
		
		assertFalse(tempSch.addPhotoTime(tempTime));
	}
	
	/*
	 * Boundary coverage: PhotoTime.endTime equals PhotoSchedule.endTime
	 */
	@Test
	public void test_addPhotoTime_equal_end()
	{
		PhotoSchedule tempSch = PhotoSchedule.of(LocalTime.ofSecondOfDay(1), LocalTime.ofSecondOfDay(3));
		PhotoTime tempTime = PhotoTime.of("a", 2, 3, 1);
		
		assertFalse(tempSch.addPhotoTime(tempTime));
	}
	
	/*
	 * Structure basis: all conditions true
	 */
	@Test
	public void test_removePhotoTime_photo_in_list()
	{
		assertTrue(sampleObject.removePhotoTime(time1));
	}
	
	/*
	 * Branch coverage: _photo is null
	 */
	@Test
	public void test_removePhotoTime_null()
	{
		exceptionRule.expect(NullPointerException.class);
		sampleObject.removePhotoTime(null);
	}
	
	/*
	 * Branch coverage: _photo is not in the list
	 */
	@Test
	public void test_removePhotoTime_photo_not_in_list()
	{
		PhotoTime temp = PhotoTime.of("e", 1, 4, 1);
		assertFalse(sampleObject.removePhotoTime(temp));
	}
	
	/*
	 * Structure basis: no errors thrown
	 */
	@Test
	public void test_schedule()
	{
		sampleObject.schedule();
	}
}
