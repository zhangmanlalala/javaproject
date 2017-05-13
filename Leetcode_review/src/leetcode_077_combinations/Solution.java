package leetcode_077_combinations;

import java.util.ArrayList;
import java.util.List;

/**
 *  Given two integers n and k, return all possible combinations of k
 *   numbers out of 1 ... n.

	For example,
	If n = 4 and k = 2, a solution is:

	[
	  [2,4],
	  [3,4],
	  [2,3],
	  [1,2],
	  [1,3],
	  [1,4],
	]

 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 这个题目又是标准的dfs
	 * @param n
	 * @param k
	 * @return
	 */
    public List<List<Integer>> combine(int n, int k) {
    	
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	if(k>n || n<1){
    		return result;
    	}
    	
    	List<Integer> lst = new ArrayList<Integer>();
    	
    	
    	dfs(n,k,1,lst,result);
    	
    	return result;
    	
    	
    	
    }
    
    
    public void dfs(int n,int k,int t,List<Integer> lst,List<List<Integer>> result){
    	
    	if(lst.size() == k){
    		
    		List<Integer> temp = new ArrayList<Integer>(lst);
    		result.add(temp);
    		return ;
    	}
    	
    	
    	for(int i=t;i<=n;i++){
    		lst.add(i);
    		
    		dfs(n,k,i+1,lst,result);
    		
    		lst.remove(lst.size()-1);
    		
    		
    	}
    	
    }
    
    
    public static void main(String[] args) {
		new Solution().combine(4, 2);
	}
}
