package leetcode_078_subsets;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *  Given a set of distinct integers, nums, return all possible subsets.

	Note: The solution set must not contain duplicate subsets.
	
	For example,
	If nums = [1,2,3], a solution is:
	
	[
	  [3],
	  [1],
	  [2],
	  [1,2,3],
	  [1,3],
	  [2,3],
	  [1,2],
	  []
	]

 * @author Administrator
 *
 */
public class Solution {
	
	
	/**
	 * 
	 * 最标准的
	 * @param nums
	 * @return
	 */
	
    public List<List<Integer>> subsets(int[] nums) {
    	
    	
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	result.add(new ArrayList<Integer>());
    	if(nums == null || nums.length==0){
    		return result;
    	}
    	
    	List<Integer> lst = new ArrayList<Integer>();
    	
    	dfs(nums,0,nums.length-1,lst,result);
    	
    	return result;
    	
    }
    
    
    public void dfs(int[] nums,int start,int end,List<Integer> lst,List<List<Integer>> result){
    	
    	
    	if(lst.size()>0){
    		List<Integer> tmp = new ArrayList<Integer>(lst);
    		result.add(tmp);
    	}
    	
    	
    	for(int i=start;i<=end;i++){
    		
    		lst.add(nums[i]);
    		
    		dfs(nums,i+1,end,lst,result);
    		
    		lst.remove(lst.size()-1);
    		

    		
    	}
    }
    
    
}
