/*******************************************************************************
 * @file 	PhotoSchedule.java
 * @author 	Michael Thompson (mjt106)
 * @date 	10/15/2019
 * @details	This file outlines the photo schedule class
 ******************************************************************************/

package pictures;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

import weightedjobschedule.WeightedJobSchedulable;
import weightedjobschedule.WeightedJobScheduler;

public final class PhotoSchedule 
{
	/**
	 * a SortedSet<PhotoTime> used for manipulation
	 */
	private SortedSet<WeightedJobSchedulable> m_photoTimes;
	
	/**
	 * The start time for the day
	 */
	private final LocalTime m_startTime;
	
	/**
	 * The end time for the day
	 */
	private final LocalTime m_endTime;
	
	/**
	 * @brief Constructor
	 * @param _photoTimes: a SortedSet<PhotoTime> used for manipulation
	 * @param _startTime: a LocalTime used to define the start time for the day
	 * @param _endTime: a LocalTime used to define the end time for the day
	 */
	private PhotoSchedule(
			SortedSet<PhotoTime> _photoTimes,
			LocalTime _startTime,
			LocalTime _endTime)
	{		
		m_startTime = _startTime;
		m_endTime = _endTime;
		
		m_photoTimes = new TreeSet<WeightedJobSchedulable>();
		
		for(PhotoTime photo : _photoTimes)
		{
			this.addPhotoTime(photo);
		}

	}
	
	/**
	 * @brief asserts the _startTime is before _endTime
	 * @param _startTime: a LocalTime for the start time
	 * @param _endTime: a LocalTime for the end time
	 */
	private static void validateStartEndTime(
			LocalTime _startTime,
			LocalTime _endTime)
	{
		assert _startTime.isBefore(_endTime) : "_startTime must be before _endTime";
	}
	
	/**
	 * @brief Builder method for PhotoSchedule
	 * @param _startTime: a LocalTime used to define the start time for the day
	 * @param _endTime: a LocalTime used to define the end time for the day
	 * @return a new PhotoSchedule object
	 */
	public static final PhotoSchedule of(
			LocalTime _startTime,
			LocalTime _endTime)
	{
		Objects.requireNonNull(_startTime, "Parameter _startTime cannot be null");
		Objects.requireNonNull(_endTime, "Paramter _endTime cannot be null");
		validateStartEndTime(_startTime, _endTime);
		
		return new PhotoSchedule(new TreeSet<PhotoTime>(), _startTime, _endTime);
	}
	
	/**
	 * @brief Builder method for the PhotoSchedule
	 * @param _photoTimes: a SortedSet<PhotoTime> used for manipulation
	 * @param _startTime: a LocalTime used to define the start time for the day
	 * @param _endTime: a LocalTime used to define the end time for the day
	 * @return a new PhotoSchedule object
	 */
	public static final PhotoSchedule of(
			SortedSet<PhotoTime> _photoTimes,
			LocalTime _startTime,
			LocalTime _endTime)
	{
		Objects.requireNonNull(_photoTimes, "Paramter _photoTimes cannot be null");
		Objects.requireNonNull(_startTime, "Parameter _startTime cannot be null");
		Objects.requireNonNull(_endTime, "Paramter _endTime cannot be null");
		validateStartEndTime(_startTime, _endTime);
		
		return new PhotoSchedule(_photoTimes, _startTime, _endTime);
	}
	
	/**
	 * @return the m_photoTimes
	 */
	public SortedSet<WeightedJobSchedulable> getM_photoTimes() {
		return m_photoTimes;
	}

	/**
	 * @param _photo: the PhotoTime to add
	 * @return if adding worked successfully
	 */
	public boolean addPhotoTime(PhotoTime _photo)
	{
		Objects.requireNonNull(_photo, "Paramter cannot be null");
		
		if(_photo.getM_startTime().isAfter(m_startTime) && _photo.getM_endTime().isBefore(m_endTime))
		{
			return m_photoTimes.add(_photo);
		}
		
		return false;
	}
	
	/**
	 * @param _photo: the PhotoTime to remove
	 * @return if remove worked successfully
	 */
	public boolean removePhotoTime(WeightedJobSchedulable _photo)
	{
		Objects.requireNonNull(_photo, "Parameter cannot be null");
		
		return m_photoTimes.remove(_photo);
	}
	
	/**
	 * @return the optimal schedule of PhotoTimes
	 */
	public List<WeightedJobSchedulable> schedule()
	{
		return WeightedJobScheduler.weightedJobSchedule(m_photoTimes);
	}
	
	/**
	 * Inner class that will be used to test private methods
	 */
	public class TestHook
	{
		public PhotoSchedule PhotoScheduleConstructor(			
				SortedSet<PhotoTime> _photoTimes,
				LocalTime _startTime,
				LocalTime _endTime)
		{
			return new PhotoSchedule(_photoTimes, _startTime, _endTime);
		}
		
		public void validateStartEndTime(
				LocalTime _startTime,
				LocalTime _endTime)
		{
			PhotoSchedule.validateStartEndTime(_startTime, _endTime);
		}
	}
}
