package leetcode_079_word_search;
/**
 * 
 * Given a 2D board and a word, find if the word exists in the grid.

	The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

	For example,
	Given board =
	
	[
	  ['A','B','C','E'],
	  ['S','F','C','S'],
	  ['A','D','E','E']
	]
	
	word = "ABCCED", -> returns true,
	word = "SEE", -> returns true,
	word = "ABCB", -> returns false. 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
    public boolean exist(char[][] board, String word) {
    	if(board == null || board.length==0 || board[0].length==0 || word==null){
    		return false;
    	}
    	
    	boolean isUsed[][] = new boolean[board.length][board[0].length];
    	for(int i=0;i<board.length;i++){
    		for(int j=0;j<board[0].length;j++){
    			if(board[i][j] ==word.charAt(0)){
    				bfs(board,i,j,word,0,isUsed);
    				if(isExist){
    					return true;
    				}
    			}
    		}
    			
    	}
    	
    	return false;
    }
    
    public boolean isExist = false;
    public void bfs(char[][] board,int row,int col,String word,int start,boolean[][] isUsed){
    	
    	if(start==word.length() || isExist){
    		isExist = true;
    		return ;
    	}	
    	if(row<0 || row>=board.length || col<0 || col>=board[0].length){
    		return;
    	}
    	if(isUsed[row][col]){
    		return;
    	}
    	
    	
    	if(board[row][col] == word.charAt(start)){ 
    		isUsed[row][col] = true;
    		bfs(board,row+1,col,word,start+1,isUsed);
    		bfs(board,row-1,col,word,start+1,isUsed);
    		bfs(board,row,col+1,word,start+1,isUsed);
    		bfs(board,row,col-1,word,start+1,isUsed);
    		isUsed[row][col] = false;
    	}else{
    		return;
    	}
    	
    	
    	
    }
    
    public static void main(String[] args) {
    	char[][] board = new char[][]{{'A','B','C','E'},
    	                              {'S','F','C','S'},
    	                              {'A','D','E','E'}};
		System.out.println(new Solution().exist(board, "ABCB"));
	}
}
