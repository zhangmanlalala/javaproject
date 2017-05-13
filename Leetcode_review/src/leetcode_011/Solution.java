package leetcode_011;

import java.util.Stack;

/**
 * 
 * 
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 装最大量水的问题，使用栈，栈中一直存放递增的元素的下标
	 * @param height
	 * @return
	 */
    public int maxArea(int[] height) {
    	   int left = 0, right = height.length - 1;
    		int maxArea = 0;

    		while (left < right) {
    			maxArea = Math.max(maxArea, Math.min(height[left], height[right])
    					* (right - left));
    			if (height[left] < height[right])
    				left++;
    			else
    				right--;
    		}

    		return maxArea;
    }
}
