/*******************************************************************************
 * @file 	RouteState.java
 * @author 	Michael Thompson (mjt106)
 * @date 	9/24/2019
 * @details	This file outlines the Route State class
 ******************************************************************************/

package airtravel;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Objects;
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
		Objects.requireNonNull(_airports, "Parameter _airports cannot be null");
		Objects.requireNonNull(_origin, "Parameter _origin cannot be null");
		Objects.requireNonNull(_departureTime, "Parameter _departureTime cannot be null");
		
		m_airportNode = new LinkedHashMap<Airport, RouteNode>();
		m_unreached = new TreeSet<>();
		
		RouteNode first = RouteNode.of(_origin, new RouteTime(_departureTime), null);
		
		m_airportNode.put(_origin, first);
		
		for(Airport airport : _airports)
		{
			m_airportNode.put(airport, RouteNode.of(airport));
			m_unreached.add(RouteNode.of(airport));
		}
		
	}
	
	/**
	 * @brief replaces the route node for the corresponding airport
	 * @param _routeNode: the routeNode being replaced
	 */
	void replaceNode(RouteNode _routeNode)
	{
		m_airportNode.replace(_routeNode.getAirport(), _routeNode);
		
		m_unreached.remove(_routeNode);
		
		/*
		for(RouteNode node : m_unreached)
		{
			if(node.equals(_routeNode))
			{
				m_unreached.remove(node);
				m_unreached.add(_routeNode);
			}
		}
		*/
	}
	
	/**
	 * @return whether all airports have been reached or not
	 */
	boolean allReached()
	{		
		return m_unreached.isEmpty();
	}
	
	/**
	 * @return the un reached airport whose arrival time is the smallest
	 * @throws NoSuchElementException if all airports have been reached
	 */
	RouteNode closestUnreached()
	{
		if(m_unreached.isEmpty())
		{
			throw new NoSuchElementException("No such element exists");
		}
		
		RouteNode smallestTime = m_unreached.first();
		
		for(RouteNode node : m_unreached)
		{
			//smallestTime > node
			if(smallestTime.compareTo(node) > 0)
			{
				smallestTime = node;
			}
		}
		
		return smallestTime;
	}
	
	
	/**
	 * @param _airport: the airport being checked
	 * @return the route node corresponding to the given airport
	 */
	RouteNode airportNode(Airport _airport)
	{
		Objects.requireNonNull(_airport);
		
		return m_airportNode.get(_airport);
	}
}
