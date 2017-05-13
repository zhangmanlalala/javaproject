package leetcode_059_spiral_Matrix_II;
/**
 * 
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 
 * in spiral order.
	For example,
	Given n = 3,
	You should return the following matrix:
	
	[
	 [ 1, 2, 3 ],
	 [ 8, 9, 4 ],
	 [ 7, 6, 5 ]
	]
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public int[][] generateMatrix(int n) {
    	int[][] result = new int[n][n];
    	int overall = 1;
        int row=0,col=n-1;
        if(n==1){
        	result[0][0] = n;
        	return result;
        }
        
        while(row<=col){
        	
        	if(row == col){
        		result[row][col] =overall;
        		break;
        	}
        	
        	for(int j=row;j<=col-1;j++){
        		result[row][j] = overall;
        		overall++;
        	}
        	
        	for(int i=row;i<=col-1;i++){
        		result[i][col] = overall;
        		overall++;
        	}
        	
        	for(int j=col;j>=row+1;j--){
        		result[col][j] = overall;
        		overall++;      		
        	}
        	
        	for(int i=col;i>=row+1;i--){
        		result[i][row] = overall;
        		overall++;
        	}
        	
        	row++;
        	col--;
        	
        }
        
        return result;
        
    }
    
    public static void main(String[] args) {
    	int[][] result = new Solution().generateMatrix(4);
    	for(int i=0;i<result.length;i++){
    		for(int j=0;j<result[0].length;j++){
    			
    			System.out.println(result[i][j]);
    		}
    	}
	}
}
