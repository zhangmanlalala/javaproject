package leetcode_054_sprial_matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Administrator
 *Given a matrix of m x n elements (m rows, n columns), 
 *return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

 */
public class Solution {
	/**
	 * 
	 * µäÐÍµÄµÝ¹é
	 * @param matrix
	 * @return
	 */
    public List<Integer> spiralOrder(int[][] matrix) {
    	
    	List<Integer> lst = new ArrayList<Integer>();
    	if(matrix == null || matrix.length == 0){
    		return lst;
    	}
    	int m = matrix.length;
    	int n = matrix[0].length;
    	recursive(matrix,0,0,m,n,lst);
    	return lst;
        
    }
    
    
    public void recursive(int[][] matrix,int x0,int y0,int rowLen,int colLen,List<Integer> lst){
    	if(colLen == 1){
    		for(int i=x0;i<rowLen+x0;i++){
    			lst.add(matrix[i][y0]);
    		}
    		return ;
    	}else if(rowLen== 1){
    		for(int j=y0;j<colLen+y0;j++){
    			lst.add(matrix[x0][j]);
    		}
    		return;
    	}else if(rowLen==0 || colLen==0){
    		return;
    	}
    	
    	for(int j=y0;j<colLen+y0-1;j++){
			lst.add(matrix[x0][j]);
		}
    	for(int i=x0;i<rowLen+x0-1;i++){
			lst.add(matrix[i][colLen+y0-1]);
		}
    	
    	for(int j=colLen+y0-1;j>=y0+1;j--){
			lst.add(matrix[rowLen+x0-1][j]);
		}
    	for(int i=rowLen+x0-1;i>=x0+1;i--){
			lst.add(matrix[i][y0]);
		}
    	
    	recursive(matrix,x0+1,y0+1,rowLen-2,colLen-2,lst);
    	
    	
    	
    
    }
    
    public static void main(String[] args) {
    	int[][] arr = new int[][]{{ 1, 2, 3},{ 4, 5, 6},{ 7, 8, 9 }};

		System.out.println(new Solution().spiralOrder(arr));
	}
}
