/*******************************************************************************
 * @file 	Leg.java
 * @author 	Michael Thompson (mjt106)
 * @date 	9/7/2019
 * @details	This file outlines the FlightSchedule class
 ******************************************************************************/

package airtravel;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public final class FlightSchedule 
{
	/**
	 * @brief the departure time for the flight
	 */
	private final LocalTime m_departureTime;
	
	/**
	 * @brief The arrival time for the flight
	 */
	private final LocalTime m_arrivalTime;
	
	/**
	 * @brief Private constructor
	 * @param[in] _departureTime: departure time for the flight
	 * @param[in] _arrivalTime: arrival time for the flight
	 * @returns This method performs an action and does not return a value
	 */
	private FlightSchedule(
			LocalTime _departureTime,
			LocalTime _arrivalTime)
	{
		m_departureTime = _departureTime;
		m_arrivalTime = _arrivalTime;
	}
	
	/**
	 * @brief Builder method for @FlightSchedule class
	 * @param[in] _departureTime: the departure time used for the FlightSchedule
	 * @param[in] _arrivalTime: the arrival time used for the FlightSchedule
	 * @returns A constructed FlightSchedule object
	 */
	public static final FlightSchedule of(
			LocalTime _departureTime,
			LocalTime _arrivalTime)
	{
		_departureTime = Objects.requireNonNull(_departureTime, "Parameter _depatureTime cannot be null");
		_arrivalTime = Objects.requireNonNull(_arrivalTime, "Paramter _arrivalTime cannot be null");
		
		if(_arrivalTime.isBefore(_departureTime))
		{
			throw new IllegalArgumentException("departure time must preceed " + "arrival time");
		}
		
		return new FlightSchedule(_departureTime, _arrivalTime);
	}
	
	/**
	 * @brief Simple getter method
	 * @returns @m_departureTime of the object
	 */
	public LocalTime getDepartureTime()
	{
		return m_departureTime;
	}
	
	/**
	 * @brief Simple getter method
	 * @returns @m_arrivalTime of the object
	 */
	public LocalTime getArrivalTime()
	{
		return m_arrivalTime;
	}
	
	/**
	 * @brief tells whether the flight is shorter than or equal to the given duration
	 * @param[in] _durationMax: the max duration of the flight
	 * @returns true if _durationMax is shorter than equal current duration
	 * 			false if _durationMax is longer than current duration
	 */
	public final boolean isShort(Duration _durationMax)
	{
		_durationMax = Objects.requireNonNull(_durationMax, "Paramter cannot be null");
		
		Duration totalDuration = Duration.between(m_departureTime, m_arrivalTime);
		
		return _durationMax.compareTo(totalDuration) < 0;
	}
}
