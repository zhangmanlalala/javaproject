package leetcode_051_N_queens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]

 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * 这道题是经典的DFS回溯算法，用一维数组就能表示皇后的位置，数组下标表示横坐标，数组值表示纵坐标
	 * 正是这个一维数组，在回溯找结果的时候不需要进行remove重置操作，因为回溯的时候刚好回到上一行，就可以在重新找下一个
	 * 合法坐标了
	 * @param n
	 * @return
	 */
    public List<List<String>> solveNQueens(int n) {
        
    	List<List<String>> res = new ArrayList<List<String>>();
    	if(n<0){
    		return res;
    	}
    	int[] columnVal = new int[n];
    	
    	DFS_helper(n,res,0,columnVal);
    	
    	return res;
    }
    
    
    public void DFS_helper(int nQueens,List<List<String>> res,int row,int[] columnVal){
    	if(row==nQueens){//保存结果
    		List<String> lst = new ArrayList<String>();
    		StringBuilder sb ;

    		
    		for(int j=0;j<nQueens;j++){
    			sb = new StringBuilder();
    			for(int m=0;m<nQueens;m++){
    				
    				if(m==columnVal[j]){
    					sb.append("Q");
    				}else{
    					sb.append(".");
    				}			
    				
    			}
    			
    			lst.add(sb.toString());
    		}
    		
    		res.add(lst);
    	}else{
    		for(int i=0;i<nQueens;i++){
    			columnVal[row] = i;
    			
    			if(isValid(row,columnVal)){
    				DFS_helper(nQueens,res,row+1,columnVal);
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
