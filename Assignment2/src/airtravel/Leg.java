/*******************************************************************************
 * @file 	Leg.java
 * @author 	Michael Thompson (mjt106)
 * @date 	9/7/2019
 * @details	This file outlines the Leg class
 ******************************************************************************/

package airtravel;

import java.util.Objects;

public final class Leg 
{
	/**
	 * @brief The origin Airport for this leg
	 */
	private final Airport m_origin;
	
	/**
	 * @brief The destination Airport for this leg
	 */
	private final Airport m_destination;
	
	/**
	 * @brief Private constructor
	 * @param[in] _origin: The origin Airport
	 * @param[in] _destination: The destination Airport
	 * @returns This method performs an action and does not return a value
	 */
	private Leg(
			Airport _origin,
			Airport _destination)
	{
		m_origin = _origin;
		m_destination = _destination;
	}
	
	/**
	 * @brief Builder method for @Leg class
	 * @param[in] _origin: the origin used for the leg
	 * @param[in] _destination: the destination used for the leg
	 * @returns A constructed Leg object
	 */
	public static final Leg of(
			Airport _origin,
			Airport _destination)
	{
		_origin = Objects.requireNonNull(_origin, "Parameter _origin cannot be null");
		_destination = Objects.requireNonNull(_destination, "Parameter _destination cannot be null");
		
		return new Leg(_origin, _destination);
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
	 * @brief Simple getter method
	 * @returns @m_destination of the object
	 */
	public Airport getDestination()
	{
		return m_destination;
	}
}
