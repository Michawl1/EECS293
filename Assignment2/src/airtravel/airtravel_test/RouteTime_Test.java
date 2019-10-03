package airtravel.airtravel_test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import airtravel.RouteTime;

import org.junit.jupiter.api.Test;

class RouteTime_Test 
{

	@Test
	void routeTime_compareToTest() 
	{
		RouteTime temp0 = new RouteTime(LocalTime.of(1, 0));
		RouteTime temp1 = new RouteTime(LocalTime.of(1, 1));
		RouteTime temp2 = new RouteTime(LocalTime.of(1, 1));
		RouteTime temp3 = new RouteTime(LocalTime.of(1, 2));
		RouteTime temp4 = new RouteTime(null);
		RouteTime temp5 = new RouteTime(null);
		
		
		assertEquals(0, temp1.compareTo(temp2));
		assertEquals(true, temp1.compareTo(temp0) > 0);
		assertEquals(true, temp1.compareTo(temp3) < 0);
		assertEquals(true, temp1.compareTo(temp4) < 0);
		assertEquals(true, temp4.compareTo(temp1) > 0);
		assertEquals(true, temp5.compareTo(temp4) == 0);
	}
	
	@Test
	void routeTime_getTimeTest()
	{
		RouteTime temp0 = new RouteTime(LocalTime.of(1, 0));
		RouteTime temp1 = new RouteTime(null);
		
		assertEquals(LocalTime.of(1, 0), temp0.getTime());
		assertThrows(IllegalStateException.class, () -> {temp1.getTime();});
	}
}
