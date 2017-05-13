package leetcode_018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * Given an array S of n integers, are there elements a, b, c, and d in S 
 * such that a + b + c + d = target? Find all unique quadruplets in the array 
 * which gives the sum of target.
 * 
 * Note: The solution set must not contain duplicate quadruplets.

For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]

 * @author Administrator
 *
 */
public class Solution {
	
	//先使用跟3Sum相同的思路试一下，发现超时
    public List<List<Integer>> fourSum(int[] nums, int target) {
        
    	Arrays.sort(nums);
    	Set<List<Integer>> set = new HashSet<List<Integer>>();
    	
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	int sum = 0;
    	
    	
    	for(int i=0;i<nums.length-3;i++){
    		
    		for(int j=i+1;j<nums.length-2;j++){
    			int m = j+1,n=nums.length-1;
    			while(m<n){
	    			sum = nums[i]+nums[j]+nums[m]+nums[n];
	    			
	    			if(sum == target){
	    				List<Integer> lst  = new ArrayList<Integer>();
	    				lst.add(nums[i]);
	    				lst.add(nums[j]);
	    				lst.add(nums[m]);
	    				lst.add(nums[n]);
	    				
	    				if(!set.contains(lst)){
	    					result.add(lst);
	    					set.add(lst);
	    				}
	    				
	    				m++;
	    				n--;
	    			}else if(sum>target){
	    				n--;
	    			}else{
	    				m++;
	    			}
    			}
    			
    		}
    	}
    	
    	return result;
    	
    }
    
    //使用动态规划试一试
    public List<List<Integer>> fourSum2(int[] nums, int target) {
    	Arrays.sort(nums);
    	
    	List<Integer> lst = new ArrayList<Integer>();
    	
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	
    	Set<List<Integer>> set = new HashSet<List<Integer>>();
    	
    	dfs(nums,0,0,target,lst,result,set);
    	
    	return result;
    }
    
    private void dfs(int[] nums,int k,int sum,int target,List<Integer> lst,List<List<Integer>> result,Set<List<Integer>> set){
    	if(lst.size() < 4){
	    	if(sum>target && k<nums.length && nums[k]>=0){//数组已排序，没有必要继续递归下去
	    		return;
	    	}
    	}else if(lst.size() == 4){
    		if(sum == target){
    			List<Integer> temp = new ArrayList<Integer>();
    			temp.addAll(lst);
    			temp.sort(null);
    			if(!set.contains(temp)){
    				result.add(temp);
    				set.add(temp);
    			}
    		}
    		
    		return ;
    	}
    	
    	
    	for(int i=k;i<nums.length;i++){
    		lst.add(nums[i]);
    		sum +=nums[i];
    		
    		dfs(nums,i+1,sum,target,lst,result,set);
    		
    		sum = sum - lst.remove(lst.size()-1);//恢复现场
    				
    	}
    }
    
    public static void main(String[] args) {
		System.out.println(new Solution().fourSum2(new int[]{-1,0,1,2,-1,-4}, -1));
	}
}
