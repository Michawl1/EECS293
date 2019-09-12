/*******************************************************************************
 * @file 	AbstractFlight.java
 * @author 	Michael Thompson (mjt106)
 * @date 	9/8/2019
 * @details	This file outlines the part of a Flight
 ******************************************************************************/

package airtravel;

import java.time.Duration;
import java.time.LocalTime;

public abstract class AbstractFlight implements Flight
{

	/*
	 * @brief Default Constructor
	 * @returns This method performs an action and does not return a value
	 */
	public AbstractFlight() 
	{
		
	}

	public abstract String getCode();
	
	public abstract Leg getLeg();
	
	public abstract FlightSchedule getFlightSchedule();
	
	public Airport origin()
	{
		return getLeg().getOrigin();
	}
	
	public Airport destination()
	{
		return getLeg().getDestination();
	}
	
	public LocalTime departureTime()
	{
		return getFlightSchedule().getDepartureTime();
	}
	
	public LocalTime arrivalTime()
	{
		return getFlightSchedule().getArrivalTime();
	}
	
	public boolean isShort(Duration _durationMax)
	{		
		return getFlightSchedule().isShort(_durationMax);
	}
}
