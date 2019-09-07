/*******************************************************************************
 * @file 	Leg.java
 * @author 	Michael Thompson (mjt106)
 * @date 	9/7/2019
 * @details	This file outlines the FlightSchedule class
 ******************************************************************************/

package airtravel;

import java.time.LocalTime;

public final class FlightSchedule 
{
	/*
	 * @brief the departure time for the flight
	 */
	private final LocalTime m_departureTime;
	
	/*
	 * @brief The arrival time for the flight
	 */
	private final LocalTime m_arrivalTime;
	
	/*
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
	
	/*
	 * @brief Builder method for @FlightSchedule class
	 * @param[in] _departureTime: the departure time used for the FlightSchedule
	 * @param[in] _arrivalTime: the arrival time used for the FlightSchedule
	 * @returns A constructed FlightSchedule object
	 */
	public static final FlightSchedule of(
			LocalTime _departureTime,
			LocalTime _arrivalTime)
	{
		if(_departureTime == null || _arrivalTime == null)
		{
			throw new NullPointerException("Parameters cannot be null");
		}
		else if(_arrivalTime.isBefore(_departureTime))
		{
			throw new IllegalArgumentException("departure time must preceed "
					+ "arrival time");
		}
		else
		{
			return new FlightSchedule(_departureTime, _arrivalTime);
		}
	}
	
	/*
	 * @brief Simple getter method
	 * @returns @m_departureTime of the object
	 */
	public LocalTime getDepartureTime()
	{
		return m_departureTime;
	}
	
	/*
	 * @brief Simple getter method
	 * @returns @m_arrivalTime of the object
	 */
	public LocalTime getArrivalTime()
	{
		return m_arrivalTime;
	}
}
