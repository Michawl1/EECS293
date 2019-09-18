/*******************************************************************************
 * @file 	AbstractFlight.java
 * @author 	Michael Thompson (mjt106)
 * @date 	9/8/2019
 * @details	This file outlines the part of a Flight
 ******************************************************************************/

package airtravel;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public abstract class AbstractFlight implements Flight
{
	public abstract String getCode();
	
	public abstract Leg getLeg();
	
	public abstract FlightSchedule getFlightSchedule();
	
	public abstract SeatConfiguration seatsAvailable(FareClass _fareClass);
	
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
		_durationMax = Objects.requireNonNull(_durationMax, "Parameter cannot be null");
		
		return getFlightSchedule().isShort(_durationMax);
	}
	
	public boolean hasSeats(FareClass _fareClass)
	{
		_fareClass = Objects.requireNonNull(_fareClass, "Parameter cannot be null");
		
		return seatsAvailable(_fareClass).hasSeats();
	}
	
}
