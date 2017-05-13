package _213_House_Robber_II;
/**
 * This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for 
his thievery so that he will not get too much attention. This time, all houses at this 
place are arranged in a circle. That means the first house is the neighbor of the last 
one. Meanwhile, the security system for these houses remain the same as for those in the
 previous street.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police. 
 * 
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * 照样使用动态规划，可以把数组nums拆成两半，nums{0~len-2},nums{1,len-1},分别求两个数组的最大值，作比较
	 * 求出状态转移方程
	 * @param nums
	 * @return
	 */
    public int rob(int[] nums) {
    	if(nums== null || nums.length==0){
    		return 0;
    	}
    	if(nums.length == 1){
    		return nums[0];
    	}
        int[] pre = new int[nums.length];
        int[] last = new int[nums.length];
        
        pre[0] = 0;//数组长度为1
        pre[1] = nums[0];//数组长度为2
        
        last[0] = 0;
        last[1] = nums[1];
        for(int i=2;i<nums.length;i++){//数组长度从3开始
        	pre[i] = Math.max(nums[i-1]+pre[i-2], pre[i-1]);
        	last[i] = Math.max(nums[i]+last[i-2],last[i-1]);
        }
        
        return Math.max(pre[nums.length-1], last[nums.length-1]);
    }
}
