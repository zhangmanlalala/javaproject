package LeetCode_67_Maximal_Rectangle;

import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle 
 * containing only 1's and return its area.
   For example, given the following matrix:
	
	1 0 1 0 0
	1 0 1 1 1
	1 1 1 1 1
	1 0 0 1 0

Return 6.  
 * 很经典
 * @author Administrator
 *
 */
public class Solution {
	
	
	/**
	 * 
	 * 我自己的解法
	 * @param matrix
	 * @return
	 */
	
	
	/**
	 * 分析：

	之所以先写68，是因为这道题是以68题为子问题的，关键就是怎么构造直方图了。

	对每一行都建直方图，

	如果第 i 行第 j 列元素为1，则第 i 行该位置高度就是第 i-1 行该位置高度+1，即 height[i][j] = height[i-1][j] + 1;

	如果此位置元素为0，则此行该位置的高度就是0，因为已经没有和上一行的1连续了，高度要重新算起了。
	 * 
	 */
	public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = m==0 ? 0:matrix[0].length;
        
        //这里用了一个小技巧，就是让每一行的直方图的最后一个元素为0
        //这样的好处是遍历到最后栈一定为空的，避免了最后在弹栈的多余代码
        int[][] height = new int[m][n+1];
        int maxArea = 0;
        //计算每一个位置的高度
        for(int i=0;i<m;i++){
        	for(int j=0;j<n;j++){
        		if(matrix[i][j] == '0'){
        			height[i][j] = 0;
        		}else{
        			height[i][j] = i==0 ? 1:height[i-1][j]+1;
        		}
        	}
        }
        //每一行都相当于有n个直方图，每一行求一次构造的最大矩形，每一行转变为问题68一模一样的问题
        for(int i=0;i<m;i++){
    		int area = maxAreaInHist(height[i]);
    		if(area>maxArea){
    			maxArea = area;
    		}
    	}
        
        return maxArea;
    }
	
	
	private int maxAreaInHist(int[] height){
		Stack<Integer> st = new Stack<Integer>();//小技巧：让数组最后一个值为零，不会有后续的代码
		int i=0;
		int max=0;
		while(i<height.length){
			if(st.isEmpty() || height[st.peek()] <=height[i]){
				st.push(i);
				i++;
			}else{//该算法的核心是栈中的元素是递增的，这样保证了当前i和弹栈之后st.peek()之间的元素一定大于等于弹出元素指向的值
				int t=st.pop();
				max = Math.max(max, height[t]*(st.isEmpty()?i:i-st.peek()-1));
			}
		}
		
		return max;
	}
	
	
}
