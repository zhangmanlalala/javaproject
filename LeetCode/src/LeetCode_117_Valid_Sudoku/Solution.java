package LeetCode_117_Valid_Sudoku;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Determine if a Sudoku is valid, according to: 
 * Sudoku Puzzles – The Rules.
 * @author Administrator
 *
 */
/**
 * 具体做法：先检查行规则，再检查列规则，最后检查每个sub-box的规则。利用set的无序不可重复性，检查是不是有1-9
 * 
 * set.add()方法，如果添加成功，返回true
 * @author Administrator
 *
 */

public class Solution {

	public static void main(String[] args) {
	

	}
	
	public boolean checkValid(char[][] sudoku){
		
		Set<Character> set = new HashSet<Character>();
		for(int i=0;i<9;i++){
			set.clear();
			for(int j=0;j<9;j++){
				if(sudoku[i][j] !='.' && !set.add(sudoku[i][j])){
					return false;
				}
			}
		}
		for(int i=0;i<9;i++){
			set.clear();
			for(int j=0;j<9;j++){
				if(sudoku[j][i] !='.' && !set.add(sudoku[i][j])){
					return false;
				}
			}
		}
		
		for(int row=0;row<3;row++){
			for(int col=0;col<3;col++){
				for(int j=row*3;j<row*3+3;j++){
					for(int i=col*3;j<col*3+3;j++){
						if(sudoku[j][i] !='.' && !set.add(sudoku[i][j])){
							return false;
						}
					}
				}
			}
		}
		return true;
		
	}

}
