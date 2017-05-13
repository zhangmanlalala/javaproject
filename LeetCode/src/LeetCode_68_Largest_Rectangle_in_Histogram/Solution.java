package LeetCode_68_Largest_Rectangle_in_Histogram;

import java.util.Stack;

/**
 * 
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,

Given height = [2,1,5,6,2,3],

return 10.
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * ���Լ��Ľⷨ��ʱ�临�Ӷ�ΪO(n*n) leetcode����ͨ��
	 * @param heights
	 * @return
	 */
	public int largestRectangleArea(int[] heights) {
        
		if(heights == null || heights.length==0){
			return 0;
		}
		if(heights.length == 1){
			return heights[0];
		}
		int left = 0;
		int right = 0;
		int same_num = 0;		
		int Max_rec = 0;
		
		for(int i=0;i<heights.length;){
			
			if(heights[i] == 0 || (i-1>=0 && heights[i]==heights[i-1]) ){
				i++;
				continue;
			}
			int j = i-1;
			boolean flag = true;//�źŵ�
			while(j>=0){
				if(heights[j]>heights[i]){
					flag = false;
					j--;
				}else if(heights[j]==heights[i]){
					j--;
					
				}else{
					break;
				}
			}
			if(!flag){
				left = ++j;
			}
			
			same_num = i;
			flag = true;//�źŵ�
			j = i+1;
			while(j<heights.length){
				if(heights[j]>heights[i]){				
					flag = false;
					j++;
				}else if(heights[j] == heights[i]){
					if(flag){
						same_num++;
					}
					j++;
				}else{
					break;
				}
			}
			right = --j;
			
			int rec = ((right-left)+1)*heights[i];
			if(rec>Max_rec){
				Max_rec = rec;
			}
			
			i = (same_num>i)? same_num:i+1;
			
		}
		return Max_rec;
		
    }
	
	
	/**
	 *��������˼·�����������Ͽ����˵��ˡ�
	����һ��ջ��ջ�ﱣ������±꣬����ջ�ﱣ����ǵ���Ԫ�ص��±꣬	
	��ǰ���������	
	���ջΪ�ջ���������ջ��Ԫ�ش�����±���ջ��	
	�����ǰԪ�ر�ջ��Ԫ��С����ջ��ֱ��ջΪ�ջ���ջ��Ԫ�رȵ�ǰԪ��С��	
	ÿ��һ��ջ����Ҫ����һ�ξ����������Ϊջ��ά�ֵ�Ԫ���ǵ����ģ�����Ա�֤�յ�����Ԫ��end�͵�ǰ������Ԫ��i֮���ֵ����height[end]��
	���Զ����Լ��������εĿ��߶���Ϊ��ǰ������Ԫ�أ�	
	ֱ�����һ��Ԫ�أ����ջ�����ǿյģ���Ӧ�ü�����ջ��ֱ��ջ�ա� 
	 * 
	 * @param args
	 */
	
	 public int largestRectangleArea1(int[] height) {  
         
	        int area = 0;  
	        Stack<Integer> st = new Stack<Integer>();  
	        for(int i=0; i<height.length; i++){  
	            if(st.empty() || height[i]>height[st.peek()]) //st.peek()ȡ��ջ��Ԫ��
	                st.push(i);  
	            else{  
	            	//��Ϊջ��ά�ֵ�Ԫ���ǵ����ģ�����Ա�֤�յ�����Ԫ��end�͵�ǰ������Ԫ��i֮���ֵ����height[end]��
	            	//���Զ����Լ��������εĿ��߶���Ϊ��ǰ������Ԫ��
	                int end = st.pop();  
	                int width = st.empty() ? i : i-st.peek()-1;  
	                area = Math.max(area, height[end]*width);  
	                i--;  
	            }  
	        }  
	        //�������  
	        while(!st.empty()){  
	            int end = st.pop();  //i�ͱ�ζheight.length
	            int width = st.empty() ? height.length : height.length-st.peek()-1;  
	            area = Math.max(area, height[end]*width);  
	        }  
	        return area;  
	    }  
	
	
	
	public static void main(String[] args) {
		int heights[] = {2,1,5,6,2,3};
		System.out.println(new Solution().largestRectangleArea(heights));
	}
}
