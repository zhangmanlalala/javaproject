package leetcode_064_minimum_path_sum;
/**
 * 
 *Given a m x n grid filled with non-negative numbers, 
 *find a path from top left to bottom right which minimizes 
 *the sum of all numbers along its path.

	Note: You can only move either down or right at any point in time. 
 * @author Administrator
 *
 */
public class Solution {
    public int minPathSum(int[][] grid) {
    	
    	int row = grid.length;
    	int col = grid[0].length;
    	
    	int[][] minSum = new int[row][col];
    	minSum[0][0] = grid[0][0];
    	if(row == 1){
    		for(int j=1;j<col;j++){
    			minSum[0][j] = grid[0][j]+minSum[0][j-1];
    		}
    		
    		return minSum[row-1][col-1];
    		
    	}else if(col == 1){
    		for(int i=1;i<row;i++){
    			minSum[i][0] = grid[i][0]+minSum[i-1][0];
    		}
    		return minSum[row-1][col-1];
    		
    	}
    	
    	//初始化第一行和第一列
    	for(int j=1;j<col;j++){
			minSum[0][j] = grid[0][j]+minSum[0][j-1];
		}
   	
    	for(int i=1;i<row;i++){
			minSum[i][0] = grid[i][0]+minSum[i-1][0];
		}
    	
    	for(int i=1;i<row;i++){
    		
    		for(int j=1;j<col;j++){
    			minSum[i][j] = Math.min(minSum[i-1][j], minSum[i][j-1])+grid[i][j];
    		}
    	}
    	
    	return minSum[row-1][col-1];
    }
}
