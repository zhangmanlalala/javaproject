package LeetCode_127_Remove_Duplicates_from_Sorted_Array;

import java.util.Arrays;


/**
 * Given a sorted array, remove the duplicates in place such that 
 * each element appear only once and return the new length.

 * Given input array A = [1,1,2],
	Your function should return length = 2, and A is now [1,2].
 * @author Administrator
 *
 */
public class Solution {
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int sortedArrayRemove(int[] arr){
		if(arr.length ==0){
			return arr.length;
		}
		int j =1;
		for(int i=1;i<arr.length;i++){
			if(arr[i]!=arr[i-1]){
				arr[j] = arr[i];
				j++;
			}
		}
		arr = Arrays.copyOf(arr, j);
		return arr.length;

	}

}
