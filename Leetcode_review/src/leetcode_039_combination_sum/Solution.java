package leetcode_039_combination_sum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 *  Given a set of candidate numbers (C) (without duplicates) and a target number (T), 
 *  find all unique combinations in C where the candidate numbers sums to T.

    The same repeated number may be chosen from C unlimited number of times.

	Note:
	
	    All numbers (including target) will be positive integers.
	    The solution set must not contain duplicate combinations.
	
	For example, given candidate set [2, 3, 6, 7] and target 7,
	A solution set is: 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        
    	List<Integer> lst = new ArrayList<Integer>();
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	
    	if(candidates == null || candidates.length==0){
    		return result;
    	}
    	
    	Set<List<Integer>> set = new HashSet<List<Integer>>();
    	
    	dfs(candidates,0,target,lst,result,set);
    	
    	return result;
    }
    
    
    private void dfs(int[] candidates,int sum,int target,List<Integer> lst,List<List<Integer>> result,Set<List<Integer>> set){
    	
    	if(sum>target){
    		return ;
    	}else if(sum == target){
    		List<Integer> temp = new ArrayList<Integer>();
    		temp.addAll(lst);
    		temp.sort(null);
    		
    		if(!set.contains(temp)){
    			result.add(temp);
    			set.add(temp);
    		}
    		
    		
    		return ;
    	}
    	
    	for(int i=0;i<candidates.length;i++){
    		sum +=candidates[i];
    		lst.add(candidates[i]);
    		
    		dfs(candidates,sum,target,lst,result,set);
    		
    		
    		sum = sum - lst.remove(lst.size()-1);//注意保护现场
    	}
    	
    	
    }
}
