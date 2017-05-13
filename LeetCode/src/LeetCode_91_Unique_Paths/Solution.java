package LeetCode_91_Unique_Paths;

import sun.applet.Main;

/**
 *A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. The robot is trying to reach the 
bottom-right corner of the grid (marked 'Finish' in the diagram below).
How many possible unique paths are there? 
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	public static int pathNum(int m,int n){
		if(m-1==0 || n-1==0){
			return 1;
		}else if(m-1<0 || n-1<0){
			return -1;
		}
		int result[][] = new int[m][n];
					
		result[0][0] = 0;
		for(int i=0;i<=m-1;i++){
			result[i][0] = 1;
			result[0][0] = 0;
			for(int j=1;j<=n-1;j++){
				if(i == 0){
					result[i][j] = 1;
				}else{
					result[i][j] = result[i-1][j]+result[i][j-1];
				}
			}
		}
		return result[m-1][n-1];
		
		
	}
	
	public static void main(String[] args) {
		System.out.println(pathNum(3,3));
	}
	
}
