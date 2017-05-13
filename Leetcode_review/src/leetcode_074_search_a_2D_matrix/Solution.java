package leetcode_074_search_a_2D_matrix;
/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

    Integers in each row are sorted from left to right.
    The first integer of each row is greater than the last integer of the previous row.

	For example,
	
	Consider the following matrix:
	
	[
	  [1,   3,  5,  7],
	  [10, 11, 16, 20],
	  [23, 30, 34, 50]
	]

Given target = 3, return true.
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 该题目是在二维数组中进行一维二分查找的问题
	 * @param matrix
	 * @param target
	 * @return
	 */
    public boolean searchMatrix(int[][] matrix, int target) {
    	
    	if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
    		return false;
    	}
    	int m = matrix.length;
    	int n = matrix[0].length;
    	
    	int i =0,j = m*n-1;
    	
    	while(i<=j){
    		
    		int mid = (i+j)/2;
    		
    		if(matrix[mid/n][mid%n] > target){
    			j = mid-1;//舍弃右下角
    		}else if(matrix[mid/n][mid%n] < target){
    			i = mid+1;//舍弃左下角
    		}else{
    			return true;
    		}
    		
    		
    	}
    	
    	return false;
    	
    	
    }
}
