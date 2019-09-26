/*******************************************************************************
 * @file 	RouteTime.java
 * @author 	Michael Thompson (mjt106)
 * @date 	9/19/2019
 * @details	This file outlines the over-arching Route Time class
 ******************************************************************************/


package airtravel;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public final class RouteTime implements Comparable<RouteTime>
{

	/**
	 * @brief the time it takes for a route
	 */
	private final LocalTime m_routeTime;
	
	/**
	 * @brief public constructor
	 * @param _routeTime: the time it takes to complete a route
	 * @returns This method performs an action and does not return a value
	 */
	public RouteTime(LocalTime _routeTime)
	{		
		m_routeTime = _routeTime;
	}
	
	/**
	 * @brief value for unknown RouteTimes
	 */
	public static final RouteTime UNKNOWN = new RouteTime(null);
	
	/**
	 * @brief Simple getter method
	 * @return @m_routTime of the object
	 */
	public LocalTime getTime()
	{
		if(!isKnown())
		{
			throw new IllegalStateException("Cannot get m_routeTime as it is unkown");
		}
		return m_routeTime;
	}
	
	/**
	 * @brief tells whether the time is known
	 * @return true if the time is known
	 * 		   false if the time is unkown
	 */
	public boolean isKnown()
	{
		return m_routeTime != null;
	}	
	
	/**
	 * @brief adds duration to the RouteTime
	 * @param _duration: the amount of time to be added to the RouteTime
	 * @return: a new RouteTime with a new time after the duration
	 */
	public RouteTime plus(Duration _duration)
	{
		Objects.requireNonNull(_duration, "Parameter cannot be null");
		
		if(isKnown())
		{
			return new RouteTime(this.getTime().plus(_duration));
		}
		
		return UNKNOWN;
	}
	
	@Override
	public int compareTo(RouteTime arg0) 
	{		
		//could do with boolean compareTo
		if(!arg0.isKnown() && !this.isKnown()) 
		{
			return 0;
		}
		else if(!this.isKnown())
		{
			return -1;
		}
		else if(!arg0.isKnown())
		{
			return 1;
		}

		return this.getTime().compareTo(arg0.m_routeTime);
	}
}
