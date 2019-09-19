package airtravel.airtravel_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import airtravel.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

class Airport_Test 
{
	
	static Duration t_duration0;
	static Duration t_duration1;
	static Duration t_duration2;
	static Duration t_duration3;
	static Duration t_duration4;
	
	static String t_code1;
	static String t_code2;
	static String t_code3;
	static String t_code4;
	
	static Airport t_airport1;
	static Airport t_airport2;
	static Airport t_airport3;
	static Airport t_airport4;
	
	static LocalTime t_time1;
	static LocalTime t_time2;
	static LocalTime t_time3;
	static LocalTime t_time4;
	
	static Leg t_leg1;
	static Leg t_leg2;
	static Leg t_leg3;
	static Leg t_leg4;
	
	static FlightSchedule t_schedule1;
	static FlightSchedule t_schedule2;
	static FlightSchedule t_schedule3;
	static FlightSchedule t_schedule4;
	
	static Flight t_flight1;
	static Flight t_flight2;
	static Flight t_flight3;
	static Flight t_flight4;
	static Flight t_flight5;
	static Flight t_flight6;
	
	static FlightGroup t_group1;
	static FlightGroup t_group2;
	static FlightGroup t_group3;
	static FlightGroup t_group4;
	
	static EnumMap<SeatClass, Integer> t_seats1;
	static EnumMap<SeatClass, Integer> t_seats2;
	static EnumMap<SeatClass, Integer> t_seats3;
	static EnumMap<SeatClass, Integer> t_seats4;
	static EnumMap<SeatClass, Integer> t_seats5;
	
	static SeatConfiguration t_config1;
	static SeatConfiguration t_config2;
	static SeatConfiguration t_config3;
	static SeatConfiguration t_config4;
	static SeatConfiguration t_config5;
	
	static FareClass t_fare1;
	static FareClass t_fare2;
	static FareClass t_fare3;
	static FareClass t_fare4;
	
	static FlightPolicy t_policy1;
	static FlightPolicy t_policy2;
	static FlightPolicy t_policy3;
	static FlightPolicy t_policy4;
	
