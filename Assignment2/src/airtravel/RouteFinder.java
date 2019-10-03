/*******************************************************************************
 * @file 	RouteFinder.java
 * @author 	Michael Thompson (mjt106)
 * @date 	9/24/2019
 * @details	This file outlines the Route Finder class
 ******************************************************************************/

package airtravel;

import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

public final class RouteFinder 
{
	private final Set<Airport> m_airports;
	
	/**
	 * @brief Private constructor
	 * @param _airports: a set of airports for routeFinder
	 * @return this method performs an action and does not return a value
	 */
	private RouteFinder(Set<Airport> _airports)
	{		
		m_airports = _airports;
	}
	
	/**
	 * 
	 * @param _node
	 * @param _fareClass
	 * @param _state
	 * @return a RouteState with the best flight option within the given RouteNode's flights
	 */
	private RouteState getBestFlightOption(RouteNode _node, FareClass _fareClass, RouteState _state)
	{
		RouteNode shortesRouteDestinationNode = RouteNode.of(_node.getAirport());
		
		for(Flight flight : _node.availableFlights(_fareClass))
		{
			if(new RouteTime(flight.arrivalTime()).compareTo(shortesRouteDestinationNode.getArrivalTime()) < 0)
			{
				shortesRouteDestinationNode = RouteNode.of(flight, null);
				_state.replaceNode(shortesRouteDestinationNode);
			}
		}
		
		return _state;
	}
	
	/**
	 * @brief Builder method for @RouteFinder
	 * @param _airports: a set of airports for routefinder
	 * @return a constructed @RouteFinder object
	 */
	public static RouteFinder of(Set<Airport> _airports)
	{
		Objects.requireNonNull(_airports, "Parameter cannot be null");
		
		return new RouteFinder(_airports);
	}
	
	/**
	 * @brief 
	 * @param _origin
	 * @param _destination
	 * @param _departureTime
	 * @param _fareClass
	 * @return the last route node in the fastest route from the departure airport to the final destination
	 */
	public final RouteNode route(
			Airport _origin,
			Airport _destination,
			LocalTime _departureTime,
			FareClass _fareClass)
	{
		Objects.requireNonNull(_origin, "Parameter _origin cannot be null");
		Objects.requireNonNull(_destination, "Parameter _destination cannot be null");
		Objects.requireNonNull(_departureTime, "Parameter _departureTime cannot be null");
		Objects.requireNonNull(_fareClass, "Parameter _fareClass cannot be null");
		
		RouteState state = RouteState.of(m_airports, _origin, _departureTime);
		
		RouteNode currentNode = RouteNode.of(_origin);
		
		while(!state.allReached())
		{			
			currentNode = state.closestUnreached();
			
			if(currentNode.getAirport().equals(_destination))
			{
				return currentNode;
			}
			state = getBestFlightOption(currentNode, _fareClass, state);
			state.removeUnreachedNode(currentNode);
		}
		
		return null;
	}

}
