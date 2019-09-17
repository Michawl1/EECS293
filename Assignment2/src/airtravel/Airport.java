/*******************************************************************************
 * @file 	Airport.java
 * @author 	Michael Thompson (mjt106)
 * @date 	9/7/2019
 * @details	This file outlines the over-arching Airport class
 ******************************************************************************/

package airtravel;

import java.time.Duration;
import java.util.Objects;

public final class Airport implements Comparable<Airport> 
{
	
	/*
	 * @brief Universal identifier for the airport
	 */
	private final String m_code;
	
	/*
	 * @brief Shortest length of time that a passenger needs to transfer planes
	 * 		  or to walk from the reservation counter to the gates
	 */
	private final Duration m_connectionTimeMin;
	
	/*
	 * @brief Group manager for flights
	 */
	private final FlightGroup m_outFlights;
	
	/*
	 * @brief Private constructor
	 * @param[in] _code: the code used for the airport
	 * @param[in] _connectionTimeMin: connection time or reservation time
	 * @returns This method performs an action and does not return a value
	 */
	private Airport(
			String _code,
			Duration _connectionTimeMin) 
	{
		m_code = _code;
		m_connectionTimeMin = _connectionTimeMin;
		m_outFlights = FlightGroup.of(this);
	}
	
	/*
	 * @brief Builder method for @Airport class
	 * @param[in] _code: the code used for the airport
	 * @param[in] _connectionTimeMin: connect ion time or reservation time
	 * @returns A constructed Airport object
	 */
	public static final Airport of(
			String _code,
			Duration _connectionTimeMin) 
	{	
		_code = Objects.requireNonNull(_code, "Parameter _code cannot be null");
		_connectionTimeMin = Objects.requireNonNull(_connectionTimeMin, "Parameter _connectionTimeMIn cannot be null");

		return new Airport(_code, _connectionTimeMin);
	}
	
	/*
	 * @brief Simple getter method
	 * @returns @m_code of the object
	 */
	public String getCode()
	{
		return this.m_code;
	}
	
	/*
	 * @brief Simple getter method
	 * @returns @m_connectionTimeMin of the object
	 */
	public Duration getConnectionTimeMin()
	{
		return this.m_connectionTimeMin;
	}
	
	/*
	 * @brief adds a flight to the flight group
	 * @returns false if it failed to add flight
	 * 			true if the succeeded in adding the flight
	 */
	public boolean addFlight(Flight _flight)
	{
		_flight = Objects.requireNonNull(_flight, "Parameter cannot be null");
		return m_outFlights.add(_flight);
	}
	
	/*
	 * @brief removes a flight from the flight group
	 * @returns false if it failed to remove flight
	 * 				  true if it succeeded in removing the flight
	 */
	public boolean removeFlight(Flight _flight)
	{
		_flight = Objects.requireNonNull(_flight, "Parameter cannot be null");
		return m_outFlights.remove(_flight);
	}

	/*
	 * @brief Comparable implementation for @Airport class
	 * @param[in] arg0:	Airport getting compared to
	 * @returns 1 if arg0 is greater in value than the calling object
	 * 			0 if arg0 is equivalent in value to the calling object
	 * 			-1 if arg0 is less in value than the calling object
	 */
	@Override
	public int compareTo(Airport arg0) 
	{
		return m_code.compareTo(arg0.getCode());
	}
	
	/*
	 * @brief Equals implementation for @Airport class
	 * @param[in] o: Airport getting compared to
	 * @returns: true if this.m_code and o.m_code are equivalent
	 * 			 false if this.m_code and o.m_code are not equivalent
	 */
	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof Airport))
		{
			return false;
		}
		
		return m_code.equals(((Airport)o).getCode());
	}
	
	/*
	 * @brief HashCode implementation for @Airport class
	 * @returns the int value of the String @m_code
	 */
	@Override
	public int hashCode()
	{
		return m_code.hashCode();
	}
	
	/*
	 * @brief ToString implementation for @Airport class
	 * @returns a string containing this.m_code
	 */
	@Override
	public String toString()
	{
		return m_code;
	}
}
