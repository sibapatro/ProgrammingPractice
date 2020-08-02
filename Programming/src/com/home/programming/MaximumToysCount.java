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
 * any given number of these toys or none of these toys. 
 * Price for 
 * A - Rs.5, 
 * B - Rs.1 , 
 * C - Rs 3, 
 * D - Rs. 2, 
 * E - Rs.8   
 * 
 * When given the number of toys for each
 * variety in a toy shop, and given an amount R , 
 * 
 * please write a program that
 * gives the max number of toys one could buy.   
 * 
 * For ex , Shop S has  1 A , 10  B, 5 C, 3 D, 0 E and R is Rs25. 
 * Output should be  : 16 . 
 * One could have bought 10 B s for 1 Rs each and 3 of Ds for Rs2 each and 
 * 3 of C for Rs.3 each
 * 
 * Please make necessary assumptions and document it in your solution.
 * 
 * @author Siba Patro
 *
 */
class MaximumToysCount {

	public static void main(String[] args) {
		
		int toysPrice[] = { 5, 1, 3, 2, 8 };
		int toysAvailable[] = new int[toysPrice.length];
		
		try (Scanner sc = new Scanner(System.in)) {
			
			System.out.print("Enter Toys availability in shop for A B C D E :: ");
			for(int i = 0 ; i < toysPrice.length ; i++) {
				toysAvailable[i] = sc.nextInt();
			}
			System.out.print("Enter the payable amount :: ");
			int amount = sc.nextInt();
			
			Map<Integer, Integer> toysPriceMap = generateMap(toysPrice, true);
			Map<Integer, Integer> toysAvailableMap = generateMap(toysAvailable, false);
			int sum = 0;
			int purchaseQuantity = 0;
			Iterator<Map.Entry<Integer, Integer>> priceMapIterator = 
					toysPriceMap.entrySet().iterator();
			while (priceMapIterator.hasNext()) {
				Map.Entry<Integer, Integer> priceEntry = priceMapIterator.next();
				int price = priceEntry.getValue();
				int quantity = toysAvailableMap.get(priceEntry.getKey());
				int tempAmount = price*quantity;
				if(sum + tempAmount <= amount) {
					purchaseQuantity = purchaseQuantity + quantity;
				} else if (sum + tempAmount > amount ) {
					int newQuantity = (amount - sum)/price;
					tempAmount = price * newQuantity;
					purchaseQuantity = purchaseQuantity + newQuantity;
				}
				sum = sum + tempAmount ;
				if(sum == amount) {
					break;
				}
			}
			System.out.println(purchaseQuantity);
		}
	}
	
	static Map<Integer,Integer> generateMap(int[] input,boolean isSortRequired){
		
		Map<Integer, Integer> opMap = new HashMap<>();
		for(int i= 0 ; i < input.length;i++ )
			opMap.put(i, input[i]);
		
		if(isSortRequired) {
			Map<Integer, Integer> opMapSort = opMap.entrySet()
	                .stream()
	                .sorted(Map.Entry.comparingByValue())
	                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
	                		(e1, e2) -> e2, LinkedHashMap::new));
			return opMapSort;
		}
		return opMap;
	}
}
