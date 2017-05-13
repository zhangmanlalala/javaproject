package leetcode_090_subsets_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 *  Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,2], a solution is:

	[
	  [2],
	  [1],
	  [1,2,2],
	  [2,2],
	  [1,2],
	  []
]

 * 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 
	 * ”÷ «øº≤Èdfs
	 * @param nums
	 * @return
	 */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	
    	result.add(new ArrayList<Integer>());
    	if(nums == null || nums.length == 0){
    		return result;
    	}
    	
    	Arrays.sort(nums);
    	List<Integer> lst = new ArrayList<Integer>();
    	Set<List<Integer>> set = new HashSet<List<Integer>>();
    	
    	dfs(nums,0,lst,set);
    	
    	result.addAll(set);
    	
    	return result;
    	
    }
    
    private void dfs(int[] nums,int start,List<Integer> lst,Set<List<Integer>> set){
    	
    	
    	if(lst.size()>0){
    		List<Integer> temp = new ArrayList<Integer>(lst);
    		if(!set.contains(temp)){
    			set.add(temp);
    		}	
    	}
    	
    	
    	for(int i=start;i<nums.length;i++){
    		
    		lst.add(nums[i]);
    		
    		dfs(nums,i+1,lst,set);
    		
    		lst.remove(lst.size()-1);
    	}
    }
    
    public static void main(String[] args) {
    	int[] nums = new int[]{1,2,2};
    	
    	List<List<Integer>> result = new Solution().subsetsWithDup(nums);
    	for(List<Integer> temp:result){
    		System.out.println(temp.toString());
    	}
	}
}
