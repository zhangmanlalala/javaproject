package leetcode_063_unique_paths_2;

public class Solution {
	
	//还是动态规划
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        
    	int row = obstacleGrid.length;
    	int col = obstacleGrid[0].length;
    	if(row ==1){
    		for(int j=0;j<col;j++){
    			if(obstacleGrid[0][j] == 1){
    				return 0;
    			}
    		}
     		return 1;
    	}else if(col == 1){
    		for(int i=0;i<row;i++){
    			if(obstacleGrid[i][0] == 1){
    				return 0;
    			}
    		}
     		return 1;
    	}
    	int result[][] = new int[row][col];
    	
    	for(int j=0;j<col;j++){
    		if(obstacleGrid[0][j] == 1){
				break;
			}else{
				result[0][j] = 1;
			}
    	}
    	
    	for(int i=0;i<row;i++){
    		if(obstacleGrid[i][0] == 1){
    			break;
    		}else{
    			result[i][0] = 1;
    		}
    	}
    	
    	
    	for(int i=0;i<row;i++){
    		for(int j=0;j<col;j++){
    			
    			
    			if(obstacleGrid[i][j] == 1){
    				result[i][j] = 0;
    			}else{
    				result[i][j] = result[i-1][j]+result[i][j-1];
    			}
    		}
    	}
    	
    	return result[row-1][col-1];
    }
}
