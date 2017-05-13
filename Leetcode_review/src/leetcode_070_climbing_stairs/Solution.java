package leetcode_070_climbing_stairs;


/**
 * You are climbing a stair case. It takes n steps to reach to the top.
	Each time you can either climb 1 or 2 steps. 
	In how many distinct ways can you climb to the top? 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 典型的动态规划
	 * @param n
	 * @return
	 */
    public int climbStairs(int n) {
    	
    	if(n <=1){
    		return n;
    	}
    	int[] result = new int[n+1];
    	
    	result[0] = 0;
    	result[1] = 1;
    	for(int i=2;i<=n;i++){
    		result[i] = result[i-1]+result[i-2];
    	}
    	
    	return result[n];
    	
    }
}
