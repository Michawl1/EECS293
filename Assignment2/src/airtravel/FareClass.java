/*******************************************************************************
 * @file 	FareClass.java
 * @author 	Michael Thompson (mjt106)
 * @date 	9/17/2019
 * @details	This file outlines the Fare Class
 ******************************************************************************/


package airtravel;

import java.util.Objects;

public final class FareClass 
{
	/**
	 * @brief price identifier for the seat
	 */
	private final int m_identifier;
	
	/**
	 * @brief seat class identifier
	 */
	private final SeatClass m_seatClass;
	
	/**
	 * @brief Private constructor
	 * @param[in] _identifier: the numerical identifier for the class of ticket
	 * @param[in] _seatClass: the enum seat class that is being used
	 * @return This method performs an action and does not return a value
	 */
	private FareClass(
			int _identifier,
			SeatClass _seatClass)
	{
		m_identifier = _identifier;
		m_seatClass = _seatClass;
	}
	
	/**
	 * @brief Builder method for @FareClass class
	 * @param[in] _identifier: the numerical identifier for the class of ticket
	 * @param[in] _seatClass: the enum seat class that is being used
	 * @return A constructed FareClass object
	 */
	public static final FareClass of(
			int _identifier,
			SeatClass _seatClass)
	{
		_seatClass = Objects.requireNonNull(_seatClass, "Parameter _seatClass cannot be null");
		
		return new FareClass(_identifier, _seatClass);
	}

	/**
	 * @brief Simple getter method
	 * @returns @m_identifier of the object
	 */
	public int getIdentifier() 
	{
		return m_identifier;
	}
	
	/**
	 * @brief Simple getter method
	 * @returns @m_seatClass of the object
	 */
	public SeatClass getSeatClass()
	{
		return m_seatClass;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof FareClass))
		{
			return false;
		}
		
		return m_identifier == ((FareClass)o).getIdentifier();
	}
	
	@Override
	public int hashCode()
	{
		int a = m_identifier;
		
		//Robert Jenkins' 32 bit integer hash function
		//sourced from: https://gist.github.com/badboy/6267743
		a = (a+0x7ed55d16) + (a<<12);
		a = (a^0xc761c23c) ^ (a>>19);
		a = (a+0x165667b1) + (a<<5);
		a = (a+0xd3a2646c) ^ (a<<9);
		a = (a+0xfd7046c5) + (a<<3);
		a = (a^0xb55a4f09) ^ (a>>16);
		
		return a;
	}
	
}
