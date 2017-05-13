package LeetCode_90_UniquePath2;

import sun.applet.Main;

public class Solution {
	 public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		   int m = obstacleGrid.length;
			int n = obstacleGrid[0].length;
			
//	 		if(obstacleGrid[0][0] == 1){
//	 			return 0;
//	 		}
				
			if(m-1==0){
			    if(n-1==0){
			        return 0;
			    }else{
	    			for(int h=0;h<m-1;h++){
	    				if(obstacleGrid[0][h] == 1){
	    					return 0;
	    				}
	    			}
	    			return 1;
			    }
			}
		   if(n-1 == 0){
		       if(m-1==0){
		           return 0;
		       }else{
	    		   for(int h=1;h<n-1;h++){
	    				if(obstacleGrid[h][0] == 1){
	    					return 0;
	    				}
	    			}
	    		   return 1;
		       }
		   }
		   
		   
		   
		   int result[][] = new int[m][n];			
		   result[m-1][n-1] = 0;
		   boolean obstacle = false;
		   for(int i=m-1;i>=0;i--){
			    if(obstacleGrid[i][n-1] == 1){
			    	obstacle = true;
			    	result[i][n-1] = 0;
			    }else if(!obstacle){
			    	result[i][n-1] = 1;
			    }
				result[m-1][n-1] = 0;
				for(int j=n-2;j>=0;j--){
					if(obstacleGrid[i][j] == 1){
						if(i==m-1){
							break;	
						}else{
							result[i][j] = 0;
						}
					}else{
						if(i == m-1){
							result[i][j] = 1;
						}else{
							result[i][j] = result[i+1][j]+result[i][j+1];
						}
					}
					
				}
			}
			return result[0][0];
	   }
		
	
	 
	 
	 public static void main(String[] args) {
		 int[][] obstacleGrid = {
		                         {0,0,0},
		                         {0,1,0},
		                         {0,0,0}
		 						};
		 System.out.println(new Solution().uniquePathsWithObstacles(obstacleGrid));
	}
		 
		 
	        
	 
}
