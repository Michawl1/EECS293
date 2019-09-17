/*******************************************************************************
 * @file 	SimpleFlight.java
 * @author 	Michael Thompson (mjt106)
 * @date 	9/8/2019
 * @details	This file outlines the Simple Flight class
 ******************************************************************************/

package airtravel;

import java.util.Objects;

public final class SimpleFlight extends AbstractFlight
{
	/*
	 * @brief The code used for the airport identifier
	 */
	private final String m_code;
	
	/*
	 * @brief The leg the flight will partake
	 */
	private final Leg m_leg;
	
	/*
	 * @brief The schedule for the flight
	 */
	private final FlightSchedule m_flightSchedule;
	
	/*
	 * @brief Tracks the seats on the flight
	 */
	private final SeatConfiguration m_seatsAvailable;
	
	/*
	 * @brief Private constructor
	 * @param[in] _code: the code of the airport
	 * @param[in] _leg: the leg for the flight
	 * @param[in] _flightSchedule: the schedule for that flight
	 * @param[in] _seatsAvaible: the seat tracker for that flight
	 * @returns This method performs an action and does not return a value
	 */
	private SimpleFlight(
			String _code,
			Leg _leg,
			FlightSchedule _flightSchedule,
			SeatConfiguration _seatsAvailable)
	{
		m_code = _code;
		m_leg = _leg;
		m_flightSchedule = _flightSchedule;
		m_seatsAvailable = _seatsAvailable;
	}
	
	/*
	 * @brief Builder method for @SimpleFlight class
	 * @param[in] _code: the code of the airport
	 * @param[in] _leg: the leg for the flight
	 * @param[in] _flightSchedule: the schedule for that flight
	 * @param[in] _seatsAvaible: the seat tracker for that flight
	 * @returns A constructed SimpleFlight object
	 */
	public static final SimpleFlight of(
			String _code,
			Leg _leg,
			FlightSchedule _flightSchedule,
			SeatConfiguration _seatsAvailable)
	{
		_code = Objects.requireNonNull(_code, "Parameter _code cannot be null");
		_leg = Objects.requireNonNull(_leg, "Parameter _leg cannot be null");
		_flightSchedule = Objects.requireNonNull(_flightSchedule, "Parameter _flightSchedule cannot be null");
		_seatsAvailable = Objects.requireNonNull(_seatsAvailable, "Parameter _seatsAvaiable cannot be null");
		
		SimpleFlight temp = new SimpleFlight(_code, _leg, _flightSchedule, _seatsAvailable);
		
		_leg.getOrigin().addFlight(temp);
	
		return temp;	
	}
	
	/*
	 * @brief Simple getter method
	 * @returns @m_code of the object
	 */
	@Override
	public String getCode()
	{
		return m_code;
	}

	/*
	 * @brief Simple getter method
	 * @returns @m_leg of the object
	 */
	@Override 
	public Leg getLeg()
	{
		return m_leg;
	}

	/*
	 * @brief Simple getter method
	 * @returns @m_flightSchedule of the object
	 */
	@Override
	public FlightSchedule getFlightSchedule()
	{
		return m_flightSchedule;
	}

}
