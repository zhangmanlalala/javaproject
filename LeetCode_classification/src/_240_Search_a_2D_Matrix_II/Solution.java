package _240_Search_a_2D_Matrix_II;
/**
 * 
 *Write an efficient algorithm that searches for a value in an m x n matrix. 
 *This matrix has the following properties:

    Integers in each row are sorted in ascending from left to right.
    Integers in each column are sorted in ascending from top to bottom.

For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]

Given target = 5, return true.

Given target = 20, return false 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 
	 * 已获通过
	 * @param matrix
	 * @param target
	 * @return
	 */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i=0;
        for(;i<m;i++){
        	if(matrix[i][0]>target){
        		break;
        	}else if(matrix[i][0]==target){
        		return true;
        	}
        }
        if(i-1<0){
        	return false;
        }
        for(int j=0;j<n;j++){
        	if(matrix[0][j] == target || matrix[i-1][j]==target){
        		return true;
        	}else if(matrix[0][j]<target && matrix[i-1][j]>target){
        		if(binarySearch(matrix,i-1,j,target)){
        			return true;
        		}
        	}else if(matrix[0][j]>target){
        		return false;
        	}
        }
        
        return false;
    }
    
    private boolean binarySearch(int[][] matrix,int row,int col,int target){
    	int i=0;
    	int j=row;
    	while(i<=j){//二分查找法，这个位置很重要是" <= "
    		int central = (i+j)/2;
    		if(matrix[central][col] == target){
    			return true;
    		}else if(matrix[central][col]>target){
    			j = central-1;
    		}else{
    			i = central+1;
    		}
    	}
    	return false;
    }
}
