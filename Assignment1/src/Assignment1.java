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
import java.util.Scanner;

public class Assignment1 {
	
	/*
	 * @brief The main method of the program
	 */
	public static void main(String[] args) {
		Scanner inputObj = new Scanner(System.in);
		
		System.out.println("Enter 2 strings with each word seperated by a space");
		
		String ina = inputObj.nextLine();
		String inb = inputObj.nextLine();
		
		Comparator <String> comp = new Comparator<String>() {
			@Override
			public int compare(String e1, String e2) {
				return e1.compareTo(e2);
			}
		};
		
		List<String> a = Arrays.asList(ina.split(" "));
		List<String> b = Arrays.asList(inb.split(" "));
		
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
