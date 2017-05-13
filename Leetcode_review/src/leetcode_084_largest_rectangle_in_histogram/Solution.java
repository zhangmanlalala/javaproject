package leetcode_084_largest_rectangle_in_histogram;

import java.util.Stack;

/**
 * 
 *  Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given heights = [2,1,5,6,2,3],
return 10. 
 * 
 * @author Administrator
 *
 */
public class Solution {
    public int largestRectangleArea(int[] heights) {
    	
    	if(heights==null || heights.length == 0){
    		return 0;
    	}
    	
    	Stack<Integer> ss = new Stack<Integer>();
    	int maxRec = Integer.MIN_VALUE;
    	
    	for(int i=0;i<heights.length;i++){
    		if(ss.isEmpty()){
    			ss.push(i);
    		}else if(heights[i]>=heights[ss.peek()]){
    			ss.push(i);
    		}else{
    	
    			int idx = ss.pop();
    			int len = 0;
    			if(ss.isEmpty()){
    				len = i;
    			}else{
    				len = i-ss.peek()-1;
    			}
    			int area = heights[idx]*len;
    				maxRec = Math.max(maxRec, area);
    				i--;
    		}
    		
    	}
    	

		while(!ss.isEmpty()){
			int idx = ss.pop();
			int len = 0;
			if(ss.isEmpty()){
				len = heights.length ;
			}else{
				len = heights.length-idx;
			}
			int area = heights[idx]*len;
			maxRec = Math.max(maxRec, area);
		}
		
    	
    	
    	return maxRec;
    	
    	
    }
}
