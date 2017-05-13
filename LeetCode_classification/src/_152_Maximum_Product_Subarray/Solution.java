package _152_Maximum_Product_Subarray;
/**
 * Find the contiguous subarray within an array (containing at least one number) 
 * which has the largest product(乘积).
For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6. 
 * @author Administrator
 *
 */


/**
 * 
 * 
 * 动态规划，第一步先找到子问题
 * 第二步找到状态转移方程，
 * 采用从前往后或者从后往前的思维，求解子问题
 * @author Administrator
 *
 */
public class Solution {
	/**
	 * 
	 * 记录当前的最大值最小值，因为遇到负数，与最小值的乘积也有可能是最大值
	 * @param nums
	 * @return
	 */
    public int maxProduct(int[] nums) {
    	if(nums == null || nums.length<1) return 0;
    	if(nums.length<2) return nums[0];
    	
    	int global = nums[0];
    	int max = nums[0];
    	int min = nums[0];
    	for(int i=1;i<nums.length;i++){
    		int a = max*nums[i];
    		int b = min*nums[i];
    		max = Math.max(nums[i], Math.max(a, b));
    		min = Math.min(nums[i], Math.min(a, b));
    		global  = Math.max(max, global);
    	}
    	
    	return global;
    }
}
