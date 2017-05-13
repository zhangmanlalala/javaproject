package LeetCode_94_Spiral_Matrix_II;

/**
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

	For example,
	Given n = 3,

You should return the following matrix:
    [  
     [ 1, 2, 3 ],  
     [ 8, 9, 4 ],  
     [ 7, 6, 5 ]  
    ]  
 * 
 * @author Administrator
 *
 */

/**
 * 
 * 
 * 分析一层一层的赋值
 * @author Administrator
 *
 */
public class Solution {
	
		public int[][] spiralMatrix2(int n){
			int result[][] = new int[n][n];
			int length = n;
			int val = 1;
			for(int lay=0;lay<n/2;lay++){
				for(int col=lay;col<lay+length;lay++){//上
					result[lay][col] = val++;
				}
				
				for(int row=lay+1;row<lay+length;row++){//右
					result[row][lay+length-1] = val++;
				}
				for(int col=lay+length-2;col>=lay;col--){//下
					result[lay+length-1][col] = val++;
				}
				for(int row=lay+length-2;row>=lay;row--){//左
					result[row][lay] = val++;
 				}
				length = length-2;//每一圈的长度减去2
			}
			if(n%2!=0){
				result[n/2][n/2] = val++;
			}
			return result;
					
		}
}
