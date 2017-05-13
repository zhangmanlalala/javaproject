package LeetCode_111_Trapping_Rain_Water;

import com.sun.org.apache.bcel.internal.generic.NEW;


/**
 * Given n non-negative integers representing an elevation map 
 * where the width of each bar is 1, compute how much water it is able to trap after raining.
   For example,
   Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * 
 * @author Administrator
 *
 */
public class Solution {

	public static void main(String[] args) {
		int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
		System.out.println(new Solution().trappingWater(arr));

	}
	
	public int trappingWater(int[] arr){
		int idx1 = -1;
		int idx2 = -1;
		boolean flag = true;
		int sum = 0;
		for(int i=0;i<arr.length-1;i++){
			if(flag){
				if(arr[i+1]<arr[i]){
					if(idx1==-1){
						idx1 = i;
					}
				}else if(arr[i+1]==arr[i]){
					
				}else if(idx1>-1){
					flag = false;
				}
			}
			if(!flag){
				if(arr[i+1]>=arr[i]){
					idx2 = i+1;
				}else{
					int min_num = arr[idx2];
					if(arr[idx2]>arr[idx1]){
						min_num = arr[idx1];
					}
					for(int j=idx1+1;j<=idx2-1;j++){
						if(min_num-arr[j]>0){
							sum = sum+min_num-arr[j];
						}
					}
					idx1 = -1;
					idx2 = -1;
					flag = true;
					i--;
				}
			}
			
			
		}
		
		return sum;
	}

}
