/*******************************************************************************
 * @file 	WeightedJobScheduler.java
 * @author 	Michael Thompson (mjt106)
 * @date 	10/15/2019
 * @details	This file outlines the weighted job scheduler algorithm
 ******************************************************************************/


package weightedjobschedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.stream.Collectors;

public class WeightedJobScheduler 
{
	/**
	 * @brief links the most valuable object before it in the list 
	 * @param _valueList: a List<Integer> of all the values
	 * @param _focusList: a List<WeightedJobSchedulable> of all the objects searching through
	 * @param endIndex: a int on which where currently compared object is on both of the lists
	 */
	private static void linkBestPredecessor(
			List<Integer> _valueList, 
			List<WeightedJobSchedulable> _focusList, 
			int endIndex)
	{
		for(int j = 0; j < endIndex; j++)
		{
			WeightedJobSchedulable job = _focusList.get(j);
			
			Integer totalValue = _focusList.get(endIndex).value() + _valueList.get(j);
			
			boolean currentIndexOverlapsEnd = !_focusList.get(endIndex).doesOverlap(job);
			boolean totalValueBetterEndValue = totalValue > _valueList.get(endIndex);
					
			if(currentIndexOverlapsEnd && totalValueBetterEndValue)
			{
				_valueList.set(endIndex, totalValue);
				
				_focusList.get(endIndex).linkPredecessor(_focusList.get(j));
			}
		}
	}
	
	/**
	 * @brief finds the maximum value object in the _valueList
	 * @param _valueList: a List<Integer> of all the values
	 * @return the index of highest value in the list
	 */
	private static int maxValueIndex(
			List<Integer> _valueList)
	{
		Integer max = _valueList.get(0);
		int maxIndex = 0;

		for(int i = 1; i < _valueList.size(); i++)
		{

			if(_valueList.get(i) > max)
			{
				max = _valueList.get(i);
				maxIndex = i;
			}
		}
		
		return maxIndex;
	}
	
	/**
	 * @brief goes back through the list and generates the optimal list
	 * @param _focusItem: a WeightedJobSchedulable that starts the linked list
	 * @return a reverse list of the traversed linked list
	 */
	private static List<WeightedJobSchedulable> optimalSchedule(
			WeightedJobSchedulable _focusItem)
	{
		List<WeightedJobSchedulable> solution = new ArrayList<WeightedJobSchedulable>();

		WeightedJobSchedulable link = _focusItem;
		solution.add(link);
		
		while(link.linkedObject() != null)
		{
			link = (WeightedJobSchedulable) link.linkedObject();
			solution.add(link);
		}

		Collections.reverse(solution);
		
		return solution;
	}
	
	/**
	 * @brief finds the weighted job schedule
	 * @param _sortedSet: a SortedSet<WeightedJobSchedulable> to go through
	 * @return a list containing the most valuable schedule in the sorted set
	 */
	public static List<WeightedJobSchedulable> weightedJobSchedule(
			SortedSet<WeightedJobSchedulable> _sortedSet)
	{		
		Objects.requireNonNull(_sortedSet, "Paramter cannot be null");
		if(_sortedSet.size() <= 0)
		{
			return null;
		}

		List<WeightedJobSchedulable> focusList = new ArrayList<WeightedJobSchedulable>(_sortedSet);
		List<Integer> valueList = new ArrayList<Integer>(focusList.stream().map(n -> n.value()).collect(Collectors.toList()));

		for(int i = 0; i < focusList.size(); i++)
		{
			linkBestPredecessor(valueList, focusList, i);
		}

		return optimalSchedule(focusList.get(maxValueIndex(valueList)));
	}
	
	/**
	 * Inner class that will be used to test private methods
	 */
	public class TestHook
	{
		public void linkBestPredecessor(
				List<Integer> _valueList, 
				List<WeightedJobSchedulable> _focusList, 
				int endIndex)
		{
			WeightedJobScheduler.linkBestPredecessor(_valueList, _focusList, endIndex);
		}
		
		public int maxValueIndex(
				List<Integer> _valueList)
		{
			return WeightedJobScheduler.maxValueIndex(_valueList);
		}
		
		public List<WeightedJobSchedulable> optimalSchedule(
				WeightedJobSchedulable _focusItem)
		{
			return WeightedJobScheduler.optimalSchedule(_focusItem);
		}
	}
}
