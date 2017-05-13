package LeetCode_105_Rotate_Image;


/**
 *You are given an n x n 2D matrix representing an image.
  Rotate the image by 90 degrees (clockwise).
Follow up:

Could you do this in-place? 
 * 
 * 
 * @author Administrator
 *
 */

/**
 * 分析，维持一个layer变量记录当前层，对于每一层，四条边上相同位置元素循环赋值，这样只要维持一个临时变量
 * @author Administrator
 *
 *
 *没懂！！
 */
public class Solution {
	public void rotate(int[][] matrix){
		if(matrix == null || matrix.length<=1)
			return;
		int n=matrix.length;
		//一层一层旋转
		for(int layer=0;layer<n/2;layer++){
			for(int i=layer;i<n-layer-1;i++){
				//四条边上同一位置元素循环赋值
				int temp = matrix[i][layer];
				matrix[i][layer] = matrix[n-layer-1][i];
				matrix[n-i-1][i] = matrix[n-i-1][n-layer-1];
				matrix[n-i-1][n-layer-1] = matrix[layer][n-i-1];
				matrix[layer][n-i-1] = temp;
			}
		}
	}
}
