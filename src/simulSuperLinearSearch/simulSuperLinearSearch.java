/**
 * Author: Gabriel O'Donnell 
 * Class: CS310
 * Institution: Truman State University
 * 
 * This program is made to test the effectiveness of the linear search algorithm.
 * We are running a simulation with a random Arraylist of numbers and a random search
 * value. These random values can be as large as the ArrayList size itself. 
 * In this case we are allowing for duplicates in the ArrayList so we can find the 
 * search value in the list multiple times. This program will output some statistics into 
 * console.
 */
package simulSuperLinearSearch;

import java.util.ArrayList;
import java.util.Random;
import java.util.Comparator;

public class simulSuperLinearSearch {
	
	public static int comparisons = 0; //This will keep track of comparisons in sim, must be available to all methods in the class
	public static int hit = 0; //This keeps track of in simulation hits by the simulation run
	public static int miss = 0; //This keeps track of in simulation hits by the simulation run
	
	public static void main(String[] args) {
		
		ArrayList<Integer> array = new ArrayList<Integer>();
		
	    int listSize = 100000; 
	    int simulationSize = 10000;
	    
	    double totalComparisons = 0; // All of these are used for analysis at the end
	    double totalHits = 0;
	    double totalMisses = 0;
	    int max = 0;
	    int min = 0;

		
	    
	    //This is the actual simulations 
	    for(int i =0; i <simulationSize ; i++) {
	    	
	    	//clear out the list from last simulation
	    	array.clear();
	    	//clear out in sim data63,014.774
	    	comparisons = 0;
	    	miss = 0;
	    	hit = 0;
	    	
	    	Random rand = new Random(); //This will generate number between 0 - a specified upperbound
	 		int searchValue = rand.nextInt(listSize+1);//then the nextInt will round, so at an upperbound of 10 I'll get
	 											   //numbers between 0-9.99 and it will round so we do size +1 
	 		
	    	//make the new list
	    	for(int j = 0; j < listSize; j++) { //go through the list
				array.add(rand.nextInt(listSize+1));
			}
	    	array.sort(Comparator.naturalOrder()); // This will sort the array in order of least to greatest

	    	
	    	
	    	
	    	ArrayList<Integer> searchResult = linearSearch(array, searchValue);	//This is where the search is performed
	    	
	    	//update the totals
	    	totalComparisons = totalComparisons + comparisons;
	    	totalHits = hit + totalHits;
	    	totalMisses = miss + totalMisses;
	    	
	    	//This is where we will check if this sim run was a max or min
	    	if (comparisons > max) {
	    		max = comparisons;
	    	}
	    	if(min > comparisons || i == 0) { //This will set the min the the first run or change it if its the new minimum
	    		min = comparisons;
	    	}

	    }

	    
	    //Output the data after the sim
	    
	    System.out.println("Simulation size: " + simulationSize);
	    System.out.println("Array size: " + listSize);
        System.out.printf("Total amount of Hits: %.0f " ,totalHits);
	    System.out.println();
	    System.out.printf("Total amount of Misses: %.0f " ,totalMisses);
	    System.out.println();
	    System.out.println("Max comparisons: " + max);
	    System.out.println("Min comparisons: " + min);
	    System.out.println("Average Comparisons: " + (totalComparisons/simulationSize)); //Should equal sim size * list size
	    System.out.println();

	    
	  		
		

	}
	/**
	 * This is the algorithm used to search the ArrayList, in this particular case we are allowing 
	 * duplicate values and we are searching for all instances of that value in the given ArrayList
	 * The given array is assumed to be sorted and thus once it has found the value it will track the indexs 
	 * all duplicates which should come after the first instance, then stop one the next value has been reached.
	 * 
	 * For output analysis we have the hit, miss, and comparisons varibles being iterated
	 * a hit is when the index is equal to the search value
	 * a miss is when the index is not equal to the search value
	 * a comparison is added each time we look at a value.
	 * 
	 * @param array: a randomized array to search through for the search value
	 * @param searchNum: the value we are looking for in the arraylist
	 * @return this returns an arraylist contain all the indexes that match the searchNum
	 */
	
	public static ArrayList<Integer> linearSearch(ArrayList<Integer> array, int searchNum){
		boolean found_value = false;
		ArrayList<Integer> result = new ArrayList<Integer>(); // Create an ArrayList to return
		 
		if (array.size() == 0) { // Check if its empty
			return result; // The returned result would be empty due to no ability to search
		}
		
		
		for(int index = 0; index < array.size(); index++) { //go through the list
			
			if(array.get(index) == searchNum) { // Check if they are equal
				result.add(index); //If they are add it to the result List
				found_value = true;
				hit++; 
				
			}
			else {
				if(found_value == true) {// this will end the search after all versions of the value have been counted
					return result;
				}
				miss++;
				
			}
			
			comparisons++;
		}
		
		return result;
	}


}
