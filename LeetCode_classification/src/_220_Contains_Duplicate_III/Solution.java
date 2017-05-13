package _220_Contains_Duplicate_III;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 *Given an array of integers, find out whether there are two distinct indices i 
 *and j in the array such that the difference between nums[i] and nums[j] is at 
 *most t and the difference between i and j is at most k.  
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * 超时算法
	 * @param nums
	 * @param k
	 * @param t
	 * @return
	 */
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
    	if(nums == null || nums.length<=1 || k<1){
    		return false;
    	}
        for(int i=0;i<nums.length-1;i++){
        	for(int j=i+1;j<=i+k && j<nums.length;j++){
        		long temp =nums[j]- nums[i];
        		if(Math.abs(temp)<=t){
        			return true;
        		}
        	}
        }
        
        return false;
    }
    /**
     * 维持一个长度为k的window，每次检查新的值是否与原来窗口中的所有值的差值
     * 有小于等于t的，如果用两个for循环会超时O(nk).使用treeset的subSet函数
     * 可以快速搜索.复杂度为O(nlogk)
     * 
     * 
     */
    
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    	if(k<1 || t<0 || nums==null || nums.length<2) return false;
    	
    	SortedSet<Long> set = new TreeSet<Long>();
    	
    	for(int j=0;j<nums.length;j++){
    		SortedSet<Long> subSet = set.subSet((long)nums[j]-t,(long)nums[j]+t+1);
    		if(!subSet.isEmpty()) return true;
    		
    		if(j>=k){
    			set.remove((long)nums[j-k]);
    		}
    		
    		set.add((long)nums[j]);
    	}
    	
    	return false;
    }
}
