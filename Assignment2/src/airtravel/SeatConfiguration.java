/*******************************************************************************
 * @file 	SeatConfigurationo.java
 * @author 	Michael Thompson (mjt106)
 * @date 	9/17/2019
 * @details	This file outlines the Seat Configuration Class
 ******************************************************************************/

package airtravel;

import java.util.EnumMap;
import java.util.Objects;

public final class SeatConfiguration 
{
	/**
	 * @brief Keeps track of all the seats available
	 */
	private final EnumMap<SeatClass, Integer> m_seats;
	
	/**
	 * @brief Private constructor
	 * @param[in] _seats: a map of all the available seats for all the enum values in SeatClass
	 * @returns This method performs an action and does not return a value
	 */
	private SeatConfiguration(
			EnumMap<SeatClass, Integer> _seats)
	{
		m_seats = _seats;
	}
	
	/**
	 * @brief Builder method for @SeatConfiguration class
	 * @param[in] _seats: a map of all the available seats for all the enum values in SeatClass
	 * @returns A constructed SeatConfiguration object
	 */
	public static final SeatConfiguration of(
			EnumMap<SeatClass, Integer> _seats)
	{
		_seats = Objects.requireNonNull(_seats, "Parameters cannot be null");
		
		return new SeatConfiguration(_seats);
	}
	
	public static final SeatConfiguration of(
			SeatConfiguration _seatConfig)
	{
		SeatConfiguration copy = _seatConfig;
		return copy;
	}
	
	/**
	 * @param[in] _seatClass: a value of the @SeatClass enum used determine class
	 * @returns the number of seats available in the given class
	 */
	public final int seats(SeatClass _seatClass)
	{
		_seatClass = Objects.requireNonNull(_seatClass, "Parameters cannot be null");
		
		return m_seats.get(_seatClass).intValue();
	}
	
	/**
	 * @brief sets the number of seats available in the given class
	 * @param[in] _seatClass: a value of the @SeatClass enum used determine class
	 * @param[in] _seats: The new amount of seats available
	 * @returns the number of seats previously available in the same class
	 */
	public final int setSeats(SeatClass _seatClass, int _seats)
	{
		_seatClass = Objects.requireNonNull(_seatClass, "Parameters cannot be null");
		if(_seats < 0)
		{
			_seats = 0;
		}
		
		int previousSeats = m_seats.get(_seatClass).intValue();
		m_seats.put(_seatClass, new Integer(_seats));
		
		return previousSeats;
	}
	
	/**
	 * @brief checks if there is any seats available in any class
	 * @returns True if there is an available seat
	 */
	public final boolean hasSeats()
	{
		for(SeatClass v : SeatClass.values())
		{
			if(!m_seats.get(v).equals(new Integer(0)))
			{
				return true;
			}
		}
		return false;
	}
}
