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
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

public final class FlightGroup 
{
	private final Airport m_origin;
	
	private final NavigableMap<LocalTime, Set<Flight>> m_flights;
	
	/**
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
	
	/**
	 * @brief Builder method for @FlightGroup class
	 * @param[in] _origin: the origin airport
	 * @returns A constructed FlightGroup object
	 */
	public static final FlightGroup of(
			Airport _origin)
	{
		_origin = Objects.requireNonNull(_origin, "Parameter cannot be null");

		return new FlightGroup(_origin);	
	}
	
	/**
	 * @brief Simple getter method
	 * @returns @m_origin of the object
	 */
	public Airport getOrigin()
	{
		return m_origin;
	}

	/**
	 * @brief Adds flight to the tree map
	 * @returns true if the flight can be successfully added
	 */
	public final boolean add(Flight _flight)
	{
		_flight = Objects.requireNonNull(_flight, "Parameter cannot be null");
		
		if(!_flight.getLeg().getOrigin().equals(m_origin))
		{
			throw new IllegalArgumentException("Flight " + _flight.getCode() + 
					" does not originate from " + m_origin.getCode());
		}
		
		Set<Flight> flightSet = new HashSet<Flight>();
		
		//checks to see if there is a flight that already departs from the airport
		for(Set<Flight> v : m_flights.values())
		{
			for(Flight f : v)
			{
				if(f.departureTime().equals(_flight.departureTime()))
				{
					return false;
				}
			}
		}
		
		flightSet.add(_flight);
		return m_flights.put(_flight.departureTime(), flightSet) == null;
	}
	
	/**
	 * @brief removes a flight from the tree
	 * @returns True if the was succesfully removed
	 */
	public final boolean remove(Flight _flight)
	{
		_flight = Objects.requireNonNull(_flight, "Parameter cannot be null");
		
		if(_flight.getLeg().getOrigin() != m_origin)
		{
			throw new IllegalArgumentException("Flight " + _flight.getCode() + 
					" does not originate from " + m_origin.getCode());
		}
		

		Set<Flight> flightSet = new HashSet<Flight>();
		flightSet.add(_flight);
		
		return m_flights.remove(_flight.departureTime(), flightSet);
	}
	
	/**
	 * @brief finds flights after a time
	 * @returns A set of all flights that are after @_departureTime
	 */
	public final Set<Flight> flightsAtOrAfter(LocalTime _departureTime)
	{
		_departureTime = Objects.requireNonNull(_departureTime, "Parameter cannot be null");
		
		Set<Flight> afterSet = new HashSet<Flight>();
		
		for(Set<Flight> v : m_flights.values())
		{
			for(Flight f : v)
			{
				if(f.departureTime().isAfter(_departureTime) || f.departureTime().equals(_departureTime))
				{
					afterSet.add(f);
				}
			}
		}
		
		return afterSet;
	}	
}
