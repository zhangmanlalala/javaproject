package leetcode_073_set_Matrix_zeros;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place. 
 * @author Administrator
 *
 */
public class Solution {
	
	
    public void setZeroes(int[][] matrix) {
    	if(matrix == null || matrix.length == 0 || matrix[0].length==0){
    		return ;
    	}
       List<Integer> row = new ArrayList<Integer>();
       List<Integer> col = new ArrayList<Integer>();
       
       int a = matrix.length;
       int b = matrix[0].length;
       for(int i=0;i<a;i++){
    	   
    	   for(int j=0;j<b;j++){
    		   
    		   if(matrix[i][j] == 0){
    			   if(!row.contains(i)){
    				   row.add(i);
    			   }
    			   
    			   if(!col.contains(j)){
    				   col.add(j);
    			   }
    		   }
    		   
    	   }
       }
       
       for(int i=0;i<row.size();i++){
    	   int temp = row.get(i);
    	   for(int j=0;j<b;j++){
    		   matrix[temp][j] = 0;
    	   }
       }
       
       for(int j=0;j<col.size();j++){
    	   int temp = col.get(j);
    	   for(int i=0;i<a;i++){
    		   matrix[i][temp] = 0;
    	   }
       }
     
    }
}
