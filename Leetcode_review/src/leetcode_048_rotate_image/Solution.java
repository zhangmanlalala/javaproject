package leetcode_048_rotate_image;
/**
 * 
 *You are given an n x n 2D matrix representing an image.
	Rotate the image by 90 degrees (clockwise).
	Follow up:
	Could you do this in-place? 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	
    public void rotate(int[][] matrix) {
    	if(matrix==null || matrix.length==1){
    		return ;
    	}
    	  	
    	for(int i=1;i<=(matrix[0].length+1)/2;i++){
    		swap(matrix,i-1,matrix[0].length-i);
    	}
    }
    
    
    
    private void swap(int[][] matrix,int i,int j){
    	if(i==j){
    		return ;
    	}
    	int store[] = new int[j-i+1];
    	for(int m=i;m<=j;m++){
    		store[m-i] = matrix[m][j];
    	}
    	for(int m=j;m>=i;m--){
    		
    		matrix[m][j]=matrix[i][m];    		
    	}
    	
    	for(int m=i;m<=j;m++){
    		matrix[i][i+j-m] = matrix[m][i];
    	}
    	
    	
    	for(int m=i;m<=j;m++){
    		if(m==j){
    			matrix[m][i] = store[j-i];
    		}else{
    			matrix[m][i] = matrix[j][m];
    		}
    	}
    	
    	for(int m=i;m<=j;m++){
    		    matrix[j][i+j-m] = store[m-i];
    	}
    }
}
