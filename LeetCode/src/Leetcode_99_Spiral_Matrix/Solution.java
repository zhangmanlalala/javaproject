package Leetcode_99_Spiral_Matrix;

public class Solution {
	
	public int[] spiralMatrix(int[][] arr){
		int x=0;
		int y=0;
		int row=arr.length;
		int col=arr[0].length;
		int col_temp = col;
		int row_temp = row;
		int[] newArr = new int[row*col];
		int round=0;
		int j=0;
		while(row>0 && col>0){
			for(int i=0;i<2*(row+col)-4;i++){
				newArr[j] = arr[y][x]; 
				j++;
				if(x<col_temp-1 && y<row_temp-1){
					x++;
					continue;
					
				} 
				if(x==col_temp-1 && y<row_temp-1){
					y++;
					continue;
					
				}
				if(x>round && y==row_temp-1){	
					x--;
					continue;
					
				}
				if(x==round && y>round+1 ){
					y--;
				}
			}
			if(row==1 && col==1){ //处理特殊的情况
				newArr[j] = arr[row_temp/2][col_temp/2];
			}
			row = row-2;
			col = col-2;
			round++;
			col_temp--;
			row_temp--;
			x = round;
			y = round;
		}
		
		return newArr;		
	}
	
	public static void main(String[] args) {
		int arr[][] = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
		int[] result = new Solution().spiralMatrix(arr);
		for(int i=0;i<result.length;i++){
			System.out.println(result[i]);
		}
	}
}
