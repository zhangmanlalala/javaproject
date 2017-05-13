package leetcode_052_N_queues2;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *Follow up for N-Queens problem.

Now, instead outputting board configurations, 
return the total number of distinct solutions. 
 * @author Administrator
 *
 */
public class Solution {

    public int totalNQueens(int n) {
       if(n<=0){
    	   return 0;
       }
       int[] re = new int[1];
    	DFS_helper(re,n,0,new int[n]);
    	return re[0];
    }
    
    public void DFS_helper(int[] re,int nQueens,int row,int[] columnVal){
    	if(row==nQueens){//保存结果
    		re[0]++;
    	}else{
    		for(int i=0;i<nQueens;i++){
    			columnVal[row] = i;
    			
    			if(isValid(row,columnVal)){
    				DFS_helper(re,nQueens,row+1,columnVal);
    			}
    		}
    		
    	}
    }
    
    public boolean isValid(int row,int[] columnVal){
    	for(int i=0;i<row;i++){
    		if(columnVal[row] == columnVal[i] || Math.abs(columnVal[row] -columnVal[i]) == row-i)//这代表了斜对角也不行
    			return false;
    	}
    	
    	return true;
    }
    
  
 
}
