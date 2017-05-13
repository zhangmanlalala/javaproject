package leetcode_053_maximum_subarray;
/**
 * 
 *  Find the contiguous subarray within an array (containing at least one number) 
 *  which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6. 
 * @author Administrator
 *
 */
/**
 * 
 * 最大子数组问题
 * @author Administrator
 *
 */
public class Soluiton {
	/**
	 * 动态规划，关键是找到子问题，子问题设为：
	 * dp[i]表示以nums[i]作为最后一个元素最大子数组的和，那么状态转移方程就很好写
	 * 如果dp[i-1]>0 则dp[i] = nums[i]+dp[i-1]
	 * 否则dp[i] = A[i];
	 * @param nums
	 * @return
	 */
    public int maxSubArray(int[] nums) {
    	if(nums==null || nums.length == 0){
    		return 0;
    	}
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for(int i=1;i<nums.length;i++){
        	if(dp[i-1]>0){
        		dp[i] = nums[i]+ dp[i-1];
        	}else{
        		dp[i] = nums[i];
        	}

        	if(dp[i]>max){
        		max = dp[i]; 
        	}
        }
        
        return max;
        
        
    }
}
