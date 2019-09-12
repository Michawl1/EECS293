/*******************************************************************************
 * @file 	FlightGroup.java
 * @author 	Michael Thompson (mjt106)
 * @date 	9/12/2019
 * @details	This file outlines a flight group
 ******************************************************************************/

package airtravel;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

public final class FlightGroup 
{
	private final Airport m_origin;
	
	private final NavigableMap<LocalTime, Set<Flight>> m_flights;
	
	/*
	 * @brief Private constructor
	 * @param[in] _origin: the origin airport for the flight
	 * @returns This method performs an action and does not return a value
	 */
	private FlightGroup(
			Airport _origin)
	{
		m_origin = _origin;
		m_flights = new TreeMap<LocalTime, Set<Flight>>();
	}
	
	/*
	 * @brief Builder method for @FlightGroup class
	 * @param[in] _origin: the origin airport
	 * @returns A constructed FlightGroup object
	 */
	public static final FlightGroup of(
			Airport _origin)
	{
		if(_origin == null)
		{
			throw new NullPointerException("Paramters cannot be null");
		}
		else
		{
			return new FlightGroup(_origin);
		}
	}
	
	/*
	 * @brief Simple getter method
	 * @returns @m_origin of the object
	 */
	public Airport getOrigin()
	{
		return m_origin;
	}

	/*
	 * @brief Adds flight to the tree map
	 * @returns true if the flight can be successfully added
	 */
	public final boolean add(Flight _flight)
	{
		Set<Flight> flightSet = new HashSet<Flight>();
		flightSet.add(_flight);
		if(m_flights.put(_flight.departureTime(), flightSet) == null)
		{
			return true;
		}
		else
		{
			throw new IllegalArgumentException("Flight " + _flight.getCode() + 
					" already exists in the system");
		}
	}
	
	/*
	 * @brief removes a flight from the tree
	 * @returns True if the was succesfully removed
	 */
	public final boolean remove(Flight _flight)
	{
		if(m_flights.remove(_flight.departureTime(), _flight) == true)
		{
			return true;
		}
		else
		{
			throw new IllegalArgumentException("Flight " + _flight.getCode() + 
					" does not originate from " + m_origin.getCode());
		}
	}
	
	
	public final Set<Flight> flightsAtOrAfter(LocalTime _departureTime)
	{
		Set<Flight> afterSet = new HashSet<Flight>();
		afterSet.addAll(m_flights.get(_departureTime));
		
		return null;
	}
	
	
	
	
}
