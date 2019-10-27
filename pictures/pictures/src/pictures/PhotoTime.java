/*******************************************************************************
 * @file 	PhotoTime.java
 * @author 	Michael Thompson (mjt106)
 * @date 	10/15/2019
 * @details	This file outlines the photo time class
 ******************************************************************************/

package pictures;

import java.time.LocalTime;
import java.util.Objects;

import weightedjobschedule.WeightedJobSchedulable;

/*
 * Photo Time will implement the interface Greedy First Searchable
 */
public final class PhotoTime implements WeightedJobSchedulable, Comparable<PhotoTime>
{
	/**
	 * The name for the landmark
	 */
	private final String m_landMark;
	
	/**
	 * The start time for the photo shoot
	 */
	private final LocalTime m_startTime;
	
	/**
	 * The end time for the photo shoot
	 */
	private final LocalTime m_endTime;
	
	/**
	 * The priority of the photo time
	 */
	private final Integer m_priority;
	
	/**
	 * linked list object
	 */
	private PhotoTime m_linkedPhotoTime;
	
	/**
	 * @brief Constructor
	 * @param _landMark: a String for the land mark
	 * @param _startTime: a LocalTime for the start time
	 * @param _endTime: a LocalTime for the end time
	 * @param _priority: an Integer for the priority
	 */
	private PhotoTime(
			String _landMark,
			LocalTime _startTime,
			LocalTime _endTime,
			Integer _priority)
	{
		m_landMark = _landMark;
		m_startTime = _startTime;
		m_endTime = _endTime;
		m_priority = _priority;
		m_linkedPhotoTime = null;
	}
		
	/**
	 * @brief asserts that the priority >= 0
	 * @param _priority: an Integer being checked as priority
	 */
	private static void validatePriority(Integer _priority)
	{
		assert _priority >= 0 : "_priority must be great than or equal to 0";
	}

	/**
	 * @brief asserts that _startTime is before _endTime
	 * @param _startTime: a LocalTime for the start time
	 * @param _endTime: a LocalTime for the end time
	 */
	private static void validateStartEndTime(
			LocalTime _startTime,
			LocalTime _endTime)
	{
		assert _startTime.isBefore(_endTime) : "Start Times must be before End Times";
	}

	/**
	 * @brief Builder method for PhotoTime
	 * @param _landMark: a String for the land mark
	 * @param _startTime: a LocalTime for the start time
	 * @param _endTime: a LocalTime for the end time
	 * @param _priority: an Integer for the priority
	 * @return a new PhotoTime object
	 */
	public static final PhotoTime of (
			String _landMark,
			LocalTime _startTime,
			LocalTime _endTime,
			Integer _priority)
	{
		Objects.requireNonNull(_landMark, "Parameter _landMark cannot be null");
		Objects.requireNonNull(_startTime, "Parameter _landMark cannot be null");
		Objects.requireNonNull(_endTime, "Parameter _landMark cannot be null");
		Objects.requireNonNull(_priority, "Parameter _landMark cannot be null");
		
		validatePriority(_priority);
		validateStartEndTime(_startTime, _endTime);
		
		return new PhotoTime(_landMark, _startTime, _endTime, _priority);
	}

	/**
	 * @brief Builder method for PhotoTime
	 * @param _landMark: a String for the land mark
	 * @param _startTime: an Integer for the start time in seconds from midnight
	 * @param _endTime: an Integer for the end time in seconds from midnight
	 * @param _priority: an Integer for the priority
	 * @return a new PhotoTime object
	 */
	public static final PhotoTime of(
			String _landMark,
			Integer _startTime,
			Integer _endTime,
			Integer _priority)
	{
		Objects.requireNonNull(_landMark, "Parameter _landMark cannot be null");
		Objects.requireNonNull(_startTime, "Parameter _landMark cannot be null");
		Objects.requireNonNull(_endTime, "Parameter _landMark cannot be null");
		Objects.requireNonNull(_priority, "Parameter _landMark cannot be null");
		
		validatePriority(_priority);
		LocalTime startTime = LocalTime.ofSecondOfDay(_startTime);
		LocalTime endTime = LocalTime.ofSecondOfDay(_endTime);
		validateStartEndTime(startTime, endTime);
		
		return new PhotoTime(_landMark, startTime, endTime, _priority);
	}

