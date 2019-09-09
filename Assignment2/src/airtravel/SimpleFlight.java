/*******************************************************************************
 * @file 	Leg.java
 * @author 	Michael Thompson (mjt106)
 * @date 	9/8/2019
 * @details	This file outlines the Simple Flight class
 ******************************************************************************/

package airtravel;

import java.time.LocalTime;

public final class SimpleFlight extends AbstractFlight
{
	
	private final String m_code;
	
	private final Leg m_leg;
	
	private final FlightSchedule m_flightSchedule;
	
	/*
	 * @brief Private contsructor
	 * @param[in] _code: the code of the airport
	 * @param[in] _leg: the leg for the flight
	 * @param[in] _flightSchedule: the schedule for that flight
	 * @returns This method performs an action and does not return a value
	 */
	private SimpleFlight(
			String _code,
			Leg _leg,
			FlightSchedule _flightSchedule)
	{
		m_code = _code;
		m_leg = _leg;
		m_flightSchedule = _flightSchedule;
	}
	
	/*
	 * @brief Builder method for @SimpleFlight class
	 * @param[in] _code: the code of the airport
	 * @param[in] _leg: the leg for the flight
	 * @param[in] _flightSchedule: the schedule for that flight
	 * @returns A constructed SimpleFlight object
	 */
	public static final SimpleFlight of(
			String _code,
			Leg _leg,
			FlightSchedule _flightSchedule)
	{
		if(_code == null || _leg == null || _flightSchedule == null)
		{
			throw new NullPointerException("Parameters cannot be null");
		}
		else
		{
			return new SimpleFlight(_code, _leg, _flightSchedule);
		}
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

	@Override
	public Airport origin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Airport destination() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime departureTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime arrivalTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isShort() {
		// TODO Auto-generated method stub
		return false;
	}

}
