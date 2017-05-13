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
	 * 我自己的解法，时间复杂度为O(n*n) leetcode不让通过
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
			boolean flag = true;//信号灯
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
			flag = true;//信号灯
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
	 *接下来的思路就是我在网上看别人的了。
	利用一个栈，栈里保存的是下标，而且栈里保存的是递增元素的下标，	
	从前往后遍历，	
	如果栈为空或者遇见比栈顶元素大，则把下标入栈，	
	如果当前元素比栈顶元素小，则弹栈，直到栈为空或者栈顶元素比当前元素小，	
	每弹一次栈，都要计算一次矩形面积，因为栈里维持的元素是递增的，则可以保证刚弹出的元素end和当前遍历到元素i之间的值都比height[end]大，
	所以都可以加入计算矩形的宽，高度则为当前弹出的元素，	
	直到最后一个元素，如果栈还不是空的，则应该继续弹栈，直到栈空。 
	 * 
	 * @param args
	 */
	
	 public int largestRectangleArea1(int[] height) {  
         
	        int area = 0;  
	        Stack<Integer> st = new Stack<Integer>();  
	        for(int i=0; i<height.length; i++){  
	            if(st.empty() || height[i]>height[st.peek()]) //st.peek()取得栈顶元素
	                st.push(i);  
	            else{  
	            	//因为栈里维持的元素是递增的，则可以保证刚弹出的元素end和当前遍历到元素i之间的值都比height[end]大，
	            	//所以都可以加入计算矩形的宽，高度则为当前弹出的元素
	                int end = st.pop();  
	                int width = st.empty() ? i : i-st.peek()-1;  
	                area = Math.max(area, height[end]*width);  
	                i--;  
	            }  
	        }  
	        //处理最后  
	        while(!st.empty()){  
	            int end = st.pop();  //i就变味height.length
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
