/*******************************************************************************
 * @file 	Airport.java
 * @author 	Michael Thompson (mjt106)
 * @date 	9/7/2019
 * @details	This class outlines the overarching Airport class
 ******************************************************************************/

package airtravel;

import java.time.Duration;

public final class Airport implements Comparable<Airport> {
	
	/*
	 * @brief Universal identifier for the aiport
	 */
	private final String m_code;
	
	/*
	 * @brief Shortest length of time that a passenger needs to transfer planes
	 * 		  or to walk from the reservation counter to the gates
	 */
	private final Duration m_connectionTimeMin;
	
	/*
	 * @brief Private constructor
	 * @param[in] _code: the code used for the airport
	 * @param[in] _connectionTimeMin: connection time or reservation time
	 * @returns This method preforms an action and does not return a value
	 */
	private Airport(
			String _code,
			Duration _connectionTimeMin) {
		m_code = _code;
		m_connectionTimeMin = _connectionTimeMin;
	}
	
	/*
	 * @brief Comparable implementation for @Airport class
	 * @param[in] arg0: object getting compared to
	 * @returns 1 if arg0 is greater in value than the calling object
	 * 			0 if arg0 is equivalent in value to the calling object
	 * 			-1 if arg0 is less in value than the calling object
	 */
	@Override
	public int compareTo(Airport arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
