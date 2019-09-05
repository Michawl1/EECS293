/*******************************************************************************
 * @file 	Assignment1.java
 * @author 	Michael Thompson (mjt106)
 * @date 	9/4/2019
 * @details	This class outlines assignment 1 for EECS 293
 ******************************************************************************/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class Assignment1 {
	
	/*
	 * @brief The main method of the program
	 */
	public static void main(String[] args) {
		List<Integer> a = Arrays.asList(1, 2, 2, 3);
		List<Integer> b = Arrays.asList(1, 2, 3);
		Comparator<Integer> comp = new Comparator<Integer>() {
			@Override
			public int compare(Integer e1, Integer e2){
				return Integer.compare(e1, e2);
			}
		};
		
		
		System.out.println(longestSmallerPrefix(a, b, comp));
		
	}
	
	/*
	 * @brief The assignment method for the class
	 * @param[in] a : A List of any type
	 * @param[in] b : A List of the same type as @a
	 * @param[in] cmp : A comparator that implements compare(arg1, arg2) for the type of @a and @b
	 */	
	static <T> List<T> longestSmallerPrefix(List<T> a, List<T> b, Comparator<? super T> cmp) {
		ListIterator<T> aIterator = a.listIterator();
		ListIterator<T> bIterator = b.listIterator();
		
		List<T> returnList = new ArrayList<T>();
		
		T tempA = null;
		T tempB = null;
		
		while(aIterator.hasNext() && bIterator.hasNext()) {
			tempA = aIterator.next();
			tempB = bIterator.next();
			
			if(cmp.compare(tempA, tempB) <= 0) {
				returnList.add(tempA);
			}
			else {
				break;
			}	
		}
		
		return returnList;
	}
}
