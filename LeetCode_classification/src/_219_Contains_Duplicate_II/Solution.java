package _219_Contains_Duplicate_II;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 *Given an array of integers and an integer k, find out whether there are two 
 *distinct indices i and j in the array such that nums[i] = nums[j] and the difference 
 *between i and j is at most k.  
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
    	if(k<=0 || nums==null || nums.length == 0 ){
    		return false;
    	}
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        Set<Integer> set = new HashSet<Integer>();
        for(int i=0;i<nums.length;i++){
        	set.add(nums[i]);
        }
        if(set.size() == nums.length){
        	return false;
        }
        
        for(int i=0;i<nums.length;i++){
        	if(!map.containsKey(nums[i])){
        		map.put(nums[i], i);
        	}else{
        		int temp = i - map.get(nums[i]);
        		if(temp<=k){
        			return true;
        		}else{
        			map.put(nums[i], i);
        		}
        	}
        }
        
        return false;
    }
}