	static BiFunction<SeatConfiguration, FareClass, SeatConfiguration> t_bifunction1;
	static BiFunction<SeatConfiguration, FareClass, SeatConfiguration> t_bifunction2;
	static BiFunction<SeatConfiguration, FareClass, SeatConfiguration> t_bifunction3;
	static BiFunction<SeatConfiguration, FareClass, SeatConfiguration> t_bifunction4;
	
	
	//initializes a bunch of objects for testing
	public void testCreate()
	{
		t_duration0 = Duration.ofHours(0);
		t_duration1 = Duration.ofHours(1);
		t_duration2 = Duration.ofHours(2);
		t_duration3 = Duration.ofHours(3);
		t_duration4 = Duration.ofHours(4);
		
		t_code1 = "Cle";
		t_code2 = "Pit";
		t_code3 = "Det";
		t_code4 = "Chi";
		
		t_airport1 = Airport.of(t_code1, t_duration1);
		t_airport2 = Airport.of(t_code2, t_duration2);
		t_airport3 = Airport.of(t_code3, t_duration3);
		t_airport4 = Airport.of(t_code4, t_duration4);
		
		t_time1 = LocalTime.of(1, 10);
		t_time2 = LocalTime.of(2, 20);
		t_time3 = LocalTime.of(3, 30);
		t_time4 = LocalTime.of(4, 40);
		
		t_leg1 = Leg.of(t_airport1, t_airport2);
		t_leg2 = Leg.of(t_airport1, t_airport3);
		t_leg3 = Leg.of(t_airport1, t_airport4);
		t_leg4 = Leg.of(t_airport2, t_airport4);
		
		t_schedule1 = FlightSchedule.of(t_time1, t_time2);
		t_schedule2 = FlightSchedule.of(t_time1, t_time3);
		t_schedule3 = FlightSchedule.of(t_time1, t_time4);
		t_schedule4 = FlightSchedule.of(t_time2, t_time4);
		
		t_seats1 = new EnumMap<SeatClass, Integer>(SeatClass.class);
		t_seats2 = new EnumMap<SeatClass, Integer>(SeatClass.class);
		t_seats3 = new EnumMap<SeatClass, Integer>(SeatClass.class);
		t_seats4 = new EnumMap<SeatClass, Integer>(SeatClass.class);
		t_seats5 = new EnumMap<SeatClass, Integer>(SeatClass.class);
		
		for(SeatClass v : SeatClass.values())
		{
			t_seats1.put(v, new Integer(0));
			t_seats2.put(v, new Integer(1));
			t_seats3.put(v, new Integer(2));
			t_seats4.put(v, new Integer(3));
			t_seats5.put(v, new Integer(4));
		}
		
		t_config1 = SeatConfiguration.of(t_seats1);
		t_config2 = SeatConfiguration.of(t_seats2);
		t_config3 = SeatConfiguration.of(t_seats3);
		t_config4 = SeatConfiguration.of(t_seats4);
		t_config5 = SeatConfiguration.of(t_seats5);
		
		t_flight1 = SimpleFlight.of(t_code1, t_leg1, t_schedule1, t_config1);
		t_flight2 = SimpleFlight.of(t_code1, t_leg2, t_schedule2, t_config2);
		t_flight3 = SimpleFlight.of(t_code1, t_leg3, t_schedule3, t_config3);
		t_flight4 = SimpleFlight.of(t_code2, t_leg4, t_schedule4, t_config4);
		t_flight5 = SimpleFlight.of(t_code1, t_leg2, t_schedule4, t_config5);
		
		t_group1 = FlightGroup.of(t_airport1);
		t_group2 = FlightGroup.of(t_airport2);
		t_group3 = FlightGroup.of(t_airport3);
		t_group4 = FlightGroup.of(t_airport4);
		
		t_fare1 = FareClass.of(1, SeatClass.BUSINESS);
		t_fare2 = FareClass.of(2, SeatClass.BUSINESS);
		t_fare3 = FareClass.of(1, SeatClass.ECONOMY);
		t_fare4 = FareClass.of(2, SeatClass.ECONOMY);
		
		t_bifunction1 = (a, b) -> FlightPolicy.strict(t_flight1).seatsAvailable(b);
		t_bifunction2 = (a, b) -> FlightPolicy.restrictedDuration(t_flight1, t_duration1).seatsAvailable(b);
		t_bifunction3 = (a, b) -> FlightPolicy.reserve(t_flight1, 0).seatsAvailable(b);
		t_bifunction4 = (a, b) -> FlightPolicy.limited(t_flight1).seatsAvailable(b);
		
		t_policy1 = FlightPolicy.of(t_flight1, t_bifunction1);
		t_policy2 = FlightPolicy.of(t_flight1, t_bifunction2);
		t_policy3 = FlightPolicy.of(t_flight1, t_bifunction3);
		t_policy4 = FlightPolicy.of(t_flight1, t_bifunction4);
	}
	
	/*
	 * 
	 * Tests are ordered by class in alphabetical order
	 * 
	 */
	
	@Test 
	void airport_equalsTest()
	{
		testCreate();
		
		assertEquals(false, t_airport1.equals(t_duration1));
		assertEquals(false, t_airport1.equals(null));
		assertEquals(false, t_airport1.equals(t_airport2));
		Airport temp = Airport.of(t_code1, t_duration1);
		assertEquals(true, t_airport1.equals(temp));
	}
	
	@Test
	void airport_hashCodeTest()
	{
		testCreate();
		
		assertEquals(t_airport1.hashCode(), t_code1.hashCode());
	}
	
	@Test
	void fareClass_ofTest()
	{
		assertThrows(IllegalArgumentException.class, () -> {FareClass.of(-1, SeatClass.BUSINESS); });
	}
	
	@Test
	void flightGroup_addTest()
	{
		testCreate();
		
		assertThrows(NullPointerException.class, () -> {t_group1.add(null); });
		assertThrows(IllegalArgumentException.class, () -> {t_group1.add(t_flight4); });
	
		assertEquals(true, t_group1.add(t_flight1));
		assertEquals(false, t_group1.add(t_flight1));
		SimpleFlight temp = SimpleFlight.of(t_code1, t_leg2, t_schedule2, t_config1);
		assertEquals(false, t_group1.add(temp));
		assertEquals(false, t_group1.add(t_flight2));
		
	}
	
