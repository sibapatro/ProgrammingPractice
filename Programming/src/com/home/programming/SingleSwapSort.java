package com.home.programming;

import java.util.Arrays;

public class SingleSwapSort {
	public static void main(String[] args) {

		int a[] = new int[] { 1, 2, 9, 4, 5, 7, 3, 34 };
		System.out.println("Before sort :"+Arrays.toString(a));
		int temp1 = -1;
		int temp2 = -1;
		int prev = a[0];

		for (int i = 1; i < a.length; i++) {
			if (prev > a[i]) {
				if (temp1 == -1) {
					temp1 = i - 1;
				}
				temp2 = i;
			}
			prev = a[i];
		}

		System.out.println("Indexes need to be swapped : " + temp1 + " <---> " + temp2);

		int temp = a[temp1];
		a[temp1] = a[temp2];
		a[temp2] = temp;

		System.out.println("After sort :"+Arrays.toString(a));
	}
}
