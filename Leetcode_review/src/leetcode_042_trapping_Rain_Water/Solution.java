package leetcode_042_trapping_Rain_Water;
/**
 * 
 *  Given n non-negative integers representing an elevation map where the width of each bar
 *   is 1, compute how much water it is able to trap after raining.

For example,
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. 
In this case, 6 units of rain water (blue section) are being trapped. 
Thanks Marcos for contributing this image!
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	//这个算法很直观，先找左边到右递增的边界，再找从右到左递增的边界
	//然后后面的任务就是维持这个边界
	//很经典
    public int trap(int[] height) {
    	if(height.length<3){
    		return 0;
    	}
    	
    	
    	int ans = 0;
    	int l=0,r=height.length-1;
    	//find the left and right edge which can hold water
    	while(l<r && height[l]<=height[l+1]) l++;
    	while(l<r && height[r]<=height[r-1]) r--;
    	
    	while(l<r){
    		int left = height[l];
    		int right = height[r];
    		if(left<=right){
    			//add volumn until on edge Larger than the left edge
    			while(l<r && left>=height[++l]){
    				ans+=left-height[l];
    			}
    		}else{
    			//add volumn until on edge Larger than the right volum
    			while(l<r && height[--r]<=right){
    				ans +=right-height[r];
    			}
    		}
    		
    	}
    	return ans;
    }
}
