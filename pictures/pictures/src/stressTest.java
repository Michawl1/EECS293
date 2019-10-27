/*******************************************************************************
 * @file 	StressTest.java
 * @author 	Michael Thompson (mjt106)
 * @date 	10/22/2019
 * @details	This file outlines the stress test
 ******************************************************************************/

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.List;

import org.junit.Test;

import pictures.PhotoTime;
import weightedjobschedule.WeightedJobSchedulable;
import pictures.PhotoSchedule;

public class stressTest
{	
	@Test
	public void stress_test() 
	{
		PhotoSchedule stressTest = PhotoSchedule.of(LocalTime.ofSecondOfDay(0), LocalTime.ofSecondOfDay(101));

		int temp1;
		int temp2;
		int temp3;		
	
		for(int i = 0; i < 10000; i++)
		{
			temp1 = (int) (Math.random() * 100.0);
			temp2 = (int) (Math.random() * 100.0);
			temp3 = (int) (Math.random() * 100.0);
			
			if(temp1 < temp2)
			{
				stressTest.addPhotoTime(PhotoTime.of(Integer.toString(i), temp1, temp2, temp3));
			}
		}
		
		assertTrue(stressTest.getM_photoTimes().size() < 5100);
		assertTrue(stressTest.getM_photoTimes().size() > 4600);
		
		List<WeightedJobSchedulable> p = stressTest.schedule();
		
		assertTrue(p.size() >= 32);
		assertTrue(p.size() <= 42);
	}
}
