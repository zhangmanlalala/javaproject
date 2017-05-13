package _229_Majority_Element_II;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
 * The algorithm should run in linear time and in O(1) space.
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 时间有点略长
	 * @param nums
	 * @return
	 */
    public List<Integer> majorityElement2(int[] nums) {
    	List<Integer> lst = new ArrayList<Integer>();
    	if(nums == null || nums.length == 0){
    		return lst;
    	}
    	if(nums.length<3){
    		for(int i=0;i<nums.length;i++){
    			if(!lst.contains(nums[i])){
    				lst.add(nums[i]);
    			}
    		}
    	}
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        
        for(int i=0;i<nums.length;i++){
        	if(!lst.contains(nums[i])){       	
	        	if(map.containsKey(nums[i])){
	        		map.put(nums[i], map.get(nums[i])+1);
	        		if(map.get(nums[i])>nums.length/3){
	        			lst.add(nums[i]);
	        			map.remove(nums[i]);
	        		}
	        	}else{
	        		map.put(nums[i], 1);
	        	}
        	}
        }
        
        return lst;
    }
    
    
    /**
     * 
     * 看一下标准答案
     * 
     */
    
    public List<Integer> majorityElement(int[] nums) {
    	if(nums == null || nums.length == 0){
    		return new ArrayList<Integer>();
    	}
    	
    	List<Integer> result = new ArrayList<Integer>();
    	int number1 = nums[0],number2 = nums[0],count1 = 0,count2 = 0,len = nums.length;
    	//number1和number2是两个候选人
    	for(int i=0;i<len;i++){
    		if(nums[i] == number1){
    			count1++;
    		}else if(nums[i] == number2){
    			count2++;
    		}else if(count1 == 0){
    			number1 = nums[i];//如果有一个减到0，则换换候选人
    			count1 = 1;
    		}else if(count2 == 0){
    			number2 = nums[i];
    			count2 = 1;
    		}else{
    			count1--;
    			count2--;
    		}
    	}
    	
    	count1 = 0;
    	count2 = 0;
    	
    	for(int i=0;i<len;i++){
    		if(nums[i] == number1){
    			count1++;
    		}else if(nums[i] == number2){
    			count2++;
    		}
    	}
    	
    	if(count1>len/3)
    		result.add(number1);
    	if(count2>len/3)
    		result.add(number2);
    	
    	return result;
    }
}
