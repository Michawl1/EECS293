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
	/**
	 * @brief simple getter method
	 * @return a code for a flight
	 */
	public String getCode();
	
	/**
	 * @brief simple getter method
	 * @return the leg for a flight
	 */
	public Leg getLeg();
	
	/**
	 * @brief simple getter method
	 * @return the flight schedule for a flight
	 */
	public FlightSchedule getFlightSchedule();
	
	/**
	 * @brief behaves like a getter method
	 * @return the origin airport of a flight
	 */
	public Airport origin();
	
	/**
	 * @brief behaves like a getter method
	 * @return the destination airport of a flight
	 */
	public Airport destination();
	
	/**
	 * @brief behaves like a getter method
	 * @return the departure time of a flight
	 */
	public LocalTime departureTime();
	
	/**
	 * @brief behaves like a getter method
	 * @return the arrival time of a flight
	 */
	public LocalTime arrivalTime();
	
	/**
	 * @brief
	 * @param[in] _durationMax
	 * @return
	 */
	public boolean isShort(Duration _durationMax);
	
	/**
	 * @brief checks for seats
	 * @param[in] _fareClass: the fare class used for the check
	 * @return a SeatConfiguration of all the available seats
	 */
	public SeatConfiguration seatsAvailable(FareClass _fareClass);
	
	/**
	 * @brief checks for seats
	 * @param[in] _fareClass: the fare class used for the check
	 * @return whether the flight has any seats available for the given fare class
	 */
	public boolean hasSeats(FareClass _fareClass);
}
