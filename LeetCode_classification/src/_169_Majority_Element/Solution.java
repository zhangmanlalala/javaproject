package _169_Majority_Element;

import java.util.HashMap;
import java.util.Map;

/**
 *Given an array of size n, find the majority element. The majority element is the element that 
 *appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array. 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        
        for(int i=0;i<nums.length;i++){
        	if(map.containsKey(nums[i])){
        		map.put(nums[i], map.get(nums[i])+1);
        	}else{
        		map.put(nums[i], 1);
        	}
        }
        
        int maj=nums[0];
        int num = 0;
        for(Integer temp:map.keySet()){
        	if(map.get(temp)>num){
        		maj = temp;
        		num = map.get(temp);
        	}
        }
        
        return maj;
    }
    
    public static void main(String[] args) {
    	int[] nums = new int[]{1,2,3,3};
		System.out.println(new Solution().majorityElement(nums));
	}
}
