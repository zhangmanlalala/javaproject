package leetcode_040_combination_sum_II;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 *  Given a collection of candidate numbers (C) and a target number (T), 
 *  find all unique combinations in C where the candidate numbers sums to T.

	Each number in C may only be used once in the combination.
	
	Note:
	
	    All numbers (including target) will be positive integers.
	    The solution set must not contain duplicate combinations.
	
	For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
	A solution set is:
	
	[
	  [1, 7],
	  [1, 2, 5],
	  [2, 6],
	  [1, 1, 6]
	]
	
	Subscribe to see which companies asked this question
 * @author Administrator
 *
 */
public class Solution {
	
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> lst = new ArrayList<Integer>();
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(candidates == null || candidates.length==0){
        	return result;
        }
        
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        dfs(candidates,0,0,target,lst,result,set);
        
        return result;
    }
    
    
    private void dfs(int[] candidates,int k,int sum,int target,List<Integer> lst,List<List<Integer>> result,Set<List<Integer>> set){
    	if(sum>target){
    		return;
    	}else if(sum==target){
    		List<Integer> temp = new ArrayList<Integer>();
    		temp.addAll(lst);
    		temp.sort(null);
    		
    		if(!set.contains(temp)){
    			result.add(temp);
    			set.add(temp);
    		}
    		
    		return ;
    		
    	}
    	
    	
    	
    	for(int i=k;i<candidates.length;i++){
    		sum = sum+candidates[i];
    		lst.add(candidates[i]);
    		
    		dfs(candidates,i+1,sum,target,lst,result,set);
    		
    		sum = sum-lst.remove(lst.size()-1);//×¢Òâ»Ö¸´ÏÖ³¡
    	}
    }
}
