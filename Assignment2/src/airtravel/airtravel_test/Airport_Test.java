package airtravel.airtravel_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import airtravel.*;
import java.time.Duration;
import java.time.LocalTime;

class Airport_Test {
	
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
	
	static FlightGroup t_group1;
	static FlightGroup t_group2;
	static FlightGroup t_group3;
	static FlightGroup t_group4;
	
	//initializes a bunch of objects for testing
	public void testCreate()
	{
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
		
		t_flight1 = SimpleFlight.of(t_code1, t_leg1, t_schedule1);
		t_flight2 = SimpleFlight.of(t_code1, t_leg2, t_schedule2);
		t_flight3 = SimpleFlight.of(t_code2, t_leg3, t_schedule3);
		t_flight4 = SimpleFlight.of(t_code2, t_leg4, t_schedule4);
		
		t_group1 = FlightGroup.of(t_airport1);
		t_group2 = FlightGroup.of(t_airport2);
		t_group3 = FlightGroup.of(t_airport3);
		t_group4 = FlightGroup.of(t_airport4);
	}
	
	@Test
	void flightGroup_addTest()
	{
		testCreate();
		
		assertThrows(NullPointerException.class, () -> {t_group1.add(null); });
		assertThrows(IllegalArgumentException.class, () -> {t_group1.add(t_flight4); });
	
		assertEquals(true, t_group1.add(t_flight1));
		assertEquals(false, t_group1.add(t_flight1));
		SimpleFlight temp = SimpleFlight.of(t_code1, t_leg2, t_schedule2);
		assertEquals(false, t_group1.add(temp));
		
	}
	
	@Test
	void flightGroup_removeTest()
	{
		testCreate();
		
		
	}
	
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
	void testNullParam()
	{		
		testCreate();
		
		assertThrows(NullPointerException.class, () -> {Airport.of(null, t_duration1); });
	}

}
