package leetcode_047_permutations_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:

[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

Subscribe to see which companies asked this q 
 * @author Administrator
 *
 */
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
    	
    	List<Integer> lst = new ArrayList<Integer>();
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	if(nums==null || nums.length==0){
    		return result;
    	}
    	Set<Integer> set = new HashSet<Integer>();
    	Set<List<Integer>> check = new HashSet<List<Integer>>();
    	
    	dfs(nums,set,lst,result,check);
    	
    	return result;
    }
    
   private void dfs(int[] nums,Set<Integer> set,List<Integer> lst,List<List<Integer>> result,Set<List<Integer>> check){
    	
    	if(lst.size() == nums.length){
    		List<Integer> temp = new ArrayList<Integer>();
    		temp.addAll(lst);
    		if(!check.contains(temp)){
    			check.add(temp);
    			result.add(temp);
    		}
    		return ;
    	}
    	
    	
    	for(int i=0;i<nums.length;i++){
    		if(!set.contains(i)){
	    		lst.add(nums[i]);
	    		set.add(i);
    		}else{
    			continue;
    		}
    		
    		dfs(nums,set,lst,result,check);
    		
    		lst.remove(lst.size()-1);
    		set.remove(i);
    	}
    }
   
   
   /**
    * 
    * 还是看标准解法
    * 
    */
   
   
   public List<List<Integer>> permuteUnique2(int[] nums) {
	   List<List<Integer>> res = new ArrayList<List<Integer>>();
	   
	   
	   if(nums == null || nums.length == 0) return res;
	   boolean[] used = new boolean[nums.length];
	   
	   List<Integer> list = new ArrayList<Integer>();
	   Arrays.sort(nums);//我怎么没想到排序
	   
	   Dfs(nums,used,list,res);
	   
	   return res;
   }
   
   
   
   public void Dfs(int[] nums,boolean[] used,List<Integer> list,List<List<Integer>> res){
	   if(list.size() == nums.length){
		   res.add(new ArrayList<Integer>(list));
		   return ;
	   }
	   
	   
	   for(int i=0;i<nums.length;i++){
		   if(used[i]) continue;
		 //a number has the same value with its previous, we can use this number only if his previous is used
		   if(i>0 && nums[i-1]==nums[i] && !used[i-1]) continue;
		   used[i] = true;
		   
		   list.add(nums[i]);
		   Dfs(nums,used,list,res);
		   used[i] = false;
		   
		   list.remove(list.size()-1);
	   }
   }
}
