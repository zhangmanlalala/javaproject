package leetcode_015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * Given an array S of n integers, are there elements a, b, c in S such that 
 * a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

	Note: The solution set must not contain duplicate triplets.
	
	For example, given array S = [-1, 0, 1, 2, -1, -4],
	
	A solution set is:
	[
	  [-1, 0, 1],
	  [-1, -1, 2]
	]

 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * 考查DFS回溯算法,注意恢复现场,实际中，回溯算法超时
	 * @param nums
	 * @return
	 */
    public List<List<Integer>> threeSum(int[] nums) {
        List<Integer> lst = new ArrayList<Integer>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length<3){
        	return result;
        }
        Arrays.sort(nums);
        Set<String> set = new HashSet<String>();
        dfs(nums,0,0,lst,result,set);
        
        return result;
        

    }
    
    private void dfs(int[] nums,int sum,int k,List<Integer> lst,List<List<Integer>> result,Set<String> set){
    	if(sum>0){
    		return ;
    	}
    	if(lst.size() == 3){
    		if(sum == 0){
				List<Integer> rs = new ArrayList<Integer>();  				
				rs.addAll(lst);
    			String temp = rs.toString();
    			if(!set.contains(temp)){   				
    				result.add(rs);
    				set.add(temp);
    			}
    		}
    		
    		return ;
    	}
    	
    	for(int i=k;i<nums.length;i++){
    		sum = sum+nums[i];
    		lst.add(nums[i]);
    		
    		dfs(nums,sum,i+1,lst,result,set);
    		
    		
    		sum = sum-lst.remove(lst.size()-1);
    		
    	}
    }
    
    public static void main(String[] args) {
		
		System.out.println(new Solution().threeSum2(new int[]{-1, 0, 1, 2, -1, -4}));
	}
    
    
    
    public List<List<Integer>> threeSum2(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length<3){
        	return result;
        }
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        
        int i=0;
        int k=0,j=0;
        
        for(i=0;i<nums.length-1;i++){
        	k = i+1;
        	j = nums.length-1;
        	while(k<j){
        		int sum = nums[i]+nums[k]+nums[j];
        		if(sum == 0){

        			List<Integer> lst = new ArrayList<Integer>();
        			lst.add(nums[i]);
        			lst.add(nums[k]);
        			lst.add(nums[j]);
        			if(!set.contains(lst)){

            			result.add(lst);
            			
            			set.add(lst);
            		
        			}
        			
        			k++;
        			j--;
        		}else if(sum>0 ){
        			j--;
        		}else{
        			k++;
        		}
        	}
        	
        	
        	
        }

        
        return result;
        

    }
    
}
