/*******************************************************************************
 * @file 	RouteState.java
 * @author 	Michael Thompson (mjt106)
 * @date 	9/24/2019
 * @details	This file outlines the Route State class
 ******************************************************************************/

package airtravel;

import java.time.LocalTime;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

final class RouteState 
{
	private Map<Airport, RouteNode> m_airportNode;
	
	private final NavigableSet<RouteNode> m_unreached;
	
	/**
	 * @brief Constructor for @RouteState
	 * @param _airports: a set of airports
	 * @param _origin: the origin airport for the RouteState
	 * @param _departureTime: the departure time for @_origin airport
	 */
	RouteState(
			Set<Airport> _airports, 
			Airport _origin, 
			LocalTime _departureTime)
	{
		m_unreached = new TreeSet<RouteNode>();
		
		RouteNode first = RouteNode.of(_origin, new RouteTime(_departureTime), null);
		
		m_unreached.add(first);
		
		for(Airport airport : _airports)
		{
			m_unreached.add(RouteNode.of(airport));
		}
		
	}
	
	/**
	 * @brief replaces the route node for the corresponding airport
	 * @param _routeNode: the routeNode being replaced
	 */
	void replaceNode(RouteNode _routeNode)
	{

	}
	
	/**
	 * @return whether all airports have been reached or not
	 */
	boolean allReached()
	{
		return false;
	}
	
	/**
	 * @return the un reached airport whose arrival time is the smallest
	 * @throws NoSuchElementException if all airports have been reached
	 */
	RouteNode closestUnreached()
	{
		return null;
	}
	
	
	/**
	 * @param _airport: the airport being checked
	 * @return the route node corresponding to the given airport
	 */
	RouteNode airportNode(Airport _airport)
	{
		return m_airportNode.get(_airport);
	}
}
