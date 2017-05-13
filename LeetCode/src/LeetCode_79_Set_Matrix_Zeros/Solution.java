package LeetCode_79_Set_Matrix_Zeros;


/**
 * 
 *Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.

Could you devise a constant space solution? 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 我先解出O(m+n)空间的情况
	 */
	/**
	 * 标准答案也是这样的
	 * @param matrix
	 */
	 public void setZeroes(int[][] matrix) {
	    int m = matrix.length;
	    int n = matrix[0].length;
	    int row[] = new int[m];
	    int col[] = new int[n];
	    for(int i=0;i<m;i++){
	    	
	    	for(int j=0;j<n;j++){
	    		if(matrix[i][j] == 0){
	    			if(row[i] != 1 ){
	    				row[i] = 1;
	    			}
	    			if(col[j] != 1){
	    				col[j] = 1;
	    			}
	    		}
	    	}
	    }
	    
	    for(int i=0;i<m;i++){
	    	if(row[i] == 1){
	    		for(int j=0;j<n;j++){
	    			if(matrix[i][j]!=0){
	    				matrix[i][j] =0;
	    			}
	    		}
	    	}
	    }
	    
	    for(int j=0;j<n;j++){
	    	if(col[j] == 1){
	    		for(int i=0;i<m;i++){
	    			if(matrix[i][j]!=0){
	    				matrix[i][j] = 0;
	    			}
	    		}
	    	}
	    }
	 }
	
	

}
