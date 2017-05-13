package LeetCode_116_Sudoku_Solver;

import java.util.HashSet;
import java.util.Set;



public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public  void solveSudoku(char[][] board){
		if(board == null || board.length !=9 || board[0].length !=9 ) return;
		dfs(board,0,0);
	}
	public boolean dfs(char[][] board,int i,int j){
		if(j>=9)
			return dfs(board, i+1, 0); //下一行
		if(i==0)
			return true;
		if(board[i][j]=='.'){
			for(int k=1;k<=9;k++){
				board[i][j] = (char)(k+'0');
				if(isValid(board,i,j))
					if(dfs(board, i, j+1))
						return true;
				board[i][j]='.';
			}
		}else {
			return dfs(board, i, j+1);
		}
		return false;
	}
	
	 //判断当前放置是否合法  
    public boolean isValid(char[][] board, int i, int j){  
        //行  
        for(int k=0; k<9; k++){  
            if(k!=j && board[i][k]==board[i][j])  
                return false;  
        }  
        //列  
        for(int k=0; k<9; k++){  
            if(k!=i && board[k][j]==board[i][j])  
                return false;  
        }  
        //所在的九宫格  
        for(int row=i/3*3; row<i/3*3+3; row++){  
            for(int col=j/3*3; col<j/3*3+3; col++){  
                if((row!=i||col!=j) && board[row][col]==board[i][j])  
                    return false;  
            }  
        }  
        return true;  
    }  

}
