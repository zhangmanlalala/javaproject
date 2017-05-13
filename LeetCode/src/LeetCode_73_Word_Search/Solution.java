package LeetCode_73_Word_Search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once.

For example,

Given board =
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	
	/**
	 * 
	 * 
	 * 我自己做了两天也没做出来
	 * @param board
	 * @param word
	 * @return
	 */
	/**
	 * 从某点开始搜索，直到碰见满足条件的单词，这是典型的DFS应用，这里用另外一个矩阵存储已经访问的位置。
	 * 从某点开始搜索，直到碰见满足条件的单词，这是典型的dfs应用
	 * @param board
	 * @param word
	 * @return
	 */
	public boolean exist(char[][] board, String word) {
		if(word == null|| word.length() == 0) return false;
		
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board[0].length;j++){
				//先找到开头的位置
				if(word.charAt(0) == board[i][j]){
					if(word.length()==1)
						return true;
					else{
						//mask来标记是否访问过
						boolean[][] mask = new boolean[board.length][board[0].length];
						mask[i][j] = true;
						
						if(search(board,i,j,word.substring(1),mask)){
							return true;
						}
						
					}
				}
			}
		}
		
		return false;
    }
	
	public boolean search(char[][] board,int i,int j,String str,boolean[][] mask){
		//每次去掉已经访问的字母，全部去掉时，说明已经找到了
		if(str.length() == 0) return true;
		else{
			//四个方向分别dfs
			if(i>0 && (board[i-1][j] == str.charAt(0)) && (mask[i-1][j]==false)){
				mask[i-1][j] = true;
				//向前搜索
				if(search(board,i-1,j,str.substring(1),mask))
					return true;
				//dfs注意恢复现场
				mask[i-1][j] = false;
			}
			
			if((i<board.length-1) && (board[i+1][j]== str.charAt(0)) && (mask[i+1][j]==false)){
				mask[i+1][j] = true;
				if(search(board,i+1,j,str.substring(1),mask)){ //保证某一层迭代返回true，直接能将这个true传回顶端
					return true;//很经典
				}
				mask[i][j] = false;//dfs恢复现场//如果此路走不通，恢复现场
			}
			
			if((j>0) && (board[i][j-1]==str.charAt(0)) && (mask[i][j-1]==false)){
				mask[i][j-1] = true;
				if(search(board,i,j-1,str.substring(1),mask)){
					return true;
				}
				mask[i][j-1] = false;//恢复现场
			}
			
			if((j<board[0].length-1) && (board[i][j+1] == str.charAt(0)) && (mask[i][j+1]==false)){
				mask[i][j+1] = false;
				
				//向前搜索
				if(search(board,i,j+1,str.substring(1),mask)){
					return true;
				}
				
				mask[i][j+1] = false;//恢复现场
			}
		}
		//如果4个方向都没有返回true，最后返回false;
		
		return false;
		
			
		
				
		
	}
	
	public static void main(String[] args) {
		char board[][] = {{'A','B','C','E'},
						  {'S','F','C','S'},
					  {'A','D','E','E'}};

				
//		char board[][] = {{'a','a','a','a'},
//						{'a','a','a','a'},
//						{'a','a','a','a'}};
		System.out.println(new Solution().exist(board, "SEE"));
	}
	
	
	/**
	 * 
	 * 看标准解法
	 * 4个方向dfs
	 */
	
	 public boolean exist2(char[][] board, String word) {  
	        //init check  
	        if(board==null || board.length==0 || board[0]==null || board[0].length==0) return false;  
	        boolean[][] visited = new boolean[board.length][board[0].length];  
	          
	        for(int i=0; i<board.length; i++) {  
	            for(int j=0; j<board[0].length; j++) {  
	                if( dfs(board, visited, i, j, word) )return true;  
	            }  
	        }  
	        return false;  
	    }  
	
	private boolean dfs(char[][] board,boolean[][] visited,int x,int y,String word){
		if(word.length() == 0) return true;
		
		if(x<0 || x>=board.length || y<0 || y>=board[0].length){
			return false;
		}
		
		if(visited[x][y] || board[x][y]!=word.charAt(0)) return false;
		visited[x][y] = true;
		
		//4个方向dfs，比自己的算法简单多了，判断都在前面执行
		boolean b = dfs(board,visited,x-1,y,word.substring(1))
				|| dfs(board,visited,x+1,y,word.substring(1))
				|| dfs(board,visited,x,y-1,word.substring(1))
				|| dfs(board,visited,x,y+1,word.substring(1));
		if(b) 
			return true;
		else{
			visited[x][y] = false;
			return false;
		}
	}
}