	/**
	 * @return the m_startTime
	 */
	public LocalTime getM_startTime() {
		return m_startTime;
	}

	/**
	 * @return the m_endTime
	 */
	public LocalTime getM_endTime() {
		return m_endTime;
	}
	
	/**
	 * @return the m_priority
	 */
	@Override
	public Integer value() 
	{
		return m_priority;
	}

	/**
	 * @return the m_linkedPhotoTime
	 */
	@Override
	public WeightedJobSchedulable linkedObject() 
	{
		return m_linkedPhotoTime;
	}

	/**
	 * @param _otherObject: a WeightedJobSchedulable to compare to see if it overlaps this
	 * @return whether the _otherObject overlaps this
	 */
	@Override
	public boolean doesOverlap(WeightedJobSchedulable _otherWeightedJobSchedulable) 
	{
		Objects.requireNonNull(_otherWeightedJobSchedulable, "Parameter cannot be null");
		assert _otherWeightedJobSchedulable.getClass().equals(PhotoTime.class) : "Parameter must be type PhotoTime";
		
		PhotoTime temp = (PhotoTime) _otherWeightedJobSchedulable;
		
		boolean argument1 = !( this.getM_endTime().isBefore(temp.getM_startTime()) );
		boolean argument2 = !( this.getM_startTime().isAfter(temp.getM_endTime()) );
		
		return argument1 && argument2;
	}

	/**
	 * @param _newLink: a WeightedJobSchedulable to link to this PhotoTime
	 */
	@Override
	public void linkPredecessor(WeightedJobSchedulable _newLink) 
	{
		Objects.requireNonNull(_newLink, "Paramter cannot be null");

		m_linkedPhotoTime = (PhotoTime) _newLink;		
	}
	
	/**
	 * @param _otherPhotoTime: the other PhotoTime to compare to
	 * @return 1 if this m_endTime is after other m_endTime
	 * 		   -1 if this m_endTime is before other m_endTime
	 * 	       if this m_endTime and other m_endTime are equal defer to value compareTo
	 */
	@Override
	public int compareTo(PhotoTime _otherPhotoTime)
	{		
		Objects.requireNonNull(_otherPhotoTime, "Paramter cannot be null");
		
		if(this.getM_endTime().isBefore(_otherPhotoTime.getM_endTime()))
		{
			return -1;
		}
		else if(this.getM_endTime().isAfter(_otherPhotoTime.getM_endTime()))
		{
			return 1;
		}
		else
		{
			int temp = this.value().compareTo(_otherPhotoTime.value());
			if(temp != 0)
			{
				return temp;
			}
			else
			{
				return this.toString().compareTo(_otherPhotoTime.toString());
			}
		}
	}	
	
	@Override
	public String toString() {
		return "PhotoTime [m_landMark=" + m_landMark + ", m_startTime=" + m_startTime + ", m_endTime=" + m_endTime
				+ ", m_priority=" + m_priority + "]";
	}

	/**
	 * Inner class that will be used to test private methods
	 */
	public class TestHook
	{
		public PhotoTime PhotoTimeConstructor(
				String _landMark,
				LocalTime _startTime,
				LocalTime _endTime,
				Integer _priority)
		{
			return new PhotoTime(_landMark, _startTime, _endTime, _priority);
		}
		
		public void validatePriority(
				Integer _priority)
		{
			PhotoTime.validatePriority(_priority);
		}
		
		public void validateStartEndTIme(
				LocalTime _startTime,
				LocalTime _endTime)
		{
			PhotoTime.validateStartEndTime(_startTime, _endTime);
		}
	}
}
