package _221_Maximal_Square;
/**
 * 
 *  Given a 2D binary matrix filled with 0's and 1's, find the largest square containing 
 *  only 1's and return its area.
	For example, given the following matrix:	
	1 0 1 0 0
	1 0 1 1 1
	1 1 1 1 1
	1 0 0 1 0	
	Return 4. 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 
	 * @param matrix
	 * @return
	 */
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
        	return 0;
        }
        
        
        int max = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        
        int[][] dp = new int[n+1][m+1];//dp[i][j]代表正方形的长度，该正方形的右下角坐落在matrix[i-1][j-1]
        //dp[i][j] represents the length of the square
        //whose lower-right corner is located at(i,j)
        //dp(i,j) = min{dp(i-1,j-1),dp(i-1,j),dp(i,j-1)}
        //很经典
        for(int i=1;i<=n;i++){
        	for(int j=1;j<=m;j++){
        		
        		if(matrix[i-1][j-1] == '1'){
	        		dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]))+1;
	        		max = Math.max(max, dp[i][j]);
        		}
        	}
        }
        
        return max*max;
        
        
    }
}
