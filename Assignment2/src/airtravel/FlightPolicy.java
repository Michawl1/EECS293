/*******************************************************************************
 * @file 	FlightPolicy.java
 * @author 	Michael Thompson (mjt106)
 * @date 	9/17/2019
 * @details	This file outlines the Flight Policy Class
 ******************************************************************************/

package airtravel;

import java.time.Duration;
import java.util.Objects;
import java.util.function.BiFunction;

public final class FlightPolicy extends AbstractFlight
{
	/*
	 * @brief
	 */
	private final Flight m_flight;
	
	/*
	 * @brief 
	 */
	private final BiFunction<SeatConfiguration, FareClass, SeatConfiguration> m_policy;

	/*
	 * @brief Private constructor
	 * @return This method performs an action and does not return a value
	 */
	private FlightPolicy(
			Flight _flight,
			BiFunction<SeatConfiguration, FareClass, SeatConfiguration> _policy)
	{
		m_flight = _flight;
		m_policy = _policy;
	}
	
	/*
	 * @brief Builder method for @FlightPolicy class
	 * @returns A constructed FlightPoliciy object
	 */
	public static final FlightPolicy of(			
			Flight _flight,
			BiFunction<SeatConfiguration, FareClass, SeatConfiguration> _policy)
	{
		_flight = Objects.requireNonNull(_flight, "Parameter _fight cannot be null");
		_policy = Objects.requireNonNull(_policy, "Parameter _policy cannot be null");
		
		FlightPolicy temp = new FlightPolicy(_flight, _policy);
		
		_flight.getLeg().getOrigin().addFlight(temp);
		
		return temp;
	}
	
	
	/*
	 * @brief strict policy for a flight
	 * @param[in] _flight: the flight we're checking for seats
	 * @returns a flight that only shows the available seats in a given class
	 */
	public static final Flight strict(Flight _flight)
	{
		_flight = Objects.requireNonNull(_flight, "Parameter cannot be null");
		
		FlightPolicy policy = FlightPolicy.of(_flight, (seatConfig, fareClassConfig) -> {
			SeatConfiguration copySeatConfig = SeatConfiguration.of(seatConfig);
			for(SeatClass v : SeatClass.values())
			{
				if(v != fareClassConfig.getSeatClass())
				{
					copySeatConfig.setSeats(v, 0);
				}
			}
			
			for(SeatClass v : SeatClass.values()) 
			{
				System.out.println(copySeatConfig.seats(v));
			}
			return copySeatConfig;
		});
		
		return policy;
	}
	
	public static final Flight restrictedDuration(Flight _flight, Duration _durationMax)
	{
		_flight = Objects.requireNonNull(_flight, "Parameter _flight cannot be null");
		_durationMax = Objects.requireNonNull(_durationMax, "Parameter _durationMax cannot be null");
		
		if(_flight.isShort(_durationMax))
		{
			return strict(_flight);
		}
		
		return null;
	}
	
	public static final Flight reserve(Flight _flight, int _reserve)
	{
		_flight = Objects.requireNonNull(_flight, "Parameter _flight cannot be null");
		return null;
	}
	
	public static final Flight limited(Flight _flight)
	{
		_flight = Objects.requireNonNull(_flight, "Parameter cannot be null");
		return null;
	}

	@Override
	public String getCode() 
	{
		return m_flight.getCode();
	}

	@Override
	public Leg getLeg() 
	{
		return m_flight.getLeg();
	}

	@Override
	public FlightSchedule getFlightSchedule() 
	{
		return m_flight.getFlightSchedule();
	}

	@Override
	public SeatConfiguration seatsAvailable(FareClass _fareClass) 
	{		
		_fareClass = Objects.requireNonNull(_fareClass, "Parameters cannot be null");

		SeatConfiguration returnConfig = m_flight.seatsAvailable(_fareClass);
		return m_policy.apply(returnConfig, _fareClass);
	}
}