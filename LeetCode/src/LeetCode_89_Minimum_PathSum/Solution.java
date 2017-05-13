package LeetCode_89_Minimum_PathSum;

/**
 *Given a m x n grid filled with non-negative numbers, 
 *find a path from top left to bottom right which minimizes 
 *the sum of all numbers along its path.
 Note: You can only move either down or right at any point in time. 
 * 
 * 
 * 
 * @author Administrator
 *
 */

/**
 * 
 * 第一步，刻画一个最优解的结构特征
 * 第二步，递归地定义最优解的值
 * 第三部，求出最优解的值，一般使用自底向上的方法，将每一步计算的结果保存在字典中
 * @author Administrator
 *
 */

/**
 * 
 * 这是构建二维字典，每一个格子的值都记录
 * @author Administrator
 *
 */
public class Solution {
	
	
	public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if( m==1 && n==1){
        	return grid[0][0];
        }
        
        int result[][] = new int[m][n];
        
        for(int i=m-1;i>=0;i--){
        	if(i == m-1){
        		result[m-1][n-1] = grid[m-1][n-1];
        	}else{        		
        		result[i][n-1] = grid[i][n-1]+result[i+1][n-1];
        	}       	
        	for(int j=n-2;j>=0;j--){
        		if(i==m-1){
        			result[i][j] = grid[i][j]+result[i][j+1];
        		}else{
        			int path1 = grid[i][j]+result[i][j+1];
        			int path2 = grid[i][j]+result[i+1][j];
        			result[i][j] = Math.min(path1, path2);
        		}
        	}
        }
        
        return result[0][0];
    } 
	
	/**
	 * 
	 * 这是标准解
	 * @param grid
	 * @return
	 */
	
	/**
	 * 
	 * 该解构建了一维字典，记录当前行的最后值即可
	 * @param grid
	 * @return
	 */
	public int minPathSum2(int[][] grid) {
		if(grid==null || grid.length==0 || grid[0].length==0)  
            return 0;  
        int row = grid.length;  
        int col = grid[0].length;  
        int[] dp = new int[col];  
        //除了第一个，其余最大整数，方便后面min  
        for(int i=1; i<col; i++)  
            dp[i] = Integer.MAX_VALUE;  
              
        for(int i=0; i<row; i++){  
            //第一个只有一路往下加起立  
            dp[0] = dp[0] + grid[i][0];  
              
            for(int j=1; j<col; j++)  
                //后面的dp[j]记录的实际上是上一行此位置的最优值  
                dp[j] = Math.min( dp[j-1], dp[j]) + grid[i][j];  
        }  
        return dp[col-1];  
    }  

	
	public static void main(String[] args) {
		 int[][] grid = {
                 {2,0,0},
                 {0,1,1},
                 {0,0,0}
					};
		System.out.println(new Solution().minPathSum(grid));
	}
}
