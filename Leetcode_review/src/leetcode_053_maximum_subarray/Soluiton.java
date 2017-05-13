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
 * �������������
 * @author Administrator
 *
 */
public class Soluiton {
	/**
	 * ��̬�滮���ؼ����ҵ������⣬��������Ϊ��
	 * dp[i]��ʾ��nums[i]��Ϊ���һ��Ԫ�����������ĺͣ���ô״̬ת�Ʒ��̾ͺܺ�д
	 * ���dp[i-1]>0 ��dp[i] = nums[i]+dp[i-1]
	 * ����dp[i] = A[i];
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
