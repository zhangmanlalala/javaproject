package _216_Combination_Sum_III;

import java.util.ArrayList;
import java.util.List;

/**
 *

Find all possible combinations of k numbers that add up to a number n, given that only 
numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]


Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]
 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 考查回溯算法
	 * @param k
	 * @param n
	 * @return
	 */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(k<=0 || n<=0){
        	return result;
        }
        List<Integer> lst  = new ArrayList<Integer>();
        int temp=0;
        dfs(k,1,temp,lst,result,n);
        
        return result;
    }
    
    public void dfs(int k,int idx,int temp,List<Integer> lst,List<List<Integer>> result,int n){
    	
    	if(lst.size() == k){
    		if(temp == n){
    			List<Integer> re = new ArrayList<Integer>();
    			re.addAll(lst);
    			result.add(re);
    		}
    		return ;
    	}
    	
    	for(int i=idx;i<=9;i++){
    		temp = temp+i;
    		lst.add(i);
    		
    		dfs(k,i+1,temp,lst,result,n);//很重要，这里是i+1,而不是idx+1
    		
    		temp = temp-i;
    		lst.remove(lst.size()-1);
    	}
    }
}
