package LeetCode_64_Merge_Sorted_Array;

import java.util.Arrays;

/**
 * 
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * @author Administrator
 * Note:
   You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. 
   The number of elements initialized in nums1 and nums2 are m and n respectively.
 *
 */
public class Solution {
	 public void merge(int[] nums1, int m, int[] nums2, int n) {
	    
	    int[] nums1_mod = Arrays.copyOf(nums1, nums1.length);
//	    for(int t=0;t<m;t++){
//	    	newArr[t] = nums1[t];
//	    }
	    int i=0,j=0,num=0;
	    
	    while(i<m && j<n){
	    	if(nums1_mod[i]<nums2[j]){
	    		nums1[num] = nums1_mod[i];
	    		i++;
	    		num++;
	    	}else{
	    		nums1[num] = nums2[j];
	    		j++;
	    		num++;
	    	}
	    }
	    while(i<m){
	    	nums1[num] = nums1_mod[i];
    		i++;
    		num++;
	    }
	    while(j<n){
	    	nums1[num] = nums2[j];
    		j++;
    		num++;
	    }

	 }
	 
	 public static void main(String[] args) {
		int[] nums1 = {0};
		int[] nums2 = {1};
		new Solution().merge(nums1, 0, nums2, 1);
		System.out.println(nums1[0]);
	}
}
