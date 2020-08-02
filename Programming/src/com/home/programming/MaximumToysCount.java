package com.home.programming;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * GVR C&S | Client: Healthedge | Question
 * 
 * There are  5 varieties of toys, namely A, B,C,D,E. Any toy shop could have
 * any given number of these toys or none of these toys. Price for A - Rs.5, B -
 * Rs.1 , C - Rs 3, D - Rs. 2, E - Rs.8  
 * 
 * When given the number of toys for each variety in a toy shop, and given an
 * amount R ,
 * 
 * please write a program that gives the max number of toys one could buy.  
 * 
 * For ex , Shop S has  1 A , 10 B, 5 C, 3 D, 0 E and R is Rs25. Output should
 * be  : 16 . One could have bought 10 B s for 1 Rs each and 3 of Ds for Rs2
 * each and 3 of C for Rs.3 each
 * 
 * Please make necessary assumptions and document it in your solution.
 * 
 * Assumptions 
 * ------------
 * Variety of toys is fixed as per question.
 * Maximum number of toys is the targeted for solution
 * Matching of total amount will be less than or equal to R, but will have
 * maximum Count of toys to buy. 
 * Toys price is fixed but available toys in shop is based on user input.
 * Unknown Constraints are not handled.
 * 
 * Solution 
 * --------- 
 * 2 maps will be used for a key(toy item) as pivot. 
 * Price array is fixed in the question , hence is converted to LinkedHashMap based on
 * sort by values. Hashmap is used for Shop available input array, for fast
 * retrieval and no sort required.
 * 
 * Run the program and enter values for shop availability and amount as below
 * example.
 * 
 * Enter Toys availability in shop for A B C D E :: 1 10 5 3 0
 * Enter the payable amount :: 25 
 * Maximum quantity of toys can be purchased :: 16
 * 
 * @author Siba Patro
 *
 */
class MaximumToysCount {

	public static void main(String[] args) {
		
		// Fixed Price of toys
		int toysPrice[] = { 5, 1, 3, 2, 8 };
		int toysAvailable[] = new int[toysPrice.length];
		
		try (Scanner sc = new Scanner(System.in)) {
			
			//User input
			System.out.print("Enter Toys availability in shop for A B C D E :: ");
			for (int i = 0; i < toysPrice.length; i++) {
				toysAvailable[i] = sc.nextInt();
			}
			System.out.print("Enter the payable amount :: ");
			int amount = sc.nextInt();
			
			//Sort needed for max count of toys
			Map<Integer, Integer> toysPriceMap = createMap(toysPrice, true); 
			//Map needed to get available toys
			Map<Integer, Integer> toysAvailableMap = createMap(toysAvailable, false); 
			
			//Initializing
			int sum = 0, purchaseQuantity = 0 ;
			int price , quantity, newQuantity, tempAmount;
			Map.Entry<Integer, Integer> priceEntry;
			
			//Logic
			Iterator<Map.Entry<Integer, Integer>> priceMapIterator = toysPriceMap.entrySet().iterator();
			
			while (priceMapIterator.hasNext()) {
				priceEntry = priceMapIterator.next();
				
				price = priceEntry.getValue();
				quantity = toysAvailableMap.get(priceEntry.getKey());
				tempAmount = price * quantity;
				
				if (sum + tempAmount <= amount) 
					purchaseQuantity = purchaseQuantity + quantity;
				else {
					newQuantity = (amount - sum) / price;
					tempAmount = price * newQuantity;
					purchaseQuantity = purchaseQuantity + newQuantity;
				}
				sum = sum + tempAmount;
				if(sum == amount || tempAmount == 0) {
					break;
				}
			}
			
			System.out.println("Maximum quantity of toys can be purchased :: " + purchaseQuantity);
		}
	}

	private static Map<Integer, Integer> createMap(int[] input, boolean isSortRequired) {

		Map<Integer, Integer> opMap = new HashMap<>();
		for (int i = 0; i < input.length; i++)
			opMap.put(i, input[i]);

		if (isSortRequired) {
			Map<Integer, Integer> opMapSort = opMap.entrySet()
					.stream().sorted(Map.Entry.comparingByValue())
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, 
							(e1, e2) -> e2, LinkedHashMap::new));
			return opMapSort;
		}
		return opMap;
	}
}
