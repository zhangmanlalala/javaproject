package _162_Find_Peak_Element;

import java.util.Stack;

/**
 *A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that rum[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2. 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public int findPeakElement(int[] nums) {
        if(nums == null){
        	return 0;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int peak = nums[0];
        int idx = 0;
        for(int i=0;i<nums.length-1;i++){
        	if(nums[i+1]>=nums[i]){
        		stack.push(nums[i]);//栈中的元素都比当前元素小
        	}else{
        		if(stack.size()>0){
        			if(peak<nums[i]){
        				peak = nums[i];
        				idx = i;
        			}
        			
        			stack.clear();
        		}

        	}
        }
        if(stack.size()>0){
        	if(peak<stack.peek()){
				idx = nums.length-1;
			}
        }
        return idx;
    }
}