	@Test
	void flightGroup_removeTest()
	{
		testCreate();
		
		assertThrows(NullPointerException.class, () -> {t_group1.remove(null); });
		assertThrows(IllegalArgumentException.class, () -> {t_group1.remove(t_flight4); });
		
		t_group1.add(t_flight1);
		t_group1.add(t_flight2);
		t_group1.add(t_flight3);
		
		assertEquals(true, t_group1.remove(t_flight1));
		assertEquals(false, t_group1.remove(t_flight1));
	}
	
	@Test 
	void flightGroup_flightsAtOrAfterTest()
	{
		testCreate();
		
		assertThrows(NullPointerException.class, () -> {t_group1.flightsAtOrAfter(null); });
		
		t_group1.add(t_flight1);
		t_group1.add(t_flight2);
		t_group1.add(t_flight3);
		t_group1.add(t_flight5);
		
		Set<Flight> returnSet = new HashSet<Flight>();

		returnSet.add(t_flight5);
		
		assertEquals(returnSet, t_group1.flightsAtOrAfter(t_time2));
	}
	
	@Test
	void flightPolicy_strictTest()
	{
		testCreate();
		
		assertEquals(false, t_policy1.seatsAvailable(t_fare1).hasSeats());
		assertEquals(true, FlightPolicy.strict(t_flight2).hasSeats(t_fare2));
	}
	
	@Test
	void flightPolicy_restrictedDurationTest()
	{
		testCreate();
		
		assertEquals(false, t_policy2.seatsAvailable(t_fare1).hasSeats());
		assertEquals(true, FlightPolicy.restrictedDuration(t_flight2, t_duration1).hasSeats(t_fare1));
		assertEquals(true, FlightPolicy.restrictedDuration(t_flight2, t_duration0).hasSeats(t_fare1));
	}
	
	@Test
	void flightPolicy_reserveTest()
	{
		testCreate();
		
		assertEquals(false, t_policy3.seatsAvailable(t_fare1).hasSeats());
		assertEquals(false, FlightPolicy.reserve(t_flight2, 1).hasSeats(t_fare1));
		assertEquals(true, FlightPolicy.reserve(t_flight3, 1).hasSeats(t_fare1));
	}
	
	@Test
	void flightPolicy_limitedTest()
	{
		testCreate();
		
		assertEquals(false, t_policy4.seatsAvailable(t_fare1).hasSeats());
		assertEquals(false, FlightPolicy.limited(t_flight1).hasSeats(t_fare1));
		assertEquals(true, FlightPolicy.limited(t_flight2).hasSeats(t_fare3));
	}
	
	@Test
	void flightPolicy_multiPolicyTest()
	{
		testCreate();
		
		assertEquals(true, FlightPolicy.strict(t_flight3).hasSeats(t_fare1));
		assertEquals(true, FlightPolicy.reserve(t_flight3, 1).hasSeats(t_fare1));
		assertEquals(true, FlightPolicy.restrictedDuration(t_flight3, t_duration1).hasSeats(t_fare1));
		assertEquals(false, FlightPolicy.restrictedDuration(t_flight3, t_duration0).hasSeats(t_fare3));
	}
	
	@Test
	void seatConfiguration_seatsTest()
	{
		testCreate();
		
		assertThrows(NullPointerException.class, () -> {t_config2.seats(null); });
		
		assertEquals(new Integer(1), t_config2.seats(SeatClass.BUSINESS));
	}
	
	@Test
	void seatConfiguration_setSeatsTest()
	{
		testCreate();
		
		assertThrows(NullPointerException.class, () -> {t_config2.setSeats(null, 0); });
		
		assertEquals(1, t_config2.setSeats(SeatClass.BUSINESS, 5));
		assertEquals(5, t_config2.seats(SeatClass.BUSINESS));
	}
	
	@Test
	void seatConfiguration_hasSeatsTest()
	{
		testCreate();
		
		assertEquals(true, t_config2.hasSeats());
		
		for(SeatClass v : SeatClass.values())
		{
			t_config2.setSeats(v, 0);
		}
		
		assertEquals(false, t_config2.hasSeats());
	}
	

}
