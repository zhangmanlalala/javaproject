package leetcode_062_paths;

public class Solution {
	
	//最简单的动态规划，第一步找到子问题，第二步求出状态转移方程
    public int uniquePaths(int m, int n) {
    	
    	if(m == 1 || n == 1){
    		return 1;
    	}
    	
    	int result[][] = new int[m][n];
    	
    	for(int i=0;i<m;i++){
    		result[i][0] = 1;
    	}
    	
    	for(int j=0;j<n;j++){
    		result[0][j] = 1;
    	}
    	
    	
    	for(int i=1;i<m;i++){
    		
    		for(int j=1;j<n;j++){
    			result[i][j] = result[i][j-1]+result[i-1][j];
    		}
    	}
    	
    	return result[m-1][n-1];
    }
    
    
}
