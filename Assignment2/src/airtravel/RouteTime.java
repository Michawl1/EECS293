/*******************************************************************************
 * @file 	RouteTime.java
 * @author 	Michael Thompson (mjt106)
 * @date 	9/19/2019
 * @details	This file outlines the over-arching Route Time class
 ******************************************************************************/


package airtravel;

import java.time.LocalTime;

public final class RouteTime 
{

	/**
	 * @brief the time it takes for a route
	 */
	private final LocalTime m_routeTime;
	
	/**
	 * @brief Private constructor
	 * @param _routeTime: the time it takes to complete a route
	 * @returns This method performs an action and does not return a value
	 */
	private RouteTime(
			LocalTime _routeTime)
	{
		m_routeTime = _routeTime;
	}
	
	/**
	 * @brief Builder method for @RouteTime class
	 * @param _routeTime: the time it takes to complete a route
	 * @return A constructed RouteTime object
	 */
	public static final RouteTime of(
			LocalTime _routeTime)
	{
		return new RouteTime(_routeTime);
	}
	
	public static final RouteTime UNKNOWN()
	{
		return null;
	}
	
}
