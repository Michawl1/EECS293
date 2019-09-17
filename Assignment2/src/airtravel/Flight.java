/*******************************************************************************
 * @file 	Flight.java
 * @author 	Michael Thompson (mjt106)
 * @date 	9/8/2019
 * @details	This file outlines the Flight Interface
 ******************************************************************************/

package airtravel;

import java.time.Duration;
import java.time.LocalTime;

public interface Flight 
{
	public String getCode();
	
	public Leg getLeg();
	
	public FlightSchedule getFlightSchedule();
	
	public Airport origin();
	
	public Airport destination();
	
	public LocalTime departureTime();
	
	public LocalTime arrivalTime();
	
	public boolean isShort(Duration _durationMax);
	
	public SeatConfiguration seatsAvailable(FareClass _fareClass);
	
	public boolean hasSeats(FareClass _fareClass);
}
