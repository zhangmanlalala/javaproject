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
 * �ܾ���
 * @author Administrator
 *
 */
public class Solution {
	
	
	/**
	 * 
	 * ���Լ��Ľⷨ
	 * @param matrix
	 * @return
	 */
	
	
	/**
	 * ������

	֮������д68������Ϊ���������68��Ϊ������ģ��ؼ�������ô����ֱ��ͼ�ˡ�

	��ÿһ�ж���ֱ��ͼ��

	����� i �е� j ��Ԫ��Ϊ1����� i �и�λ�ø߶Ⱦ��ǵ� i-1 �и�λ�ø߶�+1���� height[i][j] = height[i-1][j] + 1;

	�����λ��Ԫ��Ϊ0������и�λ�õĸ߶Ⱦ���0����Ϊ�Ѿ�û�к���һ�е�1�����ˣ��߶�Ҫ���������ˡ�
	 * 
	 */
	public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = m==0 ? 0:matrix[0].length;
        
        //��������һ��С���ɣ�������ÿһ�е�ֱ��ͼ�����һ��Ԫ��Ϊ0
        //�����ĺô��Ǳ��������ջһ��Ϊ�յģ�����������ڵ�ջ�Ķ������
        int[][] height = new int[m][n+1];
        int maxArea = 0;
        //����ÿһ��λ�õĸ߶�
        for(int i=0;i<m;i++){
        	for(int j=0;j<n;j++){
        		if(matrix[i][j] == '0'){
        			height[i][j] = 0;
        		}else{
        			height[i][j] = i==0 ? 1:height[i-1][j]+1;
        		}
        	}
        }
        //ÿһ�ж��൱����n��ֱ��ͼ��ÿһ����һ�ι���������Σ�ÿһ��ת��Ϊ����68һģһ��������
        for(int i=0;i<m;i++){
    		int area = maxAreaInHist(height[i]);
    		if(area>maxArea){
    			maxArea = area;
    		}
    	}
        
        return maxArea;
    }
	
	
	private int maxAreaInHist(int[] height){
		Stack<Integer> st = new Stack<Integer>();//С���ɣ����������һ��ֵΪ�㣬�����к����Ĵ���
		int i=0;
		int max=0;
		while(i<height.length){
			if(st.isEmpty() || height[st.peek()] <=height[i]){
				st.push(i);
				i++;
			}else{//���㷨�ĺ�����ջ�е�Ԫ���ǵ����ģ�������֤�˵�ǰi�͵�ջ֮��st.peek()֮���Ԫ��һ�����ڵ��ڵ���Ԫ��ָ���ֵ
				int t=st.pop();
				max = Math.max(max, height[t]*(st.isEmpty()?i:i-st.peek()-1));
			}
		}
		
		return max;
	}
	
	
}
