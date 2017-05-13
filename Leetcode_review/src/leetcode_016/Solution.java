package leetcode_016;

import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such 
 * that the sum is closest to a given number, target. Return the sum of 
 * the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

 * 
 * @author Administrator
 *
 */
public class Solution {
	
    public int threeSumClosest(int[] nums, int target) {
    	 Arrays.sort(nums);
         int closestNum = Math.abs(nums[0]+nums[1]+nums[2]-target);
         int closestSum = nums[0]+nums[1]+nums[2];
         
         int sum = 0;
         for(int i=0;i<nums.length-2;i++){
        	 int j=i+1,k=nums.length-1;
        	 
        	 while(j<k){
        		 sum = nums[i]+nums[j]+nums[k];
        		 
        		 if(sum>target){
        			if(Math.abs(sum-target)<closestNum){
        				closestNum = sum-target;
        				closestSum = sum;
        			}
        			k--;
        		 }else{
        			 if(target-sum<closestNum){
         				closestNum =target - sum;
         				closestSum = sum;
         			 } 
        			j++;
        		 }
        	 }
        	 
        	 
         }
         
         return closestSum;
    }
}
