/*******************************************************************************
 * @file 	RouteNode.java
 * @author 	Michael Thompson (mjt106)
 * @date 	9/24/2019
 * @details	This file outlines the over-arching Route Node class
 ******************************************************************************/

package airtravel;

import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

public final class RouteNode implements Comparable<RouteNode>
{

	private final Airport m_airport;
	
	private final RouteTime m_arrivalTime;
	
	private final RouteNode m_previous;
	
	/**
	 * @brief private constructor
	 * @param _airport: departure airport
	 * @param _arrivalTime: time the route takes
	 * @param _previous: previous node
	 * @returns This method performs an action and does not return a value
	 */
	private RouteNode(
			Airport _airport,
			RouteTime _arrivalTime,
			RouteNode _previous)
	{
		m_airport = _airport;
		m_arrivalTime = _arrivalTime;
		m_previous = _previous;
	}
	
	/**
	 * @brief Builder method for @RouteNode class
	 * @param _airport: departure airport
	 * @param _arrivalTime: time the rout takes
	 * @param _previous: previous node
	 * @return A constructed RouteNode object
	 */
	public static final RouteNode of(
			Airport _airport,
			RouteTime _arrivalTime,
			RouteNode _previous)
	{
		Objects.requireNonNull(_airport, "Parameter _airport cannot be null");
		Objects.requireNonNull(_arrivalTime, "Parameter _arrivalTime cannot be null");
		
		return new RouteNode(_airport, _arrivalTime, _previous);
	}
	
	/**
	 * @brief Builder method for @RouteNode class
	 * @param _flight: the flight being taken
	 * @param _previous: previous node
	 * @return A constructed RouteNode object using the @_flight destination airport
	 */
	public static final RouteNode of(
			Flight _flight,
			RouteNode _previous)
	{
		Objects.requireNonNull(_flight, "Parameter _flight cannot be null");
		
		return new RouteNode(
				_flight.destination(), 
				new RouteTime(_flight.arrivalTime()), 
				_previous);
	}
	
	public static final RouteNode of(
			Airport _airport)
	{
		Objects.requireNonNull(_airport, "Parameter cannot be null");
		
		return new RouteNode(_airport, RouteTime.UNKNOWN, null);
	}
	
	/**
	 * @brief Simple getter method
	 * @return @m_airport of the object
	 */
	public Airport getAirport()
	{
		return m_airport;
	}
	
	/**
	 * @brief Simple getter method
	 * @return @m_arrivalTime of the object
	 */
	public RouteTime getArrivalTime()
	{
		return m_arrivalTime;
	}
	
	/**
	 * @brief Simple getter method
	 * @return @m_previous of the object
	 */
	public RouteNode getPrevious()
	{
		return m_previous;
	}
	
	/**
	 * @brief passes isKnown down from m_arrivalTime
	 * @return true if the time is known
	 * 		   false if the time is unkown
	 */
	public final Boolean isArrivalTimeKnown()
	{
		return !m_arrivalTime.equals(RouteTime.UNKNOWN);
	}
	
	/**
	 * @brief gets the departure time for a RouteNode
	 * @return the airport's connection time plus the route's arrival time
	 */
	public final RouteTime departureTime()
	{
		m_airport.getConnectionTimeMin();
		
		if(isArrivalTimeKnown())
		{			
			return new RouteTime(m_arrivalTime.getTime().plus(m_airport.getConnectionTimeMin()));
		}
		else
		{
			LocalTime temp = LocalTime.of(0, 0, 0, 0);
			return new RouteTime(temp.plus(m_airport.getConnectionTimeMin()));
		}
	}
	
	/**
	 * @brief gets a set of available flights
	 * @param _fareClass: the fareClass we are checking flights for
	 * @return a set of flights that available from the give @m_airport at or after departureTime()
	 */
	public Set<Flight> availableFlights(FareClass _fareClass)
	{
		Objects.requireNonNull(_fareClass, "Parameter cannot be null");
		
		return m_airport.avaialableFlghts(this.departureTime().getTime(), _fareClass);
	}
	
	@Override
	public int compareTo(RouteNode o) 
	{
		Objects.requireNonNull(o, "Paramter cannot be null");
		
		if(this.m_arrivalTime.compareTo(o.getArrivalTime()) == 0)
		{
			return this.m_airport.compareTo(o.getAirport());
		}
		
		return this.m_arrivalTime.compareTo(o.getArrivalTime());
	}
	
}
