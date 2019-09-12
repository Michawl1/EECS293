package airtravel.airtravel_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import airtravel.*;
import java.time.Duration;
import java.time.LocalTime;

class Airport_Test {

	@Test
	void test() 
	{
		Duration duration = Duration.ofHours(3);
		Airport test = Airport.of("Cle", duration);
		Duration duration2 = Duration.ofHours(4);
		Airport test2 = Airport.of("Pit", duration2);
		assertEquals("Cle", test.getCode());
		assertEquals(duration, test.getConnectionTimeMin());
		
		
		Leg testLeg = Leg.of(test, test2);
		assertEquals(test, testLeg.getOrigin());
		assertEquals(test2, testLeg.getDestination());
		
		
		LocalTime timeTest1 = LocalTime.of(2, 30);
		LocalTime timeTest2 = LocalTime.of(3, 45);
		
		FlightSchedule testSchedule = FlightSchedule.of(timeTest1, timeTest2);
		assertEquals(timeTest1, testSchedule.getDepartureTime());
		assertEquals(timeTest2, testSchedule.getArrivalTime());
		
		
		Flight testFlight = SimpleFlight.of("Cle", testLeg, testSchedule);
		
	
		assertEquals(true, test.addFlight(testFlight));
		
	}

}
