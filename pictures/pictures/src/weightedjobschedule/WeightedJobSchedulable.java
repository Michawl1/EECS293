/*******************************************************************************
 * @file 	WeightedJobSchedulable.java
 * @author 	Michael Thompson (mjt106)
 * @date 	10/15/2019
 * @details	This file outlines the weighted job scheduler interface
 ******************************************************************************/


package weightedjobschedule;

public interface WeightedJobSchedulable 
{
	/** 
	 * @return the Value of the object
	 */
	public Integer value();
	
	/**
	 * @return the next object in a linked list
	 */
	public WeightedJobSchedulable linkedObject();

	/**
	 * @param _otherWeightedJobSchedulable: the other WeightedJobSchedulable to see if overlap
	 * @return whether this and other object overlap
	 */
	public boolean doesOverlap(WeightedJobSchedulable _otherWeightedJobSchedulable);

	/**
	 * @param _newLink: a WeightedJobSchedulable to link to this object
	 */
	public void linkPredecessor(WeightedJobSchedulable _newLink);
	
}
