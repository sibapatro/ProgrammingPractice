package com.home.programming;

import java.util.Scanner;

public class StringCompression {

	public static void main(String[] args) {
		System.out.print("Before compression String : "); 
		Scanner scan = new  Scanner(System.in);
		String inputString = scan.nextLine();
		String out ="";
		int sum = 1;
		
		for(int j = 0; j < inputString.length()-1;j++) {
			if(inputString.charAt(j)==inputString.charAt(j+1)) {
				sum++;
			}else {
				out = out + inputString.charAt(j) + sum;
				sum = 1;
			}
		}
		out = out + inputString.charAt(inputString.length()-1) + sum ;
		
		System.out.print("After compression : ");
		if(out.length() < inputString.length()) {
			System.out.println(out);
		}else {
			System.out.println(inputString);
		}
		scan.close();
				
	}

}
