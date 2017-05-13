package _167_Two_Sum_II_Input_array_is_sorted;
/**
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	
    public int[] twoSum(int[] numbers, int target) {
    	if(numbers.length<2){
    		return null;
    	}
        int pre = 0;
        int last = numbers.length-1;
        while(pre<last){
        	if(numbers[pre] + numbers[last]<target){
        		pre = pre+1;
        	}else if(numbers[pre] + numbers[last]>target){
        		last = last-1;
        	}else{
        		break;
        	}
        }
        if(pre<last){
        	return new int[]{pre+1,last+1};
        }else{
        	return null;
        }
    }
}
