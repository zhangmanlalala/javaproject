package leetcode_046_permutations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * 
 * Given a collection of distinct numbers, return all possible permutations. 
 *  For example,
[1,2,3] have the following permutations:

[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

 * 
 * @author Administrator
 *
 */
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
    	
    	
    	List<Integer> lst = new ArrayList<Integer>();
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	if(nums==null || nums.length==0){
    		return result;
    	}
    	Set<Integer> set = new HashSet<Integer>();
    	
    	dfs(nums,set,lst,result);
    	
    	return result;
        
    }
    
    
    private void dfs(int[] nums,Set<Integer> set,List<Integer> lst,List<List<Integer>> result){
    	
    	if(lst.size() == nums.length){
    		List<Integer> temp = new ArrayList<Integer>();
    		temp.addAll(lst);
    		result.add(temp);
    		return ;
    	}
    	
    	
    	for(int i=0;i<nums.length;i++){
    		if(!set.contains(i)){
	    		lst.add(nums[i]);
	    		set.add(i);
    		}else{
    			continue;
    		}
    		
    		dfs(nums,set,lst,result);
    		
    		lst.remove(lst.size()-1);
    		set.remove(i);
    	}
    }
}
